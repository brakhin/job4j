package ru.bgbrakhi.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {
	private Connection connection;

	public TrackerSQL(Connection connection) {
		this.connection = connection;
	 }

	public boolean init() {
		try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
			Properties config = new Properties();
			config.load(in);
			Class.forName(config.getProperty("driver-class-name"));
			this.connection = DriverManager.getConnection(
					config.getProperty("url"),
					config.getProperty("username"),
					config.getProperty("password")
			);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return this.connection != null;
	}

	/**
	* добавляет заявку в трекер
	*/
	public Item add(Item item) {
		try (final PreparedStatement statement = this.connection.prepareStatement(
					"insert into item(name, description, created, comments) values(?, ?, ?, ?);")) {
			statement.setString(1, item.getName());
			statement.setString(2, item.getDesc());
			statement.setLong(3, item.getCreated());
			statement.setString(4, item.getComments());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}
	/**
	* заменяет заявку в трекере
	*/
	public boolean replace(String id, Item item) {
		boolean result = false;
		try (PreparedStatement statement = connection.prepareStatement(
					"update item set name = ?, description = ?, created = ?, comment = ? where id_s = ?;",
					Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, item.getName());
			statement.setString(2, item.getDesc());
			statement.setLong(3, item.getCreated());
			statement.setString(4, item.getComments());
			statement.setString(5, id);
			statement.executeUpdate();
			try (ResultSet rs = statement.getGeneratedKeys()) {
				result = rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	* удаляет заявку из трекера
	* вначале определяется индекс найденной заявки
	* затем массив Item[] схлопывается в найденной позиции и обрезается с хвоста
	*/
	public boolean delete(String id) {
		boolean result = false;
		try (PreparedStatement statement = connection.prepareStatement(
					"delete from item where id_s = ?;",
					Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, id);
			statement.executeUpdate();
			try (ResultSet rs = statement.getGeneratedKeys()) {
				result = rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	* выводит все заявки трекера
	*/
	public List<Item> findAll() {
		List<Item> result = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement("select * from item;");
			 ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				result.add(new Item(
						rs.getString("name"),
						rs.getString("description")
				));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	* ищет заявки по имени
	*/
	public List<Item> findByName(String key) {
		List<Item> result = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement("select * from item where name = ?;")) {
			statement.setString(1, key);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					result.add(new Item(
							rs.getString("name"),
							rs.getString("description")
					));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	* ищет заявки по Id
	*/
	public Item findById(String id) {
		Item result = null;
		try (PreparedStatement statement = connection.prepareStatement("select * from item where id_s = ?;")) {
			statement.setString(1, id);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					result = new Item(
							rs.getString("name"),
							rs.getString("description"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void close() throws Exception {
		connection.close();
	}
}

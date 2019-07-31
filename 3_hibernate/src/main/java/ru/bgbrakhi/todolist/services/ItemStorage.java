package ru.bgbrakhi.todolist.services;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ru.bgbrakhi.todolist.models.Item;
import ru.bgbrakhi.todolist.utils.HibernateUtil;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class ItemStorage implements IStore {
    private final static ItemStorage ourInstance = new ItemStorage();

    public static ItemStorage getInstance() {
        return ourInstance;
    }

    public void addItem(Item item) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            session.save(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateItem(Item item) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            session.update(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<Item> getAll() {
        List<Item> result = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            result = session.createQuery("from Item").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Item getItem(Long id) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(Item.class, id);
    }

    public static void main(String[] args) {
        Item item = new Item();
        item.setDescription("desc2");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        List<Item> result = ItemStorage.getInstance().getAll();
        ItemStorage.getInstance().addItem(item);
    }
}





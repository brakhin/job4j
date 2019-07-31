package ru.bgbrakhi.todolist.services;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ru.bgbrakhi.todolist.models.Item;
import ru.bgbrakhi.todolist.utils.HibernateUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class ItemStorage implements IStore {
    private final static ItemStorage INSTANCE = new ItemStorage();

    public static ItemStorage getInstance() {
        return INSTANCE;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private void tx(final Consumer<Session> command) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void addItem(Item item) {
        this.tx((Function<Session, Serializable>) session -> session.save(item));
    }

    public void updateItem(Item item) {
        this.tx((Consumer<Session>) session -> session.update(item));
    }

    public List<Item> getAll() {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Item").list();
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





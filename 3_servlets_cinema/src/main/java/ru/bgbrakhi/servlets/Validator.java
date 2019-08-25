package ru.bgbrakhi.servlets;

import java.sql.Connection;
import java.util.concurrent.CopyOnWriteArrayList;

public class Validator implements IStore {

    private final IStore store = DbStore.getInstance();

    private static final Validator INSTANCE = new Validator();
    private static Class clazz = Validator.class;
    private Validator() {

    }

    public static Validator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean updateSeat(Seat seat, Connection outerConnection) {
        return store.updateSeat(seat, outerConnection);
    }

    @Override
    public CopyOnWriteArrayList<Seat> getSeats() {
        return store.getSeats();
    }

    @Override
    public Seat getSeat(int id) {
        return store.getSeat(id);
    }

    @Override
    public void createAccount(Account account) {
        store.createAccount(account);
    }
}

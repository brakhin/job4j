package ru.bgbrakhi.cinema;

import java.sql.Connection;
import java.util.concurrent.CopyOnWriteArrayList;

public interface IStore {
    boolean updateSeat(Seat seat, Connection outerConnection);
    CopyOnWriteArrayList<Seat> getSeats();
    Seat getSeat(int id);
    void createAccount(Account account);
}

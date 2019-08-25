package ru.bgbrakhi.servlets;

public class Seat {
    private int id = -1;
    private int line;
    private int col;
    private long account = -1L;

    public Seat(int id, int line, int col, int account) {
        this.id = id;
        this.line = line;
        this.col = col;
        this.account = account;
    }

    public int getId() {
        return id;
    }
    public int getLine() {
        return line;
    }
    public int getCol() {
        return col;
    }
    public long getIdAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }
}

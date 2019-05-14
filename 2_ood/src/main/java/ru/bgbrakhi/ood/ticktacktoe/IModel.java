package ru.bgbrakhi.ood.ticktacktoe;

public interface IModel {
    boolean emptyCell(int line, int col);
    void set(int value, int line, int col);
    int get(int line, int col);
    int size();
    Position getRandomPos();
    boolean gameOver();
    String getWinner();
    void initData();
}

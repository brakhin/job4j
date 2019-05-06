package ru.bgbrakhi.ood.ticktacktoe;

public class Position {
    private int line;
    private int col;

    public Position(int line, int col) {
        this.line = line;
        this.col = col;
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }
}

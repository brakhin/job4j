package ru.bgbrakhi.multithread.bomberman;

import java.util.Random;

public class Hero {
    private static final int MOVE_LEFT = 0;
    private static final int MOVE_RIGHT = 1;
    private static final int MOVE_UP = 2;

    private int line = 0;
    private int col = 0;

    private final Board board;

    public Hero(Board board) {
        this.board = board;
    }

    public boolean move() {
        suggestPosition();
        return board.move(line, col);
    }

    private void suggestPosition() {

        int oldLine;
        int oldCol;
        int direction;
        boolean validMove;

        do {
            oldLine = line;
            oldCol = col;

            direction = getDirection();

            if (direction == MOVE_LEFT) {
                col--;
            } else if (direction == MOVE_RIGHT) {
                col++;
            } else if (direction == MOVE_UP) {
                line--;
            } else { // DOWN
                line++;
            }

            validMove = col >= 0 && col < board.getWidth() && line >= 0 && line < board.getHeight();
            if (!validMove) {
                line = oldLine;
                col = oldCol;
            }

        } while (!validMove);

    }

    private int getDirection() {
        Random rnd = new Random();
        return rnd.nextInt(4);
    }
}

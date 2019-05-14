package ru.bgbrakhi.ood.ticktacktoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model implements IModel {

    private int size = 3;
    private int[][] data;
    private List<Integer> emptyPos = new ArrayList<>();
    private int winner = 0;

    public Model(int size) {
        this.size = size;
        initData();
    }

    public Model() {
        initData();
    }

    @Override
    public void initData() {
        data = new int[this.size][this.size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = 0;
            }
        }
        for (int i = 0; i < size * size; i++) {
            emptyPos.add(i);
        }
    }

    @Override
    public boolean emptyCell(int line, int col) {
        return (data[line][col] == 0);
    }

    @Override
    public int get(int line, int col) {
        return data[line][col];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void set(int value, int line, int col) {
        if (emptyCell(line, col)) {
            data[line][col] = value;
            int pos = line * size + col;
            emptyPos.remove(emptyPos.indexOf(pos));
        }
    }

    @Override
    public Position getRandomPos() {
        Position result = null;
        if (emptyPos.size() > 0) {
            Random rnd = new Random();
            int index = rnd.nextInt(emptyPos.size());
            int pos = emptyPos.get(index);
            result = new Position(pos / size, pos % size);
        }
        return result;
    }

    private boolean checkColsGameOver() {
        boolean result = false;
        int oneCount, twoCount;
        for (int col = 0; col < size; col++) {
            oneCount = 0;
            twoCount = 0;
            for (int line = 0; line < size; line++) {
                if (data[line][col] == 1) {
                    oneCount++;
                } else if (data[line][col] == 2) {
                    twoCount++;
                }
            }
            if (oneCount == size) {
                winner = 1;
                result = true;
            } else if (twoCount == size) {
                winner = 2;
                result = true;
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean checkLinesGameOver() {
        boolean result = false;
        int oneCount, twoCount;
        for (int line = 0; line < size; line++) {
            oneCount = 0;
            twoCount = 0;
            for (int col = 0; col < size; col++) {
                if (data[line][col] == 1) {
                    oneCount++;
                } else if (data[line][col] == 2) {
                    twoCount++;
                }
            }
            if (oneCount == size) {
                winner = 1;
                result = true;
            }  else if (twoCount == size) {
                winner = 2;
                result = true;
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean checkDiag1GameOver() {
        boolean result = false;
        int oneCount = 0;
        int twoCount = 0;
        for (int i = 0; i < size; i++) {
            if (data[i][i] == 1) {
                oneCount++;
            } else if (data[i][i] == 2) {
                twoCount++;
            }
        }
        if (oneCount == size) {
            winner = 1;
            result = true;
        } else if (twoCount == size) {
            winner = 2;
            result = true;
        }
        return result;
    }

    private boolean checkDiag2GameOver() {
        boolean result = false;
        int oneCount = 0;
        int twoCount = 0;
        for (int i = 0; i < size; i++) {
            if (data[i][size - i - 1] == 1) {
                oneCount++;
            } else if (data[i][size - i - 1] == 2) {
                twoCount++;
            }
        }
        if (oneCount == size) {
            winner = 1;
            result = true;
        } else if (twoCount == size) {
            winner = 2;
            result = true;
        }
        return result;
    }

    @Override
    public boolean gameOver() {
        boolean result;
        if (emptyPos.size() > 0) {
            result = checkColsGameOver()
                    || checkLinesGameOver()
                    || checkDiag1GameOver()
                    || checkDiag2GameOver();
        } else {
            result = true;
            winner = 0;
        }
        return result;
    }

    @Override
    public String getWinner() {
        return winner == 1 ? "CROSS" : winner == 2 ? "ZERO" : "NOBODY";
    }
}

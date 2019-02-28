package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {

        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {

        Cell[] result;
        if (isDiagonal(source, dest)) {
            int cnt = Math.abs(dest.x - source.x) + 1;
            result = new Cell[cnt - 1];
            int deltaX = source.x < dest.x ? 1 : -1;
            int deltaY = source.y < dest.y ? 1 : -1;
            for (int i = 1; i < cnt; i++) {
                result[i - 1] = getCellByPosition(source.x + deltaX * i, source.y + deltaY * i);
            }
        } else {
            result = new Cell[] {};
        }
        return result;
//        return new Cell[] { dest };
    }

    private Cell getCellByPosition(int x, int y) {
        Cell result = null;
        for (Cell cell : Cell.values()) {
            if(cell.x == x && cell.y == y) {
                result = cell;
                break;
            }
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

    private boolean isDiagonal(Cell source, Cell dest) {
        int delta = Math.abs(dest.x - source.x);
        return (dest.y == source.y - delta ||  dest.y == source.y + delta);
    }

}

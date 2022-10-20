package com.slimemold.board;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private final Set<Cell> cells;
    private final int width;
    private final int height;

    public Board(int width, int height) {
        this.cells = new HashSet<>();
        this.width = width;
        this.height = height;
    }

    public void moveCells() {
        for (Cell cell : cells) {
            cell.move(this);
        }
    }

    public Cell getCell(int x, int y) {
        for (Cell cell : cells) {
            if (cell.getxCoordinate() == x && cell.getyCoordinate() == y) {
                return cell;
            }
        }
        return null;
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

package com.slimemold.board;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private final Set<Cell> CELLS;
    private final Cell[][] BOARD;
    private final int WIDTH;
    private final int HEIGHT;

    public Board(int width, int height) {
        this.CELLS = new HashSet<>();
        this.WIDTH = width;
        this.HEIGHT = height;
        this.BOARD = new Cell[height][width];
    }

    public void fillBoard() {
        for (Cell cell : CELLS) {
            BOARD[cell.getyCoordinate()][cell.getxCoordinate()] = cell;
        }
    }

    public void handleCells() {
        Set<Cell> cells = Set.copyOf(this.CELLS);
        for (Cell cell : cells) {
            if (cell instanceof LiveCell liveCell) {
                liveCell.move(this);
            } else if (cell instanceof Trail trail) {
                trail.decrease(this);
            }
        }
    }

    public void addCell(Cell cell) {
        CELLS.add(cell);
    }

    public void addTrail(Trail trail) {
        CELLS.add(trail);
    }

    public void removeTrail(Trail trail) {
        CELLS.remove(trail);
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public Set<Cell> getCells() {
        return CELLS;
    }

    public Cell getCell(int y, int x) {
        return BOARD[y][x];
    }

    public void setCell(int y, int x, Cell cell) {
        BOARD[y][x] = cell;
    }
}

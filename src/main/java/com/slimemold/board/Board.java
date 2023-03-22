package com.slimemold.board;

import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private final Set<Cell> CELLS;
    private final Trail[][] BOARD;
    private final int WIDTH;
    private final int HEIGHT;

    public Board(int width, int height) {
        this.CELLS = new HashSet<>();
        this.WIDTH = width;
        this.HEIGHT = height;
        this.BOARD = new Trail[height][width];
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
        if (BOARD[trail.yCoordinate][trail.xCoordinate] != null) {
            Color trailColor = trail.getColor();
            Color boardColor = BOARD[trail.yCoordinate][trail.xCoordinate].getColor();
            CELLS.remove(BOARD[trail.yCoordinate][trail.xCoordinate]);
            double r = trailColor.getRed() + boardColor.getRed();
            double g = trailColor.getGreen() + boardColor.getGreen();
            double b = trailColor.getBlue() + boardColor.getBlue();
            Color color = Color.color(Math.min(1,r), Math.min(1,g), Math.min(1,b));
            Trail newTrail = new Trail(color, trail.xCoordinate, trail.yCoordinate, trail.getDirection());
            BOARD[trail.yCoordinate][trail.xCoordinate] = newTrail;
            CELLS.add(newTrail);
        } else {
            CELLS.add(trail);
            BOARD[trail.yCoordinate][trail.xCoordinate] = trail;
        }
    }

    public void removeTrail(Trail trail) {
        CELLS.remove(trail);
        BOARD[trail.yCoordinate][trail.xCoordinate] = null;
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
}

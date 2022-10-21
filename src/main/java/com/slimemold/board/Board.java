package com.slimemold.board;

import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private final Set<LiveCell> liveCells;
    private final Set<Trail> trails;
    private final Cell[][] board;
//    private final Color[][] board;
    private final int width;
    private final int height;

    public Board(int width, int height) {
        this.liveCells = new HashSet<>();
        this.trails = new HashSet<>();
        this.width = width;
        this.height = height;
        this.board = new Cell[height][width];
//        this.board = new Cell[height][width];
    }

    public void fillBoard() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
//                this.board[y][x] = getLiveCell(x, y) != null ? getLiveCell(x, y).getColor() :
//                        (getTrail(x, y) != null ? getLiveCell(x, y).getColor() : Color.BLACK);
                this.board[y][x] = getLiveCell(x, y) != null ? getLiveCell(x, y) :
                        (getTrail(x, y) != null ? getLiveCell(x, y) : null);
            }
        }
    }

    public void moveCells() {
        for (LiveCell cell : liveCells) {
            cell.move(this);
        }
    }

    public void fadeTrails() {
        Set<Trail> toRemove = new HashSet<>();
        for (Trail trail : trails) {
            if (trail.getIntensity() > 0) {
                trail.decrease();
            } else {
                toRemove.add(trail);
            }
        }
        trails.removeAll(toRemove);
    }

    public LiveCell getLiveCell(int x, int y) {
        return liveCells.stream()
                .filter(liveCell -> liveCell.getxCoordinate() == x && liveCell.getyCoordinate() == y)
                .findFirst().orElse(null);
    }

    public Trail getTrail(int x, int y) {
        return trails.stream()
                .filter(trail -> trail.getxCoordinate() == x && trail.getyCoordinate() == y)
                .findFirst().orElse(null);
    }

    public void addCell(LiveCell cell) {
        liveCells.add(cell);
    }

    public void addTrail(Trail trail) {
        trails.add(trail);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getBoard() {
        return board;
    }
}

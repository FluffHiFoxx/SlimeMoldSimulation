package com.slimemold.board;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private final Set<LiveCell> liveCells;
    private final Set<Trail> trails;
    private final Cell[][] board;
    private final int width;
    private final int height;

    public Board(int width, int height) {
        this.liveCells = new HashSet<>();
        this.trails = new HashSet<>();
        this.width = width;
        this.height = height;
        this.board = new Cell[height][width];
    }

    public void fillBoard() {
        for (LiveCell liveCell : liveCells) {
            board[liveCell.getyCoordinate()][liveCell.getxCoordinate()] = liveCell;
        }
        for (Trail trail : trails) {
            board[trail.getyCoordinate()][trail.getxCoordinate()] = trail;
        }
    }

    public void moveCells() {
        for (LiveCell cell : liveCells) {
            cell.move(this);
        }
    }

    public void fadeTrails() {
        Set<Trail> trails = Set.copyOf(this.trails);
        for (Trail trail : trails) {
            trail.decrease(this);
        }
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

    public void removeTrail(Trail trail) {
        trails.remove(trail);
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

    public Cell getCell(int y, int x) {
        return board[y][x];
    }

    public void setCell(int y, int x, Cell cell) {
        board[y][x] = cell;
    }
}

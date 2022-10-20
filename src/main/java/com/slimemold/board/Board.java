package com.slimemold.board;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private final Set<LiveCell> liveCells;
    private final Set<Trail> trails;
    private final int width;
    private final int height;

    public Board(int width, int height) {
        this.liveCells = new HashSet<>();
        this.trails = new HashSet<>();
        this.width = width;
        this.height = height;
    }

    public void moveCells() {
        for (LiveCell liveCell : liveCells) {
            liveCell.move(this);
        }
    }

    public void fadeTrails() {
        for (Trail trail : trails) {
            if (trail.getIntensity() > 0) {
                trail.decrease();
            } else {
                trails.remove(trail);
            }
        }
    }

    public LiveCell getCell(int x, int y) {
        return liveCells.stream().filter(liveCell -> liveCell.getxCoordinate() == x && liveCell.getyCoordinate() == y)
                .findFirst().orElse(null);
//        for (LiveCell liveCell : liveCells) {
//            if (liveCell.getxCoordinate() == x && liveCell.getyCoordinate() == y) {
//                return liveCell;
//            }
//        }
//        return null;
    }

    public void addCell(LiveCell liveCell) {
        liveCells.add(liveCell);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Trail getTrail(int x, int y) {
        return trails.stream().filter(trail -> trail.getxCoordinate() == x && trail.getyCoordinate() == y)
                .findFirst().orElse(null);
//        for (Trail trail : trails) {
//            if (trail.getxCoordinate() == x && trail.getyCoordinate() == y) {
//                return trail;
//            }
//        }
//        return null;
    }

    public void addTrail(Trail trail) {
        trails.add(trail);
    }
}

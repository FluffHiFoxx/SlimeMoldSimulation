package com.slimemold.board;

import com.slimemold.board.cell.Cell;
import com.slimemold.board.cell.LiveCell;
import com.slimemold.board.cell.Trail;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Tile {
    private final Set<LiveCell> liveCells = new HashSet<>();
    private Trail trail;
    private Color tileColor;

    public Tile(Color color, Set<LiveCell> cells) {
        this.trail = null;
        this.tileColor = color;
        this.liveCells.addAll(cells);
    }

    public Tile(Color color, LiveCell cell) {
        this.trail = null;
        this.tileColor = color;
        this.liveCells.add(cell);
    }

    public Tile(Color color) {
        this.trail = null;
        this.tileColor = color;
    }

    public Cell getCell() {
        Cell cell = null;
        if (!liveCells.isEmpty()) {
            cell = liveCells.stream().findAny().get();
        } else if (trail != null) {
            cell = trail;
        }
        return cell;
    }

    public void removeCell(LiveCell cell) {
        this.liveCells.remove(cell);
    }

    public void removeCells(Set<LiveCell> cells) {
        this.liveCells.removeAll(cells);
    }

    public void addCell(LiveCell cell) {
        this.liveCells.add(cell);
    }

    public void addCells(Set<LiveCell> liveCells) {
        this.liveCells.addAll(liveCells);
    }

    public Set<LiveCell> getCells() {
        return liveCells;
    }

    public Color getBgColor() {
        return tileColor;
    }

    public void setBgColor(Color bgColor) {
        tileColor = bgColor;
    }

    public Trail getTrail() {
        return trail;
    }

    public void setTrail(Trail trail) {
        this.trail = trail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Objects.equals(liveCells, tile.liveCells) && Objects.equals(trail, tile.trail) && Objects.equals(tileColor, tile.tileColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(liveCells, trail, tileColor);
    }
}

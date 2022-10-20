package com.slimemold.board;

import com.slimemold.board.cell.LiveCell;
import com.slimemold.board.cell.Trail;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

public class Tile {
    private final Set<LiveCell> liveCells = new HashSet<>();
    private Trail trail;
    private static Color BG_COLOR;
}

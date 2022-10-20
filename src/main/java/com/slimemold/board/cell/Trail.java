package com.slimemold.board.cell;

import javafx.scene.paint.Color;

public class Trail extends Cell {

    public Trail(Color color) {
        super(color);
    }

    public Trail(int[] direction, Color color) {
        super(direction, color);
    }
}

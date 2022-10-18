package com.slimemold.board;

import javafx.scene.paint.Color;

public class Cell {

    private int direction;


    private final Color color;

    public Cell(int direction, Color color) {
        this.direction = direction;
        this.color = color;
    }


    public Color getColor() {
        return color;
    }
}

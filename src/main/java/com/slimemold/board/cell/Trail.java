package com.slimemold.board.cell;

import javafx.scene.paint.Color;

public class Trail extends Cell {

    private Integer intensity;

    public Trail(Color color) {
        super(color);
        this.intensity = 50;
    }

    public Trail(int[] direction, Color color) {
        super(direction, color);
        this.intensity = 50;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }
}

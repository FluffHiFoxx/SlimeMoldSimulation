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

    public void decreaseIntensity() {
        this.intensity--;
        int red = this.color.getRed() + 1 < 255 ? (int) this.color.getRed() + 1 : (int) this.color.getRed();
        int green = this.color.getGreen() + 1 < 255 ? (int) this.color.getGreen() + 1 : (int) this.color.getGreen();
        int blue = this.color.getBlue() + 1 < 255 ? (int) this.color.getBlue() + 1 : (int) this.color.getBlue();
        this.color = Color.rgb(red, green, blue);
    }
}

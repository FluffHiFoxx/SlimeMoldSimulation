package com.slimemold.board.cell;

import javafx.scene.paint.Color;

public class Trail extends Cell {
    private Integer intensity = 70;

    public Trail(int[] direction, Color color) {
        super(direction, color);
    }

    public Integer getIntensity() {
        return intensity;
    }

    public void decreaseIntensity() {
        this.intensity--;
        int decrement = 3;
        int redOriginal = (int) (this.color.getRed() * 255);
        int greenOriginal = (int) (this.color.getGreen() * 255);
        int blueOriginal = (int) (this.color.getBlue() * 255);
        int red;
        int green;
        int blue;
        if (intensity > 1) {
            red = redOriginal - decrement > 0 ? redOriginal - decrement : redOriginal;
            green = greenOriginal - decrement > 0 ? greenOriginal - decrement : greenOriginal;
            blue = blueOriginal - decrement > 0 ? blueOriginal - decrement : blueOriginal;
        } else {
            red = 0;
            green = 0;
            blue = 0;
        }
        this.color = Color.rgb(red, green, blue);
    }
}

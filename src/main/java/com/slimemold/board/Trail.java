package com.slimemold.board;

import javafx.scene.paint.Color;

import java.util.Objects;

public class Trail extends Cell {
    private int intensity;

    public Trail(Color color, int x, int y) {
        super(color, x, y);
        intensity = 50;
    }

    public void decrease() {
        intensity--;

        int red = (int) getColor().getRed() * 255;
        int green = (int) getColor().getGreen() * 255;
        int blue = (int) getColor().getBlue() * 255;

        int newRed = Math.max(red - 1, 0);
        int newGreen = Math.max(green - 1, 0);
        int newBlue = Math.max(blue - 1, 0);

        setColor(Color.rgb(newRed, newGreen, newBlue));
    }

    public int getIntensity() {
        return intensity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trail trail = (Trail) o;
        return intensity == trail.intensity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(intensity);
    }
}

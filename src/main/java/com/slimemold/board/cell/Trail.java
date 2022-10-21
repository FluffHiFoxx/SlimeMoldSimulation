package com.slimemold.board.cell;

import javafx.scene.paint.Color;

import java.util.Objects;

public class Trail extends Cell {
    private Integer intensity = 80;
   public int x;
   public int y;

    public Trail(int[] direction, Color color,int x,int y) {
        super(direction, color);
        this.x=x;
        this.y=y;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trail)) return false;
        if (!super.equals(o)) return false;
        Trail trail = (Trail) o;
        return x == trail.x && y == trail.y && Objects.equals(intensity, trail.intensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), intensity, x, y);
    }
}

package com.slimemold.board;

import javafx.scene.paint.Color;

public class Trail extends Cell {
    private int intensity;

    public Trail(Color color, int x, int y, int[] direction) {
        super(color, x, y, direction);
        intensity = 50;
    }

    public void decrease(Board board) {
        if (intensity > 0) {
            intensity--;
            int decrement = 5;

            int red = (int) (getColor().getRed() * 255);
            int green = (int) (getColor().getGreen() * 255);
            int blue = (int) (getColor().getBlue() * 255);

            red = red - decrement > 0 ? red - decrement : red;
            green = green - decrement > 0 ? green - decrement : green;
            blue = blue - decrement > 0 ? blue - decrement : blue;

            setColor(Color.rgb(red, green, blue));
        } else {
            board.removeTrail(this);
        }
    }
}

package com.slimemold.board;

import javafx.scene.paint.Color;

import java.util.UUID;

public class Trail extends Cell {
    private int intensity;

    private UUID idOfParentLiveCell;

    public Trail(Color color, int x, int y, int[] direction, UUID id) {
        super(color, x, y, direction);
        intensity = 50;
        idOfParentLiveCell = id;
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
            board.setCell(yCoordinate, xCoordinate, null);
        }
    }

    public int getIntensity() {
        return intensity;
    }

    public UUID getIdOfParentLiveCell() {
        return idOfParentLiveCell;
    }
}

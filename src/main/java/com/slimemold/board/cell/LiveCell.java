package com.slimemold.board.cell;

import javafx.scene.paint.Color;

import java.util.Arrays;

public class LiveCell extends Cell {
    private int[] movesLeft;

    public LiveCell(Color color) {
        super(color);
        this.movesLeft = direction;
    }

    public LiveCell(int[] direction, Color color) {
        super(direction, color);
        this.movesLeft = direction;
    }

    public int[] moveToMake() {
        if (Arrays.equals(movesLeft, new int[]{0, 0})) {
            movesLeft = direction;
        }
        int x = Math.abs(movesLeft[0]);
        int y = Math.abs(movesLeft[1]);
        int[] firstMove;
        int[] secondMove;
        if (x < y) {
            secondMove = new int[]{x - 1, y > 0 ? y - 1 : y};
        } else if (y < x) {
            secondMove = new int[]{x > 0 ? x - 1 : x, y - 1};
        } else {
            secondMove = new int[]{x > 0 ? x - 1 : x, y > 0 ? y - 1 : y};
        }
        secondMove[0] = x == 0 ? 0 : (secondMove[0] * (x / movesLeft[0]));
        secondMove[1] = y == 0 ? 0 : (secondMove[1] * (y / movesLeft[1]));
        firstMove = new int[]{movesLeft[0] - secondMove[0], movesLeft[1] - secondMove[1]};
        movesLeft = secondMove;
        return firstMove;
    }

    public int[] getDirection() {
        return direction;
    }

    public void setDirection(int[] direction) {
        this.direction = direction;
    }

    public Color getColor() {
        return color;
    }

    public void reverseDirection(int i) {
        direction[i] = -direction[i];
    }
}

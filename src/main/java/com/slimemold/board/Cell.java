package com.slimemold.board;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Random;

public class Cell {

    private int[] direction;
    private static final int[] VECTOR_DIRECTIONS = {-2, -1, 0, 1, 2};
    private static final Random RANDOM = new Random();
    private int[] movesLeft;
    private final Color color;

    public Cell(Color color) {
        this.direction = new int[]{VECTOR_DIRECTIONS[RANDOM.nextInt(5)], VECTOR_DIRECTIONS[RANDOM.nextInt(5)]};
        this.color = color;
        this.movesLeft = direction;
    }

    public Cell(int[] direction, Color color) {
        if (Math.abs(direction[0]) > 2) {
            direction[0] = (int) (2 * Math.pow(direction[0], 0));
        }
        if (Math.abs(direction[1]) > 2) {
            direction[1] = (int) (2 * Math.pow(direction[1], 0));
        }
        this.direction = direction;
        this.color = color;
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
        secondMove[0] = x == 0 ? 0 : (secondMove[0] * (x/movesLeft[0]));
        secondMove[1] = y == 0 ? 0 : (secondMove[1] * (y/movesLeft[1]));
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
}

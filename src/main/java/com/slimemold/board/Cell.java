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
            secondMove = new int[]{x - 1, y - 1 > 0 ? y - 1 : y};
        } else {
            secondMove = new int[]{x - 1 > 0 ? x - 1 : x, y - 1};
        }
        secondMove[0] = (int) (secondMove[0] * Math.pow(movesLeft[0], 0));
        secondMove[1] = (int) (secondMove[1] * Math.pow(movesLeft[1], 0));
        firstMove = new int[]{x - secondMove[0], y - secondMove[1]};
        firstMove[0] = (int) (firstMove[0] * Math.pow(movesLeft[0], 0));
        firstMove[1] = (int) (firstMove[1] * Math.pow(movesLeft[1], 0));
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

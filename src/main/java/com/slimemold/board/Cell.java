package com.slimemold.board;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Random;

public class Cell {

    private int[] direction;
    private int yCoordinate;
    private int xCoordinate;
    private static final int[] VECTOR_DIRECTIONS = {-2, -1, 0, 1, 2};
    private static final Random RANDOM = new Random();
    private int[] movesLeft;
    private final Color color;

    public Cell(Color color, int x, int y) {
        this.direction = getRandomDirection();
        this.color = color;
        this.movesLeft = direction;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public Cell(int[] direction, Color color, int x, int y) {
        if (Arrays.equals(direction, new int[]{0, 0})) {
            direction = getRandomDirection();
        }
        if (Math.abs(direction[0]) > 2) {
            direction[0] = 2 * (Math.abs(direction[0]) / direction[0]);
        }
        if (Math.abs(direction[1]) > 2) {
            direction[1] = 2 * (Math.abs(direction[0]) / direction[1]);
        }
        this.direction = direction;
        this.color = color;
        this.movesLeft = direction;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public void move(Board board) {
        int[] difference = moveToMake();
        int nextX = xCoordinate + difference[0];
        int nextY = yCoordinate + difference[1];
        if (nextX > 0 && nextX < board.getWidth()) {
            xCoordinate = nextX;
        } else {
            xCoordinate += bounceOff(0);
        }
        if (nextY > 0 && nextY < board.getHeight()) {
            yCoordinate += difference[1];
        } else {
            yCoordinate += bounceOff(1);
        }
    }

    private static int[] getRandomDirection() {
        int[] direction = {0, 0};
        while (Arrays.equals(direction, new int[]{0, 0})) {
            direction = new int[]{VECTOR_DIRECTIONS[RANDOM.nextInt(5)], VECTOR_DIRECTIONS[RANDOM.nextInt(5)]};
        }
        return direction;
    }

    private int[] moveToMake() {
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

    public Color getColor() {
        return color;
    }

    public int bounceOff(int i) {
        direction[i] = -direction[i];
        return moveToMake()[i];
    }

    public void changeDirection(int[] direction) {
        this.direction = direction;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }
}

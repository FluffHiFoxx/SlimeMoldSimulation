package com.slimemold.board;

import javafx.scene.paint.Color;

import java.util.Arrays;

public class LiveCell extends Cell {
    private int[] movesLeft;

    public LiveCell(Color color, int x, int y) {
        super(color, x, y);
        this.movesLeft = direction;
    }

    public LiveCell(int[] direction, Color color, int x, int y) {
        super(color, x, y);
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
        this.movesLeft = direction;
    }

    public void move(Board board) {
        int[] difference = moveToMake();
        int originalX = xCoordinate;
        int originalY = yCoordinate;
        int nextX = xCoordinate + difference[0];
        int nextY = yCoordinate + difference[1];
        int maxX = board.getWidth() - 1;
        int maxY = board.getHeight() - 1;
        if (nextX >= 1 && nextX < maxX) {
            setxCoordinate(nextX);
        } else {
            setxCoordinate(xCoordinate + bounceOff(0));
        }
        if (nextY >= 1 && nextY < maxY) {
            setyCoordinate(nextY);
        } else {
            setyCoordinate(yCoordinate + bounceOff(1));
        }
        Trail trail = new Trail(getColor(), originalX, originalY);
        board.setCell(originalY, originalX, trail);
        board.addTrail(trail);
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

    public int bounceOff(int i) {
        direction[i] = -direction[i];
        return moveToMake()[i];
    }

    public void changeDirection(int[] direction) {
        this.direction = direction;
    }
}

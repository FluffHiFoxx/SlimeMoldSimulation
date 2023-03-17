package com.slimemold.board;

import javafx.scene.paint.Color;

import java.util.Arrays;

public class LiveCell extends Cell {

    private final int[] firstMove;

    private final int[] secondMove;

    private int stepCounter;


    public LiveCell(Color color, int x, int y) {
        super(color, x, y);
        this.firstMove = new int[2];
        this.secondMove = new int[2];
        // setting first move and second move
        calculateMoves(this.direction);
        // set step counter
        this.stepCounter = 1;
    }

    public LiveCell(Color color, int x, int y, int[] direction) {
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

        this.firstMove = new int[2];
        this.secondMove = new int[2];

        calculateMoves(direction);
        this.stepCounter = 1;
    }

    public void move(Board board) {

        Trail trail = new Trail(this.getColor(), getxCoordinate(),
                getyCoordinate(), this.direction);
        board.setCell(getyCoordinate(), getxCoordinate(), trail);
        board.addTrail(trail);

        if (stepCounter == 1) {
            this.setxCoordinate(getxCoordinate() + firstMove[0]);
            this.setyCoordinate(getyCoordinate() + firstMove[1]);

            this.stepCounter += 1;

        } else {
            this.setxCoordinate(getxCoordinate() + secondMove[0]);
            this.setyCoordinate(getyCoordinate() + secondMove[1]);

            this.stepCounter -= 1;
        }

        if (getxCoordinate() >= 0 && getxCoordinate() < board.getWidth() &&
                getyCoordinate() >= 0 && getyCoordinate() < board.getHeight()) {

            board.setCell(getyCoordinate(), getxCoordinate(), this);
        } else {
            // change direction
            if (getxCoordinate() < 0) {
                setxCoordinate(0);
                direction[0] = -direction[0];
            } else if (getxCoordinate() >= board.getWidth() - 1) {
                setxCoordinate(board.getWidth() -1 );
                direction[0] = -direction[0];
            }

            if (getyCoordinate() < 0) {
                setyCoordinate(0);
                direction[1] = -direction[1];
            } else if (getyCoordinate() >= board.getHeight() - 1) {
                setyCoordinate(board.getHeight() - 1);
                direction[1] = -direction[1];
            }

            calculateMoves(this.direction);
            board.setCell(getyCoordinate(), getxCoordinate(), this);
        }
    }


    public void changeDirection(int[] direction) {
        this.direction = direction;
    }

    private void calculateMoves(int[] direction) {
        if (Math.abs(direction[0]) > 1) {
            firstMove[0] = direction[0] / 2;
        } else {
            firstMove[0] = direction[0];
        }
        if (Math.abs(direction[0]) > 1) {
            firstMove[1] = direction[1] / 2;
        } else {
            firstMove[1] = direction[1];
        }
        // setting second move
        this.secondMove[0] = direction[0] - this.firstMove[0];
        this.secondMove[1] = direction[1] - this.firstMove[1];
    }

}

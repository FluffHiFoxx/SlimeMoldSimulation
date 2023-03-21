
package com.slimemold.board;

import javafx.scene.paint.Color;

import java.util.Arrays;

public class LiveCell extends Cell {

    private final int[] firstMove;

    private final int[] secondMove;

    private int stepCounter;

    private boolean isFollowingOther = false;


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

        leaveTrail(board);

        // get coord of nearby most intensive trail
        int[] coordOfTrailnearby = getStrongestTrailCoordNearby(board);


        if(isFollowingOther){
            simpleMove(board);
        }

        if (coordOfTrailnearby != null) {

            this.isFollowingOther = true;
            // copy direction of nearby  most intensive trail and re-calculate first and second move
            this.direction = board.getCell(coordOfTrailnearby[0], coordOfTrailnearby[1]).direction;
            calculateMoves(direction);
            // simply move to the nearby most intensive trail coord
            this.yCoordinate = coordOfTrailnearby[0];
            this.xCoordinate = coordOfTrailnearby[1];
            board.setCell(getyCoordinate(), getxCoordinate(), this);
        } else {
            simpleMove(board);
        }
    }


    public void changeDirection(int[] direction) {
        this.direction = direction;
    }

    private  void simpleMove(Board board){
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
                setxCoordinate(board.getWidth() - 1);
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

    private void leaveTrail(Board board){
        Trail trail = new Trail(this.getColor(), getxCoordinate(),
                getyCoordinate(), this.direction, ID);
        board.setCell(getyCoordinate(), getxCoordinate(), trail);
        board.addTrail(trail);
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




    private int[] getStrongestTrailCoordNearby(Board board) {
        // look around and get highest intensity trail which is related to other live cell
        int[] trailCoordToFollow = null;
        int highestintensityOfTrailNearby = 0;


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                try {
                    Cell nearbyCell = board.getCell(yCoordinate - 1 + i, xCoordinate - 1 + j);
                    if (nearbyCell instanceof Trail
                            && ((Trail) nearbyCell).getIdOfParentLiveCell() != ID
                            && ((Trail) nearbyCell).getIntensity() > highestintensityOfTrailNearby

                    ) {
                        trailCoordToFollow = new int[2];
                        trailCoordToFollow[0] = nearbyCell.yCoordinate;
                        trailCoordToFollow[1] = nearbyCell.xCoordinate;
                        highestintensityOfTrailNearby = ((Trail) nearbyCell).getIntensity();
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                }
            }
        }
        return trailCoordToFollow;
    }
}

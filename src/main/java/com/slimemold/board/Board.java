package com.slimemold.board;

import com.slimemold.board.cell.Cell;
import com.slimemold.board.cell.LiveCell;
import com.slimemold.board.cell.Trail;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Board {

    private Cell[][] board;
//    private Set<LiveCell> alreadyMovedLiveCells;

    public Board(int width, int height) {
        this.board = new Cell[height][width];
//        this.alreadyMovedLiveCells = new HashSet<>();
    }

    public void moveContent() {
        System.out.println("call moveContent");
        Set<LiveCell> alreadyMovedLiveCells = new HashSet<>();
        final int rowMax = board.length;
        final int colMax = board[0].length;
//        fadeTheTrails();
        int counter = 0;
        for (int row = 0; row < rowMax; row++) {
            for (int col = 0; col < colMax; col++) {
                Cell cell = board[row][col];
                if (cell instanceof LiveCell && !alreadyMovedLiveCells.contains((LiveCell) cell)) {
                    System.out.println("cell number " + counter);
                    moveCell(rowMax, colMax, row, col, (LiveCell) cell);
                    alreadyMovedLiveCells.add((LiveCell) cell);
                    counter++;
                }
            }
        }
    }

    private void moveCell(int rowMax, int colMax, int row, int col, LiveCell liveCell) {
        int[] difference = liveCell.moveToMake();
        if ((col + difference[1] < 0 || col + difference[1] > colMax - 1) &&
                (row + difference[0] < 0 || row + difference[0] > rowMax - 1)) {
            liveCell.reverseDirection(0);
            liveCell.reverseDirection(1);
            difference = liveCell.moveToMake();
        } else if (col + difference[1] < 0 || col + difference[1] > colMax - 1) {
            liveCell.reverseDirection(1);
            difference = liveCell.moveToMake();
        } else if (row + difference[0] < 0 || row + difference[0] > rowMax - 1) {
            liveCell.reverseDirection(0);
            difference = liveCell.moveToMake();
        }
//        System.out.println("\nCell: " + liveCell);
//        System.out.println("coordinate difference: " + Arrays.toString(difference));
//        System.out.println("current coordinate: [" + row + ", " + col + "]");
//        System.out.println("next coordinate: [" + (row + difference[0]) + ", " + (col + difference[1]) + "]");
//        if (!(board[row + difference[0]][col + difference[1]] instanceof LiveCell)) {
            board[row + difference[0]][col + difference[1]] = liveCell;
//        }
//        board[row][col] = new Trail(liveCell.getDirection(), liveCell.getColor());
    }


    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public void setCell(int row, int col, Cell cell) {
        board[row][col] = cell;
    }

    private void fadeTheTrails() {
        final int rowMax = board.length;
        final int colMax = board[0].length;
        for (int row = 0; row < rowMax; row++) {
            for (int col = 0; col < colMax; col++) {

                Cell cell = board[row][col];
                if (cell instanceof Trail) {
                    Trail trail = (Trail) cell;
                    if (trail.getIntensity() > 1) {
                        trail.decreaseIntensity();
                    } else if (trail.getIntensity() == 0) {
                        board[row][col] = null;
                    }
                }

            }
        }
    }
}

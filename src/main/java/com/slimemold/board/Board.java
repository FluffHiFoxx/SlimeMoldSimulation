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
    private Set<LiveCell> alreadyMovedLiveCells;

    public Board(int width, int height) {
        this.board = new Cell[height][width];
    }

    public void moveTileContent() {
        this.alreadyMovedLiveCells = new HashSet<>();
        final int rowMax = board.length;
        final int colMax = board[0].length;
        for (int row = 0; row < rowMax; row++) {
            for (int col = 0; col < colMax; col++) {
                Cell cell = board[row][col];
                if (cell instanceof LiveCell) {
                    moveCells(rowMax, colMax, row, col, (LiveCell) cell);
                }
            }
        }
    }

    private void moveCells(int rowMax, int colMax, int row, int col, LiveCell liveCell) {
        if (!alreadyMovedLiveCells.contains(liveCell)) {
            try {
                int[] difference = liveCell.moveToMake();
                System.out.println("\nCell: " + liveCell);
                System.out.println("coordinate difference: " + Arrays.toString(difference));
                System.out.println("next coordinate: [" + (row + difference[0]) + ", " + (col + difference[1]) + "]");
                board[row + difference[0]][col + difference[1]] = liveCell;
                board[row][col] = new Trail(liveCell.getDirection(), Color.AQUA);
                alreadyMovedLiveCells.add(liveCell);
            } catch (ArrayIndexOutOfBoundsException e) {
                if ((col <= 0 || col >= colMax - 1) && (row <= 0 || row >= rowMax - 1)) {
                    liveCell.reverseDirection(0);
                    liveCell.reverseDirection(1);
                } else if (col <= 0 || col >= colMax - 1) {
                    liveCell.reverseDirection(1);
                } else if (row <= 0 || row >= rowMax - 1) {
                    liveCell.reverseDirection(0);
                }
                int[] difference = liveCell.moveToMake();
                System.out.println("\nCell: " + liveCell);
                System.out.println("coordinate difference: " + Arrays.toString(difference));
                System.out.println("next coordinate: [" + (row + difference[0]) + ", " + (col + difference[1]) + "]");
                board[row + difference[0]][col + difference[1]] = liveCell;
                board[row][col] = new Trail(liveCell.getDirection(), Color.AQUA);
                alreadyMovedLiveCells.add(liveCell);
            }
        }
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

    public void fadeTheTrails() {
        final int rowMax = board.length;
        final int colMax = board[0].length;
        for (int row = 0; row < rowMax; row++) {
            for (int col = 0; col < colMax; col++) {

                Cell cell =  board[row][col];
                if(cell instanceof Trail){
                    Trail trail = (Trail) cell;
                    if (trail.getIntensity() > 1) {
                        trail.setIntensity(trail.getIntensity() - 1);
                    } else if (trail.getIntensity() == 1) {
                        board[row][col] = null;
                    }
                }

            }
        }
    }
}

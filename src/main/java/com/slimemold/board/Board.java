package com.slimemold.board;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Board {

    private Cell[][] board;

    public Board(int width, int height) {
        this.board = new Cell[height][width];
    }

    public void moveCells() {
        Set<Cell> alreadyMovedCells = new HashSet<>();
        final int rowMax = board.length;
        final int colMax = board[0].length;
        for (int row = 0; row < rowMax; row++) {
            for (int col = 0; col < colMax; col++) {
                Cell cell = getCell(row, col);
                if (board[row][col] != null && !alreadyMovedCells.contains(cell)) {
                    try {
                        int[] difference = cell.moveToMake();
                        System.out.println("\nCell: " + cell);
                        System.out.println("coordinate difference: " + Arrays.toString(difference));
                        System.out.println("next coordinate: [" + (row + difference[0]) + ", " + (col + difference[1]) + "]");
                        setCell(row + difference[0], col + difference[1], cell);
                        setCell(row, col, null);
                        alreadyMovedCells.add(cell);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if ((col == 0 || col >= colMax - 1) && (row == 0 || row >= rowMax - 1)) {
                            cell.reverseDirection(0);
                            cell.reverseDirection(1);
                        } else if (col == 0 || col >= colMax - 1) {
                            cell.reverseDirection(1);
                        } else if (row == 0 || row >= rowMax - 1) {
                            cell.reverseDirection(0);
                        }
                        int[] difference = cell.moveToMake();
                        System.out.println("\nCell: " + cell);
                        System.out.println("coordinate difference: " + Arrays.toString(difference));
                        System.out.println("next coordinate: [" + (row + difference[0]) + ", " + (col + difference[1]) + "]");
                        setCell(row + difference[0], col + difference[1], cell);
                        setCell(row, col, null);
                        alreadyMovedCells.add(cell);
                    }

                }
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
}

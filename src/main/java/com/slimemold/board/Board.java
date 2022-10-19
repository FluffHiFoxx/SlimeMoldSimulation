package com.slimemold.board;

public class Board {

    private Cell[][] board;

    public Board(int width, int height) {
        this.board = new Cell[height][width];
    }

    public void moveCells() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != null) {
                    Cell cell = getCell(row, col);
                    int[] coordinates = cell.moveToMake();
                    setCell(coordinates[0], coordinates[1], cell);
                    setCell(row, col, null);
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

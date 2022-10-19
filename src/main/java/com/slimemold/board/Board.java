package com.slimemold.board;

public class Board {

    private Cell[][] board;

    public Board(int width, int height) {
        this.board = new Cell[height][width];
    }

    public void moveCells() {

    }


    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public Cell getCell(int row, int col) {
        return board[col][row];
    }

    public void setCell(int row, int col, Cell cell) {
        board[col][row] = cell;
    }
}

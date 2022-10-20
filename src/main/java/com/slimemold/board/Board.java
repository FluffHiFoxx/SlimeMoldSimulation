package com.slimemold.board;

import com.slimemold.board.cell.Cell;
import com.slimemold.board.cell.LiveCell;
import com.slimemold.board.cell.Trail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Board {

    private Cell[][] board;

    public Board(int width, int height) {
        this.board = new LiveCell[height][width];
    }

    public void moveCells() {
        Set<LiveCell> alreadyMovedLiveCells = new HashSet<>();
        final int rowMax = board.length;
        final int colMax = board[0].length;
        for (int row = 0; row < rowMax; row++) {
            for (int col = 0; col < colMax; col++) {
                LiveCell liveCell = (LiveCell) getTile(row, col);
                if (board[row][col] != null && !alreadyMovedLiveCells.contains(liveCell)) {
                    try {
                        int[] difference = liveCell.moveToMake();
                        System.out.println("\nCell: " + liveCell);
                        System.out.println("coordinate difference: " + Arrays.toString(difference));
                        System.out.println("next coordinate: [" + (row + difference[0]) + ", " + (col + difference[1]) + "]");
                        setCell(row + difference[0], col + difference[1], liveCell);
                        setCell(row, col, null);
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
                        setCell(row + difference[0], col + difference[1], liveCell);
                        setCell(row, col, null);
                        alreadyMovedLiveCells.add(liveCell);
                    }

                }
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(LiveCell[][] board) {
        this.board = board;
    }

    public Cell getTile(int row, int col) {
        return board[row][col];
    }

    public void setCell(int row, int col, LiveCell liveCell) {
        board[row][col] = liveCell;
    }

    public void fadeTheTrails(){
        final int rowMax = board.length;
        final int colMax = board[0].length;
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                Trail trail = (Trail) getTile(i,j);
                if(trail != null && trail.getIntensity() > 1){
                    trail.setIntensity(trail.getIntensity() -1);
                } else if (trail != null && trail.getIntensity() == 1) {
                    trail.setIntensity(null);
                }
            }
        }
    }

    public void transformCellToTrail(int row, int col, LiveCell liveCell){
        // todo   adott [row] [col] -on a  setből kiszedni az adott liveCell-t

        // todo helyére spawnolni egy trailt és átadni a directiont


    }
}

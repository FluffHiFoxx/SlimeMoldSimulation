package com.slimemold.board;

import com.slimemold.board.cell.Cell;
import com.slimemold.board.cell.LiveCell;
import com.slimemold.board.cell.Trail;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {

    private Cell[][] board;
    private final Set<Cell> liveCellsAndTrails = new HashSet<>();
    int width;
    int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Cell[height][width];
    }

    public void moveContent() {
//        System.out.println("call moveContent");
//        Set<LiveCell> alreadyMovedLiveCells = new HashSet<>();
        final int rowMax = board.length;
        final int colMax = board[0].length;
//        fadeTheTrails();
//        List<Trail> toRemove = new ArrayList<>();
        List<Cell> cellList = new ArrayList<>(liveCellsAndTrails);
        for (Cell cell : cellList) {
            if (cell instanceof Trail) {
                fadeOneTrail((Trail) cell);
            } else if (cell instanceof LiveCell) {
                moveCell(rowMax, colMax, (LiveCell) cell);
            }
        }

//        first try to remove trails, before i copied the liveCellsAndTrails to new list
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                if(board[i][j] instanceof Trail && !liveCellsAndTrails.contains(board[i][j])){
////                    board[i][j]=null;
//                }
//            }
//        }
//        toRemove.forEach(liveCellsAndTrails::remove);


//        int counter = 0;
//        for (int row = 0; row < rowMax; row++) {
//            for (int col = 0; col < colMax; col++) {
//                Cell cell = board[row][col];
//                if (cell instanceof LiveCell && !alreadyMovedLiveCells.contains((LiveCell) cell)) {
//                    System.out.println("cell number " + counter);
//                    moveCell(rowMax, colMax, row, col, (LiveCell) cell);
//                    alreadyMovedLiveCells.add((LiveCell) cell);
//                    counter++;
//                }
//            }
//        }
    }

    private void moveCell(int rowMax, int colMax, LiveCell liveCell) {
        int row = liveCell.x;
        int col = liveCell.y;
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
        //move livecell
        board[row + difference[0]][col + difference[1]] = liveCell;
        liveCell.x = row + difference[0];
        liveCell.y = col + difference[1];
        //create trail behind
        Trail trail = new Trail(liveCell.getDirection(), liveCell.getColor(), row, col);
        liveCellsAndTrails.add(trail);
        board[row][col] = trail;
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

    public void setCell(int row, int col, Color color) {
        LiveCell cell = new LiveCell(color, row, col);
        board[row][col] = cell;
        liveCellsAndTrails.add(cell);
    }

    private void fadeOneTrail(Trail trail) {
        if (trail.getIntensity() > 1) {
            trail.decreaseIntensity();
        } else if (trail.getIntensity() < 1) {
            board[trail.x][trail.y] = null;
            liveCellsAndTrails.remove(trail);
        }
    }

//    private void fadeTheTrails() {
//        final int rowMax = board.length;
//        final int colMax = board[0].length;
//        for (int row = 0; row < rowMax; row++) {
//            for (int col = 0; col < colMax; col++) {
//
//                Cell cell = board[row][col];
//                if (cell instanceof Trail) {
//                    Trail trail = (Trail) cell;
//                    if (trail.getIntensity() > 1) {
//                        trail.decreaseIntensity();
//                    } else if (trail.getIntensity() == 0) {
//                        board[row][col] = null;
//                    }
//                }
//
//            }
//        }
//    }
}

package com.slimemold.board.cell;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Objects;

public class LiveCell extends Cell {
    public int x;
    public int y;
    private int[] movesLeft;

    public LiveCell(Color color,int x,int y) {
        super(color);
        this.movesLeft = direction;
        this.x=x;
        this.y=y;
    }

    public LiveCell(int[] direction, Color color) {
        super(direction, color);
        this.movesLeft = direction;
    }

    public int[] moveToMake() {
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

    public void reverseDirection(int i) {
        direction[i] = -direction[i];
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        LiveCell liveCell = (LiveCell) o;
//        return Arrays.equals(movesLeft, liveCell.movesLeft);
//    }
//
//    @Override
//    public int hashCode() {
//        return Arrays.hashCode(movesLeft);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LiveCell)) return false;
        LiveCell cell = (LiveCell) o;
        return x == cell.x && y == cell.y && Arrays.equals(movesLeft, cell.movesLeft);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(x, y);
        result = 31 * result + Arrays.hashCode(movesLeft);
        return result;
    }
}

package com.slimemold.board.cell;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public abstract class Cell {
    private static final int[] VECTOR_DIRECTIONS = {-2, -1, 0, 1, 2};
    private static final Random RANDOM = new Random();
    protected int[] direction;
    protected Color color;

    public Cell(Color color) {
        this.direction = getRandomDirection();
        this.color = color;
    }

    public Cell(int[] direction, Color color) {
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
        this.color = color;
    }

    private static int[] getRandomDirection() {
        int[] direction = {0, 0};
        while (Arrays.equals(direction, new int[]{0, 0})) {
            direction = new int[]{VECTOR_DIRECTIONS[RANDOM.nextInt(5)], VECTOR_DIRECTIONS[RANDOM.nextInt(5)]};
        }
        return direction;
    }

    public int[] getDirection() {
        return direction;
    }

    public void setDirection(int[] direction) {
        this.direction = direction;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Arrays.equals(direction, cell.direction) && Objects.equals(color, cell.color);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(color);
        result = 31 * result + Arrays.hashCode(direction);
        return result;
    }
}

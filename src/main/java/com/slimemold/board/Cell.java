package com.slimemold.board;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public abstract class Cell {
    private final UUID ID = UUID.randomUUID();
    private static final int[] VECTOR_DIRECTIONS = {-2, -1, 0, 1, 2};
    private static final Random RANDOM = new Random();
    protected int[] direction;
    protected int yCoordinate;
    protected int xCoordinate;
    private Color color;

    public Cell(Color color, int x, int y) {
        this.direction = getRandomDirection();
        this.color = color;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public Cell(Color color, int x, int y, int[] direction) {
        this.direction = direction;
        this.color = color;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }


    protected static int[] getRandomDirection() {
        int[] direction = {0, 0};
        while (Arrays.equals(direction, new int[]{0, 0})) {
            direction = new int[]{VECTOR_DIRECTIONS[RANDOM.nextInt(5)], VECTOR_DIRECTIONS[RANDOM.nextInt(5)]};
        }
        return direction;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return yCoordinate == cell.yCoordinate && xCoordinate == cell.xCoordinate && Objects.equals(ID, cell.ID) && Arrays.equals(direction, cell.direction) && Objects.equals(color, cell.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}

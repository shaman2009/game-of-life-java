package com.coderetreat.gameoflife;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class World {

    private static final int ALIVE_FROM_DEAD_THRESH_HOLD = 3;
    private static final int EXISTING_THRESH_HOLD_END = 3;
    private static final int EXISTING_THRESH_HOLD_BEGIN = 2;
    private Cell[][] cells;

    private final int row;
    private final int column;
    public World(int row, int column) {
        cells = new Cell[row][column];
        this.row = row;
        this.column = column;

        range(0, row).forEach(r -> {
            range(0, column).forEach(c -> {
                cells[r][c] = new Cell(Boolean.FALSE);
            });
        });
    }

    public List<Cell> pitchNeighbours(int x, int y) {
        ArrayList<Cell> neighbours = new ArrayList<>();
        range(x - 1, x + 2).forEach((int row) -> range(y - 1, y + 2)
                           .filter(column -> isNotSelf(x, y, row, column))
                           .forEach(column -> fillCell(neighbours, row, column)));
        return neighbours;
    }

    private boolean isNotSelf(int x, int y, int row, int column) {
        return column != y || row != x;
    }

    private void fillCell(ArrayList<Cell> neighbours, int i, int j) {
        neighbours.add(isInWorld(i, j) ? cells[i][j] : new Cell(Boolean.FALSE));
    }

    private boolean isInWorld(int x, int y) {
        return (x >= 0 && y >= 0 && x < row && y < column);
    }

    public Cell pitchCell(int x, int y) {
        return cells[x][y];
    }

    public void makeCellAlive(int x, int y) {
        cells[x][y] = new Cell(Boolean.TRUE);
    }

    public List<Cell> pitchAliveNeighbours(int x, int y) {
        return pitchNeighbours(x, y).stream().filter(cell -> cell.isAlive()).collect(toList());
    }

    public void tick() {
        Cell[][] nextGeneration = new Cell[row][column];
        range(0, row).forEach(r -> range(0, column).forEach(c -> nextGeneration[r][c]
                = makeNewCell(cells[r][c], r, c)));
        cells = nextGeneration;
    }

    private Cell makeNewCell(Cell cell, int row, int column) {
        int aliveCellCount = pitchAliveNeighbours(row, column).size();
        return cell.isAlive() ? makeCellForAliveCell(aliveCellCount) : makeCellForDeadCell(aliveCellCount);
    }

    private Cell makeCellForDeadCell(int aliveCellCount) {
        return new Cell(shouldBeAliveFromDead(aliveCellCount));
    }

    private boolean shouldBeAliveFromDead(int aliveCellCount) {
        return aliveCellCount == ALIVE_FROM_DEAD_THRESH_HOLD;
    }

    private Cell makeCellForAliveCell(int aliveCellCount) {
        return new Cell(shouldStillBeAlive(aliveCellCount));
    }

    private boolean shouldStillBeAlive(int aliveCellCount) {
        return aliveCellCount == EXISTING_THRESH_HOLD_BEGIN || aliveCellCount == EXISTING_THRESH_HOLD_END;
    }
}

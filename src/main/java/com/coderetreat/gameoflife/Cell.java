package com.coderetreat.gameoflife;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private final Boolean isAlive;

    public Cell(Boolean isAlive) {
        this.isAlive = isAlive;
    }

    public Boolean isAlive() {
        return isAlive;
    }


}

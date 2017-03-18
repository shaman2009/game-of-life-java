package com.coderetreat.gameoflife;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CellTest {

    @Test
    public void should_get_cell_dead_or_alive(){
        Cell cell = new Cell(Boolean.FALSE);
        assertThat(cell.isAlive(), is(Boolean.FALSE));

        Cell aliveCell = new Cell(Boolean.TRUE);
        assertThat(aliveCell.isAlive(), is(Boolean.TRUE));
    }
}
package com.coderetreat.gameoflife;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorldTest {
    private World world = new World(4, 8);

    @Test
    public void should_get_a_dead_cell() {
        Cell deadCell = world.pitchCell(0, 0);
        assertThat(deadCell.isAlive(), is(Boolean.FALSE));
    }

    @Test
    public void should_set_a_alive_cell_into_world() {
        world.makeCellAlive(1, 1);
        assertThat(world.pitchCell(1, 1).isAlive(), is(Boolean.TRUE));
    }

    @Test
    public void should_get_all_neighbours() {
        List<Cell> cells = world.pitchNeighbours(1, 1);
        assertThat(cells.size(), is(8));


        cells = world.pitchNeighbours(0, 0);
        assertThat(cells.size(), is(8));
    }

    @Test
    public void should_get_all_alive_neighbours() {
        world.makeCellAlive(0, 0);
        List<Cell> aliveCells = world.pitchAliveNeighbours(1, 1);
        assertThat(aliveCells.size(), is(1));
    }

    @Test
    public void should_be_dead_if_lonely_cell_in_world(){
        world.makeCellAlive(0, 0);
        world.tick();
        assertThat(world.pitchCell(0, 0).isAlive(), is(Boolean.FALSE));
    }

    @Test
    public void should_be_alive_if_a_dead_cell_has_three_alive_neighbours(){
        world.makeCellAlive(0, 0);
        world.makeCellAlive(0, 1);
        world.makeCellAlive(1, 0);

        assertThat(world.pitchCell(1, 1).isAlive(), is(Boolean.FALSE));

        world.tick();
        assertThat(world.pitchCell(1, 1).isAlive(), is(Boolean.TRUE));
    }
    
    @Test
    public void should_should_be_dead_if_a_alive_cell_has_less_than_2_alive_neighbours(){
        world.makeCellAlive(0, 0);
        world.makeCellAlive(1, 1);

        assertThat(world.pitchCell(1, 1).isAlive(), is(Boolean.TRUE));

        world.tick();
        assertThat(world.pitchCell(1, 1).isAlive(), is(Boolean.FALSE));
    }

    @Test
    public void should_be_dead_if_a_alive_cell_has_more_than_3_alive_neighbours(){
        world.makeCellAlive(0, 0);
        world.makeCellAlive(0, 1);
        world.makeCellAlive(1, 0);
        world.makeCellAlive(2, 0);

        world.makeCellAlive(1, 1);

        assertThat(world.pitchCell(1, 1).isAlive(), is(Boolean.TRUE));
        world.tick();

        assertThat(world.pitchCell(1, 1).isAlive(), is(Boolean.FALSE));
    }
}
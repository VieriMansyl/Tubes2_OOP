package com.aetherwars.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwapTest {

    @Test
    void testGetDuration() {
        Swap swap = new Swap(1, "desc", "src", "src", 10, 5);

        assertNotEquals(1, swap.getDuration());
        assertNotEquals(3, swap.getDuration());
        assertNotEquals(5, swap.getDuration());
        assertEquals(10, swap.getDuration());

    }

    @Test
    void testAddDurationPos() {
        Swap swap = new Swap(1, "desc", "src", "src", 10, 5);
        swap.addDuration(5);

        assertNotEquals(5, swap.getDuration());
        assertNotEquals(10, swap.getDuration());
        assertEquals(15, swap.getDuration());
    }

    @Test
    void testAddDurationNeg() {
        Swap swap = new Swap(1, "desc", "src", "src", 10, 5);
        swap.addDuration(-5);

        assertNotEquals(15, swap.getDuration());
        assertNotEquals(10, swap.getDuration());
        assertEquals(5, swap.getDuration());
    }

}
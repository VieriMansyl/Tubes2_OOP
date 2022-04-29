package com.aetherwars.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionTest {

    @Test
    void testGetAtk() {
        Potion ptn = new Potion(1, "Potion A", "Ini potion", "src",
                3, 3, 5, 3);
        assertEquals(3, ptn.getAtk());
    }

    @Test
    void testGetHp() {
        Potion ptn = new Potion(1, "Potion A", "Ini potion", "src",
                3, 3, 5, 3);
        assertEquals(3, ptn.getHp());
    }

    @Test
    void testGetDuration() {
        Potion ptn = new Potion(1, "Potion A", "Ini potion", "src",
                3, 3, 5, 3);
        assertEquals(3, ptn.getDuration());
    }

    @Test
    void testAddDuration() {
        Potion ptn = new Potion(1, "Potion A", "Ini potion", "src",
                3, 3, 5, 3);
        ptn.addDuration(7);
        assertEquals(10, ptn.getDuration());
    }

    @Test
    void testGetInfo() {
        Potion ptn = new Potion(1, "Potion A", "Ini potion", "src",
                3, 3, 5, 3);
        assertEquals("ATK3/HP3", ptn.getInfo());
    }
}
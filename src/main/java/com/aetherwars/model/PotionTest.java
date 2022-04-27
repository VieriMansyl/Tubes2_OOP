package com.aetherwars.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionTest {

    @Test
    void getAtk() {
        Potion ptn = new Potion(1, "Potion A", "Ini potion", "src",
                3, 3, 5, 3);
        assertEquals(3, ptn.getAtk());
    }

    @Test
    void getHp() {
        Potion ptn = new Potion(1, "Potion A", "Ini potion", "src",
                3, 3, 5, 3);
        assertEquals(3, ptn.getHp());
    }

    @Test
    void getDuration() {
        Potion ptn = new Potion(1, "Potion A", "Ini potion", "src",
                3, 3, 5, 3);
        assertEquals(3, ptn.getDuration());
    }

    @Test
    void addDuration() {
        Potion ptn = new Potion(1, "Potion A", "Ini potion", "src",
                3, 3, 5, 3);
        ptn.addDuration(7);
        assertEquals(10, ptn.getDuration());
    }

    @Test
    void getInfo() {
        Potion ptn = new Potion(1, "Potion A", "Ini potion", "src",
                3, 3, 5, 3);
        assertEquals("ATK3/HP3", ptn.getInfo());
    }
}
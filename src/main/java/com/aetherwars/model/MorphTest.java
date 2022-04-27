package com.aetherwars.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MorphTest {

    @Test
    void testGetCharacterID() {
        Morph mor = new Morph(1, "Sheep", "Mbee", "src", 1, 2);
        assertEquals(2, mor.getCharacterID());
    }

    @Test
    void testGetInfo() {
        Morph mor = new Morph(1, "Sheep", "Mbee", "src", 1, 2);
        assertEquals("MORPH", mor.getInfo());
    }
}
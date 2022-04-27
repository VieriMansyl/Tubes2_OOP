package com.aetherwars.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelSpellTest {

    @Test
    void testGetLvlEffect() {
        LevelSpell lvl = new LevelSpell(1, "up level", "up level", "src", 1);
        assertEquals(1, lvl.getLvlEffect());
    }

    @Test
    void testEffect() {
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);
        LevelSpell lvl = new LevelSpell(1, "up level", "up level", "src", 1);
        lvl.effect(c);
        assertEquals(2, c.getLevel());
    }

    @Test
    void testGetInfo() {
        LevelSpell lvl = new LevelSpell(1, "up level", "up level", "src", 1);
        assertEquals("up level", lvl.getInfo());
    }
}
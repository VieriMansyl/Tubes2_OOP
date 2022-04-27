package com.aetherwars.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testSetCard() {
        Board b = new Board();
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);
        try {
            b.setCard(c, 3);
            assertEquals(c, b.getCard(3));
        } catch(Exception e) {
            System.out.println("Cannot Place Card");
        }
    }

    @Test
    void testDestroyCard() {
        Board b = new Board();
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);
        try {
            b.setCard(c, 3);
            b.destroyCard(3);
            assertEquals(null, b.getCard(3));
        } catch(Exception e) {
            System.out.println("Cannot Place Card");
        }
    }
}
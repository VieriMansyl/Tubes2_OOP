package com.aetherwars.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testGetName() {
        Deck deck = new Deck();
        Board board = new Board();
        Hand hand = new Hand(deck);
        Player player = new Player("Pewdiepie", deck, hand, board);

        assertEquals("Pewdiepie", player.getName());
        assertNotEquals("Pewdaipie", player.getName());

    }

    @Test
    void testGetHealth() {
        Deck deck = new Deck();
        Board board = new Board();
        Hand hand = new Hand(deck);
        Player player = new Player("Pewdiepie", deck, hand, board);

        assertEquals(80, player.getHealth());
        assertNotEquals(100, player.getHealth());

    }


    @Test
    void testGetMana() {
        Deck deck = new Deck();
        Board board = new Board();
        Hand hand = new Hand(deck);
        Player player = new Player("Pewdiepie", deck, hand, board);

        assertEquals(1, player.getMana());
        assertNotEquals(10, player.getMana());
    }

    @Test
    void testGetMaxMana() {
        Deck deck = new Deck();
        Board board = new Board();
        Hand hand = new Hand(deck);
        Player player = new Player("Pewdiepie", deck, hand, board);

        assertEquals(1, player.getMaxMana());
        assertNotEquals(10, player.getMaxMana());
    }

    @Test
    void testIsDead() {
        Deck deck = new Deck();
        Board board = new Board();
        Hand hand = new Hand(deck);
        Player player = new Player("Pewdiepie", deck, hand, board);

        player.addHealth(-80);
        assertEquals(true, player.isDead());
        assertNotEquals(false, player.isDead());
    }

    @Test
    void testAddHealthNeg() {
        Deck deck = new Deck();
        Board board = new Board();
        Hand hand = new Hand(deck);
        Player player = new Player("Pewdiepie", deck, hand, board);
        player.addHealth(-20);

        assertEquals(60, player.getHealth());
        assertNotEquals(80, player.getHealth());
    }

    @Test
    void testAddHealthPos() {
        Deck deck = new Deck();
        Board board = new Board();
        Hand hand = new Hand(deck);
        Player player = new Player("Pewdiepie", deck, hand, board);
        player.addHealth(20);

        assertEquals(100, player.getHealth());
        assertNotEquals(80, player.getHealth());
    }

    @Test
    void testGetHand() {
        Deck deck = new Deck();
        Board board = new Board();
        Hand hand = new Hand(deck);
        Player player = new Player("Pewdiepie", deck, hand, board);

        assertEquals(hand, player.getHand());
        assertNotEquals(board, player.getHand());
        assertNotEquals(deck, player.getHand());
    }

    @Test
    void testGetBoard() {
        Deck deck = new Deck();
        Board board = new Board();
        Hand hand = new Hand(deck);
        Player player = new Player("Pewdiepie", deck, hand, board);

        assertEquals(board, player.getBoard());
        assertNotEquals(deck, player.getBoard());
        assertNotEquals(hand, player.getBoard());
    }

    @Test
    void testGetDeck() {
        Deck deck = new Deck();
        Board board = new Board();
        Hand hand = new Hand(deck);
        Player player = new Player("Pewdiepie", deck, hand, board);

        assertEquals(deck, player.getDeck());
        assertNotEquals(hand, player.getDeck());
        assertNotEquals(board, player.getDeck());
    }

}
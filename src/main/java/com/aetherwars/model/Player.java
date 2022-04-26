package com.aetherwars.model;

public class Player {
    private final String name;
    private double health;
    private int mana;
    private Deck deck;
    private Hand hand;
    private Show show;
    private Board board;

    public Player(String name) {
        this.name = name;
        this.health = 80;
        this.mana = 1;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public Hand getHand() {
        return hand;
    }

    public Show getShow() {
        return show;
    }

    public void addHealth(int health) {
        this.health += health;
    }

    public void newTurn(int turn) {
        mana = Math.min(turn, 10);
    }

    public void drawCards() {
        show.fillShow();
    }

    public void pickCard(Card card) throws HandOverException {
        show.chooseCard(card);
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    // DECK
    public void setDeck(Deck deck){
        this.deck = deck;
    }

    public Deck getDeck(){
        return this.deck;
    }

    public boolean playCard(Character character, int index) {
        assert hand.cards.contains(character);
        hand.cards.remove(character);

        if (mana < character.getMana()) {
            return false;
        }

        try {
            board.setCard(character, index);
            return true;
        } catch (IllegalCardPlacementException e) {
            return false;
        }
    }
/*
    public boolean playCard(Spell spell, int index) {
        assert hand.cards.contains(spell);
        hand.cards.remove(spell);

        if (!hand.cards.contains(character)) {
            return false;
        }

        if (spell instanceof LevelSpell) {
            if (mana < Math.ceil(character.getLevel())) {
                return false;
            }
        }
        else {
            if (mana < spell.getMana()) {
                return false;
            }
        }

        character.attachSpell(spell);
        return true;
    }
*/
}

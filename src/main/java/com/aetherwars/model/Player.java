package com.aetherwars.model;

public class Player {
    private String name;
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

    // DECK
    public void setDeck(Deck deck){
        this.deck = deck;
    }

    public Deck getDeck(){
        return this.deck;
    }
}

package com.aetherwars.model;

public class Player {
    private final String name;
    private double health;
    private int mana;
    private int maxMana;
    private Deck deck;
    private Hand hand;
    private Show show;
    private Board board;

    public Player(String name, Deck deck, Hand hand, Board board) {
        this.name = name;
        this.health = 80;
        this.mana = 1;
        this.maxMana = 0;
        this.board = board;
        this.hand = hand;
        this.deck = deck;
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

    public int getMaxMana() {
        return maxMana;
    }

    public Hand getHand() {
        return hand;
    }

    public Show getShow() {
        return show;
    }

    public Board getBoard() {
        return this.board;
    }

    public Deck getDeck(){
        return this.deck;
    }

    public void addHealth(int health) {
        this.health += health;
    }

    public void newTurn() {
        if (maxMana < 10)
            maxMana += 1;
        mana = maxMana;
    }

    public void drawCards() {
        show.fillShow();
    }

    public void pickCard(Card card) throws HandOverException {
        show.chooseCard(card);
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

    public boolean playCard(Spell spell, int index) {
        assert hand.cards.contains(spell);
        hand.cards.remove(spell);

        Character character = board.getCard(index);

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

}

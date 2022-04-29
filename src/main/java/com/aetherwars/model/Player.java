package com.aetherwars.model;

public class Player {
    private final String name;
    private double health;
    private int mana;
    private int maxMana;
    private Deck deck;
    private Hand hand;
    private Board board;

    public Player(String name, Deck deck, Hand hand, Board board) {
        this.name = name;
        this.health = 80;
        this.mana = 1;
        this.maxMana = 1;
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

    public boolean isDead(){
        return  (this.health == 0);
    }

    public Board getBoard() {
        return this.board;
    }

    public Deck getDeck(){
        return this.deck;
    }

    public void addHealth(double health) {
        this.health += health;
        if (this.health < 0)
            this.health = 0;
    }

    public void newTurn() {
        if (maxMana < 10)
            maxMana += 1;
        mana = maxMana;

        board.getCards().stream().forEach(c -> {if (c != null) c.newTurn();});
    }

    public void playCard(Character character, int index) {
        assert hand.cards.contains(character);

        if (mana < character.getMana()) {
            return;
        }

        try {
            board.setCard(character, index);
            hand.cards.remove(character);
            mana -= character.getMana();
        } catch (IllegalCardPlacementException ignored) {
        }
    }

    public void playCard(Spell spell, int index) {
        assert hand.cards.contains(spell);

        Character character = board.getCard(index);
        if (character == null) {
            return;
        }

        int neededMana;
        if (spell instanceof LevelSpell) {
            neededMana = (int) Math.ceil((double) character.getLevel() / 2);
        }
        else {
            neededMana = spell.getMana();
        }

        if (mana < neededMana) {
            return;
        }
        mana -= neededMana;
        character.attachSpell(spell);
        hand.cards.remove(spell);
    }

    public void playCard(Player player, Morph morph, int index) {
        assert hand.cards.contains(morph);

        Character character = player.getBoard().getCard(index);
        if (character == null) {
            return;
        }

        int neededMana = morph.getMana();
        if (mana < neededMana) {
            return;
        }
        mana -= neededMana;
        character.attachSpell(morph);
        hand.cards.remove(morph);
    }

    public void giveExp(Character c, int exp) {
        assert c != null;

        if (mana < exp) {
            return;
        }
        mana -= exp;
        c.addExp(exp);
    }

}

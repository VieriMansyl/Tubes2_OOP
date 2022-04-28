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
        board.getCards().forEach(Character::newTurn);
    }

    public void drawCards() {
        show.fillShow();
    }

    public void pickCard(Card card) throws HandOverException {
        show.chooseCard(card);
    }

    public void playCard(Character character, int index) {
        assert hand.cards.contains(character);

        if (mana < character.getMana()) {
            System.out.println(character.getName() +  " mana kurang mas");
            return;
        }

        try {
            board.setCard(character, index);
            hand.cards.remove(character);
            System.out.println("success character");
        } catch (IllegalCardPlacementException e) {
            return;
        }
    }

    public void playCard(Spell spell, int index) {
        assert hand.cards.contains(spell);

        Character character = board.getCard(index);
        if (spell instanceof LevelSpell) {
            if (mana < Math.ceil(character.getLevel())) {
                return;
            }
        }
        else {
            if (mana < spell.getMana()) {
                return;
            }
        }

        character.attachSpell(spell);
        hand.cards.remove(spell);
        System.out.println("success spell");
    }

}

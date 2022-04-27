package com.aetherwars.model;

import java.util.*;

abstract public class Card {
    protected int id;
    protected CardType type;
    protected String name;
    protected String desc;
    protected String imgSrc;
    protected int mana;
    public static List<Card> availableCard = new ArrayList<>();

    public Card(int id, String name, String desc, String imgSrc, int mana) {
        this.id = id;
        this.imgSrc = imgSrc;
        this.name = name;
        this.desc = desc;
        this.mana = mana;

    }

    public int getId() {
        return id;
    }

    public CardType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getMana() {
        return mana;
    }

    public String getImgSrc() { return this.imgSrc; }

    public static Card getCard(int id) {
        for (Card c : availableCard) {
            if (c.id == id)
                return c;
        }
        return null;
    }

    public static Card getNewCard(int id) {
        Card tc = getCard(id);
        if (tc == null)
            return null;

        Card nc;
        if (tc instanceof Character) {
            nc = new Character(tc.id, tc.name, ((Character) tc).characterType, tc.desc,
                    tc.imgSrc, ((Character) tc).baseAttack, ((Character) tc).baseHealth,
                    tc.mana, ((Character) tc).attackUp, ((Character) tc).healthUp);
        }
        else {      // instance of Spell
            if (tc instanceof Potion) {
                nc = new Potion(tc.id, tc.name, tc.desc, tc.imgSrc, ((Potion) tc).getAtk(), ((Potion) tc).getHp(),
                        tc.mana, ((Potion) tc).getDuration());
            }
            else if (tc instanceof Morph) {
                nc = new Morph(tc.id,  tc.name, tc.desc, tc.imgSrc, tc.mana, ((Morph) tc).getCharacterID());
            }
            else if (tc instanceof Swap) {
                nc = new Swap(tc.id,  tc.name, tc.desc, tc.imgSrc, ((Swap) tc).getDuration(), tc.mana);
            }
            else {      // instance of LevelSpell
                nc = new LevelSpell(tc.id, tc.name, tc.desc, tc.imgSrc, ((LevelSpell) tc).getLvlEffect());
            }
        }
        return nc;
    }
}

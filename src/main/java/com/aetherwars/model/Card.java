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

    public Card(int id, String name, String desc, String imgSrc, int mana){
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

    public static Card getCard(int id) {
        for (Card c : availableCard) {
            if (c.id == id)
                return c;
        }
        return null;
    }
}

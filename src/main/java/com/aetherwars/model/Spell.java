package com.aetherwars.model;

public class Spell extends Card{
    public Spell(int id, String name, String desc, String imgSrc, int mana){
        super(id, name, desc, imgSrc, mana);
        this.type = CardType.SPELL;
    }
}

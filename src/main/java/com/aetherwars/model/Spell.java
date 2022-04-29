package com.aetherwars.model;

abstract public class Spell extends Card implements SpellAction {
    public Spell(int id, String name, String desc, String imgSrc, int mana){
        super(id, name, desc, imgSrc, mana);
    }

    abstract public void effect(Character target);

    abstract public String getInfo();
}
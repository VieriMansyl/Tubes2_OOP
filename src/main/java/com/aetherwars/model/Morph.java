package com.aetherwars.model;

public class Morph extends Spell implements SpellAction{
    private int characterID;

    public Morph(int id, String name, String desc,  String imgSrc, int mana, int characterID){
        super(id, name, desc, imgSrc, mana);
        this.characterID = characterID;
    }

    public void effect (Character target){
        // Effect Morph
        target.morph(characterID);
    }
}
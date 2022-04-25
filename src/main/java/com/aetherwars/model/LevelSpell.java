package com.aetherwars.model;

public class LevelSpell extends Spell implements SpellAction{
    private int lvl;    //Nilainya +1 atau -1

    public LevelSpell(int id, String name, String desc, String imgSrc, int mana,int lvl){
        super(id, name, desc, imgSrc, mana);
        this.lvl = lvl;
    }

    public void effect(Character target){
        int temp = target.getLevel() + this.lvl;

        temp = Math.max(0, temp);
        temp = Math.min(temp, 10);

        target.setLevel(temp);
        target.setExp(0);
    }
}

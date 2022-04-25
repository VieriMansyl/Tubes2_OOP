package com.aetherwars.model;

public class LevelSpell extends Spell {
    private final int lvl;

    public LevelSpell(int id, String name, String desc, String imgSrc, int lvl){
        super(id, name, desc, imgSrc, 0);
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

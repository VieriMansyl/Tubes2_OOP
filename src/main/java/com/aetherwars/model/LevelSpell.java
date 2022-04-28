package com.aetherwars.model;

public class LevelSpell extends Spell {
    private final int lvl;

    public LevelSpell(int id, String name, String desc, String imgSrc, int lvl){
        super(id, name, desc, imgSrc, -1);
        this.lvl = lvl;
    }

    public int getLvlEffect(){
        return  this.lvl;
    }

    public void effect(Character target){
        target.addLevel(this.lvl);
        target.resetExp();
    }

    public String getInfo(){
        return (name);
    }

}

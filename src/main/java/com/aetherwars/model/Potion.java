package com.aetherwars.model;

public class Potion extends Spell {
    private int duration;
    private final int atk;
    private final int hp;

    public Potion(int id, String name, String desc, String imgSrc, int atk, int hp, int mana, int duration){
        super(id, name, desc, imgSrc, mana);
        this.duration = duration;
        this.atk = atk;
        this.hp = hp;
    }

    public void effect(Character target){
        target.addTempHealth(this.hp);
        target.addTempAttack(this.atk);
    }

    public void substractDuration(){
        this.duration--;
    }

    public int getDuration(){
        return this.duration;
    }
}

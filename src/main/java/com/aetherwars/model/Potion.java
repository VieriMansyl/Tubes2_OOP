package com.aetherwars.model;

public class Potion extends Spell implements HasDuration {
    private int duration;
    private final int atk;
    private final int hp;

    public Potion(int id, String name, String desc, String imgSrc, int atk, int hp, int mana, int duration){
        super(id, name, desc, imgSrc, mana);
        this.duration = duration;
        this.atk = atk;
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getHp() {
        return hp;
    }

    public void effect(Character target){
        target.addTempHealth(this.hp);
        target.addTempAttack(this.atk);
        this.duration -= 1;
    }

    public int getDuration(){
        return this.duration;
    }

    public void addDuration(int duration) {
        this.duration += duration;
    }

    public String getInfo(){
        return ("ATK" + atk + "/HP" + hp);
    }
}

package com.aetherwars.model;

public class Potion extends Spell implements HasDuration {
    private int duration;
    private int atk;
    private int hp;

    public Potion(int id, String name, String desc, String imgSrc, int atk, int hp, int mana, int duration){
        super(id, name, desc, imgSrc, mana);
        if (duration == 0) duration = -1;
        this.duration = duration;
        this.atk = atk;
        this.hp = hp;
    }

    public int getAtk() { return atk; }

    public int getHp() { return hp; }

    public double addHp (double hp){
        this.hp += hp;
        if (this.hp < 0){
            return this.hp;
        } else {
            return 0;
        }
    }

    public void effect(Character target){
        target.addTempHealth(this.hp);
        target.addTempAttack(this.atk);
    }

    public void swapStats() {
        int temp = atk;
        atk = hp;
        hp = temp;
    }

    public int getDuration(){
        return this.duration;
    }

    public void addDuration(int duration) {
        if (this.duration >= 0)
        this.duration += duration;
    }

    public String getInfo(){
        return ("ATK" + atk + "/HP" + hp);
    }
}

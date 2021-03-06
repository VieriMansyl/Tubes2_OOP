package com.aetherwars.model;

public class Swap extends Spell implements HasDuration {
    private int duration;

    public Swap(int id, String name, String desc, String imgSrc, int duration,int mana){
        super(id, name, desc, imgSrc, mana);
        if (duration == 0) duration = -1;
        this.duration = duration;
    }

    public int getDuration(){
        return this.duration;
    }

    public void addDuration(int duration) {
        if (this.duration >= -1)
            this.duration += duration;
    }

    public void effect(Character target){
        double temp = target.getCurrAttack();
        target.setAttack(target.getCurrHealth());
        target.setHealth(temp);
    }

    public String getInfo(){
        return ("ATK<->HP");
    }
    
}

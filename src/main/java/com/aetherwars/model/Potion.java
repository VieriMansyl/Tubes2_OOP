package com.aetherwars.model;

public class Potion extends Spell implements SpellAction{
    private int duration;
    private int atk;
    private int hp;

    public Potion(int id, String name, String desc, String imgSrc, int atk, int hp, int mana, int duration){
        super(id, name, desc, imgSrc, mana);
        this.duration = duration;
        this.atk = atk;
        this.hp = hp;
    }

    public void effect(Character target){
        
        // Harusnya Set HP dan Atk Target
        // Implement armor di character?
        target.addHealth(this.hp);
        target.addAttack(this.atk);
    }

    public void substractDuration(){
        this.duration--;
    }

    public int getDuration(){
        return this.duration;
    }
}

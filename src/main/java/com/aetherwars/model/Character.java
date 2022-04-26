package com.aetherwars.model;
import java.util.*;

public class Character extends Card implements CharacterAction {
    protected CharacterType characterType;
    protected double baseAttack;
    protected double baseHealth;
    protected int attackUp;
    protected int healthUp;
    protected double currAttack;
    protected double currHealth;
    protected int exp;
    protected int level;
    protected List<Spell> attachedSpells;
    static final int[] levelExp = {0, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19};

    public Character(int id, String name, CharacterType characterType, String desc,String imgSrc, int baseAttack, int baseHealth, int mana, int attackUp, int healthUp) {
        super(id, name, desc, imgSrc, mana);
        this.type = CardType.CHARACTER;
        this.characterType = characterType; 
        this.baseAttack = baseAttack;
        this.baseHealth = baseHealth;
        this.attackUp = attackUp;
        this.healthUp = healthUp;
        this.level = 1;
        this.exp = 0;
        this.attachedSpells = new ArrayList<>();
    }

    public double getCurrAttack() {
        return currAttack;
    }

    public double getCurrHealth() {
        return currHealth;
    }

    public int getExp(){
        return exp;
    }

    public int getLevel(){
        return level;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public List<Spell> getAttachedSpells() {
        return attachedSpells;
    }

    public void addHealth(double health) {
        baseHealth += health;
    }

    public void addAttack(double attack) {
        baseAttack += attack;
    }

    public void addTempHealth(double health) {
        currHealth += health;
    }

    public void addTempAttack(double attack) {
        currAttack += attack;
    }

    public void addExp(int exp) {
        this.exp += exp;
        levelUp();
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAttack(double attack) {
        this.currAttack = attack;
    }

    public void setHealth(double health) {
        this.currHealth = health;
    }

    public void attachSpell(Spell s) {
        if (s instanceof Morph || s instanceof LevelSpell) {
            s.effect(this);
        }
        else {
            attachedSpells.add(s);
        }
    }

    public boolean isDead() {
        return currHealth == 0;
    }

    public void newTurn() {
        this.currAttack = baseAttack;
        this.currHealth = baseHealth;
        spellEffect();
        if (currAttack < 0)
            currAttack = 0;
        if (currHealth < 0)
            currHealth = 0;
    }

    private void spellEffect() {
        attachedSpells.forEach(s -> s.effect(this));
    }

    private void levelUp() {
        if (exp < levelExp[level])
            return;
        exp -= levelExp[level];
        level += 1;
        baseAttack += attackUp;
        baseHealth += healthUp;
    }

    public void attack(Character target) {
        double typeMultiplier = CharacterAction.typeEffectiveness[characterType.ordinal()]
                [target.characterType.ordinal()];

        double damage = typeMultiplier * currAttack;
        target.addHealth(-damage);

        if (target.currHealth == 0) {
            exp += target.level;
            levelUp();
        }
    }

    public void morph(int characterID){
        Character copyChar = (Character) Card.getCard(characterID);
        assert copyChar != null;
        this.id = copyChar.id;
        this.characterType = copyChar.characterType;
        this.name = copyChar.name;
        this.desc = copyChar.desc;
        this.mana = copyChar.mana;
        this.imgSrc = copyChar.imgSrc;
        this.baseAttack = copyChar.baseAttack;
        this.baseHealth = copyChar.baseHealth;
        this.level = 1;
        this.exp = 0;
        this.attachedSpells.clear();
    }
}


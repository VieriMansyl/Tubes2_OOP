package com.aetherwars.model;
import java.util.*;

public class Character extends Card implements CharacterAction {
    protected CharacterType characterType;
    protected double baseAttack;
    protected double baseHealth;
    protected double maxHealth;
    protected int attackUp;
    protected int healthUp;
    protected double currAttack;
    protected double currHealth;
    protected int exp;
    protected int level;
    protected boolean dead;
    protected List<Spell> attachedSpells;
    protected boolean attackable;

    public Character(int id, String name, CharacterType characterType, String desc,String imgSrc, double baseAttack,
                     double baseHealth, int mana, int attackUp, int healthUp) {
        super(id, name, desc, imgSrc, mana);
        this.characterType = characterType; 
        this.baseAttack = baseAttack;
        this.baseHealth = baseHealth;
        this.maxHealth = baseHealth;
        this.currHealth = baseHealth;
        this.currAttack =baseAttack;
        this.attackUp = attackUp;
        this.healthUp = healthUp;
        this.level = 1;
        this.exp = 0;
        this.dead = false;
        this.attachedSpells = new ArrayList<>();
        this.attackable = true;
    }

    public double getBaseAttack() {
        return baseAttack;
    }

    public double getBaseHealth() {
        return baseHealth;
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

    public int getCapExp() {
        return 2 * level - 1;
    }

    public int getLevel(){
        return level;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isAttackable() {
        return attackable;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public List<Spell> getAttachedSpells() {
        return attachedSpells;
    }

    public void addHealth(double health) {
        baseHealth += health;
        if (baseHealth <= 0)
            dead = true;
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
        while (exp >= getCapExp())
        {System.out.println("masuk level up"); levelUp(true);}
    }

    public void resetExp() {
        this.exp = 0;
    }

    public void addLevel(int level) {
        if (level > 0)
            while (level > 0 && this.level >= 1 && this.level < 10) {
                levelUp(false);
                level -= 1;
            }
        else {
            this.level -= level;
            if (this.level < 1)
                this.level = 1;
        }
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
        else if (s instanceof Swap) {
            boolean found = false;
            for (Spell existingSpell: attachedSpells) {
                if (existingSpell instanceof Swap) {
                    ((Swap) existingSpell).addDuration(((Swap) s).getDuration());
                    found = true;
                    break;
                }
            }
            if (!found)
                attachedSpells.add(s);
        }
        else {
            attachedSpells.add(s);
        }
    }

    public void newTurn() {
        this.currAttack = baseAttack;
        this.currHealth = baseHealth;
        spellEffect();
        if (currAttack < 0)
            currAttack = 0;
        if (currHealth < 0) {
            currHealth = 0;
            dead = true;
        }

        this.attackable = true;
    }

    // Use spell first, then check if duration equals 0
    // Purpose is to ignore permanent spell
    private void spellEffect() {
        attachedSpells.forEach(spell -> spell.effect(this));
        Iterator<Spell> iter = attachedSpells.iterator();
        while (iter.hasNext()) {
            Spell spell = iter.next();
            assert spell instanceof HasDuration;
            if (((HasDuration) spell).getDuration() == 0)
//                    this.setHealth(-spell.getattack);
                iter.remove();
        }
    }

    private void levelUp(boolean consumeExp) {
        if (consumeExp) {
            int cap = getCapExp();
            if (exp < cap) {
                System.out.println("belum cukup");
                return;
            }
            exp -= cap;
        }
        if (level < 10) {
            System.out.println("hi");
            level += 1;
            baseAttack += attackUp;
            maxHealth += healthUp;
        }
        baseHealth = maxHealth;
    }

    public void attack(Character target) {
        double typeMultiplier = CharacterAction.typeEffectiveness[characterType.ordinal()]
                [target.characterType.ordinal()];

        double damage = typeMultiplier * currAttack;
        target.addHealth(-damage);

        if (target.currHealth <= 0) {
            addExp(target.level);
        }
    }

    public void attack(Player target) {

        double damage =  currAttack;
        target.addHealth(-damage);

        if (target.getHealth() == 0) {
            //end game
        }
    }

    public void hasInitiatedAttack() {
        this.attackable = false;
    }

    // Change whole data without changing object reference
    public void morph(int characterID) {
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


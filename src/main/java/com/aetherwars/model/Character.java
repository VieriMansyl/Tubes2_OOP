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
    protected List<Potion> swappedPotions;
    protected boolean swapped;
    protected double swappedHealthUp;
    protected double swappedAttackUp;
    protected boolean attackable;

    public Character(int id, String name, CharacterType characterType, String desc,String imgSrc, double baseAttack,
                     double baseHealth, int mana, int attackUp, int healthUp) {
        super(id, name, desc, imgSrc, mana);
        this.characterType = characterType; 
        this.baseAttack = baseAttack;
        this.baseHealth = baseHealth;
        this.maxHealth = baseHealth;
        this.currHealth = baseHealth;
        this.currAttack = baseAttack;
        this.attackUp = attackUp;
        this.healthUp = healthUp;
        this.level = 1;
        this.exp = 0;
        this.dead = false;
        this.attachedSpells = new ArrayList<>();
        this.swappedPotions = new ArrayList<>();
        this.swapped = false;
        this.swappedAttackUp = 0;
        this.swappedHealthUp = 0;
        this.attackable = true;
    }

    public double getBaseAttack() {
        return baseAttack;
    }

    public double getBaseHealth() {
        return baseHealth;
    }

    public double getCurrAttack() {
        
        spellEffect();
        return currAttack;
    }

    public double getCurrHealth() {
        // currHealth = baseHealth;
        spellEffect();
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
    
    public void addTempHealth(double health) {
        currHealth += health;
    }

    public void addTempAttack(double attack) {
        currAttack += attack;
    }

    public void addHealth(double health) {
        if (health < 0) {   // damage
            Collections.reverse(attachedSpells);
            for (Spell s : attachedSpells) {
                if (s instanceof Potion)
                    health = ((Potion) s).addHp(health);
                if (health == 0)
                    break;
            }
            Collections.reverse(attachedSpells);
            if (health < 0) {
                this.baseHealth += health;
            }
            if (this.baseHealth <= 0) {
                this.dead = true;
            }
        }
        else {      // heal to base
            baseHealth += health;
        }
    }

    public void addAttack(double attack) {
        baseAttack += attack;
    }

    public void addExp(int exp) {
        this.exp += exp;
        while (this.exp >= getCapExp() && this.level < 10)
            levelUp(true);
        if (this.level == 10 && this.exp >= getCapExp()) {
            this.exp = getCapExp();
        }
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
            this.level += level;
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

    private void swapStats() {
        double temp = baseAttack;
        baseAttack = baseHealth;
        baseHealth = temp;
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
            if (!found) {
                for (Spell existingSpell : attachedSpells) {
                    if (existingSpell instanceof Potion) {
                        ((Potion) existingSpell).swapStats();
                        swappedPotions.add((Potion) existingSpell);
                    }
                }
                attachedSpells.add(s);
                swapStats();
                swapped = true;
            }
        }
        else {
            attachedSpells.add(s);
        }
    }

    public void newTurn() {
        // Check spell duration
        Iterator<Spell> iter = attachedSpells.iterator();
        while (iter.hasNext()) {
            Spell spell = iter.next();
            assert spell instanceof HasDuration;
            ((HasDuration) spell).addDuration(-1);
            // To accomodate permanent duration
            if (((HasDuration) spell).getDuration() == -1) {
                // If swap ended
                if (spell instanceof Swap) {
                    swappedPotions.stream().forEach(Potion::swapStats);
                    swapped = false;
                    swapStats();
                    baseAttack = baseHealth - swappedHealthUp + swappedAttackUp;
                    baseHealth = baseAttack - swappedAttackUp + swappedHealthUp;
                    swappedAttackUp = 0;
                    swappedHealthUp = 0;
                }
                iter.remove();
            }

            if (spell instanceof Potion) {
                swappedPotions.remove(spell);
            }
        }

        spellEffect();

        if (currHealth < 0) {
            currHealth = 0;
            dead = true;
        }

        this.attackable = true;
    }

    // Use spell first, then check if duration equals 0
    // Purpose is to ignore permanent spell
    private void spellEffect() {
        currHealth = baseHealth;
        currAttack = baseAttack;

        attachedSpells.forEach(spell -> {if (spell instanceof Potion) spell.effect(this);});

        if (currHealth <= 0)
       
            this.dead = true;
    }

    private void levelUp (boolean consumeExp) {
        if (consumeExp) {
            int cap = getCapExp();
            if (exp < cap) {
                return;
            }
            exp -= cap;
        }
        if (level < 10) {
            level += 1;
            baseAttack += attackUp;
            maxHealth += healthUp;
            if (swapped) {
                swappedAttackUp += attackUp;
                swappedHealthUp += healthUp;
            }
        }
        baseHealth = maxHealth;
    }

    public void attack(Character target) {

        if (currAttack > 0) {
            double typeMultiplier = CharacterAction.typeEffectiveness[characterType.ordinal()]
                    [target.characterType.ordinal()];

            double damage = typeMultiplier * currAttack;
            target.addHealth(-damage);

            if (target.isDead()) {
                addExp(target.level);
            }
           
        }
    }

    public void attack(Player target) {
            
        if (currAttack > 0)
            target.addHealth(-currAttack);
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
        this.swappedPotions.clear();
        this.swappedAttackUp = 0;
        this.swappedHealthUp = 0;
        this.swapped = false;
    }

}


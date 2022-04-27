package com.aetherwars.model;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {
    @Test
    public void testCharacterID() {
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);
        assertEquals(1, c.getId());
    }

    @Test
    public void testCharacterCharacterType() {
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);
        assertEquals(CharacterType.OVERWORLD, c.getCharacterType());
    }

    @Test
    public void testCharacterName() {
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);
        assertEquals("Steve", c.getName());
    }

    @Test
    public void testCharacterType() {
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);
        assertEquals(CharacterType.OVERWORLD, c.getCharacterType());
    }

    @Test
    public void testCharacterLevel() {
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);
        c.addLevel(2);

        assertEquals(3, c.getLevel());
    }

    @Test
    public void testCharacterAttack() {
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);
        c.setAttack(3);

        assertEquals(3, c.getCurrAttack());
    }

    @Test
    public void testCharacterHealth() {
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);
        c.setHealth(3);

        assertEquals(3, c.getCurrHealth());
    }

    @Test
    public void testCharacterAttachSpell() {
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);

        Spell s = new Potion(100, "Spell", "This is a spell",
                "src", 1, 1, 1, 1);
        c.attachSpell(s);

        List<Spell> spell_list = new ArrayList<>();
        spell_list.add(s);

        assertEquals(spell_list, c.getAttachedSpells());
    }

    @Test
    public void testCharacterSetExp() {
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);

        c.addExp(1);

        // Karena level up, exp reset ke 0
        assertEquals(0, c.getExp());
    }

    @Test
    public void testCharacterResetExp() {
        Character c = new Character(1, "Steve", CharacterType.OVERWORLD, "Pro Minecrafter",
                "src", 1, 1, 1, 1, 1);

        c.addExp(1000);
        c.resetExp();
        assertEquals(0, c.getExp());
    }
}
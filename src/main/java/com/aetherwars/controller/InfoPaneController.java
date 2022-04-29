package com.aetherwars.controller;

import com.aetherwars.model.*;
import com.aetherwars.model.Character;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class InfoPaneController {

    @FXML
    private Label name;

    @FXML
    private Label type;

    @FXML
    private Text cardInfo;

    @FXML
    private Text description;

    @FXML
    private Text currAppliedSpell;

    @FXML
    private ImageView cardImage;

    public void setUpInfoPane (Card currCard) {
        // card's name
        name.setText(currCard.getName());

        // card's description
        description.setText(currCard.getDesc());

        if (currCard instanceof Character){
            // current card is a character card
            type.setText(((Character) currCard).getCharacterType().toString());

            String atkBuffInfo = String.valueOf(((Character) currCard).getCurrAttack() - ((Character) currCard).getBaseAttack());
            String hpBuffInfo = String.valueOf(((Character) currCard).getCurrHealth() - ((Character) currCard).getBaseHealth());
            String atkInfo = "ATK : " + ((Character) currCard).getBaseAttack() + "(+" + atkBuffInfo + ")\n";
            String hpInfo = "HP : " + ((Character) currCard).getBaseHealth() + "(+" + hpBuffInfo + ")\n";
            String levelInfo = "Level : " + ((Character) currCard).getLevel() + "\n";
            String expInfo = "Exp : " + ((Character) currCard).getExp() + "/" + ((Character) currCard).getCapExp() + "\n";

            StringBuilder currSpells = new StringBuilder();
            List<Spell> attachedSpells = ((Character) currCard).getAttachedSpells();

            // character card's current applied spell(s)
            if (attachedSpells.isEmpty()){
                currAppliedSpell.setText("there's no spell card applied");
            }else {
                for (Spell spell : attachedSpells) {
                    currSpells.append(spell.getName()).append("\n");
                }
                currAppliedSpell.setText(currSpells.toString());
            }

            // character card's info
            cardInfo.setText(atkInfo + hpInfo + levelInfo + expInfo);
        }else{
            // current card is a spell card
            String[] temp = currCard.getClass().getName().split("\\.");

            // spell card's type
            type.setText(String.valueOf(temp[temp.length -1 ]));

            // spell card's buff info
            String manaInfo = "Mana     : " + currCard.getMana() + "\n";
            if (currCard instanceof Potion){
                String atkBuffInfo = "Attack   : + " + ((Potion) currCard).getAtk() + ")\n";
                String hpBuffInfo = "Health   : + " + ((Potion) currCard).getHp() + ")\n";
                String duration = "Duration : " + ((Potion) currCard).getDuration() + "\n";
                cardInfo.setText(atkBuffInfo + hpBuffInfo + manaInfo + duration);
            }else if (currCard instanceof Swap) {
                String duration = "Duration : " + ((Swap) currCard).getDuration() + "\n";
                cardInfo.setText(manaInfo + duration);
            }

            // spell card's current applied spell : None
            currAppliedSpell.setText("It is a spell card");
        }
        // card's image
        cardImage.setImage(new Image("/com/aetherwars/" + currCard.getImgSrc()));
        BoardController.centerImage(cardImage);
    }
}

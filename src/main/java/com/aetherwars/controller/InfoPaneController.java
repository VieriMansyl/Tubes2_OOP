package com.aetherwars.controller;

import com.aetherwars.model.Card;
import com.aetherwars.model.Character;
import com.aetherwars.model.Potion;
import com.aetherwars.model.Spell;
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

    // Color code on Text
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public void setUpInfoPane (Card currCard) {

        name.setText(currCard.getName());
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
            if (attachedSpells.isEmpty()){
                currAppliedSpell.setText("there's no spell card applied");
            }else {
                for (Spell spell : attachedSpells) {
                    currSpells.append(spell.getName()).append("\n");
                }
                currAppliedSpell.setText(currSpells.toString());
            }

            cardInfo.setText(atkInfo + hpInfo + levelInfo + expInfo);
            // TO-DO : setText pada ATK dan HP belum interaktif terhadap attached spell

        }else{
            // current card is a spell card
            String[] temp = currCard.getClass().getName().split("\\.");

            type.setText(String.valueOf(temp[temp.length -1 ]));

            String manaInfo = "Mana : " + currCard.getMana() + "\n";
            cardInfo.setText(manaInfo);
            currAppliedSpell.setText("It is a spell card");
        }
        cardImage.setImage(new Image("/com/aetherwars/" + currCard.getImgSrc()));
        BoardController.centerImage(cardImage);
    }
}

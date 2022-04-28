package com.aetherwars.controller;

import com.aetherwars.model.Card;
import com.aetherwars.model.Character;
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

    public void setUpInfoPane (Card currCard) {

        name.setText(currCard.getName());
        if (currCard instanceof Character){
            // current card is a character card
            type.setText(((Character) currCard).getCharacterType().toString());

            String atkInfo = "ATK : " + ((Character) currCard).getCurrAttack() + "\n";
            String hpInfo = "HP : " + ((Character)currCard).getCurrHealth() + "\n";
            String levelInfo = "Level : " + ((Character) currCard).getLevel() + "\n";
            String expInfo = "Exp : " + ((Character) currCard).getExp() + "/" + ((Character) currCard).getCapExp() + "\n";
            cardInfo.setText(atkInfo + hpInfo + levelInfo + expInfo);

            StringBuilder currSpells = new StringBuilder();
            List<Spell> attachedSpells = ((Character) currCard).getAttachedSpells();
            for (Spell spell : attachedSpells) {
                currSpells.append(spell.getName()).append("\n");
            }
            currAppliedSpell.setText(currSpells.toString());

            // TO-DO : setText pada ATK dan HP belum interaktif terhadap attached spell

        }else{
            // current card is a spell card
            type.setText(currCard.getClass().getName());

            String manaInfo = "Mana : " + currCard.getMana() + "\n";
            cardInfo.setText(manaInfo);
            currAppliedSpell.setText("It is a spell card");
        }
//        cardImage.setImage(new Image("/com/aetherwars/" + curr.getImgSrc()));
//        BoardController.centerImage(cardImage);
    }
}

package com.aetherwars.controller;

import com.aetherwars.model.*;
import com.aetherwars.model.Character;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HandCharacterCardController {
    @FXML
    private Label handCharacterCardAtk;

    @FXML
    private Label handCharacterCardHp;

    @FXML
    private ImageView handCharacterCardImage;

    @FXML
    private Label handCharacterCardMana;

    public void setCard(Character cur) {
        handCharacterCardMana.setText(Integer.toString(cur.getMana()));
        handCharacterCardHp.setText(Double.toString(cur.getCurrHealth()));
        handCharacterCardAtk.setText(Double.toString(cur.getCurrAttack()));
        handCharacterCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
    }
}

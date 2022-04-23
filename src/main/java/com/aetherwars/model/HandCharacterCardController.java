package com.aetherwars.model;

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

    public void setCard(/* Card Hand */) {
        handCharacterCardAtk.setText("10");
        handCharacterCardHp.setText("100");
        handCharacterCardMana.setText("5");
        handCharacterCardImage.setImage(new Image("/com/aetherwars/card/image/character/Creeper.png"));
    }
}

package com.aetherwars.model;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HandSpellCardController {
    @FXML
    private ImageView handSpellCardImage;

    @FXML
    private Label handSpellCardMana;

    @FXML
    private Label handSpellCardSpell;

    public void setCard(/* Card Hand */) {
        handSpellCardMana.setText("5");
        handSpellCardSpell.setText("Sugondese");
        handSpellCardImage.setImage(new Image("/com/aetherwars/card/image/spell/morph/Sugondese.png"));
    }
}

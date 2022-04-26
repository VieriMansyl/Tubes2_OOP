package com.aetherwars.controller;

import com.aetherwars.model.*;
import com.aetherwars.model.Character;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

public class HandSpellCardController {
    @FXML
    private Pane SpellCard;

    @FXML
    private ImageView handSpellCardImage;

    @FXML
    private Label handSpellCardMana;

    @FXML
    private Label handSpellCardSpell;

    public void setCard(Spell cur) {
        if (cur != null){
            handSpellCardMana.setText(Integer.toString(cur.getMana()));
            handSpellCardSpell.setText(cur.getName());
            handSpellCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
        }
    }

    @FXML
    void click(MouseEvent event) {

    }

    @FXML
    void handleCardDragDetection(MouseEvent event) {
        System.out.println("DRAG");
        Dragboard db = SpellCard.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        cb.putString("ISI");

        db.setContent(cb);

        event.consume();
    }
}
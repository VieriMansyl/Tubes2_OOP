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

    private int test;

    private Card card;

    public void setCard(Card cur /*int i*/) {
        if (cur != null){
            this.card = cur; /* apakah harus membuat objek baru atau reference saja cukup? */
            this.test = 0;
            handSpellCardMana.setText("5");
            handSpellCardSpell.setText("Sugondese");
            handSpellCardImage.setImage(new Image("/com/aetherwars/card/image/character/Shulker.png" /*+ cur.imgSrc*/));
        }
    }

    @FXML
    void click(MouseEvent event) {
        System.out.println(this.test);
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
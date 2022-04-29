package com.aetherwars.controller;

import com.aetherwars.model.Phase;
import com.aetherwars.model.Spell;
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
    private Label handSpellCardName;

    @FXML
    private ImageView handSpellCardImage;

    @FXML
    private Label handSpellCardMana;

    @FXML
    private Label handSpellCardSpell;

    private int idx;
    private Spell card;

    public void setCard(Spell cur, int idx) {
        if (cur != null){
            this.idx = idx;
            this.card = cur;
            handSpellCardName.setText(cur.getName());
            handSpellCardMana.setText(Integer.toString(cur.getMana()));
            handSpellCardSpell.setText(cur.getInfo());
            handSpellCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
            BoardController.centerImage(handSpellCardImage);
        }
    }

    @FXML
    void handleCardDragDetection(MouseEvent event) {
        if (BoardController.getInstance().getCurrPhase() == Phase.PLAN) {
            Dragboard db = SpellCard.startDragAndDrop(TransferMode.ANY);

            ClipboardContent cb = new ClipboardContent();
            cb.putString(String.valueOf(this.idx));

            db.setContent(cb);

            event.consume();
        }
    }

    @FXML
    void onHover(MouseEvent event) {
        BoardController.getInstance().displayInfoPane(this.card);
        BoardController.getInstance().getGiveExpButton().setVisible(false);
    }

}
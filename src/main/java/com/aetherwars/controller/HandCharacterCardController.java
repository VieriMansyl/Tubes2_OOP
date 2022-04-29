package com.aetherwars.controller;

import com.aetherwars.model.Card;
import com.aetherwars.model.Character;

import com.aetherwars.model.Phase;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

public class HandCharacterCardController {
    @FXML
    private Pane CharacterCard;

    @FXML
    private Label handCharacterCardAtk;

    @FXML
    private Label handCharacterCardHp;

    @FXML
    private ImageView handCharacterCardImage;

    @FXML
    private Label handCharacterCardMana;

    private int idx;
    private Character card;

    public void setCard(Character cur, int idx) {
        if (cur != null) {
            this.idx = idx;
            this.card = cur;
            handCharacterCardMana.setText(Integer.toString(cur.getMana()));
            handCharacterCardHp.setText(Double.toString(cur.getCurrHealth()));
            handCharacterCardAtk.setText(Double.toString(cur.getCurrAttack()));
            handCharacterCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
            BoardController.centerImage(handCharacterCardImage);
        }
    }

    @FXML
    void handleCardDragDetection(MouseEvent event) {
        if (BoardController.getInstance().getCurrPhase() == Phase.PLAN) {
            Dragboard db = CharacterCard.startDragAndDrop(TransferMode.ANY);

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

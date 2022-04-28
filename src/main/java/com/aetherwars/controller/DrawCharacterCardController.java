package com.aetherwars.controller;

import com.aetherwars.model.Card;
import com.aetherwars.model.Character;
import com.aetherwars.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DrawCharacterCardController {

    @FXML
    private Label handCharacterCardAtk;

    @FXML
    private Label handCharacterCardHp;

    @FXML
    private ImageView handCharacterCardImage;

    @FXML
    private Label handCharacterCardMana;

    private FlowPane hand, drawPane;
    private Character card;
    private Player player;
    private int idx;

    public void setCard(Player player,Character cur, int idx, FlowPane hand, FlowPane drawPane) {
        if (cur != null){
            this.player = player;
            this.idx = idx;
            this.hand = hand;
            this.card = cur;
            this.drawPane = drawPane;
            handCharacterCardMana.setText(Integer.toString(cur.getMana()));
            handCharacterCardHp.setText(Double.toString(cur.getCurrHealth()));
            handCharacterCardAtk.setText(Double.toString(cur.getCurrAttack()));
            handCharacterCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
            BoardController.centerImage(handCharacterCardImage);
        }
    }

    @FXML
    void onMouseClick(MouseEvent event) {
        try {
            this.player.getHand().addCard(this.card);
            this.player.getDeck().removeCard(this.card);
            this.drawPane.setVisible(false);
            BoardController.getInstance().refreshBoard();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}

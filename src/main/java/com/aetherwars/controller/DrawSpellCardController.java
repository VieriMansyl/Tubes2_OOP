package com.aetherwars.controller;

import com.aetherwars.model.*;
import com.aetherwars.model.Character;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DrawSpellCardController {

    @FXML
    private Pane SpellCard;

    @FXML
    private ImageView handSpellCardImage;

    @FXML
    private Label handSpellCardMana;

    @FXML
    private Label handSpellCardSpell;

    private FlowPane hand, drawPane;
    private Spell card;
    private Player player;
    private int idx;


    public void setCard(Player player,Spell cur, int idx,FlowPane hand,  FlowPane drawPane) {
        if (cur != null){
            this.hand = hand;
            this.card = cur;
            this.drawPane = drawPane;
            this.player = player;
            this.idx = idx;
            handSpellCardMana.setText(Integer.toString(cur.getMana()));
            handSpellCardSpell.setText(cur.getName());
            handSpellCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
            BoardController.centerImage(handSpellCardImage);
        }
    }

    @FXML
    void onMouseClick(MouseEvent event) {

        try {

            this.player.getHand().addCard(this.card);

            this.player.getDeck().removeCard(this.card);

            this.drawPane.setVisible(false);
            BoardController.getInstance().setPhaseToPlan();
            BoardController.getInstance().refreshBoard();

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @FXML
    void onHover(MouseEvent event) {
        BoardController.getInstance().displayInfoPane(this.card);
    }
}
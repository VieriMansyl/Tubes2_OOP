package com.aetherwars.controller;

import com.aetherwars.model.Card;
import com.aetherwars.model.Character;
import com.aetherwars.model.CharacterType;
import com.aetherwars.model.Spell;
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

    public void setCard(Spell cur, FlowPane hand, FlowPane drawPane) {
        if (cur != null){
            this.hand = hand;
            this.card = cur;
            this.drawPane = drawPane;
            handSpellCardMana.setText(Integer.toString(cur.getMana()));
            handSpellCardSpell.setText(cur.getName());
            handSpellCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
        }
    }

    @FXML
    void onMouseClick(MouseEvent event) throws IOException {
        try {
            FXMLLoader handCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
            Pane handPane = handCardLoader.load();

            HandSpellCardController handCardController = handCardLoader.getController();
            handCardController.setCard(this.card, hand.getChildren().size());
            hand.getChildren().add(handPane);
            this.drawPane.setVisible(false);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

}
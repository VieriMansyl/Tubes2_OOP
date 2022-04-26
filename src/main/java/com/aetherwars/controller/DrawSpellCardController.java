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

    public void centerImage() {
        Image img = handSpellCardImage.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = handSpellCardImage.getFitWidth() / img.getWidth();
            double ratioY = handSpellCardImage.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            handSpellCardImage.setX((handSpellCardImage.getFitWidth() - w) / 2);
            handSpellCardImage.setY((handSpellCardImage.getFitHeight() - h) / 2);

        }
    }

    public void setCard(Spell cur, FlowPane hand, FlowPane drawPane) {
        if (cur != null){
            this.hand = hand;
            this.card = cur;
            this.drawPane = drawPane;
            handSpellCardMana.setText(Integer.toString(cur.getMana()));
            handSpellCardSpell.setText(cur.getName());
            handSpellCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
            centerImage();
        }
    }

    @FXML
    void onMouseClick(MouseEvent event) throws IOException {
        try {
            FXMLLoader handCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
            Pane handPane = handCardLoader.load();

            HandSpellCardController handCardController = handCardLoader.getController();
            handCardController.setCard(this.card);
            hand.getChildren().add(handPane);
            this.drawPane.setVisible(false);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

}
package com.aetherwars.controller;

import com.aetherwars.model.Card;
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

    public void centerImage() {
        Image img = handCharacterCardImage.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = handCharacterCardImage.getFitWidth() / img.getWidth();
            double ratioY = handCharacterCardImage.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            handCharacterCardImage.setX((handCharacterCardImage.getFitWidth() - w) / 2);
            handCharacterCardImage.setY((handCharacterCardImage.getFitHeight() - h) / 2);

        }
    }

    public void setCard(Character cur, FlowPane hand, FlowPane drawPane) {
        if (cur != null){
            this.hand = hand;
            this.card = cur;
            this.drawPane = drawPane;
            handCharacterCardMana.setText(Integer.toString(cur.getMana()));
            handCharacterCardHp.setText(Double.toString(cur.getCurrHealth()));
            handCharacterCardAtk.setText(Double.toString(cur.getCurrAttack()));
            handCharacterCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
            centerImage();
        }
    }

    @FXML
    void onMouseClick(MouseEvent event) {
        try {
            FXMLLoader handCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handCharacterCard.fxml"));
            Pane handPane = handCardLoader.load();

            HandCharacterCardController handCardController = handCardLoader.getController();
            handCardController.setCard(this.card);
            hand.getChildren().add(handPane);
            this.drawPane.setVisible(false);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

}

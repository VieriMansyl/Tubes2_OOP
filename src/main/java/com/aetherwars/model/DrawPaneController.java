package com.aetherwars.model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DrawPaneController {

    @FXML
    private Pane card0;

    @FXML
    private Pane card1;

    @FXML
    private Pane card2;

    public void setDrawPane(/*Card 1 2 3 or maybe list*/) throws IOException {
        FXMLLoader handCardLoader0 = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
        Pane handPane0 = handCardLoader0.load();

        HandSpellCardController handCardController0 = handCardLoader0.getController();
        handCardController0.setCard(/*Card*/);
        card0.getChildren().add(handPane0);

        FXMLLoader handCardLoader1 = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
        Pane handPane1 = handCardLoader1.load();

        HandSpellCardController handCardController1 = handCardLoader1.getController();
        handCardController1.setCard(/*Card*/);
        card1.getChildren().add(handPane1);
//
        FXMLLoader handCardLoader2 = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
        Pane handPane2 = handCardLoader2.load();

        HandSpellCardController handCardController2 = handCardLoader2.getController();
        handCardController2.setCard(/*Card*/);
        card2.getChildren().add(handPane2);
    }
    @FXML
    void onChooseCard0(MouseEvent event) {
        System.out.println("Card 0");
    }

    @FXML
    void onChooseCard1(MouseEvent event) {
        System.out.println("Card 1");
    }

    @FXML
    void onChooseCard2(MouseEvent event) {
        System.out.println("Card 2");
    }

}
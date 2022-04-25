package com.aetherwars.controller;

//import com.aetherwars.model.Player;
//import com.aetherwars.model.Card;
//import com.aetherwars.model.Character;
import com.aetherwars.model.*;
import com.aetherwars.model.Character;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;

public class DrawPaneController {

    @FXML
    private Pane card0;

    @FXML
    private Pane card1;

    @FXML
    private Pane card2;

    private FlowPane hand;
    private Player currPlayer;

    public void setDrawPane(Player currPlayer, FlowPane hand) throws IOException {
        this.currPlayer = currPlayer;
        this.hand = hand;
        List<Card> listOfCard= currPlayer.getDeck().getTop3();
        FXMLLoader handCardLoader0 = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
        Pane handPane0 = handCardLoader0.load();

        HandSpellCardController handCardController0 = handCardLoader0.getController();
        handCardController0.setCard(listOfCard.get(0));
        card0.getChildren().add(handPane0);

        FXMLLoader handCardLoader1 = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
        Pane handPane1 = handCardLoader1.load();

        HandSpellCardController handCardController1 = handCardLoader1.getController();
        handCardController1.setCard(listOfCard.get(1));
        card1.getChildren().add(handPane1);
//
        FXMLLoader handCardLoader2 = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
        Pane handPane2 = handCardLoader2.load();

        HandSpellCardController handCardController2 = handCardLoader2.getController();
        handCardController2.setCard(listOfCard.get(2));
        card2.getChildren().add(handPane2);
    }
    @FXML
    void onChooseCard0(MouseEvent event) throws IllegalCardPlacementException, IOException {
        System.out.println("Card 0");
        currPlayer.getBoard().setCard((Character) currPlayer.getDeck().getTop3().get(0),0);
        System.out.println("berhasil set" + currPlayer.getBoard().getCard(0).getName());

        FXMLLoader handCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
        Pane handPane = handCardLoader.load();

        HandSpellCardController handCardController = handCardLoader.getController();
        handCardController.setCard(new Character(1, "Obsidian", CharacterType.valueOf("OVERWORLD"), "An obsidian block used to create Nether portals.", "card/image/character/Obsidian.png", 1, 25, 8, 0, 5));
        hand.getChildren().add(handPane);
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
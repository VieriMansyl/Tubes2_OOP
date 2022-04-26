package com.aetherwars.controller;

import  com.aetherwars.model.*;

import com.aetherwars.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardController {
    @FXML
    private Pane board0_0, board0_1, board0_2, board0_3, board0_4;

    @FXML
    private Pane board1_0, board1_1, board1_2, board1_3, board1_4;

    @FXML
    private Button buttonNextPhase;

    @FXML
    private Button buttonPrevPhase;

    @FXML
    private Label counterDeckA;

    @FXML
    private Label counterDeckB;

    @FXML
    private ImageView deckA;

    @FXML
    private FlowPane drawPane;

    @FXML
    private FlowPane flowPaneManaA, flowPaneManaB;

    @FXML
    private FlowPane hand;

    @FXML
    private Text labelCurrPhase;

    @FXML
    private Label turn;
    
    private Player p1;
    private Player p2;

    /**
     * this function serves to setup
     * the board controller class
     */
    public void setupBoardController(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void displayBoard() throws IOException {

        displayCard(p1.getBoard().getCard(0), board0_0);
        displayCard(p1.getBoard().getCard(1), board0_1);
        displayCard(p1.getBoard().getCard(2), board0_2);
        displayCard(p1.getBoard().getCard(3), board0_3);
        displayCard(p1.getBoard().getCard(4), board0_4);

        displayCard(p2.getBoard().getCard(0), board1_0);
        displayCard(p2.getBoard().getCard(1), board1_1);
        displayCard(p2.getBoard().getCard(2), board1_2);
        displayCard(p2.getBoard().getCard(3), board1_3);
        displayCard(p2.getBoard().getCard(4), board1_4);
    }

    public void displayCard(Character cur, Pane board) throws IOException {

        FXMLLoader boardCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/boardCard.fxml"));
        Pane boardPane = boardCardLoader.load();

        BoardCardController boardCardController = boardCardLoader.getController();

        boardCardController.setCard(cur);
        board.getChildren().add(boardPane);
    }

    public void displayHand() throws IOException {
//        for (int i = 0; i < 6; i++) {
//            FXMLLoader handCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handCharacterCard.fxml"));
//            Pane handPane = handCardLoader.load();
//
//            HandCharacterCardController handCardController = handCardLoader.getController();
//            handCardController.setCard(/*Card*/);
//            hand.add(handPane, i, 0);
//        }

        hand.setAlignment(Pos.CENTER);

        for (int i = 0; i < 5; i++) {
            FXMLLoader handCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
            Pane handPane = handCardLoader.load();

            HandSpellCardController handCardController = handCardLoader.getController();
//            handCardController.setCard(/*Card*/i);
            hand.getChildren().add(handPane);
        }

    }

    public void displayManaPane() throws IOException {
        flowPaneManaA.setPrefWidth(100 /* RUMUS = max * 25*/);

        for (int i = 0; i < 3 /* cur */; i++) {
            FXMLLoader manaBarLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/manaBar.fxml"));
            Rectangle manaBar = manaBarLoader.load();

            flowPaneManaA.getChildren().add(manaBar);
        }

        flowPaneManaB.setPrefWidth(75 /* RUMUS = max * 25*/);

        for (int i = 0; i < 3 /* cur */; i++) {
            FXMLLoader manaBarLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/manaBar.fxml"));
            Rectangle manaBar = manaBarLoader.load();

            flowPaneManaB.getChildren().add(manaBar);
        }
    }

    public void refreshBoard() throws IOException {
        displayBoard();
        displayHand();
        displayManaPane();
    }

    @FXML
    void deckAClicked(MouseEvent event) throws IOException {
        this.drawPane.setVisible(true);

        FXMLLoader drawPaneLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/drawPane.fxml"));
        Pane drawPane = drawPaneLoader.load();

        this.p1.getDeck().shuffleCards();
        DrawPaneController drawPaneController = drawPaneLoader.getController();
        drawPaneController.setDrawPane(p1, this.hand);

        this.drawPane.getChildren().add(drawPane);
        refreshBoard();
    }

    @FXML
    void handleCardDragOver(DragEvent event) {
//        System.out.println("OVER");
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    void handleCardDropped(DragEvent event) throws IOException {
//        System.out.println(event.getDragboard().getString());
        FXMLLoader boardCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/boardCard.fxml"));
        Pane boardPane = boardCardLoader.load();

        BoardCardController boardCardController = boardCardLoader.getController();

        Character card = new Character(1, "Obsidian", CharacterType.valueOf("OVERWORLD"), "An obsidian block used to create Nether portals.", "card/image/character/Obsidian.png", 1, 25, 8, 0, 5);

        boardCardController.setCard(card);
        ((Pane) event.getSource()).getChildren().add(boardPane);
    }

    @FXML
    void onClickNextPhase(ActionEvent event) {

    }

    @FXML
    void onClickPrevPhase(ActionEvent event) {

    }

}

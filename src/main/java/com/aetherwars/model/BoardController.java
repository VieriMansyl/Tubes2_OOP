package com.aetherwars.model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardController {
    @FXML
    private Pane board0_0;

    @FXML
    private Pane board0_1;

    @FXML
    private Pane board0_2;

    @FXML
    private Pane board0_3;

    @FXML
    private Pane board0_4;

    @FXML
    private Pane board1_0;

    @FXML
    private Pane board1_1;

    @FXML
    private Pane board1_2;

    @FXML
    private Pane board1_3;

    @FXML
    private Pane board1_4;

    @FXML
    private GridPane hand;
    
    private Board leftBoard;
    private Board rightBoard;

    /**
     * this function serves to setup
     * the board controller class
     */
    public void setupBoardController(Board leftBoard, Board rightBoard) {
        this.leftBoard = leftBoard;
        this.rightBoard = rightBoard;
    }

    public void displayBoard() throws IOException {
//        displayCard((Character)  , board0_0);
//        displayCard((Character) , board0_1);
        
        // board.getcard.index
        // problem: board not define
 
        // displayCard((Character) Card.availableCard.get(2),board0_2);
        // displayCard((Character) Card.availableCard.get(3),board0_3);
        // displayCard((Character) Card.availableCard.get(4),board0_4);

        // displayCard((Character) Card.availableCard.get(6),board1_0);
        // displayCard((Character) Card.availableCard.get(7),board1_1);
        // displayCard((Character) Card.availableCard.get(8),board1_2);
        // displayCard((Character) Card.availableCard.get(9),board1_3);
        // displayCard((Character) Card.availableCard.get(10),board1_4);
        displayCard(leftBoard.getCard(0), board0_0);
        displayCard(leftBoard.getCard(1), board0_1);
        displayCard(leftBoard.getCard(2), board0_2);
        displayCard(leftBoard.getCard(3), board0_3);
        displayCard(leftBoard.getCard(4), board0_4);

        displayCard(rightBoard.getCard(0), board1_0);
        displayCard(rightBoard.getCard(1), board1_1);
        displayCard(rightBoard.getCard(2), board1_2);
        displayCard(rightBoard.getCard(3), board1_3);
        displayCard(rightBoard.getCard(4), board1_4);
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
        for (int i = 0; i < 6; i++) {
            FXMLLoader handCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
            Pane handPane = handCardLoader.load();

            HandSpellCardController handCardController = handCardLoader.getController();
            handCardController.setCard(/*Card*/);
            hand.add(handPane, i, 0);
        }
    }

    public void refreshBoard() throws IOException {

        displayBoard();
        displayHand();
    }
}

package com.aetherwars.model;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardController {
    @FXML
    private Pane board0_0, board0_1, board0_2, board0_3, board0_4;

    @FXML
    private Pane board1_0, board1_1, board1_2, board1_3, board1_4;

    @FXML
    private ImageView deckA;

    @FXML
    private GridPane hand;

    @FXML
    private Pane drawPane;

    public void displayBoard() throws IOException {
        displayCard(board0_0);
        displayCard(board0_1);
        displayCard(board0_2);
        displayCard(board0_3);
        displayCard(board0_4);
        displayCard(board1_0);
        displayCard(board1_1);
        displayCard(board1_2);
        displayCard(board1_3);
        displayCard(board1_4);
    }

    public void displayCard(/*Card,*/Pane board) throws IOException {
        FXMLLoader boardCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/boardCard.fxml"));
        Pane boardPane = boardCardLoader.load();

        BoardCardController boardCardController = boardCardLoader.getController();
        boardCardController.setCard(/*Card*/);
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

    @FXML
    void deckAClicked(MouseEvent event) throws IOException {
        this.drawPane.setVisible(true);

        FXMLLoader drawPaneLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/drawPane.fxml"));
        Pane drawPane = drawPaneLoader.load();

        DrawPaneController drawPaneController = drawPaneLoader.getController();
        drawPaneController.setDrawPane();

        this.drawPane.getChildren().add(drawPane);
    }
}

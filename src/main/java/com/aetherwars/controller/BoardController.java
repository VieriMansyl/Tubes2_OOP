package com.aetherwars.controller;

import  com.aetherwars.model.*;

import com.aetherwars.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    private Button buttonNextPhase, buttonEndPhase;

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

    @FXML
    private ProgressBar healthBarA, healthBarB;

    @FXML
    private Text cardDesc;

    @FXML
    private Pane infoPane;
    
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
        hand.getChildren().clear();
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
            Spell card = new LevelSpell(1, "tes", "tes", "card/image/spell/swap/Potion of Turtle Master.png", 1);
            handCardController.setCard(card, hand.getChildren().size());
            hand.getChildren().add(handPane);
        }

    }

    public void displayManaPane() throws IOException {
        flowPaneManaA.getChildren().clear();
        flowPaneManaB.getChildren().clear();

        flowPaneManaA.setPrefWidth(100 /* RUMUS = max * 25*/ + 2 /*border*/);

        for (int i = 0; i < 3 /* cur */; i++) {
            FXMLLoader manaBarLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/manaBar.fxml"));
            Rectangle manaBar = manaBarLoader.load();

            flowPaneManaA.getChildren().add(manaBar);
        }

        flowPaneManaB.setPrefWidth(75 /* RUMUS = max * 25*/+ 2 /*border*/);

        for (int i = 0; i < 1 /* cur */; i++) {
            FXMLLoader manaBarLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/manaBar.fxml"));
            Rectangle manaBar = manaBarLoader.load();

            flowPaneManaB.getChildren().add(manaBar);
        }
    }

    public void refreshBoard() throws IOException {
        displayBoard();
        displayHand();
        displayManaPane();
        displayHealthBar();

        counterDeckA.setText("40");
        counterDeckB.setText("60");
        turn.setText("0");

        // for testing purpose
        cardDesc.setText("asdasdasdasdasdasdasddasasd"); //bisa set warp text

        FXMLLoader infoPaneLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/infoPane.fxml"));
        Pane infoPane = infoPaneLoader.load();

        InfoPaneController infoPaneController = infoPaneLoader.getController();
        infoPaneController.setUpInfoPane();
        this.infoPane.getChildren().add(infoPane);
    }

    public void displayHealthBar() {
        healthBarA.setProgress(0.7);
        healthBarB.setProgress(0.5);
    }

    @FXML
    void deckAClicked(MouseEvent event) throws IOException {
        this.drawPane.getChildren().clear();
        this.drawPane.setVisible(true);

        this.p1.getDeck().shuffleCards();
        List<Card> listOfCard= this.p1.getDeck().getTop3();

        for (Card card : listOfCard) {
            if (listOfCard.get(0) instanceof Spell) {
                FXMLLoader drawCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/drawSpellCard.fxml"));
                Pane drawCardPane = drawCardLoader.load();

                DrawSpellCardController drawCardController = drawCardLoader.getController();
                drawCardController.setCard((Spell) card, hand, this.drawPane);
                this.drawPane.getChildren().add(drawCardPane);
            } else /* Character */ {
                FXMLLoader drawCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/drawCharacterCard.fxml"));
                Pane drawCardPane = drawCardLoader.load();

                DrawCharacterCardController drawCardController = drawCardLoader.getController();
                drawCardController.setCard((Character) card, hand, this.drawPane);
                this.drawPane.getChildren().add(drawCardPane);
            }
        }
    }

    @FXML
    void handleCardDragOver(DragEvent event) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    void handleCardDropped(DragEvent event) {
        char player = ((Pane) event.getSource()).getId().charAt(5);
        int idx = Integer.parseInt(String.valueOf(((Pane) event.getSource()).getId().charAt(7)));
        System.out.println(player);
        System.out.println(idx);

        System.out.println(event.getDragboard().getString());
    }

    @FXML
    void onClickNextPhase(ActionEvent event) {
        labelCurrPhase.setText("Draw Phase");
    }

    @FXML
    void onClickEndPhase(ActionEvent event) {
        labelCurrPhase.setText("End Phase");
    }

}

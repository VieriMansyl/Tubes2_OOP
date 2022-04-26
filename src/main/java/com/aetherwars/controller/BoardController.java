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
import javafx.scene.image.Image;
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
import java.util.Objects;

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
    private ImageView deckA, deckB;

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

    private Phase currPhase;
    private Player currPlayer;

    public static void centerImage(ImageView imgVar) {
        Image img = imgVar.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imgVar.getFitWidth() / img.getWidth();
            double ratioY = imgVar.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imgVar.setX((imgVar.getFitWidth() - w) / 2);
            imgVar.setY((imgVar.getFitHeight() - h) / 2);

        }
    }

    public void switchTurn(){
        if (this.currPlayer == this.p1) {
            this.currPlayer = this.p2;
        }
        else {
            this.currPlayer = this.p1;
        }
        this.currPhase = Phase.DRAW;
    }


    /**
     * this function serves to setup the board controller class
     */
    public void setupBoardController(Player p1, Player p2) {
        hand.setAlignment(Pos.CENTER);

        this.p1 = p1;
        this.p2 = p2;
        this.currPhase = Phase.DRAW;
        this.currPlayer = this.p1;

        // To Do: Handle hand card
    }

    public void displayBoard() throws IOException {
        // Player1
        displayCard(p1.getBoard().getCard(0), board0_0);
        displayCard(p1.getBoard().getCard(1), board0_1);
        displayCard(p1.getBoard().getCard(2), board0_2);
        displayCard(p1.getBoard().getCard(3), board0_3);
        displayCard(p1.getBoard().getCard(4), board0_4);

        // Player2
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
        List<Card> playerHand;

        if (this.currPlayer == this.p1) {
            playerHand = this.p1.getHand().getCards();
        } else {
            playerHand = this.p2.getHand().getCards();
        }

        for (Card card : playerHand) {
            if (card instanceof Spell) {
                FXMLLoader handCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handSpellCard.fxml"));
                Pane handPane = handCardLoader.load();

                HandSpellCardController handCardController = handCardLoader.getController();
                handCardController.setCard((Spell) card, hand.getChildren().size());
                hand.getChildren().add(handPane);
            } else {
                FXMLLoader handCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/handCharacterCard.fxml"));
                Pane handPane = handCardLoader.load();

                HandCharacterCardController handCardController = handCardLoader.getController();
                handCardController.setCard((Character) card, hand.getChildren().size());
                hand.getChildren().add(handPane);
            }
        }
    }

    public void displayManaPane() throws IOException {
        flowPaneManaA.getChildren().clear();
        flowPaneManaB.getChildren().clear();

        // Player1
        flowPaneManaA.setPrefWidth(25 * p1.getMaxMana() + 2 /*border*/);

        for (int i = 0; i < p1.getMana(); i++) {
            FXMLLoader manaBarLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/manaBar.fxml"));
            Rectangle manaBar = manaBarLoader.load();

            flowPaneManaA.getChildren().add(manaBar);
        }

        // Player2
        flowPaneManaB.setPrefWidth(25 * p2.getMaxMana() + 2 /*border*/);

        for (int i = 0; i < p2.getMana(); i++) {
            FXMLLoader manaBarLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/manaBar.fxml"));
            Rectangle manaBar = manaBarLoader.load();

            flowPaneManaB.getChildren().add(manaBar);
        }
    }

    public void displayHealthBar() {
        healthBarA.setProgress(p1.getHealth() / 80);
        healthBarB.setProgress(p2.getHealth() / 80);
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
        infoPaneController.setUpInfoPane(Card.availableCard.get(22));
        this.infoPane.getChildren().add(infoPane);
    }

    @FXML
    void deckClicked(MouseEvent event) throws IOException {
        List<Card> listOfCard;
        System.out.println("ini fase gan " + currPhase);
        if (Objects.equals(((ImageView) event.getSource()).getId(), "deckA") && this.currPlayer == this.p1 && this.currPhase == Phase.DRAW) {
            this.p1.getDeck().shuffleCards();
            listOfCard = this.p1.getDeck().getTop3();
        } else if (Objects.equals(((ImageView) event.getSource()).getId(), "deckB") && this.currPlayer == this.p2 && this.currPhase == Phase.DRAW) {
            this.p2.getDeck().shuffleCards();
            listOfCard = this.p2.getDeck().getTop3();
        } else {
            return;
        }

        this.drawPane.getChildren().clear();
        this.drawPane.setVisible(true);

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
        /* SUMMON ATAU USE SPELL */

        // CHECK PHASE
        char player;
        if (this.currPhase == Phase.PLAN) {
            player = ((Pane) event.getSource()).getId().charAt(5);
        } else {
            return;
        }

        if (this.currPlayer == this.p1 && player == '0') { }
        else if (this.currPlayer == this.p2 && player == '1') {}
        else {
            return;
        }

        int boardIdx = Integer.parseInt(String.valueOf(((Pane) event.getSource()).getId().charAt(7)));
        int handIdx = Integer.parseInt(event.getDragboard().getString());
        Card card = this.currPlayer.getHand().getCard(handIdx);
        if (card instanceof Character) {
            currPlayer.playCard((Character) card, boardIdx);
        } else if (card instanceof Spell) {
            currPlayer.playCard((Spell) card, boardIdx);
        }
    }

    @FXML
    void onClickNextPhase(ActionEvent event) {
        if (this.currPhase == Phase.DRAW) {
            this.currPhase = Phase.PLAN;
        } else if (this.currPhase == Phase.PLAN) {
            this.currPhase = Phase.ATTACK;
        } else if (this.currPhase == Phase.ATTACK) {
            this.currPhase = Phase.END;
        } else if (this.currPhase == Phase.END) {
            switchTurn();
        }
        labelCurrPhase.setText(this.currPhase.toString());
    }

    @FXML
    void onClickEndPhase(ActionEvent event) {
        switchTurn();
        labelCurrPhase.setText(this.currPhase.toString());
    }

}

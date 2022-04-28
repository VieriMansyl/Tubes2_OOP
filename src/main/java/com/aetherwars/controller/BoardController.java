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
import javafx.scene.control.SplitPane;
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
    private SplitPane splitPane;

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
    private Pane infoPane;

    @FXML
    private Label infoTurn;

    @FXML
    private Button giveExpButton;

    @FXML
    private Button atkButton;

    @FXML
    private ImageView graveYard;
    
    private Player p1;
    private Player p2;

    private final double absolutePosition = .78;
    private Phase currPhase;
    private Player currPlayer;
    private int currTurn;

    private static BoardController instance;

    public static void setInstance(FXMLLoader loader) {
        instance = loader.getController();
    }

    public static BoardController getInstance() {
        return instance;
    }

    public static void centerImage(ImageView imgVar) {
        Image img = imgVar.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imgVar.getFitWidth() / img.getWidth();
            double ratioY = imgVar.getFitHeight() / img.getHeight();

            double reducCoeff = Math.min(ratioX, ratioY);

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imgVar.setX((imgVar.getFitWidth() - w) / 2);
            imgVar.setY((imgVar.getFitHeight() - h) / 2);
        }
    }

    public void switchTurn() {
        if (this.currPlayer == this.p1) {
            this.currPlayer = this.p2;
            this.p2.newTurn();
        }
        else {
            this.currPlayer = this.p1;
            this.currTurn++;
            this.p1.newTurn();
        }
        this.currPhase = Phase.DRAW;
        this.infoTurn.setText( currPlayer.getName() + "'s turn");
        this.buttonEndPhase.setVisible(false);
        this.buttonNextPhase.setVisible(false);
        draw();
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
        this.currTurn = 1;
        this.infoTurn.setText( currPlayer.getName() + "'s turn");
        this.atkButton.setVisible(false);
        this.giveExpButton.setVisible(false);

        this.buttonEndPhase.setVisible(false);
        this.buttonNextPhase.setVisible(false);
        draw();
        // To Do: Handle hand falsed
        splitPane.getDividers().get(0).positionProperty().addListener((observable,oldValue,newValue) -> {
            splitPane.setDividerPosition(0, absolutePosition);
        });
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
        if (cur != null) {
            board.getChildren().removeAll();
            FXMLLoader boardCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/boardCard.fxml"));
            Pane boardPane = boardCardLoader.load();

            BoardCardController boardCardController = boardCardLoader.getController();
            boardCardController.setCard(cur);
            board.getChildren().add(boardPane);
        }
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

    public void displayInfoPane(/*int i*/Card card){
        this.infoPane.getChildren().clear();
        try {
            FXMLLoader infoPaneLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/infoPane.fxml"));
            Pane infoPane = infoPaneLoader.load();

            InfoPaneController infoPaneController = infoPaneLoader.getController();
            infoPaneController.setUpInfoPane(card);

            this.infoPane.getChildren().add(infoPane);
        } catch (Exception e) {

        }
    }

    public void refreshBoard() {
        try {
            displayBoard();
            displayHand();
            displayManaPane();
            displayHealthBar();
//            displayInfoPane();

            counterDeckA.setText(((Integer) p1.getDeck().getCards().size()).toString());
            counterDeckB.setText(((Integer) p2.getDeck().getCards().size()).toString());

            turn.setText(((Integer) currTurn).toString());
            labelCurrPhase.setText(String.valueOf(this.currPhase));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deckClicked(MouseEvent event) throws IOException, HandOverException {
    }

    void draw() {

        List<Card> listOfCard;
        this.currPlayer.getDeck().shuffleCards();
        listOfCard = this.currPlayer.getDeck().getTop3();

        this.drawPane.getChildren().clear();
        this.drawPane.setVisible(true);

        int i = 0;
        try {
            for (Card card : listOfCard) {
                if (card instanceof Spell) {
                    FXMLLoader drawCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/drawSpellCard.fxml"));
                    Pane drawCardPane = drawCardLoader.load();

                    DrawSpellCardController drawCardController = drawCardLoader.getController();
                    drawCardController.setCard(currPlayer, (Spell) card, i, hand, this.drawPane);
                    this.drawPane.getChildren().add(drawCardPane);
                } else /* Character */ {
                    FXMLLoader drawCardLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/drawCharacterCard.fxml"));
                    Pane drawCardPane = drawCardLoader.load();

                    DrawCharacterCardController drawCardController = drawCardLoader.getController();
                    drawCardController.setCard(currPlayer, (Character) card, i, hand, this.drawPane);
                    this.drawPane.getChildren().add(drawCardPane);
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleCardDragOver(DragEvent event) {

        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    void handleCardDropped(DragEvent event) throws IOException {
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


        System.out.println("hand " + handIdx);
        System.out.println("board " + boardIdx);
        Card card = this.currPlayer.getHand().getCard(handIdx);
        System.out.println("iki " + currPlayer.getName());
        if (card instanceof Character) {
            currPlayer.playCard((Character) card, boardIdx);
        } else if (card instanceof Spell) {
            currPlayer.playCard((Spell) card, boardIdx);
        }
        refreshBoard();
    }

    @FXML
    void dropGraveyard(DragEvent event) throws IOException{
        if (this.currPhase == Phase.DRAW && this.currPlayer.getHand().getCards().size() > 5){
            int handIdx = Integer.parseInt(event.getDragboard().getString());
            
            Card card = this.currPlayer.getHand().getCard(handIdx);
            System.out.println(card.getName() + "terbuang");
            
            currPlayer.getHand().removeCard(currPlayer.getHand().getCard(handIdx));
//            BoardController.destroyCard(handIdx);
            setPhaseToPlan();
            refreshBoard();
        } else {
            return;
        }
    }

    @FXML
    void onClickNextPhase(ActionEvent event) {
        counterDeckA.setText(((Integer) p1.getDeck().getCards().size()).toString());
        counterDeckB.setText(((Integer) p2.getDeck().getCards().size()).toString());

        if (this.currPhase == Phase.PLAN) {
            this.currPhase = Phase.ATTACK;
            this.atkButton.setVisible(true);
            this.giveExpButton.setVisible(false);
        } else if (this.currPhase == Phase.ATTACK) {
            this.currPhase = Phase.END;
            this.atkButton.setVisible(false);
        } else if (this.currPhase == Phase.END) {
            switchTurn();
        }
        refreshBoard();
    }

    @FXML
    void onClickEndPhase(ActionEvent event) {
        counterDeckA.setText(((Integer) p1.getDeck().getCards().size()).toString());
        counterDeckB.setText(((Integer) p2.getDeck().getCards().size()).toString());
        switchTurn();
        refreshBoard();
    }

    void setPhaseToPlan(){
        if (currPlayer.getHand().getCards().size() <= 5) {
            this.currPhase = Phase.PLAN;
            this.buttonEndPhase.setVisible(true);
            this.buttonNextPhase.setVisible(true);
        }
        refreshBoard();
    }
}

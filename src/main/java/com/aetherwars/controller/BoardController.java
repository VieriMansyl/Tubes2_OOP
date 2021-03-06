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
import javafx.scene.input.*;
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
    private Pane board0_0, board0_1, board0_2, board0_3, board0_4, board1_0, board1_1, board1_2, board1_3, board1_4, infoPane, endgamePhase;

    @FXML
    private Button buttonNextPhase, buttonEndPhase, giveExpButton;

    @FXML
    private Label counterDeckA, counterDeckB, turn, infoTurn, health1, health2;

    @FXML
    private ImageView deckA, deckB, drawScroll;

    @FXML
    private FlowPane drawPane, flowPaneManaA, flowPaneManaB, hand;

    @FXML
    private Text labelCurrPhase, warning;

    @FXML
    private ProgressBar healthBarA, healthBarB;

    private Player p1;
    private Player p2;

    private final double absolutePosition = .782;
    private Phase currPhase;
    private Player currPlayer;
    private String currBoard;
    private int currTurn;

    private static BoardController instance;

    public Phase getCurrPhase() {
        return this.currPhase;
    }

    public Button getGiveExpButton() {
        return this.giveExpButton;
    }

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
            if (this.currTurn > 1) this.p2.newTurn();
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
        this.giveExpButton.setVisible(false);
        this.warning.setVisible(false);

        this.buttonEndPhase.setVisible(false);
        this.buttonNextPhase.setVisible(false);
        draw();
        // To Do: Handle hand falsed
        splitPane.getDividers().get(0).positionProperty().addListener((observable,oldValue,newValue) -> {
            splitPane.setDividerPosition(0, absolutePosition);
        });
    }

    public void displayBoard() throws IOException {
        // Player 1
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
        board.getChildren().clear();
        if (cur != null) {
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
        double healthA = Math.min(p1.getHealth() / 80, 1);
        if (healthA < 0.2) healthBarA.setStyle("-fx-accent: red");
        else if (healthA < 0.5) healthBarA.setStyle("-fx-accent: yellow");
        else healthBarA.setStyle("-fx-accent: rgba(80, 200, 80, 1)");
        health1.setText(String.valueOf(p1.getHealth()));

        healthBarA.setProgress(healthA);

        double healthB = Math.min(p2.getHealth() / 80, 1);
        if (healthB < 0.2) healthBarB.setStyle("-fx-accent: red");
        else if (healthB < 0.5) healthBarB.setStyle("-fx-accent: yellow");
        else healthBarB.setStyle("-fx-accent: rgba(80, 200, 80, 1)");
        health2.setText(String.valueOf(p2.getHealth()));

        healthBarB.setProgress(healthB);
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
            endGame();
            displayBoard();
            displayHand();
            displayManaPane();
            displayHealthBar();

            giveExpButton.setVisible(false);
//            displayInfoPane();

            counterDeckA.setText(((Integer) p1.getDeck().getCards().size()).toString());
            counterDeckB.setText(((Integer) p2.getDeck().getCards().size()).toString());

            turn.setText(((Integer) currTurn).toString());
            labelCurrPhase.setText(String.valueOf(this.currPhase));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endGame() throws IOException {
        if (p1.isDead() || p2.isDead() ||
                (((p1.getDeck().getCards().isEmpty() && this.currPlayer == this.p1) ||
                        (p2.getDeck().getCards().isEmpty()) && this.currPlayer == this.p2)
                        && currPhase== Phase.DRAW)) {
            FXMLLoader endgamePaneLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/views/endGamePane.fxml"));
            Pane endgamePane = endgamePaneLoader.load();
            EndGamePaneController endGamePaneController = endgamePaneLoader.getController();
            endGamePaneController.chooseWinner(this.p1, this.p2);

            this.endgamePhase.getChildren().add(endgamePane);
            this.endgamePhase.setVisible(true);
            this.endgamePhase.setDisable(false);
        }
    }

    void draw() {

        List<Card> listOfCard;
        this.currPlayer.getDeck().shuffleCards();
        listOfCard = this.currPlayer.getDeck().getTop3();

        this.drawPane.getChildren().clear();
        this.drawScroll.setVisible(true);
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
        // CHECK PHASE
        if (this.currPhase == Phase.PLAN) {

            /* SUMMON OR USE SPELL */
            char player;
            player = ((Pane) event.getSource()).getId().charAt(5);

            int boardIdx = Integer.parseInt(String.valueOf(((Pane) event.getSource()).getId().charAt(7)));
            int handIdx = Integer.parseInt(event.getDragboard().getString());
            Card card = this.currPlayer.getHand().getCard(handIdx);

            if ((this.currPlayer == this.p1 && player == '0') || (this.currPlayer == this.p2 && player == '1')) {
                if (card instanceof Character) {
                    currPlayer.playCard((Character) card, boardIdx);
                } else if (card instanceof Spell) {
                    currPlayer.playCard((Spell) card, boardIdx);
                }
            } else {
                // kalo morph ke musuh
                if (this.currPlayer.getHand().getCard(handIdx) instanceof Morph) {
                    Player foe;
                    if (this.currPlayer == this.p1) {
                        foe = this.p2;
                    } else {
                        foe = this.p1;
                    }
                    currPlayer.playCard(foe, (Morph) card, boardIdx);
                }
            }
            refreshBoard();

        } else if (this.currPhase == Phase.ATTACK) {

            // ATTACK


            String attacker = event.getDragboard().getString();

            char player = attacker.charAt(0);
            char attacked = ((Pane) event.getSource()).getId().charAt(5);

            if (this.currPlayer == this.p1 && player == '0' && attacked == '1') { }
            else if (this.currPlayer == this.p2 && player == '1' && attacked == '0') {}
            else {
                return;
            }

            Character boardAttacker, boardAttacked;
            Player foe;
            if (this.currPlayer == this.p1) {
                boardAttacker = this.p1.getBoard().getCard(attacker.charAt(attacker.length() - 1) - '0');
                boardAttacked = this.p2.getBoard().getCard(((Pane) event.getSource()).getId().charAt(7) - '0');
                foe = this.p2;
            } else {
                boardAttacker = this.p2.getBoard().getCard(((Pane) event.getSource()).getId().charAt(7) - '0');
                boardAttacked = this.p1.getBoard().getCard(attacker.charAt(attacker.length() - 1) - '0');
                foe = this.p1;
            }

            if (boardAttacker.isAttackable()) {
                if (foe.getBoard().isEmpty()) {
                    boardAttacker.attack(foe);
                    boardAttacker.hasInitiatedAttack();
                } else if (boardAttacked != null){
                    boardAttacker.attack(boardAttacked);
                    boardAttacked.attack(boardAttacker);
                    boardAttacker.hasInitiatedAttack();
                }
            }

        } else {
            // DO NOTHING
            return;
        }
        // endGame();
        refreshBoard();

    }

    @FXML
    void dropGraveyard(DragEvent event) throws IOException {
        if (this.currPhase == Phase.DRAW && this.currPlayer.getHand().getCards().size() > 5){
            this.drawScroll.setVisible(false);
            int handIdx = Integer.parseInt(event.getDragboard().getString());
            
            Card card = this.currPlayer.getHand().getCard(handIdx);

            
            currPlayer.getHand().removeCard(currPlayer.getHand().getCard(handIdx));
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
            this.giveExpButton.setVisible(false);
        } else if (this.currPhase == Phase.ATTACK) {
            this.currPhase = Phase.END;
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
            this.warning.setVisible(false);
            this.drawScroll.setVisible(false);
            this.currPhase = Phase.PLAN;
            this.buttonEndPhase.setVisible(true);
            this.buttonNextPhase.setVisible(true);
        }else{
            this.warning.setText("Warning: Hand Overflow");
            this.warning.setVisible(true);
        }
        refreshBoard();
    }

    @FXML
    void handleCardDragDetection(MouseEvent event) {
        if (this.currPhase == Phase.ATTACK) {
            Dragboard db = ((Pane) event.getSource()).startDragAndDrop(TransferMode.ANY);

            ClipboardContent cb = new ClipboardContent();
            String string = ((Pane) event.getSource()).getId();
            cb.putString(string.substring(5, 8));

            db.setContent(cb);

            event.consume();
        }
    }

    @FXML
    void onHover(MouseEvent event) {
        this.currBoard = ((Pane) event.getSource()).getId();

        char player = this.currBoard.charAt(5);
        int idx = this.currBoard.charAt(7) - '0';
        boolean chr =  this.currPlayer.getBoard().getCards().get(idx) != null;

        if (this.currPlayer == this.p1 && player == '0' && this.currPhase == Phase.PLAN && chr) {
            this.giveExpButton.setVisible(true);
        }
        else if (this.currPlayer == this.p2 && player == '1' && this.currPhase == Phase.PLAN && chr) {
            this.giveExpButton.setVisible(true);
        }
        else {
            this.giveExpButton.setVisible(false);
        }
    }

    @FXML
    void onExpClick(MouseEvent event) {
        
        int idx = this.currBoard.charAt(7) - '0';

        try {
            this.currPlayer.giveExp(this.currPlayer.getBoard().getCards().get(idx), 1);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        refreshBoard();
    }

}

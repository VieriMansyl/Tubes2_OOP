package com.aetherwars.controller;

import com.aetherwars.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndGamePaneController {

    @FXML
    private Label winner;

    public void chooseWinner(Player p1 , Player p2) {
        if (p1.isDead() || p1.getDeck().getCards().isEmpty() ) {
            this.winner.setText(p2.getName());
        }else if (p2.isDead() || p2.getDeck().getCards().isEmpty()) {
            this.winner.setText(p1.getName());
        }
    }
}
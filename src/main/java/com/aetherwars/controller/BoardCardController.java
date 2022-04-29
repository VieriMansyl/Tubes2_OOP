package com.aetherwars.controller;

import com.aetherwars.model.Character;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class BoardCardController {
    @FXML
    private Label boardCardAtk;

    @FXML
    private Label boardCardExp;

    @FXML
    private Label boardCardHp;

    @FXML
    private ImageView boardCardImage;

    @FXML
    private Label boardCardLvl;

    @FXML
    private Label boardCardMaxExp;

    private Character card;

    public void setCard(Character cur) {
        if (cur != null){
            this.card = cur;
            boardCardAtk.setText(String.valueOf(cur.getCurrAttack()));
            boardCardExp.setText(String.valueOf(cur.getExp()));
            boardCardHp.setText(String.valueOf(cur.getCurrHealth()));
            boardCardLvl.setText(String.valueOf(cur.getLevel()));
            boardCardMaxExp.setText(String.valueOf(cur.getCapExp()));
            boardCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
            BoardController.centerImage(boardCardImage);
        }
    }


    @FXML
    void onHover(MouseEvent event) {
        BoardController.getInstance().displayInfoPane(this.card);
    }
}

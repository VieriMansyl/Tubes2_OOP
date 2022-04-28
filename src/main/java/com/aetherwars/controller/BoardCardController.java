package com.aetherwars.controller;

import com.aetherwars.model.Character;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public void setCard(Character cur) {
        if (cur != null){
            boardCardAtk.setText(cur.getName());
            boardCardExp.setText(String.valueOf(cur.getExp()));
            boardCardHp.setText(String.valueOf(cur.getCurrHealth()));
            boardCardLvl.setText(String.valueOf(cur.getLevel()));
            boardCardMaxExp.setText(String.valueOf(cur.getCapExp()));
            boardCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
            BoardController.centerImage(boardCardImage);
        }
    }
}

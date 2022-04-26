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

    public void centerImage() {
        Image img = boardCardImage.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = boardCardImage.getFitWidth() / img.getWidth();
            double ratioY = boardCardImage.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            boardCardImage.setX((boardCardImage.getFitWidth() - w) / 2);
            boardCardImage.setY((boardCardImage.getFitHeight() - h) / 2);

        }
    }

    public void setCard(Character cur) {
        if (cur != null){
            boardCardAtk.setText(cur.getName());
            boardCardExp.setText(String.valueOf(cur.getExp()));
            boardCardHp.setText(String.valueOf(cur.getCurrHealth()));
            boardCardLvl.setText(String.valueOf(cur.getLevel()));
            boardCardMaxExp.setText("5");
            boardCardImage.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
            centerImage();
        }
    }
}

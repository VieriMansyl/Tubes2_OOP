package com.aetherwars.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InfoPaneController {

    @FXML
    private Label attack;

    @FXML
    private Label health;

    @FXML
    private ImageView image;

    public void setUpInfoPane() {
        attack.setText("10");
        health.setText("20");
//        image.setImage(new Image("/com/aetherwars/" + cur.getImgSrc()));
    }
}

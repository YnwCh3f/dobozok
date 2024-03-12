package com.example.dobozok;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private Pane pnPane;
    @FXML private Label lbOpen;
    @FXML private Label lbBox;

    private Image open =new Image(getClass().getResourceAsStream("boxopen.png"));
    private Image box = new Image(getClass().getResourceAsStream("box.png"));
    private Image empty = new Image(getClass().getResourceAsStream("null.png"));

    private Label[][] ivArray = new Label[10][15];

    public void initialize(){
        for (int i = 0; i < 10; i++){ for (int j = 0; j < 15; j++){
                ivArray[i][j] = new Label();
                ivArray[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("null.png"))));
                int ii = i; int jj = j;
                ivArray[i][j].setOnMouseEntered(e -> ivArray[ii][jj].setStyle("-fx-background-color: lightgreen;"));
                ivArray[i][j].setOnMouseExited(e -> ivArray[ii][jj].setStyle("-fx-background-color: white;"));
                ivArray[i][j].setTranslateX(j*64+10);
                ivArray[i][j].setTranslateY(i*64+10);
                pnPane.getChildren().add(ivArray[i][j]);
            }
        }
    }
}
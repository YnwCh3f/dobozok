package com.example.dobozok;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private Pane pnPane;
    @FXML private Label lbOpen;
    @FXML private Label lbBox;
    @FXML private ImageView ivArrow;


    private Label[][] ivArray = new Label[10][15];
    private char[][] chArray = new char[10][15];

    private AnimationTimer atTimer = null;

    private long step = 0;

    private int fok = 0;

    private int k = 0;

    private int x;
    private int y;

    private int feldolgozott = 0;
    private int letett = 0;
    private boolean go = false;

    public void initialize(){
        for (int i = 0; i < 10; i++){ for (int j = 0; j < 15; j++){
                ivArray[i][j] = new Label();
                ivArray[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("null.png"))));
                int ii = i; int jj = j;
                ivArray[i][j].setOnMouseEntered(e -> ivArray[ii][jj].setStyle("-fx-background-color: lightgreen;"));
                ivArray[i][j].setOnMouseExited(e -> ivArray[ii][jj].setStyle("-fx-background-color: white;"));
                ivArray[i][j].setTranslateX(j*64+10);
                ivArray[i][j].setTranslateY(i*64+10);
                ivArray[i][j].setOnMouseClicked(e -> katt(ii, jj));
                chArray[i][j] = 'X';
                pnPane.getChildren().add(ivArray[i][j]);
            }
        }
        atTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now > step){
                    ivArrow.setRotate(fok);
                    step = now + 250000000;
                    fok += 90;
                    if (go) k++;
                    if (k % 4 == 0 && k != 0){
                        ivArray[y][x].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("null.png"))));
                        chArray[y][x] = 'X';
                        k = 0;
                        feldolgozott++;
                        lbOpen.setText(feldolgozott + " db");
                        go = false;
                    }
                }
            }
        };
        atTimer.start();
        feltolt();
    }

    @FXML private void onRandomClick(){
        for (int i = 0; i < 10; i++){ for (int j = 0; j < 15; j++) {
                ivArray[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("null.png"))));
                chArray[i][j] = 'X';
            }
        }
        feltolt();
    }

    private void feltolt(){
        for (int j = 0; j < 15; j++){
            int i = 9;
            int r = (int)(Math.random()*10);
            while (r != 0){
                ivArray[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("box.png"))));
                chArray[i][j] = '0';
                i--;
                r--;
            }
        }
    }

    private void katt(int i, int j){
        if (i > 0) {
            if (chArray[i][j] == '0' && chArray[i-1][j] == 'X') {
                ivArray[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("boxopen.png"))));
                chArray[i][j] = '1';
                x = j;
                y = i;
                k = 0;
                go = true;
            }
        }else{
            if (chArray[i][j] == '0'){
                ivArray[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("boxopen.png"))));
                chArray[i][j] = '1';
                x = j;
                y = i;
                k = 0;
                go = true;
            }
        }
        if (i < 9){
            if (chArray[i][j] == 'X' && chArray[i+1][j] == '0') {
                ivArray[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("box.png"))));
                chArray[i][j] = '0';
                letett++;
                lbBox.setText(letett + " db");
            }
        }else{
            if (chArray[i][j] == 'X') {
                ivArray[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("box.png"))));
                chArray[i][j] = '0';
                letett++;
                lbBox.setText(letett + " db");
            }
        }
    }
}
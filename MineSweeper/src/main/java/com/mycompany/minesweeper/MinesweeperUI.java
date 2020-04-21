
package com.mycompany.minesweeper;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MinesweeperUI extends Application {
    private static Pane alusta = new Pane();
    public static void addTile(Tile tile){

        alusta.getChildren().add(tile);
    }
    public static Pane startGame(){
        alusta = new Pane();
        alusta.setPrefHeight(Minesweeper.getHeight());
        alusta.setPrefWidth(Minesweeper.getWidth());
        Minesweeper.setupGame();
        return alusta;
    }
    
    @Override
    public void start(Stage stage) {
        Pane alusta = startGame();
        Scene nakyma = new Scene(alusta);
        
        stage.setScene(nakyma);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

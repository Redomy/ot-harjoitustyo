
package com.mycompany.minesweeper;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MinesweeperUI extends Application {
    private static Pane alusta = new Pane();
    private static Text teksti = new Text("");
//    private static Scene scenePlay = new Scene(alusta);
//    private Scene scene = new Scene(alusta);
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
        
        alusta = startGame();
        Scene scenePlay = new Scene(alusta);
        
        BorderPane asettelu = new BorderPane();
        VBox menu = new VBox();
        menu.setSpacing(20);
        Button play = new Button("Play");
        Button quit = new Button("Quit");
        play.setOnAction(event -> {
                        Minesweeper.changeStatus(true);
                        Minesweeper.refreshScore();
                        alusta = startGame();
                        Scene scenePlay2 = new Scene(alusta);
                        stage.setScene(scenePlay2);
                        });
        quit.setOnAction(event -> System.exit(0));
        menu.getChildren().addAll(teksti, play, quit);
        asettelu.setTop(menu);
        asettelu.setPrefSize(Minesweeper.getWidth(), Minesweeper.getHeight());
        asettelu.setPadding(new Insets(20, 20, 20, 20));
        
        Scene sceneMenu = new Scene(asettelu);
        
        new AnimationTimer(){
            public void handle(long nykyhetki){
                if(Minesweeper.getStatus() == false){
                    teksti.setText("You got " + Minesweeper.getScore() + " points!");
                    stage.setScene(sceneMenu);
                }
            }
        }.start();
        
       

        stage.setScene(sceneMenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

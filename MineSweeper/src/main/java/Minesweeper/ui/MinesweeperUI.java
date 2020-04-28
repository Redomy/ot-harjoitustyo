
package Minesweeper.ui;

import Minesweeper.logic.Minesweeper;
import Minesweeper.logic.Tile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MinesweeperUI extends Application {
    private DatabaseUser db;
    private static Pane base = new Pane();
    private static Text text = new Text("");
    private Button save;
    private String player;
    private TextField nameField;
    private Button menuButton;
    
    public static void addTile(Tile tile){
        base.getChildren().add(tile);
    }
    public static Pane startGame(){
        base = new Pane();
        base.setPrefHeight(Minesweeper.getHeight());
        base.setPrefWidth(Minesweeper.getWidth());
        Minesweeper.setupGame();
        return base;
    }
    public BorderPane makeScorePane() throws ClassNotFoundException, SQLException{
        BorderPane root = new BorderPane();
        root.setPrefHeight(Minesweeper.getHeight());
        root.setPrefWidth(Minesweeper.getWidth());
        VBox scorelist = new VBox();
        Text text1 = new Text();
        Text text2 = new Text();
        Text text3 = new Text();
        Text text4 = new Text();
        Text text5 = new Text();
        ArrayList<Text> texts = new ArrayList<>();
        texts.add(text1);
        texts.add(text2);
        texts.add(text3);
        texts.add(text4);
        texts.add(text5);
        ArrayList<String> results = db.getHighScores();
        for(int i = 0; i < results.size(); i++){
            texts.get(i).setText(results.get(i));
        }
        menuButton = new Button("Back to menu");
        
        scorelist.getChildren().addAll(text1, text2, text3, text4, text5, menuButton);
        root.setCenter(scorelist);
        return root;
    }
    public BorderPane makeGameOverPane(){
        BorderPane root = new BorderPane();
        root.setPrefHeight(Minesweeper.getHeight());
        root.setPrefWidth(Minesweeper.getWidth());
        HBox hbox = new HBox();
        text.setText("You got " + Minesweeper.getScore() + " points!");
        Text text2 = new Text(" Name: ");
        nameField = new TextField();
        save = new Button("Save");
        player = nameField.getText();
        hbox.getChildren().addAll(text, text2, nameField, save);
        root.setCenter(hbox);
        return root;
    }
    
    @Override
    public void start(Stage stage) throws ClassNotFoundException{
        
        db = new DatabaseUser();
        db.makeDatabase();
        
        Button play = new Button("Play");
        Button scores = new Button("Highscores");
        Button quit = new Button("Quit");
        
        play.setOnAction(event -> {
                        Minesweeper.changeStatus(true);
                        Minesweeper.refreshScore();
                        base = startGame();
                        Scene scenePlay2 = new Scene(base);
                        stage.setScene(scenePlay2);
                        });

        quit.setOnAction(event -> System.exit(0));
        
        VBox menu = new VBox();
        menu.setSpacing(20);
        menu.getChildren().addAll(play, scores, quit);
        
        BorderPane layout = new BorderPane();
        layout.setTop(menu);
        layout.setPrefSize(Minesweeper.getWidth(), Minesweeper.getHeight());
        layout.setPadding(new Insets(20, 20, 20, 20));
        
        Scene sceneMenu = new Scene(layout);
        
        scores.setOnAction(event -> {
            try {
                BorderPane root = makeScorePane();
                Scene scoreScene = new Scene(root);
                stage.setScene(scoreScene);
                menuButton.setOnAction(e -> {
                        stage.setScene(sceneMenu);
                });
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MinesweeperUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MinesweeperUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            });

        new AnimationTimer(){
            public void handle(long currentTime){
                if(Minesweeper.getStatus() == false){
                    BorderPane root = makeGameOverPane();
                    save.setOnAction(event -> {
                        try {
                            player = nameField.getText();
                            db.WriteDatabase(player, Minesweeper.getScore());
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MinesweeperUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        stage.setScene(sceneMenu);
                    });
                    Scene gameOverScene = new Scene(root);
                    stage.setScene(gameOverScene);
                    Minesweeper.changeStatus(true);
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

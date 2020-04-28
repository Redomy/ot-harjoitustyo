
package Minesweeper.ui;

import com.mycompany.minesweeper.DatabaseUser;
import com.mycompany.minesweeper.Minesweeper;
import com.mycompany.minesweeper.Tile;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MinesweeperUI extends Application {
    private DatabaseUser db;
    private static Pane alusta = new Pane();
    private static Text teksti = new Text("");
//    private static Scene scenePlay = new Scene(alusta);
//    private Scene scene = new Scene(alusta);
//    private BorderPane scoreMenu = new BorderPane();
//    ArrayList<Text> texts = new ArrayList<>();
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
        scorelist.getChildren().addAll(text1, text2, text3, text4, text5);
        root.setCenter(scorelist);
        return root;
    }
    
    @Override
    public void start(Stage stage) throws ClassNotFoundException{
        
//        alusta = startGame();
//        Scene scenePlay = new Scene(alusta);
        db = new DatabaseUser();
        db.makeDatabase();
        
        
        BorderPane asettelu = new BorderPane();
        VBox menu = new VBox();
        menu.setSpacing(20);
        
        
        Button play = new Button("Play");
        Button scores = new Button("Highscores");
        Button quit = new Button("Quit");
        
        
        play.setOnAction(event -> {
                        Minesweeper.changeStatus(true);
                        Minesweeper.refreshScore();
                        alusta = startGame();
                        Scene scenePlay2 = new Scene(alusta);
                        stage.setScene(scenePlay2);
                        });
        scores.setOnAction(event -> {
            try {
                BorderPane root = makeScorePane();
                Scene scoreScene = new Scene(root);
                stage.setScene(scoreScene);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MinesweeperUI.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Virhe");
            } catch (SQLException ex) {
                Logger.getLogger(MinesweeperUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
        
        quit.setOnAction(event -> System.exit(0));
        menu.getChildren().addAll(teksti, play, scores, quit);
        asettelu.setTop(menu);
        asettelu.setPrefSize(Minesweeper.getWidth(), Minesweeper.getHeight());
        asettelu.setPadding(new Insets(20, 20, 20, 20));
        
        Scene sceneMenu = new Scene(asettelu);
        
        
        
        new AnimationTimer(){
            public void handle(long nykyhetki){
                if(Minesweeper.getStatus() == false){
                    teksti.setText("You got " + Minesweeper.getScore() + " points!");
                    stage.setScene(sceneMenu);
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

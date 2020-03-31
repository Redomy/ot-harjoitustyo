


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Minesweeper extends Application {
    private static final int tileSize = 40;
    private static final int width = 800;
    private static final int height = 600;
    private static final int xTiles = width / tileSize;
    private static final int yTiles = height / tileSize;
    private static Tile[][] grid = new Tile[xTiles][yTiles];
    
    public static int getTileSize(){
        return Minesweeper.tileSize;
    }
    public static int getWidth(){
        return Minesweeper.width;
    }
    public static int getHeight(){
        return Minesweeper.height;
    }
    
    @Override
    public void start(Stage stage) {
        
        Pane alusta = new Pane();
        alusta.setPrefHeight(height);
        alusta.setPrefWidth(width);
        
        for(int y = 0; y < yTiles; y++){
            for(int x = 0; x < xTiles; x++){
                boolean hasBomb = Math.random() < 0.2;
                Tile tile = new Tile(x, y, hasBomb);
                
                grid[x][y] = tile;
                alusta.getChildren().add(tile);
            }
        }
        
        Scene nakyma = new Scene(alusta);
        
        stage.setScene(nakyma);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

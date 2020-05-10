package Minesweeper.logic;

import Minesweeper.ui.MinesweeperUI;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Minesweeper{
    private static boolean gameStatus = true;
    private static int score = 0;
    private static final int tileSize = 40;
    private static final int width = 800;
    private static final int height = 600;
    private static final int xTiles = width / tileSize;
    private static final int yTiles = height / tileSize;
    private static Tile[][] grid = new Tile[xTiles][yTiles];
    
    public static int getTileSize() {
        return Minesweeper.tileSize;
    }
    public static int getWidth() {
        return Minesweeper.width;
    }
    public static int getHeight() {
        return Minesweeper.height;
    }
    public static int getScore() {
        return Minesweeper.score;
    }
    public static void increaseScore() {
        Minesweeper.score += 100;
    }
    public static void refreshScore() {
        Minesweeper.score = 0;
    }
    public static int getXtiles() {
        return Minesweeper.xTiles;
    }
    public static int getYtiles() {
        return Minesweeper.yTiles;
    }
    public static boolean getStatus() {
        return Minesweeper.gameStatus;
    }
    public static void changeStatus(boolean status) {
        Minesweeper.gameStatus = status;
    }
    
    public static void addTestTile(Tile tile) {
        grid[tile.getX()][tile.getY()] = tile;
    }
    public static Tile getTile(int x, int y) {
        return grid[x][y];
    }
    /*
    *Metodi, joka haravoi tietyn ruudun ympäristön
    *
    *@param  tile   ruutu,jonka ympäristö tarkistetaan
    *@return        lista, ruutua ympäröivistä ruuduista
    */
    public static List<Tile> getNeighbours(Tile tile) {
        List<Tile> neighbours = new ArrayList<>();
        
        int[] coordinates = new int[] {
            -1, -1,  0, -1,   1, -1,
            -1, 0,            1, 0,
            -1, 1,   0, 1,    1, 1
        };
        for (int i = 0; i < coordinates.length; i++) {
            int dx = coordinates[i];
            i++;
            int dy = coordinates[i];
            
            int checkX = tile.getX() + dx;
            int checkY = tile.getY() + dy;
            
            if (checkX >= 0 && checkX < xTiles && checkY >= 0 && checkY < yTiles) {
                neighbours.add(grid[checkX][checkY]);
            }
        }
        return neighbours;
    }
    /*
    *Metodi, joka alustaa pelin ja ruudukon
    *
    *@return null
    */
    public static void setupGame() {
        
        for (int y = 0; y < yTiles; y++) {
            for (int x = 0; x < xTiles; x++) {
                boolean hasBomb = Math.random() < 0.2;
                Tile tile = new Tile(x, y, hasBomb);     
                
                grid[x][y] = tile;
            }
        }
        
        for (int y = 0; y < yTiles; y++) {
            for (int x = 0; x < xTiles; x++) {
                Tile tile = grid[x][y];
                if (tile.bombStatus()) {
                    MinesweeperUI.addTile(tile);
                    continue;
                }
                long bombsAround = getNeighbours(tile).stream().filter(t -> t.bombStatus()).count();
                if (bombsAround > 0) {
                    tile.modifyText(String.valueOf(bombsAround));
                }
                MinesweeperUI.addTile(tile);
            }
        }
    }
}

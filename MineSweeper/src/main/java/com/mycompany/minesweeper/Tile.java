package com.mycompany.minesweeper;

import com.mycompany.minesweeper.Minesweeper;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    
    private int x;
    private int y;
    private boolean hasBomb;
    private Rectangle nelio = new Rectangle(Minesweeper.getTileSize(), Minesweeper.getTileSize());
    private Text text = new Text();
    private boolean revealed;
    
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public boolean bombStatus() {
        return this.hasBomb;
    }
    public boolean revealStatus() {
        return this.revealed;
    }
    public String getText() {
        return this.text.getText();
    }
    public void modifyText(String newText) {
        this.text.setText(newText);
    }
    public Tile(int x, int y, boolean hasBomb) {
        this.revealed = false;
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;
        nelio.setStroke(Color.CRIMSON);
        nelio.setFill(Color.LIGHTSEAGREEN);
        if (this.hasBomb == true) {
            text.setText("X");
        } else {
            text.setText("");
        }
        text.setFont(Font.font(16));
        getChildren().add(text);
        getChildren().add(nelio);
        
        setTranslateY(y * Minesweeper.getTileSize());
        setTranslateX(x * Minesweeper.getTileSize());
        setOnMouseClicked(event -> reveal());
    }
    public void reveal() {
        if (revealed) {
            return;
        }
        if (this.bombStatus()) {
            this.revealed = true;
            nelio.setFill(Color.RED);
            Minesweeper.changeStatus(false);
            return;
//            System.exit(0);
//            MinesweeperUI.startGame();
        }
        Minesweeper.increaseScore();
        revealed = true;
        nelio.setFill(null);
        if (this.text.getText().isEmpty()) {
            Minesweeper.getNeighbours(this).forEach(t -> t.reveal());
        }
    }
    
}

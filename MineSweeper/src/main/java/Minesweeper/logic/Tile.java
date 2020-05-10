package Minesweeper.logic;

import Minesweeper.logic.Minesweeper;
import Minesweeper.ui.MinesweeperUI;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile {
    
    private int x;
    private int y;
    private boolean hasBomb;
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
        if (this.hasBomb == true) {
            text.setText("X");
        } else {
            text.setText("");
        }
        text.setFont(Font.font(16));
    }
    public void reveal() {
        if (revealed) {
            return;
        }
        if (this.bombStatus()) {
            this.revealed = true;
            Minesweeper.changeStatus(false);
            return;
        }
        Minesweeper.increaseScore();
        revealed = true;
        MinesweeperUI.reveal(this.getX(), this.getY());
        if (this.text.getText().isEmpty()) {
            Minesweeper.getNeighbours(this).forEach(t -> t.reveal());
        }
    }
    
}

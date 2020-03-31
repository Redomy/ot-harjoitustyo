
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.GRAY;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tile extends StackPane{
    private int x;
    private int y;
    private boolean hasBomb;
    private Rectangle nelio = new Rectangle(Minesweeper.getTileSize(), Minesweeper.getTileSize());
    private Text text = new Text();
    private boolean revealed;
    public Tile(int x, int y, boolean hasBomb){
        this.revealed = false;
        this.x = x;
        this.y =y;
        this.hasBomb = hasBomb;
        nelio.setStroke(Color.CRIMSON);
        nelio.setFill(Color.LIGHTSEAGREEN);
        if(this.hasBomb == true){
            text.setText("X");
        }else{
            text.setText("");
        }
        text.setFont(Font.font(16));
        getChildren().add(text);
        getChildren().add(nelio);
        
        setTranslateY(y * Minesweeper.getTileSize());
        setTranslateX(x * Minesweeper.getTileSize());
        setOnMouseClicked(event -> Reveal());
    }
    public void Reveal(){
        if(revealed){
            return;
        }else{
            revealed = true;
            nelio.setFill(null);
        }
    }
    
}

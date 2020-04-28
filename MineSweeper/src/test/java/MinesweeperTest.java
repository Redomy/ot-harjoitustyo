
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Minesweeper.logic.Tile;
import Minesweeper.logic.Minesweeper;

public class MinesweeperTest {
    
    public MinesweeperTest() {
    }
    @Test
    public void startScore(){
        assertEquals(0, Minesweeper.getScore());
    }
    @Test
    public void height(){
        assertEquals(600, Minesweeper.getHeight());
    }
    @Test
    public void width(){
        assertEquals(800, Minesweeper.getWidth());
    }
    @Test
    public void tileSize(){
        assertEquals(40, Minesweeper.getTileSize());
    }
    @Test
    public void increaseScore(){
        Minesweeper.increaseScore();
        assertEquals(100, Minesweeper.getScore());
    }
    @Test
    public void refreshScore(){
        Minesweeper.increaseScore();
        Minesweeper.refreshScore();
        assertEquals(0, Minesweeper.getScore());
    }
    @Test
    public void changeStatusTrue(){
        Minesweeper.changeStatus(true);
        assertEquals(true, Minesweeper.getStatus());
    }
    @Test
    public void changeStatusFalse(){
        Minesweeper.changeStatus(false);
        assertEquals(false, Minesweeper.getStatus());
    }
    @Test
    public void neighbours(){
        Tile tile0 = new Tile(0, 0, false);
        Tile tile1= new Tile(1, 0, false);
        Tile tile2 = new Tile(1, 1, false);
        Tile tile3 = new Tile(0, 1, false);
        Minesweeper.addTestTile(tile0);
        Minesweeper.addTestTile(tile1);
        Minesweeper.addTestTile(tile2);
        Minesweeper.addTestTile(tile3);
        assertEquals(3, Minesweeper.getNeighbours(tile0).size());
    }
    @Test
    public void setupGame(){
        Minesweeper.setupGame();
        for (int y = 0; y < Minesweeper.getYtiles(); y++) {
            for (int x = 0; x < Minesweeper.getXtiles(); x++){
                if(Minesweeper.getTile(x, y) == null){
                    assertEquals(false, true);
                    return;
                }
            }
        }
        assertEquals(true, true);
    }
}

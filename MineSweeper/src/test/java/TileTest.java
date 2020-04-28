

import Minesweeper.logic.Tile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TileTest {
    
    public TileTest() {
    }
    @Test
    public void tileX(){
        Tile tile = new Tile(10, 10, true);
        assertEquals(10, tile.getX());
    }
    @Test
    public void tileY(){
        Tile tile = new Tile(10, 20, true);
        assertEquals(20, tile.getY());
    }
    @Test
    public void tileBomb(){
        Tile tile = new Tile(10, 10, true);
        assertEquals(true, tile.bombStatus());
    }
    @Test
    public void tileText(){
        Tile tile = new Tile(10, 10, true);
        assertEquals("X", tile.getText());
    }
    @Test
    public void tileOpenOrNot(){
        Tile tile = new Tile(10, 10, true);
        assertEquals(false, tile.revealStatus());
    }
    @Test
    public void tileModifyText(){
        Tile tile = new Tile(10, 10, true);
        tile.modifyText("7");
        assertEquals("7", tile.getText());
    }
//    @Test
//    public void tileRevealed(){
//        Tile tile = new Tile(10, 10, false);
//        tile.reveal();
//        assertEquals(true, tile.revealStatus());
//    }
    
    
}

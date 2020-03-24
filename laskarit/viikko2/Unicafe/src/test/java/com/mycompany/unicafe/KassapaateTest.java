package com.mycompany.unicafe;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Maksukortti kortti;
    Kassapaate kassa;
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    @Test
    public void alussaOikein(){
        assertEquals("100000", String.valueOf(kassa.kassassaRahaa()));
    }
    @Test
    public void kateisostoRiittavaRaha(){
        int vaihtoraha = kassa.syoEdullisesti(250);
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(10, vaihtoraha);
    }
    @Test
    public void kateisostoRiittavaRahaMaukas(){
        int vaihtoraha = kassa.syoMaukkaasti(450);
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(50, vaihtoraha);
    }
    @Test
    public void kateisostoLounasmaaraKasvaa(){
        kassa.syoEdullisesti(250);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    @Test
    public void kateisostoEiRiitaRaha(){
        int vaihtoraha = kassa.syoEdullisesti(100);
        assertEquals(100, vaihtoraha);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    @Test
    public void kateisostoEiRiitaRahaMaukas(){
        int vaihtoraha = kassa.syoMaukkaasti(100);
        assertEquals(100, vaihtoraha);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        
    }
    @Test
    public void korttiostoRiittavaRaha(){
        boolean vastaus = kassa.syoMaukkaasti(kortti);
        assertEquals(true, vastaus);
    }
    @Test
    public void korttiostoRiittavaRahaEdullinen(){
        boolean vastaus = kassa.syoEdullisesti(kortti);
        assertEquals(true, vastaus);
    }
    @Test
    public void korttiostoMyyntiNousee(){
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void korttiostoEiRiitaRaha(){
        kassa.syoMaukkaasti(kortti);
        boolean vastaus = kassa.syoMaukkaasti(kortti);
        assertEquals(false, vastaus);
    }
    @Test
    public void korttiostoEiRiitaRahaEdullinen(){
        kassa.syoMaukkaasti(kortti);
        boolean vastaus = kassa.syoEdullisesti(kortti);
        assertEquals(false, vastaus);
    }
    @Test
    public void korttiostoEiRiitaRahaMyyntiEiNouse(){
        kassa.syoMaukkaasti(kortti);
        boolean vastaus = kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void korttiostoEiRiitaRahaSaldoSama(){
        kassa.syoMaukkaasti(kortti);
        boolean vastaus = kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }
    @Test
    public void korttiostoKassaSama(){
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void kortilleLatausKortinSaldo(){
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(1000, kortti.saldo());
    }
    @Test
    public void kortilleLatausKortinSaldoNegatiivinenSumma(){
        kassa.lataaRahaaKortille(kortti, -1000);
        assertEquals(500, kortti.saldo());
    }
    @Test
    public void kortilleLatausKassanRaha(){
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }
    @Test
    public void kortilleLatausKassanRahaNegatiivinenSumma(){
        kassa.lataaRahaaKortille(kortti, -1000);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
}

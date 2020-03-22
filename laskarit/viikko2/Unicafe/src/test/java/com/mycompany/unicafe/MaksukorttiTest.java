package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void SaldoAlussaOikein() {
        assertEquals("10", String.valueOf(kortti.saldo()));
    }
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("saldo: 0.35", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeRahaaTarpeeksi(){
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
    }
    @Test
    public void saldoEiVaheneRahaaEiTarpeeksi(){
        kortti.otaRahaa(15);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void otaRahaaPalauttaaTrueRiittavastiRahaa(){
        assertEquals(true, kortti.otaRahaa(5));
    }
    @Test
    public void otaRahaaPalauttaaFalseEiRiittavastiRahaa(){
        assertEquals(false, kortti.otaRahaa(25));
    }
    
}

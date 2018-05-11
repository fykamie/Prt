/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eszti
 */
public class EndGameTest {
    private final Kockak kockak= new Kockak();
    @Before
    public void setUp() {
        kockak.setSajatKockaimLista(Arrays.asList(1, 2, 3));
        kockak.setEllenfelKockaiLista(Arrays.asList(2, 1, 3));
        kockak.setRandomKockakLista(Arrays.asList(1, 2, 3));
        kockak.setRandomKocka(5);
        kockak.setCsereleshezKocka(6);
    }
 
    @Test
    public void testIsEndGame() {
        assertTrue(EndGame.isEndGame(kockak));

        kockak.setEllenfelKockaiLista(Arrays.asList(1, 2, 3));
        kockak.setSajatKockaimLista(Arrays.asList(2, 1, 3));

        assertTrue(EndGame.isEndGame(kockak));

        kockak.setSajatKockaimLista(Arrays.asList(2, 1, 3));
        kockak.setEllenfelKockaiLista(Arrays.asList(2, 1, 3));

        assertFalse(EndGame.isEndGame(kockak));

    }

    @Test
    public void testKiNyert() {
        assertEquals("Nyertél", EndGame.kiNyert(kockak));

        kockak.setEllenfelKockaiLista(Arrays.asList(1, 2, 3));
        kockak.setSajatKockaimLista(Arrays.asList(2, 1, 3));

        assertEquals("Vesztettél", EndGame.kiNyert(kockak));

    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eszti
 */
public class KockakTest {
    private final Kockak kockak= new Kockak();
    
    
    @Before
    public void setUp() {
        kockak.setSajatKockaimLista(Arrays.asList(1, 2, 3));
        kockak.setEllenfelKockaiLista(Arrays.asList(1, 2, 3));
        kockak.setRandomKockakLista(Arrays.asList(1, 2, 3));
        kockak.setRandomKocka(5);
        kockak.setCsereleshezKocka(6);
    }

    @Test
    public void testSetRandomKocka() {
        kockak.setRandomKocka(5);
        
        assertTrue(kockak.getRandomKocka() == 5);
    }

    @Test
    public void testSetSajatKockaimLista() {
        kockak.setSajatKockaimLista(Arrays.asList(1, 2, 3));
        assertEquals(Arrays.asList(1, 2, 3), kockak.getSajatKockaimLista());
    }

    @Test
    public void testSetEllenfelKockaiLista() {
        kockak.setEllenfelKockaiLista(Arrays.asList(1, 2, 3));
        assertEquals(Arrays.asList(1, 2, 3), kockak.getEllenfelKockaiLista());
    }

    @Test
    public void testSetRandomKockakLista() {
        kockak.setRandomKockakLista(Arrays.asList(1, 2, 3));
        assertEquals(Arrays.asList(1, 2, 3), kockak.getRandomKockakLista());
    }

    @Test
    public void testSetCsereleshezKocka() {
        kockak.setCsereleshezKocka(5);
        assertTrue(kockak.getCsereleshezKocka() == 5);
    }


    @Test
    public void testSetEgyKockaSajatKockaimbol() {
        kockak.setSajatKockaimLista(Arrays.asList(1, 2, 3));
        kockak.setEgyKockaSajatKockaimbol(1, 5);
        assertEquals(Arrays.asList(1, 5, 3), kockak.getSajatKockaimLista());

    }

    @Test
    public void testSetEgyKockaellenfelKockaibol() {
        kockak.setEllenfelKockaiLista(Arrays.asList(1, 2, 3));
        kockak.setEgyKockaellenfelKockaibol(1, 5);
        assertEquals(Arrays.asList(1, 5, 3), kockak.getEllenfelKockaiLista());
    }

    @Test
    public void testSetEgyKockaRandomKockakbol() {
        kockak.setRandomKockakLista(Arrays.asList(1, 2, 3));
        kockak.setEgyKockaRandomKockakbol(1, 5);
        assertEquals(Arrays.asList(1, 5, 3), kockak.getRandomKockakLista());
    }

    @Test
    public void testSwapCserelniEsSajatKockaim() {
        kockak.setCsereleshezKocka(6);
        kockak.setSajatKockaimLista(Arrays.asList(3, 4, 5));
        kockak.swapCserelniEsSajatKockaim(0);
        assertEquals(Arrays.asList(6, 4, 5), kockak.getSajatKockaimLista());
        
        kockak.setCsereleshezKocka(7);
        kockak.swapCserelniEsSajatKockaim(1);
        assertEquals(10005, kockak.getPontok().getMySource());
        
    }

    @Test
    public void testSwapCserelniEsEllenfelKockai() {
        kockak.setCsereleshezKocka(6);
        kockak.swapCserelniEsEllenfelKockai(0);
        assertEquals(Arrays.asList(6, 2, 3), kockak.getEllenfelKockaiLista());
        
        kockak.setCsereleshezKocka(7);
        kockak.getPontok().setMySource(10);
        kockak.swapCserelniEsEllenfelKockai(1);
        assertEquals(5, kockak.getPontok().getMySource());
    }

    @Test
    public void testSwapCserelniEsRandomKockak() {
        kockak.setCsereleshezKocka(6);
        kockak.swapCserelniEsRandomKockak(0);
        assertEquals(Arrays.asList(6, 2, 3), kockak.getRandomKockakLista());
    }

    @Test
    public void testSwapEgyRandomEsSajatKockaim() {
        kockak.swapEgyRandomEsSajatKockaim(0);
        assertEquals(Arrays.asList(5, 2, 3), kockak.getSajatKockaimLista());
        assertTrue(kockak.getCsereleshezKocka().equals(1));
    }

    
    @Test
    public void testSwapRandomKockaEsCsereleshez() {
        kockak.swapRandomKockaEsCsereleshez();
        assertTrue(kockak.getCsereleshezKocka().equals(5));
        assertTrue(kockak.getRandomKocka().equals(6));
    }

    @Test
    public void testMegvizsgalomSorbanVannakSajatKockaim() {
        assertTrue(kockak.megvizsgalomSorbanVannakSajatKockaim());
    }

    @Test
    public void testMegvizsgalomSorbanVannakEllenfelKockai() {
        assertTrue(kockak.megvizsgalomSorbanVannakEllenfelKockai());
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author eszti
 */
public class EllenfelTest {
    private final Ellenfel ellen= new Ellenfel();
    private final Kockak kockak= new Kockak();
    
    @Before
    public void setUpKockak(){
        kockak.setCsereleshezKocka(5);
        kockak.setSajatKockaimLista(Arrays.asList(35, 22, 21, 20, 19, 18, 17, 16, 29, 33, 47, 11, 10, 39, 8, 32, 36, 38, 45, 1));
        kockak.setEllenfelKockaiLista(Arrays.asList(26, 25, 31, 13, 7, 46, 34, 48, 49, 50, 30, 2, 3, 4, 7, 9, 13, 25, 28, 31));
        kockak.setRandomKockakLista(Arrays.asList(15, 14, 23, 12, 42, 24, 44, 6, 27));
    }
    
    @Test
    public void testGetMax(){
        System.out.println("Ellenfel class getMax function testing");
        List<Integer> tesztelni= Arrays.asList(1, 5, 2);
        
        int expected= 5;
        int resoult= ellen.getMax(tesztelni);
        
        assertEquals(expected, resoult);
        
    }
    
    @Test
    public void testGetMin(){
        System.out.println("Ellenfel class getMin function testing");
        List<Integer> tesztelni= Arrays.asList(3, 5, 2);
        
        int expected= 2;
        int resoult= ellen.getMin(tesztelni);
        
        assertEquals(expected, resoult);
        
    }

    
    @Test
    public void testIntervallumbaEsik() {
        System.out.println("Ellenfel class intervallumbaEsik function testing");
        int vizsgaljuk= 10;
        int alsoHatar= 6;
        int felsoHatar= 20;
        boolean resoult= ellen.intervallumbaEsik(vizsgaljuk,alsoHatar,felsoHatar);
        
        Assert.assertTrue(resoult);
        
        alsoHatar= 12;
        resoult= ellen.intervallumbaEsik(vizsgaljuk, alsoHatar, felsoHatar);
        
        Assert.assertFalse(resoult);
    }
    
    @Test
    public void testElsoKategoriabanCserel() {
        System.out.println("Ellenfel class elsoKategoriabanCserel function testing");
        List<Integer> expected= kockak.getEllenfelKockaiLista();
        expected.set(0, 5);
        ellen.lep(kockak);
       
        Assert.assertEquals(expected, kockak.getEllenfelKockaiLista());
        
    }
    
    @Test
    public void testNagyobbKategoriabanCserel() {
        System.out.println("Ellenfel class nagyobbKategoriabanCserel function testing");
        List<Integer> vizsgalandoKategoriaTagjai= Arrays.asList(12, 10,  15);
        kockak.setCsereleshezKocka(11);
        List<Integer> expected= Arrays.asList(12, 11,  15);

       
        Assert.assertEquals(expected, ellen.nagyobbKategoriakbanCserel(vizsgalandoKategoriaTagjai, kockak));
        
        kockak.setCsereleshezKocka(10);
        expected.set(0, 10);
       
        Assert.assertEquals(expected, ellen.nagyobbKategoriakbanCserel(vizsgalandoKategoriaTagjai, kockak));
        
        kockak.setCsereleshezKocka(17);
        expected.set(2, 17);
       
        Assert.assertEquals(expected, ellen.nagyobbKategoriakbanCserel(vizsgalandoKategoriaTagjai, kockak));
    }

    @Test
    public void testLep() {
        
   }
    
}

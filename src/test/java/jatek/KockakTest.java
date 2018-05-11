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
    
    @After
    public void tearDown() {
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

//    @Test
//    public void testSwapCserelniEsRandomKockak() {
//        System.out.println("swapCserelniEsRandomKockak");
//        Integer hol = null;
//        Kockak instance = new Kockak();
//        instance.swapCserelniEsRandomKockak(hol);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of swapEgyRandomEsSajatKockaim method, of class Kockak.
//     */
//    @Test
//    public void testSwapEgyRandomEsSajatKockaim() {
//        System.out.println("swapEgyRandomEsSajatKockaim");
//        Integer hol = null;
//        Kockak instance = new Kockak();
//        instance.swapEgyRandomEsSajatKockaim(hol);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of swapRandomKockaEsCsereleshez method, of class Kockak.
//     */
//    @Test
//    public void testSwapRandomKockaEsCsereleshez() {
//        System.out.println("swapRandomKockaEsCsereleshez");
//        Kockak instance = new Kockak();
//        instance.swapRandomKockaEsCsereleshez();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of megvizsgalomSorbanVannakSajatKockaim method, of class Kockak.
//     */
//    @Test
//    public void testMegvizsgalomSorbanVannakSajatKockaim() {
//        System.out.println("megvizsgalomSorbanVannakSajatKockaim");
//        Kockak instance = new Kockak();
//        boolean expResult = false;
//        boolean result = instance.megvizsgalomSorbanVannakSajatKockaim();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of megvizsgalomSorbanVannakEllenfelKockai method, of class Kockak.
//     */
//    @Test
//    public void testMegvizsgalomSorbanVannakEllenfelKockai() {
//        System.out.println("megvizsgalomSorbanVannakEllenfelKockai");
//        Kockak instance = new Kockak();
//        boolean expResult = false;
//        boolean result = instance.megvizsgalomSorbanVannakEllenfelKockai();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of randomotDob method, of class Kockak.
//     */
//    @Test
//    public void testRandomotDob() {
//        System.out.println("randomotDob");
//        Kockak instance = new Kockak();
//        Integer expResult = null;
//        Integer result = instance.randomotDob();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isAdatbazisEmpty method, of class Kockak.
//     */
//    @Test
//    public void testIsAdatbazisEmpty() {
//        System.out.println("isAdatbazisEmpty");
//        Kockak instance = new Kockak();
//        boolean expResult = false;
//        boolean result = instance.isAdatbazisEmpty();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of adatbazisbaMent method, of class Kockak.
//     */
//    @Test
//    public void testAdatbazisbaMent() {
//        System.out.println("adatbazisbaMent");
//        Kockak instance = new Kockak();
//        instance.adatbazisbaMent();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of datbazisbolKiszedi method, of class Kockak.
//     */
//    @Test
//    public void testDatbazisbolKiszedi() {
//        System.out.println("datbazisbolKiszedi");
//        Kockak instance = new Kockak();
//        Kockak expResult = null;
//        Kockak result = instance.datbazisbolKiszedi();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of adatbazisRekordokTorlese method, of class Kockak.
//     */
//    @Test
//    public void testAdatbazisRekordokTorlese() {
//        System.out.println("adatbazisRekordokTorlese");
//        Kockak instance = new Kockak();
//        instance.adatbazisRekordokTorlese();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of closeConnect method, of class Kockak.
//     */
//    @Test
//    public void testCloseConnect() {
//        System.out.println("closeConnect");
//        Kockak instance = new Kockak();
//        instance.closeConnect();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Kockak.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Kockak instance = new Kockak();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}

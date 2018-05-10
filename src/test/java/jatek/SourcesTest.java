/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eszti
 */
public class SourcesTest {
    
    private final Sources source= new Sources();

    @Test
    public void testMinusz5() {
        System.out.println("Source class minusz5 function testing");
        source.setMySource(10);
        int expected= 5;
        int actual= source.minusz5();
        assertEquals(expected, actual);
    }

    
    @Test
    public void testPlusz15() {
        System.out.println("Source class plusz15 function testing");
        source.setMySource(10);
        int expected= 25;
        int actual= source.plusz15();
        assertEquals(expected, actual);
    }
}

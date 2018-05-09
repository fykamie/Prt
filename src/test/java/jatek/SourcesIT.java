/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eszti
 */
public class SourcesIT {
    private final Sources source= new Sources();

    /**
     * Test of minusz5 method, of class Sources.
     */
    @Test
    public void testMinusz5() {
        System.out.println("minusz5 testing:");
        System.out.println("resoult: "+source.minusz5());
        
      //  assertFalse("Sikeresen kivonva", resoult == whatWasInSources-5 );
        assertEquals(995, source.minusz5());
        fail("Sikertelen kivonás");
    }

    /**
     * Test of plusz10 method, of class Sources.
     */
  /*  @Test
    public void testPlusz10() {
        System.out.println("plusz10 testing:");
        Sources sources= new Sources();
        
        int whatWasInSources= sources.getMySource();
        System.out.println("whatwas: "+whatWasInSources);
        sources.plusz10();
        int resoult= sources.getMySource();
        System.out.println("resoult: "+resoult);
        
        assertTrue("Sikeresen hozzáadva", resoult == whatWasInSources+10 );
        fail("Sikertelen összeadás");
    }
    */
}

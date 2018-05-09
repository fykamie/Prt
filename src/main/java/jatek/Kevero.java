/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author eszti
 */
public class Kevero {
    private static final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());
    private static Random rn= new Random();
    
    public static List<Integer> kever(List<Integer> keverendo){
        int csereSeged;
        int pozicio;
        
        for(int index= 0; index < keverendo.size(); ++index){ 
            csereSeged = keverendo.get(index);
            keverendo.set(index, keverendo.get(pozicio= rn.nextInt(keverendo.size()))); 
            keverendo.set(pozicio, csereSeged); 
        }
        
        LOG.debug("megkevertük a listát");
        return keverendo;
    }
}

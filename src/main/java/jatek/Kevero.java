/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import java.util.List;
import java.util.Random;

/**
 *
 * @author eszti
 */
public class Kevero {
    Random rn= new Random();
    
    public List<Integer> kever(List<Integer> keverendo){
        int csereSeged;
        int pozicio;
        
        for(int index= 0; index < keverendo.size(); ++index){ 
            csereSeged = keverendo.get(index);
            keverendo.set(index, keverendo.get(pozicio= rn.nextInt(keverendo.size()))); 
            keverendo.set(pozicio, csereSeged); 
        }
        
        return keverendo;
    }
}

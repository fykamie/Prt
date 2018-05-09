/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author eszti
 */
public class EndGame {
    private static final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());
    public static boolean isEndGame(Kockak kockak){
        LOG.debug("megvizsgáljuk hogy vége van-e a játéknak");
        if( kockak.megvizsgalomSorbanVannakEllenfelKockai() || kockak.megvizsgalomSorbanVannakSajatKockaim() )
            return true;
        
        return false;
    }
    
    public static String kiNyert(Kockak kockak){
        LOG.debug("megvizsgáljuk ki nyert");
        if(kockak.megvizsgalomSorbanVannakSajatKockaim())
            return "Nyertél";
        
        return "Vesztettél";
    }
    
}

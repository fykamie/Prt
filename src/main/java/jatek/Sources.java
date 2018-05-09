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
public class Sources {
    private final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());
    private int mySource= 1000;
    
    public int getMySource() {
        return mySource;
    }
    
    public int minusz5(){
        LOG.debug("csökkent a pontszám öttel");

        return mySource-= 5;
    }
    
    public int plusz15(){
        LOG.debug("nőtt a pontszám tizenöttel");

        return mySource+= 15;
    }
}

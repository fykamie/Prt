/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

/**
 *
 * @author eszti
 */
public class Sources {
    private int mySource= 1000;
    
    public int getMySource() {
        return mySource;
    }
    
    public int minusz5(){
       return mySource-= 5;
    }
    
    public int plusz10(){
       return mySource+= 10;
    }
}

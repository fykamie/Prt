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
    
    public void minusz5(){
        mySource-= 5;
    }
    
    public void plusz10(){
        mySource+= 10;
    }
}

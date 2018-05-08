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
public class EndGame {
    public boolean isEndGame(Kockak kockak){
        if( kockak.megvizsgalomSorbanVannakEllenfelKockai() || kockak.megvizsgalomSorbanVannakSajatKockaim() )
            return true;
        
        return false;
    }
    
    public String kiNyert(Kockak kockak){
        if(kockak.megvizsgalomSorbanVannakSajatKockaim())
            return "Nyertél";
        
        return "Vesztettél";
    }
    
}

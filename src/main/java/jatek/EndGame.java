package jatek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndGame {
    /**
     * Ha a paraméterül kapott állásban valamelyik félnek sorban vannak a kockái igazzal, egyébként hamissal tér vissza.
     */
    private static final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());
    public static boolean isEndGame(Kockak kockak){
        LOG.debug("megvizsgáljuk hogy vége van-e a játéknak");
        if( kockak.megvizsgalomSorbanVannakEllenfelKockai() || kockak.megvizsgalomSorbanVannakSajatKockaim() )
            return true;
        
        return false;
    }
    
    /**
     * Ha a játékos nyert akkor "Nyertél", egyébként "Vesztettél" {@link java.lang.String}-gel tér vissza.
     * 
     * @param kockak
     * @return 
     */
    public static String kiNyert(Kockak kockak){
        LOG.debug("megvizsgáljuk ki nyert");
        if(kockak.megvizsgalomSorbanVannakSajatKockaim())
            return "Nyertél";
        
        return "Vesztettél";
    }
    
}

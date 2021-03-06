package jatek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sources {
    private final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());
    private int mySource= 10000;

    /**
     * A mySource settere.
     * @param mySource 
     */
    public void setMySource(int mySource) {
        this.mySource = mySource;
    }
    
    /**
     * A mySource gettere.
     * 
     * @return 
     */
    public int getMySource() {
        return mySource;
    }
    
    /**
     * A pontokból kivon 5-t.
     * 
     * @return 
     */
    public int minusz5(){
        LOG.info("csökkent a pontszám öttel");

        return mySource-= 5;
    }
    
    /**
     * A pontokhoz ad 15-t.
     * 
     * @return 
     */
    public int plusz15(){
        LOG.info("nőtt a pontszám tizenöttel");

        return mySource+= 15;
    }

    /**
     * Az osztály tartalmát stringgé alakítja.
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Pontjaim: "+ mySource ;
    }
    
    
}

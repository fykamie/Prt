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
    
    /**
     * Megkeveri az átadott lista tagjait.
     * 
     * @param keverendo
     * @return 
     */
    public static List<Integer> kever(List<Integer> keverendo){
        int csereSeged;
        int pozicio;
        
        for(int index= 0; index < keverendo.size(); ++index){ 
            csereSeged = keverendo.get(index);
            keverendo.set(index, keverendo.get(pozicio= rn.nextInt(keverendo.size()))); 
            keverendo.set(pozicio, csereSeged); 
        }
        
        LOG.info("megkevertük a listát");
        return keverendo;
    }
}

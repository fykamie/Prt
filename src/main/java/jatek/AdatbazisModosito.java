package jatek;

import adatbazis.BuildPyramid;
import adatbazis.Lekerdezesek;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdatbazisModosito {
        
    private static final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());

    /**
     * A {@code Lekerdezesek#deleteAll()} segítségével törli az adatbázis összes rekordját.
     * 
     * @param em 
     */
    public static void mindentKiNullaz(EntityManager em){
        Lekerdezesek lekerdezes= new Lekerdezesek(em);
        List<BuildPyramid> adatbazisbanVannak= lekerdezes.getAll();
        if(!adatbazisbanVannak.isEmpty())
            lekerdezes.deleteAll();

        LOG.info("adatbázisban kitörölte az összes rekordot");
    }
        
    /**
     * A paraméterül kapott állást menti le az adatbázisba.
     * 
     * @param emf
     * @param kockak 
     */
    public static void kockakAdatbazisbaMentese( EntityManager emf, Kockak kockak ){
        Lekerdezesek lekerdezesek= new Lekerdezesek(emf);
        
        kockak.getSajatKockaimLista().forEach(a -> {
            BuildPyramid berakni= lekerdezesek.getByID(a);
            berakni.setKihezTartozik(1);
            lekerdezesek.insert(berakni);
        });

        kockak.getEllenfelKockaiLista().forEach(a -> {
            BuildPyramid berakni= lekerdezesek.getByID(a);
            berakni.setKihezTartozik(2);
            lekerdezesek.insert(berakni);
        });

        kockak.getRandomKockakLista().forEach(a -> {
            BuildPyramid berakni= lekerdezesek.getByID(a);
            berakni.setKihezTartozik(3);
            lekerdezesek.insert(berakni);
        });
        
        BuildPyramid berakni= lekerdezesek.getByID(kockak.getCsereleshezKocka());
        berakni.setKihezTartozik(4);
        lekerdezesek.insert(berakni);

        LOG.info("adatbázisba mentette az állást");
    }
    
    /**
     * Lekéri az adatbázisból a játékos állását.
     * 
     * @param emf
     * @return 
     */
    public static List<Integer> sajatKockakAdatbazisbol(EntityManager emf){
        Lekerdezesek lekerdezesek= new Lekerdezesek(emf);
        List<Integer> visszaTer= new ArrayList<>();
        lekerdezesek.get(a -> a.getKihezTartozik() == 1).forEach(a -> visszaTer.add(a.getKockak()));

        LOG.info("adatbázisból saját kockák");
        return visszaTer;        
    }
    
    /**
     * Lekéri az adatbázisból az ellenfél álllását.
     * 
     * @param emf
     * @return 
     */
    public static List<Integer> ellenfelKockakAdatbazisbol(EntityManager emf){
        Lekerdezesek lekerdezesek= new Lekerdezesek(emf);
        List<Integer> visszaTer= new ArrayList<>();
        lekerdezesek.get(a -> a.getKihezTartozik() == 2).forEach(a -> visszaTer.add(a.getKockak()));

        LOG.info("adatbázisból ellenfel kockák");
        return visszaTer;        
    }
    
    /**
     * Lekéri az adatbázisból a {@code Kockak#randomKockakLista } állását.
     * 
     * @param emf
     * @return 
     */
    public static List<Integer> randomKockakAdatbazisbol(EntityManager emf){
        Lekerdezesek lekerdezesek= new Lekerdezesek(emf);
        List<Integer> visszaTer= new ArrayList<>();
        lekerdezesek.get(a -> a.getKihezTartozik() == 3).forEach(a -> visszaTer.add(a.getKockak()));

        LOG.info("adatbázisból random kockák");
        return visszaTer;        
    }
    
    /**
     * Lekéri az adatbázisból a {@code Kockak#csereleshezKocka}-t.
     * 
     * @param emf
     * @return 
     */
    public static Integer csereleshezKockaAdatbazisbol(EntityManager emf){
        Lekerdezesek lekerdezesek= new Lekerdezesek(emf);
        
        LOG.info("adatbázisból random kockák");
        return lekerdezesek.get(a -> a.getKihezTartozik() == 4).get(0).getKockak();
    }
}

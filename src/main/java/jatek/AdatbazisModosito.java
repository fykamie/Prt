/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import adatbazis.BuildPyramid;
import adatbazis.Lekerdezesek;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author eszti
 */
public class AdatbazisModosito {
        
    private static final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());
    
    public static void mindentKiNullaz(EntityManagerFactory emf){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        Lekerdezesek lekerdezes= new Lekerdezesek(emf);
        List<BuildPyramid> adatbazisbanVannak= lekerdezes.getAll();
        if(!adatbazisbanVannak.isEmpty())
            lekerdezes.deleteAll();

        LOG.debug("adatbázisban kitörölte az összes rekordot");
    }
        
    public static void kockakAdatbazisbaMentese( EntityManagerFactory emf, Kockak kockak ){
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

        LOG.debug("adatbázisba mentette az állást");
    }
    
    public static List<Integer> sajatKockakAdatbazisbol(EntityManagerFactory emf){
        Lekerdezesek lekerdezesek= new Lekerdezesek(emf);
        List<Integer> visszaTer= new ArrayList<>();
        lekerdezesek.get(a -> a.getKihezTartozik() == 1).forEach(a -> visszaTer.add(a.getKockak()));

        LOG.debug("adatbázisból saját kockák");
        return visszaTer;        
    }
    
    public static List<Integer> ellenfelKockakAdatbazisbol(EntityManagerFactory emf){
        Lekerdezesek lekerdezesek= new Lekerdezesek(emf);
        List<Integer> visszaTer= new ArrayList<>();
        lekerdezesek.get(a -> a.getKihezTartozik() == 2).forEach(a -> visszaTer.add(a.getKockak()));

        LOG.debug("adatbázisból ellenfel kockák");
        return visszaTer;        
    }
    
    public static List<Integer> randomKockakAdatbazisbol(EntityManagerFactory emf){
        Lekerdezesek lekerdezesek= new Lekerdezesek(emf);
        List<Integer> visszaTer= new ArrayList<>();
        lekerdezesek.get(a -> a.getKihezTartozik() == 3).forEach(a -> visszaTer.add(a.getKockak()));

        LOG.debug("adatbázisból random kockák");
        return visszaTer;        
    }
    
    public static Integer csereleshezKockaAdatbazisbol(EntityManagerFactory emf){
        Lekerdezesek lekerdezesek= new Lekerdezesek(emf);
        
        LOG.debug("adatbázisból random kockák");
        return lekerdezesek.get(a -> a.getKihezTartozik() == 4).get(0).getKockak();
    }
}

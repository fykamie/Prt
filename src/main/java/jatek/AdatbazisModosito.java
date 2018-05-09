/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import adatbazis.BuildPyramid;
import adatbazis.Lekerdezesek;
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
        
    private final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());
    
    public void mindentKiNullaz(EntityManagerFactory emf){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        Lekerdezesek lekerdezes= new Lekerdezesek(emf);
        List<BuildPyramid> adatbazisbanVannak= lekerdezes.getAll();
        if(!adatbazisbanVannak.isEmpty())
            lekerdezes.deleteAll();

        LOG.debug("adatbázisban kitörölte az összes rekordot");
    }
        
    public void adatbazisbanCserelCsereleshezEsSajatbolEgy(EntityManagerFactory emf, Integer csereleshez, Integer sajatbolEgy){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        Lekerdezesek lekerdezes= new Lekerdezesek(emf);
        
        BuildPyramid seged1= lekerdezes.getByID(csereleshez);
        seged1.setKihezTartozik(1);
        lekerdezes.insert(seged1);

        BuildPyramid seged2= lekerdezes.getByID(sajatbolEgy);
        seged2.setKihezTartozik(4);
        lekerdezes.insert(seged2);
        LOG.debug("adatbázisban cserélte a cseréléshez kockát és kiválasztott kockát");
        
    }
    
    public void adatbazisbanCserelCsereleshezEsEllenfelbolEgy(EntityManagerFactory emf, Integer csereleshez, Integer ellenfelbolEgy){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        Lekerdezesek lekerdezes= new Lekerdezesek(emf);
        
        BuildPyramid seged1= lekerdezes.getByID(csereleshez);
        seged1.setKihezTartozik(2);
        lekerdezes.insert(seged1);

        BuildPyramid seged2= lekerdezes.getByID(ellenfelbolEgy);
        seged2.setKihezTartozik(4);
        lekerdezes.insert(seged2);
        LOG.debug("adatbázisban cserélte a cseréléshez kockát és ellenfélből egy kockát");
        
    }
    
    public void adatbazisbanCserelRandomEsSajatbolEgy(EntityManagerFactory emf, Integer randombolEgy, Integer sajatbolEgy, Integer eldobott){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        Lekerdezesek lekerdezes= new Lekerdezesek(emf);
        
        BuildPyramid seged= lekerdezes.getByID(eldobott);
        adatbazisbanCserelCsereleshezEsSajatbolEgy(emf, randombolEgy, sajatbolEgy);
        
        seged.setKihezTartozik(3);
        lekerdezes.insert(seged);
        LOG.debug("adatbázisban cserélte a kapott random kockát és választott kockát");
    }
}

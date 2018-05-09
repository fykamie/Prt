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
        
    private Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());
    public void mindentKiNullaz(EntityManagerFactory emf){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        Lekerdezesek lekerdezes= new Lekerdezesek(emf);
        List<BuildPyramid> adatbazisbanVannak= lekerdezes.getAll();
        if(!adatbazisbanVannak.isEmpty())
            lekerdezes.deleteAll();
        
        for(int kockak= 1; kockak < 51; kockak++){
            BuildPyramid record= new BuildPyramid();
            record.setKockak(kockak);
            lekerdezes.insert(record);
        }
    }
    
    public Kockak setTableForStart(EntityManagerFactory emf){        
     
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        Lekerdezesek lekerdezes= new Lekerdezesek(emf);
        Kockak kocka= new Kockak();
        ReadyForStart makeReady= new ReadyForStart();
        
        kocka.setSajatKockaimLista(makeReady.sajatKockaimBeallitasa(kocka));
        kocka.setEllenfelKockaiLista(makeReady.ellenfelKockainakBeallitasa(kocka));
        kocka.setRandomKockakLista(makeReady.randomKockakBeallitasa(kocka));
        kocka.setCsereleshezKocka(makeReady.randomKockaBeallitasa(kocka));
        
        for(Integer sajatKockakBeallitasa : kocka.getSajatKockaimLista()){
            BuildPyramid berakando= lekerdezes.getByID(sajatKockakBeallitasa);
            berakando.setKihezTartozik(1);
            lekerdezes.insert(berakando);
        }

        for(Integer ellenfelKockakBeallitasa : kocka.getEllenfelKockaiLista()){
            BuildPyramid berakando= lekerdezes.getByID(ellenfelKockakBeallitasa);
            berakando.setKihezTartozik(2);
            lekerdezes.insert(berakando);
        }
        
        List<BuildPyramid> randomKockaRekordok= lekerdezes.get(a -> a.getKihezTartozik() == 0);
        
        for(BuildPyramid record : randomKockaRekordok){
            record.setKihezTartozik(3);
            lekerdezes.insert(record);
        }
        
        BuildPyramid cserelendoKockaRekord= lekerdezes.getByID(kocka.getCsereleshezKocka());
        cserelendoKockaRekord.setKihezTartozik(4);
        lekerdezes.insert(cserelendoKockaRekord);
        
        return kocka;
    }
    
    public void adatbazisbanCserelCsereleshezEsSajatbolEgy(EntityManagerFactory emf, Integer csereleshez, Integer sajatbolEgy){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        Lekerdezesek lekerdezes= new Lekerdezesek(emf);
        
        BuildPyramid seged1= lekerdezes.getByID(csereleshez);
//        LOG.debug(csereleshez+"csere elott csereleshez( "+seged1+" ) kihez tartozik: "+ seged1.getKihezTartozik());
        seged1.setKihezTartozik(1);
        lekerdezes.insert(seged1);
//        LOG.debug(csereleshez+"csere utan csereleshez( "+seged1+" ) kihez tartozik: "+ seged1.getKihezTartozik());
        BuildPyramid seged2= lekerdezes.getByID(sajatbolEgy);
//        LOG.debug(sajatbolEgy+"csere elott sajatbolEgy( "+seged1+" ) kihez tartozik: "+ seged1.getKihezTartozik());
        seged2.setKihezTartozik(4);
        lekerdezes.insert(seged2);
//        LOG.debug(sajatbolEgy+"csere utan sajatbolEgy( "+seged2+" ) kihez tartozik: "+ seged2.getKihezTartozik());
        
    }
    
    public void adatbazisbanCserelRandomEsSajatbolEgy(EntityManagerFactory emf, Integer randombolEgy, Integer sajatbolEgy, Integer eldobott){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        Lekerdezesek lekerdezes= new Lekerdezesek(emf);
        
        BuildPyramid seged= lekerdezes.getByID(eldobott);
        adatbazisbanCserelCsereleshezEsSajatbolEgy(emf, randombolEgy, sajatbolEgy);
        
        seged.setKihezTartozik(3);
        lekerdezes.insert(seged);
    }
}

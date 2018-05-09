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

/**
 *
 * @author eszti
 */
public class AdatbazisModosito {
        
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
    
    public void setTableForStart(EntityManagerFactory emf){        
     
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
        
    }
    
    public void AdazbazisbaMentAllast(EntityManagerFactory emf, Kockak kockak){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        Lekerdezesek lekerdezes= new Lekerdezesek(emf);
        Kockak myKocka= kockak;
        
        myKocka.getSajatKockaimLista().forEach(berakando -> {
            BuildPyramid toBeInsert= lekerdezes.getByID(berakando);
            toBeInsert.setKihezTartozik(1);
            lekerdezes.insert(toBeInsert);
        });
        
        myKocka.getEllenfelKockaiLista().forEach(berakando -> {
            BuildPyramid toBeInsert= lekerdezes.getByID(berakando);
            toBeInsert.setKihezTartozik(2);
            lekerdezes.insert(toBeInsert);
        });
        
        myKocka.getRandomKockakLista().forEach(berakando -> {
            BuildPyramid toBeInsert= lekerdezes.getByID(berakando);
            toBeInsert.setKihezTartozik(3);
            lekerdezes.insert(toBeInsert);
        });
        
        BuildPyramid berakando= lekerdezes.getByID(myKocka.getCsereleshezKocka());
        berakando.setKihezTartozik(4);
    }
    
    public Kockak AdatbasibolKiszediAllast(EntityManagerFactory emf){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        Lekerdezesek lekerdezes= new Lekerdezesek(emf);
        Kockak myKocka= new Kockak();
                
        List<BuildPyramid> sajatom= lekerdezes.get(a -> a.getKihezTartozik() == 1);
        List<BuildPyramid> ellenfel= lekerdezes.get(a -> a.getKihezTartozik() == 2);
        List<BuildPyramid> random= lekerdezes.get(a -> a.getKihezTartozik() == 3);
        List<BuildPyramid> csereleshez= lekerdezes.get(a -> a.getKihezTartozik() == 4);
        
        sajatom.forEach(a -> myKocka.getSajatKockaimLista().add(a.getKockak()));
        ellenfel.forEach(a -> myKocka.getEllenfelKockaiLista().add(a.getKockak()));
        random.forEach(a -> myKocka.getRandomKockakLista().add(a.getKockak()));
        myKocka.setCsereleshezKocka(csereleshez.get(0).getKockak());
        
        return myKocka;
    }
}

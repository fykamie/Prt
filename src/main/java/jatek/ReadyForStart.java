/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import adatbazis.BuildPyramid;
import adatbazis.Lekerdezesek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author eszti
 */
public class ReadyForStart {
    private Random rn= new Random();
    private Integer random;
    
    public Kockak makeReady(Kockak kockak){
        EntityManagerFactory ef= Persistence.createEntityManagerFactory("databaseConnection");
        Lekerdezesek lekerdezes= new Lekerdezesek(ef);
        
        AdatbazisModosito nullazo= new AdatbazisModosito();
        nullazo.mindentKiNullaz(ef);
        nullazo.setTableForStart(ef);
        
        List<BuildPyramid> sajatok= lekerdezes.get(a -> a.getKihezTartozik() == 1);
        List<BuildPyramid> ellenfel= lekerdezes.get(a -> a.getKihezTartozik() == 2);
        List<BuildPyramid> random= lekerdezes.get(a -> a.getKihezTartozik() == 3);
        random.add(lekerdezes.get(a -> a.getKihezTartozik() == 4).get(0));
        
        List<Integer> sajatom= new ArrayList<>();
        List<Integer> ellenfele= new ArrayList<>();
        List<Integer> randomban= new ArrayList<>();
        
        lekerdezes.get(a -> a.getKihezTartozik() == 1).stream().forEach( a -> sajatom.add(a.getKockak()));
        lekerdezes.get(a -> a.getKihezTartozik() == 2).stream().forEach( a -> ellenfele.add(a.getKockak()));
        lekerdezes.get(a -> a.getKihezTartozik() == 3).stream().forEach( a -> randomban.add(a.getKockak()));
        lekerdezes.get(a -> a.getKihezTartozik() == 4).stream().forEach( a -> randomban.add(a.getKockak()));
        
        kockak.setSajatKockaimLista(sajatom);
        kockak.setEllenfelKockaiLista(ellenfele);
        kockak.setRandomKockakLista(randomban);
        kockak.setCsereleshezKocka(lekerdezes.get(a -> a.getKihezTartozik() == 4).get(0).getKihezTartozik());
        
        
        ef.close();
        return kockak;
    }
    
    public List<Integer> sajatKockaimBeallitasa(Kockak kockak){
        for(int index= 0; index < 20; index++){
            random= rn.nextInt(50)+1;
            while(kockak.getSajatKockaimLista().contains(random)){
                random= rn.nextInt(50)+1;
            }
            kockak.getSajatKockaimLista().add(random);
        }
        
        return kockak.getSajatKockaimLista();
    }
    
    public List<Integer> ellenfelKockainakBeallitasa(Kockak kockak){
        for(int index= 0; index < 20; index++){
            random= rn.nextInt(50)+1;
            while(kockak.getEllenfelKockaiLista().contains(random) || kockak.getSajatKockaimLista().contains(random)){
                random= rn.nextInt(50)+1;
            }
            kockak.getEllenfelKockaiLista().add(random);
        }
        
        return kockak.getEllenfelKockaiLista();
    }
    
    public List<Integer> randomKockakBeallitasa( Kockak kockak ){
        for(int berakando= 1; berakando < 51; berakando++){
            if( !kockak.getSajatKockaimLista().contains(berakando) && !kockak.getEllenfelKockaiLista().contains(berakando))
                kockak.getRandomKockakLista().add(berakando);
        }
        
        return kockak.getRandomKockakLista();
    }
    
    public Integer randomKockaBeallitasa ( Kockak kockak){
        kockak.setRandomKocka(kockak.getRandomKockakLista().get(rn.nextInt(kockak.getRandomKockakLista().size())));
        return kockak.getRandomKocka();
    }
}
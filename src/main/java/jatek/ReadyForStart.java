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
import java.util.Random;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author eszti
 */
public class ReadyForStart {
    private final Random rn= new Random();
    private Integer random;
    
    public Kockak makeReady(Kockak kockak){
        EntityManagerFactory ef= Persistence.createEntityManagerFactory("databaseConnection");
        Lekerdezesek lekerdezes= new Lekerdezesek(ef);
        
        new AdatbazisModosito().mindentKiNullaz(ef);
        
        Kockak kocku = new Kockak();
        
        List<BuildPyramid> mindenKocka = new ArrayList<>(50);
        for(int i = 1; i < 51; ++i){
            
            mindenKocka.add(new BuildPyramid(i, 0));
        }
        
        List<Integer> kie = new ArrayList<>(50);
        for(int i = 0; i < 20; ++i){
            kie.add(1);
            kie.add(2);
            if(i < 10 ){
                kie.add(3);
            }
        }
        
        kie = Kevero.kever(kie);
        
        for(int i = 0; i < 50; ++i){
            if(kie.get(i) == 3){
                kie.set(i, 4);
                break;
            }
        }
        
        for(int i = 0; i < 50; ++i){
            mindenKocka.get(i).setKihezTartozik(kie.get(i));
        }
        
        mindenKocka.forEach(e -> {
        
            lekerdezes.insert(e);
        });
        
        kocku.setSajatKockaimLista(
                mindenKocka.stream()
                        .filter(e -> e.getKihezTartozik() == 1)
                        .mapToInt(f -> f.getKockak())
                        .boxed()
                        .collect(Collectors.toList())
        );
        kocku.setEllenfelKockaiLista(
                mindenKocka.stream()
                        .filter(e -> e.getKihezTartozik() == 2)
                        .mapToInt(f -> f.getKockak())
                        .boxed()
                        .collect(Collectors.toList())
        );
        kocku.setRandomKockakLista(
                mindenKocka.stream()
                        .filter(e -> e.getKihezTartozik() == 3)
                        .mapToInt(f -> f.getKockak())
                        .boxed()
                        .collect(Collectors.toList())
        );
        
        kocku.setCsereleshezKocka(mindenKocka.stream().filter(e -> e.getKihezTartozik() == 4).findFirst().get().getKockak());
        
        ef.close();
        return kocku;
    }
    
}
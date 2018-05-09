/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

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
public class Ellenfel {
    private final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());

    public void lep( Kockak kockak){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        AdatbazisModosito adatbazisModosito= new AdatbazisModosito();
        List<Integer> csereSeged= new ArrayList<>();
        Integer listaIndex= 2;
        
        if(kockak.getCsereleshezKocka()< 9){
            elsoKategoriabanCserel(kockak, emf);
        }
        
        for(int kategoriaIntervallum= 9; kategoriaIntervallum < 46; kategoriaIntervallum+= 7){
            if(intervallumbaEsik(kockak.getCsereleshezKocka(), kategoriaIntervallum, kategoriaIntervallum+6)){
                
                csereSeged.add(kockak.getEllenfelKockaiLista().get(listaIndex));
                csereSeged.add(kockak.getEllenfelKockaiLista().get(listaIndex+1));
                csereSeged.add(kockak.getEllenfelKockaiLista().get(listaIndex+2));
                csereSeged= nagyobbKategoriakbanCserel(emf, csereSeged, kockak);
                kockak.setEgyKockaellenfelKockaibol(listaIndex, csereSeged.get(0));
                kockak.setEgyKockaellenfelKockaibol(listaIndex+1, csereSeged.get(1));
                kockak.setEgyKockaellenfelKockaibol(listaIndex+2, csereSeged.get(2));
                csereSeged.clear();
                break;
            }
            
            listaIndex+= 3;
        }
        

    }
    
    private boolean intervallumbaEsik(Integer ellenerozni, Integer alsoHatar, Integer felsoHatar){
        for(int checker= alsoHatar; checker <= felsoHatar; checker++){
            if(ellenerozni == checker)
                return true;
        }
        
        return false;
    }
    
    private void elsoKategoriabanCserel( Kockak kockak, EntityManagerFactory emf ){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        int csereSeged;
        AdatbazisModosito adatbazisModosito= new AdatbazisModosito();
        if( kockak.getCsereleshezKocka()> kockak.getEllenfelKockaiLista().get(0) ){
            csereSeged= kockak.getEllenfelKockaiLista().get(1);
            adatbazisModosito.adatbazisbanCserelCsereleshezEsEllenfelbolEgy(emf, kockak.getCsereleshezKocka(), csereSeged);
            kockak.setEgyKockaellenfelKockaibol(1, kockak.getCsereleshezKocka());
            kockak.setCsereleshezKocka(csereSeged);          
        }
        else{
            csereSeged= kockak.getEllenfelKockaiLista().get(0);
            adatbazisModosito.adatbazisbanCserelCsereleshezEsEllenfelbolEgy(emf, kockak.getCsereleshezKocka(), csereSeged);
            kockak.setEgyKockaellenfelKockaibol(0, kockak.getCsereleshezKocka());
            kockak.setCsereleshezKocka(csereSeged);          
        }
                  
    }
    
    private List<Integer> nagyobbKategoriakbanCserel(EntityManagerFactory  emf, List<Integer> kategoriaTagjai, Kockak kockak){
        if(!emf.isOpen())
            emf= Persistence.createEntityManagerFactory("databaseConnection");
        List<Integer> csereSegedLista= kategoriaTagjai;
        csereSegedLista.add(kockak.getCsereleshezKocka());
        int max= getMax(csereSegedLista);
        int min= getMin(csereSegedLista);
        int csereSeged;
        AdatbazisModosito adatbazisModosito= new AdatbazisModosito();
        
        if( kockak.getCsereleshezKocka()== max ){
            csereSeged= kategoriaTagjai.get(2);
            adatbazisModosito.adatbazisbanCserelCsereleshezEsEllenfelbolEgy(emf, kockak.getCsereleshezKocka(), csereSeged);
            kategoriaTagjai.set(2, kockak.getCsereleshezKocka());
            kockak.setCsereleshezKocka(csereSeged);
        }
        else if( kockak.getCsereleshezKocka()== min ){
            csereSeged= kategoriaTagjai.get(0);
            adatbazisModosito.adatbazisbanCserelCsereleshezEsEllenfelbolEgy(emf, kockak.getCsereleshezKocka(), csereSeged);
            kategoriaTagjai.set(0, kockak.getCsereleshezKocka());
            kockak.setCsereleshezKocka(csereSeged);
        }
        else{
            csereSeged= kategoriaTagjai.get(1);
            adatbazisModosito.adatbazisbanCserelCsereleshezEsEllenfelbolEgy(emf, kockak.getCsereleshezKocka(), csereSeged);
            kategoriaTagjai.set(1, kockak.getCsereleshezKocka());
            kockak.setCsereleshezKocka(csereSeged);
        }

        return kategoriaTagjai;
    }
    
    private Integer getMax(List<Integer> keresendo){
        int max= 0;
        for( Integer toBeMax : keresendo ){
            if(toBeMax > max)
                max= toBeMax;
        }
        return max;
    }
    
    private Integer getMin(List<Integer> keresendo){
        int min= 0;
        for( Integer toBeMax : keresendo ){
            if(toBeMax > min)
                min= toBeMax;
        }
        return min;
    }
}

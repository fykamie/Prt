/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author eszti
 */
public class Ellenfel {
    private final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());

    public void lep( Kockak kockak){
        
        AdatbazisModosito adatbazisModosito= new AdatbazisModosito();
        List<Integer> csereSeged= new ArrayList<>();
        Integer listaIndex= 2;
        
        if(kockak.getCsereleshezKocka()< 9){
            elsoKategoriabanCserel( kockak );
        }
        
        for(int kategoriaIntervallum= 9; kategoriaIntervallum < 46; kategoriaIntervallum+= 7){
            if(intervallumbaEsik(kockak.getCsereleshezKocka(), kategoriaIntervallum, kategoriaIntervallum+6)){
                
                csereSeged.add(kockak.getEllenfelKockaiLista().get(listaIndex));
                csereSeged.add(kockak.getEllenfelKockaiLista().get(listaIndex+1));
                csereSeged.add(kockak.getEllenfelKockaiLista().get(listaIndex+2));
                csereSeged= nagyobbKategoriakbanCserel(csereSeged, kockak);
                kockak.setEgyKockaellenfelKockaibol(listaIndex, csereSeged.get(0));
                kockak.setEgyKockaellenfelKockaibol(listaIndex+1, csereSeged.get(1));
                kockak.setEgyKockaellenfelKockaibol(listaIndex+2, csereSeged.get(2));
                csereSeged.clear();
                break;
            }
            
            listaIndex+= 3;
        }
    }
    
    public boolean intervallumbaEsik(Integer ellenerozni, Integer alsoHatar, Integer felsoHatar){
        LOG.debug("ellenfél megvizsgálja intervallumba esik-e kapott kocka");
        for(int checker= alsoHatar; checker <= felsoHatar; checker++){
            if(ellenerozni == checker)
                return true;
        }
        
        return false;
    }
    
    public void elsoKategoriabanCserel( Kockak kockak ){
        int csereSeged;
        if( kockak.getCsereleshezKocka()> kockak.getEllenfelKockaiLista().get(0) ){
            csereSeged= kockak.getEllenfelKockaiLista().get(1);
            kockak.setEgyKockaellenfelKockaibol(1, kockak.getCsereleshezKocka());
            kockak.setCsereleshezKocka(csereSeged);          
        }
        else{
            csereSeged= kockak.getEllenfelKockaiLista().get(0);
            kockak.setEgyKockaellenfelKockaibol(0, kockak.getCsereleshezKocka());
            kockak.setCsereleshezKocka(csereSeged);          
        }
        LOG.debug("ellenfél cserélt kategóriában");
                  
    }
    
    public List<Integer> nagyobbKategoriakbanCserel(List<Integer> kategoriaTagjai, Kockak kockak){
        
        List<Integer> csereSegedLista= new ArrayList<>();
        csereSegedLista.addAll(kategoriaTagjai);
        csereSegedLista.add(kockak.getCsereleshezKocka());
        int max= getMax(csereSegedLista);
        int min= getMin(csereSegedLista);
        int csereSeged;
        
        if( kockak.getCsereleshezKocka()== max ){
            csereSeged= kategoriaTagjai.get(2);
            kategoriaTagjai.set(2, kockak.getCsereleshezKocka());
            kockak.setCsereleshezKocka(csereSeged);
        }
        else if( kockak.getCsereleshezKocka()== min ){
            csereSeged= kategoriaTagjai.get(0);
            kategoriaTagjai.set(0, kockak.getCsereleshezKocka());
            kockak.setCsereleshezKocka(csereSeged);
        }
        else{
            csereSeged= kategoriaTagjai.get(1);
            kategoriaTagjai.set(1, kockak.getCsereleshezKocka());
            kockak.setCsereleshezKocka(csereSeged);
        }

        LOG.debug("ellenfél cserélt kategóriában");
        return kategoriaTagjai;
    }
    
    public Integer getMax(List<Integer> keresendo){
        int max= keresendo.get(0);
        for( Integer toBeMax : keresendo ){
            if(toBeMax > max)
                max= toBeMax;
        }
        LOG.debug("ellenfél maximumot keresett");
        return max;
    }
    
    public Integer getMin(List<Integer> keresendo){
        int min= keresendo.get(0);
        for( Integer toBeMin : keresendo ){
            if(toBeMin < min)
                min= toBeMin;
        }
        LOG.debug("ellenfél minimumot keresett");
        return min;
    }
}

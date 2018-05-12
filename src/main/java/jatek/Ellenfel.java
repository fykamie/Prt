package jatek;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ellenfel {
    private final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());

    /**
     * Az elenfél új állást generál magnak.
     * Attólfuggően, hogy milyen kategóriába(első, vagy nagyobb) esik a {@code Kockak#getCsereleshezKocka()} fog a saját listájában
     * megfelelő kategóriában csélni az ellenfél.
     * 
     * @param kockak 
     */
    public void lep( Kockak kockak){
        
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
    /**
     * Ellenörzi hogy a paraméterül adott szám beleesik-e a paraméterül adott intervallumba(kategóriába).
     * 
     * @param ellenerozni ellenöriendő szám
     * @param alsoHatar kategória alsó határja
     * @param felsoHatar kategóra felső határja
     * @return igaz ha beleesik, egyébként hamis
     */
    public boolean intervallumbaEsik(Integer ellenerozni, Integer alsoHatar, Integer felsoHatar){
        LOG.info("ellenfél megvizsgálja intervallumba esik-e kapott kocka");
        for(int checker= alsoHatar; checker <= felsoHatar; checker++){
            if(ellenerozni == checker)
                return true;
        }
        
        return false;
    }
    
    /**
     * Attól függően hogy milyen nagy a {@code Kockak#getCsereleshezKocka()}, cseréli ki a saját listájában a cserélésre kapott kockát.
     * Ha a csserélésre kapott nagyobb mint a saját listájában az első kocka akkor a második helyre rakja be, egyébként az elsőre.
     * 
     * @param kockak 
     */
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
        LOG.info("ellenfél cserélt kategóriában");
                  
    }
    
    /**
     * Attól függően hogy milyen nagy a {@code Kockak#getCsereleshezKocka()}, cseréli ki a kategória tagjait és a cserélésre kapott kockát.
     * Ha a cserélésre szánt a legkisebb akkor a kategória aljára kerül, ha legnagyobb a tetejére, egyébbként középre
     * 
     * @param kategoriaTagjai
     * @param kockak
     * @return 
     */
    public List<Integer> nagyobbKategoriakbanCserel(List<Integer> kategoriaTagjai, Kockak kockak){
        
        List<Integer> csereSegedLista= new ArrayList<>();
        csereSegedLista.addAll(kategoriaTagjai);
        csereSegedLista.add(kockak.getCsereleshezKocka());
        int max= getMax(csereSegedLista);
        int min= getMin(csereSegedLista);
        int csereSeged;
        LOG.info("kapott kategória: "+ kategoriaTagjai);
        LOG.info("cseréléshez: "+ kockak.getCsereleshezKocka());
        
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

        LOG.info("Felállított kategória: "+ kategoriaTagjai);
        return kategoriaTagjai;
    }
    
    /**
     * A kapott listából kikeresi a maximumot.
     * @param keresendo
     * @return 
     */
    public Integer getMax(List<Integer> keresendo){
        return keresendo.stream().mapToInt(a -> a).max().getAsInt();
    }
    
    /**
     * A kapott listából kikeresi a minimumot.
     * @param keresendo
     * @return 
     */
    public Integer getMin(List<Integer> keresendo){
        return keresendo.stream().mapToInt(a -> a).min().getAsInt();
    }
}

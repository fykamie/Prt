package jatek;

import adatbazis.Lekerdezesek;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author eszti
 */
public class Kockak {
    
    private List<Integer> sajatKockaimLista= new ArrayList<>();
    private List<Integer> ellenfelKockaiLista= new ArrayList<>();
    private List<Integer> randomKockakLista= new ArrayList<>();
    private Integer csereleshezKocka;
    private Integer randomKocka;
    private final Sources pontok= new Sources();
    private final EntityManagerFactory emf= Persistence.createEntityManagerFactory("databaseConnection");
    private final EntityManager em;

    /**
     * Az osztály konstruktora.
     */
    public Kockak() {
        em= emf.createEntityManager();
    }
    
    /**
     * em gettere.
     * 
     * @return 
     */
    public EntityManager getManager(){
        return this.em;
    }
    
    /**
     * Álláshoz tartozó pontok gettere.
     * 
     * @return 
     */
    public Sources getPontok() {
        return pontok;
    }
    
    /**
     * Álláshoz tartozó randomKocka gettere.
     * 
     * @return 
     */
    public Integer getRandomKocka() {
        return randomKocka;
    }

    /**
     * Álláshoz tartozó randomKocka settere.
     * 
     *  
     * @param randomKocka
     */
    public void setRandomKocka(Integer randomKocka) {
        this.randomKocka = randomKocka;
    }
    
    /**
     * Álláshoz tartozó saját kockák gettere.
     * 
     * @return 
     */
    public List<Integer> getSajatKockaimLista() {
        return sajatKockaimLista;
    }

    /**
     * Álláshoz tartozó saját kockák settere.
     * 
     * @param sajatKockaimLista
     */
    public void setSajatKockaimLista(List<Integer> sajatKockaimLista) {
        this.sajatKockaimLista = sajatKockaimLista;
    }

    /**
     * Álláshoz tartozó ellenfél kockák gettere.
     * 
     * @return 
     */
    public List<Integer> getEllenfelKockaiLista() {
        return ellenfelKockaiLista;
    }

    /**
     * Álláshoz tartozó ellenfél kockák settere.
     * 
     * @param ellenfelKockaiLista
     */
    public void setEllenfelKockaiLista(List<Integer> ellenfelKockaiLista) {
        this.ellenfelKockaiLista = ellenfelKockaiLista;
    }

    /**
     * Álláshoz tartozó random kockák gettere.
     * 
     * @return 
     */
    public List<Integer> getRandomKockakLista() {
        return randomKockakLista;
    }
    
    /**
     * Álláshoz tartozó random kockák settere.
     * 
     * @param randomKockakLista
     */
    public void setRandomKockakLista(List<Integer> randomKockakLista) {
        this.randomKockakLista = randomKockakLista;
    }

    /**
     * Álláshoz tartozó cserélésre szánt kocka gettere.
     * 
     * @return 
     */
    public Integer getCsereleshezKocka() {
        return csereleshezKocka;
    }

    /**
     * Álláshoz tartozó cserélésre szánt kocka settere.
     * 
     * @param csereleshezKocka
     */
    public void setCsereleshezKocka(Integer csereleshezKocka) {
        this.csereleshezKocka = csereleshezKocka;
    }
    
    /**
     * Saját kockáimban állít be egy kockát adott helyen.
     * 
     * @param hol
     * @param mit 
     */
    public void setEgyKockaSajatKockaimbol(Integer hol, Integer mit){
        this.sajatKockaimLista.set(hol, mit);
    }
    
    
    /**
     * Ellenfél kockáiban állít be egy kockát adott helyen.
     * 
     * @param hol
     * @param mit 
     */
    public void setEgyKockaellenfelKockaibol(Integer hol, Integer mit){
        this.ellenfelKockaiLista.set(hol, mit);
    }
    
    /**
     * Random kockákban állít be egy kockát adott helyen.
     * 
     * @param hol
     * @param mit 
     */
    public void setEgyKockaRandomKockakbol(Integer hol, Integer mit){
        this.randomKockakLista.set(hol, mit);
    }
    
    /**
     * Megcseréli a saját kockáim közül kiválasztott és cserélésre szánt kockát adott helyen.
     * 
     * @param hol 
     */
    public void swapCserelniEsSajatKockaim(Integer hol){
        int seged= this.csereleshezKocka;
        csereleshezKocka= this.sajatKockaimLista.get(hol);
        this.sajatKockaimLista.set(hol, seged);
        pontok.minusz5();
        for(int listaIndex= 0; listaIndex< sajatKockaimLista.size()-1; listaIndex++){
            if( sajatKockaimLista.get(listaIndex)+1  == sajatKockaimLista.get(listaIndex+1) ){
                if(listaIndex == hol || listaIndex+1 == hol){
                    pontok.plusz15();
                }
            }
        }
        
        
    }
    
    /**
     * Megcseréli az ellenfél kockái közül kiválasztott és cserélésre szánt kockát adott helyen.
     * 
     * @param hol 
     */
    public void swapCserelniEsEllenfelKockai(Integer hol){
        int seged= this.csereleshezKocka;
        csereleshezKocka= this.ellenfelKockaiLista.get(hol);
        this.ellenfelKockaiLista.set(hol, seged);
        for(int listaIndex= 0; listaIndex< ellenfelKockaiLista.size()-1; listaIndex++){
            if( ellenfelKockaiLista.get(listaIndex)+1  == ellenfelKockaiLista.get(listaIndex+1) ){
                if(listaIndex == hol || listaIndex+1 == hol){
                    pontok.minusz5();
                }
            }
        }
        
    }
    
    /**
     * Megcseréli a random kockák közül kiválasztott és cserélésre szánt kockát adott helyen.
     * 
     * @param hol 
     */
    public void swapCserelniEsRandomKockak(Integer hol){
        int seged= this.csereleshezKocka;
        csereleshezKocka= this.randomKockakLista.get(hol);
        this.randomKockakLista.set(hol, seged);
    }
    
    /**
     * Megcseréli a random és saját kockák közül a kiválasztott kockát.
     * 
     * @param hol 
     */
    public void swapEgyRandomEsSajatKockaim(Integer hol){
        int seged= randomKocka;
        csereleshezKocka= sajatKockaimLista.get(hol);
        sajatKockaimLista.set(hol, seged);
        pontok.minusz5();
        for(int listaIndex= 0; listaIndex< sajatKockaimLista.size()-1; listaIndex++){
            if( sajatKockaimLista.get(listaIndex)+1  == sajatKockaimLista.get(listaIndex+1) ){
                if(listaIndex == hol || listaIndex+1 == hol){
                    pontok.plusz15();
                }
            }
        }
    }
    
    /**
     * Megcseréli a már előre beállítorr random és cserélésre szánt kockát.
     * 
     */
    public void swapRandomKockaEsCsereleshez(){
        int seged= randomKocka;
        randomKocka= csereleshezKocka;
        csereleshezKocka= seged;
    }
    
    /**
     * Meg vizsgálja a saját kockimat, hogy sorban vannak-e.
     * @return igaz ha sorban vannak, egyébként hamis
     */
    public boolean megvizsgalomSorbanVannakSajatKockaim(){
        for( int index= 0; index < sajatKockaimLista.size()-1 ; index++ )
            if(sajatKockaimLista.get(index) > sajatKockaimLista.get(index+1))
                return false;
        
        return true;
    }
    
    /**
     * Meg vizsgálja az ellenfél kockit, hogy sorban vannak-e.
     * @return igaz ha sorban vannak, egyébként hamis
     */
    public boolean megvizsgalomSorbanVannakEllenfelKockai(){
        for( int index= 0; index < sajatKockaimLista.size()-1 ; index++ )
            if(ellenfelKockaiLista.get(index) > ellenfelKockaiLista.get(index+1))
                return false;
        
        return true;
    }
    
    /**
     * Visszatér egy random kockával a random kockák közül.
     * 
     * @return 
     */
    public Integer randomotDob(){
        Random rn= new Random();
        int random= rn.nextInt(this.randomKockakLista.size());
        while( randomKockakLista.get(random) == csereleshezKocka )
            random= rn.nextInt(randomKockakLista.size());
        
        return randomKockakLista.remove(random);
    }
    
    /**
     * Megvizsgálja, hogy az adatbázis üres-e.
     * 
     * @return 
     */
    public boolean isAdatbazisEmpty(){
        return new Lekerdezesek(em).getAll().isEmpty();
    }
    
    /**
     * Az adatbázisba menti önmagát a {@code AdatbázisModosito#kockakAdatbazisbaMentese} segítségével.
     */
    public void adatbazisbaMent() {
        AdatbazisModosito.kockakAdatbazisbaMentese(em, this);
    }
    
    /**
     * Az adatbázisból generál egy új osztály tipusú változót amivel visszatér.
     * 
     * @return 
     */
    public Kockak datbazisbolKiszedi(){
        Kockak kocka= new Kockak();
        kocka.setCsereleshezKocka(AdatbazisModosito.csereleshezKockaAdatbazisbol(em));
        kocka.setRandomKockakLista(AdatbazisModosito.randomKockakAdatbazisbol(em));
        kocka.setEllenfelKockaiLista(AdatbazisModosito.ellenfelKockakAdatbazisbol(em));
        kocka.setSajatKockaimLista(AdatbazisModosito.sajatKockakAdatbazisbol(em));
        
        kocka.closeConnect();
        return kocka;
    }
    
    /**
     * Kitörli az állást az adatbázisból.
     */
    public void adatbazisRekordokTorlese(){
        AdatbazisModosito.mindentKiNullaz(em);
    }
    
    /**
     * Lezárja a kommunikációját.
     */
    public void closeConnect(){
        em.close();
        emf.close();
    }

    /**
     * Az osztály tartalmát stringgé alakítja.
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "{ " + "sajatKockaimLista= " + sajatKockaimLista + ", ellenfelKockaiLista= " + ellenfelKockaiLista
                    + ", randomKockakLista= " + randomKockakLista + ", csereleshezKocka= " + csereleshezKocka 
                    + ", randomKocka= " + randomKocka + ", pontok= " + pontok + " }";
    }
    
    
}

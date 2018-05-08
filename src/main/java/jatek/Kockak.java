/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public Integer getRandomKocka() {
        return randomKocka;
    }

    public void setRandomKocka(Integer randomKocka) {
        this.randomKocka = randomKocka;
    }

    public List<Integer> getSajatKockaimLista() {
        return sajatKockaimLista;
    }

    public void setSajatKockaimLista(List<Integer> sajatKockaimLista) {
        this.sajatKockaimLista = sajatKockaimLista;
    }

    public List<Integer> getEllenfelKockaiLista() {
        return ellenfelKockaiLista;
    }

    public void setEllenfelKockaiLista(List<Integer> ellenfelKockaiLista) {
        this.ellenfelKockaiLista = ellenfelKockaiLista;
    }

    public List<Integer> getRandomKockakLista() {
        return randomKockakLista;
    }

    public void setRandomKockakLista(List<Integer> randomKockakLista) {
        this.randomKockakLista = randomKockakLista;
    }

    public Integer getCsereleshezKocka() {
        return csereleshezKocka;
    }

    public void setCsereleshezKocka(Integer csereleshezKocka) {
        this.csereleshezKocka = csereleshezKocka;
    }
    
    public void setEgyKockaSajatKockaimbol(Integer hol, Integer mit){
        this.sajatKockaimLista.set(hol, mit);
    }
    
    public void setEgyKockaellenfelKockaibol(Integer hol, Integer mit){
        this.ellenfelKockaiLista.set(hol, mit);
    }
    
    public void setEgyKockaRandomKockakbol(Integer hol, Integer mit){
        this.randomKockakLista.set(hol, mit);
    }
    
    public void swapCserelniEsSajatKockaim(Integer hol){
        int seged= this.csereleshezKocka;
        csereleshezKocka= this.sajatKockaimLista.get(hol);
        this.sajatKockaimLista.set(hol, seged);
    }
    
    public void swapCserelniEsEllenfelKockai(Integer hol){
        int seged= this.csereleshezKocka;
        csereleshezKocka= this.ellenfelKockaiLista.get(hol);
        this.ellenfelKockaiLista.set(hol, seged);
    }
    
    public void swapCserelniEsRandomKockak(Integer hol){
        int seged= this.csereleshezKocka;
        csereleshezKocka= this.randomKockakLista.get(hol);
        this.randomKockakLista.set(hol, seged);
    }
    
    public void swapEgyRandomEsSajatKockaim(Integer hol){
        int seged= randomKocka;
        randomKocka= sajatKockaimLista.get(hol);
        sajatKockaimLista.set(hol, seged);
    }
    
    public void swapEgyRandomEsEllenfelKockai(Integer hol){
        int seged= randomKocka;
        randomKocka= ellenfelKockaiLista.get(hol);
        ellenfelKockaiLista.set(hol, seged);
    }
    
    public void swapRandomKockaEsCsereleshez(){
        int seged= randomKocka;
        randomKocka= csereleshezKocka;
        csereleshezKocka= randomKocka;
    }
        
    public boolean megvizsgalomSorbanVannakSajatKockaim(){
        for( int index= 0; index < sajatKockaimLista.size()-1 ; index++ )
            if(sajatKockaimLista.get(index) > sajatKockaimLista.get(index+1))
                return false;
        
        return true;
    }
    
    public boolean megvizsgalomSorbanVannakEllenfelKockai(){
        for( int index= 0; index < sajatKockaimLista.size()-1 ; index++ )
            if(ellenfelKockaiLista.get(index) > ellenfelKockaiLista.get(index+1))
                return false;
        
        return true;
    }
    
    public Integer randomotDob(){
        Random rn= new Random();
        int random= rn.nextInt(this.randomKockakLista.size());
        while( randomKockakLista.get(random) == csereleshezKocka )
            random= rn.nextInt(randomKockakLista.size());
        
        return randomKockakLista.get(random);
    }
    
}

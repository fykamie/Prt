/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

import java.util.List;
import java.util.Random;

/**
 *
 * @author eszti
 */
public class ReadyForStart {
    private Random rn= new Random();
    private Integer random;
    
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

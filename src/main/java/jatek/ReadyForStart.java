package jatek;

import adatbazis.BuildPyramid;
import adatbazis.Lekerdezesek;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadyForStart {
    private final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());
    private final Random rn= new Random();
    private Integer random;
    
    /**
     * Beállítja kezdésre az állást.
     * 
     * @param kockak
     * @return 
     */
    public Kockak makeReady(Kockak kockak){

        Lekerdezesek lekerdezes= new Lekerdezesek(kockak.getManager());
        
        AdatbazisModosito.mindentKiNullaz(kockak.getManager());
        
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
        LOG.debug("Kihez tartozik lista: "+kie);
        
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
        
        LOG.debug("Elkészült kocka: "+kocku.toString());
        return kocku;
    }
    
}
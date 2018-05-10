package jatek;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import adatbazis.BuildPyramid;
import adatbazis.Lekerdezesek;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author eszti
 */
public class FXMLController implements Initializable {
    private final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());
    
    @FXML
    private Pane pane;
    @FXML
    private Button randomCube;
    @FXML
    private Button selectedCube;
    @FXML
    private Label ownPyramidTop;
    @FXML
    private Label sellerPyramidTop;
    @FXML
    private Label littlePyramidTop;
    @FXML
    private ImageView batfarao;

    private final javafx.geometry.Rectangle2D primaryScreenBounds= Screen.getPrimary().getVisualBounds();
    private final Double screenWidth= primaryScreenBounds.getWidth();
    private final Double screenHeight= primaryScreenBounds.getHeight();

    private ArrayList<Button> ownCubesList;

    private ArrayList<Label> sellerCubesList;
    private Kockak cube= new Kockak();
    private final Ellenfel ellenfel= new Ellenfel();
    private final Sources pontjaim= new Sources();
    private final AdatbazisModosito adatbaModosito= new AdatbazisModosito();
    private final Kevero keveres= new Kevero();
    private final ReadyForStart readyForStart= new ReadyForStart();
    private final int myWidther= 6;
    private final int myLayoutX= 170;
    private boolean randomotKattintott= false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void afterInitialize(){

        batfarao.setFitHeight(screenHeight);
        batfarao.setFitWidth(screenWidth/6);
        batfarao.setLayoutX(primaryScreenBounds.getMinX()+screenWidth-batfarao.getFitWidth()-7);
        
        cube= readyForStart.makeReady(cube);

        cube.setSajatKockaimLista(Kevero.kever(cube.getSajatKockaimLista()));
        cube.setEllenfelKockaiLista(Kevero.kever(cube.getEllenfelKockaiLista()));
        
        ownCubesList= new ArrayList<>();
        sellerCubesList= new ArrayList<>();
        randomCube= new Button("random");
        selectedCube= new Button("selected");
        ownPyramidTop= new Label();
        sellerPyramidTop= new Label();
        littlePyramidTop= new Label();
        
        
        for(int i= 0; i<20; i++ ){
            Button but= new Button("ownCube"+i);
            Label lab= new Label("sellerCube"+i);
            
            but.setLayoutY(60);
            but.setOnAction(ev -> gombActionSajatKockakGombokon(ev));
            lab.setLayoutY(60);
            
            ownCubesList.add(but);
            sellerCubesList.add(lab);
            pane.getChildren().add(lab);
            pane.getChildren().add(but);
            
        }
        
        for(int i= 19; i>=0; i--){
            
            this.ownCubesList.get(i).setPrefHeight(30);
            this.sellerCubesList.get(i).setPrefHeight(30);
            this.ownCubesList.get(i).setLayoutY(this.ownCubesList.get(i).getLayoutY()+(i+1)*25);
            this.sellerCubesList.get(i).setLayoutY(this.sellerCubesList.get(i).getLayoutY()+(i+1)*25);
        }

        pane.getChildren().add(randomCube);
        pane.getChildren().add(selectedCube);
        pane.getChildren().add(ownPyramidTop);
        pane.getChildren().add(sellerPyramidTop);
        pane.getChildren().add(littlePyramidTop);
        
        modifyOwnCubesList();
        modifySellerCubesList();
        modifyRandomCube();
        modifySelectedCube();

        randomCube.setOnAction( (ev) -> gombActionRandomGombon(ev) );
        selectedCube.setOnAction( (ev) -> gombActionCsereleshezGombon(ev) );

        LOG.debug("játékhoz be- és felállítottuk a képernyőt");
    }
    
    public void gombActionSajatKockakGombokon(ActionEvent ev){
        LOG.debug("játékos sajátKockáinak gomjai közül kattintott egyre");

        this.ownCubesList.stream().forEach(a -> a.setDisable(true));
        this.selectedCube.setDisable(false);
        this.randomCube.setDisable(false);
        
        jatekosLep(ev);
        pontjaim.minusz5();
        LOG.debug("játékos lépése után pontjaiból elvettem 5-t");

        modifyOwnCubesList();
        modifySelectedCube();
        modifyRandomCube();
        
        ownCubesList.forEach(a -> a.setDisable(true));
        randomCube.setDisable(true);
        selectedCube.setDisable(true);
        
        if( EndGame.isEndGame(cube)){
            sellerCubesList.forEach(a -> a.setDisable(false));
            
            Button but= new Button();
            Label lab= new Label();
            
            but.setText("Újra");
            but.setPrefHeight(80);
            but.setPrefWidth(100);
            but.setLayoutX(screenWidth/2);
            but.setLayoutY(screenHeight/2);
            but.setBackground(Background.EMPTY);
            but.setOnAction(uev -> {
                try {
                    ujraKezdjuk(uev);
                } catch (IOException ex) {
                    LOG.debug("Nem tudtuk újrakezdeni");
                }
            });
            
            lab.setPrefHeight(80);
            lab.setPrefWidth(100);
            lab.setLayoutX(screenWidth/2);
            lab.setLayoutY(screenHeight/2 - 90);
            lab.setText(EndGame.kiNyert(cube));
            lab.setAlignment(Pos.CENTER);
            lab.setBackground(Background.EMPTY);

            pane.getChildren().add(lab);
            pane.getChildren().add(but);
            LOG.debug("A játék véget ért");
        }
        else{
            LOG.debug("játékos lépett átadta a vezérlést a gépnek");

            ellenfel.lep(cube);
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("databaseConnection");
                adatbaModosito.adatbazisbaMenitEllenfel(emf, cube);
            emf.close();
            if( EndGame.isEndGame(cube)){
                sellerCubesList.forEach(a -> a.setDisable(false));
            
                Button but= new Button();
                Label lab= new Label();

                but.setText("Újra");
                but.setAlignment(Pos.CENTER);
                but.setBackground(Background.EMPTY);
                but.setOnAction(uev -> {
                    try {
                        ujraKezdjuk(uev);
                    } catch (IOException ex) {
                        LOG.debug("Nem tudtuk újrakezdeni");
                    }
                });
                lab.setText(EndGame.kiNyert(cube));
                lab.setAlignment(Pos.CENTER);
                lab.setBackground(Background.EMPTY);

                pane.getChildren().add(lab);
                pane.getChildren().add(but);
                LOG.debug("A játék véget ért");
            }
            else{
                modifySellerCubesList();
                modifyOwnCubesList();
                modifySelectedCube();
                modifyRandomCube();

                LOG.debug("ellenfél lépett visszaadta a vezérlést a játékosnak");
            }

        }
    }
    
    private void jatekosLep(ActionEvent ev){
        Integer mitKattintott= Integer.valueOf(((Button)ev.getSource()).getText());
        Integer holKattintott= sajatbanKeresiPoziciojat(mitKattintott);
        BuildPyramid mit; 
        BuildPyramid mivel; 
        
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("databaseConnection");
        
        if(!randomotKattintott){
            adatbaModosito.adatbazisbanCserelCsereleshezEsSajatbolEgy(emf, cube.getCsereleshezKocka(), mitKattintott);
            cube.swapCserelniEsSajatKockaim(holKattintott);
            
            
            randomotKattintott= false;
        }
        else{
            cube.setRandomKocka(cube.randomotDob());
            cube.getRandomKockakLista().add(cube.getCsereleshezKocka());
            adatbaModosito.adatbazisbanCserelRandomEsSajatbolEgy(emf, cube.getRandomKocka(), mitKattintott, cube.getCsereleshezKocka());
            cube.swapEgyRandomEsSajatKockaim(holKattintott);

            randomotKattintott= false;
        }
        if(cube.getSajatKockaimLista().get(holKattintott+1).equals(mitKattintott+1) 
                || cube.getSajatKockaimLista().get(holKattintott-1).equals(mitKattintott-1)){
            pontjaim.plusz15();

            LOG.debug("pontokhoz hozzáadtunk 15-t játékos lépése után");
        }

        LOG.debug("játékos lépését véglegesítjük");
        emf.close();
    }

    private void pontokatAllit(Integer hovaRakott, Integer mitRakott){
        
    }
    private void gombActionRandomGombon(ActionEvent ev){
        this.ownCubesList.stream().forEach(a -> a.setDisable(false));
        this.selectedCube.setDisable(true);
        this.randomCube.setDisable(true);
        
        cube.setRandomKocka(cube.randomotDob());
        randomCube.setPrefWidth(this.cube.getRandomKocka()*myWidther);
        randomCube.setLayoutX(myLayoutX+325-this.randomCube.getPrefWidth()/2);
        randomCube.setText(cube.getRandomKocka().toString());
        
        randomotKattintott= true;
        
        LOG.debug("játékos a randomKocka gombjára kattintott");
    }
    
    private void gombActionCsereleshezGombon(ActionEvent ev){
        this.ownCubesList.stream().forEach(a -> a.setDisable(false));
        this.selectedCube.setDisable(true);
        this.randomCube.setDisable(true);
        this.randomotKattintott= false;
        
        LOG.debug("játékos a cseréléshezKocka gombjára kattintott");
    }
    
    private void modifySelectedCube(){
        if ( this.cube.getCsereleshezKocka()< 5) this.selectedCube.setPrefWidth(30);
            else this.selectedCube.setPrefWidth(this.cube.getCsereleshezKocka()*myWidther);
            this.selectedCube.setLayoutX(myLayoutX+325-this.selectedCube.getPrefWidth()/2);
            this.selectedCube.setText( this.cube.getCsereleshezKocka().toString());
            this.selectedCube.setLayoutY(560);
            this.selectedCube.setDisable(false);

        LOG.debug("módosult a cseréléshezKocka gombjának megjelenése");
    }
    
    private void modifyRandomCube(){      
        if ( this.cube.getCsereleshezKocka()< 5)
            this.randomCube.setPrefWidth(30);
        else
            this.randomCube.setPrefWidth(this.selectedCube.getPrefWidth()*2/3);
        this.randomCube.setLayoutX(myLayoutX+325-this.randomCube.getPrefWidth()/2);
        this.randomCube.setText( "?" );
        this.randomCube.setLayoutY(535);
        this.randomCube.setDisable(false);
            
        LOG.debug("módosult a randomKocka gombjának megjelenése");
    }
   
    private void modifySellerCubesList(){
        for(int i= 0; i<20; i++){
            if ( this.cube.getEllenfelKockaiLista().get(i) < 5) 
                this.sellerCubesList.get(i).setPrefWidth(30);
            else 
                this.sellerCubesList.get(i).setPrefWidth(this.cube.getEllenfelKockaiLista().get(i)*myWidther);
            this.sellerCubesList.get(i).setLayoutX(myLayoutX+650-this.sellerCubesList.get(i).getPrefWidth()/2);
            this.sellerCubesList.get(i).setText(this.cube.getEllenfelKockaiLista().get(i).toString());
            this.sellerCubesList.get(i).setEffect(new GaussianBlur());
        }

        LOG.debug("módosult az ellenfél kockáinak megjelenése");
    }
        
    private void modifyOwnCubesList(){
        for(int i= 19; i>=0; i--){
            if ( this.cube.getSajatKockaimLista().get(i) < 5)
                this.ownCubesList.get(i).setPrefWidth(30);
            else
                this.ownCubesList.get(i).setPrefWidth(this.cube.getSajatKockaimLista().get(i)*myWidther);
            this.ownCubesList.get(i).setLayoutX(myLayoutX-this.ownCubesList.get(i).getPrefWidth()/2);
            this.ownCubesList.get(i).setText(this.cube.getSajatKockaimLista().get(i).toString());
            this.ownCubesList.get(i).setDisable(true);
        }
        
        LOG.debug("modosult a sajátkockák megjelenése");
    }
   
    private Integer sajatbanKeresiPoziciojat(Integer minek){
        int holvan;
        for(holvan= 0; holvan< ownCubesList.size(); holvan++)
            if( Integer.valueOf(ownCubesList.get(holvan).getText()) == minek)
                break;
        
         LOG.debug("megkerestük játékos melyik kockáára kattintott");

         return holvan;
    }
    
    private void ujraKezdjuk(ActionEvent ev) throws IOException{
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage= (Stage) this.batfarao.getScene().getWindow();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        javafx.geometry.Rectangle2D primaryScreenBounds= Screen.getPrimary().getVisualBounds();
        
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setResizable(false);

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        
        fxmlLoader.<FXMLController>getController().afterInitialize();
        stage.show();
    }
}

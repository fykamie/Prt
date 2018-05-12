package jatek;

import adatbazis.BuildPyramid;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javafx.application.Platform;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GameController implements Initializable {
    private final Logger LOG= LoggerFactory.getLogger(AdatbazisModosito.class.getClass());
    
    @FXML
    private Pane pane;
    @FXML
    private Button randomCube;
    @FXML
    private Button selectedCube;
    @FXML
    private Label sourceLabel;
    @FXML
    private Button ownPyramidTop;
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
    private final AdatbazisModosito adatbaModosito= new AdatbazisModosito();
    private final Kevero keveres= new Kevero();
    private final ReadyForStart readyForStart= new ReadyForStart();
    private final int myWidther= 6;
    private final int myLayoutX= 170;
    private boolean randomotKattintott= false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    /**
     * A kezdeti képernyőt állítja be megfelelő kinézettel a játéknak.
     */
    public void afterInitialize(){
        Stage stage= (Stage) batfarao.getScene().getWindow();
        stage.setOnCloseRequest(e -> {
            cube.closeConnect();
            Platform.exit();
        });
        
        batfarao.setFitHeight(screenHeight);
        batfarao.setFitWidth(screenWidth/6);
        batfarao.setLayoutX(primaryScreenBounds.getMinX()+screenWidth-batfarao.getFitWidth()-7);
        if(cube.isAdatbazisEmpty()){
            cube= readyForStart.makeReady(cube);
        }
        else{
            cube= cube.datbazisbolKiszedi();
        }
        cube.setSajatKockaimLista(Kevero.kever(cube.getSajatKockaimLista()));
        cube.setEllenfelKockaiLista(Kevero.kever(cube.getEllenfelKockaiLista()));
        
        ownCubesList= new ArrayList<>();
        sellerCubesList= new ArrayList<>();
        randomCube= new Button("random");
        selectedCube= new Button("selected");
        ownPyramidTop= new Button();
        sellerPyramidTop= new Label();
        littlePyramidTop= new Label();
        sourceLabel= new Label();
        
        
        for(int i= 0; i<20; i++ ){
            Button but= new Button("ownCube"+i);
            Label lab= new Label("sellerCube"+i);
            
            but.setLayoutY(60);
            but.setOnAction(ev -> gombActionSajatKockakGombokon(ev) );
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
        
        this.ownPyramidTop.setPrefWidth(25);
        this.ownPyramidTop.setPrefWidth(30);
        this.ownPyramidTop.setLayoutX(myLayoutX-this.ownPyramidTop.getPrefWidth()/2);
        this.ownPyramidTop.setLayoutY(60);
     
        
        this.sellerPyramidTop.setPrefWidth(25);
        this.sellerPyramidTop.setPrefHeight(30);
        this.sellerPyramidTop.setLayoutX(myLayoutX+650-this.sellerPyramidTop.getPrefWidth()/2);
        this.sellerPyramidTop.setLayoutY(60);
        this.sellerPyramidTop.setEffect(new GaussianBlur());
        
        this.littlePyramidTop.setPrefWidth(25);
        this.littlePyramidTop.setPrefHeight(30);
        this.littlePyramidTop.setLayoutX(myLayoutX+325-this.littlePyramidTop.getPrefWidth()/2);
        this.littlePyramidTop.setLayoutY(510);
        
        this.sourceLabel.setPrefWidth(50*myWidther);
        this.sourceLabel.setPrefHeight(45);
        this.sourceLabel.setLayoutX(myLayoutX+325-this.sourceLabel.getPrefWidth()/2);
        this.sourceLabel.setLayoutY(585);

       
        pane.getChildren().add(sourceLabel);
        pane.getChildren().add(selectedCube);
        pane.getChildren().add(randomCube);
        pane.getChildren().add(ownPyramidTop);
        pane.getChildren().add(sellerPyramidTop);
        pane.getChildren().add(littlePyramidTop);
        
        modifyOwnCubesList();
        modifySellerCubesList();
        modifyRandomCube();
        modifySelectedCube();

        randomCube.setOnAction( (ev) -> gombActionRandomGombon(ev) );
        selectedCube.setOnAction( (ev) -> gombActionCsereleshezGombon(ev) );

        LOG.debug("új játékhoz be- és felállítottuk a képernyőt");
    }
    
    /**
     * Meghívja a logika cserélés metódusát előbb a játékosra, majd az ellenfélre is.
     * Ha a játékos nyert akkor nem hívja meg az ellenfelet, illetve ha vége van a játéknak akkor elhomályosít mindent 
     * és lehetőéget ad az újrakezdésre egy gomb megjelenítésével ezzel egyidőben írja ki hogy kinyert
     * 
     * @param ev 
     */
    public void gombActionSajatKockakGombokon(ActionEvent ev){
        LOG.debug("játékos sajátKockáinak gomjai közül kattintott egyre");

        this.ownCubesList.stream().forEach(a -> a.setDisable(true));
        this.selectedCube.setDisable(false);
        this.randomCube.setDisable(false);
        
        Integer mitKattintott= Integer.valueOf(((Button)ev.getSource()).getText());
        Integer holKattintott= sajatbanKeresiPoziciojat(mitKattintott);
        BuildPyramid mit; 
        BuildPyramid mivel; 
        
        
        if(!randomotKattintott){
            cube.swapCserelniEsSajatKockaim(holKattintott);
          
            randomotKattintott= false;
        }
        else{
            cube.setRandomKocka(cube.randomotDob());
            cube.getRandomKockakLista().add(cube.getCsereleshezKocka());
            cube.swapEgyRandomEsSajatKockaim(holKattintott);

            randomotKattintott= false;
        }

        LOG.debug("játékos lépését véglegesítjük");
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
            but.setPrefHeight(60);
            but.setPrefWidth(100);
            but.setLayoutX(myLayoutX+325-this.selectedCube.getPrefWidth()/2);
            but.setLayoutY(450);
            but.setBackground(Background.EMPTY);
            but.setOnAction(uev -> {
                try {
                    ujraKezdjuk(uev);
                } catch (IOException ex) {
                    LOG.debug("Nem tudtam felálítani az új játékra szánt képernyőt");
                }
            });
            
            lab.setPrefHeight(60);
            lab.setPrefWidth(100);
            but.setLayoutX(myLayoutX+325-this.selectedCube.getPrefWidth()/2);
            lab.setLayoutY(500);
            lab.setText(EndGame.kiNyert(cube));

            pane.getChildren().add(lab);
            pane.getChildren().add(but);
            
            cube.adatbazisRekordokTorlese();
            LOG.debug("A játék véget ért");
        }
        else{
            LOG.debug("játékos lépett átadta a vezérlést a gépnek");
            
//            Timer timer= new Timer();
//            timer.schedule(new TimerTask(){
//                 @Override
//                public void run() {
//                    batfarao.setImage(new Image("/images/batfarao_agyal.gif"));
//                    
//                }
//            }, 3000, 1000);
//            TimeUnit.SECONDS.sleep(3);
            ellenfel.lep(cube);
            
            cube.adatbazisbaMent();
            cube= cube.datbazisbolKiszedi();
            if( EndGame.isEndGame(cube)){
                sellerCubesList.forEach(a -> a.setDisable(false));
            
                Button but= new Button();
                Label lab= new Label();

                
                but.setText("Újra");
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
                
                cube.adatbazisRekordokTorlese();
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

    /**
     * Beállít egy randomot a logika az állásokhoz és módosul az összes gomb megjelenése.
     * 
     * @param ev 
     */
    private void gombActionRandomGombon(ActionEvent ev){
        this.ownCubesList.stream().forEach(a -> a.setDisable(false));
        this.selectedCube.setDisable(true);
        this.randomCube.setDisable(true);
        this.ownPyramidTop.setDisable(false);
        this.littlePyramidTop.setDisable(true);
        
        cube.setRandomKocka(cube.randomotDob());
        randomCube.setPrefWidth(this.cube.getRandomKocka()*myWidther);
        randomCube.setLayoutX(myLayoutX+325-this.randomCube.getPrefWidth()/2);
        randomCube.setText(cube.getRandomKocka().toString());
        
        randomotKattintott= true;
        
        LOG.debug("játékos a randomKocka gombjára kattintott");
    }
    
    /**
     * Módusol az összesgomb megjelenése.
     * @param ev 
     */
    private void gombActionCsereleshezGombon(ActionEvent ev){
        this.ownCubesList.stream().forEach(a -> a.setDisable(false));
        this.ownPyramidTop.setDisable(false);
        this.selectedCube.setDisable(true);
        this.randomCube.setDisable(true);
        this.littlePyramidTop.setDisable(true);
        this.randomotKattintott= false;
        
        LOG.debug("játékos a cseréléshezKocka gombjára kattintott");
    }
    
    /**
     * Beállítja az állás alapján a cserélésre szánt kockát megjelenító gombot.
     */
    private void modifySelectedCube(){
        if ( this.cube.getCsereleshezKocka()< 5) this.selectedCube.setPrefWidth(30);
            else this.selectedCube.setPrefWidth(this.cube.getCsereleshezKocka()*myWidther);
            this.selectedCube.setLayoutX(myLayoutX+325-this.selectedCube.getPrefWidth()/2);
            this.selectedCube.setText( this.cube.getCsereleshezKocka().toString());
            this.selectedCube.setLayoutY(560);
            this.selectedCube.setDisable(false);
            
            this.sourceLabel.setText(cube.getPontok().toString());
            this.littlePyramidTop.setDisable(false);


        LOG.debug("módosult a cseréléshezKocka gombjának megjelenése");
    }
    
    /**
     * Módosítja a random kockát megjelenító gomb megjelenését.
     */
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
   
    /**
     * Beállítja az állás alapján az ellenfél kockáit megjelenító labeleket.
     */
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
    
    /**
     * Beállítja az állás alapján a játékos kockáit megjelenító gombokat.
     */
    private void modifyOwnCubesList(){
        for(int i= 19; i>=0; i--){
            if ( this.cube.getSajatKockaimLista().get(i) < 5)
                this.ownCubesList.get(i).setPrefWidth(30);
            else
                this.ownCubesList.get(i).setPrefWidth(this.cube.getSajatKockaimLista().get(i)*myWidther);
            this.ownCubesList.get(i).setLayoutX(myLayoutX-this.ownCubesList.get(i).getPrefWidth()/2);
            this.ownCubesList.get(i).setText(this.cube.getSajatKockaimLista().get(i).toString());
            this.ownCubesList.get(i).setDisable(true);
            this.ownPyramidTop.setDisable(true);
        }
        
        LOG.debug("modosult a sajátkockák megjelenése");
    }
   
    /**
     * A logika részére megkeresi hogy a sajátgombok közül hol szeretne cserélni a játékos.
     * 
     * @param minek
     * @return 
     */
    private Integer sajatbanKeresiPoziciojat(Integer minek){
        int holvan;
        for(holvan= 0; holvan< ownCubesList.size(); holvan++)
            if( Integer.valueOf(ownCubesList.get(holvan).getText()) == minek)
                break;
        
         LOG.debug("holvan a kattintás= "+holvan);

         return holvan;
    }
    
    /**
     * Új scene-t állít fel a játék számára.
     * 
     * @param ev
     * @throws IOException 
     */
    private void ujraKezdjuk(ActionEvent ev) throws IOException{
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        Stage stage= (Stage) batfarao.getScene().getWindow();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setResizable(false);

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        
        fxmlLoader.<GameController>getController().afterInitialize();
        stage.show();
    }
}

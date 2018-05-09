package jatek;

import adatbazis.Lekerdezesek;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = fxmlLoader.load();
        
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

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
//        EntityManagerFactory emf= Persistence.createEntityManagerFactory("databaseConnection");
//        AdatbazisModosito adatbazisModosito= new AdatbazisModosito();
//        adatbazisModosito.setTableForStart(emf);
//        
//        Lekerdezesek lek= new Lekerdezesek(emf);
//        
//        System.out.println("csereleshezKocka adatban: " + lek.get(a -> a.getKihezTartozik() == 4));


    }

}

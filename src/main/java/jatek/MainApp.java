package jatek;

import adatbazis.Lekerdezesek;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
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
        EntityManagerFactory ef= Persistence.createEntityManagerFactory("databaseConnection");
        
        adatbazisModosito nullazo= new adatbazisModosito();
        nullazo.mindentKiNullaz(ef);
        nullazo.setTableForStart(ef);
        
        Lekerdezesek lekerdezesek= new Lekerdezesek(ef);
        
        
        System.out.println("sajat meret: "+ lekerdezesek.get(a -> a.getKihezTartozik() == 1 ).size());
        System.out.println("ellenfel meret: "+ lekerdezesek.get(a -> a.getKihezTartozik() == 2 ).size());
        System.out.println("random meret: "+ lekerdezesek.get(a -> a.getKihezTartozik() == 3 ).size());

        ef.close();


    }

}

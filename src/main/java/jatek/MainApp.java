package jatek;

import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


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
        
 //       fxmlLoader.<FXMLController>getController().afterInitialize();
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
        Ellenfel el= new Ellenfel();
        Kockak kockak= new Kockak();

        kockak.setCsereleshezKocka(5);
        kockak.setSajatKockaimLista(Arrays.asList(35, 22, 21, 20, 19, 18, 17, 16, 29, 33, 47, 11, 10, 39, 8, 32, 36, 38, 45, 1));
        kockak.setEllenfelKockaiLista(Arrays.asList(26, 25, 31, 13, 7, 46, 34, 48, 49, 50, 30, 2, 3, 4, 7, 9, 13, 25, 28, 31));
        kockak.setRandomKockakLista(Arrays.asList(15, 14, 23, 12, 42, 24, 44, 6, 27));
        
        List<Integer> vizsgalandoKategoriaTagjai= Arrays.asList(12, 10,  15);
        
        System.out.println("ellenfel: "+ el.nagyobbKategoriakbanCserel(vizsgalandoKategoriaTagjai, kockak));

    }

}

package jatek;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class MainApp extends Application {

    /**
     * Megjelenít egy új ablakot a játéknak és meghívja bele a "controller" modell osztályát.
     * 
     * @param stage
     * @throws Exception 
     */
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
     * A project main függvénye.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);


    }

}

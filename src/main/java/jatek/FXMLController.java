package jatek;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class FXMLController implements Initializable {
    
    @FXML
    private ImageView batfarao= new ImageView();
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void afterInitialize(){
        imageViewModifier();
    }
    
    public void imageViewModifier(){
        javafx.geometry.Rectangle2D screenBounds= Screen.getPrimary().getVisualBounds();
        
        batfarao.setFitHeight(screenBounds.getHeight());
        batfarao.setFitWidth(screenBounds.getWidth()/6);
        batfarao.setLayoutX( screenBounds.getMinX() + screenBounds.getWidth() - batfarao.getFitWidth() -7);
//        batfarao.setStyle("-fx-background-color: red");
    }
}

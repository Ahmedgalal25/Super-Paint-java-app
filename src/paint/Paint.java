
package paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Galal1
 */
public class Paint extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Paintfxml.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.setTitle("Paint App");
        stage.show();
    }
        
        /*
        stage.setTitle("Paint App");
        stage.setScene(new Scene(root));
        stage.show();
    }*/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

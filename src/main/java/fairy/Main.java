/* Source: https://se-education.org/guides/tutorials/javaFxPart4.html */

package fairy;

import java.io.IOException;

import fairy.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Fairy fairy = new Fairy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Fairy");
            fxmlLoader.<MainWindow>getController().setFairy(fairy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package fairy.ui;

import fairy.Fairy;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Fairy fairy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image fairyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Duke instance.
     */
    public void setFairy(Fairy f) {
        fairy = f;
        showFairyMessage(Gui.getGreetMessage());
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fairy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFairyDialog(response, fairyImage)
        );
        userInput.clear();

        if (fairy.shouldExit()) {
            // exit
            PauseTransition delay = new PauseTransition(Duration.millis(750));
            delay.setOnFinished(event -> {
                Stage stage = (Stage) dialogContainer.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
    }

    /**
     * Show a message sent by Fairys chatbot on GUI.
     *
     * @param message Message to be shown.
     */
    @FXML
    public void showFairyMessage(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getFairyDialog(message, fairyImage));
    }
}

package sample.controllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HighScoreViewController {

    @FXML
    private Button backButton;

    private Parent root;
    private Stage parentView;

    @FXML
    void backButtonAction( ) {
        parentView = (Stage) backButton.getScene().getWindow();

        setView(parentView, "views/WelcomeView.fxml");
    }

    private void setView(Stage stage, String location) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(location)));
        } catch (Exception exception) {

            System.exit(0);
        }

        stage.setScene(new Scene(root));
        stage.show();
    }

}

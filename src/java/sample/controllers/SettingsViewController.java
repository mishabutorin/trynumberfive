package sample.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;



public class SettingsViewController {

    @FXML
    private Button backButton;

    private Parent root;
    private Stage parentView;

    public void backButtonAction( )  {
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

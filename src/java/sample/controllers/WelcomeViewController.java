package sample.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class WelcomeViewController {

    @FXML
    private Button startButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button scoreButton;

    private Parent root;
    private Stage parentView;

    public void exit() {
        System.exit(0);
    }

    public void startButtonAction() {
        parentView = (Stage) startButton.getScene().getWindow();

        setView(parentView, "views/ChooseGameView.fxml");
    }

    public void settingsButtonAction() {

        parentView = (Stage) settingsButton.getScene().getWindow();

        setView(parentView, "views/SettingsView.fxml");
    }

    public void scoreButtonAction() {
        parentView = (Stage) scoreButton.getScene().getWindow();

        setView(parentView, "views/HighScoresView.fxml");
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
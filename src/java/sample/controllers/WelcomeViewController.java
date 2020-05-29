package sample.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.GameScene;

public class WelcomeViewController {

    @FXML
    private Button startButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button scoreButton;

    private Parent root;
    private Stage parentView;

    private long difficulty = 100_000_000;

    public void exit() {
        System.exit(0);
    }

    public void startButtonAction() {
//        AnchorPane root = new AnchorPane();
//
//        parentView = (Stage) startButton.getScene().getWindow();
//        parentView.setScene(new GameScene(root, difficulty));
//        parentView.centerOnScreen();
//        parentView.show();
        parentView = (Stage) settingsButton.getScene().getWindow();

        setView(parentView, "views/ChooseGame.fxml");
    }

    public void settingsButtonAction() {

        parentView = (Stage) settingsButton.getScene().getWindow();

        setView(parentView, "views/SettingsView.fxml");
    }

    public void scoreButtonAction() {
        parentView = (Stage) scoreButton.getScene().getWindow();

        setView(parentView, "views/ScoreView.fxml");
    }

    private void setView(Stage stage, String location) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(location));
        } catch (Exception exception) {

            System.exit(0);
        }

        stage.setScene(new Scene(root));
        stage.show();
    }
}
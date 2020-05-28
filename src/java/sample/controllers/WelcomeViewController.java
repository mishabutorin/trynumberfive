package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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


    void exit() { System.exit(0);}



    void startButtonAction()  {
        AnchorPane root = new AnchorPane();
//
//        parentView = (Stage) startButton.getScene().getWindow();
//        parentView.setScene(new GameScene(root, difficulty));
//        parentView.centerOnScreen();
//        parentView.show();
        parentView = (Stage) startButton.getScene().getWindow();

        setView(parentView, "views/ChooseGame.fxml");

    }



    void settingsButtonAction() {
        parentView = (Stage) settingsButton.getScene().getWindow();

        setView(parentView, "views/SettingsView.fxml");

    }



    void scoreButtonAction() throws IOException {
        parentView = (Stage) scoreButton.getScene().getWindow();

        setView(parentView, "views/ScoreView.fxml");

    }



   private void setView(Stage primaryStage, String location) {
       try {
           root = FXMLLoader.load(getClass().getClassLoader().getResource(location));
       } catch (Exception ex) {
           //	MyLogger.WARN(location + " file not found");
           System.exit(0);
       }

        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
   }
}

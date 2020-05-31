package sample.controllers;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseLevelController {

    private Stage parentView;
    private Parent root;


    @FXML
    private Button LevelOne;

    @FXML
    private Button LevelTwo;

    @FXML
    private Button LevelThree;

    @FXML
    private Button LevelFour;

    @FXML
    private Button BackButton;


    public void LevelOneAction() {

    }


    public void LevelTwoAction() {

    }


    public void LevelThreeAction() {

    }


    public void LevelFourAction() {

    }


    public void BackButtonAction( ) {
        parentView = (Stage) BackButton.getScene().getWindow();

        setView(parentView, "views/ChooseGameView.fxml");

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

package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.GameScene;

public class ChooseGameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SnakeGameButton;

    @FXML
    private Button SovietSnakeGameButton;

    @FXML
    private Button LevelSnakeGameButton;

    private Parent root;
    private Stage parentView;

    private long difficulty = 100_000_000;


    void SnakeGameButtonAction( ) {
        AnchorPane root = new AnchorPane();

        parentView = (Stage) SnakeGameButton.getScene().getWindow();
        parentView.setScene(new GameScene(root, difficulty));
        parentView.centerOnScreen();
        parentView.show();

    }


    @FXML
    void SovietSnakeGameButtonAction() {

    }

    @FXML
    void LevelSnakeGameButtonAction() {

    }


    @FXML
    void initialize() {
        assert SnakeGameButton != null : "fx:id=\"SnakeGameButton\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert SovietSnakeGameButton != null : "fx:id=\"SovietSnakeGameButton\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert LevelSnakeGameButton != null : "fx:id=\"LevelSnakeGameButton\" was not injected: check your FXML file 'ChooseGame.fxml'.";

    }
}

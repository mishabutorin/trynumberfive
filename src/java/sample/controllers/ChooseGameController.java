package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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

    private Stage parentView;
    private Parent root;

    private long difficulty = 100_000_000;



    public void SnakeGameButtonAction() {
        AnchorPane root = new AnchorPane();

        parentView = (Stage) SnakeGameButton.getScene().getWindow();
        parentView.setScene(new GameScene(root, difficulty));
        parentView.centerOnScreen();
        parentView.setTitle("Snake");
        parentView.show();

    }



    public void SovietSnakeGameButtonAction() {
        AnchorPane root = new AnchorPane();

        parentView = (Stage) SnakeGameButton.getScene().getWindow();
        parentView.setScene(new GameScene(root, difficulty));
        parentView.centerOnScreen();
        parentView.setTitle("Soviet Snake");
        parentView.show();

    }


    public void LevelSnakeGameButtonAction() {
        AnchorPane root = new AnchorPane();

        parentView = (Stage) SnakeGameButton.getScene().getWindow();
        parentView.setScene(new GameScene(root, difficulty));
        parentView.centerOnScreen();
        parentView.setTitle("LevelSnake");
        parentView.show();

    }

}

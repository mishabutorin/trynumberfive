package sample.controllers;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.SnakeGameScene;
import sample.ReversSnakeGameScene;

public class ChooseGameController {

    @FXML
    private Button SnakeGameButton;
    @FXML
    private Button SovietSnakeGameButton;
    @FXML
    private Button LevelSnakeGameButton;
    @FXML
    private Button BackButton;

    private Stage parentView;
    private Parent root;

    private final long difficulty = 100_000_000;

    public void SnakeGameButtonAction() {
        AnchorPane root = new AnchorPane();

        parentView = (Stage) SnakeGameButton.getScene().getWindow();
        parentView.setScene(new SnakeGameScene(root, difficulty));
        parentView.centerOnScreen();
        parentView.setTitle("Snake");
        parentView.show();
    }

    public void SovietSnakeGameButtonAction() {
        AnchorPane root = new AnchorPane();

         parentView = (Stage) SovietSnakeGameButton.getScene().getWindow();
        parentView.setScene(new ReversSnakeGameScene(root, difficulty));
        parentView.centerOnScreen();
        parentView.setTitle("Yamal Snake");
        parentView.show();

    }

    public void LevelSnakeGameButtonAction() {
        parentView = (Stage) LevelSnakeGameButton.getScene().getWindow();

        setView(parentView, "views/ChooseLevelView.fxml");
    }

    public void BackButtonAction()  {
        parentView = (Stage) BackButton.getScene().getWindow();

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


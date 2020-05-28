package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.SettingsViewController;


import java.io.IOException;
import java.util.Objects;
import java.util.prefs.Preferences;


public class App extends Application {
    public static final int Width = 500;
    public static final int Height = 500;


    //@Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = null;

        root = FXMLLoader.load((Objects.requireNonNull(getClass().getClassLoader().getResource("views/WelcomeView.fxml"))));

       // primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("images/snake_icon.png")));
        primaryStage.setTitle("Snake");
        primaryStage.setScene(new Scene(root, Width, Height));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        setDefCont();

        primaryStage.show();




    }

    private void setDefCont() {
        Preferences preferences = Preferences.userRoot().node(SettingsViewController.class.getName());

        String UP = "UP";
        preferences.put(UP, UP);
        String DOWN = "DOWN";
        preferences.put(DOWN, DOWN);
        String RIGHT = "RIGHT";
        preferences.put(RIGHT, RIGHT);
        String LEFT = "LEFT";
        preferences.put(LEFT, LEFT);

        preferences.putBoolean("renderScore", false);

    }
}

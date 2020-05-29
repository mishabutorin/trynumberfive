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
    public static final int Width = 400; //ширина окна
    public static final int Height = 400; //высота окна


    public void start(Stage primaryStage) throws IOException {
        Parent root = null; //создание сцены с корневым узлом root

        root = FXMLLoader.load((Objects.requireNonNull(getClass().getClassLoader().getResource("views/WelcomeView.fxml")))); //загрузка настроект из fxml

        primaryStage.setTitle("App"); //наименование окна
        primaryStage.setScene(new Scene(root, Width, Height)); //установка сцены для primaryStage
        primaryStage.setResizable(false); //ограничение по изменению ширины и высоты
        primaryStage.centerOnScreen(); //расположение сцены по центру экрана


        setDefCont();

        primaryStage.show(); //показ сцены




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

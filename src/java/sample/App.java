package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;


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

        primaryStage.show(); //показ сцены
    }
}

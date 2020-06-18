package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;


public class App extends Application {
    public static final int Width = 400;
    public static final int Height = 400;


    public void start(Stage primaryStage) throws IOException {
        Parent root;

        root = FXMLLoader.load((Objects.requireNonNull(getClass().getClassLoader().getResource("views/WelcomeView.fxml"))));

        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(root, Width, Height));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        primaryStage.show();
    }
}

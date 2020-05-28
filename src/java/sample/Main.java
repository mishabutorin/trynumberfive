package sample;

import com.sun.xml.internal.ws.api.pipe.Engine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main {

    //@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Canvas canvas = new Canvas(500, 500);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLACK);
       // graphicsContext;
        VBox root = new VBox();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();





    }}

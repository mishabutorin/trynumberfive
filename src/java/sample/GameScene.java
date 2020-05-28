package sample;

import com.sun.javafx.scene.traversal.Direction;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.controllers.SettingsViewController;
import sample.controllers.WelcomeViewController;

import java.io.IOException;
import java.util.Base64;
import java.util.prefs.Preferences;

public class GameScene extends Scene {
    private static final int PixelSize = 25;

    private final GraphicsContext graphicsContext;
    private Canvas canvas;


    public final int Width = 1000;
    public final int Height = 700;

    //private final myTimer timer;
    private long time;

//    private final Food food;
//    private Snake snake;

    private boolean inGame;
    private boolean paused;
    private boolean gameOver;

    private int score = 0;

   // private final Preferences preferences;

    private Label pauseLabel;
    private Label gameOverLabel;
    private Label scoreLabel;
    private Label inGameScoreLabel;


    //private final HandlerForArrows HandlerForArrows = new HandlerForArrows();

    public GameScene(Parent root, long time) {
        this(root);
        this.time = time;
    }

    public GameScene(Parent root) {
        super(root);
        Preferences preferences = Preferences.userRoot().node(SettingsViewController.class.getName());

        Canvas canvas = new Canvas(Width, Height);
        ((Pane) root).getChildren().add(canvas);

        graphicsContext = canvas.getGraphicsContext2D();

        //food = new Food(PixelSize, PixelSize);

        //timer = new myTimer();

        //  addEventHandler(KeyEvent.KEY_PRESSED, myHandlerForArrows);
        // MyHandlerForEsc myHandlerForEsc = new MyHandlerForEsc();
        //addEventHandler(KeyEvent.KEY_PRESSED, myHandlerForEsc);

        initLabels();

        initScreen();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setTime(long time) {
        this.time = time;
    }
//
//   public Snake getSnake() {
//        return snake;
//    }

    private void initLabels() {
        gameOverLabel = new Label("Game Over!");
        gameOverLabel.setLayoutX(Width / 2f - 75);
        gameOverLabel.setLayoutY(Height / 2f - 40);
        gameOverLabel.getStylesheets().add(getClass().getClassLoader().getResource("styles/overallStyle.css").toString());

        scoreLabel = new Label();
        scoreLabel.setLayoutX(Width / 2f - 125);
        scoreLabel.setLayoutY(Height / 2f - 10);
        scoreLabel.getStylesheets().add(getClass().getClassLoader().getResource("styles/overallStyle.css").toString());

        inGameScoreLabel = new Label();
        inGameScoreLabel.setLayoutX(0);
        inGameScoreLabel.setLayoutY(0);
        ((AnchorPane) getRoot()).getChildren().add(inGameScoreLabel);

    }

    private void initScreen() {
        score = 0;

        renderBackground();

        initSnake();
       // food.setRandomPosition(Width, Height);

        renderGameElements();

    }

    private void renderBackground() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, Width, Height);
    }

    private void initSnake() {
        //snake = new Snake(new Point2D(Width / 2f, Height / 2f), new Point2D(Width / 2f - PixelSize, Height / 2f), PixelSize);
    }

//    private boolean checkSnake() {
//        double posX = snake.getHead().getPosition.getX();
//        double posY = snake.getHead().getPosition().getY();
//        return posX >= Width || posX < 0 || posY > Height || posY < 0;
//    }

    private void renderGameElements() {
//        snake.render(graphicsContext);
//        food.render(graphicsContext);
//        snake.render(graphicsContext);
    }

    private void renderGameOverMessage() {
        scoreLabel.setText("Ты набрал:" + score);

        Button restartButton = new Button("Перезапустить");
        restartButton.setLayoutX(Width / 2f - 125);
        restartButton.setLayoutY(Height / 2f + 50);
        restartButton.getStylesheets().add(getClass().getClassLoader().getResource("styles/GameOverStyle.css").toString());

        Button exitButton = new Button("Exit");
        exitButton.setLayoutX(Width / 2f - 50);
        exitButton.setLayoutY(Height / 2f + 100);
        exitButton.getStylesheets().add(getClass().getClassLoader().getResource("styles/GameOverStyle.css").toString());

        Button backButton = new Button("Вернуться в меню");
        backButton.setLayoutX(Width / 2f + 30);
        backButton.setLayoutY(Height / 2f + 50);
        backButton.getStylesheets().add(getClass().getClassLoader().getResource("styles/GameOverStyle.css").toString());

        exitButton.setOnAction(event -> System.exit(0));

        restartButton.setOnAction(event -> {
            gameOver = false;
            ((AnchorPane) getRoot()).getChildren().removeAll(gameOverLabel, scoreLabel, restartButton, exitButton, backButton);
        });

        //food.setRandomPosition(Width, Height);
      //  addEventHandler(KeyEvent.KEY_PRESSED, HandlerForArrows);
        initScreen();

        backButton.setOnAction(event -> {
            Stage stage = (Stage) getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/views/WelcomeView.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        });

        ((AnchorPane) getRoot()).getChildren().addAll(gameOverLabel, scoreLabel, restartButton, exitButton, backButton);
    }
}

//    private class myTimer extends AnimationTimer {
//        private long lastUpdate = 0;
//
//        @Override
//        public void start() {
//            super.start();
//            inGame = true;
//        }
//
//        @Override
//        public void handle(long now) {
//            addEventHandler(KeyEvent.KEY_PRESSED, HandlerForArrows);
//            lastUpdate = now;
//
//            snake.move();
//            if (snake.getHead().intersecr(food)) {
//                do {
//                    food.setRandomPositin(Width, Height);
//                }
//                while (snake.intersect(food));
//                int foodPoint = 10;
//                score += foodPoint;
//                snake.grow();
//
//                inGameScoreLabel.setText("Счёт: " + score + "поинтов.");
//            }
//            renderGameElements();
//            if (snake.collide() || checkSnake()) {
//                gameOver = true;
//            }
//
//            if (gameOver) {
//                //таймер останавливается
//                this.stop();
//                renderGameOverMessage();
//            }
//        }
//    }
//
//    private class HandlerForArrows implements EventHandler<KeyEvent> {
//        @Override
//        public void handle(KeyEvent event) {
//            KeyCode keyCode = event.getCode();
//
//            String key = keyCode.toString();
//            String UP = "UP";
//            String DOWN = "DOWN";
//            String LEFT = "LEFT";
//            String RIGHT = "RIGHT";
//            if ((key.equals(preferences.get(UP, ""))
//                    || key.equals(preferences.get(DOWN, "")) || key.equals(preferences.get(LEFT, "")) || key.equals(preferences.get(RIGHT, "")) && !gameOver)
//            ) {
//                timer.start();
//            }
//            if (key.equals(preferences.get(UP, "")) && snake.getDirection() != Direction.DOWN) {
//                snake.setDirection(Direction.UP);
//            } else {
//                if (key.equals(preferences.get(DOWN, "")) && snake.getDirection() != Direction.UP) {
//                    snake.setDirection(Direction.DOWN);
//
//                } else {
//                    if (key.equals(preferences.get(LEFT, "")) && snake.getDirection() != Direction.RIGHT) {
//                        snake.setDirection(Direction.LEFT);
//                    } else {
//                        if (key.equals(preferences.get(RIGHT, "")) && snake.getDirection() != Direction.LEFT) {
//                            snake.setDirection(Direction.RIGHT);
//                        }
//                    }
//
//                }
//            }
//            removeEventHandler(KeyEvent.KEY_PRESSED, this);
//        }
//    }
//}
//

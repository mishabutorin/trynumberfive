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

import java.io.IOException;
import java.util.Objects;


public class ReverseSnakeGameScene extends Scene {
    public static final int PixelSize = 25;

    private final GraphicsContext graphicsContext;

    public final int Width = 1000;
    public final int Height = 700;

    private final myTimer timer;
    private long time;

    private final Food food;
    private ReverseSnake snake;

    private boolean gameOver;

    private int score = 0;

    private Label gameOverLabel;
    private Label scoreLabel;
    private Label GameScoreLabel;
    private Label snakeGameLabel;
    private Label pressEnterLabel;


    private final HandlerControl handlerForArrows = new HandlerControl();


    public ReverseSnakeGameScene(Parent root, long time) {
        this(root);
        this.time = time;
    }

    public ReverseSnakeGameScene(Parent root) {
        super(root);

        Canvas canvas = new Canvas(Width, Height);
        ((Pane) root).getChildren().add(canvas);

        graphicsContext = canvas.getGraphicsContext2D();

        food = new Food(PixelSize, PixelSize);

        timer = new myTimer(); //добавление таймера

        addEventHandler(KeyEvent.KEY_PRESSED, handlerForArrows); //добавление управления

        initLabels();

        initScreen();
    }

    private void initLabels() {
        snakeGameLabel = new Label("Snake Game!");
        snakeGameLabel.setLayoutX(Width / 2f - 85);
        snakeGameLabel.setLayoutY(Height / 2f - 75);
        snakeGameLabel.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles/overallStyle.css")).toString());
        pressEnterLabel = new Label("Press Enter to Start!");
        pressEnterLabel.setLayoutX(Width / 2f - 155);
        pressEnterLabel.setLayoutY(Height / 2f - 35);
        pressEnterLabel.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles/overallStyle.css")).toString());

        gameOverLabel = new Label("Game Over!");
        gameOverLabel.setLayoutX(Width / 2f - 85);
        gameOverLabel.setLayoutY(Height / 2f - 75);
        gameOverLabel.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles/overallStyle.css")).toString());

        scoreLabel = new Label();
        scoreLabel.setLayoutX(Width / 2f - 115);
        scoreLabel.setLayoutY(Height / 2f - 35);
        scoreLabel.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles/overallStyle.css")).toString());

        GameScoreLabel = new Label();
        GameScoreLabel.setLayoutX(0);
        GameScoreLabel.setLayoutY(0);
        GameScoreLabel.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles/GameOverStyle.css")).toString());
        ((AnchorPane) getRoot()).getChildren().addAll(GameScoreLabel, snakeGameLabel, pressEnterLabel);

    }

    private void initScreen() {
        //инициализация экрана
        score = 0;

        GameScoreLabel.setText("Score: " + score + " pt.");

        renderBackground();

        initSnake();


        food.setRandomPosition(Width, Height);

        renderGameElements();

    }

    private void renderBackground() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, Width, Height);
    }

    private void initSnake() {
        snake = new ReverseSnake(new Point2D(Width / 2f, Height / 2f),
                new Point2D(Width / 2f - PixelSize, Height / 2f), PixelSize);
    }

    private boolean checkSnake() {
        double posX = snake.getHead().getPosition().getX();
        double posY = snake.getHead().getPosition().getY();
        return posX >= Width || posX < 0 || posY > Height || posY < 0;
    }

    private void renderGameElements() {
        snake.render(graphicsContext);
        food.render(graphicsContext);
    }

    public void renderGameOverMessage() {
        scoreLabel.setText("Your score: " + score);

        Button restartButton = new Button("Restart");
        restartButton.setLayoutX(Width / 2f - 145);
        restartButton.setLayoutY(Height / 2f + 50);
        restartButton.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles/GameOverStyle.css")).toString());

        Button exitButton = new Button("Exit");
        exitButton.setLayoutX(Width / 2f - 30);
        exitButton.setLayoutY(Height / 2f + 100);
        exitButton.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles/GameOverStyle.css")).toString());

        Button backButton = new Button("Back");
        backButton.setLayoutX(Width / 2f + 30);
        backButton.setLayoutY(Height / 2f + 50);
        backButton.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles/GameOverStyle.css")).toString());

        exitButton.setOnAction(event -> System.exit(0)); //действие после нажатия кнопки выхода

        restartButton.setOnAction(event -> {
            gameOver = false;
            ((AnchorPane) getRoot()).getChildren().removeAll(gameOverLabel, scoreLabel, restartButton, exitButton, backButton);
            initScreen();
            ((AnchorPane) getRoot()).getChildren().addAll(pressEnterLabel, snakeGameLabel);
        });
        ((AnchorPane) getRoot()).getChildren().addAll(gameOverLabel, scoreLabel, restartButton, exitButton, backButton);

        backButton.setOnAction(event -> {
            Stage stage = (Stage) getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/ChooseGameView.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert root != null;
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        });
    }


    private class myTimer extends AnimationTimer {

        private long lastUpdate = 0;
        private long lastTime = 0;

        @Override
        public void start() {
            super.start();
        }


        @Override
        public void handle(long now) {
            if (now > lastTime + 500000000){
                lastTime = now;score++;
                food.setRandomPosition(Width, Height);
            }

            if (now - lastUpdate >= time) {
                addEventHandler(KeyEvent.KEY_PRESSED, handlerForArrows);
                lastUpdate = now;

                GameScoreLabel.setText("Score: " + score + " pt.");

                snake.move();
                if (snake.getHead().intersect(food)) {
                    while (snake.intersect(food));
                    snake.loss();
                }

            }

            renderGameElements();

            if (snake.collide() || checkSnake()) {
                gameOver = true;
                timer.stop();
            }

            if (gameOver) {
                this.stop();
                renderGameOverMessage();
            }
        }
    }

    private class HandlerControl implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            //назначение управления
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.ENTER) {
                timer.start();
                ((AnchorPane) getRoot()).getChildren().removeAll(snakeGameLabel, pressEnterLabel);
            }
            if (keyCode == KeyCode.W && snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            } else {
                if (keyCode == KeyCode.S && snake.getDirection() != Direction.UP) {
                    snake.setDirection(Direction.DOWN);
                } else {
                    if (keyCode == KeyCode.A && snake.getDirection() != Direction.RIGHT) {
                        snake.setDirection(Direction.LEFT);
                    } else {
                        if (keyCode == KeyCode.D && snake.getDirection() != Direction.LEFT) {
                            snake.setDirection(Direction.RIGHT);
                        }
                    }
                }
            }
        }
    }
}


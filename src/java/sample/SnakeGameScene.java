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

import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class SnakeGameScene extends Scene {
    public static final int PixelSize = 25;

    private final GraphicsContext graphicsContext;

    public static Audio done;

    private MediaPlayer sound;

    public final int Width = 1000;
    public final int Height = 700;

    private final myTimer timer;
    private long time;

    private final GoldenFood goldenFood;
    private final Food food;
    private Snake snake;

    private boolean inGame;
    private boolean gameOver;

    private int score = 0;

    private Label gameOverLabel;
    private Label scoreLabel;
    private Label GameScoreLabel;
    private Label snakeGameLabel;
    private Label pressEnterLabel;


    private final HandlerForArrows handlerForArrows = new HandlerForArrows();


    public SnakeGameScene(Parent root, long time) {
        this(root);
        this.time = time;
        System.out.println(time);
    }

    public SnakeGameScene(Parent root) {
        super(root);

        Canvas canvas = new Canvas(Width, Height); //создание холста
        ((Pane) root).getChildren().add(canvas); //добавление холста на сцену

        graphicsContext = canvas.getGraphicsContext2D();

        goldenFood = new GoldenFood(PixelSize, PixelSize);

        food = new Food(PixelSize, PixelSize);

        timer = new myTimer(); //добавление таймера

        addEventHandler(KeyEvent.KEY_PRESSED, handlerForArrows); //добавление управления

        initLabels(); //добавление меток

        initScreen(); //добавление начального экрана
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
        //начальный экран
        score = 0; //количество очков

        GameScoreLabel.setText("Score: " + score + " pt."); //Счётчик

        renderBackground(); //отрисовка заднего фона

        initSnake();

        goldenFood.setRandomPosition(Width, Height);

        food.setRandomPosition(Width, Height); //установка рандомной позиции на поле для еды

        renderGameElements(); //отображение змеи и яблока

    }

    private void renderBackground() {
        //отрисовка заднего фона
        graphicsContext.setFill(Color.BLACK); //заливка чёрным цветом
        graphicsContext.fillRect(0, 0, Width, Height); //создание поля(ректангла) с заданными шириной и высотой, с началом координат 0, 0
    }

    private void initSnake() {
        snake = new Snake(new Point2D(Width / 2f, Height / 2f),
                new Point2D(Width / 2f - PixelSize, Height / 2f), PixelSize); //создание змеи на поле
    }

    private boolean checkSnake() {
        //отслеживание положения змеи
        double posX = snake.getHead().getPosition().getX();
        double posY = snake.getHead().getPosition().getY();
        return posX >= Width || posX < 0 || posY > Height || posY < 0; //если змея за пределами поля
    }

    private void renderGameElements() {
        snake.render(graphicsContext); //отрисока змеи
        food.render(graphicsContext); //отрисовка еды
        if (score % 50 == 0) {
            goldenFood.render(graphicsContext);
        }
    }

    public void renderGameOverMessage() {
        //сообщение, которое выводится после окончания игры
        scoreLabel.setText("Your score: " + score); //количество очков

        Button restartButton = new Button("Restart"); //кнопка перезапуска
        restartButton.setLayoutX(Width / 2f - 145);
        restartButton.setLayoutY(Height / 2f + 50);
        restartButton.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles/GameOverStyle.css")).toString()); //стиль текста и кнопки

        Button exitButton = new Button("Exit"); //кнопка выхода
        exitButton.setLayoutX(Width / 2f - 30);
        exitButton.setLayoutY(Height / 2f + 100);
        exitButton.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles/GameOverStyle.css")).toString()); //стиль текста и кнопки

        Button backButton = new Button("Back");
        backButton.setLayoutX(Width / 2f + 30);
        backButton.setLayoutY(Height / 2f + 50);
        backButton.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles/GameOverStyle.css")).toString());

        exitButton.setOnAction(event -> System.exit(0)); //действие после нажатия кнопки выхода

        restartButton.setOnAction(event -> {
            //действие после нажатия кнопки перезапуска
            gameOver = false;
            ((AnchorPane) getRoot()).getChildren().removeAll(gameOverLabel, scoreLabel, restartButton, exitButton, backButton); // удаление всех элементов(текста и кнопок)

            initScreen(); //запуск начального экрана
            ((AnchorPane) getRoot()).getChildren().addAll(pressEnterLabel, snakeGameLabel);
        });
        ((AnchorPane) getRoot()).getChildren().addAll(gameOverLabel, scoreLabel, restartButton, exitButton, backButton); //добавление всех элементов

        backButton.setOnAction(event -> {
            Stage stage = (Stage) getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/ChooseGameView.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        });
    }


    private class myTimer extends AnimationTimer {
        //создание таймера
        private long lastUpdate = 0;

        @Override
        public void start() {
            //запуск таймера
            super.start();
            inGame = true;
        }


        @Override
        public void handle(long now) {
            if (now - lastUpdate >= time) {
                addEventHandler(KeyEvent.KEY_PRESSED, handlerForArrows);
                lastUpdate = now;

                snake.move();
                if (snake.getHead().intersect(food)) { //если голова смеи в зоне еды
                    do {
                        food.setRandomPosition(Width, Height); //еде назначается новая рандомная позиия
                    } while (snake.intersect(food)); //выполняется до момента пока игра не закончится
                    int foodPoint = 10; //количество очков за съеденную еду
                    score += foodPoint; //к счёту добавляется количество очков
                    snake.grow(); //змея растёт

                    GameScoreLabel.setText("Score: " + score + " pt."); //счётчик очков


                }
                if (snake.getHead().intersect(goldenFood)) { //если голова смеи в зоне еды
                    do {
                        goldenFood.norender(graphicsContext);
                        //еде назначается новая рандомная позиия
                    } while (snake.intersect(food)); //выполняется до момента пока игра не закончится
                    int foodPoint = 100; //количество очков за съеденную еду
                    score += foodPoint; //к счёту добавляется количество очков
                    snake.grow(); //змея растёт

                    GameScoreLabel.setText("Score: " + score + " pt."); //счётчик очков


                }
            }

            renderGameElements();

            if (snake.collide() || checkSnake()) {
                //проверка на столкновение или самопоедание
                gameOver = true;
                timer.stop();
            }

            if (gameOver) {
                this.stop(); //таймер останавливается
                renderGameOverMessage(); //выводится сообщение окончания игры
            }
        }
    }

    private class HandlerForArrows implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            //назначение управления
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.ENTER) {
                //если нажата кнопка ENTER
                timer.start(); //таймер запускается
                ((AnchorPane) getRoot()).getChildren().removeAll(snakeGameLabel, pressEnterLabel); //убирается сообщение начала игры
            }
            //назначение управления
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
            removeEventHandler(KeyEvent.KEY_PRESSED, this);
        }
    }
}


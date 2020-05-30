package sample;

import com.sun.javafx.scene.traversal.Direction;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
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


public class GameScene extends Scene {
    public static final int PixelSize = 25;

    private final GraphicsContext graphicsContext;
    private Canvas canvas;

    public final int Width = 1000;
    public final int Height = 700;

    private final myTimer timer;
    private long time;

    private Food food;
    private Snake snake;

    private boolean inGame;
    private boolean gameOver;

    private int score = 0;

    private Label gameOverLabel;
    private Label scoreLabel;
    private Label inGameScoreLabel;

    private final HandlerForArrows handlerForArrows = new HandlerForArrows();


    public GameScene(Parent root, long time) {
        this(root);
        this.time = time;
    }

    public GameScene(Parent root) {
        super(root);

        Canvas canvas = new Canvas(Width, Height);
        ((Pane) root).getChildren().add(canvas);

        graphicsContext = canvas.getGraphicsContext2D();

        food = new Food(PixelSize, PixelSize);

        timer = new myTimer();

        addEventHandler(KeyEvent.KEY_PRESSED, handlerForArrows);


        initLabels();

        initScreen();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Snake getSnake() {
        return snake;
    }

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
        //начальный экран
        score = 0; //количество очков

        inGameScoreLabel.setText("Счёт: " + score); //Счётчик

        renderBackground(); //отрисовка заднего фона

        initSnake();

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
        double posX = snake.getHead().getPosition().getX();
        double posY = snake.getHead().getPosition().getY();
        return posX >= Width || posX < 0 || posY > Height || posY < 0;
    }

    private void renderGameElements() {
        snake.render(graphicsContext); //отрисока змеи
        food.render(graphicsContext); //отрисовка еды
    }

    private void renderGameOverMessage() {
        //сообщение, которое выводится после окончания игры
        scoreLabel.setText("Ты набрал:" + score); //количество очков

        Button restartButton = new Button("Перезапустить"); //кнопка перезапуска
        restartButton.setLayoutX(Width / 2f - 125); //расположение по ширине
        restartButton.setLayoutY(Height / 2f + 50); //расположение по высоте
        restartButton.getStylesheets().add(getClass().getClassLoader().getResource("styles/GameOverStyle.css").toString()); //стиль текста и кнопки

        Button exitButton = new Button("Выйти"); //кнопка выхода
        exitButton.setLayoutX(Width / 2f + 30); //расположение по ширине
        exitButton.setLayoutY(Height / 2f + 50); //расположение по высота
        exitButton.getStylesheets().add(getClass().getClassLoader().getResource("styles/GameOverStyle.css").toString()); //стиль текста и кнопки

        exitButton.setOnAction(event -> System.exit(0)); //действие после нажатия кнопки выхода

        restartButton.setOnAction(event -> {
            //действие после нажатия кнопки перезапуска
            gameOver = false;
            ((AnchorPane) getRoot()).getChildren().removeAll(gameOverLabel, scoreLabel, restartButton, exitButton); // удаление всех элементов(текста и кнопок)

            initScreen(); //запуск начального экрана
        });


    }


    private class myTimer extends AnimationTimer {
        private long lastUpdate = 0;

        @Override
        public void start() {
            super.start();
            inGame = true;
        }

        @Override
        public void handle(long now) {
            if (now - lastUpdate >= time) {
                addEventHandler(KeyEvent.KEY_PRESSED, handlerForArrows);
                lastUpdate = now;

                snake.move();
                if (snake.getHead().intersect(food)) {
                    do {
                        food.setRandomPosition(Width, Height);
                    } while (snake.intersect(food));
                    int foodPoint = 10;
                    score += foodPoint;
                    snake.grow();

                    inGameScoreLabel.setText("Счёт: " + score + "очков.");
                }

                renderGameElements();
                if (snake.collide() || checkSnake()) {
                    gameOver = true;
                }

                if (gameOver) {
                    //таймер останавливается
                    this.stop();
                    //выводится сообщение окончания игры
                    renderGameOverMessage();
                }
            }
        }
    }


    private class HandlerForArrows implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode keyCode = event.getCode();

            String key = keyCode.toString();
            String UP = "UP";
            String DOWN = "DOWN";
            String RIGHT = "RIGHT";
            String LEFT = "LEFT";
            if ((key.equals(UP)) || key.equals(DOWN) || key.equals(LEFT) || key.equals(RIGHT) && !gameOver) {
                timer.start();
            }
            if (key.equals(UP) && snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            } else {
                if (key.equals(DOWN) && snake.getDirection() != Direction.UP) {
                    snake.setDirection(Direction.DOWN);
                } else {
                    if (key.equals(LEFT) && snake.getDirection() != Direction.RIGHT) {
                        snake.setDirection(Direction.LEFT);
                    } else {
                        if (key.equals(RIGHT) && snake.getDirection() != Direction.LEFT) {
                            snake.setDirection(Direction.RIGHT);
                        }
                    }
                }
            }
            removeEventHandler(KeyEvent.KEY_PRESSED, this);
        }
    }
}


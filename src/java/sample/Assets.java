package sample;

import javafx.scene.image.ImageView;

import java.util.Objects;

public class Assets {
    static ImageView snakeHead = new ImageView(Assets.class.getClassLoader().getResource("images/head.png").toString());
    static ImageView snakeBody = new ImageView(Assets.class.getClassLoader().getResource("images/body.png").toString());
    static ImageView apple = new ImageView(Assets.class.getClassLoader().getResource("images/apple.png").toString());
    static ImageView goldenApple = new ImageView(Objects.requireNonNull(Assets.class.getClassLoader().getResource("images/goldenApple.png")).toString());

}


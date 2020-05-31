package sample;

import javafx.scene.image.ImageView;

import java.util.Objects;

public class Assets {
    static ImageView snakeHead = new ImageView(Assets.class.getClassLoader().getResource("images/head.png").toString()); //добавление картинки головы змеи
    static ImageView snakeBody = new ImageView(Assets.class.getClassLoader().getResource("images/body.png").toString()); //добавление картинки тела змеи
    static ImageView apple = new ImageView(Assets.class.getClassLoader().getResource("images/apple.png").toString()); //добавление картинки яблока
    static ImageView sovietSnakeHead = new ImageView(Objects.requireNonNull(Assets.class.getClassLoader().getResource("images/soviethead.png")).toString());
    static ImageView sovietSnakeBody = new ImageView(Objects.requireNonNull(Assets.class.getClassLoader().getResource("images/sovietbody.png")).toString());
    static ImageView goldenApple = new ImageView(Objects.requireNonNull(Assets.class.getClassLoader().getResource("images/goldenApple.png")).toString());

}


package sample;

import javafx.scene.image.ImageView;

public class Assets {
    static ImageView snakeHead = new ImageView(Assets.class.getClassLoader().getResource("images/head.png").toString()); //добавление картинки головы змеи
    static ImageView snakeBody = new ImageView(Assets.class.getClassLoader().getResource("images/body.png").toString()); //добавление картинки тела змеи
    static ImageView apple = new ImageView(Assets.class.getClassLoader().getResource("images/apple.png").toString()); //добавление картинки яблока
}
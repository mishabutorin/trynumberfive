package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GoldenFood extends GameObject{
    public GoldenFood(double width, double height) {super(width, height);}

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(Assets.goldenApple.getImage(), position.getX(), position.getY());
    }

}

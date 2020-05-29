package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;
import static sample.GameScene.PixelSize;

public class GameObject implements Renderable{
    protected Point2D position;
    protected double width;
    protected double height;
    protected boolean alive;


    public GameObject() {
        position = new Point2D(0,0);
        alive = true;
    }

    public GameObject(double width, double height) {
        position = new Point2D(0,0);
        this.width = width;
        this.height = height;
        alive = true;
    }

    public GameObject(Point2D position, double bodySize) {
        this.position = position;
        this.width = bodySize;
        this.height = bodySize;
        alive = true;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if (alive) {
            graphicsContext.fillRect(position.getX() + 1, position.getY() + 1, width -2, height - 2);

        }
    }
    public Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), width, height);
    }

    public boolean intersect(GameObject other) {return this.getBoundary().intersects((other.getBoundary()));}

    public void setRandomPosition(int width, int height) {
        Random random = new Random();
        setPosition(new Point2D(random.nextInt((width) / PixelSize) * PixelSize,
                random.nextInt((height) / PixelSize) * PixelSize));
    }

}

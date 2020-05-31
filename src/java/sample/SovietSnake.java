package sample;


import com.sun.javafx.scene.traversal.Direction;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class SovietSnake implements Renderable {
    private final LinkedList<GameObject> body = new LinkedList<>();
    private final int bodySize;
    public GameObject tail;

    private Direction direction = Direction.RIGHT;

    GameObject gameObject = new GameObject();

    public SovietSnake(Point2D head, Point2D tail, int bodySize) {
        this.bodySize = bodySize;
        body.add(new GameObject(head, bodySize));
        body.add(new GameObject(tail, bodySize));
        body.add(new GameObject(tail, bodySize));
        body.add(new GameObject(tail, bodySize));
        body.add(new GameObject(tail, bodySize));
        body.add(new GameObject(tail, bodySize));
        body.add(new GameObject(tail, bodySize));
        body.add(new GameObject(tail, bodySize));
        body.add(new GameObject(tail, bodySize));
        body.add(new GameObject(tail, bodySize));
        body.add(new GameObject(tail, bodySize));
    }


    public GameObject getHead() {
        return body.getFirst();
    }

    public GameObject getNeck() {
        return body.get(1);
    }

    public GameObject getBody(int index) {
        return body.get(index);
    }

    public int getLength() {
        return body.size();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        tail = body.pollLast();

        Point2D newPos = null;
        switch (direction) {
            case UP:
                newPos = getHead().getPosition().subtract(0, bodySize);
                break;
            case DOWN:
                newPos = getHead().getPosition().add(0, bodySize);
                break;
            case LEFT:
                newPos = getHead().getPosition().subtract(bodySize, 0);
                break;
            case RIGHT:
                newPos = getHead().getPosition().add(bodySize, 0);
                break;
        }
        body.addFirst(new GameObject(newPos, bodySize));
    }



    public void loss() {
        body.pollFirst(); //убавление частей змейки
    }



    public boolean intersect(GameObject other) {
        for (int i = 0; i < getLength(); i++) {
            if (other.intersect(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        switch (direction) {
            case UP: {
                Assets.sovietSnakeHead.setRotate(180);
                break;
            }
            case DOWN: {
                Assets.sovietSnakeHead.setRotate(0);
                break;
            }
            case LEFT: {
                Assets.sovietSnakeHead.setRotate(90);
                break;
            }
            case RIGHT: {
                Assets.sovietSnakeHead.setRotate(-90);
                break;
            }
        }
        graphicsContext.drawImage(Assets.sovietSnakeHead.snapshot(new SnapshotParameters(), null),
                getHead().getPosition().getX() + 1,
                getHead().getPosition().getY() + 1,
                23,
                23);
        graphicsContext.drawImage(Assets.sovietSnakeBody.getImage(),
                getNeck().getPosition().getX() + 1,
                getNeck().getPosition().getY() + 1,
                23,
                23);
        if (tail != null) {
            graphicsContext.setFill(Color.FIREBRICK);
            tail.render(graphicsContext);
        }

    }

    public boolean collide() {
        for (int i = 1; i < getLength(); i++) {
            if (getHead().getPosition().equals((getBody(i).getPosition()))) {
                return true;
            }
        }
        return false;
    }
}

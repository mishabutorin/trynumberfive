package sample;


import com.sun.javafx.scene.traversal.Direction;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class Snake implements Renderable {
    private final LinkedList<MovingGameObject> body = new LinkedList<>();
    private final int bodySize;
    private MovingGameObject tail;

    private Direction direction = Direction.RIGHT;

    public Snake(Point2D head, Point2D tail, int bodySize) {
        this.bodySize = bodySize;
        body.add(new MovingGameObject(head, bodySize));
        body.add(new MovingGameObject(tail, bodySize));
    }

    public MovingGameObject getHead() {
        return body.getFirst();
    }

    public MovingGameObject getNeck() {
        return body.get(1);
    }

    public MovingGameObject getBody(int index) {
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

        switch (direction) {
            case UP: {
                Point2D newPos = getHead().getPosition().subtract(0, bodySize);
                body.addFirst(new MovingGameObject(newPos, bodySize, direction));
                break;
            }
            case DOWN: {
                Point2D newPos = getHead().getPosition().add(0, bodySize);
                body.addFirst(new MovingGameObject(newPos, bodySize, direction));
                break;
            }
            case LEFT: {
                Point2D newPos = getHead().getPosition().subtract(bodySize, 0);
                body.addFirst(new MovingGameObject(newPos, bodySize, direction));
                break;
            }
            case RIGHT: {
                Point2D newPos = getHead().getPosition().add(bodySize, 0);
                body.addFirst(new MovingGameObject(newPos, bodySize, direction));
                break;
            }
        }
    }

    public void grow() {
        body.add(new MovingGameObject());
    } //добавление частей к змейке

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
                Assets.snakeHead.setRotate(180);
                break;
            }
            case DOWN: {
                Assets.snakeHead.setRotate(0);
                break;
            }
            case LEFT: {
                Assets.snakeHead.setRotate(90);
                break;
            }
            case RIGHT: {
                Assets.snakeHead.setRotate(-90);
                break;
            }
        }
        graphicsContext.drawImage(Assets.snakeHead.snapshot(new SnapshotParameters(), null),
                getHead().getPosition().getX() + 1,
                getHead().getPosition().getY() + 1,
                23,
                23);
        graphicsContext.drawImage(Assets.snakeBody.getImage(),
                getNeck().getPosition().getX() + 1,
                getNeck().getPosition().getY() + 1,
                23,
                23);
        if (tail != null) {
            graphicsContext.setFill(Color.BLACK);
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

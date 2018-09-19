package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.Destructible;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import jdk.nashorn.internal.objects.Global;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable, Destructible {

    private Point2D heading;
    private static final int damage = 10;

    public SimpleEnemy(Pane pane) {
        super(pane);
        int speed = 0; // 1
        do {
            spawnInit(speed);
        } while (isOnEntity(getX(), getY()));
        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);

    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }

    @Override
    public void getHit() {
        destroy();
    }

    public void spawnInit(int speed) {
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    public boolean isOnEntity(double x, double y) {
        System.out.println(Globals.getGameObjects().size());
        for (GameEntity entity: Globals.getGameObjects()) {
            System.out.println(String.format("Result is: %d", (Math.abs(entity.getX() - x))));
            if (Math.abs(x - entity.getX()) < 60 &&  Math.abs(y - entity.getY()) < 60) {
                System.out.println(Math.abs(entity.getX() - x));
                return true;
            }
        }
        return false;
    }
}

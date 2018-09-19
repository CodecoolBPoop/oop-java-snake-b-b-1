package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static int laserCounter;
    private static double rotation;
    private static double xCoordinate;
    private static double yCoordinate;
    private static float speed = 3;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private static int healthValue;
    private int score;


    public void modifyScore(int value) {
        this.score += value;
    }


    public int getHealth() {
        return health;
    }

    public static int getLaserCounter() {
        return laserCounter;
    }

    public static void modifyLaser(int amount){
        laserCounter += amount;
    }
    public void modifySpeed(float amount) {
        SnakeHead.speed += amount;
    }

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        laserCounter = 0;
        setX(xc);
        setY(yc);
        health = 100;
        score = 0;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        addPart(2);
    }

    public static double getRotation() {
        return rotation;
    }


    public static double getxCoordinate() {
        return xCoordinate;
    }

    public static double getyCoordinate() {
        return yCoordinate;
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        SnakeHead.xCoordinate = getX();
        SnakeHead.yCoordinate = getY();
        rotation = getRotate();

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
            Game.gameOver(score);
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }
}

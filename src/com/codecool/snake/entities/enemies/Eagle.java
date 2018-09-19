package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Controller.Controller;
import com.codecool.snake.entities.Destructible;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;


import java.util.Random;

public class Eagle extends GameEntity implements Destructible, Animatable {
    private Point2D heading;
    private int hp;
    private int speed;
    public Eagle(Pane pane) {
        super(pane);
        Controller.eagleExists = true;
        hp = 2;
        setImage(Globals.eagle);
        speed = 1;
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        pane.getChildren().add(this);

    }

    @Override
    public void step() {
        // v1 where to go
        double snakeHeadX= SnakeHead.getxCoordinate();
        double snakeHeadY = SnakeHead.getyCoordinate();

        // v2 user
        double currentX = getX();
        double currentY = getY();

        double theta = 180.0 / Math.PI * Math.atan2(snakeHeadX - currentX, currentY - snakeHeadY);
        setRotate(theta);
        heading = Utils.directionToVector(theta,speed);
        setX(getX() + heading.getX());
        setY(getY()+ heading.getY());

    }

    @Override
    public void getHit() {
        if (hp>0){
            hp-=1;
        }else {
            Controller.eagleExists = false;
            destroy();
        }
    }
}

package com.codecool.snake.entities.projectiles;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.Node;

public class Laser extends GameEntity implements Animatable  {
    public static int speed = 10;


    public Laser(Pane pane) {
        super(pane);
        setX(SnakeHead.getxCoordinate());
        setY(SnakeHead.getyCoordinate());
        setImage(Globals.laser);
        setRotate(SnakeHead.getRotation());
        pane.getChildren().add(this);

        Point2D heading = Utils.directionToVector(getRotate(), speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void step() {
        Point2D heading = Utils.directionToVector(getRotate(), speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }
}

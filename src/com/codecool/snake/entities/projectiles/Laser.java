package com.codecool.snake.entities.projectiles;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Destructible;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Projectile;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.Node;

public class Laser extends GameEntity implements Animatable, Projectile {
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

        //check if hits destructible
        checkIfHit();


    }

    @Override
    public void hit(Destructible entity) {
        entity.getHit();
        this.destroy();
        System.out.println(entity +"hit");
    }

    @Override
    public void checkIfHit() {
        for (GameEntity entity : Globals.getGameObjects()){
            if (getBoundsInParent().intersects(entity.getBoundsInParent())){
                if (entity instanceof Destructible){
                    Destructible destructible = (Destructible) entity;
                    hit(destructible);
                }
            }
        }
    }
}

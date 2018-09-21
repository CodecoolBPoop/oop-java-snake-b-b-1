package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;


public class MousePowerup extends GameEntity implements Interactable, Animatable {

    private int speed = 0;

    public MousePowerup(Pane pane){
        super(pane);

        do {
            spawnInit();
        } while (isOnEntity(getX(), getY()));
        setImage(Globals.mouse);
        pane.getChildren().add(this);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.modifyScore(30);
        if (snakeHead.getHealth()<=90){
            snakeHead.changeHealth(5);
        }else if (snakeHead.getHealth()<100 && snakeHead.getHealth()>90){
            snakeHead.changeHealth(100 - snakeHead.getHealth());
        }
        snakeHead.addPart(10);
        destroy();
        new MousePowerup(pane);
    }

    @Override
    public String getMessage() {
        return "mouse eaten";
    }

    public void step(){
        double dir = getRotate();
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir,speed);
        setX(getX() + heading.getX());
        setY(getY()+ heading.getY());
    }

    public void spawnInit(){
        Random rnd = new Random();
        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH-50));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT-50));
    }

    public boolean isOnEntity(double x, double y){
        for (GameEntity entity: Globals.getGameObjects()) {
            if (Math.abs(x - entity.getX()) < 70 &&  Math.abs(y - entity.getY()) < 70) {
                return true;
            }
        }
        return false;
    }
}


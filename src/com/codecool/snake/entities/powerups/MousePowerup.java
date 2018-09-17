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
        setImage(Globals.mouse);
        pane.getChildren().add(this);

        Random rnd = new Random();
        Double y = rnd.nextDouble()* (Globals.WINDOW_HEIGHT-70);
        Double x = rnd.nextDouble() * (Globals.WINDOW_WIDTH-40);
        setX(x);
        setY(y);
        System.out.println("X ="+ this.getX());
        System.out.println("Y =" + this.getY());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.modifyScore(30);
        if (snakeHead.getHealth()<=90){
            snakeHead.changeHealth(5);
        }else if (snakeHead.getHealth()<100 && snakeHead.getHealth()>90){
            snakeHead.changeHealth(snakeHead.getHealth()-100);
        }
        snakeHead.addPart(1);
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
}

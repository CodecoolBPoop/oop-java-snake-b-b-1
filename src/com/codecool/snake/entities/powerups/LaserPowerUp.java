package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Controller.Controller;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class LaserPowerUp extends GameEntity implements Interactable, Animatable {
    private int timer;

    public LaserPowerUp(Pane pane){
        super(pane);
        timer = 200;
        Controller.laserPowerUpExists =true;
        System.out.println("laser created");
        setImage(Globals.laserPowerUp);
        pane.getChildren().add(this);

        Random rnd = new Random();
        Double y = rnd.nextDouble()* (Globals.WINDOW_HEIGHT-70);
        Double x = rnd.nextDouble() * (Globals.WINDOW_WIDTH-40);
        setX(x);
        setY(y);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.modifyLaser(4);
        Controller.laserPowerUpExists = false;
        destroy();
    }

    @Override
    public String getMessage() {
        return "laser aquired";
    }

    @Override
    public void step() {
        if (timer<=0){
            destroy();
            new LaserPowerUp(Globals.pane);
            timer = 400;
        }else{
            timer-=1;
        }
    }
}

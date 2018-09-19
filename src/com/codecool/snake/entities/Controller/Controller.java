package com.codecool.snake.entities.Controller;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.powerups.LaserPowerUp;
import javafx.scene.layout.Pane;

public class Controller extends GameEntity implements Animatable {

    public static boolean laserPowerUpExists;
    public int laserPoweUpTimer = 300;

    public Controller(Pane pane) {
        super(pane);
    }

    @Override
    public void step(){
        if (!laserPowerUpExists){
            laserPoweUpTimer-=1;
            if (laserPoweUpTimer<=0){
                new LaserPowerUp(Globals.pane);
                laserPoweUpTimer=300;
            }
        }
    }
}

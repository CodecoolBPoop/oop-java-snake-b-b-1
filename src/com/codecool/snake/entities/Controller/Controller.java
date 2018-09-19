package com.codecool.snake.entities.Controller;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.Eagle;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.LaserPowerUp;
import javafx.scene.layout.Pane;

public class Controller extends GameEntity implements Animatable {

    public static boolean eagleExists;
    public static boolean laserPowerUpExists;
    public int laserPoweUpTimer = 300;
    public int enemySpawnTimer = 200;
    public int eagleSpawnTimer= 400;

    public Controller(Pane pane) {
        super(pane);
        eagleExists = false;
        laserPowerUpExists = false;
    }

    @Override
    public void step(){

        //laserPowerUpTimer
        if (!laserPowerUpExists){
            laserPoweUpTimer-=1;
            if (laserPoweUpTimer<=0){
                new LaserPowerUp(Globals.pane);
                laserPoweUpTimer=300;
            }
        }

        //eagleTimer
        if (!eagleExists){
            eagleSpawnTimer-=1;
            if (eagleSpawnTimer<=0){
                new Eagle(Globals.pane);
                eagleSpawnTimer=400;
            }
        }

        //simpleEnemy Timer
        if (enemySpawnTimer<=0){
            new SimpleEnemy(Globals.pane);
            enemySpawnTimer = 200;
        }else{
            enemySpawnTimer-=1;
        }
    }
}

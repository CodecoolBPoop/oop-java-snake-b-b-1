package com.codecool.snake.entities.terrain;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Terrain extends GameEntity implements Interactable {

    public Terrain(Pane pane) {
        super(pane);
        do {
            spawnInit();
        } while (isOnEntity(getX(), getY()));
        setImage(Globals.rock1);
        pane.getChildren().add(this);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        Globals.gameLoop.stop();
        Game.gameOver(snakeHead.getScore());
    }

    @Override
    public String getMessage() {
        return "You just hit a rock";
    }
    public void spawnInit(){
        Random rnd = new Random();
        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH-50));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT-50));
        double direction = rnd.nextDouble() * 360;
    }

    public boolean isOnEntity(double x, double y){
        for (GameEntity entity: Globals.getGameObjects()) {
            if (Math.abs(x - entity.getX()) < 200 &&  Math.abs(y - entity.getY()) < 200) {
                System.out.println(Math.abs(entity.getX() - x));
                return true;
            }
        }
        return false;
    }
}

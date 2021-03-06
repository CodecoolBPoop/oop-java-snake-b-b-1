package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static Game game;
    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;
    public static Pane pane;
    public static Image snakeHead = new Image("snakehead.png");
    public static Image snakeBody = new Image("snakebody.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image mouse = new Image("myMouse.png");
    public static Image restartImage = new Image("myRestart.png");
    public static Image backgroundImage = new Image("grass-background.png");
    public static Image laser = new Image("laser.png");
    public static Image eagle = new Image("eagle.png");
    public static Image laserPowerUp = new Image("laserPowerUp.png");
    public static Image healthBarFrameImage = new Image("healthbarframe.png");
    public static Image healthBarImage = new Image("healthbar.png");
    public static Image rock1 = new Image("rock1.png");
    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;

    public static Stage primaryStage;

    public static SnakeHead getSnakeHead() {
        for (GameEntity entity : Globals.getGameObjects()) {
            if (entity instanceof SnakeHead) {
                return (SnakeHead) entity;
            }
        }
        return null;
    }
    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}

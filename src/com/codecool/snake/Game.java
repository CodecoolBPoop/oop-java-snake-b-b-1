package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.MousePowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class Game extends Pane {

    private Button restartButton = new Button("", new ImageView(Globals.restartImage));


    // if this constructor is changed, restartCase() method has to be changed the same way!
    public Game() {
        gameInit();
    }

    /*  at restart creates new object for the game because they we cleared
    *   if Game() - constructor changed, this has to be changed the same way */

    public void gameInit() {
        new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        new MousePowerup(this);
    }
    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }




    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        initButton();
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    // Event handler for the restart button
    private EventHandler<MouseEvent> onRestartHandler = e -> {
        Globals.gameLoop.stop();
        Globals.gameObjects.clear();
        getChildren().clear();
        restartCase();
        start();
    };


    // puts the eventhandler onto the restart button
    public void addMouseEventHandler() {
        restartButton.setOnMouseClicked(onRestartHandler);
    }

    // (if exist remove) creates the restart button
    public void initButton() {
        if(getChildren().contains(restartButton))
            getChildren().remove(restartButton);
        restartButton.setLayoutX(40);
        restartButton.setLayoutY(40);

        restartButton.setMaxSize(10, 10);
        restartButton.setMinSize(10, 10);

        restartButton.setStyle("-fx-background-color: transparent; ");
        getChildren().add(restartButton);
        addMouseEventHandler();
    }
}

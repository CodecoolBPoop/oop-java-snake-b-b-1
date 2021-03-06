package com.codecool.snake;

import com.codecool.snake.entities.projectiles.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game(primaryStage);
        game.gameSetter(game);
        game.setPane();
        game.addEventFilter(KeyEvent.KEY_PRESSED, event->{
            if (event.getCode() == KeyCode.SPACE) {
                if (SnakeHead.getLaserCounter()>0) {
                    System.out.println("laser fired");
                    new Laser(game);
                    SnakeHead.modifyLaser(-1);
                }else {
                    System.out.println("out of lasers");
                }
            }
        });
        game.setTableBackground(Globals.backgroundImage);
        primaryStage.setTitle("Snake Game");
        Scene scene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        scene.getStylesheets().add(com.codecool.snake.Main.class.getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        game.start();
    }

}

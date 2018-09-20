package com.codecool.snake.entities.utilities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class ScoreDisplay extends GameEntity implements Animatable {
    private Button scoreButton = new Button();

    public ScoreDisplay(Pane pane){
        super(pane);
        scoreButton.getStyleClass().add("scoreButton");
        pane.getChildren().add(scoreButton);
    }


    public void step() {
        scoreButton.setText(String.format("Score: %d", Globals.getSnakeHead().getScore()));
        scoreButton.toFront();
    }
}

package com.codecool.snake.entities.utilities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class LaserDisplay extends GameEntity implements Animatable {
    private Button scoreButton = new Button();

    public LaserDisplay(Pane pane){
        super(pane);
        scoreButton.getStyleClass().add("laserButton");
        pane.getChildren().add(scoreButton);
    }


    public void step() {
        scoreButton.setText(String.format("Laser ammo: %d", Globals.getSnakeHead().getLaserCounter()));
        scoreButton.toFront();
    }
}

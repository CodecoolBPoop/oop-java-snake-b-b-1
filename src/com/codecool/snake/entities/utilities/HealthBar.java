package com.codecool.snake.entities.utilities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import javafx.scene.layout.Pane;

public class HealthBar extends GameEntity implements Animatable {
    public HealthBar(Pane pane){
        super(pane);
        setX(880);
        setY(20);
        setFitWidth(100);
        setImage(Globals.healthBarImage);
        pane.getChildren().add(this);
    }

    public void step() {
        setFitWidth(Globals.getSnakeHead().getHealth());
    }
}

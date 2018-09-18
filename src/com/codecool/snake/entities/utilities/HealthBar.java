package com.codecool.snake.entities.utilities;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import javafx.scene.layout.Pane;

public class HealthBar extends GameEntity implements Animatable {
    public HealthBar(Pane pane){
        super(pane);
        setX(800);
        setY(40);
        setFitWidth(100);
    }

    public void step() {
        // pass
    }
}

package com.codecool.snake.entities.utilities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import javafx.scene.layout.Pane;

public class HealthBarFrame extends GameEntity implements Animatable {
    public HealthBarFrame(Pane pane){
        super(pane);
        setX(875);
        setY(15);
        setImage(Globals.healthBarFrameImage);
        pane.getChildren().add(this);
    }

    public void step() {
        // pass
    }
}
package com.codecool.snake.entities.utilities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import javafx.scene.layout.Pane;


public class HealthBar extends GameEntity implements Animatable {
    public HealthBar(Pane pane){
        super(pane);
        this.getStyleClass().add("healthBar");
        setFitWidth(100);
        setImage(Globals.healthBarImage);
        pane.getChildren().add(this);
    }


    public void step() {
        int currentHealth = Globals.getSnakeHead().getHealth();
        if (currentHealth <= 0) {
            setFitWidth(1);
        } else {
            setFitWidth(currentHealth);
        }
    }
}

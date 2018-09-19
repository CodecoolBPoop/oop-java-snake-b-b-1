package com.codecool.snake.entities;

public interface Projectile {

    void hit(Destructible entity);
    void checkIfHit();
}

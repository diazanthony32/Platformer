package com.diaza.platformer.controller;

import com.badlogic.gdx.math.Vector2;
import com.diaza.platformer.model.Enemy;

public class EnemyController {

    public static Enemy enemy;

    public static void initializeController(){

        //makes a new enemy
        enemy = new Enemy(new Vector2(5,5),51, 58, "img/enemy-barnacle.png");

    }

    public static void update(float deltatime) {

        //updates the enemy's position
        enemy.update(deltatime);

    }

}

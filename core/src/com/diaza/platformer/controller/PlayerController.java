package com.diaza.platformer.controller;

import com.badlogic.gdx.math.Vector2;
import com.diaza.platformer.model.Player;

public class PlayerController {

    public static Player player;

    public static void initializeController(){

        //makes a new player
        player = new Player(new Vector2(3,5),70, 100);

    }

    public static void update(float deltatime) {

        //updates the character position
        player.update(deltatime);

    }

}

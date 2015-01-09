package com.diaza.platformer.controller;

import com.diaza.platformer.model.Player;

public class PlayerController {

    public static Player player;

    public static void initializeController(){

        //makes a new player
        player = new Player(70, 100);

    }

    public static void update(float deltatime) {

        //updates the character position
        player.update(deltatime);

    }

}

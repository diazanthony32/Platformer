package com.diaza.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.diaza.platformer.model.Player;

public class PlayerController {

    public static Player player;

    private static final float VELOCITY = 1f;

    private static final float MAX_VELOCITY = 4f;

    public static void initializeController(){

        //makes a new player
        player = new Player(new Vector2(3,5),70, 100);

    }

    public static void update(float deltatime) {

        handleInputs();

        //updates the character position
        player.update(deltatime);

    }

    private static void handleInputs() {

        //gets the player velocity
        Vector2 velocity = player.physicsBody.getLinearVelocity();
        //gets the player position
        Vector2 position = player.physicsBody.getPosition();

        if (Math.abs(velocity.x) > MAX_VELOCITY){

            velocity.x = Math.signum(velocity.x) * MAX_VELOCITY;

            player.physicsBody.setLinearVelocity(velocity.x, velocity.y);

        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){

            player.physicsBody.applyLinearImpulse(VELOCITY, 0f, position.x, position.y, true);

        }

    }

}

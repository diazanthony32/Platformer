package com.diaza.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.diaza.platformer.model.Player;

public class PlayerController {

    public static Player player;

    private static final float VELOCITY = 1f;

    private static final float MAX_VELOCITY = 3f;

    public static void initializeController(){

        //makes a new player
        player = new Player(new Vector2(3,5),70, 100, "img/aliens.png");

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

            velocity.y = Math.signum(velocity.y) * MAX_VELOCITY;

            player.physicsBody.setLinearVelocity(velocity.x, velocity.y);

        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){

            player.physicsBody.applyLinearImpulse(VELOCITY, 0f, position.x, position.y, true);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            player.physicsBody.applyLinearImpulse(-1 * VELOCITY, 0f, position.x, position.y, true);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

            player.physicsBody.applyLinearImpulse( 0f, VELOCITY, position.x, position.y, true);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

            player.physicsBody.applyLinearImpulse( 0f, -1 * VELOCITY, position.x, position.y, true);

        }

    }

}

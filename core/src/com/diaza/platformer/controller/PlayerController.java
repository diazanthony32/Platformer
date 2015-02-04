package com.diaza.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.diaza.platformer.model.Player;

public class PlayerController {

    public static Player player;

    private static final float VELOCITY = 1f;

    private static final float MAX_VELOCITY = 4f;

    private static final float JUMP_VELOCITY = 9f;

    public static String specialAction;

    public static String movementAction;

    private enum State{

        Idle, Walk, Run, Swim, Duck, Stand, Jump, Climb, Hurt;

    }

    private static State playerState;

    public static void initializeController(){

        //makes a new player
        player = new Player(new Vector2(3,7),70, 100, "img/aliens.png");

        specialAction = "";

        movementAction = "";

        playerState = State.Idle;

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

        if (Math.abs(velocity.y) > JUMP_VELOCITY){

            velocity.y = Math.signum(velocity.y) * JUMP_VELOCITY;

            player.physicsBody.setLinearVelocity(velocity.x, velocity.y);

        }

        if (movementAction.equalsIgnoreCase("left")){

            player.physicsBody.applyLinearImpulse(-1 * VELOCITY, 0f, position.x, position.y, true);

            player.direction = "left";

        }

        if (movementAction.equalsIgnoreCase("right")){

            player.physicsBody.applyLinearImpulse(VELOCITY, 0f, position.x, position.y, true);

            player.direction = "right";

        }

        if (movementAction.equalsIgnoreCase("jump")) {

            player.physicsBody.applyLinearImpulse( 0f, VELOCITY, position.x, position.y, true);

        }

        if (movementAction.equalsIgnoreCase("climb")) {

            player.physicsBody.applyLinearImpulse( 0f, VELOCITY /2, position.x, position.y, true);

        }

        if (Math.abs(velocity.y) > 0.05){

            playerState = State.Jump;

        }

        else if (Math.abs(velocity.x)>0){

            playerState = State.Walk;

        }

        else if (Math.abs(velocity.x) <= 1 && Math.abs(velocity.y) == 0 && PlayerController.movementAction == "duck"){

            playerState = State.Duck;

        }

        else if (PlayerController.movementAction == "climb"){

            playerState = State.Climb;

        }

        else {

            playerState = State.Idle;

        }

        setCurrentAnimation();

    }

    public static void setCurrentAnimation(){

        if (player.direction.equals("right")){

            setRightAnimation();

        }
        else if (player.direction.equals("left")){

            setLeftAnimation();

        }

    }

    //if (movementAction.equalsIgnoreCase("left")){

//        player.direction = "left";

//    }

  //  if (movementAction.equalsIgnoreCase("left")){

//        player.direction = "left";

  //  }

    public static void setRightAnimation(){

        if (playerState == State.Walk){

            player.currentAnimation = "walkRight";

        }

        else if (playerState == State.Jump){

            player.currentAnimation = "jumpRight";

        }

        else if (playerState == State.Duck){

            player.currentAnimation = "duckRight";

        }

        else if (playerState == State.Climb){

            player.currentAnimation = "Climb";

        }

        else if (playerState == State.Idle){

            player.currentAnimation = "idleRight";

        }

    }

    public static void setLeftAnimation(){

        if (playerState == State.Walk){

            player.currentAnimation = "walkLeft";

        }

        else if (playerState == State.Jump){

            player.currentAnimation = "jumpLeft";

        }

        else if (playerState == State.Duck){

            player.currentAnimation = "duckLeft";

        }

        else if (playerState == State.Climb){

            player.currentAnimation = "Climb";

        }

        else if (playerState == State.Idle){

            player.currentAnimation = "idleLeft";

        }

    }

}

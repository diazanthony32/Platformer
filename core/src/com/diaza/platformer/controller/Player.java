package com.diaza.platformer.controller;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {

    public Vector2 position;
    public Animation animation;
    private float stateTime;
    public SpriteSheet spriteSheet;

    public int width;
    public int height;

    public Player() {

        //sets the position of the characters
        position = new Vector2(1,4);

        //sets the width of the tile
        width = 70;

        //sets the height of the tile
        height = 100;

        spriteSheet = new SpriteSheet("img/aliens.png", width, height);

        animation = spriteSheet.createAnimation();

        //initating the stateTime to 0
        stateTime = 0f;

    }

public void draw(Batch spriteBatch){

    //plays the animation
    spriteBatch.draw(animation.getKeyFrame(stateTime,true), position.x, position.y, width * (1/70f), height * (1/70f));

}

public void update(float deltaTime){

    //adds to the player position
    position.y += deltaTime;

    //adds to the time in stateTime
    stateTime += deltaTime;



}

}

package com.diaza.platformer.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.diaza.platformer.controller.LevelController;

import java.util.HashMap;

public class Sprite {

    public Body physicsBody;

    public Vector2 position;
    protected float stateTime;
    public SpriteSheet spriteSheet;

    public String currentAnimation;

    public float width;
    public float height;

    protected HashMap<String, Animation> animations;

    public Sprite(Vector2 position,int width, int height, String sheetPath) {

        //gets the spritesheet and determines the width and height of the unit
        spriteSheet = new SpriteSheet(sheetPath, width, height);

        //sets the position of the characters
        this.position = position;

        animations = new HashMap<String, Animation>();

        //sets the width of the tile
        this.width = width * LevelController.UNIT_SCALE;

        //sets the height of the tile
        this.height = height * LevelController.UNIT_SCALE;

        //initating the stateTime to 0
        stateTime = 0f;

    }

    public void draw(Batch spriteBatch){

        //plays the animation
        spriteBatch.draw(animations.get(currentAnimation).getKeyFrame(stateTime,true), position.x, position.y, width, height);

    }

    public void update(float deltaTime){

        //adds to the time in stateTime
        stateTime += deltaTime;

    }

}

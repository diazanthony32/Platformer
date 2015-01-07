package com.diaza.platformer.controller;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.diaza.platformer.view.GameScreen;

import java.awt.Polygon;
import java.util.HashMap;

public class Player {

    public Vector2 position;
    private float stateTime;
    public SpriteSheet spriteSheet;

    public String currentAnimation;

    public int width;
    public int height;

    private HashMap<String, Animation> animations;

    public Player() {

        //sets the position of the characters
        position = new Vector2(0,5);

        animations = new HashMap<String, Animation>();

        //sets the width of the tile
        width = 70;

        //sets the height of the tile
        height = 100;


        //gets the spritesheet and determines the width and height of the unit
        spriteSheet = new SpriteSheet("img/aliens.png", width, height);


        //Stand Animation
        animations.put("Stand", spriteSheet.createAnimation(21, 21, 0.25f));

        //Climbing Animation
        animations.put("Climb", spriteSheet.createAnimation(22, 23, 0.25f));

        //Damage Animations
        animations.put("damageRight", spriteSheet.createAnimation(24, 25, 0.25f));
        animations.put("damageLeft", spriteSheet.flipAnimation(animations.get("damageRight"),true,false));

        //Damage Animations
        animations.put("landRight", spriteSheet.createAnimation(26, 26, 0.25f));
        animations.put("landLeft", spriteSheet.flipAnimation(animations.get("landRight"),true,false));

        //Jump Animations
        animations.put("jumpRight", spriteSheet.createAnimation(27, 27, 0.25f));
        animations.put("jumpLeft", spriteSheet.flipAnimation(animations.get("jumpRight"),true,false));

        //Idle Animations
        animations.put("idleRight", spriteSheet.createAnimation(28, 28, 0.25f));
        animations.put("idleLeft", spriteSheet.flipAnimation(animations.get("idleRight"),true,false));

        //Swimming Animations
        animations.put("swimRight", spriteSheet.createAnimation(29, 30, 0.25f));
        animations.put("swimLeft", spriteSheet.flipAnimation(animations.get("swimRight"),true,false));

        //Walking Animations
        animations.put("walkRight", spriteSheet.createAnimation(31, 32, 0.25f));
        animations.put("walkLeft", spriteSheet.flipAnimation(animations.get("walkRight"),true,false));

        //initating the stateTime to 0
        stateTime = 0f;

        currentAnimation = "swimLeft";

        //makes the body properties
        BodyDef bodyDefinition = new BodyDef();

        //sets the body to be able to be dynamic
        bodyDefinition.type = BodyDef.BodyType.DynamicBody;

        //sets the postion of the player
        bodyDefinition.position.set(position);

        //attaches the body def to the body
        Body playerBody = GameScreen.gameWorld.createBody(bodyDefinition);
        playerBody.setUserData(this);

        //creates the shape
        PolygonShape rectangleShape = new PolygonShape();

        rectangleShape.setAsBox(width / 2f, height / 2f, new Vector2(width / 2f, height / 2f), 0f);

        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = rectangleShape;

        playerBody.createFixture(fixtureDefinition);
        rectangleShape.dispose();

    }

public void draw(Batch spriteBatch){

    //plays the animation
    spriteBatch.draw(animations.get(currentAnimation).getKeyFrame(stateTime,true), position.x, position.y, width * (1/70f), height * (1/70f));



}

public void update(float deltaTime){

    //adds to the time in stateTime
    stateTime += deltaTime;



}

}

package com.diaza.platformer.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.diaza.platformer.controller.LevelController;

public class Player extends Sprite{

    public Player(Vector2 position,int width, int height, String sheetPath) {

        super(position, width, height, sheetPath);

        //Stand Animation
        animations.put("Stand", spriteSheet.createAnimation(22, 22, 0.25f));

        //Climbing Animation
        animations.put("Climb", spriteSheet.createAnimation(23, 24, 0.25f));

        //Damage Animations
        animations.put("duckRight", spriteSheet.createAnimation(25, 25, 0.25f));
        animations.put("duckLeft", spriteSheet.flipAnimation(animations.get("duckRight"),true,false));

        //Damage Animations
        animations.put("damageRight", spriteSheet.createAnimation(26, 26, 0.25f));
        animations.put("damageLeft", spriteSheet.flipAnimation(animations.get("damageRight"),true,false));

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

        //makes the body properties
        BodyDef bodyDefinition = new BodyDef();

        //sets the body to be able to be dynamic
        bodyDefinition.type = BodyDef.BodyType.DynamicBody;

        //sets the position of the player
        bodyDefinition.position.set(position);

        //attaches the body def to the body
        physicsBody = LevelController.gameWorld.createBody(bodyDefinition);
        physicsBody.setUserData(this);

        physicsBody.setFixedRotation(true);

        //creates the shape
        PolygonShape rectangleShape = new PolygonShape();

        rectangleShape.setAsBox(this.width / 2f, this.height / 2f, new Vector2(this.width / 2f, this.height / 2f), 0f);

        PolygonShape sensorShape = new PolygonShape();
        sensorShape.setAsBox(this.width/2.5f, this.height/32, new Vector2(this.width/2, 0f), 0f);

        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = rectangleShape;

        FixtureDef fixtureDefinitionSensor = new FixtureDef();
        fixtureDefinitionSensor.shape = sensorShape;

        fixtureDefinitionSensor.isSensor = true;

        physicsBody.createFixture(fixtureDefinition);
        physicsBody.createFixture(fixtureDefinitionSensor);

        rectangleShape.dispose();
        sensorShape.dispose();

        currentAnimation = "walkRight";

    }

    public void draw(Batch spriteBatch){

        super.draw(spriteBatch);

    }

    public void update(float deltaTime){

        super.update(deltaTime);

    }

}

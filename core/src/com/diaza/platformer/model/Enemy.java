package com.diaza.platformer.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.diaza.platformer.controller.LevelController;

public class Enemy extends Sprite{

    public Enemy(Vector2 position,int width, int height, String sheetPath) {

        super(position, width, height, sheetPath);

        //Stand Animation
        animations.put("Idle", spriteSheet.createAnimation(0, 1, 0.25f));

        //Damage Animations
        animations.put("Damage", spriteSheet.createAnimation(2, 3, 0.05f));

        //makes the body properties
        BodyDef bodyDefinition = new BodyDef();

        //sets the body to be able to be dynamic
        bodyDefinition.type = BodyDef.BodyType.DynamicBody;

        //sets the position of the player
        bodyDefinition.position.set(position);

        //attaches the body def to the body
        physicsBody = LevelController.gameWorld.createBody(bodyDefinition);
        physicsBody.setUserData(this);

        //creates the shape
        PolygonShape rectangleShape = new PolygonShape();

        rectangleShape.setAsBox(this.width / 2f, this.height / 2f, new Vector2(this.width / 2f, this.height / 2f), 0f);

        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = rectangleShape;

        physicsBody.createFixture(fixtureDefinition);
        rectangleShape.dispose();

        currentAnimation = "Damage";

    }

}

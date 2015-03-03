package com.diaza.platformer.model;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.diaza.platformer.controller.PlayerController;

public class CollisionListener implements ContactListener {

    public static Player player;
    public static void initializeCollisionListener(Player playerObject){
        player = playerObject;
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Fixture playerFixture = null;
        Fixture entityFixture = null;

        Object FixtureDataA =  fixtureA.getBody().getUserData();
        Object FixtureDataB = fixtureB.getBody().getUserData();

        if(FixtureDataA != null){
            playerFixture = fixtureA;
            entityFixture = fixtureB;
        }
        if(FixtureDataB != null){
            playerFixture = fixtureB;
            entityFixture = fixtureA;

        }
        if(entityFixture.getFilterData().categoryBits == 2){
            System.out.println("Game Over");
        } else if (entityFixture.getFilterData().categoryBits == 3) {
            System.out.println("message");
        }
        boolean sensorA = fixtureA.isSensor();
        boolean sensorB = fixtureB.isSensor();

        if(sensorA || sensorB) {
            PlayerController.grounded = true;
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
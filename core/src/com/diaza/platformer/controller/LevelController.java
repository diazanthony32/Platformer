package com.diaza.platformer.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.diaza.platformer.model.Level;
import com.diaza.platformer.model.Player;
import com.diaza.platformer.model.Sprite;

public class LevelController {

    public static final float UNIT_SCALE = 1/70f;

    public static Level level;

    public static OrthogonalTiledMapRenderer renderer;

    public static Batch spriteBatch;

    public static World gameWorld;

    public static Array<Body> worldBodies;

    private static Box2DDebugRenderer debugRenderer;

    public static void initializeController() {

        level = new Level("map/Level_01.tmx");

        //renders the map (1/70 is the unit to pixel size)
        renderer = new OrthogonalTiledMapRenderer(level.map, UNIT_SCALE);
        gameWorld = new World(new Vector2(0,-10), true);
        worldBodies = new Array<Body>();
        debugRenderer = new Box2DDebugRenderer();

        //creates a new batch of sprites
        spriteBatch = renderer.getSpriteBatch();

    }

    public static void draw() {

        spriteBatch.begin();
        PlayerController.player.draw(spriteBatch);
        spriteBatch.end();

        debugRenderer.render(gameWorld, CameraController.camera.combined);

    }

    public static void update(){

        //sets the view to the camera
        renderer.setView(CameraController.camera);

        //renders the render
        renderer.render();

        //makes the game run at 60fps
        gameWorld.step(1/60f, 1, 1);

        updateWorldBodies();

    }

    private static void updateWorldBodies() {

        //so it gets the most recent change of the world bodies
        worldBodies.clear();

        //gets the bodies
        gameWorld.getBodies(worldBodies);

        for (Body body : worldBodies){

            //grabs the data from Player.java
            Sprite playerBody = (Sprite)body.getUserData();
            //connects the position of the body(Sprite) to the box
            playerBody.position = body.getPosition();

        }

    }

}

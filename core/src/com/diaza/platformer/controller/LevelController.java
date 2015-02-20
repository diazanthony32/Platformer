package com.diaza.platformer.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.diaza.platformer.model.Bodies;
import com.diaza.platformer.model.CollisionListener;
import com.diaza.platformer.model.Level;
import com.diaza.platformer.model.Sprite;

public class LevelController {

    public static final float UNIT_SCALE = 1/70f;

    public static Level level;

    public static OrthogonalTiledMapRenderer renderer;

    public static Batch spriteBatch;

    public static World gameWorld;

    public static Array<Body> worldBodies;

    public static Array<Sprite> worldSprites;

    private static Box2DDebugRenderer debugRenderer;

    public static void initializeController() {

        level = new Level("map/Level_01.tmx");

        //renders the map (1/70 is the unit to pixel size)
        renderer = new OrthogonalTiledMapRenderer(level.map, UNIT_SCALE);
        gameWorld = new World(new Vector2( 0, -10f), true);
        worldBodies = new Array<Body>();
        worldSprites = new Array<Sprite>();
        debugRenderer = new Box2DDebugRenderer();

        gameWorld.setContactListener(new CollisionListener());

        //creates a new batch of sprites
        spriteBatch = renderer.getSpriteBatch();

        createLevelBodies();

        MusicController.play("music");

    }

    public static void draw() {

        spriteBatch.setProjectionMatrix(CameraController.camera.combined);

        spriteBatch.begin();

        PlayerController.player.draw(spriteBatch);

        EnemyController.enemy.draw(spriteBatch);

        for (Sprite sprite : worldSprites){

            sprite.draw(spriteBatch);

        }

        spriteBatch.end();

        spriteBatch.setProjectionMatrix(CameraController.inputCamera.combined);

        InputController.draw(spriteBatch);

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
            Sprite spriteBody = (Sprite)body.getUserData();

            if (spriteBody != null){
                spriteBody.position = body.getPosition();
            }

        }

    }

    private static void createLevelBodies() {

        MapObjects mapObjects = level.getMapObjects(level.getMapLayer("collision"));

        for (MapObject mapObject : mapObjects) {

            Bodies.createBody(mapObject);

        }

        MapObjects dynamicMapObjects = level.getMapObjects(level.getMapLayer("blocks"));

        for (MapObject mapObject : dynamicMapObjects) {

            Bodies.createBody(mapObject);


        }

    }

}

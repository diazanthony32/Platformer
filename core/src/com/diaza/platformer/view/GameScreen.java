package com.diaza.platformer.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.diaza.platformer.controller.Player;

public class GameScreen implements Screen{

    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera;

    public Batch spriteBatch;
    public Player player;

    public static World gameWorld;
    private Box2DDebugRenderer debugRenderer;

    public GameScreen() {

        //loads up the map from assets folder
        map = new TmxMapLoader().load("map/Level_01.tmx");

        //renders the map (1/70 is the unit to pixel size)
        renderer = new OrthogonalTiledMapRenderer(map, 1/70f);
        gameWorld = new World(new Vector2(0,-10), true);
        debugRenderer = new Box2DDebugRenderer();

        //gets the width of the window
        float width = Gdx.graphics.getWidth();

        //gets the height of the window
        float height = Gdx.graphics.getHeight();

        //creates a camera with a width and length of 14*14 units with the proper height and width alignment
        camera = new OrthographicCamera(14f, 14f * (height/width));

        //changes the position of the camera to align with the bottom left corner of screen
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);

        //creates a new batch of sprites
        spriteBatch = renderer.getSpriteBatch();

        //makes a new player
        player = new Player();

    }


    //always running during the game
    @Override
    public void render(float delta) {

        //setting the color of the background
        Gdx.gl.glClearColor(0.80f,0.95f,1.00f,1f);

        //clearing the game screen and setting it to our color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //when camera position changes it updates
        camera.update();

        //sets the view to the camera
        renderer.setView(camera);

        //renders the render
        renderer.render();

        //updates the character position
        player.update(delta);

        spriteBatch.begin();
        player.draw(spriteBatch);
        spriteBatch.end();

        debugRenderer.render(gameWorld, camera.combined);

    }

    //runs every time window is re-sized
    @Override
    public void resize(int width, int height) {

        //sets the width of the camera to the correct size
        camera.viewportWidth = 14f;

        //sets the correct height of the camera to the correct size1
        camera.viewportHeight = 14f * height / width;

        //updates the camera
        camera.update();

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}

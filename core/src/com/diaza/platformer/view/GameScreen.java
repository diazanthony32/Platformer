package com.diaza.platformer.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.diaza.platformer.controller.CameraController;
import com.diaza.platformer.controller.LevelController;
import com.diaza.platformer.controller.PlayerController;
import com.diaza.platformer.model.Player;

public class GameScreen implements Screen{

    public GameScreen() {

        LevelController.initializeController();

        CameraController.initializeController();

        PlayerController.initializeController();

    }


    //always running during the game
    @Override
    public void render(float delta) {

        //setting the color of the background
        Gdx.gl.glClearColor(0.80f,0.95f,1.00f,1f);

        //clearing the game screen and setting it to our color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        CameraController.update();

        LevelController.update();

        PlayerController.update(delta);

        LevelController.draw();
    }

    //runs every time window is re-sized
    @Override
    public void resize(int width, int height) {

        CameraController.resize(width, height);

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

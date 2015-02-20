package com.diaza.platformer.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.diaza.platformer.controller.CameraController;
import com.diaza.platformer.controller.EnemyController;
import com.diaza.platformer.controller.LevelController;
import com.diaza.platformer.controller.MusicController;
import com.diaza.platformer.controller.PlayerController;
import com.diaza.platformer.controller.InputController;

public class GameScreen implements Screen{

    public GameScreen() {

        MusicController.initializeMusicController();

        LevelController.initializeController();

        CameraController.initializeController();

        PlayerController.initializeController();

        EnemyController.initializeController();

        InputController.initializeController();

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

        EnemyController.update(delta);

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

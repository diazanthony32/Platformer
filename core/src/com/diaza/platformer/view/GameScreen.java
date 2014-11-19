package com.diaza.platformer.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen implements Screen{

    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera;

    public GameScreen() {

        //loads up the map from assets folder
        map = new TmxMapLoader().load("map/Level_01.tmx");

        //renders the map (1/70 is the unit to pixel size)
        renderer = new OrthogonalTiledMapRenderer(map, 1/70f);

        //creates a camera with a width and length of 14*14 units
        camera = new OrthographicCamera(14f, 14f);

        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);

    }


    //always running during the game
    @Override
    public void render(float delta) {

        //when camera position changes it updates
        camera.update();

        //sets the view to the camera
        renderer.setView(camera);

        //renders the render
        renderer.render();

    }

    @Override
    public void resize(int width, int height) {

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

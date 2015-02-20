package com.diaza.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.diaza.platformer.model.Level;
import com.diaza.platformer.model.Player;

public class CameraController {

    public static OrthographicCamera camera;
    public static OrthographicCamera inputCamera;

    public static float widthScale;
    public static float heightScale;

    public static void initializeController() {

        //gets the width of the window
        float width = Gdx.graphics.getWidth();

        //gets the height of the window
        float height = Gdx.graphics.getHeight();

        //creates a camera with a width and length of 14*14 units with the proper height and width alignment
        camera = new OrthographicCamera(14f, 14f * (height/width));

        inputCamera = new OrthographicCamera(14f, 14f * (height/width));

        //changes the position of the camera to align with the bottom left corner of screen
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);

        inputCamera.position.set(inputCamera.viewportWidth /2f, inputCamera.viewportHeight / 2, 0f);

        inputCamera.update();

    }

    public static void update() {

        float positionX = MathUtils.clamp( PlayerController.player.position.x, inputCamera.viewportWidth /2f, 80f);

        float positionY = MathUtils.clamp(PlayerController.player.position.y, inputCamera.viewportHeight /2f, 20f);

        camera.position.set(positionX, positionY, 0f);

        //when camera position changes it updates
        camera.update();

    }

    public static void resize(int width, int height) {

        //sets the width of the camera to the correct size
        camera.viewportWidth = 14f;

        //sets the correct height of the camera to the correct size1
        camera.viewportHeight = 14f * height / width;

        inputCamera.viewportWidth = 14f;

        inputCamera.viewportHeight = 14f * height/width;

        inputCamera.position.set(inputCamera.viewportWidth /2f, inputCamera.viewportHeight / 2, 0f);

        //updates the camera
        camera.update();

        inputCamera.update();

        widthScale = width / inputCamera.viewportWidth * LevelController.UNIT_SCALE;

        heightScale = height / inputCamera.viewportHeight * LevelController.UNIT_SCALE;

    }

}

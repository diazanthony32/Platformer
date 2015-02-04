package com.diaza.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.diaza.platformer.model.InputControl;
import com.diaza.platformer.model.SpriteSheet;

import java.util.ArrayList;

public class InputController {

    private static SpriteSheet spriteSheet;
    private static SpriteSheet spriteSheet1;
    private static SpriteSheet spriteSheet2;

    private static ArrayList<InputControl> inputControls;

    private static InputControl left;
    private static InputControl right;
    private static InputControl jump;
    private static InputControl down;
    private static InputControl up;

    //private static InputControl icon;
    //private static InputControl hp1;
    //private static InputControl hp2;
    //private static InputControl hp3;

    public static void initializeController(){

        spriteSheet = new SpriteSheet("img/touch-controls.png", 80, 80);

        //spriteSheet1 = new SpriteSheet("img/aliens-badges.png", 47, 47);

        //spriteSheet2 = new SpriteSheet("img/hud-hearts.png", 53, 45);

        inputControls = new ArrayList<InputControl>();

        left = new InputControl(new Vector2(0.5f, 1.5f), spriteSheet.spriteFrames[0], "left");

        right = new InputControl(new Vector2(2.5f, 1.5f), spriteSheet.spriteFrames[1], "right");

        up = new InputControl(new Vector2(1.5f, 2.5f), spriteSheet.spriteFrames[2], "up");

        down = new InputControl(new Vector2(1.5f, 0.5f), spriteSheet.spriteFrames[3], "down");

        jump = new InputControl(new Vector2(12.5f, 1.5f), spriteSheet.spriteFrames[12], "jump");


        //icon = new InputControl(new Vector2(0, 9.5f), spriteSheet1.spriteFrames[5], "icon");


        //hp1 = new InputControl(new Vector2(1, 9.5f), spriteSheet2.spriteFrames[2], "hp1");

        //hp2 = new InputControl(new Vector2(2, 9.5f), spriteSheet2.spriteFrames[2], "hp2");

        //hp3 = new InputControl(new Vector2(3, 9.5f), spriteSheet2.spriteFrames[2], "hp3");


        inputControls.add(left);

        inputControls.add(right);

        inputControls.add(jump);

        inputControls.add(up);

        inputControls.add(down);


        //inputControls.add(icon);

        //inputControls.add(hp1);

        //inputControls.add(hp2);

        //inputControls.add(hp3);

        Gdx.input.setInputProcessor(createInputAdapter());

    }

    public static void draw(Batch spriteBatch){

        spriteBatch.begin();

        for (InputControl inputControl : inputControls){

            inputControl.draw(spriteBatch);

        }

        spriteBatch.end();

    }

    private static InputAdapter createInputAdapter(){

        return new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                screenY = Gdx.graphics.getHeight() - screenY;

                for (InputControl inputControl : inputControls) {

                    if (inputControl.getBoundingBox().contains(screenX, screenY)) {

                        if (inputControl.action.equalsIgnoreCase("left")){

                            PlayerController.movementAction = "left";

                        }

                        if (inputControl.action.equalsIgnoreCase("right")){

                            PlayerController.movementAction = "right";

                        }

                        if (inputControl.action.equalsIgnoreCase("jump")){

                            PlayerController.movementAction = "jump";

                        }

                        if (inputControl.action.equalsIgnoreCase("up")){

                            PlayerController.movementAction = "climb";

                        }

                        if (inputControl.action.equalsIgnoreCase("down")){

                            PlayerController.movementAction = "duck";

                        }

                    }

                }

                    return true;

            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {

                screenY = Gdx.graphics.getHeight() - screenY;

                for (InputControl inputControl : inputControls) {

                    if (inputControl.getBoundingBox().contains(screenX, screenY)) {

                        if (inputControl.action.equalsIgnoreCase("left")){

                            PlayerController.movementAction = "";

                        }

                        if (inputControl.action.equalsIgnoreCase("right")){

                            PlayerController.movementAction = "";

                        }

                        if (inputControl.action.equalsIgnoreCase("down")){

                            PlayerController.movementAction = "";

                        }

                        if (inputControl.action.equalsIgnoreCase("climb")){

                            PlayerController.movementAction = "";

                        }

                        else {

                            PlayerController.movementAction = "";

                        }

                    }

                }

                return true;

            }



            @Override
            public boolean keyDown(int keycode) {

                if(keycode == Input.Keys.RIGHT){

                    PlayerController.movementAction = "right";

                }

                if(keycode == Input.Keys.LEFT){

                    PlayerController.movementAction = "left";

                }

                if(keycode == Input.Keys.DOWN){

                    PlayerController.movementAction = "duck";

                }

                if(keycode == Input.Keys.SPACE){

                    PlayerController.movementAction = "jump";

                }

                if(keycode == Input.Keys.UP){

                    PlayerController.movementAction = "climb";

                }

                return true;

            }

            @Override
            public boolean keyUp(int keycode) {

                if(keycode == Input.Keys.RIGHT){

                    PlayerController.movementAction = "";

                }

                if(keycode == Input.Keys.LEFT){

                    PlayerController.movementAction = "";

                }

                if(keycode == Input.Keys.DOWN){

                    PlayerController.movementAction = "";

                }

                if(keycode == Input.Keys.SPACE){

                    PlayerController.movementAction = "";

                }

                if(keycode == Input.Keys.UP){

                    PlayerController.movementAction = "";

                }

                return true;

            }
        };

    }

}

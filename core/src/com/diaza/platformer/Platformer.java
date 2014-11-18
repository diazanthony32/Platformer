package com.diaza.platformer;

import com.badlogic.gdx.Game;
import com.diaza.platformer.view.GameScreen;

public class Platformer extends Game {

    //Game Starts off from here
    @Override
    public void create() {

        //Sets screen to the camera
        setScreen(new GameScreen());

    }

}

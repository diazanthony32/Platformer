package com.diaza.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheet {

    public Texture spriteSheet;
    public TextureRegion[] spriteFrames;

    public Animation animation;

    //takes in the path to the spriteSheet and takes the width and height
    public SpriteSheet(String pathToFile, int width, int height) {

        //gets the sprite sheet saved from our folder
        spriteSheet = new Texture(Gdx.files.internal(pathToFile));

        //splits the character spites into seperate items
        TextureRegion[][] spriteSheetFrames = TextureRegion.split(spriteSheet, width, height);

        int counter = 0;

        //counts the amount of character sprites in the sheet
        //going to the first row down to the final 4 row (eve though it goes to 3)
        for(int row = 0; row < spriteSheetFrames.length; row++){

            //goes down the row to access the columns
            for (int column = 0; column < spriteSheetFrames[row].length; column++){

                counter++;

            }

        }

        //creates space for 55(the amount of sprites) in spriteFrames
        spriteFrames = new TextureRegion[counter];

        //sets counter to 0
        counter = 0;

        //takes every individual row and stores it in row
        for(TextureRegion[] row : spriteSheetFrames){

            //stores each column from each row into sprite
            for(TextureRegion sprite : row){

                //stores sprites in sprite frame one by one to be able to be accessed
                spriteFrames[counter++] = sprite;

            }

        }

    }

    public Animation createAnimation(){

        //creates space inside animation frames
        TextureRegion[] animationFrames = new TextureRegion[2];

        //gets the first part of the array and puts the first frame in
        animationFrames[0] = spriteFrames[23];
        //same as before
        animationFrames[1] = spriteFrames[24];

        //amount of time the frame is played for
        animation = new Animation(0.3f, animationFrames);

        return animation;

    }
}

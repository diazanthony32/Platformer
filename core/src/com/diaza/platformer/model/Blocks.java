package com.diaza.platformer.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Blocks extends Sprite{

    public Blocks(Vector2 position, int width, int height, String sheetPath) {

        super(position, width, height, sheetPath);

        //Stand Animation
        animations.put("idle", spriteSheet.createAnimation(1, 1, 0.25f));

        currentAnimation = "idle";

    }

    @Override
    public void draw(Batch spriteBatch) {
        super.draw(spriteBatch);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}

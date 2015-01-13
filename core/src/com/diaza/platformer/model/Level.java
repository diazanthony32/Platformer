package com.diaza.platformer.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Level {

    public TiledMap map;

    public Level(String mapPath) {

        //loads up the map from assets folder
        map = new TmxMapLoader().load(mapPath);

    }

}

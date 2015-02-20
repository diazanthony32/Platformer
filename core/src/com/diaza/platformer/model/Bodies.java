package com.diaza.platformer.model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.diaza.platformer.controller.LevelController;

public class Bodies {

    public static void createBody(MapObject mapObject) {

        String bodyType = mapObject.getProperties().get("type").toString();

        if (bodyType.equalsIgnoreCase("solid")){

            RectangleMapObject rectangleObject = (RectangleMapObject)mapObject;

            BodyDef bodyDefinition = new BodyDef();

            bodyDefinition.type = BodyDef.BodyType.StaticBody;

            bodyDefinition.position.set(rectangleObject.getRectangle().x * LevelController.UNIT_SCALE,
                                        rectangleObject.getRectangle().y * LevelController.UNIT_SCALE);


            Body physicsBody = LevelController.gameWorld.createBody(bodyDefinition);

            PolygonShape rectangleShape = new PolygonShape();

            rectangleShape.setAsBox(rectangleObject.getRectangle().width * LevelController.UNIT_SCALE / 2f,
                    rectangleObject.getRectangle().height * LevelController.UNIT_SCALE / 2f,
                    new Vector2(rectangleObject.getRectangle().width * LevelController.UNIT_SCALE / 2f, rectangleObject.getRectangle().height * LevelController.UNIT_SCALE / 2f), 0f);

            FixtureDef fixtureDefinition = new FixtureDef();

            fixtureDefinition.shape = rectangleShape;

            physicsBody.createFixture(fixtureDefinition);

            rectangleShape.dispose();

        }

        else if (bodyType.equalsIgnoreCase("ground")){

            PolylineMapObject polylineObject = (PolylineMapObject)mapObject;

            BodyDef bodyDefinition = new BodyDef();

            bodyDefinition.type = BodyDef.BodyType.StaticBody;

            bodyDefinition.position.set(polylineObject.getPolyline().getX() * LevelController.UNIT_SCALE,
                    polylineObject.getPolyline().getY() * LevelController.UNIT_SCALE);


            Body physicsBody = LevelController.gameWorld.createBody(bodyDefinition);

            ChainShape chainShape = new ChainShape();

            float[] transformedVertices = new float[polylineObject.getPolyline().getVertices().length];

            for (int index = 0;index < transformedVertices.length; index++){

                transformedVertices[index] = polylineObject.getPolyline().getVertices()[index] * LevelController.UNIT_SCALE;

            }

            chainShape.createChain(transformedVertices);

            FixtureDef fixtureDefinition = new FixtureDef();

            fixtureDefinition.shape = chainShape;

            fixtureDefinition.friction = 2f;

            physicsBody.createFixture(fixtureDefinition);

            chainShape.dispose();

        }

        if (bodyType.equalsIgnoreCase("blocks")){

            RectangleMapObject rectangleObject = (RectangleMapObject)mapObject;

            BodyDef bodyDefinition = new BodyDef();

            bodyDefinition.type = BodyDef.BodyType.DynamicBody;

            bodyDefinition.position.set(rectangleObject.getRectangle().x * LevelController.UNIT_SCALE,
                    rectangleObject.getRectangle().y * LevelController.UNIT_SCALE);

            Blocks blocks = new Blocks(bodyDefinition.position,70,70,"img/background-tiles.png");

            LevelController.worldSprites.add(blocks);

            Body physicsBody = LevelController.gameWorld.createBody(bodyDefinition);

            physicsBody.setUserData(blocks);

            PolygonShape rectangleShape = new PolygonShape();

            rectangleShape.setAsBox(rectangleObject.getRectangle().width * LevelController.UNIT_SCALE / 2f,
                    rectangleObject.getRectangle().height * LevelController.UNIT_SCALE / 2f,
                    new Vector2(rectangleObject.getRectangle().width * LevelController.UNIT_SCALE / 2f, rectangleObject.getRectangle().height * LevelController.UNIT_SCALE / 2f), 0f);

            FixtureDef fixtureDefinition = new FixtureDef();

            fixtureDefinition.shape = rectangleShape;

            fixtureDefinition.density = 1f;

            physicsBody.createFixture(fixtureDefinition);

            rectangleShape.dispose();

        }

    }

}

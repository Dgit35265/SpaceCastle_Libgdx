package com.SpaceCastle.game;

import com.badlogic.gdx.maps.MapProperties;

public class Wall extends ActorBeta {

    public Wall(MapProperties props)
    {
        setSize((float) props.get("width"), (float)props.get("height"));
        setPosition((float)props.get("x"), (float)props.get("y"));
        setBoundaryRectangle();
    }
}

package com.SpaceCastle.game;

import com.badlogic.gdx.maps.MapProperties;

public class Door extends ActorBeta {

    public Door(MapProperties props)
    {
        setSize((float)props.get("width"), (float)props.get("height"));
        setPosition((float)props.get("x"), (float)props.get("y"));
        setBoundaryRectangle();
    }
}

package com.SpaceCastle.game;

import com.badlogic.gdx.maps.MapProperties;

public class Ammo extends ActorBeta {
    public Ammo(MapProperties props){
        loadTexture("Ammo.png");
        setSize((float)props.get("width"), (float)props.get("height"));
        setPosition((float)props.get("x"), (float)props.get("y"));
        setBoundaryRectangle();

    }
}

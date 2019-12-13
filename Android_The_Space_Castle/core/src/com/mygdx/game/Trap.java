package com.mygdx.game;

import com.badlogic.gdx.maps.MapProperties;

public class Trap extends ActorBeta {
    public Trap(MapProperties props) {
        //loadTexture("Trap.png");
        setSize((float) props.get("width"), (float) props.get("height"));
        setPosition((float) props.get("x"), (float) props.get("y"));
        setBoundaryRectangle();
    }
}

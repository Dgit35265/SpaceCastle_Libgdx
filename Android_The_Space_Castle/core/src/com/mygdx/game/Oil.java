package com.mygdx.game;

import com.badlogic.gdx.maps.MapProperties;

public class Oil extends ActorBeta {
    public Oil(MapProperties props){
        loadTexture("Oil_Barrel.png");
        setSize((float)props.get("width"), (float)props.get("height"));
        setPosition((float)props.get("x"), (float)props.get("y"));
        setBoundaryRectangle();

    }
}

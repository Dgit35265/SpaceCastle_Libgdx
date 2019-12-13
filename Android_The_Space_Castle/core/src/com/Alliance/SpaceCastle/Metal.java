package com.Alliance.SpaceCastle;

import com.badlogic.gdx.maps.MapProperties;

public class Metal extends ActorBeta {

    public Metal(MapProperties props){
        loadTexture("Iron_Metal.png");
        setSize((float)props.get("width"), (float)props.get("height"));
        setPosition((float)props.get("x"), (float)props.get("y"));
        setBoundaryRectangle();
    }
}

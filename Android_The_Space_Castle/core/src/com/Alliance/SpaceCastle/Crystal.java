package com.Alliance.SpaceCastle;

import com.badlogic.gdx.maps.MapProperties;

public class Crystal extends ActorBeta {

    public Crystal(MapProperties props){
        loadTexture("Crystal.png");
        setSize((float)props.get("width"), (float)props.get("height"));
        setPosition((float)props.get("x"), (float)props.get("y"));
        setBoundaryRectangle();

    }
}

package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.LinkedList;

public class TopdownGame3 extends TileMapScreen {
    Label Title;
    TilemapActor tilemap;
    @Override
    public void initialize() {
        super.initialize();

        //PlayBGM();
        tilemap = new TilemapActor("Map/DungeonMap3.tmx", mainStage);
        wall = new LinkedList<Wall>();
        doors = new LinkedList<Door>();
        oils = new LinkedList<Oil>();
        crystals = new LinkedList<Crystal>();
        traps = new LinkedList<Trap>();
        ammos = new LinkedList<Ammo>();
        metals = new LinkedList<Metal>();



        for(MapObject obj : tilemap.getRectangleList("wall"))
        {
            Wall col = new Wall(obj.getProperties());
            wall.add(col);
        }
        for(MapObject obj : tilemap.getRectangleList("door"))
        {
            Door col = new Door(obj.getProperties());
            doors.add(col);
        }
        for(MapObject obj : tilemap.getRectangleList("oil"))
        {
            Oil col = new Oil(obj.getProperties());
            oils.add(col);
            mainStage.addActor(col);
        }
        for(MapObject obj : tilemap.getRectangleList("crystal"))
        {
            Crystal col = new Crystal(obj.getProperties());
            crystals.add(col);
            mainStage.addActor(col);
        }
        for(MapObject obj : tilemap.getRectangleList("metal"))
        {
            Metal col = new Metal(obj.getProperties());
            metals.add(col);
            mainStage.addActor(col);
        }
        for(MapObject obj : tilemap.getRectangleList("ammo"))
        {
            Ammo col = new Ammo(obj.getProperties());
            ammos.add(col);
            mainStage.addActor(col);
        }
        for(MapObject obj : tilemap.getRectangleList("trap"))
        {
            Trap col = new Trap(obj.getProperties());
            traps.add(col);
            mainStage.addActor(col);
        }


        mainStage.addActor(player);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        for(int i = 0; i < wall.size(); i++){
            if(player.overlaps(wall.get(i))){
                //Gdx.app.log("Ground", "Hit");
                player.preventOverlap(wall.get(i));
            }
        }
        for(int i = 0; i < doors.size(); i++){
            if(player.overlaps(doors.get(i))){
                // SpaceCastle.setActiveScreen(new TopdownGame3());
            }
        }
        for(int i = 0; i < oils.size(); i++){
            if(player.overlaps(oils.get(i))){
                Oil temp = oils.get(i);
                oils.remove(i);
                temp.remove();
                player.setOil(1);
            }
        }

        for(int i = 0; i < traps.size(); i++){
            if(player.overlaps(traps.get(i))){
                Trap temp = traps.get(i);
                traps.remove(i);
                temp.remove();
                player.hit();
            }
        }
        for(int i = 0; i < metals.size(); i++){
            if(player.overlaps(metals.get(i))){
                Metal temp = metals.get(i);
                metals.remove(i);
                temp.remove();
                player.setMetal(1);
            }
        }
        for(int i = 0; i < ammos.size(); i++){
            if(player.overlaps(ammos.get(i))){
                Ammo temp = ammos.get(i);
                ammos.remove(i);
                temp.remove();
                player.setAmmo(1);
            }
        }
        for(int i = 0; i < crystals.size(); i++){
            if(player.overlaps(crystals.get(i))){
                Crystal temp = crystals.get(i);
                crystals.remove(i);
                temp.remove();
                player.setCrystal(1);
            }
        }

    }
}

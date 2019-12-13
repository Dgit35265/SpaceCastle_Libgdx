package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.LinkedList;

public class TileMapScreen extends ScreenBeta {

    Texture  GoldTex, CrystalTex, OilTex, MetalTex, AmmoTex, InhabitantTex, FoodTex;
    Image  GoldImg, CrystalImg, OilImage, MetalImage, AmmoImage, InhabitantImg, FoodImg;;
    Label GoldLbl, CrystalLbl, OilLbl, MetalLbl, AmmoLbl, InhabitantLbl, FoodLbl, TimeLbl;

    float DungeonTimer, DungeonTime;
    float speed, dist, distToEat;

    Table DPad, SquadResourceTable, Btns;
    Button Up, Down, Left, Right;
    ImageTextButton BackBtn, OptBtn;

    //Colliders
    public LinkedList<Wall> wall;
    public LinkedList<Door> doors;
    public LinkedList<Trap> traps;
    public LinkedList<Ammo> ammos;
    public LinkedList<Oil> oils;
    public LinkedList<Crystal> crystals;
    public LinkedList<Metal> metals;

    boolean isReleased;
    TopdownPlayer player;

    @Override
    public void initialize() {
        DungeonTime = 100;
        DungeonTimer = DungeonTime;
        speed = 150;
        distToEat = 750;

        GameBGM.play();
        isReleased = true;
        player = new TopdownPlayer();
        player.setPosition(50,50);

        //Load Textures
        GoldTex = new Texture("Gold.png");
        CrystalTex = new Texture("Crystal.png");
        OilTex = new Texture("Oil_Barrel.png");
        MetalTex = new Texture("Iron_Metal.png");
        AmmoTex = new Texture("Ammo.png");
        InhabitantTex = new Texture("Inhabitant_Icon.png");
        FoodTex = new Texture("Food_Icon.png");
        //Create Images
        GoldImg = new Image(GoldTex);
        CrystalImg = new Image(CrystalTex);
        OilImage = new Image(OilTex);
        MetalImage = new Image(MetalTex);
        AmmoImage = new Image(AmmoTex);
        InhabitantImg = new Image(InhabitantTex);
        FoodImg = new Image(FoodTex);
        //Buttons
        Up = new Button(uiSkin);
        Down = new Button(uiSkin);
        Left = new Button(uiSkin);
        Right = new Button(uiSkin);
        BackBtn = new ImageTextButton("Go Back to SpaceCastle(Lose All Food)", uiSkin); //this should be another function
        OptBtn = new ImageTextButton("Option", uiSkin);
        //Labels
        GoldLbl = new Label(Integer.toString(SpaceCastle.S_Gold), uiSkin);
        CrystalLbl = new Label(Integer.toString(SpaceCastle.S_Crystal), uiSkin);
        OilLbl = new Label(Integer.toString(SpaceCastle.S_Oil), uiSkin);
        MetalLbl = new Label(Integer.toString(SpaceCastle.S_Metal), uiSkin);
        AmmoLbl = new Label(Integer.toString(SpaceCastle.S_Ammo), uiSkin);
        InhabitantLbl = new Label(Integer.toString(SpaceCastle.S_Inhabitant), uiSkin);
        FoodLbl = new Label(Integer.toString(SpaceCastle.S_Food), uiSkin);
        TimeLbl = new Label("Time Remain: ",uiSkin);
        //D-Pad
        DPad = new Table();
        DPad.setSize( 400, 400);
        DPad.setPosition(100, 100);
        DPad.add(Up).colspan(2).minWidth(100).minHeight(100).padBottom(25);
        DPad.row();
        DPad.add(Left).minWidth(100).minHeight(100).padRight(60);
        DPad.add(Right).minWidth(100).minHeight(100).padLeft(60);
        DPad.row();
        DPad.add(Down).colspan(2).minWidth(100).minHeight(100).padTop(25);
        //Resource list
        SquadResourceTable = new Table();
        SquadResourceTable.setSize(WIDTH, 100);
        SquadResourceTable.setPosition(WIDTH/2 - (SquadResourceTable.getWidth()/2), HEIGHT - SquadResourceTable.getHeight());
        SquadResourceTable.add(GoldImg).maxWidth(100).maxHeight(100).padLeft(100);
        SquadResourceTable.add(GoldLbl).expandX();
        SquadResourceTable.add(CrystalImg).maxWidth(100).maxHeight(100);
        SquadResourceTable.add(CrystalLbl).expandX();
        SquadResourceTable.add(OilImage).maxWidth(100).maxHeight(100);
        SquadResourceTable.add(OilLbl).expandX();
        SquadResourceTable.add(MetalImage).maxWidth(100).maxHeight(100);
        SquadResourceTable.add(MetalLbl).expandX();
        SquadResourceTable.add(AmmoImage).maxWidth(100).maxHeight(100);
        SquadResourceTable.add(AmmoLbl).expandX();
        SquadResourceTable.add(InhabitantImg).maxWidth(100).maxHeight(100);
        SquadResourceTable.add(InhabitantLbl).expandX();
        SquadResourceTable.add(FoodImg).maxWidth(100).maxHeight(100);
        SquadResourceTable.add(FoodLbl).expandX();
        SquadResourceTable.add(TimeLbl).expandX();
        //Buttons
        Btns = new Table();
        Btns.setSize(2960, 200);
        Btns.setPosition(WIDTH/2 - (Btns.getWidth()/2), 0);
        Btns.add(BackBtn).padLeft(1000);
        Btns.add(OptBtn).padLeft(1000);


        uiStage.addActor(SquadResourceTable);
        uiStage.addActor(DPad);
        uiStage.addActor(Btns);

    }

    @Override
    public void update(float dt) {
        SpaceCastle.TotalTime += dt;
        Gdx.app.log("TTime", Float.toString(SpaceCastle.TotalTime));
        //DungeonTimer
        DungeonTimer -= dt;
        TimeLbl.setText("Time Remain: " + (int) DungeonTimer);
        if (DungeonTimer <= 0) {
            EndDungeon();
        }

        SpaceCastle.TotalTime += dt;
        if (OptBtn.isPressed()) {
            OptionScreen.isTileMap = true;
            SpaceCastle.setActiveScreen(new OptionScreen());
        }
        if (BackBtn.isPressed()) {
            OptionScreen.isTileMap = false;
            EndDungeonEarly();
        }

        if (Up.isPressed() && isReleased)
        {
            player.moveBy(0, speed * dt);
            dist += speed * dt;
        }
        if (Down.isPressed() && isReleased)
        {
            player.moveBy(0, -speed * dt);
            dist += speed * dt;
        }
        if(Left.isPressed() && isReleased)
        {
            player.moveBy(-speed * dt, 0);
            dist += speed * dt;
        }
        if(Right.isPressed() && isReleased)
        {
            player.moveBy(speed * dt, 0);
            dist += speed * dt;
        }

        Gdx.app.log("Distance", Float.toString(dist));
        //Consume food base on distance player moved
        if(dist >= distToEat)
        {
            SpaceCastle.S_Food -= 1;
            RefreshSquadResource();
            dist = 0;
        }
        //Lose All Resource if Not Food Left
        if(SpaceCastle.S_Food <= 0)
        {
            SquadDead();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        GameBGM.stop();
    }

    public void EndDungeon()
    {
        SpaceCastle.Gold += SpaceCastle.S_Gold;
        SpaceCastle.Crystal += SpaceCastle.S_Crystal;
        SpaceCastle.Oil += SpaceCastle.S_Oil;
        SpaceCastle.Metal += SpaceCastle.S_Metal;
        SpaceCastle.Ammo += SpaceCastle.S_Ammo;
        SpaceCastle.Inhabitant += SpaceCastle.S_Inhabitant;
        SpaceCastle.Food += SpaceCastle.S_Food;

        SpaceCastle.setActiveScreen(new ClickerScreen());
        dispose();
    }
    public void EndDungeonEarly()
    {
        SpaceCastle.S_Food = 0;
        EndDungeon();
    }
    public void SquadDead()
    {
        SpaceCastle.S_Gold = 0;
        SpaceCastle.S_Crystal = 0;
        SpaceCastle.S_Oil = 0;
        SpaceCastle.S_Metal = 0;
        SpaceCastle.S_Ammo = 0;
        SpaceCastle.S_Inhabitant = 0;
        SpaceCastle.S_Food = 0;
        RefreshSquadResource();
        EndDungeon();
    }
    public void RefreshSquadResource()
    {
        GoldLbl.setText(Integer.toString(SpaceCastle.S_Gold));
        CrystalLbl.setText(Integer.toString(SpaceCastle.S_Crystal));
        OilLbl.setText(Integer.toString(SpaceCastle.S_Oil));
        MetalLbl.setText(Integer.toString(SpaceCastle.S_Metal));
        AmmoLbl.setText(Integer.toString(SpaceCastle.S_Ammo));
        InhabitantLbl.setText(Integer.toString(SpaceCastle.S_Inhabitant));
        FoodLbl.setText(Integer.toString(SpaceCastle.S_Food));
    }
}

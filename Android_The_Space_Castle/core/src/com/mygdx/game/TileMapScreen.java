package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sun.org.apache.regexp.internal.RESyntaxException;

public class TileMapScreen extends ScreenBeta {

    Texture BGTex, GoldTex, CrystalTex, ListBGTex;
    Image BGImg, GoldImg, CrystalImg;

    Table DPad, ResourceTable, Btns;
    Button Up, Down, Left, Right;
    ImageTextButton Btn1, OptBtn;
    Label GoldLbl, CrystalLbl;

    //GameData
    int Gold;

    @Override
    public void initialize() {
        //get gold from game database
        Gold = 0;
        //Load Textures
        BGTex = new Texture("MainMenuBG.jpg");
        GoldTex = new Texture("Gold.png");
        CrystalTex = new Texture("Crystal.png");
        ListBGTex = new Texture("resourceListBG.png");
        //Create Images
        BGImg = new Image(BGTex);
        GoldImg = new Image(GoldTex);
        CrystalImg = new Image(CrystalTex);
        //Buttons
        Up = new Button(uiSkin);
        Down = new Button(uiSkin);
        Left = new Button(uiSkin);
        Right = new Button(uiSkin);
        Btn1 = new ImageTextButton("Get Back to Clicker", uiSkin); //this should be another function
        OptBtn = new ImageTextButton("Option", uiSkin);
        //Labels
        GoldLbl = new Label("0", uiSkin);
        GoldLbl.setText(Integer.toString(Gold));
        CrystalLbl = new Label("0", uiSkin);
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
        ResourceTable = new Table();
        ResourceTable.setSize(2960, 100);
        ResourceTable.setPosition(WIDTH/2 - (ResourceTable.getWidth()/2), HEIGHT - ResourceTable.getHeight());
        ResourceTable.setBackground(new TextureRegionDrawable(new TextureRegion(ListBGTex)));
        ResourceTable.add(GoldImg).maxWidth(100).maxHeight(100);
        ResourceTable.add(GoldLbl).padRight(400);
        ResourceTable.add(CrystalImg).maxWidth(100).maxHeight(100);
        ResourceTable.add(CrystalLbl);
        //Buttons
        Btns = new Table();
        Btns.setSize(2960, 200);
        Btns.setPosition(WIDTH/2 - (Btns.getWidth()/2), 0);
        Btns.add(Btn1).padLeft(1000);
        Btns.add(OptBtn).padLeft(1000);


        uiStage.addActor(BGImg);
        uiStage.addActor(ResourceTable);
        uiStage.addActor(DPad);
        uiStage.addActor(Btns);
    }

    @Override
    public void update(float dt) {
        if(OptBtn.isPressed())
        {
            OptionScreen.isTileMap = true;
            SpaceCastle.setActiveScreen(new OptionScreen());
        }
        if(Btn1.isPressed())
        {
            OptionScreen.isTileMap = false;
            SpaceCastle.setActiveScreen(new ClickerScreen());
        }
    }
}

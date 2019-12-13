package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class OptionScreen extends ScreenBeta {

    Texture BGTex;
    Image BGImg;

    Table Btns;

    ImageTextButton EasyBtn, NormalBtn, HardBtn, BackBtn, ResumeBtn;
    Label Title, VolumeL, BrightnessL;

    boolean isSetResume;
    public static boolean isClicker, isTileMap;

    @Override
    public void initialize() {
        isSetResume = false;

        BGTex = new Texture("MainMenuBG.jpg");
        BGImg = new Image(BGTex);
        //Buttons
        EasyBtn = new ImageTextButton("Easy", uiSkin);
        NormalBtn = new ImageTextButton("Normal", uiSkin);
        HardBtn = new ImageTextButton("Hard", uiSkin);
        BackBtn = new ImageTextButton("Back to Menu", uiSkin);
        ResumeBtn = new ImageTextButton("Resume", uiSkin);
        //Title
        Title = new Label("Option", uiSkin);
        //Labels
        VolumeL = new Label("Volume", uiSkin);
        BrightnessL = new Label("Brightness", uiSkin);
        //Tables
        Btns = new Table();
        Btns.setSize(800, 1000);
        Btns.setPosition(WIDTH/2 - (Btns.getWidth()/2), HEIGHT/2 - (Btns.getHeight()/2));
        Btns.add(Title).colspan(2).padBottom(100);
        Btns.row();
        Btns.add(EasyBtn).padBottom(100);
        Btns.row();
        Btns.add(NormalBtn).padBottom(100);
        Btns.row();
        Btns.add(HardBtn).padBottom(100);
        Btns.row();
        Btns.add(BackBtn).padTop(400);

        uiStage.addActor(BGImg);
        uiStage.addActor(Btns);
    }

    @Override
    public void update(float dt) {
        Gdx.app.log("isClicker", Boolean.toString(isClicker));
        //Gdx.app.log("isTileMap", Boolean.toString(isTileMap));
        if(BackBtn.isPressed())
        {
            SpaceCastle.isGame = false;
            SpaceCastle.setActiveScreen(new MainMenu());
            GameBGM.stop();
        }
        //Choose Difficulty when not in game
        if(!SpaceCastle.isGame) {
            if (EasyBtn.isPressed()) {
                SpaceCastle.LoadDifficulty(1);
            }
            if (NormalBtn.isPressed()) {
                SpaceCastle.LoadDifficulty(2);
            }
            if (HardBtn.isPressed()) {
                SpaceCastle.LoadDifficulty(3);
            }
        }

        if(SpaceCastle.isGame && !isSetResume)
        {
            //add resume button
            Btns.row();
            Btns.add(ResumeBtn).padTop(100);
            isSetResume = true;
        }
        if(ResumeBtn.isPressed())
        {
            if(isClicker)
            {
                isClicker = false;
                SpaceCastle.setActiveScreen(new ClickerScreen());
            }
            if(isTileMap)
            {
                isTileMap = false;
                SpaceCastle.setActiveScreen(new TileMapScreen());
            }
        }
    }
}

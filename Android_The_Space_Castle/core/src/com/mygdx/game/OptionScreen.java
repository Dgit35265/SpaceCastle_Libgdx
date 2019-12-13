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

    Table Btns, Sliders;

    ImageTextButton Opt1, Opt2, Opt3, BackBtn, ResumeBtn;
    Slider Volume, Brightness;
    Label Title, VolumeL, BrightnessL;

    boolean isSetResume;
    public static boolean isClicker, isTileMap;

    @Override
    public void initialize() {
        isSetResume = false;

        BGTex = new Texture("MainMenuBG.jpg");
        BGImg = new Image(BGTex);
        //Buttons
        Opt1 = new ImageTextButton("Option1", uiSkin);
        Opt2 = new ImageTextButton("Option2", uiSkin);
        Opt3 = new ImageTextButton("Option3", uiSkin);
        BackBtn = new ImageTextButton("Back to Menu", uiSkin);
        ResumeBtn = new ImageTextButton("Resume", uiSkin);
        //Sliders
        Volume = new Slider(0, 5, 1, false, uiSkin);
        Brightness = new Slider(0, 5, 1, false, uiSkin);
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
        Btns.add(Opt1).padBottom(100);
        Btns.row();
        Btns.add(Opt2).padBottom(100);
        Btns.row();
        Btns.add(Opt3).padBottom(100);
        Btns.row();
        Btns.add(BackBtn).padTop(400);

        Sliders = new Table();
        Sliders.setSize(2000, 400);
        Sliders.setPosition(WIDTH/2 - (Sliders.getWidth()/2), 300);
        Sliders.add(VolumeL).padRight(400);
        Sliders.add(BrightnessL);
        Sliders.row();
        Sliders.add(Volume).padRight(400);
        Sliders.add(Brightness);

        uiStage.addActor(BGImg);
        uiStage.addActor(Btns);
        uiStage.addActor(Sliders);
    }

    @Override
    public void update(float dt) {
        Gdx.app.log("isClicker", Boolean.toString(isClicker));
        //Gdx.app.log("isTileMap", Boolean.toString(isTileMap));
        if(BackBtn.isPressed())
        {
            SpaceCastle.isGame = false;
            SpaceCastle.setActiveScreen(new MainMenu());
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

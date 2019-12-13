package com.SpaceCastle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class LoadScreen extends ScreenBeta {
    Texture BGTex;
    Image BGImg;

    Table LoadTable;
    Label Title;
    ImageTextButton Slot1, Slot2, BackBtn;

    @Override
    public void initialize() {
        BGTex = new Texture("MainMenuBG.jpg");
        BGImg = new Image(BGTex);
        //Title
        Title = new Label("Load Game", uiSkin);
        Title.setFontScale(3);
        Title.setSize(WIDTH/6, HEIGHT/6);
        Title.setPosition(WIDTH/2 - (Title.getWidth()/2), HEIGHT/2 + Title.getHeight());
        Title.setAlignment(Align.center);
        //Buttons
        Slot1 = new ImageTextButton("Slot1", uiSkin);
        Slot2 = new ImageTextButton("Slot2", uiSkin);
        BackBtn = new ImageTextButton("Back to Menu", uiSkin);
        //Table
        LoadTable = new Table();
        LoadTable.setSize(800, 1000);
        LoadTable.setPosition(WIDTH/2 - (LoadTable.getWidth()/2), 0);
        LoadTable.add(Slot1).padBottom(100);
        LoadTable.row();
        LoadTable.add(Slot2).padBottom(100);
        LoadTable.row();
        LoadTable.add(BackBtn).padBottom(100);
        LoadTable.row();


        uiStage.addActor(BGImg);
        uiStage.addActor(Title);
        uiStage.addActor(LoadTable);
    }

    @Override
    public void update(float dt) {
        if(Slot1.isPressed())
        {
            //load save game 1
            SpaceCastle.isGame = true;
        }
        if(Slot2.isPressed())
        {
            //load save game 2
            SpaceCastle.isGame = true;
        }
        if(BackBtn.isPressed())
        {
            SpaceCastle.setActiveScreen(new MainMenu());
        }
    }
}

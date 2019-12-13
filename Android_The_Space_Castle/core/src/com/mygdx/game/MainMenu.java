package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import java.awt.HeadlessException;

public class MainMenu extends ScreenBeta {
    Texture BGTex;
    Image BGImg;

    Table Btns;
    Label Title;
    ImageTextButton StartGameBtn, LoadGameBtn, HowToPlayBtn, OptionBtn, CreditBtn;


    @Override
    public void initialize() {
        //set is not in game
        SpaceCastle.isGame = false;

        BGTex = new Texture("MainMenuBG.jpg");
        BGImg = new Image(BGTex);
        //Title
        Title = new Label("Space Castle", uiSkin);
        Title.setFontScale(6);
        Title.setSize(WIDTH/6, HEIGHT/6);
        Title.setPosition(WIDTH/2 - (Title.getWidth()/2), HEIGHT/2 + Title.getHeight());
        Title.setAlignment(Align.center);
        //Buttons
        StartGameBtn = new ImageTextButton("Start Game", uiSkin);
        //LoadGameBtn = new ImageTextButton("Load Game", uiSkin);
        HowToPlayBtn = new ImageTextButton("How To Play", uiSkin);
        OptionBtn = new ImageTextButton("Option", uiSkin);
        CreditBtn = new ImageTextButton("Credit", uiSkin);

        //Table
        Btns = new Table();
        Btns.setSize(1000, 600);
        Btns.setPosition(WIDTH/2 - (Btns.getWidth()/2), HEIGHT/2 - (Btns.getHeight()/2) - 150);
        Btns.add(StartGameBtn).padBottom(50);
        Btns.row();
        //Btns.add(LoadGameBtn).padBottom(50);
        Btns.add(HowToPlayBtn).padBottom(50);
        Btns.row();
        Btns.add(OptionBtn).padBottom(50);
        Btns.row();
        Btns.add(CreditBtn).padBottom(50);

        MenuBGM.play();

        uiStage.addActor(BGImg);
        uiStage.addActor(Title);
        uiStage.addActor(Btns);
    }

    @Override
    public void update(float dt) {
        if(StartGameBtn.isPressed())
        {
            SpaceCastle.isGame = true;
            dispose();
            SpaceCastle.setActiveScreen(new ClickerScreen());
            //initialize game here
        }
        /*if(LoadGameBtn.isPressed())
        {
            SpaceCastle.setActiveScreen(new LoadScreen());
        }*/
        if(HowToPlayBtn.isPressed())
        {
            SpaceCastle.setActiveScreen(new HowToPlayScreen());
        }
        if(OptionBtn.isPressed())
        {
            SpaceCastle.setActiveScreen(new OptionScreen());
        }
        if(CreditBtn.isPressed())
        {
            SpaceCastle.setActiveScreen(new CreditScreen());
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        MenuBGM.stop();
        GameBGM.play();
    }
}

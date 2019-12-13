package com.SpaceCastle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class LoseScreen extends ScreenBeta {
    Texture BGTex;
    Image BGImg;

    Label Title, Lbl1, Lbl2, Lbl3;
    Table WinTable;
    ImageTextButton BackBtn;
    @Override
    public void initialize() {
        //BG
        BGTex = new Texture("MainMenuBG.jpg");
        BGImg = new Image(BGTex);
        //Labels
        Title = new Label("You Lose!", uiSkin);
        Title.setFontScale(2);
        Title.setSize(WIDTH/6, HEIGHT/6);
        Title.setPosition(WIDTH/2 - (Title.getWidth()/2), HEIGHT/2 + Title.getHeight());
        Title.setAlignment(Align.center);
        //Label For Content
        Lbl1 = new Label("Resource You Got", uiSkin);
        Lbl2 = new Label("Gold: " + SpaceCastle.Gold + " Crystal: " + SpaceCastle.Crystal + " Oil: " + SpaceCastle.Oil + " Metal: " + SpaceCastle.Metal
                + " Ammo: " + SpaceCastle.Ammo + " Inhabitants: " + SpaceCastle.Inhabitant, uiSkin);
        Lbl3 = new Label("Total Time Used: " + SpaceCastle.TotalTime, uiSkin);
        //Button
        BackBtn = new ImageTextButton("Back to Menu", uiSkin);
        //Table
        WinTable = new Table();
        WinTable.setSize(1000, 1000);
        WinTable.setPosition(WIDTH/2 - (WinTable.getWidth()/2), HEIGHT/2 - (WinTable.getHeight()/2));
        WinTable.add(Lbl1).padBottom(100).padTop(200);
        WinTable.row();
        WinTable.add(Lbl2).padBottom(100);
        WinTable.row();
        WinTable.add(Lbl3).padBottom(200);
        WinTable.row();
        WinTable.add(BackBtn);

        uiStage.addActor(BGImg);
        uiStage.addActor(Title);
        uiStage.addActor(WinTable);
    }

    @Override
    public void update(float dt) {
        if(BackBtn.isPressed())
        {
            SpaceCastle.LoadDifficulty(1);
            SpaceCastle.setActiveScreen(new MainMenu());
        }
    }
}

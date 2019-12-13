package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class HowToPlayScreen extends ScreenBeta {

    Texture BGTex;
    Image BGImg;

    Label Title, Lbl1, Lbl2, Lbl3;
    Table TutorialTable;
    ImageTextButton BackBtn;

    @Override
    public void initialize() {
        //BG
        BGTex = new Texture("MainMenuBG.jpg");
        BGImg = new Image(BGTex);
        //Labels
        Title = new Label("How To Play", uiSkin);
        Title.setFontScale(2);
        Title.setSize(WIDTH/6, HEIGHT/6);
        Title.setPosition(WIDTH/2 - (Title.getWidth()/2), HEIGHT/2 + Title.getHeight());
        Title.setAlignment(Align.center);
        //Label For Content
        Lbl1 = new Label("First Game is just like other clicker game, you need gather money by clicking. Also you can upgrade and purchase other device to get other resource", uiSkin);
        Lbl2 = new Label("Second Game is TopDownRPG-like game, basically you need to discover it and find anything you need in a limited time.", uiSkin);
        Lbl3 = new Label("Be Careful, you might fall into a trap and there is punishment.", uiSkin);
        //Button
        BackBtn = new ImageTextButton("Back to Menu", uiSkin);
        //Table
        TutorialTable = new Table();
        TutorialTable.setSize(1000, 1000);
        TutorialTable.setPosition(WIDTH/2 - (TutorialTable.getWidth()/2), HEIGHT/2 - (TutorialTable.getHeight()/2));
        TutorialTable.add(Lbl1).padBottom(100).padTop(200);
        TutorialTable.row();
        TutorialTable.add(Lbl2).padBottom(100);
        TutorialTable.row();
        TutorialTable.add(Lbl3).padBottom(200);
        TutorialTable.row();
        TutorialTable.add(BackBtn);

        uiStage.addActor(BGImg);
        uiStage.addActor(Title);
        uiStage.addActor(TutorialTable);
    }

    @Override
    public void update(float dt) {
        if(BackBtn.isPressed())
        {
            SpaceCastle.setActiveScreen(new MainMenu());
        }
    }
}

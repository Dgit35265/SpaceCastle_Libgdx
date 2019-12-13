package com.SpaceCastle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class CreditScreen extends ScreenBeta {

    Texture BGTex;
    Image BGImg;

    Table creditTable;
    Label Title, Lbl1, Lbl2;
    ImageTextButton BackBtn;

    @Override
    public void initialize() {
        //BG
        BGTex = new Texture("MainMenuBG.jpg");
        BGImg = new Image(BGTex);
        //Labels
        Title = new Label("Credit", uiSkin);
        Title.setFontScale(2);
        Title.setSize(WIDTH/6, HEIGHT/6);
        Title.setPosition(WIDTH/2 - (Title.getWidth()/2), HEIGHT/2 + Title.getHeight());
        Title.setAlignment(Align.center);

        Lbl1 = new Label("Ding Zhang - 101131908", uiSkin);
        Lbl2 = new Label("HsiaoTe Tang - xxxxxxxx", uiSkin);
        //Button
        BackBtn = new ImageTextButton("Back to Menu", uiSkin);
        //Table
        creditTable = new Table();
        creditTable.setSize(1000, 1000);
        creditTable.setPosition(WIDTH/2 - (creditTable.getWidth()/2), HEIGHT/2 - (creditTable.getHeight()/2));
        creditTable.add(Lbl1).padBottom(100).padTop(100);
        creditTable.row();
        creditTable.add(Lbl2).padBottom(200);
        creditTable.row();
        creditTable.add(BackBtn);

        uiStage.addActor(BGImg);
        uiStage.addActor(Title);
        uiStage.addActor(creditTable);
    }

    @Override
    public void update(float dt) {
        if(BackBtn.isPressed())
        {
            SpaceCastle.setActiveScreen(new MainMenu());
        }
    }
}

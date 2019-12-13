package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SplashScreen extends ScreenBeta {

    Texture LogoTex;
    Image LogoImg;

    float timer;

    @Override
    public void initialize() {
        LogoTex = new Texture("Alliance_Logo.png");
        LogoImg = new Image(LogoTex);
        LogoImg.setSize(510, 523);
        LogoImg.setPosition(WIDTH/2 - (LogoImg.getWidth()/2), HEIGHT/2 - (LogoImg.getHeight()/2));
        LogoImg.getColor().a = 0.f;

        LogoImg.addAction(Actions.sequence(Actions.fadeIn(1.f), Actions.repeat(2, Actions.sequence(Actions.moveBy(0, -50, 0.5f), Actions.moveBy(0, 50, 0.5f))), Actions.fadeOut(0.5f)));

        timer = 0.f;

        uiStage.addActor(LogoImg);
    }

    @Override
    public void update(float dt) {
        timer++;
        if(timer > 3/dt){
            SpaceCastle.setActiveScreen(new MainMenu());
            this.dispose();
        }
    }
}

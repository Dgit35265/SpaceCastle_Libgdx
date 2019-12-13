package com.mygdx.game;

import javax.swing.text.html.Option;

public class SpaceCastle extends GameBeta {
    public static boolean isGame;
    @Override
    public void create() {
        super.create();

        setActiveScreen(new SplashScreen());
    }
}

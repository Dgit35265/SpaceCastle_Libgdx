package com.mygdx.game;

import javax.swing.text.html.Option;

public class SpaceCastle extends GameBeta {
    public static boolean isGame;

    //Game Resource
    public static int Gold, Crystal, Oil, Metal, Ammo, Inhabitant, Food;
    public static boolean CrystalUpgrade, OilUpgrade, MetalUpgrade, AmmoUpgrade;

    public static int AutoFoodNum;

    //Win Condition
    public static int WinInhabitants;


    @Override
    public void create() {
        super.create();

        //Init Game Resource
        Gold = 50;
        Crystal = 30;
        Oil = 15;
        Metal = 5;
        Ammo = 0;
        Inhabitant = 50;
        Food = 10;
        AutoFoodNum = 0;

        //Init Win Condition
        WinInhabitants = 2000; //set 2000 for test

        setActiveScreen(new SplashScreen());
    }
}

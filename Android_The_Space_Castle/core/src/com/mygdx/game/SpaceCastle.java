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
        LoadDifficulty(1);

        //test, delete later
        Gold = 50;
        Crystal = 30;
        Oil = 15;
        Metal = 5;
        Ammo = 50;
        Inhabitant = 50;
        Food = 10;
        AutoFoodNum = 0;

        //Init Win Condition
        WinInhabitants = 2000; //set 2000 for test

        setActiveScreen(new SplashScreen());
    }
    //Load Different Game Difficulty
    public static void LoadDifficulty(int level)
    {
        if(level == 1)
        {
            Gold = 100;
            Crystal = 20;
            Oil = 10;
            Metal = 0;
            Ammo = 0;
            Inhabitant = 50;
            Food = 15;
            AutoFoodNum = 0;
        }
        if(level == 2)
        {
            Gold = 50;
            Crystal = 10;
            Oil = 0;
            Metal = 0;
            Ammo = 0;
            Inhabitant = 50;
            Food = 10;
            AutoFoodNum = 0;
        }
        if(level == 3)
        {
            Gold = 0;
            Crystal = 0;
            Oil = 0;
            Metal = 0;
            Ammo = 0;
            Inhabitant = 50;
            Food = 5;
            AutoFoodNum = 0;
        }
    }
}

package com.mygdx.game;

public class TopdownPlayer extends ActorBeta {

    enum AnimState{
        IDLE,
        MOVELEFT,
        MOVERIGHT,
        MOVEUP,
        MOVEDOWN
    }

    public static AnimState animState;

    int oil;
    int crystal;
    int metal;
    int ammo;
    int health;

    TopdownPlayer()
    {
        animState = AnimState.IDLE;

        this.setSize(32, 32);
        this.setScale(1);
        this.setBoundaryRectangle();

        oil = 0;
        crystal = 0;
        metal = 0;
        ammo = 0;
        health = 10;
    }

    void setOil(int num)
    {oil += num;}

    void setCrystal(int num)
    {crystal += num;}

    void setMetal(int num)
    {metal += num;}

    void setAmmo(int num)
    {ammo += num;}

    void hit(){health--;}

}

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
    int health;

    TopdownPlayer()
    {
        animState = AnimState.IDLE;

        this.setSize(32, 32);
        this.setScale(1);
        this.setBoundaryRectangle();
        health = 10;
    }

    void setOil(int num)
    {SpaceCastle.S_Oil += num;}

    void setCrystal(int num)
    {SpaceCastle.S_Crystal += num;}

    void setMetal(int num)
    {SpaceCastle.S_Metal += num;}

    void setAmmo(int num)
    {SpaceCastle.S_Ammo += num;}

    void hit(){SpaceCastle.S_Food--;}

}

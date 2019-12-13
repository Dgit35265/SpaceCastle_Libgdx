package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TopdownPlayer extends ActorBeta {

    enum AnimState{
        IDLE,
        MOVELEFT,
        MOVERIGHT,
        MOVEUP,
        MOVEDOWN
    }

    //Load Animations
    String[] IdleAnim = {"Player/Soldier-01-1_02.png"};
    String[] MoveDownAnim = {"Player/Soldier-01-1_02.png","Player/Soldier-01-1_01.png","Player/Soldier-01-1_02.png","Player/Soldier-01-1_03.png"};
    String[] MoveUpAnim = {"Player/Soldier-01-1_11.png","Player/Soldier-01-1_10.png","Player/Soldier-01-1_11.png","Player/Soldier-01-1_12.png"};
    String[] MoveLeftAnim = {"Player/Soldier-01-1_05.png","Player/Soldier-01-1_04.png","Player/Soldier-01-1_05.png","Player/Soldier-01-1_06.png"};
    String[] MoveRightAnim = {"Player/Soldier-01-1_08.png","Player/Soldier-01-1_09.png","Player/Soldier-01-1_08.png","Player/Soldier-01-1_07.png"};
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

    @Override
    public void act(float dt) {
        super.act(dt);
        switch (animState)
        {
            case IDLE:
                Animation<TextureRegion> idleAnim;
                idleAnim = loadAnimationFromFiles(IdleAnim, 0.1f, true);
                setAnimation(idleAnim);
                break;
            case MOVEDOWN:
                Animation<TextureRegion> moveDownAnim;
                moveDownAnim = loadAnimationFromFiles(MoveDownAnim, 0.1f, true);
                setAnimation(moveDownAnim);
                break;
            case MOVEUP:
                Animation<TextureRegion> moveUpAnim;
                moveUpAnim = loadAnimationFromFiles(MoveUpAnim, 0.1f, true);
                setAnimation(moveUpAnim);
                break;
            case MOVELEFT:
                Animation<TextureRegion> moveLeftAnim;
                moveLeftAnim = loadAnimationFromFiles(MoveLeftAnim, 0.1f, true);
                setAnimation(moveLeftAnim);
                break;
            case MOVERIGHT:
                Animation<TextureRegion> moveRightAnim;
                moveRightAnim = loadAnimationFromFiles(MoveRightAnim, 0.1f, true);
                setAnimation(moveRightAnim);
                break;
            default:
                break;
        }
        //this.boundToWorld();
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

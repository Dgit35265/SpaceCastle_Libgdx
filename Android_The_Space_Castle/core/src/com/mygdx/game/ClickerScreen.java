package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sun.org.apache.regexp.internal.RESyntaxException;

import java.awt.HeadlessException;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class ClickerScreen extends ScreenBeta {
    Texture BGTex, GoldTex, CrystalTex, ListBGTex;
    Texture TableTex;
    Image BGImg, GoldImg, CrystalImg;
    Label GoldLbl, CrystalLbl;
    ImageTextButton SwitchBtn, MoneyBtn, OptBtn;
    ImageTextButton Btn1_1;
    ImageTextButton LeaveBtn;


    //GameData
    int Gold;

    boolean isReleased, isTable1;

    Table ResourceTable, ClickerTable1, ClickerTable2, Btns;

    @Override
    public void initialize() {
        //if new game, gold = 0
        Gold = 0;

        isReleased = true;
        isTable1 = true;

        //Load Textures
        BGTex = new Texture("MainMenuBG.jpg");
        GoldTex = new Texture("Gold.png");
        CrystalTex = new Texture("Crystal.png");
        ListBGTex = new Texture("resourceListBG.png");
        //TableTex = new Texture("TableBG.png");
        //create Images
        BGImg = new Image(BGTex);
        GoldImg = new Image(GoldTex);
        CrystalImg = new Image(CrystalTex);
        //Labels
        GoldLbl = new Label("0", uiSkin);
        CrystalLbl = new Label("0", uiSkin);
        //Buttons
        SwitchBtn = new ImageTextButton("SwitchTable", uiSkin);
        MoneyBtn = new ImageTextButton("Get Money!", uiSkin);
        OptBtn = new ImageTextButton("Option", uiSkin);
        Btn1_1 = new ImageTextButton("Btn1_1", uiSkin);
        LeaveBtn = new ImageTextButton("To Space", uiSkin);
        //Resource list
        ResourceTable = new Table();
        ResourceTable.setSize(WIDTH, 100);
        ResourceTable.setPosition(WIDTH/2 - (ResourceTable.getWidth()/2), HEIGHT - ResourceTable.getHeight());
        ResourceTable.setBackground(new TextureRegionDrawable(new TextureRegion(ListBGTex)));
        ResourceTable.add(GoldImg).maxWidth(100).maxHeight(100);
        ResourceTable.add(GoldLbl).padRight(400);
        ResourceTable.add(CrystalImg).maxWidth(100).maxHeight(100);
        ResourceTable.add(CrystalLbl);
        //Clicker Tables
        ClickerTable1 = new Table();
        ClickerTable1.setSize(1400, HEIGHT/8);
        ClickerTable1.setPosition(WIDTH/2 - (ClickerTable1.getWidth()/2), HEIGHT - (ClickerTable1.getHeight()* 3));
        //ClickerTable1.setBackground(new TextureRegionDrawable(new TextureRegion(TableTex)));
        ClickerTable1.add(Btn1_1);

        ClickerTable2 = new Table();
        ClickerTable2.setSize(1400, HEIGHT/8);
        ClickerTable2.setPosition(WIDTH/2, HEIGHT - (ClickerTable2.getHeight()* 3));
        ClickerTable2.add(LeaveBtn);
        ClickerTable2.getColor().a = 0;

        //Buttons Table
        Btns = new Table();
        Btns.setSize(WIDTH, 200);
        Btns.setPosition(WIDTH/2 - (Btns.getWidth()/2), 0);
        Btns.add(SwitchBtn).expandX();
        Btns.add(MoneyBtn).expandX();
        Btns.add(OptBtn).expandX();

        uiStage.addActor(BGImg);
        uiStage.addActor(ResourceTable);
        uiStage.addActor(ClickerTable1);
        uiStage.addActor(ClickerTable2);
        uiStage.addActor(Btns);

    }

    @Override
    public void update(float dt) {
        //Gdx.app.log("TableY: ", Float.toString(WIDTH/2 - (ClickerTable1.getWidth()/2)));
        if(MoneyBtn.isPressed() && isReleased)
        {
            Gold++;
            GoldLbl.setText(Integer.toString(Gold));
            isReleased = false;
        }
        if(!MoneyBtn.isPressed() && !SwitchBtn.isPressed() && !isReleased)
        {
            isReleased = true;
        }
        if(OptBtn.isPressed())
        {
            OptionScreen.isClicker = true;
            SpaceCastle.setActiveScreen(new OptionScreen());
        }
        if(SwitchBtn.isPressed() && isReleased)
        {
            if(isTable1)
            {
                ClickerTable1.addAction(Actions.parallel(Actions.fadeOut(1.f), Actions.moveBy(-ClickerTable1.getWidth() / 2, 0, 1.f)));
                ClickerTable2.addAction(Actions.parallel(Actions.fadeIn(1.f), Actions.moveBy(-ClickerTable2.getWidth() / 2, 0, 1.f)));
                isTable1 = false;
            }
            else if(!isTable1)
            {
                ClickerTable1.addAction(Actions.parallel(Actions.fadeIn(1.f), Actions.moveBy(ClickerTable1.getWidth() / 2, 0, 1.f)));
                ClickerTable2.addAction(Actions.parallel(Actions.fadeOut(1.f), Actions.moveBy(ClickerTable2.getWidth() / 2, 0, 1.f)));
                isTable1 = true;
            }
            isReleased = false;
        }
        if(LeaveBtn.isPressed())
        {
            SpaceCastle.setActiveScreen(new TileMapScreen());
        }
    }
}

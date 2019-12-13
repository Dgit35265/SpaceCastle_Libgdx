package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sun.org.apache.regexp.internal.RESyntaxException;

import java.awt.HeadlessException;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class ClickerScreen extends ScreenBeta {
    Texture BGTex, GoldTex, CrystalTex, OilTex, MetalTex, AmmoTex, InhabitantTex, FoodTex, ListBGTex;
    Texture TableTex;
    Image BGImg, GoldImg, CrystalImg, OilImage, MetalImage, AmmoImage, InhabitantImg, FoodImg;
    Label GoldLbl, CrystalLbl, OilLbl, MetalLbl, AmmoLbl, InhabitantLbl, FoodLbl, AutoFoodLbl;
    ImageTextButton SwitchBtn, MoneyBtn, OptBtn;
    ImageTextButton CrystalUpgradeBtn, OilUpgradeBtn, MetalUpgradeBtn, AmmoUpgradeBtn, AutoFoodUpgradeBtn;
    ImageTextButton CrystalGenerateBtn, OilGenerateBtn, MetalGenerateBtn, AmmoGenerateBtn;
    ImageTextButton BuyFoodBtn;
    ImageTextButton LeaveBtn;

    //Progress bars for resource generate
    ProgressBar CrystalBar, OilBar, MetalBar, AmmoBar, ConsumeBar, AutoFoodBar;

    boolean isReleased, isTable1;

    Table ResourceTable, ResourceGenerateTable, PrepareTable, Btns;

    //SFXs
    Sound GoldSFX, CompleteSFX, UpgradeSFX;

    float CrystalTime, OilTime, MetalTime, AmmoTime; //can dynamically change later
    float CrystalTimer, OilTimer, MetalTimer, AmmoTimer; //For call the generate function
    float FoodConsumeTimer, FoodConsumeTime, AutoFoodTimer, AutoFoodTime;
    boolean CrystalGenrating, OilGenerating, MetalGenrating, AmmoGenrating;

    @Override
    public void initialize() {
        isReleased = true;
        isTable1 = true;
        CrystalTime = 10.0f;
        OilTime = 20.0f;
        MetalTime = 30.0f;
        AmmoTime = 60.0f;
        FoodConsumeTimer = 0;
        FoodConsumeTime = 10; //set 10s for test
        AutoFoodTime = 5;//set 5s for test

        //Load Textures
        BGTex = new Texture("MainMenuBG.jpg");
        GoldTex = new Texture("Gold.png");
        CrystalTex = new Texture("Crystal.png");
        OilTex = new Texture("Oil_Barrel.png");
        MetalTex = new Texture("Iron_Metal.png");
        AmmoTex = new Texture("Ammo.png");
        InhabitantTex = new Texture("Inhabitant_Icon.png");
        FoodTex = new Texture("Food_Icon.png");
        ListBGTex = new Texture("resourceListBG.png");
        //TableTex = new Texture("TableBG.png");
        //create Images
        BGImg = new Image(BGTex);
        GoldImg = new Image(GoldTex);
        CrystalImg = new Image(CrystalTex);
        OilImage = new Image(OilTex);
        MetalImage = new Image(MetalTex);
        AmmoImage = new Image(AmmoTex);
        InhabitantImg = new Image(InhabitantTex);
        FoodImg = new Image(FoodTex);
        //Labels
        GoldLbl = new Label("0", uiSkin);
        CrystalLbl = new Label("0", uiSkin);
        OilLbl = new Label("0", uiSkin);
        MetalLbl = new Label("0", uiSkin);
        AmmoLbl = new Label("0", uiSkin);
        InhabitantLbl = new Label("0", uiSkin);
        FoodLbl = new Label("0", uiSkin);
        AutoFoodLbl = new Label("", uiSkin);
        //Buttons
        SwitchBtn = new ImageTextButton("SwitchTable", uiSkin);
        MoneyBtn = new ImageTextButton("Get Money!", uiSkin);
        OptBtn = new ImageTextButton("Option", uiSkin);
        LeaveBtn = new ImageTextButton("To Space", uiSkin);
        //Button for Buy Food
        BuyFoodBtn = new ImageTextButton("Purchase Food(10G)", uiSkin);
        //Button for Upgrade Resource Generator
        CrystalUpgradeBtn = new ImageTextButton("Crystal Mining Machine(5G)", uiSkin);
        OilUpgradeBtn = new ImageTextButton("Oil Digging Machine(10G,5C)", uiSkin);
        MetalUpgradeBtn = new ImageTextButton("Metal Merchant(15G,10C,5O)", uiSkin);
        AmmoUpgradeBtn = new ImageTextButton("Ammo Factory(20G,15C,10O,5M)", uiSkin);
        AutoFoodUpgradeBtn = new ImageTextButton("Buy Food Factory(1000G, 200C, 100O, 50M)", uiSkin);
        //Buttons for Resource Generating
        CrystalGenerateBtn = new ImageTextButton("CrystalGenerate", uiSkin);
        OilGenerateBtn = new ImageTextButton("OilGenerate", uiSkin);
        MetalGenerateBtn = new ImageTextButton("MetalGenerate", uiSkin);
        AmmoGenerateBtn = new ImageTextButton("AmmoGenerate", uiSkin);
        //ProgressBars
        CrystalBar = new ProgressBar(0, CrystalTime, 1, false, uiSkin);
        OilBar = new ProgressBar(0, OilTime, 1, false, uiSkin);
        MetalBar = new ProgressBar(0, MetalTime, 1, false, uiSkin);
        AmmoBar = new ProgressBar(0, AmmoTime, 1, false, uiSkin);
        ConsumeBar = new ProgressBar(0, FoodConsumeTime, 1, false, uiSkin);
        AutoFoodBar = new ProgressBar(0, AutoFoodTime, 1, false, uiSkin);

        //Resource list
        ResourceTable = new Table();
        ResourceTable.setSize(WIDTH, 100);
        ResourceTable.setPosition(WIDTH/2 - (ResourceTable.getWidth()/2), HEIGHT - ResourceTable.getHeight());
        ResourceTable.setBackground(new TextureRegionDrawable(new TextureRegion(ListBGTex)));
        ResourceTable.add(GoldImg).maxWidth(100).maxHeight(100);
        ResourceTable.add(GoldLbl).padLeft(100).padRight(100);
        ResourceTable.add(CrystalImg).maxWidth(100).maxHeight(100);
        ResourceTable.add(CrystalLbl).padLeft(100).padRight(100);
        ResourceTable.add(OilImage).maxWidth(100).maxHeight(100);
        ResourceTable.add(OilLbl).padLeft(100).padRight(100);
        ResourceTable.add(MetalImage).maxWidth(100).maxHeight(100);
        ResourceTable.add(MetalLbl).padLeft(100).padRight(100);
        ResourceTable.add(AmmoImage).maxWidth(100).maxHeight(100);
        ResourceTable.add(AmmoLbl).padLeft(100).padRight(100);
        ResourceTable.add(InhabitantImg).maxWidth(100).maxHeight(100);
        ResourceTable.add(InhabitantLbl).padLeft(100).padRight(100);
        ResourceTable.add(FoodImg).maxWidth(100).maxHeight(100);
        ResourceTable.add(FoodLbl).padLeft(100).padRight(100);
        ResourceTable.add(ConsumeBar);
        RefreshResource();
        //Resource Generate Tables
        ResourceGenerateTable = new Table();
        ResourceGenerateTable.setSize(1400, HEIGHT/2);
        ResourceGenerateTable.setPosition(WIDTH/2 - (ResourceGenerateTable.getWidth()/2), HEIGHT/2 - (ResourceGenerateTable.getHeight()/2));
        //ClickerTable1.setBackground(new TextureRegionDrawable(new TextureRegion(TableTex)));
        ResourceGenerateTable.add(CrystalUpgradeBtn);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(CrystalGenerateBtn);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(CrystalBar);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(OilUpgradeBtn);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(OilGenerateBtn);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(OilBar);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(MetalUpgradeBtn);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(MetalGenerateBtn);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(MetalBar);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(AmmoUpgradeBtn);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(AmmoGenerateBtn);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(AmmoBar);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(AutoFoodUpgradeBtn);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(AutoFoodBar);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(AutoFoodLbl);
        //Hide AutoFood at the beginning
        AutoFoodUpgradeBtn.getColor().a = 0;
        AutoFoodBar.getColor().a = 0;
        AutoFoodLbl.getColor().a = 0;
        //Check All Generator Buttons at the beginning
        if(SpaceCastle.CrystalUpgrade)
        {
            ResourceGenerateTable.removeActor(CrystalUpgradeBtn);
            CrystalGenerateBtn.getColor().a = 1;
            CrystalBar.getColor().a = 1;
        }
        else
        {
            CrystalGenerateBtn.getColor().a = 0;
            CrystalBar.getColor().a = 0;
        }
        if(SpaceCastle.OilUpgrade)
        {
            ResourceGenerateTable.removeActor(OilUpgradeBtn);
            OilGenerateBtn.getColor().a = 1;
            OilBar.getColor().a = 1;
        }
        else
        {
            OilGenerateBtn.getColor().a = 0;
            OilBar.getColor().a = 0;
        }
        if(SpaceCastle.MetalUpgrade)
        {
            ResourceGenerateTable.removeActor(MetalUpgradeBtn);
            MetalGenerateBtn.getColor().a = 1;
            MetalBar.getColor().a = 1;
        }
        else
        {
            MetalGenerateBtn.getColor().a = 0;
            MetalBar.getColor().a = 0;
        }
        if(SpaceCastle.AmmoUpgrade)
        {
            ResourceGenerateTable.removeActor(AmmoUpgradeBtn);
            AmmoGenerateBtn.getColor().a = 1;
            AmmoBar.getColor().a = 1;
        }
        else
        {
            AmmoGenerateBtn.getColor().a = 0;
            AmmoBar.getColor().a = 0;
        }

        //Prepare Table
        PrepareTable = new Table();
        PrepareTable.setSize(1400, HEIGHT/8);
        PrepareTable.setPosition(WIDTH/2, HEIGHT - (PrepareTable.getHeight()* 3));
        PrepareTable.add(LeaveBtn);
        PrepareTable.getColor().a = 0;

        //Buttons Table
        Btns = new Table();
        Btns.setSize(WIDTH, 200);
        Btns.setPosition(WIDTH/2 - (Btns.getWidth()/2), 0);
        Btns.add(SwitchBtn).expandX();
        Btns.add(BuyFoodBtn).expandX();
        Btns.add(MoneyBtn).expandX();
        Btns.add(OptBtn).expandX();

        //SFXs
        GoldSFX = Gdx.audio.newSound(Gdx.files.internal("Sounds/Gold_Generate_SFX.wav"));
        CompleteSFX = Gdx.audio.newSound(Gdx.files.internal("Sounds/Generate_Complete_SFX.wav"));
        UpgradeSFX = Gdx.audio.newSound(Gdx.files.internal("Sounds/Upgrade_SpaceCastle_SFX.wav"));

        uiStage.addActor(BGImg);
        uiStage.addActor(ResourceTable);
        uiStage.addActor(ResourceGenerateTable);
        uiStage.addActor(PrepareTable);
        uiStage.addActor(Btns);
    }

    @Override
    public void update(float dt) {
        //Gdx.app.log("TableY: ", Float.toString(WIDTH/2 - (ClickerTable1.getWidth()/2)));
        if(MoneyBtn.isPressed() && isReleased)
        {
            SpaceCastle.Gold++;
            RefreshResource();
            GoldSFX.play();
            isReleased = false;
        }
        //Prevent Click Btn in frames
        if(!MoneyBtn.isPressed() && !SwitchBtn.isPressed() && !CrystalUpgradeBtn.isPressed() && !OilUpgradeBtn.isPressed()
                && !MetalUpgradeBtn.isPressed() && !AmmoUpgradeBtn.isPressed() && !BuyFoodBtn.isPressed()
                && !AutoFoodUpgradeBtn.isPressed() && !isReleased)
            isReleased = true;

        if(OptBtn.isPressed())
        {
            OptionScreen.isClicker = true;
            SpaceCastle.setActiveScreen(new OptionScreen());
            dispose();
        }
        //Resource Generator Upgrade Buttons
        if(CrystalUpgradeBtn.isPressed() && isReleased)
        {
            int GoldNeed = 5;
            if(SpaceCastle.Gold >= GoldNeed)
            {
                SpaceCastle.Gold -= GoldNeed;
                RefreshResource();
                ResourceGenerateTable.removeActor(CrystalUpgradeBtn);
                SpaceCastle.CrystalUpgrade = true;
                CrystalGenerateBtn.getColor().a = 1;
                CrystalBar.getColor().a = 1;
                UpgradeSFX.play();
            }
            isReleased = false;
        }
        if(OilUpgradeBtn.isPressed() && isReleased)
        {
            int GoldNeed = 10;
            int CrystalNeed = 5;
            if(SpaceCastle.Gold >= GoldNeed && SpaceCastle.Crystal >= CrystalNeed)
            {
                SpaceCastle.Gold -= GoldNeed;
                SpaceCastle.Crystal -= CrystalNeed;
                RefreshResource();
                ResourceGenerateTable.removeActor(OilUpgradeBtn);
                SpaceCastle.OilUpgrade = true;
                OilGenerateBtn.getColor().a = 1;
                OilBar.getColor().a = 1;
                UpgradeSFX.play();
            }
            isReleased = false;
        }
        if(MetalUpgradeBtn.isPressed() && isReleased)
        {
            int GoldNeed = 15;
            int CrystalNeed = 10;
            int OilNeed = 5;
            if(SpaceCastle.Gold >= GoldNeed && SpaceCastle.Crystal >= CrystalNeed && SpaceCastle.Oil >= OilNeed)
            {
                SpaceCastle.Gold -= GoldNeed;
                SpaceCastle.Crystal -= CrystalNeed;
                SpaceCastle.Oil -= OilNeed;
                RefreshResource();
                ResourceGenerateTable.removeActor(MetalUpgradeBtn);
                SpaceCastle.MetalUpgrade = true;
                MetalGenerateBtn.getColor().a = 1;
                MetalBar.getColor().a = 1;
                UpgradeSFX.play();
            }
            isReleased = false;
        }
        if(AmmoUpgradeBtn.isPressed() && isReleased)
        {
            int GoldNeed = 20;
            int CrystalNeed = 15;
            int OilNeed = 10;
            int MetalNeed = 5;
            if(SpaceCastle.Gold >= GoldNeed && SpaceCastle.Crystal >= CrystalNeed && SpaceCastle.Oil >= OilNeed && SpaceCastle.Metal >= MetalNeed)
            {
                SpaceCastle.Gold -= GoldNeed;
                SpaceCastle.Crystal -= CrystalNeed;
                SpaceCastle.Oil -= OilNeed;
                SpaceCastle.Metal -= MetalNeed;
                RefreshResource();
                ResourceGenerateTable.removeActor(AmmoUpgradeBtn);
                SpaceCastle.AmmoUpgrade = true;
                AmmoGenerateBtn.getColor().a = 1;
                AmmoBar.getColor().a = 1;
                UpgradeSFX.play();
            }
            isReleased = false;
        }
        if(AutoFoodUpgradeBtn.isPressed() && isReleased)
        {
            int GoldNeed = 1;
            int CrystalNeed = 1;
            int OilNeed = 1;
            int MetalNeed = 1;
            if(SpaceCastle.Gold >= GoldNeed && SpaceCastle.Crystal >= CrystalNeed && SpaceCastle.Oil >= OilNeed && SpaceCastle.Metal >= MetalNeed)
            {
                SpaceCastle.Gold -= GoldNeed;
                SpaceCastle.Crystal -= CrystalNeed;
                SpaceCastle.Oil -= OilNeed;
                SpaceCastle.Metal -= MetalNeed;
                RefreshResource();
                SpaceCastle.AutoFoodNum += 1;
                AutoFoodBar.getColor().a = 1;
                AutoFoodLbl.getColor().a = 1;
                AutoFoodLbl.setText((SpaceCastle.AutoFoodNum) + "X");
                UpgradeSFX.play();
            }
            isReleased = false;
        }

        //Show AutoFoodUpgrade Option
        if(SpaceCastle.CrystalUpgrade && SpaceCastle.OilUpgrade && SpaceCastle.MetalUpgrade)
            AutoFoodUpgradeBtn.getColor().a = 1;

        //Resource Generate Table Buttons
        if(CrystalGenerateBtn.isPressed())
        {
            if(!CrystalGenrating)
            {
                CrystalTimer = CrystalTime;
                CrystalGenrating = true;
            }
        }
        if(OilGenerateBtn.isPressed())
        {
            if(!OilGenerating)
            {
                OilTimer = OilTime;
                OilGenerating = true;
            }
        }
        if(MetalGenerateBtn.isPressed())
        {
            if(!MetalGenrating)
            {
                MetalTimer = MetalTime;
                MetalGenrating = true;
            }
        }
        if(AmmoGenerateBtn.isPressed())
        {
            if(!AmmoGenrating)
            {
                AmmoTimer = AmmoTime;
                AmmoGenrating = true;
            }
        }

        //Switch Table
        if(SwitchBtn.isPressed() && isReleased)
        {
            if(isTable1)
            {
                ResourceGenerateTable.addAction(Actions.parallel(Actions.fadeOut(1.f), Actions.moveBy(-ResourceGenerateTable.getWidth() / 2, 0, 1.f)));
                PrepareTable.addAction(Actions.parallel(Actions.fadeIn(1.f), Actions.moveBy(-PrepareTable.getWidth() / 2, 0, 1.f)));
                isTable1 = false;
            }
            else if(!isTable1)
            {
                ResourceGenerateTable.addAction(Actions.parallel(Actions.fadeIn(1.f), Actions.moveBy(ResourceGenerateTable.getWidth() / 2, 0, 1.f)));
                PrepareTable.addAction(Actions.parallel(Actions.fadeOut(1.f), Actions.moveBy(PrepareTable.getWidth() / 2, 0, 1.f)));
                isTable1 = true;
            }
            isReleased = false;
        }
        if(LeaveBtn.isPressed()) //To Space
        {
            SpaceCastle.setActiveScreen(new TopdownGame1());
        }

        //Call Generating
        if(CrystalGenrating)
        {
            CrystalTimer -= dt;
            CrystalBar.setValue(CrystalTime - CrystalTimer);
            //Gdx.app.log("timer", Float.toString(CrystalTimer));
            if(CrystalTimer <= 0)
                GenerateCrystal();
        }
        if(OilGenerating)
        {
            OilTimer -= dt;
            OilBar.setValue(OilTime - OilTimer);
            if(OilTimer <= 0)
                GenerateOil();
        }
        if(MetalGenrating)
        {
            MetalTimer -= dt;
            MetalBar.setValue(MetalTime - MetalTimer);
            if(MetalTimer <= 0)
                GenerateMetal();
        }
        if(AmmoGenrating)
        {
            AmmoTimer -= dt;
            AmmoBar.setValue(AmmoTime - AmmoTimer);
            if(AmmoTimer <= 0)
                GenerateAmmo();
        }
        //Food Part
        if(BuyFoodBtn.isPressed() && isReleased)
        {
            int GoldNeed = 10;
            if(SpaceCastle.Gold >= GoldNeed)
            {
                SpaceCastle.Gold -= GoldNeed;
                SpaceCastle.Food += 1;
                RefreshResource();
            }
            isReleased = false;
        }

        //Consume Food base on FoodConsumeTime
        FoodConsumeTimer += dt;
        ConsumeBar.setValue(FoodConsumeTimer);
        if(FoodConsumeTimer >= FoodConsumeTime)
        {
            ConsumeFood();
            FoodConsumeTimer = 0;
        }

        //Auto Food base on AutoFoodTime
        if(SpaceCastle.AutoFoodNum >= 1)
        {
            AutoFoodTimer += dt;
            AutoFoodBar.setValue(AutoFoodTimer);
            if(AutoFoodTimer >= AutoFoodTime)
            {
                AutoFood();
                AutoFoodTimer = 0;
                CompleteSFX.play();
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    // Generate Resources Functions
    public void GenerateCrystal()
    {
        SpaceCastle.Crystal++;
        CrystalLbl.setText(Integer.toString(SpaceCastle.Crystal));
        CompleteSFX.play();
        CrystalGenrating = false;
        CrystalTimer = CrystalTime;
        CrystalBar.setValue(CrystalTime - CrystalTimer);
    }

    public void GenerateOil()
    {
        SpaceCastle.Oil++;
        OilLbl.setText(Integer.toString(SpaceCastle.Oil));
        CompleteSFX.play();
        OilGenerating = false;
        OilTimer = OilTime;
        OilBar.setValue(OilTime - OilTimer);
    }

    public void GenerateMetal()
    {
        SpaceCastle.Metal++;
        MetalLbl.setText(Integer.toString(SpaceCastle.Metal));
        CompleteSFX.play();
        MetalGenrating = false;
        MetalTimer = MetalTime;
        MetalBar.setValue(MetalTime - MetalTimer);
    }

    public void GenerateAmmo()
    {
        SpaceCastle.Ammo += 10;
        AmmoLbl.setText(Integer.toString(SpaceCastle.Ammo));
        CompleteSFX.play();
        AmmoGenrating = false;
        AmmoTimer = AmmoTime;
        AmmoBar.setValue(AmmoTime - AmmoTimer);
    }
    public void RefreshResource()
    {
        GoldLbl.setText(Integer.toString(SpaceCastle.Gold));
        CrystalLbl.setText(Integer.toString(SpaceCastle.Crystal));
        OilLbl.setText(Integer.toString(SpaceCastle.Oil));
        MetalLbl.setText(Integer.toString(SpaceCastle.Metal));
        AmmoLbl.setText(Integer.toString(SpaceCastle.Ammo));
        InhabitantLbl.setText(Integer.toString(SpaceCastle.Inhabitant));
        FoodLbl.setText(Integer.toString(SpaceCastle.Food));
    }
    public void ConsumeFood()
    {
        int FoodNeed = 1 + (SpaceCastle.Inhabitant/50); //each amount of food for 50 ppl
        if(SpaceCastle.Food >= FoodNeed) //Enough Food
        {
            SpaceCastle.Inhabitant += 50;
            SpaceCastle.Food -= FoodNeed;
            if(SpaceCastle.Inhabitant >= SpaceCastle.WinInhabitants)
            {
                //Win Game!
            }
        }
        else // Not Enough Food
        {
            Gdx.app.log("Inhabitant", Integer.toString(SpaceCastle.Inhabitant/50));
            Gdx.app.log("Food", Integer.toString(SpaceCastle.Food));
            Gdx.app.log("reduce", Integer.toString(50*(SpaceCastle.Inhabitant/50 - SpaceCastle.Food)));
            SpaceCastle.Inhabitant -= 50*(SpaceCastle.Inhabitant/50 - SpaceCastle.Food);
            SpaceCastle.Food = 0;
            if(SpaceCastle.Inhabitant <= 0)
            {
                //Lose Game!
            }
        }
        RefreshResource();
    }
    public void AutoFood()
    {
        SpaceCastle.Food += SpaceCastle.AutoFoodNum;
        RefreshResource();
    }
}

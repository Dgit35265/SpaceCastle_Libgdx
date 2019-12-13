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
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.awt.HeadlessException;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class ClickerScreen extends ScreenBeta {
    Texture BGTex, GoldTex, CrystalTex, OilTex, MetalTex, AmmoTex, InhabitantTex, FoodTex, ListBGTex;
    Image BGImg, GoldImg, CrystalImg, OilImage, MetalImage, AmmoImage, InhabitantImg, FoodImg;
    Label GoldLbl, CrystalLbl, OilLbl, MetalLbl, AmmoLbl, InhabitantLbl, FoodLbl, AutoFoodLbl;
    ImageTextButton SwitchBtn, MoneyBtn, OptBtn;
    ImageTextButton CrystalUpgradeBtn, OilUpgradeBtn, MetalUpgradeBtn, AmmoUpgradeBtn, AutoFoodUpgradeBtn;
    ImageTextButton CrystalGenerateBtn, OilGenerateBtn, MetalGenerateBtn, AmmoGenerateBtn;
    ImageTextButton BuyFoodBtn;

    //Progress bars for resource generate
    ProgressBar CrystalBar, OilBar, MetalBar, AmmoBar, ConsumeBar, AutoFoodBar;

    boolean isReleased, isTable1;

    Table ResourceTable, ResourceGenerateTable, PrepareTable, Btns;

    Label PrepareLbl, AmmoTakenLbl, AmmoTakenNumLbl, FoodTakenLbl, FoodTakenNumLbl;
    Slider AmmoTakenSlider, FoodTakenSlider;
    ImageTextButton LeaveBtn;
    //SFXs
    Sound GoldSFX, CompleteSFX, UpgradeSFX;

    float CrystalTime, OilTime, MetalTime, AmmoTime; //can dynamically change later
    float CrystalTimer, OilTimer, MetalTimer, AmmoTimer; //For call the generate function
    float FoodConsumeTimer, FoodConsumeTime, AutoFoodTimer, AutoFoodTime;
    boolean CrystalGenrating, OilGenerating, MetalGenrating, AmmoGenrating;

    @Override
    public void initialize() {
        GameBGM.play();
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
        SwitchBtn = new ImageTextButton("Switch", uiSkin);
        MoneyBtn = new ImageTextButton("Get Money!", uiSkin);
        OptBtn = new ImageTextButton("Option", uiSkin);
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
        ResourceTable.setSize(WIDTH, HEIGHT/12);
        ResourceTable.setPosition(WIDTH/2 - (ResourceTable.getWidth()/2), HEIGHT - ResourceTable.getHeight());
        ResourceTable.setBackground(new TextureRegionDrawable(new TextureRegion(ListBGTex)));
        ResourceTable.add(GoldImg).maxWidth(HEIGHT/12).maxHeight(HEIGHT/12).padLeft(HEIGHT/12);
        ResourceTable.add(GoldLbl).expandX();
        ResourceTable.add(CrystalImg).maxWidth(HEIGHT/12).maxHeight(HEIGHT/12);
        ResourceTable.add(CrystalLbl).expandX();
        ResourceTable.add(OilImage).maxWidth(HEIGHT/12).maxHeight(HEIGHT/12);
        ResourceTable.add(OilLbl).expandX();
        ResourceTable.add(MetalImage).maxWidth(HEIGHT/12).maxHeight(HEIGHT/12);
        ResourceTable.add(MetalLbl).expandX();
        ResourceTable.add(AmmoImage).maxWidth(HEIGHT/12).maxHeight(HEIGHT/12);
        ResourceTable.add(AmmoLbl).expandX();
        ResourceTable.add(InhabitantImg).maxWidth(HEIGHT/12).maxHeight(HEIGHT/12);
        ResourceTable.add(InhabitantLbl).expandX();
        ResourceTable.add(FoodImg).maxWidth(HEIGHT/12).maxHeight(HEIGHT/12);
        ResourceTable.add(FoodLbl).expandX();
        ResourceTable.add(ConsumeBar).expandX();

        RefreshResource();
        //Resource Generate Tables
        ResourceGenerateTable = new Table();
        ResourceGenerateTable.setSize(WIDTH/2, HEIGHT/2);
        ResourceGenerateTable.setPosition(WIDTH/2 - (ResourceGenerateTable.getWidth()/2), HEIGHT/2 - (ResourceGenerateTable.getHeight()/2));
        ResourceGenerateTable.add(CrystalUpgradeBtn).colspan(2);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(CrystalGenerateBtn).expandX();
        ResourceGenerateTable.add(CrystalBar).expandX();
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(OilUpgradeBtn).colspan(2);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(OilGenerateBtn).expandX();
        ResourceGenerateTable.add(OilBar).expandX();
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(MetalUpgradeBtn).colspan(2);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(MetalGenerateBtn).expandX();
        ResourceGenerateTable.add(MetalBar).expandX();
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(AmmoUpgradeBtn).colspan(2);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(AmmoGenerateBtn).expandX();
        ResourceGenerateTable.add(AmmoBar).expandX();
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(AutoFoodUpgradeBtn).colspan(2);
        ResourceGenerateTable.row();
        ResourceGenerateTable.add(AutoFoodBar).expandX();
        ResourceGenerateTable.add(AutoFoodLbl).expandX();
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
        //Labels
        PrepareLbl = new Label("Prepare to Dungeon...", uiSkin);
        AmmoTakenLbl = new Label("Ammo Carried", uiSkin);
        AmmoTakenNumLbl = new Label("0", uiSkin);
        FoodTakenLbl = new Label("Food Carried", uiSkin);
        FoodTakenNumLbl = new Label("0", uiSkin);
        //Sliders
        AmmoTakenSlider = new Slider(0, SpaceCastle.Ammo, 1, false, uiSkin);
        FoodTakenSlider = new Slider(0, SpaceCastle.Food, 1, false, uiSkin);
        //Buttons
        LeaveBtn = new ImageTextButton("Squad Departure(50ppl)", uiSkin);
        //Prepare Table
        PrepareTable = new Table();
        PrepareTable.setSize(WIDTH/2, HEIGHT/2);
        PrepareTable.setPosition(WIDTH/2, HEIGHT/2 - (PrepareTable.getHeight()/2));
        PrepareTable.add(PrepareLbl).colspan(2);
        PrepareTable.row();
        PrepareTable.add(AmmoTakenLbl).padTop(HEIGHT/10).expandX();
        PrepareTable.add(FoodTakenLbl).padTop(HEIGHT/10).expandX();
        PrepareTable.row();
        PrepareTable.add(AmmoTakenSlider).padTop(HEIGHT/10).expandX();
        PrepareTable.add(FoodTakenSlider).padTop(HEIGHT/10).expandX();
        PrepareTable.row();
        PrepareTable.add(AmmoTakenNumLbl).padTop(HEIGHT/10).expandX();
        PrepareTable.add(FoodTakenNumLbl).padTop(HEIGHT/10).expandX();
        PrepareTable.row();
        PrepareTable.add(LeaveBtn).colspan(2);
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
        SpaceCastle.TotalTime += dt;
        Gdx.app.log("TTime", Float.toString(SpaceCastle.TotalTime));
        //Check Win and Lose Condition
        if(SpaceCastle.Inhabitant >= SpaceCastle.WinInhabitants)
        {
            Win();
        }
        if(SpaceCastle.Inhabitant <= 0)
        {
            Lose();
        }
        //Check if Slider Dragging
        if(AmmoTakenSlider.isDragging())
        {
            SpaceCastle.S_Ammo = (int)AmmoTakenSlider.getValue();
            AmmoTakenNumLbl.setText(SpaceCastle.S_Ammo);
        }
        if(FoodTakenSlider.isDragging())
        {
            SpaceCastle.S_Food = (int)FoodTakenSlider.getValue();
            FoodTakenNumLbl.setText(SpaceCastle.S_Food);
        }
        //Button Pressed
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
            SpaceCastle.S_Inhabitant = 50;
            SpaceCastle.Ammo -= SpaceCastle.S_Ammo;
            SpaceCastle.Inhabitant -= SpaceCastle.S_Inhabitant;
            SpaceCastle.Food -= SpaceCastle.S_Food;
            SpaceCastle.setActiveScreen(new TopdownGame1());
            dispose();
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
        GameBGM.stop();
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
        }
        else // Not Enough Food
        {
            Gdx.app.log("Inhabitant", Integer.toString(SpaceCastle.Inhabitant/50));
            Gdx.app.log("Food", Integer.toString(SpaceCastle.Food));
            Gdx.app.log("reduce", Integer.toString(50*(SpaceCastle.Inhabitant/50 - SpaceCastle.Food)));
            SpaceCastle.Inhabitant -= 50*(SpaceCastle.Inhabitant/50 - SpaceCastle.Food);
            SpaceCastle.Food = 0;
        }
        RefreshResource();
    }
    public void AutoFood()
    {
        SpaceCastle.Food += SpaceCastle.AutoFoodNum;
        RefreshResource();
    }
    //Win and Lose
    public void Win()
    {
        SpaceCastle.setActiveScreen(new WinScreen());
        dispose();
    }
    public void Lose()
    {
        SpaceCastle.setActiveScreen(new LoseScreen());
        dispose();
    }
}

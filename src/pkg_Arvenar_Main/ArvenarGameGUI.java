/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;
import java.io.FileNotFoundException;
import static java.lang.Math.abs;
import java.util.Random;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import pkg_3DPack.*;
import pkg_Characters.Character_DataBase_NPC;
import pkg_Characters.Character_DataBase_PC;
import pkg_Maps.Map_DataBase;

/**
 *
 * @author TE332168
 */
public class ArvenarGameGUI{
       
    Stage gameRunStage = new Stage();
    Pane game2DUILayoutPane = new Pane();
        
    VBox escPopUpVBox = new VBox();
    
    Pane controlsPane = new Pane();
    Pane popupPane = new Pane();
    Scene gameMainScene = new Scene(game2DUILayoutPane);
    SubScene subScene3DWorld;
    Group group3DWorld = new Group();
    Group uiControlGroup = new Group();
    Group groupForHero = new Group();
    
    HBox dialogHBox = new HBox();
    ImageView pirateTooltipImageView, pirateDialogImageView, heroTooltipImageView;
    Image heroImage, pirateImage;
    
    Image bkgImage = new Image("img/skyworld.png", 1920, 1080, true, false, true);
    
    Text dialogText = new Text();
    Text infoText = new Text();
    Text escPopupHeadText, optReturnToGameText, optExitToMainMenuText, optSettingsText;
    Text pirateNameLabel = new Text();
    Text heroNameLabel = new Text();
    
    static Weather weather = new Weather();
       
    Character_DataBase_NPC dbaseNPC = new Character_DataBase_NPC();
    Character_DataBase_PC dbasePC = new Character_DataBase_PC();        
    Map_DataBase dbaseMAPS = new Map_DataBase();
    
    ArvenarEffects arvfx = new ArvenarEffects();
    ArvenarButtons arvbuttons = new ArvenarButtons();
    ArvenarSettings arvset;
    ArvenarFonts arvfonts = new ArvenarFonts();
    
    //Arvenar3DObjects 3dobjects; --> HIBALEHETŐSÉG: számmal nem kezdődhet az azonosító!!
    Arvenar3DObjects objects3d;
    Arvenar3DTransforms transform3d;
    Arvenar3DObstacles obstacles3d = new Arvenar3DObstacles();
    Arvenar3DTerrains terrains3d = new Arvenar3DTerrains();
    Arvenar3DSkyBox skybox3d = new Arvenar3DSkyBox();
    
    Node hero3d, pirate3d, compass3d, selectednode;
                
    int hero_x = 300, hero_y = 150;
    int pirate_x = 205, pirate_y = 175;
    int j = 0;
    int rx = 0, ry = 0, rz = 0;
    
    private final DoubleProperty angleWorldX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleWorldY = new SimpleDoubleProperty(0);
    private final DoubleProperty angleHeroX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleHeroY = new SimpleDoubleProperty(0);
    
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final double MAXROTATIONANGLEX = 90; //does not flip over
    private final double MINROTATIONANGLEX = -90; //does not flip over
    private final int DEFAULTMOVEMENTSPEED = 20;
    private int speedModifier = 5;
    private int currentSpeed;
    
    
    
    int playableScreenXSize; //(int)(ArvenarFXMain.stageElven.getWidth() / 2);
    int playableScreenYSize; //(int)(ArvenarFXMain.stageElven.getHeight() / 2);
    final int SCREEN_MAX_X;
    final int SCREEN_MAX_Y;
    final int SCREEN_MIN_X;
    final int SCREEN_MIN_Y;
    
    Button btnPlayGame;
    Button btnExitGame;
    Button btnPlayRandomly;
    
    Button moveUpButton = new Button("N");
    Button moveDownButton = new Button("S");
    Button moveLeftButton = new Button("W");
    Button moveRightButton = new Button("E");
        
    Random random = new Random();
        
    PerspectiveCamera pcamera1 = new PerspectiveCamera(true);
         
    AmbientLight ambientlight;
    PointLight pointlight1;
                    
    public ArvenarGameGUI() throws FileNotFoundException, InterruptedException{ //ez nem lehet void, különben üres stage-t kapsz vissza!!
        
        objects3d = new Arvenar3DObjects();
        transform3d = new Arvenar3DTransforms();
        weather.createAnimation(game2DUILayoutPane, 0, 0, 1920, 1080,2);
              
        subScene3DWorld = new SubScene(group3DWorld, 1366, 768, true, SceneAntialiasing.BALANCED);
               
        playableScreenXSize = ArvenarFXMain.guiResolutionX-100; playableScreenYSize = ArvenarFXMain.guiResolutionY-100;
        
        SCREEN_MAX_X = 1200; 
        SCREEN_MAX_Y = 738;
        SCREEN_MIN_X = 30; 
        SCREEN_MIN_Y = 30;
        
       
       initNewGame(); 
       initPerspectiveCamera();
       initRotationControl();
        
        //------------------------------------------------------
        
        btnPlayGame = arvbuttons.actionButtons(btnPlayGame, "Play game", arvfx.setGlowEffect(0.5), null, Font.font("Verdana", FontWeight.BOLD, 12), 10, 20);     
        btnPlayRandomly = arvbuttons.actionButtons(btnPlayRandomly, "Play random", arvfx.setGlowEffect(0.5), null, Font.font("Verdana", FontWeight.BOLD, 12), 10, 50);
        btnExitGame = arvbuttons.actionButtons(btnExitGame, "Exit game", arvfx.setGlowEffect(0.5), null, Font.font("Verdana", FontWeight.BOLD, 12), 0, 320);     
        
        moveUpButton.setLayoutX(100); moveUpButton.setLayoutY(155);
        moveDownButton.setLayoutX(100); moveDownButton.setLayoutY(300);
        moveLeftButton.setLayoutX(25); moveLeftButton.setLayoutY(230);
        moveRightButton.setLayoutX(170); moveRightButton.setLayoutY(230);
        
        compass3d = objects3d.object3DCompass();
        compass3d.setLayoutX(110); compass3d.setLayoutY(240);
                              
        infoText = arvfx.setTextEffect(infoText, arvfx.glowEffect, null, Font.font("Verdana", FontWeight.BOLD, 20), Color.GOLD, ArvenarFXMain.guiResolutionX/2, 50);
        dialogText = arvfx.setTextEffect(dialogText, null, null, Font.font("Verdana", FontWeight.BOLD, 18), Color.BEIGE, 0, 10);
        
        heroNameLabel = arvfx.setTextEffect(heroNameLabel, null, null, Font.font("Verdana", FontWeight.BOLD, 14), Color.HONEYDEW, 0, 0);
        pirateNameLabel = arvfx.setTextEffect(pirateNameLabel, null, null, Font.font("Verdana", FontWeight.BOLD, 14), Color.HONEYDEW, 0, 0);
        
        escPopupHeadText = arvfonts.newTextFormat("Pause Menu", escPopupHeadText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 24), Color.CORAL, 0, 0);
        optReturnToGameText = arvfonts.newTextFormat("Return to game", optReturnToGameText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
        optExitToMainMenuText = arvfonts.newTextFormat("Exit to Main Menu", optExitToMainMenuText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
        optSettingsText = arvfonts.newTextFormat("Settings", optSettingsText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
        arvfx.btnTextEffects(optReturnToGameText); 
        arvfx.btnTextEffects(optExitToMainMenuText); 
        arvfx.btnTextEffects(optSettingsText); 
                
        
        //DIALOGHBOX for dialogText and avatar image      
        dialogHBox.setMinWidth(500);
        dialogHBox.setAlignment(Pos.CENTER);
        dialogHBox.setStyle("-fx-background-color: rgba(128, 64, 128, 0.4); -fx-background-radius: 5; -fx-spacing: 10; -fx-padding: 10"); //transparent and rounded dialog box
        dialogHBox.setVisible(false);
        dialogHBox.getChildren().add(dialogText); 
        dialogHBox.setLayoutX((ArvenarFXMain.guiResolutionX/2)-(dialogHBox.getPrefWidth()/2)); dialogHBox.setLayoutY(ArvenarFXMain.guiResolutionY-200);
        
                
        //SUBPANE for controls, compass and buttons
        controlsPane.setStyle("-fx-background-color: rgba(0, 50, 50, 0.6); -fx-background-radius: 5; -fx-padding: 10"); 
        controlsPane.setLayoutX(50); controlsPane.setLayoutY(50);
        controlsPane.getChildren().addAll(btnPlayGame, btnPlayRandomly, btnExitGame, moveUpButton, moveDownButton, moveLeftButton, moveRightButton, compass3d);
                
        escPopUpVBox.setMaxSize(500, 300); escPopUpVBox.setMinSize(500, 300);
        escPopUpVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.6); -fx-background-radius: 5; -fx-padding: 20;");
        escPopUpVBox.setSpacing(10); 
        escPopUpVBox.setAlignment(Pos.CENTER);
        escPopUpVBox.getChildren().addAll(escPopupHeadText, optReturnToGameText, optSettingsText, optExitToMainMenuText);
        popupPane.setStyle("-fx-background-color: rgba(0, 50, 50, 0.6); -fx-background-radius: 5;");
        
        popupPane.getChildren().add(escPopUpVBox);
        
                //GAMEMAINPANE - main pane 
        game2DUILayoutPane.setBackground(new Background(new BackgroundImage(bkgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        //gameMainPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        game2DUILayoutPane.getChildren().add(dialogHBox);
        
        //addLights();
              
        arvfx.buttonEffects(btnExitGame);
        arvfx.buttonEffects(btnPlayGame);
        arvfx.buttonEffects(btnPlayRandomly);
        
        
        //-------------
        
//Keyboard events
    //*i have tryed to implement here also the mouse control rotation logic, so that we can use here the same code now
        gameMainScene.setOnKeyPressed((KeyEvent kevent)->{ 
            
                currentSpeed = DEFAULTMOVEMENTSPEED;
                
                if(kevent.isShiftDown()) //run
                    {currentSpeed = DEFAULTMOVEMENTSPEED * speedModifier;}
                if(kevent.isControlDown()) //crouch
                    {currentSpeed = DEFAULTMOVEMENTSPEED / speedModifier;}
                                          
            switch  (kevent.getCode()){
                                                
                case W:  
                {check_HeroPos();}
                transform3d.rotateByXY(compass3d, -1, Rotate.X_AXIS);
                transform3d.move3DNode(groupForHero, 0, 0,currentSpeed);
                                                                
                break;

                case S: 
                {check_HeroPos();}
                transform3d.rotateByXY(compass3d, 1, Rotate.X_AXIS);
                transform3d.move3DNode(groupForHero, 0, 0,-currentSpeed); 
                
                break;

                case A: 
                transform3d.rotateByXY(compass3d, 1, Rotate.Y_AXIS);
                
                transform3d.move3DNode(groupForHero, -currentSpeed, 0, 0); 
                break;
                                
                case D: 
                transform3d.rotateByXY(compass3d, -1, Rotate.Y_AXIS);
                
                transform3d.move3DNode(groupForHero, currentSpeed, 0, 0); 
                break;
                                
                case UP:  angleWorldX.set(angleWorldX.get()+1);
                          angleHeroX.set(angleWorldX.get()*5);  
                break;
                    
                case DOWN: angleWorldX.set(angleWorldX.get()-1); 
                           angleHeroX.set(angleWorldX.get()*5);   
                break;
                    
                case LEFT: angleWorldY.set(angleWorldY.get()-1); 
                           angleHeroY.set(angleWorldY.get()*5);   
                break;
                
                case RIGHT: angleWorldY.set(angleWorldY.get()+1); 
                            angleHeroY.set(angleWorldY.get()*5);   
                break;
                
                case Q: groupForHero.translateYProperty().set(groupForHero.getTranslateY()-50);
                break;
                
                case E: groupForHero.translateYProperty().set(groupForHero.getTranslateY()+50);
                break;
                
                
                case ESCAPE: showPauseMenuPopupPane(); break;
            }
                    checkRotationAngles();
                    
                //System.out.println("Current speed: "+currentSpeed);
                System.out.println("AngleX: "+angleWorldX);
                    
            });
                        
        
//Mouse events
            
            hero3d.setOnMouseEntered(action -> {
                game2DUILayoutPane.getChildren().addAll(heroTooltipImageView, heroNameLabel);
                heroTooltipImageView.setFitHeight(50); heroTooltipImageView.setFitWidth(50);
                heroTooltipImageView.setTranslateX(500);
                heroTooltipImageView.setTranslateY(500);
                heroTooltipImageView.setTranslateZ(0);
                
                heroNameLabel.translateXProperty().set(heroTooltipImageView.getTranslateX());
                heroNameLabel.translateYProperty().set(heroTooltipImageView.getTranslateY()-10);
            });
                        
            hero3d.setOnMouseExited(action -> {
            game2DUILayoutPane.getChildren().removeAll(heroTooltipImageView, heroNameLabel);
            
            });
        
        
            pirate3d.setOnMouseEntered(action -> {
                game2DUILayoutPane.getChildren().addAll(pirateTooltipImageView, pirateNameLabel);
                pirateTooltipImageView.setFitHeight(50); pirateTooltipImageView.setFitWidth(50);
                pirateTooltipImageView.translateXProperty().set(500);
                pirateTooltipImageView.translateYProperty().set(500);
                pirateTooltipImageView.translateZProperty().set(0);
                
                pirateNameLabel.translateXProperty().set(pirateTooltipImageView.getTranslateX());
                pirateNameLabel.translateYProperty().set(pirateTooltipImageView.getTranslateY()-10);
            });
        
        
            pirate3d.setOnMouseExited(action -> {
                game2DUILayoutPane.getChildren().removeAll(pirateTooltipImageView, pirateNameLabel);
            
            });
        
            gameMainScene.setOnMousePressed((MouseEvent mevent) -> { //prepare values for mouse rotation
                anchorX = mevent.getSceneX();
                anchorY = mevent.getSceneY();
                anchorAngleX = angleWorldX.get();
                anchorAngleY = angleWorldY.get();
                           
               
            });
        
        
            gameMainScene.setOnMouseDragged((MouseEvent mevent) -> {
                
                angleWorldX.set(anchorAngleX - (mevent.getSceneY()- anchorY));
                angleWorldY.set(anchorAngleY + mevent.getSceneX() - anchorX);
                System.out.println("angleWorldX: "+angleWorldX.get()+" / angleWorldY: "+angleWorldY.get());
                angleHeroX.set(angleWorldX.get()); //hero looks in the opposite direction of world
                angleHeroY.set(angleWorldY.get());
                
                checkRotationAngles();
                 
            });
            
            gameMainScene.setOnScroll((ScrollEvent event) ->{
                double deltaY = event.getDeltaY();
                groupForHero.setTranslateZ(groupForHero.getTranslateZ()+deltaY);
                
            });
        
//select nodes - only for the tests
            hero3d.setOnMouseClicked((MouseEvent event) ->{
                selectednode = hero3d;
            });
            
                            
            pirate3d.setOnMouseClicked((MouseEvent event) ->{
                selectednode = pirate3d;
            });
            
            
            compass3d.setOnMouseClicked((MouseEvent event) ->{
                selectednode = pcamera1;
            });
            
            group3DWorld.setOnMouseClicked((MouseEvent event) ->{
                selectednode = group3DWorld;
            });
            
            
           
//Menu control
            btnExitGame.setOnAction(action -> {
                showPauseMenuPopupPane();
            
            });
        
            optReturnToGameText.setOnMouseClicked(action->{
                showPauseMenuPopupPane();
            });
        
            optExitToMainMenuText.setOnMouseClicked(action->{
                showPauseMenuPopupPane();
                ArvenarFXMain.stageElven.setScene(ArvenarFXMain.sceneElven);
                ArvenarFXMain.stageElven.setFullScreen(ArvenarFXMain.flagFullScreen == 1 ? true : false);
            });
        
            optSettingsText.setOnMouseClicked(action->{
                showPauseMenuPopupPane();
                ArvenarFXMain.stageElven.setScene(ArvenarSettings.settings_scene);
            
            });
        
                        
//Button events - moves the camera along the X and Y axes
                
            moveUpButton.setOnAction(action -> {
                check_HeroPos();
                pcamera1.translateZProperty().set(pcamera1.getTranslateZ()-100);
                System.out.println("CamZ: "+pcamera1.getTranslateZ());
            });
        
            moveDownButton.setOnAction(action -> {
                check_HeroPos();
                pcamera1.translateZProperty().set(pcamera1.getTranslateZ()+100);
                System.out.println("CamZ: "+pcamera1.getTranslateZ());
            });
        
            moveLeftButton.setOnAction(action -> {
                check_HeroPos();
                pcamera1.translateXProperty().set(pcamera1.getTranslateX()-100);
            });
        
            moveRightButton.setOnAction(action -> {
                check_HeroPos();
                pcamera1.translateXProperty().set(pcamera1.getTranslateX()+100);
            });
        
            //----------------------------------------------- 
        
               
        btnPlayGame.setOnAction(action -> {
            
            
            
        });
              
        btnPlayRandomly.setOnAction(action ->{
                     
         
        });        
       
    }
    
    
    public void initNewGame() throws FileNotFoundException{
               
        clearDialogUI();
        clearGameUI();
        resetGameUI();
        createControlGUI();
        createWorld();
        
                
    }
    
    public void initPerspectiveCamera(){
        pcamera1.setNearClip(0.1); // ha setNearClip(1.0), akkor üres kezdőháttered lesz!!
        pcamera1.setFarClip(100000);
        Translate cameraTranslate = new Translate(0,-500,-10000);
        pcamera1.getTransforms().add(cameraTranslate);
        
        subScene3DWorld.setCamera(pcamera1);
             
        
    };
    
    public void addLights(){
        ambientlight = new AmbientLight();
        ambientlight.setColor(Color.AZURE);
        
        pointlight1 = new PointLight(Color.GREEN);
        pointlight1.getTransforms().add(new Translate(500,-1000,500));
        
        group3DWorld.getTransforms().addAll(pointlight1.getTransforms());
        group3DWorld.getChildren().addAll(ambientlight, pointlight1);
    }
    
    public void initRotationControl() { //applies both mouse and keyboard control
       
        Rotate xRotateHeroCam, xRotateHero;
        Rotate yRotateHeroCam, yRotateHero;
        
                
        groupForHero.getTransforms().addAll(xRotateHeroCam = new Rotate(0,Rotate.X_AXIS),
               yRotateHeroCam = new Rotate(0, Rotate.Y_AXIS)
               );
        
        hero3d.getTransforms().addAll(
                xRotateHero = new Rotate(0, Rotate.X_AXIS), 
                yRotateHero = new Rotate(0, Rotate.Y_AXIS));
                //bind counter direction of hero3d to the group rotate together
                
               xRotateHeroCam.angleProperty().bind(angleWorldX);
               yRotateHeroCam.angleProperty().bind(angleWorldY);
              
                                             
               xRotateHero.angleProperty().bind(angleHeroX);
               yRotateHero.angleProperty().bind(angleHeroY);
               
        }
    
    public void checkRotationAngles(){ //X-axis rotationangles shouldn't flip over -5 and 90 grades 
        if (angleWorldX.get() > MAXROTATIONANGLEX){
                    angleWorldX.set(MAXROTATIONANGLEX);
        } 
                
        if (angleWorldX.get() < MINROTATIONANGLEX){
                    angleWorldX.set(MINROTATIONANGLEX);
        } 
        
    }
       
        
    public void check_HeroPos(){
        
        clearDialogUI();
        
            if ((hero3d.getTranslateX() > pirate3d.getTranslateX()+50) && 
                 (hero3d.getTranslateY() > pirate3d.getTranslateY()+50)){
                    
                dialogHBox.setVisible(true); 
                dialogText.setText(objects3d.playerPC+" was fucked up by "+objects3d.playerNPC+"\n");
                pirateDialogImageView.setFitHeight(100); pirateDialogImageView.setFitWidth(100);
                dialogHBox.getChildren().add(pirateDialogImageView);
            }           
    }
      
    public void show_GameGUI(){
        gameRunStage.show();
    }

    public void addPirates() throws FileNotFoundException{ //Add pirates to the map
        //FOR TEST ONLY!!
               
        //for (j = 0; j < 50; j++){
           
           //pirate3d = new Arvenar3DObjects().object3DPirate();
           pirate3d = objects3d.object3DPirate();
           pirateNameLabel.setText(objects3d.playerNPC);
           pirateTooltipImageView = new ImageView(Arvenar3DObjects.pirateImg);
           pirateDialogImageView = new ImageView(Arvenar3DObjects.pirateImg);
          
           rx = random.nextInt(1300)-200;
           ry = -125;
           rz = random.nextInt(1000)+100;
                    
           pirate3d.setTranslateX(rx); 
           pirate3d.setTranslateY(ry); 
           pirate3d.setTranslateZ(rz);
           group3DWorld.getChildren().add(pirate3d);
        
        //}
    }   
       
    public void addHero() throws FileNotFoundException{
                
                hero3d = objects3d.object3DHero();
                heroNameLabel.setText(objects3d.playerPC);
                //NOT USED: heroImage = Arvenar3DObjects.heroImg;                
                heroTooltipImageView = new ImageView(Arvenar3DObjects.heroImg);
               
                hero3d.getTransforms().addAll(new Translate(0,-100,1000));
                groupForHero.getChildren().addAll(hero3d, pcamera1);
                
                group3DWorld.getChildren().add(groupForHero);
                
                infoText.setText("Hero "+objects3d.playerPC+" is playing on current map: "+dbaseMAPS.get_Random_Map().getMap_name()); //for selected map
                
    }
       
       
    public void createWorld() throws FileNotFoundException{
               
              
        skybox3d.buildSkyBox(group3DWorld);
        obstacles3d.buildWalls(group3DWorld);
        //terrains3d.buildTerrain(group3DWorld);
        
        /*group3DWorld.getChildren().add(objects3d.object3DWall(50000, 50000, 5, 0, -1000, 25000, "skyworld.png", "skyworld.png"));
        group3DWorld.getChildren().add(objects3d.object3DWall(50000, 50000, 5, 0, -1000, -25000, "skyworld.png", "skyworld.png"));
        group3DWorld.getChildren().add(objects3d.object3DWall(5, 50000, 50000, -25000, -1000, 0, "skyworld.png", "skyworld.png"));
        group3DWorld.getChildren().add(objects3d.object3DWall(5, 50000, 50000, 25000, -1000, 0, "skyworld.png", "skyworld.png"));*/
                
        
        
        
        group3DWorld.getTransforms().addAll(new Translate(ArvenarFXMain.guiResolutionX/2, ArvenarFXMain.guiResolutionY-100, 5000));
        
        addHero();
        addPirates();
                
    }
    
    public void createControlGUI(){
        
        uiControlGroup.getChildren().addAll(controlsPane);
                        
    }
    
    public void clearDialogUI(){
        
        dialogHBox.setVisible(false);   
        dialogHBox.getChildren().remove(pirateDialogImageView);
        dialogText.setText(null); //reset text and image
        
    }
       
       public void clearGameUI(){
        
            group3DWorld.getChildren().clear();
            
            uiControlGroup.getChildren().clear();
            game2DUILayoutPane.getChildren().removeAll(controlsPane, subScene3DWorld, uiControlGroup, infoText);
                       
       }
   
       public void resetGameUI() throws FileNotFoundException{
       
           game2DUILayoutPane.getChildren().addAll(controlsPane, subScene3DWorld, uiControlGroup, infoText); //...hogy mindig legfelülre kerüljön a controlPanelVBox layer
           //weather.createAnimation(game2DUILayoutPane, 0, 0, 1920, 1080, (int)Math.round(Math.random()*2));
       }
       
       public Scene game_Scene(){
           return gameMainScene;
       }
          
      
    public void showPauseMenuPopupPane(){
        
        if (!game2DUILayoutPane.getChildren().contains(popupPane)){ 
            popupPane.setLayoutX((game2DUILayoutPane.getWidth()/2)-300);
            popupPane.setLayoutY((game2DUILayoutPane.getHeight()/2)-250);
            game2DUILayoutPane.getChildren().add(popupPane);
            controlsPane.setDisable(true);
        }
        else if (game2DUILayoutPane.getChildren().contains(popupPane)){
            game2DUILayoutPane.getChildren().remove(popupPane);
            controlsPane.setDisable(false);
        } 
    }
    
    
    
 }


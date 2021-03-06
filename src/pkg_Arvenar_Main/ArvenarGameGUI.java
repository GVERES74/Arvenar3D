/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;
import java.io.FileNotFoundException;
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
import pkg_GUI.GuiDialogs;
import pkg_Maps.Map_DataBase;

/**
 *
 * @author TE332168
 */
public class ArvenarGameGUI{
       
    DisplayManager displayManager = new DisplayManager();
    Stage gameRunStage = new Stage();
    Pane paneUILayout = new Pane();
    Pane world3DPane = new Pane();
        
    GuiDialogs dialog = new GuiDialogs();
    
    Pane controlsPane = new Pane();
    Pane popupPane = new Pane();
    VBox consoleInfoVBox = new VBox();
    
    Scene gameMainScene = new Scene(paneUILayout);
    SubScene subScene3DWorld;
        
    Group group3DWorld = new Group();
    Group uiContentGroup = new Group();
    Group cameraGroup = new Group();
        
    HBox dialogHBox = new HBox();
    ImageView pirateTooltipImageView, pirateDialogImageView, heroTooltipImageView;
    Image heroImage, pirateImage;
    
    Image bkgImage = new Image("img/skyworld.png", 1920, 1080, true, false, true);
    
    Text dialogText = new Text();
    Text infoText = new Text();
    Text escPopupHeadText, optReturnToGameText, optExitToMainMenuText, optSettingsText;
    Text consoleInfoText = new Text("Console info");
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
    
    Node player3D, pirate3d, compass3d, selectednode;
                
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
    private double moveFwZDirection, moveFwXDirection, moveSideZDirection, moveSideXDirection;
    
    //min - max rotation angles (x,y)
    private final double MAXROTATIONANGLEX = 60; //does not flip over
    private final double MINROTATIONANGLEX = -40; //does not flip over
    private final double MAXROTATIONANGLEY = 360; 
    private final double MINROTATIONANGLEY = 0; 
    
    //movement values (speed when walk, crouch and sprint)
    private final double DEFAULTMOVEMENTSPEED = 10;
    private double speedModifier = 2;
    private double currentSpeed;
    
    static int invertedMouse =-1;
    static int invertedKeyBoard =-1;
    
       
    int playableScreenXSize; 
    int playableScreenYSize;
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
        
    PerspectiveCamera playerCamera;
         
        
    Rotate rotateCameraX, rotateCameraY, rotateCameraZ, rotateWorldX, rotateWorldY, zRotateWorld, rotatePlayerX, rotatePlayerY;
    Translate translateCameraGroup, translateWorld, translatePlayer;
                    
    public ArvenarGameGUI() throws FileNotFoundException, InterruptedException{ //ez nem lehet void, különben üres stage-t kapsz vissza!!
        
        playerCamera = new PerspectiveCamera(true);
        objects3d = new Arvenar3DObjects();
        transform3d = new Arvenar3DTransforms();
                      
        subScene3DWorld = new SubScene(group3DWorld, displayManager.getResolutionX(), displayManager.getResolutionY(), true, SceneAntialiasing.BALANCED);
        paneUILayout.getChildren().add(world3DPane);
        
        weather.createAnimation(world3DPane, 0, 0, 1920, 1080,2);
                
                
        SCREEN_MAX_X = 1200; 
        SCREEN_MAX_Y = 738;
        SCREEN_MIN_X = 30; 
        SCREEN_MIN_Y = 30;
        
       
       initNewGame(); 
       initPerspectiveCamera();
       initCameraTransforms();
       initWorldTransforms();
       initPlayerTransforms();
       createBindings();
        
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
                              
        infoText = arvfx.setTextEffect(infoText, arvfx.glowEffect, null, Font.font("Verdana", FontWeight.BOLD, 20), Color.GOLD, displayManager.getResolutionX()/2, 50);
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
        dialogHBox.setLayoutX((displayManager.getResolutionX()/2)-(dialogHBox.getPrefWidth()/2)); dialogHBox.setLayoutY(displayManager.getResolutionY()-200);
        
                
        //SUBPANE for controls, compass and buttons
        controlsPane.setStyle("-fx-background-color: rgba(0, 50, 50, 0.6); -fx-background-radius: 8; -fx-padding: 10"); 
        controlsPane.setLayoutX(50); controlsPane.setLayoutY(300);
        controlsPane.getChildren().addAll(btnPlayGame, btnPlayRandomly, moveUpButton, moveDownButton, moveLeftButton, moveRightButton, compass3d);
        
        //SUBPANE for console info
        consoleInfoVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.6); -fx-background-radius: 5; -fx-padding: 10; fx_spacing: 20"); 
        consoleInfoVBox.setLayoutX(50); consoleInfoVBox.setLayoutY(50);
        consoleInfoVBox.getChildren().addAll(consoleInfoText);
        consoleInfoText = arvfx.setTextEffect(consoleInfoText, null, null, Font.font("Verdana", FontWeight.BOLD, 12), Color.BEIGE, 0, 0);
               
        popupPane.getChildren().add(dialog.showPopupWindow(escPopupHeadText, optReturnToGameText, optSettingsText, optExitToMainMenuText));
        
                //GAMEMAINPANE - main pane 
//        paneUILayout.setBackground(new Background(new BackgroundImage(bkgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        //gameMainPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        paneUILayout.getChildren().add(dialogHBox);
        
        
        //addLights();
              
        //arvfx.buttonEffects(btnExitGame);
        arvfx.buttonEffects(btnPlayGame);
        arvfx.buttonEffects(btnPlayRandomly);
        
        
        //-------------
        
//Keyboard events
    //*i have tryed to implement here also the mouse control rotation logic, so that we can use here the same code now
        gameMainScene.setOnKeyPressed((KeyEvent kevent)->{ 
         
            
            showConsoleInfo();         
            
              currentSpeed = DEFAULTMOVEMENTSPEED;
                
                if(kevent.isShiftDown()) //run
                    {currentSpeed = DEFAULTMOVEMENTSPEED * speedModifier;}
                if(kevent.isControlDown()) //crouch
                    {currentSpeed = DEFAULTMOVEMENTSPEED / speedModifier;}
                
                calculateMovementDirections();
                                
            switch (kevent.getCode()){
                                                
                case W:  
                {check_HeroPos();}
                transform3d.rotateByXY(compass3d, -1, Rotate.X_AXIS);
                
                //MOVE and ROTATE THE WORLD
                  translateWorld.setZ(translateWorld.getZ()-moveFwZDirection);
                  translateWorld.setX(translateWorld.getX()+moveFwXDirection);
                
                //MOVE and ROTATE THE CAMERA
//                    translateCameraGroup.setZ(translateCameraGroup.getZ()-moveFwZDirection);
//                    translateCameraGroup.setX(translateCameraGroup.getX()+moveFwXDirection);
                               
                break;

                case S: 
                {check_HeroPos();}
                transform3d.rotateByXY(compass3d, 1, Rotate.X_AXIS);
                translateWorld.setZ(translateWorld.getZ()+moveFwZDirection);
                translateWorld.setX(translateWorld.getX()-moveFwXDirection);
                
                                
                break;

                case A: 
                transform3d.rotateByXY(compass3d, 1, Rotate.Y_AXIS);
                //MOVE and ROTATE THE WORLD
                    translateWorld.setZ(translateWorld.getZ()+moveSideZDirection);
                    translateWorld.setX(translateWorld.getX()-moveSideXDirection);
                //MOVE and ROTATE THE CAMERA
//                    translateCameraGroup.setZ(translateCameraGroup.getZ()+moveSideZDirection);
//                    translateCameraGroup.setX(translateCameraGroup.getX()-moveSideXDirection);
                
                
                break;
                                
                case D: 
                transform3d.rotateByXY(compass3d, -1, Rotate.Y_AXIS);
                translateWorld.setZ(translateWorld.getZ()-moveSideZDirection);
                translateWorld.setX(translateWorld.getX()+moveSideXDirection);
                                
                break;
                                
                case UP:  
                           angleWorldX.set(angleWorldX.get()+invertedKeyBoard);
                           angleHeroX.set(angleWorldX.get()*2); //hero looks in the opposite direction of world
                                           
                break;
                    
                case DOWN: 
                           angleWorldX.set(angleWorldX.get()-invertedKeyBoard);
                           angleHeroX.set(angleWorldX.get()*2); //hero looks in the opposite direction of world
                break;
                    
                case LEFT: 
                           angleWorldY.set(angleWorldY.get()-invertedKeyBoard);
                           angleHeroY.set(angleWorldY.get()*2);
                                          
                break;
                
                case RIGHT:
                           angleWorldY.set(angleWorldY.get()+invertedKeyBoard);
                           angleHeroY.set(angleWorldY.get()*2);
                           
                break;
                
                case Q: 
                        cameraGroup.translateYProperty().set(cameraGroup.getTranslateY()-50);
                break;
                
                case E: 
                        cameraGroup.translateYProperty().set(cameraGroup.getTranslateY()+50);
                break;
                
                
                case ESCAPE: showPauseMenuPopupPane(); break;
            }
                    checkRotationAngles();
                   
            });
        
                 
        
//Mouse events
            
            player3D.setOnMouseEntered(action -> {
                paneUILayout.getChildren().addAll(heroTooltipImageView, heroNameLabel);
                heroTooltipImageView.setFitHeight(50); heroTooltipImageView.setFitWidth(50);
                heroTooltipImageView.setTranslateX(500);
                heroTooltipImageView.setTranslateY(500);
                heroTooltipImageView.setTranslateZ(0);
                
                heroNameLabel.translateXProperty().set(heroTooltipImageView.getTranslateX());
                heroNameLabel.translateYProperty().set(heroTooltipImageView.getTranslateY()-10);
            });
                        
            player3D.setOnMouseExited(action -> {
            paneUILayout.getChildren().removeAll(heroTooltipImageView, heroNameLabel);
            
            });
        
        
            pirate3d.setOnMouseEntered(action -> {
                paneUILayout.getChildren().addAll(pirateTooltipImageView, pirateNameLabel);
                pirateTooltipImageView.setFitHeight(50); pirateTooltipImageView.setFitWidth(50);
                pirateTooltipImageView.translateXProperty().set(500);
                pirateTooltipImageView.translateYProperty().set(500);
                pirateTooltipImageView.translateZProperty().set(0);
                
                pirateNameLabel.translateXProperty().set(pirateTooltipImageView.getTranslateX());
                pirateNameLabel.translateYProperty().set(pirateTooltipImageView.getTranslateY()-10);
            });
        
        
            pirate3d.setOnMouseExited(action -> {
                paneUILayout.getChildren().removeAll(pirateTooltipImageView, pirateNameLabel);
            
            });
        
            gameMainScene.setOnMousePressed((MouseEvent mevent) -> { //prepare values for mouse rotation
                anchorX = mevent.getSceneX();
                anchorY = mevent.getSceneY();
               
                anchorAngleX = angleWorldX.get();
                anchorAngleY = angleWorldY.get();
                           
               
            });
        
        
            gameMainScene.setOnMouseDragged((MouseEvent mevent) -> {
                
                angleWorldX.set(anchorAngleX - (mevent.getSceneY() - anchorY)*invertedMouse);
                angleWorldY.set(anchorAngleY + (mevent.getSceneX() - anchorX)*invertedMouse);
                
                calculateMovementDirections();
                checkRotationAngles();
                showConsoleInfo(); //only for developer mode
                                
                angleHeroX.set(angleWorldX.get()*2); //hero looks in the opposite direction of world
                angleHeroY.set(angleWorldY.get()*2);
                
            });
            
            gameMainScene.setOnScroll((ScrollEvent event) ->{
                double deltaY = event.getDeltaY();
                playerCamera.setTranslateZ(playerCamera.getTranslateZ()+deltaY);
                                
            });
        
//select nodes - only for the tests
            player3D.setOnMouseClicked((MouseEvent event) ->{
                selectednode = player3D;
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
                playerCamera.translateZProperty().set(playerCamera.getTranslateZ()+100);
//                playerCamera.translateYProperty().set(playerCamera.getTranslateY()+10);
            });
        
            moveDownButton.setOnAction(action -> {
                check_HeroPos();
                playerCamera.translateZProperty().set(playerCamera.getTranslateZ()-100);
//                playerCamera.translateYProperty().set(playerCamera.getTranslateY()-10);
                
            });
        
            moveLeftButton.setOnAction(action -> {
                check_HeroPos();
                playerCamera.translateXProperty().set(playerCamera.getTranslateX()-100);
            });
        
            moveRightButton.setOnAction(action -> {
                check_HeroPos();
                playerCamera.translateXProperty().set(playerCamera.getTranslateX()+100);
            });
        
            //----------------------------------------------- 
        
               
        btnPlayGame.setOnAction(action -> {
            
          skybox3d.clearSkyBox(group3DWorld);
          skybox3d.buildImgViewSkyBox(group3DWorld);  
          
        });
              
        btnPlayRandomly.setOnAction(action ->{
           
        });        
       
    }
    
    
    public void initNewGame() throws FileNotFoundException{
               
        clearDialogUI();
        clearGameUI();
        resetGameUI();
        createWorld();
               
    }
    
    
    public void createBindings(){ 
          
        //MOVE and ROTATE THE WORLD 
          rotateWorldX.angleProperty().bind(angleWorldX);
          rotateWorldY.angleProperty().bind(angleWorldY);
        
        //MOVE and ROTATE THE CAMERA
//            rotateCameraX.angleProperty().bind(angleWorldX);
//            rotateCameraY.angleProperty().bind(angleWorldY);
            
        
        rotatePlayerX.angleProperty().bind(angleHeroX);
        rotatePlayerY.angleProperty().bind(angleHeroY);
        
        }
    
    public void initPerspectiveCamera(){
        playerCamera.setNearClip(0.1); // ha setNearClip(1.0), akkor üres kezdőháttered lesz!!
        playerCamera.setFarClip(150000);
        playerCamera.setFieldOfView(60);
                
        cameraGroup.getChildren().addAll(playerCamera);
        //group3DWorld.getChildren().addAll(cameraGroup); //MOVE and ROTATE THE CAMERA
        subScene3DWorld.setCamera(playerCamera);
        
    };
    
    public void addLights(){
        AmbientLight ambientlight = new AmbientLight();
        ambientlight.setColor(Color.AZURE);
        
        PointLight pointlight1 = new PointLight(Color.GREEN);
        pointlight1.getTransforms().add(new Translate(0,-100,100));
        
        group3DWorld.getTransforms().addAll(pointlight1.getTransforms());
        group3DWorld.getChildren().addAll(ambientlight, pointlight1);
    }
    
    public void initWorldTransforms() { //applies both mouse and keyboard control
                     
               rotateWorldX = new Rotate(0,Rotate.X_AXIS);
               rotateWorldY = new Rotate(0,Rotate.Y_AXIS);
               
               translateWorld = new Translate(0,0,0); //center of the world
               
        group3DWorld.getTransforms().addAll(rotateWorldX, rotateWorldY, translateWorld);
             
        }
    
    
    public void initCameraTransforms() { //applies both mouse and keyboard control
                     
               rotateCameraX = new Rotate(0,0,0,0, Rotate.X_AXIS);
               rotateCameraY = new Rotate(0,Rotate.Y_AXIS);
               //rotateCameraZ = new Rotate(0,Rotate.Z_AXIS); 
               translateCameraGroup = new Translate(displayManager.getResolutionX()/2, -200, 50000);
               
        playerCamera.getTransforms().addAll(rotateCameraX, rotateCameraY);
        cameraGroup.getTransforms().addAll(translateCameraGroup);
                
    }
    
    public void initPlayerTransforms(){
        
        rotatePlayerX = new Rotate(0,0,0,0,Rotate.X_AXIS);
        rotatePlayerY = new Rotate(0,0,0,0,Rotate.Y_AXIS);
        translatePlayer = new Translate(0,0,2000);
                
        player3D.getTransforms().addAll(rotatePlayerX, rotatePlayerY, translatePlayer);
    }
    
    public void checkRotationAngles(){ 
        
        angleWorldX.set(angleWorldX.get() > MAXROTATIONANGLEX ? MAXROTATIONANGLEX : angleWorldX.get());
        angleWorldX.set(angleWorldX.get() < MINROTATIONANGLEX ? MINROTATIONANGLEX : angleWorldX.get());
//        angleWorldY.set(angleWorldY.get() > MAXROTATIONANGLEY ? 1 : angleWorldY.get());
//        angleWorldY.set(angleWorldY.get() < MINROTATIONANGLEY ? 359 : angleWorldY.get());
        
        /*if (angleWorldX.get() > MAXROTATIONANGLEX){ //X-axis rotationangles shouldn't flip over -5 and 90 grades 
                    angleWorldX.set(MAXROTATIONANGLEX);
        } 
                
        if (angleWorldX.get() < MINROTATIONANGLEX){
                    angleWorldX.set(MINROTATIONANGLEX);
        } 
                
        }*/
        
    }
    
    public void calculateMovementDirections(){
              
        //MOVE THE camera(group)
//            moveFwXDirection = currentSpeed * Math.cos(Math.toRadians(angleWorldY.get()-90));
//            moveFwZDirection = currentSpeed * Math.sin(Math.toRadians(angleWorldY.get()-90));
//            moveSideXDirection = currentSpeed * Math.cos(Math.PI / 180 * (angleWorldY.get()));
//            moveSideZDirection = currentSpeed * Math.sin(Math.PI / 180 * (angleWorldY.get()));
                
        //MOVE THE WORLD
            moveFwXDirection = currentSpeed * Math.sin(Math.PI / 180 * angleWorldY.get());
            moveFwZDirection = currentSpeed * Math.cos(Math.PI / 180 * angleWorldY.get());
            moveSideXDirection = currentSpeed * Math.sin(Math.PI / 180 * (angleWorldY.get()-90));
            moveSideZDirection = currentSpeed * Math.cos(Math.PI / 180 * (angleWorldY.get()-90));
    }
       
        
    public void check_HeroPos(){
        
        clearDialogUI();
        
            if ((player3D.getTranslateX() > pirate3d.getTranslateX()+50) && 
                 (player3D.getTranslateY() > pirate3d.getTranslateY()+50)){
                    
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
          
           rx = random.nextInt(600)-600;
           ry = -125;
           rz = random.nextInt(1000)+100;
                    
           pirate3d.setTranslateX(rx); 
           pirate3d.setTranslateY(ry); 
           pirate3d.setTranslateZ(rz);
           group3DWorld.getChildren().add(pirate3d);
        
        //}
    }   
       
    public void addHero() throws FileNotFoundException{
                
                player3D = objects3d.object3DHero();
                heroNameLabel.setText(objects3d.playerPC);
                //NOT USED: heroImage = Arvenar3DObjects.heroImg;                
                heroTooltipImageView = new ImageView(Arvenar3DObjects.heroImg);
               
                group3DWorld.getChildren().add(player3D);             
                
                infoText.setText("Hero "+objects3d.playerPC+" is playing on current map: "+dbaseMAPS.get_Random_Map().getMap_name()); //for selected map
                
    }
       
       
    public void createWorld() throws FileNotFoundException{
              
        //skybox3d.buildImgViewSkyBox(group3DWorld);
        //skybox3d.buildMeshViewCubeMapSkyBox(group3DWorld);
        obstacles3d.buildWalls(group3DWorld);
        obstacles3d.buildBush(group3DWorld);
        obstacles3d.buildMeshCube(group3DWorld);
        //terrains3d.buildTerrain(group3DWorld);
        
        
        
        terrains3d.buildMeshTerrain(group3DWorld, 1000, 10, 1000, 0, 0, 0);
        
        group3DWorld.getTransforms().addAll(new Translate(displayManager.getResolutionX()/2, 0, 50000));
        
        addHero();
        addPirates();
                
    }
    
        
    public void clearDialogUI(){
        
        dialogHBox.setVisible(false);   
        dialogHBox.getChildren().remove(pirateDialogImageView);
        dialogText.setText(null); //reset text and image
        
    }
       
       public void clearGameUI(){
        
            group3DWorld.getChildren().clear();
            
            uiContentGroup.getChildren().clear();
            paneUILayout.getChildren().removeAll(subScene3DWorld, world3DPane, uiContentGroup, infoText);
                       
       }
   
       public void resetGameUI() throws FileNotFoundException{
           uiContentGroup.getChildren().addAll(controlsPane, consoleInfoVBox);
           paneUILayout.getChildren().addAll(subScene3DWorld, world3DPane, uiContentGroup, infoText); //...hogy mindig legfelülre kerüljön a controlPanelVBox layer
           //weather.createAnimation(paneUILayout, 0, 0, 1920, 1080, (int)Math.round(Math.random()*2));
       }
       
       void showConsoleInfo(){
           consoleInfoText.setText("Console info:"+"\n"+
                                    "World-Z-Axis: "+translateWorld.getZ()+"\n"+
                                    "World X-Axis: "+translateWorld.getX()+"\n"+
                                    "Pitch: "+angleWorldX.get()+"\n"+
                                    "Yaw: "+angleWorldY.get()+"\n"+
                                    "rCosX: "+moveFwXDirection+"\n"+
                                    "rSinZ: "+moveFwZDirection+"\n"+
                                    "Speed: "+currentSpeed+"\n"+
                                    "PlayerCamera Z: "+playerCamera.getTranslateZ());    
           
       }
       
       public Scene game_Scene(){
           return gameMainScene;
       }
          
      
    public void showPauseMenuPopupPane(){
        
        if (!paneUILayout.getChildren().contains(popupPane)){ 
            popupPane.setLayoutX((displayManager.getResolutionX()/2)-150);
            popupPane.setLayoutY((displayManager.getResolutionY()/2)-100);
            paneUILayout.getChildren().add(popupPane);
            controlsPane.setDisable(true);
        }
        else if (paneUILayout.getChildren().contains(popupPane)){
            paneUILayout.getChildren().remove(popupPane);
            controlsPane.setDisable(false);
        } 
    }
    
    
    
 }


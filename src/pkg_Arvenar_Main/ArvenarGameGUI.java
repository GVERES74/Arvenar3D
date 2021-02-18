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
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
       
    Stage gamestage = new Stage();
    Pane gameMainPane = new Pane();
        
    VBox escPopUpVBox = new VBox();
    
    Pane controlsPane = new Pane();
    Pane popupPane = new Pane();
    Scene gameMainScene = new Scene(gameMainPane);
    SubScene worldSubScene;
    Group actors = new Group();
    Group heroGroup = new Group();
    Group controlGroup = new Group();
    
    HBox dialogHBox = new HBox();
    ImageView pirateTooltipImageView, pirateDialogImageView, heroTooltipImageView;
    Image heroImage, pirateImage;
    
    Image bkgImage = new Image("img/bkg_main.jpg", 1366 , 768, true, false, true);
    
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
    
    Node hero3d, pirate3d, compass3d, selectednode, world3d;
                
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
    private final double MINROTATIONANGLEX = -5; //does not flip over
    
    
    
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
         
    AmbientLight ambientlight = new AmbientLight();
    PointLight pointlight1 = new PointLight(Color.BLUEVIOLET);
                    
    public ArvenarGameGUI() throws FileNotFoundException, InterruptedException{ //ez nem lehet void, különben üres stage-t kapsz vissza!!
        
        objects3d = new Arvenar3DObjects();
        transform3d = new Arvenar3DTransforms();
        weather.createAnimation(gameMainPane, 0, 0, 1920, 1080,2);
              
        worldSubScene = new SubScene(actors, 1920, 1080, true, SceneAntialiasing.BALANCED);
               
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
        //gameMainPane.setBackground(new Background(new BackgroundImage(bkgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        gameMainPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        gameMainPane.getChildren().add(dialogHBox);
        
        
        
        //addLights();
              
        arvfx.buttonEffects(btnExitGame);
        arvfx.buttonEffects(btnPlayGame);
        arvfx.buttonEffects(btnPlayRandomly);
        
        
        //-------------
        
//Keyboard events
    //*i have tryed to implement here also the mouse control rotation logic, so that we can use here the same code now
        gameMainScene.setOnKeyPressed(event ->{ 
            
                //anchorX = angleWorldX.get(); //angle values should be read directly when the key pressed, otherwise the mouse control fails to work at the same time
                //anchorY = angleWorldY.get();
                
                            
            switch  (event.getCode()){
                case W:  
                {check_HeroPos();}
                transform3d.rotateByXY(compass3d, -1, Rotate.X_AXIS);
                transform3d.move3DNode(actors, 0, 0, -10);
                
                break;

                case S: 
                {check_HeroPos();}
                transform3d.rotateByXY(compass3d, 1, Rotate.X_AXIS);
                transform3d.move3DNode(actors, 0, 0, 10); 
                
                
                break;

                case A: 
                transform3d.rotateByXY(compass3d, 1, Rotate.Y_AXIS);
                transform3d.move3DNode(actors, 10, 0, 0); 
                break;
                                
                case D: 
                transform3d.rotateByXY(compass3d, -1, Rotate.Y_AXIS);
                transform3d.move3DNode(actors, -10, 0, 0); 
                break;
                                
                case UP:  angleWorldX.set(angleWorldX.get()-1);
                          angleHeroX.set(-angleWorldX.get()*5);  
                break;
                    
                case DOWN: angleWorldX.set(angleWorldX.get()+1); 
                           angleHeroX.set(-angleWorldX.get()*5);   
                break;
                    
                case LEFT: angleWorldY.set(angleWorldY.get()+1); 
                           angleHeroY.set(-angleWorldY.get()*5);   
                break;
                
                case RIGHT: angleWorldY.set(angleWorldY.get()-1); 
                            angleHeroY.set(-angleWorldY.get()*5);   
                break;
                
                case ESCAPE: showPauseMenuPopupPane(); break;
            }
                    checkRotationAngles();
                    System.out.println("Distance from zeroZ: "+actors.getTranslateZ());
            });
            
            
        
//Mouse events
            
            hero3d.setOnMouseEntered(action -> {
                gameMainPane.getChildren().addAll(heroTooltipImageView, heroNameLabel);
                heroTooltipImageView.setFitHeight(50); heroTooltipImageView.setFitWidth(50);
                heroTooltipImageView.setTranslateX(hero3d.getTranslateX()+20);
                heroTooltipImageView.setTranslateY(hero3d.getTranslateY()-20);
                heroTooltipImageView.setTranslateZ(hero3d.getTranslateZ()-10);
                
                heroNameLabel.translateXProperty().set(heroTooltipImageView.getTranslateX());
                heroNameLabel.translateYProperty().set(heroTooltipImageView.getTranslateY()-10);
            });
                        
            hero3d.setOnMouseExited(action -> {
            gameMainPane.getChildren().removeAll(heroTooltipImageView, heroNameLabel);
            
            });
        
        
            pirate3d.setOnMouseEntered(action -> {
                gameMainPane.getChildren().addAll(pirateTooltipImageView, pirateNameLabel);
                pirateTooltipImageView.setFitHeight(50); pirateTooltipImageView.setFitWidth(50);
                pirateTooltipImageView.translateXProperty().set(hero3d.getTranslateX()+50);
                pirateTooltipImageView.translateYProperty().set(hero3d.getTranslateY()-50);
                pirateTooltipImageView.translateZProperty().set(hero3d.getTranslateZ());
                
                pirateNameLabel.translateXProperty().set(pirateTooltipImageView.getTranslateX());
                pirateNameLabel.translateYProperty().set(pirateTooltipImageView.getTranslateY()-10);
            });
        
        
            pirate3d.setOnMouseExited(action -> {
                gameMainPane.getChildren().removeAll(pirateTooltipImageView, pirateNameLabel);
            
            });
        
            gameMainScene.setOnMousePressed((MouseEvent mevent) -> { //prepare values for mouse rotation
                anchorX = mevent.getSceneX();
                anchorY = mevent.getSceneY();
                anchorAngleX = angleWorldX.get();
                anchorAngleY = angleWorldY.get();
                           
               
            });
        
        
            gameMainScene.setOnMouseDragged((MouseEvent mevent) -> {
                angleWorldX.set(anchorAngleX - (anchorY - mevent.getSceneY()));
                angleWorldY.set(anchorAngleY + anchorX - mevent.getSceneX());
                angleHeroX.set(-angleWorldX.get()); //hero looks in the opposite direction of world
                angleHeroY.set(-angleWorldY.get());
                
                checkRotationAngles();
                
                    if (mevent.isMiddleButtonDown()){
                    pcamera1.translateYProperty().set(pcamera1.getTranslateY()+angleHeroX.get());
                    }
                    
            });
            
            gameMainScene.setOnScroll((ScrollEvent event) ->{
                double deltaY = event.getDeltaY();
                actors.setTranslateZ(actors.getTranslateZ()+deltaY);
            });
        
//select nodes - only for the tests
            hero3d.setOnMouseClicked((MouseEvent event) ->{
                selectednode = hero3d;
            });
            
                            
            pirate3d.setOnMouseClicked((MouseEvent event) ->{
                selectednode = pirate3d;
            });
            
            
            world3d.setOnMouseClicked((MouseEvent event) ->{
                selectednode = world3d;
            });
            
            compass3d.setOnMouseClicked((MouseEvent event) ->{
                selectednode = pcamera1;
            });
            
            actors.setOnMouseClicked((MouseEvent event) ->{
                selectednode = actors;
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
                pcamera1.translateYProperty().set(pcamera1.getTranslateY()-100);
            });
        
            moveDownButton.setOnAction(action -> {
                check_HeroPos();
                pcamera1.translateYProperty().set(pcamera1.getTranslateY()+100);
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
        createWorld();
        
                
    }
    
    public void initPerspectiveCamera(){
        pcamera1.setNearClip(0.1); // ha setNearClip(1.0), akkor üres kezdőháttered lesz!!
        pcamera1.setFarClip(50000);
        pcamera1.setTranslateZ(-1000);
        pcamera1.setTranslateY(-500);
                
        worldSubScene.setCamera(pcamera1);
                
        
    };
    
    public void addLights(){
        pointlight1.getScope().add(hero3d);
        pointlight1.getTransforms().add(new Translate(50, -50, -50));
        ambientlight.setColor(Color.AZURE);
        gameMainPane.getChildren().add(pointlight1);
        actors.getChildren().add(ambientlight);
    }
    
    public void initRotationControl() { //applies both mouse and keyboard control
       
        Rotate xRotateWorld, xRotateHero;
        Rotate yRotateWorld, yRotateHero;
        
        actors.getTransforms().addAll(
               xRotateWorld = new Rotate(0, Rotate.X_AXIS),
               yRotateWorld = new Rotate(0, Rotate.Y_AXIS)
               );
        
        hero3d.getTransforms().addAll(
                xRotateHero = new Rotate(0, Rotate.X_AXIS), 
                yRotateHero = new Rotate(0, Rotate.Y_AXIS)
                ); //bind counter direction of hero3d to the group rotate together
                
               xRotateWorld.angleProperty().bind(angleWorldX);
               yRotateWorld.angleProperty().bind(angleWorldY);
               
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
        gamestage.show();
    }

    public void addPirates() throws FileNotFoundException{ //Add pirates to the map
        //FOR TEST ONLY!!
               
        //for (j = 0; j < 50; j++){
           
           //pirate3d = new Arvenar3DObjects().object3DPirate();
           pirate3d = objects3d.object3DPirate();
           pirateNameLabel.setText(objects3d.playerNPC);
           pirateTooltipImageView = new ImageView(Arvenar3DObjects.pirateImg);
           pirateDialogImageView = new ImageView(Arvenar3DObjects.pirateImg);
          
           rx = random.nextInt(1500);
           ry = random.nextInt(10);
           rz = random.nextInt(1000)+100;
                    
           pirate3d.setTranslateX(rx); 
           pirate3d.setTranslateY(-50); 
           pirate3d.setTranslateZ(rz);
           actors.getChildren().add(pirate3d);
        
        //}
    }   
       
    public void addHero() throws FileNotFoundException{
                
                heroImage = Arvenar3DObjects.heroImg;                
                heroTooltipImageView = new ImageView(heroImage);
                hero3d = objects3d.object3DHero();
                heroNameLabel.setText(objects3d.playerPC);
                
                hero3d.translateXProperty().set(ArvenarFXMain.guiResolutionX/2);
                hero3d.translateYProperty().set(ArvenarFXMain.guiResolutionY-200);
                hero3d.translateZProperty().set(500);
                heroGroup.getChildren().add(hero3d);
                          
                
                infoText.setText("Hero "+objects3d.playerPC+" is playing on current map: "+dbaseMAPS.get_Random_Map().getMap_name()); //for selected map
                
    }
       
       
    public void createWorld() throws FileNotFoundException{
        
        controlGroup.getChildren().addAll(controlsPane);
        world3d = objects3d.object3DWorld();
        actors.getChildren().add(world3d);
        
        addHero();
        addPirates();
                
    }
    
    public void clearDialogUI(){
        
        dialogHBox.setVisible(false);   
        dialogHBox.getChildren().remove(pirateDialogImageView);
        dialogText.setText(null); //reset text and image
        
    }
       
       public void clearGameUI(){
        
            actors.getChildren().clear();
            heroGroup.getChildren().clear();
            controlGroup.getChildren().clear();
            gameMainPane.getChildren().removeAll(controlsPane, worldSubScene, controlGroup, infoText, heroGroup);
                       
       }
   
       public void resetGameUI() throws FileNotFoundException{
       
           gameMainPane.getChildren().addAll(controlsPane, worldSubScene, controlGroup, infoText, heroGroup); //...hogy mindig legfelülre kerüljön a controlPanelVBox layer
           //weather.createAnimation(gameMainPane, 0, 0, 1920, 1080, (int)Math.round(Math.random()*2));
       }
       
       public Scene game_Scene(){
           return gameMainScene;
       }
          
      
    public void showPauseMenuPopupPane(){
        
        if (!gameMainPane.getChildren().contains(popupPane)){ 
            popupPane.setLayoutX((gameMainPane.getWidth()/2)-300);
            popupPane.setLayoutY((gameMainPane.getHeight()/2)-250);
            gameMainPane.getChildren().add(popupPane);
            controlsPane.setDisable(true);
        }
        else if (gameMainPane.getChildren().contains(popupPane)){
            gameMainPane.getChildren().remove(popupPane);
            controlsPane.setDisable(false);
        } 
    }
    
    
    
 }


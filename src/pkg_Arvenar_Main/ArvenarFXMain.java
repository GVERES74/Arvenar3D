package pkg_Arvenar_Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javafx.scene.Node;

/**
 *
 * @author VERESG
 */
public class ArvenarFXMain extends Application {
    
        Timer timer;
        public static int guiResolutionX = 1366;
        public static int guiResolutionY = 768;
        
        static Stage stageElven;
        public static int flagFullScreen;
        static Scene sceneElven;
        
        static ArvenarEffects arvfx = new ArvenarEffects();
        ArvenarFonts arvfonts;
        
        ArvenarCredits credits;
        ArvenarExtras extras;
        ArvenarGameMode gamemodegui;
        
                        
        static Text mTxtStartGame, mTxtSettings, mTxtExtras, mTxtCredits, mTxtExit, versionText;
        static double g, h;
                
        static Pane paneElven;
        static Timer delayer;
        
        static Weather weather;
                       
        VBox exitGameVBox;
        VBox mTxtVBox;
        Text exitGameHeaderText, exitGameYesText, exitGameNoText;
        Image bkgImage = new Image("img/bkg_main.jpg");
        
        BufferedReader readSettingsBR;
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ArvenarFx.fxml"));


        readSettingsBR = new BufferedReader(new FileReader("c:\\Users\\djver\\OneDrive\\Dokumentumok\\NetBeansProjects\\Arvenar\\src\\settings.txt"));
      
        credits = new ArvenarCredits();
        weather = new Weather();
        gamemodegui = new ArvenarGameMode();
        ArvenarSettings gsettings = new ArvenarSettings();
                
        arvfonts = new ArvenarFonts();
        
        paneElven = new Pane();
                
        mTxtVBox = new VBox();
        exitGameVBox = new VBox();
                
        
        
        stageElven = new Stage();
        stageElven.setTitle("Arvenar - Elven Tales - 2020 - by Gabor Veres"+" - Width: "+guiResolutionX+" Height: "+guiResolutionY+" FullScreen: "+flagFullScreen);
        
        
        
        sceneElven = new Scene(paneElven, guiResolutionX, guiResolutionY);
                
        stageElven.setResizable(false);
                       
        stageElven.setScene(sceneElven);
        
        
        
        stageElven.setHeight(guiResolutionY); stageElven.setWidth(guiResolutionX);
        
        
        stageElven.setFullScreen(flagFullScreen == 1 ? true : false);        
        stageElven.show();
        
        MPlayer.mPlayer_start("monastery.mp3", true, 5);    
        
        new GVTimer().mainTimer(100);
        //createWeather(10);
        
        weather.createAnimation(paneElven, 0, 0, 1920, 1080,1);
        
        
        createGUIElements();
                
        //--------------------------------------------------------
        
        
        //--------------------------------------------------------
        
        mTxtExit.setOnMouseClicked(action -> {
            showExitMenuPopupPane();
                        
         });
        
        exitGameNoText.setOnMouseClicked(action -> {
            
            showExitMenuPopupPane();
            
        });
       
        exitGameYesText.setOnMouseClicked(action -> {
            System.exit(0);
        });
       
        
        mTxtStartGame.setOnMouseClicked(action -> {
            
            stageElven.setScene(gamemodegui.gmode_Scene());
            stageElven.setFullScreen(flagFullScreen == 1 ? true : false);
        
                //gamegui.show_GameGUI();
                //elvenarapp.fight();
                MPlayer.mPlayer_stop();
                MPlayer.mPlayer_start("main.mp3", true, 5);
                
        });
        
        mTxtSettings.setOnMouseClicked(action -> {
        
            //gsettings.show_Settings();
            stageElven.setScene(gsettings.settings_Scene());
            stageElven.setFullScreen(flagFullScreen == 1 ? true : false);
        });
                
        mTxtExtras.setOnMouseClicked(action -> {
              
            try {
                extras = new ArvenarExtras();
            } catch (Exception ex) {
                Logger.getLogger(ArvenarFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        MPlayer.mPlayer_stop();
        MPlayer.mPlayer_start("credits1.mp3", true, 5);
        stageElven.setScene(extras.extras_Scene());
        stageElven.setFullScreen(flagFullScreen == 1 ? true : false);
                            
        });
                
        mTxtCredits.setOnMouseClicked(action -> {
            
        MPlayer.mPlayer_stop();
        MPlayer.mPlayer_start("credits1.mp3", true, 5);
        stageElven.setScene(credits.credits_Scene());
        stageElven.setFullScreen(flagFullScreen == 1 ? true : false);
                            
        });
    
    }    
                   
    public static void buttonTxtFxer() {
                                       
            if (arvfx.glowEffect.getLevel() < 0.1){
                g = g + 0.1;
                                                }        
            if (arvfx.glowEffect.getLevel() > 0.9) {
                g = g - 0.1;
                        
                }
                    mTxtStartGame.setEffect(arvfx.setGlowEffect(arvfx.glowEffect.getLevel()+g));
                    //System.out.println("Glow: "+arvfx.glowEffect.getLevel());
                       
    }   
               
        
    public void showExitMenuPopupPane(){
        
        if (!paneElven.getChildren().contains(exitGameVBox)){ 
            exitGameVBox.setLayoutX((guiResolutionX/2)-150);
            exitGameVBox.setLayoutY((guiResolutionY/2)-150);
            paneElven.getChildren().add(exitGameVBox);
            mTxtVBox.setDisable(true);
        }
        else if (paneElven.getChildren().contains(exitGameVBox)){
            paneElven.getChildren().remove(exitGameVBox);
            mTxtVBox.setDisable(false);
        } 
    }
     
    
    public void createWeather (int delayms) throws InterruptedException {
    
        ActionListener taskPerformer = new ActionListener() {
                    
            public void actionPerformed(ActionEvent evt) {
                     
                weather.Falling(true, 1);
            }
            
    };
        delayer = new Timer(delayms, taskPerformer);
        delayer.start();
    }    
    
    public void setLayouts(Node node, int xpos, int ypos){
        node.setLayoutX(xpos);
        node.setLayoutY(ypos);
        
    }
    
    public void createGUIElements(){
        
        setLayouts(mTxtVBox, 50, 50);       
        mTxtVBox.setMaxHeight(300); mTxtVBox.setMaxWidth(400);
        
        mTxtVBox.setSpacing(20); 
        mTxtVBox.setStyle("-fx-background-color: rgba(64, 50, 128, 0.2); -fx-background-radius: 5; -fx-padding: 30;"); //transparent and rounded VBox with padding
        
        mTxtStartGame = arvfonts.newTextFormat("Start game", mTxtStartGame, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 24), Color.CORAL, 0, 0);
        mTxtSettings = arvfonts.newTextFormat("Game settings", mTxtSettings, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 24), Color.CORAL, 0, 0);
        mTxtExtras = arvfonts.newTextFormat("Extras", mTxtExtras, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 24), Color.CORAL, 0, 0);
        mTxtCredits = arvfonts.newTextFormat("Credits", mTxtCredits, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 24), Color.CORAL, 0, 0);
        mTxtExit = arvfonts.newTextFormat("Exit game", mTxtExit, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 24), Color.CORAL, 0, 0);
        versionText = arvfonts.newTextFormat("Arvenar GUI version - Build 01.12.20", versionText, null, arvfx.reflectionEffect, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, (int)mTxtVBox.getLayoutX(), guiResolutionY-200);
        exitGameHeaderText = arvfonts.newTextFormat("Exit game?", exitGameHeaderText, null, arvfx.shadowEffect, Font.font("Verdana", FontWeight.BOLD, 28), Color.DARKORANGE, 0, 0);
        exitGameYesText = arvfonts.newTextFormat("Yes", exitGameYesText, null, null, Font.font("Verdana", FontWeight.BOLD, 20), Color.CORAL, 0, 0);
        exitGameNoText = arvfonts.newTextFormat("No", exitGameNoText, null, null, Font.font("Verdana", FontWeight.BOLD, 20), Color.AQUA, 0, 0);
                
        mTxtVBox.getChildren().addAll(mTxtStartGame, mTxtSettings, mTxtExtras, mTxtCredits, mTxtExit);
        
        exitGameVBox.setMaxHeight(300); exitGameVBox.setMaxWidth(450);
        exitGameVBox.setMinHeight(300); exitGameVBox.setMinWidth(450);
        exitGameVBox.setAlignment(Pos.CENTER);
        exitGameVBox.setSpacing(20); 
        exitGameVBox.setStyle("-fx-background-color: rgba(64, 50, 128, 0.2); -fx-background-radius: 5; -fx-padding: 30;"); //transparent and rounded VBox with padding
        exitGameVBox.getChildren().addAll(exitGameHeaderText, exitGameYesText, exitGameNoText);
                     
        paneElven.setBackground(new Background(new BackgroundImage(bkgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        paneElven.getChildren().addAll(mTxtVBox, versionText); 
        
        arvfx.btnTextEffects(mTxtStartGame);
        arvfx.btnTextEffects(mTxtSettings);
        arvfx.btnTextEffects(mTxtExtras);
        arvfx.btnTextEffects(mTxtCredits);
        arvfx.btnTextEffects(mTxtExit);
        
        arvfx.btnTextEffects(exitGameYesText);
        arvfx.btnTextEffects(exitGameNoText);
        
        
    }
    
    public static void main(String[] args) throws FileNotFoundException {
      Application.launch(args); //Kell az Application.launch();!!
     
    }
    /**
     * @param args the command line arguments
     */
        
}
                
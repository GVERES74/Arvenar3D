/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import static pkg_Arvenar_Main.ArvenarFXMain.stageElven;

/**
 *
 * @author TE332168
 */
public class ArvenarGameMode {
  
    Stage gmode_stage = new Stage();
    Pane gmode_pane = new Pane();
    Pane quickViewPane = new Pane();
    static Scene gmode_scene;
    
    VBox selectModeVBox = new VBox(); 
    VBox quickViewVBox = new VBox();
    
    ImageView gameModeImgView = new ImageView();
    ImageView noModeImgView = new ImageView();
    Image gModeImg, noModeImg;
    
    FileInputStream fisGameMode;
    
    Text gModeSceneText = new Text("New Game");
    Text gModeHeadText;
    Text gModeText;
    static Text mTxtStoryMode, mTxtFreeMode, mTxtInstantMode;
    Button btnExit;
    
    ArvenarEffects arvfx;
    ArvenarFonts arvfonts;
    ArvenarButtons arv_buttons;
    ArvenarGameGUI gamegui;
    Arvenar_View_Maps g_FreeMode_Maps;
    ArvenarSetPC g_FreeMode_PC;
                          
        
    public ArvenarGameMode()throws Exception{
        

        arvfx = new ArvenarEffects();
        arvfonts = new ArvenarFonts();
        arv_buttons = new ArvenarButtons();
        gamegui = new ArvenarGameGUI();
        
        g_FreeMode_Maps = new Arvenar_View_Maps();
        g_FreeMode_PC = new ArvenarSetPC() ;
        
        gmode_stage.setTitle("Select game mode");
        gmode_stage.setScene(gmode_scene);
        gmode_scene = new Scene(gmode_pane);
        noModeImgView = new ImageView(new Image(new FileInputStream("src/img/gm_splash.jpg")));
                       
        btnExit = arv_buttons.actionButtons(btnExit, "Back to Main Menu", arvfx.setGlowEffect(0.5), null, Font.font("Verdana", FontWeight.BOLD, 12), 50, 400);
        
        gModeSceneText = arvfonts.newTextFormat("Select game mode", gModeSceneText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 26), Color.GOLD, 0, 0);
        mTxtStoryMode = arvfonts.newTextFormat("Story mode", mTxtStoryMode, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
        mTxtFreeMode = arvfonts.newTextFormat("Free mode", mTxtFreeMode, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
        mTxtInstantMode = arvfonts.newTextFormat("Instant play", mTxtInstantMode, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
                       
        //---------- Select mode VBox-------------------------------------------------
        selectModeVBox.setLayoutX(50); selectModeVBox.setLayoutY(50);
        selectModeVBox.setMaxSize(350, 250); selectModeVBox.setMinSize(350, 250);
        selectModeVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 30;");
        selectModeVBox.setSpacing(10); 
        selectModeVBox.getChildren().addAll(gModeSceneText, mTxtStoryMode, mTxtFreeMode, mTxtInstantMode);
        
        quickViewVBox.setLayoutX(500); quickViewVBox.setLayoutY(50);
        quickViewVBox.setMaxSize(600, 600); quickViewVBox.setMinSize(600, 600);
        quickViewVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 20;");
        quickViewVBox.setSpacing(20);
        quickViewVBox.getChildren().add(noModeImgView);
        quickViewPane.getChildren().add(quickViewVBox);
              
        
        //---------- Pack all children nodes-------------------------------------------------
        gmode_pane.setBackground(new Background(new BackgroundImage(new Image("/img/bkg_extras.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        gmode_pane.getChildren().addAll(quickViewPane, selectModeVBox, btnExit);
        
        //arvfx.buttonEffects(btnExit);
        
                
        //------------ MouseEvents for game modes -----------------------------    
        
        mTxtStoryMode.setOnMouseEntered(action -> {
            quickViewVBox.getChildren().clear();
            mTxtStoryMode.setScaleX(1.2); mTxtStoryMode.setScaleY(1.2);
            
            try {
                fisGameMode = new FileInputStream("src/img/gm_story.png");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArvenarGameMode.class.getName()).log(Level.SEVERE, null, ex);
            }
            gModeImg = new Image(fisGameMode);
            gameModeImgView.setImage(gModeImg);
            gModeHeadText = arvfonts.newTextFormat("Story Mode", gModeHeadText, null, arvfx.setGlowEffect(0.9), Font.font("Verdana", FontWeight.BOLD, 28), Color.CORAL, 0, 0);
            gModeText = arvfonts.newTextFormat("Start story mode with your companions\n"+"through an adventure across the ancient Elven Lands", gModeText, null, arvfx.shadowEffect, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 10);
            quickViewVBox.getChildren().addAll(gModeHeadText, gameModeImgView, gModeText);
        });
        
        mTxtStoryMode.setOnMouseExited(action -> {
            
            mTxtStoryMode.setScaleX(1.0); mTxtStoryMode.setScaleY(1.0);
            quickViewVBox.getChildren().removeAll(gModeHeadText, gameModeImgView, gModeText);
            quickViewVBox.getChildren().add(noModeImgView);
            
        });
        
                         
        mTxtStoryMode.setOnMouseClicked(action -> {
            //MPlayer.mPlayer_stop();
            //MPlayer.mPlayer_start("menu.mp3", true, 5);
            stageElven.setScene(gamegui.game_Scene());
            ArvenarFXMain.stageElven.setFullScreen(ArvenarFXMain.flagFullScreen == 1 ? true : false);
            
        });
               
        mTxtFreeMode.setOnMouseEntered(action -> {
            quickViewVBox.getChildren().clear();
            mTxtFreeMode.setScaleX(1.2); mTxtFreeMode.setScaleY(1.2);
            
            try {
                fisGameMode = new FileInputStream("src/img/gm_freeplay.png");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArvenarGameMode.class.getName()).log(Level.SEVERE, null, ex);
            }
            gModeImg = new Image(fisGameMode);
            gameModeImgView.setImage(gModeImg);
            gModeHeadText = arvfonts.newTextFormat("Free Play", gModeHeadText, null, arvfx.setGlowEffect(0.9), Font.font("Verdana", FontWeight.BOLD, 28), Color.CORAL, 0, 0);
            gModeText = arvfonts.newTextFormat("Roam the Land freely with your companions\n"+"trying to survive", gModeText, null, arvfx.shadowEffect, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 10);
            quickViewVBox.getChildren().addAll(gModeHeadText, gameModeImgView, gModeText);
        });
        
        mTxtFreeMode.setOnMouseExited(action -> {
            
            mTxtFreeMode.setScaleX(1.0); mTxtFreeMode.setScaleY(1.0);
            quickViewVBox.getChildren().removeAll(gModeHeadText, gameModeImgView, gModeText);
            quickViewVBox.getChildren().add(noModeImgView);
        });
        
                         
        mTxtFreeMode.setOnMouseClicked(action -> {
            //MPlayer.mPlayer_stop();
            //MPlayer.mPlayer_start("menu.mp3", true, 5);
            stageElven.setScene(gamegui.game_Scene());
            ArvenarFXMain.stageElven.setFullScreen(ArvenarFXMain.flagFullScreen == 1 ? true : false);
            
        });
        
        
         mTxtInstantMode.setOnMouseEntered(action -> {
            quickViewVBox.getChildren().clear();
            mTxtInstantMode.setScaleX(1.2); mTxtInstantMode.setScaleY(1.2);
            
            try {
                fisGameMode = new FileInputStream("src/img/gm_instakill.jpg");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArvenarGameMode.class.getName()).log(Level.SEVERE, null, ex);
            }
            gModeImg = new Image(fisGameMode);
            gameModeImgView.setImage(gModeImg);
            gModeHeadText = arvfonts.newTextFormat("Instant Play", gModeHeadText, null, arvfx.setGlowEffect(0.9), Font.font("Verdana", FontWeight.BOLD, 28), Color.CORAL, 0, 0);
            gModeText = arvfonts.newTextFormat("Instant action with or without your companions\n"+"on the killing grounds", gModeText, null, arvfx.shadowEffect, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 10);
            quickViewVBox.getChildren().addAll(gModeHeadText, gameModeImgView, gModeText);
        });
        
        mTxtInstantMode.setOnMouseExited(action -> {
            
            mTxtInstantMode.setScaleX(1.0); mTxtInstantMode.setScaleY(1.0);
            quickViewVBox.getChildren().removeAll(gModeHeadText, gameModeImgView, gModeText);
            quickViewVBox.getChildren().add(noModeImgView);
        });
                                 
        mTxtInstantMode.setOnMouseClicked(action -> {
            //MPlayer.mPlayer_stop();
            //MPlayer.mPlayer_start("menu.mp3", true, 5);
            stageElven.setScene(gamegui.game_Scene());
            ArvenarFXMain.stageElven.setFullScreen(ArvenarFXMain.flagFullScreen == 1 ? true : false);
            
        });
        
        
        //---------- Exit screen-----------------------------------------------
        btnExit.setOnAction(action -> {
            
            ArvenarFXMain.stageElven.setScene(ArvenarFXMain.sceneElven);
            
        });  
       
    }
        
    public Scene gmode_Scene(){
        
        return gmode_scene;
    }
  
}

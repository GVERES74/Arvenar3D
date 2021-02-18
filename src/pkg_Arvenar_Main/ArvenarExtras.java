/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author TE332168
 */
public class ArvenarExtras {
  
    Stage extrStage = new Stage();
    Pane extrPane = new Pane();
    Pane contentPane = new Pane();
    VBox mp3PlayerVBox = new VBox();
    static Scene extrScene;
    
    VBox mTxtVBox = new VBox();  
    Text xtrasSceneText = new Text("EXTRAS");
    Text musicTitle;
    Text mplayerTitle = new Text();
    Button openFileButton = new Button("Open");
    
    static Text mTxtPc, mTxtMaps, mTxtNpc, mTxtMusicPlayer;
    
    ArvenarEffects arvfx = new ArvenarEffects();
    ArvenarFonts arvfonts;
    Arvenar_View_NPC view_npc;
    Arvenar_View_Maps view_maps;
    ArvenarSetPC view_pc;
    MPlayer mp3player;
                          
    
    int stageSizeX = ArvenarFXMain.guiResolutionX;
    int stageSizeY = ArvenarFXMain.guiResolutionY;
    
    
    public ArvenarExtras()throws Exception{
        
        arvfonts = new ArvenarFonts();
        view_npc = new Arvenar_View_NPC();
        view_maps = new Arvenar_View_Maps();
        view_pc = new ArvenarSetPC() ;
        mp3player = new MPlayer();
        musicTitle = new Text("Now playing: "+MPlayer.song);
        extrStage.setTitle("Game extras");
        extrStage.setScene(extrScene);
        extrScene = new Scene(extrPane);
        
        
        Button btnExit = new Button("Back to main menu"); btnExit.setLayoutX(50); btnExit.setLayoutY(450);
        
        xtrasSceneText = arvfonts.newTextFormat("Extras", xtrasSceneText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 26), Color.GOLD, 0, 0);
        mTxtPc = arvfonts.newTextFormat("Hero's quarters", mTxtPc, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
        mTxtMaps = arvfonts.newTextFormat("View maps", mTxtMaps, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
        mTxtNpc = arvfonts.newTextFormat("NPC database", mTxtNpc, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
        mTxtMusicPlayer = arvfonts.newTextFormat("Music Player", mTxtMusicPlayer, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
        musicTitle = arvfonts.newTextFormat("Now playing: "+MPlayer.song, musicTitle, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 12), Color.AQUAMARINE, 0, 0);               
        mplayerTitle = arvfonts.newTextFormat("Ahoy Matey - Music Player", mplayerTitle, arvfx.shadowEffect, null, Font.font("Verdana", FontWeight.BOLD, 18), Color.BISQUE, 0, 0);               
//---------- Extras menu text's properties-------------------------------------------------
        mTxtVBox.setLayoutX(50); mTxtVBox.setLayoutY(50);
        mTxtVBox.setMaxSize(350, 250); mTxtVBox.setMinSize(350, 250);
        mTxtVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 30;");
        mTxtVBox.setSpacing(10); 
        
        mTxtVBox.getChildren().addAll(xtrasSceneText, mTxtPc, mTxtMaps, mTxtNpc, mTxtMusicPlayer);
        
        //------------Side pane for content-----------------
        //contentPane.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 30;");
        contentPane.setTranslateX(450); contentPane.setTranslateY(50);
        contentPane.setMinHeight(600); contentPane.setMinWidth(800);

        //------------Side pane for Music Player-----------------
        mp3PlayerVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 30;");
        mp3PlayerVBox.setTranslateX(10); mp3PlayerVBox.setTranslateY(50);
        mp3PlayerVBox.setMinHeight(400); mp3PlayerVBox.setMinWidth(600);
        mp3PlayerVBox.getChildren().addAll(mplayerTitle, musicTitle, openFileButton);
                



//---------- Pack all children nodes-------------------------------------------------
        extrPane.setBackground(new Background(new BackgroundImage(new Image("/img/bkg_extras.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        extrPane.getChildren().addAll(mTxtVBox, btnExit, contentPane);
                
        arvfx.buttonEffects(btnExit);
        arvfx.buttonEffects(openFileButton);
                
        //---------------------------------------------------    
        
        btnExit.setOnAction(action -> {
             
            ArvenarFXMain.stageElven.setScene(ArvenarFXMain.sceneElven);
            
                     
        }); 
                  
         mTxtPc.setOnMouseClicked(action -> {
            contentPane.getChildren().clear();
            MPlayer.mPlayer_stop();
            MPlayer.mPlayer_start("menu.mp3", true, 5);
            
            //arvenarset.stagename.show();    
            //elvenarapp.choose_Character();
            contentPane.getChildren().add(view_pc.panename);
        
        });
        
        mTxtPc.setOnMouseEntered(action -> {
            
            mTxtPc.setScaleX(1.2); mTxtPc.setScaleY(1.2);
            
            //arvenarset.stagename.show();    
            //elvenarapp.choose_Character();
        }); 
        
        mTxtPc.setOnMouseExited(action -> {
            mTxtPc.setScaleX(1.0); mTxtPc.setScaleY(1.0);
            
            //arvenarset.stagename.show();    
            //elvenarapp.choose_Character();
        }); 
        
        mTxtMaps.setOnMouseClicked(action -> {
            contentPane.getChildren().clear();
            MPlayer.mPlayer_stop();
            MPlayer.mPlayer_start("outdoor.mp3", true, 5);
            contentPane.getChildren().add(view_maps.pane_view_maps);
            //view_maps.stage_view_maps.show();
            //elvenarapp.view_Map_DataBase();
        
        });
                
        mTxtMaps.setOnMouseEntered(action -> {
            
            mTxtMaps.setScaleX(1.2); mTxtMaps.setScaleY(1.2);
            
            //arvenarset.stagename.show();    
            //elvenarapp.choose_Character();
        }); 
        
        mTxtMaps.setOnMouseExited(action -> {
            mTxtMaps.setScaleX(1.0); mTxtMaps.setScaleY(1.0);
            
            //arvenarset.stagename.show();    
            //elvenarapp.choose_Character();
        }); 
        
        
        mTxtNpc.setOnMouseClicked(action -> {
            contentPane.getChildren().clear();
            MPlayer.mPlayer_stop();
            MPlayer.mPlayer_start("browse.mp3", true, 5);
            contentPane.getChildren().add(view_npc.panename);            
        
        });
        
         mTxtNpc.setOnMouseEntered(action -> {
            
            mTxtNpc.setScaleX(1.2); mTxtNpc.setScaleY(1.2);
            
            //arvenarset.stagename.show();    
            //elvenarapp.choose_Character();
        }); 
        
        mTxtNpc.setOnMouseExited(action -> {
            mTxtNpc.setScaleX(1.0); mTxtNpc.setScaleY(1.0);
            
            //arvenarset.stagename.show();    
            //elvenarapp.choose_Character();
        }); 
        
        mTxtMusicPlayer.setOnMouseClicked(action -> {
                                   
            contentPane.getChildren().clear();
            contentPane.getChildren().addAll(mp3PlayerVBox);            
        
        });
        
         mTxtMusicPlayer.setOnMouseEntered(action -> {
            
            mTxtMusicPlayer.setScaleX(1.2); mTxtMusicPlayer.setScaleY(1.2);
            
            //arvenarset.stagename.show();    
            //elvenarapp.choose_Character();
        }); 
        
        mTxtMusicPlayer.setOnMouseExited(action -> {
            mTxtMusicPlayer.setScaleX(1.0); mTxtMusicPlayer.setScaleY(1.0);
            
            //arvenarset.stagename.show();    
            //elvenarapp.choose_Character();
        }); 
        
        openFileButton.setOnMouseClicked(action -> {
            
            MPlayer.mPlayer_stop();
            FileChooser mp3filechooser = new FileChooser();
            
            mp3filechooser.setInitialDirectory(new File("src/music"));
            mp3filechooser.setTitle("Open MP3 Music Files");
            mp3filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3 files", "*.mp3"));
            File selectedMP3file = mp3filechooser.showOpenDialog(extrStage);
            
            
            String musicName = selectedMP3file.toURI().toString();
            Media music = new Media(musicName);    
            AudioClip mediaPlayerExtras = new AudioClip(music.getSource());
            MPlayer.mediaPlayer = mediaPlayerExtras;
            MPlayer.mediaPlayer.setCycleCount(5);
            MPlayer.mediaPlayer.play();
            musicTitle.setText("Now playing: "+musicName);
            
            System.out.println(musicName);
                      
        
        });
        
        contentPane.setOnMouseEntered(action -> {
            mTxtVBox.setScaleX(0.5); mTxtVBox.setScaleY(0.5);
        });
    
        contentPane.setOnMouseExited(action -> {
          
            mTxtVBox.setScaleX(1.0); mTxtVBox.setScaleY(1.0);
                
        });
    
    }
    
    public Scene extras_Scene(){
        
        return extrScene;
    }
    
       
    
}

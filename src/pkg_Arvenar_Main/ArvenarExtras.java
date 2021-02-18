/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author TE332168
 */
public class ArvenarExtras {
  
    Stage extras_stage = new Stage();
    Pane extras_pane = new Pane();
    Pane contentPane = new Pane();
    static Scene extras_scene;
    
    VBox mTxtVBox = new VBox();  
    Text xtrasSceneText = new Text("EXTRAS");
    
    static Text mTxtPc, mTxtMaps, mTxtNpc;
    
    ArvenarEffects arvfx = new ArvenarEffects();
    ArvenarFonts arvfonts;
    Arvenar_View_NPC view_npc;
    Arvenar_View_Maps view_maps;
    ArvenarSetPC view_pc;
                          
    
    int stageSizeX = ArvenarFXMain.resX;
    int stageSizeY = ArvenarFXMain.resY;
    
    
    public ArvenarExtras()throws Exception{
        
        arvfonts = new ArvenarFonts();
        view_npc = new Arvenar_View_NPC();
        view_maps = new Arvenar_View_Maps();
        view_pc = new ArvenarSetPC() ;
        
        extras_stage.setTitle("Game settings");
        extras_stage.setScene(extras_scene);
        extras_scene = new Scene(extras_pane);
        
        
        Button btnExit = new Button("Back to main menu"); btnExit.setLayoutX(50); btnExit.setLayoutY(450);
        
        xtrasSceneText = arvfonts.newTextFormat("Extras", xtrasSceneText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 26), Color.GOLD, 0, 0);
        mTxtPc = arvfonts.newTextFormat("Hero's quarters", mTxtPc, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
        mTxtMaps = arvfonts.newTextFormat("View maps", mTxtMaps, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
        mTxtNpc = arvfonts.newTextFormat("NPC database", mTxtNpc, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.CORAL, 0, 0);
                       
        //---------- Extras menu text's properties-------------------------------------------------
        mTxtVBox.setLayoutX(50); mTxtVBox.setLayoutY(50);
        mTxtVBox.setMaxSize(350, 250); mTxtVBox.setMinSize(350, 250);
        mTxtVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 30;");
        mTxtVBox.setSpacing(10); 
        
        mTxtVBox.getChildren().addAll(xtrasSceneText, mTxtPc, mTxtMaps, mTxtNpc);
        
        //------------Side pane for content-----------------
        //contentPane.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 30;");
        contentPane.setTranslateX(450); contentPane.setTranslateY(50);
        contentPane.setMinHeight(600); contentPane.setMinWidth(800);

        //---------- Pack all children nodes-------------------------------------------------
        extras_pane.setBackground(new Background(new BackgroundImage(new Image("/img/bkg_extras.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        extras_pane.getChildren().addAll(mTxtVBox, btnExit, contentPane);
                
        arvfx.buttonEffects(btnExit);
        
                
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
        
        
        contentPane.setOnMouseEntered(action -> {
            mTxtVBox.setScaleX(0.5); mTxtVBox.setScaleY(0.5);
        });
    
        contentPane.setOnMouseExited(action -> {
          
            mTxtVBox.setScaleX(1.0); mTxtVBox.setScaleY(1.0);
                
        });
    
    }
    
    public Scene extras_Scene(){
        
        return extras_scene;
    }
    
       
    
}

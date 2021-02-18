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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkg_Maps.Arvenar_Maps;
import pkg_Maps.Map_DataBase;

/**
 *
 * @author TE332168
 */
public class Arvenar_View_Maps {
    
    Map_DataBase maps = new Map_DataBase(); 
    ArvenarEffects arvfx = new ArvenarEffects();
    ArvenarExtras arvextras;
    FileInputStream map_inputimg = new FileInputStream("src/maps/m1_forest.jpg");
    Image selected_map_image = new Image(map_inputimg);
    ImageView map_imgview = new ImageView(selected_map_image);
    TextField map_name_textfield = new TextField(); 
    TextArea map_story_textarea = new TextArea();
    TextArea map_description_textarea = new TextArea();
    TextArea map_inhabit_textarea = new TextArea();
    
    Stage stage_view_maps = new Stage();
    Pane pane_view_maps = new Pane();
    Pane subPane = new Pane();
    Scene scene_view_maps;
    String selected_map_name = "";
    static Arvenar_Maps choosen_map;
    
    int i = 0;
    
    public Arvenar_View_Maps() throws FileNotFoundException {
        
                
        map_name_textfield.setLayoutX(305); map_name_textfield.setLayoutY(50);
       
        map_name_textfield.setEditable(false); map_name_textfield.setFocusTraversable(false);
            
        map_story_textarea.setLayoutX(100); map_story_textarea.setLayoutY(400); map_story_textarea.setWrapText(true); map_story_textarea.setMaxHeight(100);
       
        map_story_textarea.setEditable(false); map_story_textarea.setFocusTraversable(false);
        
        map_inhabit_textarea.setLayoutX(620); map_inhabit_textarea.setLayoutY(300); map_inhabit_textarea.setWrapText(true); map_inhabit_textarea.setMaxWidth(200.0);
        map_inhabit_textarea.setEditable(false); map_inhabit_textarea.setFocusTraversable(false);
        
        map_description_textarea.setLayoutX(620); map_description_textarea.setLayoutY(100); map_description_textarea.setWrapText(true); map_description_textarea.setMaxWidth(200.0);
        map_description_textarea.setEditable(false); map_description_textarea.setFocusTraversable(false);
                
        stage_view_maps.setTitle("View maps");
        stage_view_maps.setResizable(false);
        stage_view_maps.setMinHeight(600); stage_view_maps.setMinWidth(800);
        stage_view_maps.setScene(scene_view_maps);
        stage_view_maps.setResizable(false);
        stage_view_maps.initModality(Modality.APPLICATION_MODAL);
        
        Button btnChoose = new Button("Choose map"); btnChoose.setLayoutX(300); btnChoose.setLayoutY(520);
        Button btnCancel = new Button("Back"); btnCancel.setLayoutX(50); btnCancel.setLayoutY(520);
        Button btnNext = new Button("Next"); btnNext.setLayoutX(500); btnNext.setLayoutY(50);
        Button btnPrev = new Button("Previous"); btnPrev.setLayoutX(200); btnPrev.setLayoutY(50);
        
        get_Selected_Map();
        //pane_view_maps.setBackground(new Background(new BackgroundImage(new Image("/img/bkg_maps.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        
        subPane.setLayoutX(0); subPane.setLayoutY(0);
        subPane.getChildren().addAll(btnChoose, btnCancel, btnNext, btnPrev, map_name_textfield, map_inhabit_textarea, map_story_textarea, map_description_textarea);
        pane_view_maps.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 30;");
        pane_view_maps.getChildren().add(subPane);
        scene_view_maps = new Scene(pane_view_maps);
                
        useArrowKeys();
        
        arvfx.buttonEffects(btnChoose);
        arvfx.buttonEffects(btnCancel);
        arvfx.buttonEffects(btnNext);
        arvfx.buttonEffects(btnPrev);
        
        
        
    //ActionListener block ----------------------------------------------------------------------------------
        
        
        //Choose selected hero_name-------------------------------------------------------------------------------
        btnChoose.setOnAction(Action -> {
            map_name_textfield.setText("You are viewing: "+maps.getMap(i).getMap_name());
            choosen_map = maps.getMap(i);
            
            
        });
        
        //Back to previous page (Main menu)------------------------------------------------------------------
        btnCancel.setOnAction(Action -> {
            try {
                arvextras = new ArvenarExtras();
            } catch (Exception ex) {
                Logger.getLogger(ArvenarSetPC.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArvenarFXMain.stageElven.setScene(ArvenarExtras.extras_scene);
            
        });
                                  
               
        //Get the next Player---------------------------------------------------------------------------------
        btnNext.setOnAction(Action -> nextMap());
                
        //--------------------------------------------------------------------------------------------------------
        
        //Get the previous Player---------------------------------------------------------------------------------
        btnPrev.setOnAction(Action -> previousMap());
                
    }
        
        public void nextMap(){
        
            if(i < maps.maplist.size()-1){
                    i++;
            }
                    else {
                        i = maps.maplist.size()-1;
                    }
                    map_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    get_Selected_Map();
        }
        
        public void previousMap(){
            
           map_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    
                    if(i > 0){
                    i--;
                    }
                    else {
                        i = 0;
                    }            
                    get_Selected_Map();
        }
      
                
        public void useArrowKeys(){
            scene_view_maps.setOnKeyPressed(event ->{
                switch  (event.getCode()){
                 case RIGHT: nextMap();break;
                    case LEFT: previousMap(); break;
            }
        
            });
    
        }
        
        public void get_Selected_Map(){
            
         try {
             map_inputimg = new FileInputStream(maps.getMap(i).getMap_image_path());
             map_name_textfield.setText(maps.getMap(i).getMap_name());
             map_description_textarea.clear();
             map_description_textarea.setText(maps.getMap(i).getMap_desc());
             map_inhabit_textarea.setText("Possible enemies: \n"+maps.getMap(i).getMap_inhabitants());
             map_story_textarea.setText(maps.getMap(i).getMap_story());
             
             selected_map_name = maps.getMap(i).getMap_name();
             
             map_imgview = new ImageView(new Image(map_inputimg));
             map_imgview.setLayoutX(100);
             map_imgview.setLayoutY(100);
             pane_view_maps.getChildren().add(map_imgview);
         } catch (FileNotFoundException ex) {
             Logger.getLogger(Arvenar_View_NPC.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        }
        
        public Scene maps_Scene(){
            return scene_view_maps;
        }
        
        }


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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkg_Characters.Character_DataBase_NPC;
import pkg_Characters.Summon_Characters;


/**
 *
 * @author TE332168
 */
public class Arvenar_View_NPC {
    
     int i = 0;
    
    Summon_Characters clone = new Summon_Characters();
    Character_DataBase_NPC cdbase_npc = new Character_DataBase_NPC();
    ArvenarEffects arvfx = new ArvenarEffects();
    ArvenarExtras arvextras;
            
    FileInputStream inputImg = new FileInputStream("src/img/npc_animal_bbear1.jpg");
    Image selectedpcimage = new Image(inputImg);
    ImageView npc_ImgView = new ImageView(selectedpcimage);
    TextField npc_Name_TextField = new TextField(); 
    TextArea npc_Bio_TextArea = new TextArea();
    TextArea npc_Description_TextArea = new TextArea();
    TextArea npc_Stats_TextArea = new TextArea();
    HBox selectHBox1 = new HBox();
    HBox buttonsHBox2 = new HBox();
    HBox dataHBox3 = new HBox();
    HBox bioHBox4 = new HBox();
    
    Stage stagename = new Stage();
    Pane panename = new Pane();
    Pane subPane = new Pane();
    Scene scenename;
    String npc_name = "";
                    
    public Arvenar_View_NPC() throws FileNotFoundException {
        
        //cdbase_npc.Character_DataBase_NPC(); //Karakter adatbázis inicializálása. Nem kell ha konstruktort hozol létre: "public Character_DataBase_NPC()" és ebben töltöd fel az ArrayList-et. Nem lehet void!!
        
        npc_Name_TextField.setEditable(false); npc_Name_TextField.setFocusTraversable(false);
            
        npc_Bio_TextArea.setWrapText(true); npc_Bio_TextArea.setMaxHeight(100);
       
        npc_Bio_TextArea.setEditable(false); npc_Bio_TextArea.setFocusTraversable(false);
        
        npc_Stats_TextArea.setWrapText(true); npc_Stats_TextArea.setMaxWidth(200.0);
        npc_Stats_TextArea.setEditable(false); npc_Stats_TextArea.setFocusTraversable(false);
        
        npc_Description_TextArea.setWrapText(true); npc_Description_TextArea.setMaxWidth(250.0);
        npc_Description_TextArea.setEditable(false); npc_Description_TextArea.setFocusTraversable(false);
                            
        getNonPlayableCharacter();
        
        stagename.setTitle("View non-playable characters");
        stagename.setResizable(false);
        stagename.setMinHeight(600); stagename.setMinWidth(800);
        stagename.setScene(scenename);
        stagename.setResizable(false);
        stagename.initModality(Modality.APPLICATION_MODAL);
        
        Button btnChoose = new Button("Choose character"); 
        Button btnCancel = new Button("Back"); 
        Button btnNext = new Button("Next"); 
        Button btnPrev = new Button("Previous");
        selectHBox1.setLayoutX(150); selectHBox1.setLayoutY(50); selectHBox1.setSpacing(60);
        buttonsHBox2.setLayoutX(50); buttonsHBox2.setLayoutY(450); buttonsHBox2.setSpacing(100);
        dataHBox3.setLayoutX(50); dataHBox3.setLayoutY(220); dataHBox3.setSpacing(20);
        bioHBox4.setLayoutX(50); bioHBox4.setLayoutY(100);
        
        selectHBox1.getChildren().addAll(btnPrev, npc_Name_TextField, btnNext);
        buttonsHBox2.getChildren().addAll(btnCancel, btnChoose);
        dataHBox3.getChildren().addAll(npc_Stats_TextArea, npc_Description_TextArea);
        bioHBox4.getChildren().add(npc_Bio_TextArea);
        subPane.setLayoutX(0); subPane.setLayoutY(0);
        
        subPane.getChildren().addAll(selectHBox1, buttonsHBox2, dataHBox3, bioHBox4);
        panename.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 30;");
        panename.getChildren().add(subPane);
        scenename = new Scene(panename);
                
        useArrowKeys();
        
        arvfx.buttonEffects(btnChoose);
        arvfx.buttonEffects(btnCancel);
        arvfx.buttonEffects(btnNext);
        arvfx.buttonEffects(btnPrev);
        
        
        
    //ActionListener block ----------------------------------------------------------------------------------
        
        
        //Choose selected hero_name-------------------------------------------------------------------------------
        btnChoose.setOnAction(Action -> {
            npc_Name_TextField.setText("You are viewing: "+npc_name);
            
        });
        
        //Back to previous page (Main menu)------------------------------------------------------------------
        btnCancel.setOnAction(Action -> {
            try {
                arvextras = new ArvenarExtras();
            } catch (Exception ex) {
                Logger.getLogger(ArvenarSetPC.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArvenarFXMain.stageElven.setScene(ArvenarExtras.extrScene);
            
        });
                                  
               
        //Get the next Player---------------------------------------------------------------------------------
        btnNext.setOnAction(Action -> nextNpc());
                
        //--------------------------------------------------------------------------------------------------------
        
        //Get the previous Player---------------------------------------------------------------------------------
        btnPrev.setOnAction(Action -> previousNpc());
                
    }
        
        public void nextNpc(){
        
            if(i < cdbase_npc.npc_character.size()-1){
                    i++;
            }
                    else {
                        i = cdbase_npc.npc_character.size()-1;
                    }
                    npc_ImgView.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    getNonPlayableCharacter();
        }
        
        public void previousNpc(){
            
           npc_ImgView.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    
                    if(i > 0){
                    i--;
                    }
                    else {
                        i = 0;
                    }            
                    getNonPlayableCharacter();
        }
      
        public String getNpcName(){
            return cdbase_npc.getNPC(i).getFname();
        }
        
        public void useArrowKeys(){
            scenename.setOnKeyPressed(event ->{
                switch  (event.getCode()){
                 case RIGHT: nextNpc();break;
                    case LEFT: previousNpc(); break;
            }
        
            });
    
        }
        
        public void getNonPlayableCharacter(){
            
         try {
             inputImg = new FileInputStream(cdbase_npc.npc_img.get(i));
             npc_Name_TextField.setText(cdbase_npc.getNPC(i).getFname());
             npc_Bio_TextArea.setText(cdbase_npc.getNPC(i).getBiography());
             npc_Stats_TextArea.clear();
             npc_Description_TextArea.clear();
             npc_Description_TextArea.appendText(cdbase_npc.getNPC(i).getNpcDesc());
             
             
             npc_Stats_TextArea.appendText("Race: "+cdbase_npc.getNPC(i).getRace());
             npc_Stats_TextArea.appendText("\nCast: "+cdbase_npc.getNPC(i).getCast());
             npc_Stats_TextArea.appendText("\nHealth: "+cdbase_npc.getNPC(i).getHealth_point());
             npc_Stats_TextArea.appendText("\nWeapon: "+cdbase_npc.getNPC(i).getFav_weapon());
             npc_Stats_TextArea.appendText("\nBattle shout: "+cdbase_npc.getNPC(i).getShout());
             this.npc_name = cdbase_npc.getNPC(i).getFname();
             
             
             
             npc_ImgView = new ImageView(new Image(inputImg));
             npc_ImgView.setLayoutX(600); npc_ImgView.setLayoutY(100);
             panename.getChildren().add(npc_ImgView);
             
         } catch (FileNotFoundException ex) {
             Logger.getLogger(Arvenar_View_NPC.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        }
        
        public Scene npc_Scene(){
            return scenename;
        }
        
        }


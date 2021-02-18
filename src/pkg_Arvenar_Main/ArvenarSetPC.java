/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import pkg_Characters.Summon_Characters;
import pkg_Characters.Character_DataBase_PC;
import pkg_Characters.Playable_Character;

/**
 *
 * @author TE332168
 */
public class ArvenarSetPC {
    
    int i = 0;
    
    Summon_Characters clone = new Summon_Characters();
    Character_DataBase_PC cdbase_pc = new Character_DataBase_PC();
    MPlayer play_music; //ne példányosítsd!!
    ArvenarEffects arvfx = new ArvenarEffects();
    ArvenarExtras arvextras;
    
    FileInputStream inputimg = new FileInputStream("src/img/pc_human_warrior_arthur.jpg");
    
    FileOutputStream data_out = new FileOutputStream("src/data.df");
    FileInputStream data_back = new FileInputStream("src/data.df");
    
    Image selectedpcimage = new Image(inputimg);
    ImageView pc_imgview = new ImageView(selectedpcimage);
    TextField pc_name_textfield = new TextField(); 
    TextArea pc_bio_textarea = new TextArea();
    TextArea pc_stats_textarea = new TextArea();
    TextArea pc_description_textarea = new TextArea();
    
    Stage stagename = new Stage();
    Pane panename = new Pane();
    Pane subPane = new Pane();
    Scene scenename;
    static String hero_name = "Player 1";
    static Playable_Character selected_pc;
    
    
                    
    public ArvenarSetPC() throws FileNotFoundException {
                
        pc_name_textfield.setLayoutX(305); pc_name_textfield.setLayoutY(50);
        pc_name_textfield.setEditable(false); pc_name_textfield.setFocusTraversable(false);
        
        pc_description_textarea.setLayoutX(310); pc_description_textarea.setLayoutY(220); pc_description_textarea.setWrapText(true); pc_description_textarea.setMaxWidth(250.0);
        pc_description_textarea.setEditable(false); pc_description_textarea.setFocusTraversable(false);
            
        pc_bio_textarea.setLayoutX(100); pc_bio_textarea.setLayoutY(100); pc_bio_textarea.setWrapText(true); pc_bio_textarea.setMaxHeight(100);
        pc_bio_textarea.setEditable(false); pc_bio_textarea.setFocusTraversable(false);
        
        
        pc_stats_textarea.setLayoutX(100); pc_stats_textarea.setLayoutY(220); pc_stats_textarea.setWrapText(true); pc_stats_textarea.setMaxWidth(200.0);
        pc_stats_textarea.setEditable(false); pc_stats_textarea.setFocusTraversable(false);
        
        getPlayableCharacter();
                
        stagename.setTitle("Choose playable hero");
        stagename.setResizable(false);
        stagename.setMinHeight(600); stagename.setMinWidth(800);
        stagename.setScene(scenename);
        stagename.setResizable(false);
        stagename.initModality(Modality.APPLICATION_MODAL);
        
        Button btnChoose = new Button("Choose character"); btnChoose.setLayoutX(300); btnChoose.setLayoutY(450);
        Button btnCancel = new Button("Back"); btnCancel.setLayoutX(50); btnCancel.setLayoutY(450);
        Button btnNext = new Button("Next"); btnNext.setLayoutX(500); btnNext.setLayoutY(50);
        Button btnPrev = new Button("Previous"); btnPrev.setLayoutX(200); btnPrev.setLayoutY(50);
        
        subPane.setLayoutX(0); subPane.setLayoutY(0);
        subPane.getChildren().addAll(btnChoose, btnCancel, btnNext, btnPrev, this.pc_name_textfield, this.pc_bio_textarea, pc_stats_textarea, pc_description_textarea);
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
            pc_name_textfield.setText("You have selected: "+hero_name);
            setHero_name(hero_name);
                        
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
        btnNext.setOnAction(Action -> nextPlayer()); 
        //--------------------------------------------------------------------------------------------------------
        
        //Get the previous Player---------------------------------------------------------------------------------
        btnPrev.setOnAction(Action -> previousPlayer());
                
    }
        
        public void nextPlayer(){
        
            if(i < cdbase_pc.player_character.size()-1){
                i++;
            }
                    else {
                        i = cdbase_pc.player_character.size()-1;
                    }
                    pc_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.     
                    getPlayableCharacter();
        } 
        
        
        public void previousPlayer(){
            
           pc_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    
                    if(i > 0){
                    i--;
                    }
                    else {
                        i = 0;
                    }  
                                        
                   getPlayableCharacter();
        }
      
               
        public void useArrowKeys(){
            scenename.setOnKeyPressed(event ->{
                switch  (event.getCode()){
                    case RIGHT: nextPlayer(); break;               
                    case LEFT:  previousPlayer(); break;
                }
            });
        }
        
        
        public void getPlayableCharacter(){
            
        try {
            inputimg = new FileInputStream(cdbase_pc.players_img.get(i));
            pc_name_textfield.setText(cdbase_pc.getPcCharacter(i).getFname());
            pc_bio_textarea.setText(cdbase_pc.getPcCharacter(i).getBiography());
            pc_stats_textarea.clear();
            pc_description_textarea.clear();
            pc_description_textarea.appendText(cdbase_pc.getPcCharacter(i).getPcDesc());
            
            pc_stats_textarea.appendText("Gender: "+cdbase_pc.getPcCharacter(i).getGender()); 
            pc_stats_textarea.appendText("\nAge: "+cdbase_pc.getPcCharacter(i).getAge());
            pc_stats_textarea.appendText("\nCast: "+cdbase_pc.getPcCharacter(i).getCast());
            pc_stats_textarea.appendText("\nHealth: "+cdbase_pc.getPcCharacter(i).getHealth_point());
            pc_stats_textarea.appendText("\nSkill: "+cdbase_pc.getPcCharacter(i).getSkill_point());
            pc_stats_textarea.appendText("\nExperience: "+cdbase_pc.getPcCharacter(i).getExperience_point());
            pc_stats_textarea.appendText("\nDefence: "+cdbase_pc.getPcCharacter(i).getDefend_point());
            hero_name = cdbase_pc.getPcCharacter(i).getFname();
            selected_pc = cdbase_pc.getPcCharacter(i);
                    
            
            pc_imgview = new ImageView(new Image(inputimg));
            pc_imgview.setLayoutX(600);
            pc_imgview.setLayoutY(100);
            panename.getChildren().add(pc_imgview);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArvenarSetPC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        public String getHeroName(){
                           
                return hero_name;
            }

    public void setHero_name(String hero_name) {
        this.hero_name = hero_name;
    }
       
    public FileInputStream getHeroImg() throws FileNotFoundException{
                           
        return new FileInputStream(cdbase_pc.players_img.get(i));
            }
    public Scene pc_Scene(){
        return scenename;
    } 
    
        }       //-----------------------------------------------------------------------------------------------------   

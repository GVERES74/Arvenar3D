/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static pkg_Arvenar_Main.ArvenarFXMain.flagFullScreen;
import static pkg_Arvenar_Main.ArvenarFXMain.stageElven;
import static pkg_Arvenar_Main.ArvenarFXMain.guiResolutionX;
import static pkg_Arvenar_Main.ArvenarFXMain.guiResolutionY;

/**
 *
 * @author TE332168
 */
public class ArvenarSettings {
  
    Stage settings_stage = new Stage();
    Pane settingsMainPane, audio_pane, video_pane, popUpPane;
    static Scene settings_scene;
    
    GridPane settingsGridPane;
    VBox addSettingsVBox;
    VBox acceptChangesVBox;
    VBox exitSettingsVBox;
    CheckBox checkBoxMusicOn = new CheckBox();
    CheckBox cbFullScreen = new CheckBox();
    ChoiceBox cbResolution = new ChoiceBox();
    Slider volumeSlider = new Slider(0,100,50);
    Label slider_music_label = new Label("Music volume:");
    Label volumeSliderLabel = new Label("50%");
    Label label_music = new Label("Music On / Off");
    Label label_is_Fullscreen = new Label("Fullscreen On / Off");
    Label labelResolution = new Label("Select display resolution");
    Tooltip volumeSlider_Tooltip = new Tooltip();
    Text sceneText = new Text("SETTINGS");
    Text audioGroupText = new Text("Audio settings");
    Text videoGroupText = new Text("Video settings");
    Text acceptChangesText, cancelChangesText, changesOptionText, addSettingsText, acceptExitText, cancelExitText, exitSettingsText = new Text();
    static Text mTxtPlayMusic, mTxtStopMusic;
    Button btnAccept, btnExit;
    
    ArvenarEffects arvfx = new ArvenarEffects();
    ArvenarFonts arvfonts;
    ArvenarButtons arv_buttons = new ArvenarButtons();
    
    int stageSizeX = ArvenarFXMain.guiResolutionX;
    int stageSizeY = ArvenarFXMain.guiResolutionY;
    
    BufferedWriter settingsBW;
    
    public ArvenarSettings()throws Exception{
        
        arvfonts = new ArvenarFonts();
        settingsGridPane = new GridPane();
        addSettingsVBox = new VBox();
        acceptChangesVBox = new VBox();
        exitSettingsVBox = new VBox();
        popUpPane = new Pane();
        audio_pane = new Pane();
        video_pane = new Pane();
        settingsMainPane = new Pane();
        
                
        settings_stage.setTitle("Game settings");
        settings_stage.setScene(settings_scene);
        settings_scene = new Scene(settingsMainPane);
        
        btnAccept = arv_buttons.actionButtons(btnAccept, "Accept settings", arvfx.setGlowEffect(0.5), null, Font.font("Verdana", FontWeight.BOLD, 12), 0, 0);
        btnExit = arv_buttons.actionButtons(btnExit, "Back to Main Menu", arvfx.setGlowEffect(0.5), null, Font.font("Verdana", FontWeight.BOLD, 12), 0, 0);
        
        mTxtPlayMusic = arvfonts.newTextFormat("Play music", mTxtPlayMusic, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 12), Color.CORAL, 0, 0); //playButton.setPrefWidth(150); playButton.setGraphic(new ImageView(new Image("file:\\c:\\Users\\te332168\\Documents\\NetBeansProjects\\Arvenar\\src\\img\\music_play.png"))); playButton.setTooltip(new Tooltip("Play main theme"));
        mTxtStopMusic = arvfonts.newTextFormat("Stop music", mTxtStopMusic, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 12), Color.CORAL, 0, 0); //stopButton.setPrefWidth(150); stopButton.setGraphic(new ImageView(new Image("file:\\c:\\Users\\te332168\\Documents\\NetBeansProjects\\Arvenar\\src\\img\\music_stop.png"))); stopButton.setTooltip(new Tooltip("Stop music"));
        audioGroupText = arvfonts.newTextFormat("Audio settings", audioGroupText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.GOLD, 0, 0);
        videoGroupText = arvfonts.newTextFormat("Video settings", videoGroupText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.GOLD, 0, 0);
        addSettingsText = arvfonts.newTextFormat("Additional settings", addSettingsText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.GOLD, 0, 0);
        acceptChangesText = arvfonts.newTextFormat("Accept", acceptChangesText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.RED, 0, 10);
        cancelChangesText = arvfonts.newTextFormat("Cancel", cancelChangesText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.RED, 0, 10);
        changesOptionText = arvfonts.newTextFormat("Accept changes?", changesOptionText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 26), Color.RED, 0, 0);
        acceptExitText = arvfonts.newTextFormat("Yes", acceptExitText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.RED, 0, 10);
        cancelExitText = arvfonts.newTextFormat("No", cancelExitText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 18), Color.RED, 0, 10);
        exitSettingsText = arvfonts.newTextFormat("Exit to Main Menu?", changesOptionText, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 26), Color.RED, 0, 0);
        
        //---------------- Audio box and properties-------------------------------------------------
        //audio_pane.setLayoutX(50); audio_pane.setLayoutY(50);
        audio_pane.setMaxSize(350, 110); audio_pane.setMinSize(350, 110);
        audio_pane.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5;");
        
        audioGroupText.setLayoutX(10); audioGroupText.setLayoutY(30);
        checkBoxMusicOn.setLayoutX(140); checkBoxMusicOn.setLayoutY(39); checkBoxMusicOn.setSelected(true);
        label_music.setLayoutX(20); label_music.setLayoutY(40);
        label_music.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        volumeSlider.setLayoutX(130); volumeSlider.setLayoutY(72);
        volumeSlider_Tooltip.setText((int)volumeSlider.getValue()+"%");
        volumeSlider.setTooltip(volumeSlider_Tooltip);
        slider_music_label.setLayoutX(20); slider_music_label.setLayoutY(70);
        slider_music_label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        volumeSliderLabel.setLayoutX(280); volumeSliderLabel.setLayoutY(70);
        volumeSliderLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        audio_pane.getChildren().addAll(audioGroupText, checkBoxMusicOn, label_music, volumeSlider, slider_music_label, volumeSliderLabel);
        
        //---------------- Video box and properties-------------------------------------------------
        
        //video_pane.setLayoutX(50); video_pane.setLayoutY(200);
        video_pane.setMaxSize(350, 110); video_pane.setMinSize(350, 110);
        video_pane.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5;");
        
        videoGroupText.setLayoutX(10); videoGroupText.setLayoutY(30);
        label_is_Fullscreen.setLayoutX(20); label_is_Fullscreen.setLayoutY(40);
        label_is_Fullscreen.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        labelResolution.setLayoutX(20); labelResolution.setLayoutY(70);
        labelResolution.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        cbFullScreen.setLayoutX(170); cbFullScreen.setLayoutY(38); cbFullScreen.setSelected(false);
        cbResolution.setLayoutX(170); cbResolution.setLayoutY(70);
        cbResolution.getItems().add("1366x768"); cbResolution.getItems().add("1920x1080"); cbResolution.setValue("1366x768");
        sceneText = arvfx.setTextEffect(sceneText, arvfx.setGlowEffect(0.5), null, Font.font("Verdana", FontWeight.BOLD, 36), Color.SILVER, 50, stageSizeY-200);
        video_pane.getChildren().addAll(videoGroupText, label_is_Fullscreen, cbFullScreen, labelResolution, cbResolution);
        System.out.println(stageSizeX+" / "+stageSizeY);
        /*settings_stage.setMinWidth(800);
        settings_stage.setMinHeight(600);
        settings_stage.initModality(Modality.APPLICATION_MODAL);*/
        
                
        //---------- Option menu text's properties-------------------------------------------------
        addSettingsVBox.setLayoutX(10); addSettingsVBox.setLayoutY(10);
        addSettingsVBox.setMaxSize(350, 110); addSettingsVBox.setMinSize(350, 110);
        addSettingsVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 20;");
        addSettingsVBox.setSpacing(10); 
        addSettingsVBox.getChildren().addAll(addSettingsText, mTxtPlayMusic, mTxtStopMusic);
        
        //------------Accept changes?-------------------------------
        
        acceptChangesVBox.setMaxSize(300, 150); acceptChangesVBox.setMinSize(300, 150);
        acceptChangesVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.6); -fx-background-radius: 5; -fx-padding: 20;");
        acceptChangesVBox.setSpacing(10); 
        acceptChangesVBox.setAlignment(Pos.CENTER);
        acceptChangesVBox.getChildren().addAll(changesOptionText, acceptChangesText, cancelChangesText);
        
        //------------Exit Settings page?-------------------------------
        
        exitSettingsVBox.setMaxSize(300, 150); exitSettingsVBox.setMinSize(300, 150);
        exitSettingsVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.6); -fx-background-radius: 5; -fx-padding: 20;");
        exitSettingsVBox.setSpacing(10); 
        exitSettingsVBox.setAlignment(Pos.CENTER);
        exitSettingsVBox.getChildren().addAll(exitSettingsText, acceptExitText, cancelExitText);
        
              
        
        //---------- Gridpane for arrange children-------------------------------------------------
        settingsGridPane.setLayoutX(20); settingsGridPane.setLayoutY(20);
        settingsGridPane.add(audio_pane, 0,0);
        settingsGridPane.add(video_pane, 0,1);        
        settingsGridPane.add(addSettingsVBox, 1,0);        
        settingsGridPane.add(btnAccept, 1,1);
        settingsGridPane.add(btnExit, 1,2);                
        settingsGridPane.setAlignment(Pos.CENTER);
        settingsGridPane.setVgap(20); settingsGridPane.setHgap(20);
        //settingsGridPane.setStyle("-fx-background-color: rgba(0, 50, 50, 0.6); -fx-background-radius: 5; -fx-padding: 20;");
        
        //---------- Pack all children nodes-------------------------------------------------
        settingsMainPane.setBackground(new Background(new BackgroundImage(new Image("/img/bkg_settings.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        settingsMainPane.getChildren().addAll(settingsGridPane, sceneText);
                         
        arvfx.btnTextEffects(mTxtPlayMusic);
        arvfx.btnTextEffects(mTxtStopMusic);
        arvfx.btnTextEffects(acceptChangesText);
        arvfx.btnTextEffects(cancelChangesText);
        arvfx.btnTextEffects(acceptExitText);
        arvfx.btnTextEffects(cancelExitText);
        
                    
        //---------------------------------------------------    
        
        mTxtPlayMusic.setOnMouseClicked(action ->{MPlayer.mPlayer_start("journey.mp3", true, 5);});
        
        mTxtStopMusic.setOnMouseClicked(action ->{MPlayer.mediaPlayer.stop();});
        
        
        
        volumeSlider.valueProperty().addListener( 
            new ChangeListener<Number>() { 
  
                public void changed(ObservableValue <? extends Number >  
                      observable, Number oldValue, Number newValue){ 
                
            volumeSlider_Tooltip.setText((int)volumeSlider.getValue()+"%");
            volumeSlider.setTooltip(volumeSlider_Tooltip);
            volumeSliderLabel.setText((int)volumeSlider.getValue()+"%");
            volumeSliderLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            MPlayer.setVolume((int)volumeSlider.getValue());
                
            } 
        }); 
        
               
        checkBoxMusicOn.setOnAction(action -> {
            
            try{
            if (checkBoxMusicOn.isSelected() == true){
            
                volumeSlider.setDisable(false);
                MPlayer.setAudio_on(true);
                
            }
            
            else if (checkBoxMusicOn.isSelected() == false){
                
                volumeSlider.setDisable(true);
                MPlayer.setAudio_on(false);
                MPlayer.mPlayer_stop();
                volumeSliderLabel.setText("Music off");
                volumeSliderLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                
            } 
            }   
                catch (Exception e){
                    e.printStackTrace();
                }
                       
        });
        
        btnAccept.setOnAction(action -> {
            popUpPane.getChildren().add(acceptChangesVBox);
            showMenuPopupPane();
             
        });
                    
        acceptChangesText.setOnMouseClicked(action -> {
            
                try {
                    //settings_stage.close();
                    switch ((String) cbResolution.getValue()){
                            case "1366x768": ArvenarFXMain.stageElven.setWidth(1366); 
                                             ArvenarFXMain.stageElven.setHeight(768); 
                                             break;
                            case "1920x1080": ArvenarFXMain.stageElven.setWidth(1920); 
                                              ArvenarFXMain.stageElven.setHeight(1080); 
                                              break;
                    }
                                        
                    
                    if (cbFullScreen.isSelected()){
                        ArvenarFXMain.flagFullScreen = 1;
                        ArvenarFXMain.stageElven.setFullScreen(true);
                        ArvenarFXMain.stageElven.setFullScreenExitHint("");
                    }
                    else if (!cbFullScreen.isSelected()){
                        ArvenarFXMain.flagFullScreen = 0;
                        ArvenarFXMain.stageElven.setFullScreen(false);
                        
                    }               
                    
                } catch (Exception ex) {
                    Logger.getLogger(ArvenarSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            try {
                saveSettings();
            } catch (IOException ex) {
                Logger.getLogger(ArvenarSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
                ArvenarFXMain.stageElven.setTitle("Arvenar - Elven Tales - 2020 - by Gabor Veres"+" - Width: "+ArvenarFXMain.stageElven.getWidth()+" Height: "+ArvenarFXMain.stageElven.getHeight()+" FullScreen: "+ArvenarFXMain.stageElven.isFullScreen());
                popUpPane.getChildren().remove(acceptChangesVBox);
                showMenuPopupPane();
              
        });
        
        
        cancelChangesText.setOnMouseClicked(action -> {
            popUpPane.getChildren().remove(acceptChangesVBox);
            showMenuPopupPane();
           
            return;
        
        });    
            
        
         btnExit.setOnAction(action -> {
             popUpPane.getChildren().add(exitSettingsVBox);
             showMenuPopupPane();
           
                                
         }); 
         
         acceptExitText.setOnMouseClicked(action -> {
            popUpPane.getChildren().remove(exitSettingsVBox);
            showMenuPopupPane();
            ArvenarFXMain.stageElven.setScene(ArvenarFXMain.sceneElven);
            ArvenarFXMain.stageElven.setFullScreen(ArvenarFXMain.flagFullScreen == 1 ? true : false);
                        
            return;
        
        });    
         
         cancelExitText.setOnMouseClicked(action -> {
            popUpPane.getChildren().remove(exitSettingsVBox);
            showMenuPopupPane();
            
            return;
        
        });    
        
    }
    
    public void showMenuPopupPane(){
        
        if (!settingsMainPane.getChildren().contains(popUpPane)){ 
            popUpPane.setLayoutX((settingsMainPane.getWidth()/2)-100);
            popUpPane.setLayoutY((settingsMainPane.getHeight()/2)-100);
            settingsMainPane.getChildren().add(popUpPane);
            settingsGridPane.setDisable(true);
        }
        else if (settingsMainPane.getChildren().contains(popUpPane)){
            settingsMainPane.getChildren().remove(popUpPane);
            settingsGridPane.setDisable(false);
        } 
    }
    
    
    public void saveSettings() throws IOException{
        
            settingsBW = new BufferedWriter(new FileWriter("c:\\Users\\te332168\\Documents\\NetBeansProjects\\Arvenar\\src\\settings.txt"));
            
            settingsBW.write("ResX = "+ArvenarFXMain.guiResolutionX);
            settingsBW.write("ResY = "+ArvenarFXMain.guiResolutionY);
            settingsBW.write("FullScreen = "+ArvenarFXMain.flagFullScreen);
            
            settingsBW.flush();
            settingsBW.close();
    }
    
    
        
    public Scene settings_Scene(){
        
        return settings_scene;
    }
   
}
    

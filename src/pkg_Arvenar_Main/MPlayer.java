/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;




import java.io.File;
import java.nio.file.Paths;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class MPlayer {
 
    static AudioClip mediaPlayer = new AudioClip(new Media(Paths.get("src/music/main.mp3").toUri().toString()).getSource()); 
    static boolean audio_on = true; //audio on or off
    static int volume = 5;
    
    Pane mPlayerPane = new Pane();
    VBox mp3PlayerVBox = new VBox();
    
    ComboBox ostCombo = new ComboBox();
    static Text titleText, nowPlayingLabel, mPlayerAppName;
    
    static String ostFileName, ostURL, selectedSoundTrack;
    static String ostPathName = new String("src/music/");
    static String nowPlayingFileName = mediaPlayer.getSource();
       
    ArvenarFonts arvfonts = new ArvenarFonts();
    ArvenarEffects arvfx = new ArvenarEffects();
    ArvenarExtras arvextras;
        
    Button openFileButton = new Button("Open file");
    
    //---------------------------------------------------------------------
    public MPlayer() throws Exception{
        
        
        buildMPlayerGUI();
        
        createSongList();
        
        openFileButton.setOnMouseClicked(action -> {
                        
            FileChooser mp3filechooser = new FileChooser();
            mp3filechooser.setInitialDirectory(new File(ostPathName));
            mp3filechooser.setTitle("Open MP3 Music Files");
            mp3filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3 files", "*.mp3"));
            File selectedMP3file = mp3filechooser.showOpenDialog(new Stage());
                if(selectedMP3file == null){ return;} //Cancel selection
            
            mPlayer_stop();
            String filePath = selectedMP3file.getPath();
            String selectedSoundTrackWithFullPath = selectedMP3file.toURI().toString();
            selectedSoundTrack = selectedSoundTrackWithFullPath.replaceAll("file:/C:/Users/te332168/Documents/NetBeansProjects/Arvenar/src/music/", "");
            
            //ostCombo.setValue(selectedSoundTrack.replaceAll("file:/C:/Users/te332168/Documents/NetBeansProjects/Arvenar/src/music/", "")); //we want to see the opened file also in the combobox as selected ost
            ostURL = filePath;
            
            initMediaPlayer();
            
            mediaPlayer.play();
            
            nowPlayingLabel.setText("Now playing: "+selectedSoundTrack+" (File path: "+filePath+")");
            
            System.out.println("Now opened as file and playing: "+selectedSoundTrack);
            
                      
        
        });
    }
       
            
    public static void mPlayer_start(String ostFileName, Boolean playing, int repeat) {

    selectedSoundTrack = ostFileName; //Initial ost for the music player
    ostURL = ostPathName+selectedSoundTrack;
    
    initMediaPlayer();
          

      //MediaPlayer stops playing music afer 5-10 seconds --> use AudioClip instead

             mediaPlayer.setCycleCount(repeat);
             if(audio_on == true){
                 mediaPlayer.setVolume(volume);
                 mediaPlayer.play();
             }
             else {
                 mediaPlayer.stop();
             }

     }         

        public static void mPlayer_stop(){
            
           if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            else System.out.println("Music is no playing");
        } 

    public static void setAudio_on(boolean audio_on) {
        MPlayer.audio_on = audio_on;
    }

    public static void setVolume(int volume) {
        MPlayer.volume = volume;
    }
    
    public void mPlayerGUI(){
        
    }
    
    void createSongList(){
        
       
        ostCombo.getItems().add("browse.mp3");
        ostCombo.getItems().add("credits1.mp3");
        ostCombo.getItems().add("journey.mp3");
        ostCombo.getItems().add("main.mp3");
        ostCombo.getItems().add("menu.mp3");
        ostCombo.getItems().add("monastery.mp3");
        ostCombo.getItems().add("outdoor.mp3");
        ostCombo.getItems().add("tavern.mp3");
        ostCombo.getItems().add("tavern2.mp3");
        ostCombo.getItems().add("valley.mp3");
        ostCombo.getItems().add("village.mp3");
                
        ostCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldvalue, Object newvalue){
             
                mPlayer_stop();
                selectedSoundTrack = ostCombo.getSelectionModel().getSelectedItem().toString();
                
                System.out.println("Now selected from ComboBox and playing: "+selectedSoundTrack);
                         
            ostURL = ostPathName+selectedSoundTrack;
            //ostFileName = ; foreach ciklus a könyvtár tartalmának beolvasására
            
            initMediaPlayer();
            
            nowPlayingLabel.setText("Now playing: "+selectedSoundTrack);
            mediaPlayer.play();
            
        }});
        
        
    }
    
    static void initMediaPlayer(){
        
        Media music = new Media(Paths.get(ostURL).toUri().toString());    
        mediaPlayer = new AudioClip(music.getSource());
        mediaPlayer.setCycleCount(5);
        nowPlayingFileName = mediaPlayer.getSource();
        System.out.println("Source: "+nowPlayingFileName);
        
    }
    
    void buildMPlayerGUI(){
        
        mPlayerPane.setStyle("-fx-background-color: rgba(64, 50, 64, 0.4); -fx-background-radius: 5; -fx-padding: 20;");
        
        nowPlayingLabel = new Text("Now playing: "+nowPlayingFileName);
        mPlayerAppName = new Text("Ahoy Matey - Music Player");

        nowPlayingLabel = arvfonts.newTextFormat("Now playing: "+nowPlayingFileName, nowPlayingLabel, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 12), Color.AQUAMARINE, 0, 0);               
        mPlayerAppName = arvfonts.newTextFormat("Ahoy Matey - Music Player", mPlayerAppName, arvfx.shadowEffect, null, Font.font("Verdana", FontWeight.BOLD, 18), Color.BISQUE, 0, 0);          
        
        mp3PlayerVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 30; -fx-spacing: 30");
        mp3PlayerVBox.setLayoutX(10); mp3PlayerVBox.setLayoutY(10);
        mp3PlayerVBox.setMinHeight(400); mp3PlayerVBox.setMinWidth(600);
        mp3PlayerVBox.setAlignment(Pos.TOP_CENTER);
        
        mp3PlayerVBox.getChildren().addAll(mPlayerAppName, nowPlayingLabel, openFileButton, ostCombo);
        mPlayerPane.getChildren().add(mp3PlayerVBox);
    }
}   
  
    
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;




import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
import javax.swing.event.ChangeListener;

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
    ObservableList<String> ost = FXCollections.observableArrayList();
    ListView<String> ostListView = new ListView<String>(ost);
    static Text titleText = new Text("Music Player");
    static Text musicTitle;
    Text mplayerTitle;
    static String song = new String();
    String soundTrack;
    
    ArvenarFonts arvfonts = new ArvenarFonts();
    ArvenarEffects arvfx = new ArvenarEffects();
    ArvenarExtras arvextras;
    
    
    Button openFileButton = new Button("Open");
    
    public MPlayer() throws Exception{
        
        
        musicTitle = new Text("Now playing: "+song);
        mplayerTitle = new Text("Ahoy Matey - Music Player");
    
        mPlayerPane.setStyle("-fx-background-color: rgba(64, 50, 64, 0.4); -fx-background-radius: 5; -fx-padding: 20;");
        musicTitle = arvfonts.newTextFormat("Now playing: "+song, musicTitle, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 12), Color.AQUAMARINE, 0, 0);               
        mplayerTitle = arvfonts.newTextFormat("Ahoy Matey - Music Player", mplayerTitle, arvfx.shadowEffect, null, Font.font("Verdana", FontWeight.BOLD, 18), Color.BISQUE, 0, 0);          
        
//------------Side pane for Music Player-----------------
        mp3PlayerVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5; -fx-padding: 30;");
        mp3PlayerVBox.setLayoutX(10); mp3PlayerVBox.setLayoutY(10);
        mp3PlayerVBox.setMinHeight(400); mp3PlayerVBox.setMinWidth(600);
        mp3PlayerVBox.getChildren().addAll(mplayerTitle, musicTitle, openFileButton, ostListView);
        
        mPlayerPane.getChildren().add(mp3PlayerVBox);
        
        createSongList();
        
        openFileButton.setOnMouseClicked(action -> {
            
            mPlayer_stop();
            FileChooser mp3filechooser = new FileChooser();
            mp3filechooser.setInitialDirectory(new File("src/music"));
            mp3filechooser.setTitle("Open MP3 Music Files");
            mp3filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3 files", "*.mp3"));
            File selectedMP3file = mp3filechooser.showOpenDialog(new Stage());
                        
            String musicName = selectedMP3file.toURI().toString();
            Media music = new Media(musicName);    
            AudioClip mediaPlayerExtras = new AudioClip(music.getSource());
            mediaPlayer = mediaPlayerExtras;
            MPlayer.mediaPlayer.setCycleCount(5);
            mediaPlayer.play();
            musicTitle.setText("Now playing: "+musicName);
            
            System.out.println(musicName);
                      
        
        });
    }
       
            
    public static void mPlayer_start(String title, Boolean playing, int repeat) {

    String theme = "src/music/"+title;
    song = theme;
    

    Media music = new Media(Paths.get(theme).toUri().toString());    

    mediaPlayer = new AudioClip(music.getSource());
          

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
        
        ostListView.getItems().add("monastery.mp3");
        ostListView.getItems().add("tavern.mp3");
                
                        soundTrack = ostListView.getSelectionModel().getSelectedItem();
              
            mPlayer_stop();
            song = soundTrack;
            Media music = new Media(song);    
            AudioClip mediaPlayerExtras = new AudioClip(music.getSource());
            mediaPlayer = mediaPlayerExtras;
            musicTitle.setText("Now playing: "+song);
            mediaPlayer.play();
        }
        
        
    }
      
  
    
    

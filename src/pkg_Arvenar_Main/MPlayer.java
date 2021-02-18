/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;




import java.nio.file.Paths;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.text.Text;

/**
 *
 * @author TE332168
 */
public class MPlayer {
 
    static AudioClip mediaPlayer = new AudioClip(new Media(Paths.get("src/music/main.mp3").toUri().toString()).getSource()); 
    static boolean audio_on = true; //audio on or off
    static int volume = 5;
    
    static Text titleText = new Text(new String("Music Player"));
    static Text musicTitle = new Text();
    static String song = new String();
    ArvenarFonts arvfonts = new ArvenarFonts();
    
    public MPlayer(){
    
    
        
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
      
}    
    
    

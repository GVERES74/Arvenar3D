/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author TE332168
 */
public class ArvenarFonts {
    
    public ArvenarFonts(){
        
    }
    
    
    public Text newTextFormat(String str, Text text, Effect effect1, Effect effect2, Font font, Color color, int posX, int posY){
        
        text = new Text();
        text.setText(str);
        text.setEffect(effect1);
        text.setEffect(effect2);
        text.setFont(font);
        text.setFill(color);
        text.setLayoutX(posX); text.setLayoutY(posY);
        text.setStyle("-fx-background-color: rgba(128, 128, 128, 0.8); -fx-background-radius: 5; -fx-padding: 5;");
        return text;
    
    } 
}

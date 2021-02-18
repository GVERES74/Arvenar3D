/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author TE332168
 */
public class ArvenarButtons {
    
    ArvenarEffects arvfx = new ArvenarEffects();
    
public ArvenarButtons(){
    
    //Konstruktor
}
    
   public Button actionButtons(Button btn, String btntext, Effect effect1, Effect effect2, Font font, int posX, int posY){
        
        btn = new Button();
        btn.setText(btntext);
        btn.setEffect(effect1);
        btn.setEffect(effect2);
        btn.setFont(font);
        btn.setTranslateX(posX); btn.setTranslateY(posY);
        btn.setStyle("-fx-text-fill: blue; -fx-background-color: rgba(238, 238, 0, 0.9); -fx-background-radius: 5; -fx-padding: 5;");
        arvfx.buttonEffects(btn);
        return btn;
       
    
}    
    
}

 

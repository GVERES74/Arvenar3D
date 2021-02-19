/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author TE332168
 */
public class ArvenarEffects {
 
    DropShadow shadowEffect = new DropShadow();
    Glow glowEffect = new Glow();
    Reflection reflectionEffect = new Reflection(); 
    
    public ArvenarEffects(){
        
        
    }
    
    public DropShadow setShadowEffect(Double offsetX, Double offsetY, Double spread, Color color, Double ch1, Double ch2, Double ch3){
        
        shadowEffect.setOffsetY(offsetY);
        shadowEffect.setOffsetX(offsetX); 
        shadowEffect.setSpread(spread); 
        shadowEffect.setColor(Color.color(ch1, ch2, ch3));
        return shadowEffect;
    }
    
    public void buttonEffects(Button button){
        
        button.setOnMouseEntered(action -> {
            button.setEffect(setShadowEffect(2.0, 2.0, 0.50, Color.GOLDENROD, 0.4, 0.5, 0.3)); //0.0 = black; 1.0 = white
            //button.setEffect(setGlowEffect(0.2));
            //button.setEffect(setReflectionEffect());
            button.setScaleX(1.2); button.setScaleY(1.2);
            //button.setTranslateX(20);
        });
        
        button.setOnMouseExited(action -> {
            button.setEffect(null);
            button.setScaleX(1.0); button.setScaleY(1.0);
            //button.setTranslateX(0);
        });
        
        
        
    }
    
    public void btnTextEffects(Text text){
                        
        text.setOnMouseEntered(action -> {
            text.setEffect(setShadowEffect(2.0, 2.0, 0.50, Color.AQUA, 0.4, 0.5, 0.3)); //0.0 = black; 1.0 = white
            
            //button.setEffect(setGlowEffect(0.2));
            //button.setEffect(setReflectionEffect());
            text.setScaleX(1.2); text.setScaleY(1.2);
            //button.setTranslateX(20);
            
        });
        
        text.setOnMouseExited(action -> {
            text.setEffect(null);
            text.setScaleX(1.0); text.setScaleY(1.0);
            text.setTranslateX(0);
        });
        
    }
    
    
    public Glow setGlowEffect(Double glevel){
        //glowEffect.setLevel(0.0);
        glowEffect.setLevel(glevel);
        return glowEffect;
    }
    
    
    public Double getGlowEffect(Double glevel){
        return glowEffect.getLevel();
        
    }
    
    
    public Reflection setReflectionEffect(){
        
        reflectionEffect.setFraction(0.3);
        reflectionEffect.setTopOffset(0.1);
        return reflectionEffect;
    };
    
    
    public Text setTextEffect(Text text, Effect effect1, Effect effect2, Font font, Color color, int posX, int posY){
        
        text.setEffect(effect1);
        text.setEffect(effect2);
        text.setFont(font);
        text.setFill(color);
        text.setTranslateX(posX); text.setTranslateY(posY);
        return text;
    
    }

       
}
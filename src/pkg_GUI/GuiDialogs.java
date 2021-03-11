/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_GUI;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author TE332168
 */
public class GuiDialogs {
    
    
    
    public GuiDialogs(){
        
        
    }
    
    public VBox showPopupWindow(Text header, Text option1, Text option2, Text option3){
        VBox popupVBox = new VBox();
        popupVBox.setMaxSize(400, 400); popupVBox.setMinSize(300, 200);
        popupVBox.setStyle("-fx-background-color: rgba(0, 50, 50, 0.6); -fx-background-radius: 5; -fx-padding: 20; -fx-spacing: 10");
        popupVBox.setAlignment(Pos.CENTER);
        
        //header.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        //header.setFill(Color.GHOSTWHITE);
        //option1.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        //option1.setFill(Color.AZURE);
        popupVBox.getChildren().addAll(header, option1, option2, option3);
        return popupVBox;
                
    }
}

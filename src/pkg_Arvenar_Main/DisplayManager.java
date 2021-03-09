/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 *
 * @author TE332168
 */
public class DisplayManager {
    
    Rectangle2D  screenSize = Screen.getPrimary().getBounds();
        int resolutionX = (int)screenSize.getWidth();
        int resolutionY = (int)screenSize.getHeight();
    
    public void Displaymanager(){
        
        
    }
    
    
    public int getResolutionX() {
        return resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public void setResolution(int resolutionX, int resolutionY) {
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
    }

     
}

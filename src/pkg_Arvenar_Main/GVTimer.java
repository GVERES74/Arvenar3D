/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Gabor Veres
 */
public class GVTimer {
    
    static Timer delayer;
    
    static Weather weather = new Weather();
            
    public GVTimer(){
        
             
    }        
    
    public void textMover(int delayms) throws InterruptedException {
    
        ActionListener taskPerformer = new ActionListener() {
                    
            public void actionPerformed(ActionEvent evt) {
                           
                    ArvenarCredits.moveText();
                    
            }
    };
        delayer = new Timer(delayms, taskPerformer);
        delayer.start();
    }
    
    public void textScaler(int delayms) throws InterruptedException {
    
        ActionListener taskPerformer = new ActionListener() {
                    
            public void actionPerformed(ActionEvent evt) {
                           
                    ArvenarCredits.vibraText();
                    
            }
    };
        delayer = new Timer(delayms, taskPerformer);
        delayer.start();
    }   
    
    public void mainTimer(int delayms) throws InterruptedException {
    
        ActionListener taskPerformer = new ActionListener() {
                    
            public void actionPerformed(ActionEvent evt) {
                           
                    ArvenarFXMain.buttonTxtFxer();
                    
            }
    };
        delayer = new Timer(delayms, taskPerformer);
        delayer.start();
    }   
    
    
        
    public void Falling (int delayms) throws InterruptedException {
    
        ActionListener taskPerformer = new ActionListener() {
                    
            public void actionPerformed(ActionEvent evt) {
                     
                                                
            }
    };
        delayer = new Timer(delayms, taskPerformer);
        delayer.start();
    }   
}

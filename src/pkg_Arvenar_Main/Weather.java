/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;



import java.util.Random;
import java.util.Timer;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


/**
 *
 * @author TE332168
 */
public class Weather {
    
    Random random = new Random();
    final int FLAKECOUNT = 100+random.nextInt(200);
    Circle element = new Circle();
    Circle[] drops = new Circle[FLAKECOUNT];
    
    double elementSpeed = random.nextDouble()*2+1;
    double windSpeed = random.nextDouble()*2+1;
    
    int i;
    int startY = 0;
    int startX = 0;
    
    Timer delayer;
       
    
    
       //int snowFlakeDx = -5 + (int) (Math.random()*11); //generates random snowflake falling effect; //generates random snowflake falling effect
        //int snowFlakeDy = (int) (Math.random()*1); //generates random snowflake falling effect
                   
        //int randomSnowFlakePosX = -600 + (int) (Math.random()*1000);
        //int randomSnowFlakePosY = -400 + (int) (Math.random()*600);
    
    public Weather(){
        
            element.setTranslateX(random.nextInt(1000));
            element.setTranslateY(random.nextInt(200));
            
            
                              
    }
        
    public void Falling(Boolean falling, int wtype){
        
        if (falling == true) {
        element.setTranslateY(element.getTranslateY()+elementSpeed); //Start falling
        element.setTranslateX(element.getTranslateX()+ windSpeed); //swings element sidewards
                                             
                
            if (element.getTranslateY() > 400){
                
                createNewElement(wtype);
                 
    }}}
    
    
    public void createNewElement(int wtype){
                
            switch (wtype){
            case 0: break; //weather = sunny (dry, no drops)
            case 1: element.setRadius(random.nextDouble()*1+1); //weather = rainy
                    elementSpeed = random.nextDouble()*10+5;
                    windSpeed = randomWind(1,1);
                    element.setFill(Color.GRAY); 
                    element.setOpacity(random.nextDouble()*0.5+0.5); 
                    break;
            
            case 2: element.setRadius(random.nextDouble()*2+1); //weather = snowy
                    elementSpeed = random.nextDouble()*2+1;
                    element.setFill(Color.WHITE); 
                    windSpeed = randomWind(2,-1);
                    element.setOpacity(1.0); 
                    break;
        }
            element.setLayoutX(startX+random.nextInt(1200));
            element.setLayoutY(startY+random.nextInt(20));

    }
    
    
    public Double randomWind(int blowStrenght, int blowDirection){ //blowDirection --> negative number means blowing direction = from east to west
                double dx = Math.random();
                double speed = 0;
                if (dx < 0.5 && blowDirection ==0) {speed = -random.nextInt(blowStrenght);}
                else if (dx > 0.5 && blowDirection ==0) {speed = random.nextInt(blowStrenght);}
                else if (dx > 0.9 && blowDirection ==0) {speed = 0;}
                else speed = random.nextInt(blowDirection);
                               
                return speed;
                
    }
    
    public Double swingElement(double strenght){
                double dx = Math.random();
                double swingSpeed = 0;
                if (dx < 0.5) swingSpeed = -strenght+random.nextDouble();
                if (dx > 0.5) swingSpeed = strenght+random.nextDouble();
                if (dx > 0.9) swingSpeed = 0;
                return swingSpeed;
                
    }
    
    public void createAnimation(Pane root, int sX, int sY, int eX, int eY, int weatherType){
        
        for (int i = 0; i < FLAKECOUNT; i++){
            
            drops[i] = new Circle(1,1,2);
            
            switch (weatherType){
            case 0: break; //weather = sunny (dry, no drops)
            case 1: drops[i].setRadius(random.nextDouble()*1+1); //weather = rainy
                    elementSpeed = random.nextDouble()*2;
                    windSpeed = randomWind(100,100); 
                    drops[i].setFill(Color.GRAY); 
                    break;
            
            case 2: drops[i].setRadius(random.nextDouble()*3+1); //weather = snowy
                    elementSpeed = 5+random.nextDouble()*10;
                    drops[i].setFill(Color.WHITE); 
                    windSpeed = randomWind(500,0);
                    break;
        }
                                    
            drops[i].setTranslateX(sX+random.nextInt(eX));
            drops[i].setTranslateY(sY);
            root.getChildren().add(drops[i]);
            
                        
            TranslateTransition falling = new TranslateTransition();
            falling.setNode(drops[i]);
            falling.setFromY(sY); falling.setToY(eY);
                       
            
            falling.setByY(eY);
            
            falling.setByX(windSpeed); 
                                    
            falling.setDuration(Duration.seconds(elementSpeed));
            falling.setCycleCount(-1);
            
            falling.play();
            
                                               
    }}}

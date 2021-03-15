/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_3DPack;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 *
 * @author TE332168
 */
public class Arvenar3DSkyBox {
    
    ImageView iwTop, iwLeft, iwFront, iwRight, iwBack, iwBottom;
    Image imTop, imLeft, imFront, imRight, imBack, imBottom;
//    String imagePath = "textures/skybox/sunny/";
    String imagePath = "textures/skybox/cloudy/";
    
    public Arvenar3DSkyBox(){
        
     imTop = new Image(imagePath+"sky_top.jpg");   
     imLeft = new Image(imagePath+"sky_left.jpg");   
     imFront = new Image(imagePath+"sky_front.jpg");   
     imRight = new Image(imagePath+"sky_right.jpg");   
     imBack = new Image(imagePath+"sky_back.jpg");   
     imBottom = new Image(imagePath+"sky_bottom.jpg");   
     
        iwTop = new ImageView(imTop); 
        iwLeft = new ImageView(imLeft);
        iwFront = new ImageView(imFront);
        iwRight = new ImageView(imRight);
        iwBack = new ImageView(imBack);
        iwBottom = new ImageView(imBottom);
        
    }
    
    public void buildSkyBox(Group group){
        
        arrangeImageViews(iwTop, -50000, -75000, 0, 100000, 100000, 90, Rotate.X_AXIS);
        arrangeImageViews(iwBottom, -50000, -50000, 0, 100000, 100000, 90, Rotate.X_AXIS);
        arrangeImageViews(iwFront, -50000, -25000, -50000, 100000, 50000, 0, Rotate.Y_AXIS);
        arrangeImageViews(iwBack, -50000, -25000, 50000, 100000, 50000, 0, Rotate.Y_AXIS);
        arrangeImageViews(iwLeft, -100000, -25000, 0, 100000, 50000, 90, Rotate.Y_AXIS);
        arrangeImageViews(iwRight, 0, -25000, 0, 100000, 50000, 90, Rotate.Y_AXIS);
        
        group.getChildren().addAll(iwTop, iwLeft, iwFront, iwRight, iwBack, iwBottom);
    }
    
    void arrangeImageViews(ImageView imW, int xPos, int yPos, int zPos, int width, int height, int rAngle, Point3D rAxis){
        imW.setFitWidth(width);
        imW.setFitHeight(height);
        imW.setTranslateX(xPos);
        imW.setTranslateY(yPos);
        imW.setTranslateZ(zPos);
        imW.setRotate(rAngle);
        imW.setRotationAxis(rAxis);
        
    }
    
}

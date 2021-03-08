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
    String imagePath = "textures/skybox/sunny/";
    
    public Arvenar3DSkyBox(){
        
     imTop = new Image(imagePath+"sky_top.png");   
     imLeft = new Image(imagePath+"sky_left.png");   
     imFront = new Image(imagePath+"sky_front.png");   
     imRight = new Image(imagePath+"sky_right.png");   
     imBack = new Image(imagePath+"sky_back.png");   
     imBottom = new Image(imagePath+"sky_bottom.png");   
     
        iwTop = new ImageView(imTop); 
        iwLeft = new ImageView(imLeft);
        iwFront = new ImageView(imFront);
        iwRight = new ImageView(imRight);
        iwBack = new ImageView(imBack);
        iwBottom = new ImageView(imBottom);
        
    }
    
    public void buildSkyBox(Group group){
        
        arrangeImageViews(iwTop, -25000, -37500, 0, 50000, 50000, 90, Rotate.X_AXIS);
        arrangeImageViews(iwBottom, -25000, -25000, 0, 50000, 50000, 90, Rotate.X_AXIS);
        arrangeImageViews(iwFront, -25000, -12500, -25000, 50000, 25000, 0, Rotate.Y_AXIS);
        arrangeImageViews(iwBack, -25000, -12500, 25000, 50000, 25000, 0, Rotate.Y_AXIS);
        arrangeImageViews(iwLeft, -50000, -12500, 0, 50000, 25000, 90, Rotate.Y_AXIS);
        arrangeImageViews(iwRight, 0, -12500, 0, 50000, 25000, 90, Rotate.Y_AXIS);
        
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

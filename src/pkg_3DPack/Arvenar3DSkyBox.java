/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_3DPack;

import java.io.FileNotFoundException;
import java.util.Random;
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
    
    Arvenar3DObjects objects3d;
    String sbTexturePath = "textures/skybox/";
    String sbImagePath;
    Image cubeMapSideImage;
    String[] cubeMapSideImageFileName = {"sky_top.png", "sky_left.png", "sky_front.png", "sky_right.png", "sky_back.png", "sky_bottom.png"};
    ImageView iwTop, iwLeft, iwFront, iwRight, iwBack, iwBottom;
    ImageView[] cubeMapSideImageView = {iwTop, iwLeft, iwFront, iwRight, iwBack, iwBottom};
    //front and left must be mirrored / top must be rotated by 90 to the right 
    
    int worldSize = 100000;
    
    
    
    Random random = new Random();
        
    public Arvenar3DSkyBox(){
        
    changeSky();
        
    }
    
    
    public void buildMeshCubeMapSkyWorld(Group group) throws FileNotFoundException{
             
        objects3d = new Arvenar3DObjects();
            int cWidth = worldSize;
            int cHeight = worldSize/2;
            int cDepth = worldSize;

                                                                        
            group.getChildren().add(objects3d.object3DMesh(cWidth, cHeight, cDepth, -cWidth/2, -cHeight/2, -cDepth/2, "skyworld.png"));
    }
    
    public void buildSkyBox(Group group){
        
        arrangeImageViews(cubeMapSideImageView[0], -worldSize/2, -75000, 0, worldSize, worldSize, 90, Rotate.X_AXIS);
        arrangeImageViews(cubeMapSideImageView[1], -worldSize, -worldSize/4, 0, worldSize, worldSize/2, 90, Rotate.Y_AXIS);
        arrangeImageViews(cubeMapSideImageView[2], -worldSize/2, -worldSize/4, -worldSize/2, worldSize, worldSize/2, 0, Rotate.Y_AXIS);
        arrangeImageViews(cubeMapSideImageView[3], 0, -worldSize/4, 0, worldSize, worldSize/2, 90, Rotate.Y_AXIS);
        arrangeImageViews(cubeMapSideImageView[4], -worldSize/2, -worldSize/4, worldSize/2, worldSize, worldSize/2, 0, Rotate.Y_AXIS);
        arrangeImageViews(cubeMapSideImageView[5], -worldSize/2, -worldSize/2, 0, worldSize, worldSize, 90, Rotate.X_AXIS);
        
        group.getChildren().addAll(cubeMapSideImageView[0], cubeMapSideImageView[1], cubeMapSideImageView[2], cubeMapSideImageView[3], cubeMapSideImageView[4], cubeMapSideImageView[5]);
    }
    
    public void clearSkyBox(Group group){
        group.getChildren().removeAll(cubeMapSideImageView[0], cubeMapSideImageView[1], cubeMapSideImageView[2], cubeMapSideImageView[3], cubeMapSideImageView[4], cubeMapSideImageView[5]);
        changeSky();
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
    
    public String randomSky(){
        
        String[] skyType = {"sunny/", "cloudy/", "night/", "mountain/", "noon/", "daylight/"};
        int counter = random.nextInt(6);
        
        return skyType[counter];
      
    }
    
    public void changeSky(){
        sbImagePath = randomSky();   
        for (int sideCount = 0; sideCount < cubeMapSideImageFileName.length; sideCount++){
         
         cubeMapSideImage = new Image(sbTexturePath+sbImagePath+cubeMapSideImageFileName[sideCount]);
         cubeMapSideImageView[sideCount] = new ImageView(cubeMapSideImage);
         
     }
    }
    
}

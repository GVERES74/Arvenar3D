/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_3DPack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import pkg_Characters.Character_DataBase_NPC;
import pkg_Characters.Character_DataBase_PC;

/**
 *
 * @author Gabor Veres
 */
public class Arvenar3DMaterials { //NOT USED / USE Arvenar3DObject instead
    
    static PhongMaterial globe3DMaterial = new PhongMaterial();
    public static PhongMaterial heroAvatarMaterial = new PhongMaterial();    
    public static PhongMaterial pirateMaterial = new PhongMaterial();
    static PhongMaterial planeGroundMaterial = new PhongMaterial();
    
    public Character_DataBase_NPC dbaseNPC = new Character_DataBase_NPC();    
    public Character_DataBase_PC dbasePC = new Character_DataBase_PC();    
    public static FileInputStream pirateTextureImage, compass3DImage, heroTextureImage;
    public static Image heroImg, pirateImg;
        
    Color color = new Color(0.5,0.4,0.4,1);

public Arvenar3DMaterials() throws FileNotFoundException{    
 
    heroTextureImage = new FileInputStream(dbasePC.getRandomPC().getAvatarimg());
    pirateTextureImage = new FileInputStream(dbaseNPC.getRandomNPC().getAvatarimg());
    
    heroImg = new Image(heroTextureImage);
    pirateImg = new Image(pirateTextureImage);
    
    System.out.println("Hero texture: "+heroTextureImage+"/ Hero image: "+heroImg);
    System.out.println("Pirate texture: "+pirateTextureImage+"/ Pirate image: "+pirateImg);    
    
    compass3DImage = new FileInputStream("src/img/earth.jpg");
     
    globe3DMaterial.setDiffuseMap(new Image(compass3DImage));    
    heroAvatarMaterial.setDiffuseMap(heroImg);
    pirateMaterial.setDiffuseMap(pirateImg);
    planeGroundMaterial.setDiffuseMap(new Image(new FileInputStream("src/img/grass.jpg")));
    
    planeGroundMaterial.setSpecularColor(color);
    heroAvatarMaterial.setSpecularColor(color);
    pirateMaterial.setSpecularColor(color);    
}



}//end Class

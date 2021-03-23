/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_3DPack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import pkg_Characters.Character_DataBase_NPC;
import pkg_Characters.Character_DataBase_PC;
import pkg_Characters.NPC;
import pkg_Characters.Playable_Character;

/**
 *
 * @author GVERES
 */
public class Arvenar3DObjects {
    
    public Sphere compass3d = new Sphere();
    public Sphere pc_Hero = new Sphere();
    public Box npc_Pirate = new Box();
    public Cylinder npc_PirateBoss = new Cylinder(25,50);
    public Box world3DBox = new Box(50000,5,50000);
        
    PhongMaterial globe3DMaterial = new PhongMaterial();
    public PhongMaterial heroAvatarMaterial = new PhongMaterial();    
    public PhongMaterial pirateMaterial = new PhongMaterial();
    public PhongMaterial pirateBossMaterial = new PhongMaterial();
    static PhongMaterial planeGroundMaterial = new PhongMaterial();
    
    public Character_DataBase_NPC dbaseNPC = new Character_DataBase_NPC();    
    public Character_DataBase_PC dbasePC = new Character_DataBase_PC();

    public String playerPC, playerNPC, playerNPCBoss;
    private String imagesDirectory = "src/img/";
    private String textureWallDirectory = "src/textures/walls/";
    private String texturePlantDirectory = "src/textures/plants/";
    private String textureBumpMapDirectory = "src/textures/bumpmaps/";
    public Playable_Character pc = dbasePC.getRandomPC(); //declare the playable character
    public NPC npc = dbaseNPC.getRandomNPC(); //declare a non-playable character
    
    public FileInputStream pirateTextureImage, pirateBossTextureImage, heroTextureImage;
    public static Image heroImg, pirateImg, pirateBossImg;
        
    Color color = new Color(0.5,0.4,0.4,1);
    Random random = new Random();
    
        
public Arvenar3DObjects() throws FileNotFoundException{
    
}

public Sphere object3DHero() throws FileNotFoundException{
    pc_Hero = new Sphere();
    pc_Hero.setRadius(200.0);
    heroTextureImage = new FileInputStream(pc.getAvatarimg());
    heroImg = new Image(heroTextureImage);
    heroAvatarMaterial.setDiffuseMap(heroImg);
    heroAvatarMaterial.setSpecularColor(color);
    pc_Hero.setMaterial(heroAvatarMaterial);
    playerPC = pc.getFname();
    return pc_Hero;
}

public Box object3DPirate() throws FileNotFoundException{
    int boxSize = 250; 
    npc_Pirate = new Box();
    npc_Pirate.setDepth(boxSize);
    npc_Pirate.setWidth(boxSize);
    npc_Pirate.setHeight(boxSize);    
    
    pirateTextureImage = new FileInputStream(npc.getAvatarimg());
    pirateImg = new Image(pirateTextureImage);
    pirateMaterial.setDiffuseMap(pirateImg);
    //pirateMaterial.setBumpMap(new Image(new FileInputStream(imagesDirectory+"bump1.jpg")));
    pirateMaterial.setSpecularColor(color);
    npc_Pirate.setMaterial(pirateMaterial);
    playerNPC = npc.getFname();
    return npc_Pirate;
}

public Cylinder object3DPirateBoss() throws FileNotFoundException{
    
    pirateBossTextureImage = new FileInputStream(npc.getAvatarimg());
    pirateBossImg = new Image(pirateBossTextureImage);
    pirateBossMaterial.setDiffuseMap(pirateBossImg);
    pirateBossMaterial.setSpecularColor(color);
    npc_PirateBoss.setMaterial(pirateBossMaterial);
    playerNPCBoss = npc.getFname();
    return npc_PirateBoss;
}

public Sphere object3DCompass() throws FileNotFoundException{
    compass3d = new Sphere();
    compass3d.setRadius(50);
    globe3DMaterial.setDiffuseMap(new Image(new FileInputStream(imagesDirectory+"earth.jpg")));
    globe3DMaterial.setSpecularColor(color);
    compass3d.setMaterial(globe3DMaterial);
    return compass3d;
}



public Cylinder object3DTower(int radius, int height, int divs, int xPos, int yPos, int zPos, String diffuseMap) throws FileNotFoundException{
    
    Cylinder tower = new Cylinder(radius, height, divs);
    tower.getTransforms().addAll(new Translate(xPos, yPos, zPos));
        
    PhongMaterial texture = new PhongMaterial();
    texture.setDiffuseMap(new Image(new FileInputStream(textureWallDirectory+diffuseMap)));
//    wallPaper.setBumpMap(new Image(new FileInputStream(imagesDirectory+bumpMap)));
    //wallpaper.setSpecularColor(color);
    tower.setMaterial(texture);
        
    return tower;
}




public Box object3DWall(int width, int height, int depth, int xPos, int yPos, int zPos, String diffuseMap) throws FileNotFoundException{
    
    Box wallName = new Box(width, height, depth);
    wallName.getTransforms().addAll(new Translate(xPos, yPos, zPos));
        
    PhongMaterial wallPaper = new PhongMaterial();
    wallPaper.setDiffuseMap(new Image(new FileInputStream(textureWallDirectory+diffuseMap)));
    //wallPaper.setBumpMap(new Image(new FileInputStream(textureWallDirectory+bumpMap)));
    //wallpaper.setSpecularColor(color);
    wallName.setMaterial(wallPaper);
        
    return wallName;
}

public Sphere object3DBush(int radius, int ryAngle, int xPos, int yPos, int zPos, String diffuseMap) throws FileNotFoundException{
    
    Sphere bush = new Sphere(radius);
    bush.getTransforms().addAll(new Translate(xPos, yPos, zPos), new Rotate(ryAngle, Rotate.Y_AXIS));
        
    PhongMaterial bushTexture = new PhongMaterial();
    bushTexture.setDiffuseMap(new Image(new FileInputStream(texturePlantDirectory+diffuseMap)));
    //bushTexture.setBumpMap(new Image(new FileInputStream(texturePlantDirectory+bumpMap)));
    //wallpaper.setSpecularColor(color);
    bush.setMaterial(bushTexture);
        
    return bush;
}

} //end Class

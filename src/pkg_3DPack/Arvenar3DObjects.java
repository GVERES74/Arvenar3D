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

public Box object3DTerrain(int width, int height, int depth, int xPos, int yPos, int zPos, String diffuseMap, String bumpMap) throws FileNotFoundException{
    
    Box terrainName = new Box(width, height, depth);
    terrainName.getTransforms().addAll(new Translate(xPos, yPos, zPos));
        
    PhongMaterial groundType = new PhongMaterial();
    groundType.setDiffuseMap(new Image(new FileInputStream(imagesDirectory+diffuseMap)));
    groundType.setBumpMap(new Image(new FileInputStream(textureBumpMapDirectory+bumpMap)));
    groundType.setSpecularColor(color);
    terrainName.setMaterial(groundType);
        
    return terrainName;
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

public MeshView object3DMesh(float xWidth, float yHeight, float zDepth, int xPos, int yPos, int zPos, String diffuseMap) throws FileNotFoundException{
    
    TriangleMesh triMesh = new TriangleMesh();
    triMesh.getTexCoords().addAll(
            0.25f, 0,       //T0
            0.5f, 0,        //T1
            0, 0.25f,       //T2
            0.25f, 0.25f,   //T3
            0.5f, 0.25f,    //T4
            0.75f, 0.25f,   //T5
            1, 0.25f,       //T6
            0, 0.5f,        //T7
            0.25f, 0.5f,    //T8
            0.5f, 0.5f,     //T9
            0.75f, 0.5f,    //T10
            1, 0.5f,        //T11
            0.25f, 0.75f,   //T12
            0.5f, 0.75f     //T13
    );
        
        triMesh.getPoints().addAll(
                0, 0, zDepth,
                xWidth, 0, zDepth,
                0, yHeight, zDepth,
                xWidth, yHeight, zDepth,
                0, 0, 0,
                xWidth, 0, 0,
                0, yHeight, 0,
                xWidth, yHeight, 0
        );
        
        triMesh.getFaces().addAll(
             5,1,4,0,0,3     //P5,T1 ,P4,T0  ,P0,T3
            ,5,1,0,3,1,4    //P5,T1 ,P0,T3  ,P1,T4
            ,0,3,4,2,6,7    //P0,T3 ,P4,T2  ,P6,T7
            ,0,3,6,7,2,8    //P0,T3 ,P6,T7  ,P2,T8
            ,1,4,0,3,2,8    //P1,T4 ,P0,T3  ,P2,T8
            ,1,4,2,8,3,9    //P1,T4 ,P2,T8  ,P3,T9
            ,5,5,1,4,3,9    //P5,T5 ,P1,T4  ,P3,T9
            ,5,5,3,9,7,10   //P5,T5 ,P3,T9  ,P7,T10
            ,4,6,5,5,7,10   //P4,T6 ,P5,T5  ,P7,T10
            ,4,6,7,10,6,11  //P4,T6 ,P7,T10 ,P6,T11
            ,3,9,2,8,6,12   //P3,T9 ,P2,T8  ,P6,T12
            ,3,9,6,12,7,13  //P3,T9 ,P6,T12 ,P7,T13 
        );
        
    
        MeshView triangleMesh = new MeshView(triMesh);
        PhongMaterial groundType = new PhongMaterial();
        groundType.setDiffuseMap(new Image(new FileInputStream("src/textures/skybox/"+diffuseMap)));
        //groundType.setBumpMap(new Image(new FileInputStream(imagesDirectory+bumpMap)));
        //groundType.setSpecularColor(color);
        triangleMesh.setMaterial(groundType);
        
               
        triangleMesh.getTransforms().addAll(new Translate(xPos, yPos, zPos));

        return triangleMesh;
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

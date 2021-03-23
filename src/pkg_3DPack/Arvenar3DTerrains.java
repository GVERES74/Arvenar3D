/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_3DPack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;

/**
 *
 * @author TE332168
 */
public class Arvenar3DTerrains {
    
    Arvenar3DObjects objects3d;
    
    Arvenar3DMeshes mesh3d;
    
    String terrainImageDirectory = "src/textures/terrains/";
        
    
    
    public Arvenar3DTerrains() throws FileNotFoundException{
        
        
    }
        
    public Box object3DTerrain(int width, int height, int depth, int xPos, int yPos, int zPos, String diffuseMap) throws FileNotFoundException{
    
    objects3d = new Arvenar3DObjects();
    Box terrainName = new Box(width, height, depth);
    terrainName.getTransforms().addAll(new Translate(xPos, yPos, zPos));
        
    PhongMaterial groundType = new PhongMaterial();
    groundType.setDiffuseMap(new Image(new FileInputStream(terrainImageDirectory+diffuseMap)));
    //groundType.setBumpMap(new Image(new FileInputStream(terrainImageDirectory+bumpMap)));
    groundType.setSpecularColor(Color.GREEN);
    terrainName.setMaterial(groundType);
        
    return terrainName;
}
    
    public void buildTerrain(Group group) throws FileNotFoundException{
        
        int xPos = 0;
        int yPos = -5;
        int zPos = 0;
        int wWidth = 100000;
        int wHeight = 5;
        int wDepth = 100000;
        
               
         group.getChildren().add(object3DTerrain(wWidth, wHeight, wDepth, xPos, yPos, zPos, "grass.jpg"));
    }
    
    public void buildMeshTerrain(Group group, float xw, float yh, float zd, int xPos, int yPos, int zPos) throws FileNotFoundException{
        
        mesh3d = new Arvenar3DMeshes();
        group.getChildren().add(mesh3d.object3DTerrainMesh(xw, yh, zd, xPos, yPos, zPos));
    }
        
}

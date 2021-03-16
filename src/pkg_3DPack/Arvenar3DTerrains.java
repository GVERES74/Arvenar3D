/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_3DPack;

import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.shape.TriangleMesh;

/**
 *
 * @author TE332168
 */
public class Arvenar3DTerrains {
    
    Arvenar3DObjects objects3d;
        
    
    
    public Arvenar3DTerrains() throws FileNotFoundException{
        
        objects3d = new Arvenar3DObjects();
    }
    
    public void buildTerrain(Group group) throws FileNotFoundException{
        
        int xPos = 0;
        int yPos = -5;
        int zPos = 0;
        int wWidth = 50000;
        int wHeight = 5;
        int wDepth = 50000;
        
               
         group.getChildren().add(objects3d.object3DTerrain(wWidth, wHeight, wDepth, xPos, yPos, zPos, "grass.jpg", "bumpes.jpg"));
    }
    
    public void buildMeshTerrain(Group group, float top, float height, float width, int xPos, int zPos, String texture) throws FileNotFoundException{
        
        group.getChildren().add(objects3d.object3DMesh(top, height, width, xPos, zPos, texture));
    }
        
}

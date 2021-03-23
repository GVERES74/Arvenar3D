/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_3DPack;

import java.io.FileNotFoundException;
import java.util.Random;
import javafx.scene.Group;

/**
 *
 * @author TE332168
 */
public class Arvenar3DObstacles {
    
    Arvenar3DObjects objects3d;
    Arvenar3DMeshes mesh3d;
    Random random = new Random();
    String wallTextureFile;
    String bushTextureFile;
    
    public Arvenar3DObstacles() throws FileNotFoundException{
        
        objects3d = new Arvenar3DObjects();
    }
    
    public void buildWalls(Group group) throws FileNotFoundException{
        
        for (int i=0; i< 20;i++){
            
            int wWidth = random.nextInt(800)+300;
            int wHeight = random.nextInt(800)+300;
            int wDepth = random.nextInt(800)+300;

            int xPos = random.nextInt(20000 + 20000) -20000; //range beetwen -20.000 and 20.000
            int yPos = -wHeight/2; //buildings stand on the groud from Y=0
            int zPos = random.nextInt(20000 + 20000) -20000;
            int wallType = random.nextInt(4);
                switch (wallType){
                    case 0:  wallTextureFile = "brickwall.jpg";
                    break;
                    case 1:  wallTextureFile = "house3.jpg";
                    break;
                    case 2:  wallTextureFile = "house2.jpg";
                    break;
                    case 3:  wallTextureFile = "house.jpg";
                    break;
                }
                    
            
            
                    // W       H       D    X    Y   Z   texture(diffMap) file bumpMap
            group.getChildren().add(objects3d.object3DWall(wWidth, wHeight, wDepth, xPos, yPos, zPos, wallTextureFile));
        }
    }
    
    public void buildTowers(Group group) throws FileNotFoundException{
        
        for (int i=0; i< 20;i++){
            
            int tRadius = random.nextInt(800)+300;
            int tHeight = random.nextInt(800)+300;
            int tDivs = random.nextInt(12)+6;

            int xPos = random.nextInt(20000 + 20000) -20000; //range beetwen -20.000 and 20.000
            int yPos = -tHeight/2; //buildings stand on the groud from Y=0
            int zPos = random.nextInt(20000 + 20000) -20000;
            int textureType = random.nextInt(4);
                switch (textureType){
                    case 0:  wallTextureFile = "brickwall.jpg";
                    break;
                    case 1:  wallTextureFile = "stonewall.jpg";
                    break;
                    case 2:  wallTextureFile = "oldbrick.jpg";
                    break;
                    case 3:  wallTextureFile = "stonetile.jpg";
                    break;
                }
                    
            
            
                    // W       H       D    X    Y   Z   texture(diffMap) file bumpMap
            group.getChildren().add(objects3d.object3DTower(tRadius, tHeight, tDivs, xPos, yPos, zPos, wallTextureFile));
        }
    }
    
        
    public void buildMeshCube(Group group) throws FileNotFoundException{
        
            mesh3d = new Arvenar3DMeshes();        
            int cWidth = 5000;
            int cHeight = 5000;
            int cDepth = 8000;

                                                                        
            group.getChildren().add(mesh3d.object3DCubeMesh(cWidth, cHeight, cDepth, 2000, -cHeight, -2000, "src/textures/walls/house.jpg"));
    }
    
    
    public void buildBush(Group group) throws FileNotFoundException{
        
        for (int i=0; i< 50;i++){
            
            int radius = random.nextInt(100)+50;
            
            int xPos = random.nextInt(20000 + 20000) -20000; //range beetwen -20.000 and 20.000
            int yPos = -radius; //bush stand on the groud from Y=0
            int zPos = random.nextInt(20000 + 20000) -20000;
            int ryAngle = random.nextInt(360)
                    ;
            int bushType = random.nextInt(3);
                switch (bushType){
                    case 0:  bushTextureFile = "bush1.jpg";
                    break;
                    case 1:  bushTextureFile = "bush2.jpg";
                    break;
                    case 2:  bushTextureFile = "bush3.jpg";
                    break;
                    
                }
                    
            
            group.getChildren().add(objects3d.object3DBush(radius, ryAngle, xPos, yPos, zPos, bushTextureFile));
        }
    }
    
    
}

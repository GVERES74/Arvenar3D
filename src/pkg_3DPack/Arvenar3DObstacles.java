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
    Random random = new Random();
    
    public Arvenar3DObstacles() throws FileNotFoundException{
        
        objects3d = new Arvenar3DObjects();
    }
    
    public void buildWalls(Group group) throws FileNotFoundException{
        
        for (int i=0; i< 50;i++){
            
            int wWidth = random.nextInt(800)+300;
            int wHeight = random.nextInt(800)+300;
            int wDepth = random.nextInt(800)+300;

            int xPos = random.nextInt(20000 + 20000) -20000; //range beetwen -20.000 and 20.000
            int yPos = -wHeight/2; //buildings stand on the groud from Y=0
            int zPos = random.nextInt(20000 + 20000) -20000;
                                                            // W       H       D    X    Y   Z   texture(diffMap) file bumpMap
            group.getChildren().add(objects3d.object3DWall(wWidth, wHeight, wDepth, xPos, yPos, zPos, "brickwall.jpg", "bump_brick.jpg"));
        }
    }
}

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
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Translate;

/**
 *
 * @author TE332168
 */
public class Arvenar3DMeshes {

    public Arvenar3DMeshes() {
    }
    
   public MeshView object3DCubeMesh(float xWidth, float yHeight, float zDepth, int xPos, int yPos, int zPos, String diffuseMap) throws FileNotFoundException{
    
    TriangleMesh cubeMesh = new TriangleMesh();
    cubeMesh.getTexCoords().addAll(
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
        
        cubeMesh.getPoints().addAll(
                0, 0, zDepth,
                xWidth, 0, zDepth,
                0, yHeight, zDepth,
                xWidth, yHeight, zDepth,
                0, 0, 0,
                xWidth, 0, 0,
                0, yHeight, 0,
                xWidth, yHeight, 0
        );
        
        cubeMesh.getFaces().addAll(
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
        
    
        MeshView cubeMeshView = new MeshView(cubeMesh);
        PhongMaterial textureImg = new PhongMaterial();
        
        textureImg.setDiffuseMap(new Image(new FileInputStream(diffuseMap)));
        //groundType.setBumpMap(new Image(new FileInputStream(imagesDirectory+bumpMap)));
        //groundType.setSpecularColor(color);
        cubeMeshView.setMaterial(textureImg);
        cubeMeshView.setCullFace(CullFace.NONE); //cannot look into the mesh from the outside!!
                       
        cubeMeshView.getTransforms().addAll(new Translate(xPos, yPos, zPos));
        
        return cubeMeshView;
}
 
   public MeshView object3DTerrainMesh(float xWidth, float yHeight, float zDepth, int xPos, int yPos, int zPos) throws FileNotFoundException{
    
      yHeight = new Random().nextFloat()*50+20;
              
    TriangleMesh terrainMesh = new TriangleMesh();
    terrainMesh.getTexCoords().addAll(
            0, 0,       //T0
            1, 0,       //T1
            0, 1,       //T2
            1, 1        //T3
            
            
            
    );
        
        terrainMesh.getPoints().addAll(
                0,    0,    0,            // Point 0 - Top
                1000,    100,   0,         // Point 1 - Front
                0, 0,    1000,            // Point 2 - Left
                1000,  100,    1000            // Point 3 - Back
                
                                
        );
        
        terrainMesh.getFaces().addAll(
            
            0,0,  0,1,  0,2,          // Bottom rear face
            0,1,  0,2,  0,3           // Bottom front face
        );
        
    
        MeshView terrainMeshView = new MeshView(terrainMesh);
        PhongMaterial textureImg = new PhongMaterial();
        
        textureImg.setDiffuseMap(new Image(new FileInputStream("src/textures/terrains/grass.jpg")));
        
        terrainMeshView.setMaterial(textureImg);
                       
        terrainMeshView.getTransforms().addAll(new Translate(xPos, yPos, zPos));
        
        return terrainMeshView;
}
    
}

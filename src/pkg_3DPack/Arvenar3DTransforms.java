/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_3DPack;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

/**
 *
 * @author TE332168
 */
public class Arvenar3DTransforms {
    
    Rotate r;
    Transform t = new Rotate();
    
    Rotate cameraRotate;
    Transform cameraTransform = new Rotate();
    
   public Arvenar3DTransforms(){
       
   }
    
    public void rotateByXY(Node node, int angle, Point3D axis) {
      r = new Rotate(angle, axis);
      t = t.createConcatenation(r);
                  
      node.getTransforms().clear();
      node.getTransforms().addAll(t);
           
    }
 
        
    public void rotatePCamera(Node node, Node pivotnode, int angle, Point3D axis) {
      
      cameraRotate = new Rotate(angle, axis);
      cameraTransform = cameraTransform.createConcatenation(cameraRotate);
      
      cameraRotate.setPivotX(pivotnode.getTranslateX());
      cameraRotate.setPivotY(pivotnode.getTranslateY());
            
      node.getTransforms().clear();
      node.getTransforms().addAll(cameraTransform);
        
        
    }
    
   
    public void move3DNodeZ(Node node, double xCoords, double zSpeed){
        
        Translate translate = new Translate(xCoords, 0, zSpeed);
                        
           
        node.getTransforms().addAll(translate);
        translate.setZ(translate.getZ()+zSpeed);
        translate.setX(translate.getX()+xCoords);
           
    }
}
        

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Characters;

/**
 *
 * @author VERESG
 */
public abstract class NPC_Monster extends NPC{
  
  public NPC_Monster(){
    
}
   
    public String getFname() {
        return fname;
    }
   
   public String getCast() {
        return cast;
    }

  
   public int getHealth_point() {
        return health_point;
    }

      
    @Override
    public void attacksOpponent(Character opponent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

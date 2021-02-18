/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Maps;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TE332168
 */
public class Map_DataBase {
    
   public List<Arvenar_Maps> maplist = new ArrayList<>();
   Create_Maps levels = new Create_Maps();
    
    public Map_DataBase(){
        
        maplist.add(levels.forest);
        maplist.add(levels.city);
        maplist.add(levels.fort);
        maplist.add(levels.desert);
        maplist.add(levels.village);
        maplist.add(levels.mines);
        maplist.add(levels.harbor);
        maplist.add(levels.countryside);
    }

    public Arvenar_Maps getMap(int i) {
        
        return maplist.get(i);
    }
    
    public Arvenar_Maps get_Random_Map(){
            int i = (int) ((Math.random()*maplist.size()));
            
            
            return maplist.get(i);
            
        }
}

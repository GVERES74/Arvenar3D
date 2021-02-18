/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Maps;

import java.util.ArrayList;
import java.util.List;
import pkg_Characters.NPC;
import pkg_Characters.Summon_Characters;

/**
 *
 * @author TE332168
 */
public class Arvenar_Maps {

    String map_name;
    String map_story;
    String map_desc;
    String map_inhabitants;
    String map_image_path;
    
    
                
   public Arvenar_Maps(String map_name, String map_desc, String map_inhabitants, String map_image_path, String map_story){
     
    this.map_name = map_name;
    this.map_story = map_story;
    this.map_desc = map_desc;
    this.map_inhabitants = map_inhabitants;
    this.map_image_path = map_image_path;
    
 } 

    public String getMap_name() {
        return map_name;
    }
 

    public String getMap_desc() {
        return map_desc;
    }


    public String getMap_image_path() {
        return map_image_path;
    }
    

    public String getMap_story() {
        return map_story;
    }

    public String getMap_inhabitants() {
        return map_inhabitants;
    }

    
            
}

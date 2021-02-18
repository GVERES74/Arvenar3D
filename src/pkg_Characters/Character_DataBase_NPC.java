/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TE332168
 */
public class Character_DataBase_NPC {
    
    int i = 0;
    public List<String> npc_img = new ArrayList<>();
    public List<String> npc_name = new ArrayList<>();
    public List<String> npc_bio = new ArrayList<>();
    public List<String> npc_stats_race = new ArrayList<>();
    public List<String> npc_stats_cast = new ArrayList<>();
    public List<String> npc_stats_weapon = new ArrayList<>();
    public List<String> npc_stats_shout = new ArrayList<>();
    public List<Integer> npc_stats_maxhealth = new ArrayList<>();
    public List<NPC> npc_character = new ArrayList<>();
    
    public List<NPC> npc_for_m1_forest = new ArrayList<>();
    public List<NPC> npc_for_m2_elven_city = new ArrayList<>();
    public List<NPC> npc_for_m3_fort = new ArrayList<>();
    public List<NPC> npc_for_m4_desert = new ArrayList<>();
    public List<NPC> npc_for_m5_snowy_village = new ArrayList<>();
    public List<NPC> npc_for_m6_dwarven_mines = new ArrayList<>();
    public List<NPC> npc_for_m7_harbor = new ArrayList<>();
    
    
    Summon_Characters clone = new Summon_Characters();
    
        
    public Character_DataBase_NPC(){
        
                        
        npc_character.add(clone.bbear1);
        npc_character.add(clone.bbear2);
        npc_character.add(clone.wolf1);
        npc_character.add(clone.wolf2);
        npc_character.add(clone.ogre1);
        npc_character.add(clone.dragon1);
        npc_character.add(clone.outlaw);
        npc_character.add(clone.merlin);
        npc_character.add(clone.human_trader);
        npc_character.add(clone.croco1);
        npc_character.add(clone.eagle1);
        npc_character.add(clone.snake1);
        npc_character.add(clone.boar1);
        
        npc_img.add(clone.bbear1.getAvatarimg());
        npc_img.add(clone.bbear2.getAvatarimg());
        npc_img.add(clone.wolf1.getAvatarimg());
        npc_img.add(clone.wolf2.getAvatarimg());
        npc_img.add(clone.ogre1.getAvatarimg());
        npc_img.add(clone.dragon1.getAvatarimg());
        npc_img.add(clone.outlaw.getAvatarimg());
        npc_img.add(clone.merlin.getAvatarimg());
        npc_img.add(clone.human_trader.getAvatarimg());
        npc_img.add(clone.croco1.getAvatarimg());
        npc_img.add(clone.eagle1.getAvatarimg());
        npc_img.add(clone.snake1.getAvatarimg());
        npc_img.add(clone.boar1.getAvatarimg());
        
        
                
        npc_name.add(clone.bbear1.getFname());
        npc_name.add(clone.bbear2.getFname());
        npc_name.add(clone.wolf1.getFname());
        npc_name.add(clone.wolf2.getFname());
        npc_name.add(clone.ogre1.getFname());
        npc_name.add(clone.dragon1.getFname());
        npc_name.add(clone.outlaw.getFname());
        npc_name.add(clone.merlin.getFname());
        npc_name.add(clone.human_trader.getFname());
        npc_name.add(clone.croco1.getFname());
        npc_name.add(clone.eagle1.getFname());
        npc_name.add(clone.snake1.getFname());
        npc_name.add(clone.boar1.getFname());
        
                               
        npc_bio.add(clone.bbear1.getBiography());
        npc_bio.add(clone.bbear2.getBiography());
        npc_bio.add(clone.wolf1.getBiography());
        npc_bio.add(clone.wolf2.getBiography());
        npc_bio.add(clone.ogre1.getBiography());
        npc_bio.add(clone.dragon1.getBiography());
        npc_bio.add(clone.outlaw.getBiography());
        npc_bio.add(clone.merlin.getBiography());
        npc_bio.add(clone.human_trader.getBiography());
        npc_bio.add(clone.croco1.getBiography());
        npc_bio.add(clone.eagle1.getBiography());
        npc_bio.add(clone.snake1.getBiography());
        npc_bio.add(clone.boar1.getBiography());
        
        
        
        npc_stats_race.add(clone.bbear1.getRace());
        npc_stats_race.add(clone.bbear2.getRace());
        npc_stats_race.add(clone.wolf1.getRace());
        npc_stats_race.add(clone.wolf2.getRace());
        npc_stats_race.add(clone.ogre1.getRace());
        npc_stats_race.add(clone.dragon1.getRace());
        npc_stats_race.add(clone.outlaw.getRace());
        npc_stats_race.add(clone.merlin.getRace());
        npc_stats_race.add(clone.human_trader.getRace());
        npc_stats_race.add(clone.croco1.getRace());
        npc_stats_race.add(clone.eagle1.getRace());
        npc_stats_race.add(clone.snake1.getRace());
        npc_stats_race.add(clone.boar1.getRace());
        
       
        npc_stats_cast.add(clone.bbear1.getCast());
        npc_stats_cast.add(clone.bbear2.getCast());
        npc_stats_cast.add(clone.wolf1.getCast());
        npc_stats_cast.add(clone.wolf2.getCast());
        npc_stats_cast.add(clone.ogre1.getCast());
        npc_stats_cast.add(clone.dragon1.getCast());
        npc_stats_cast.add(clone.outlaw.getCast());
        npc_stats_cast.add(clone.merlin.getCast());
        npc_stats_cast.add(clone.human_trader.getCast());
        npc_stats_cast.add(clone.croco1.getCast());
        npc_stats_cast.add(clone.eagle1.getCast());
        npc_stats_cast.add(clone.snake1.getCast());
        npc_stats_cast.add(clone.boar1.getCast());
       
      
        npc_stats_maxhealth.add(clone.bbear1.getHealth_point());
        npc_stats_maxhealth.add(clone.bbear2.getHealth_point());
        npc_stats_maxhealth.add(clone.wolf1.getHealth_point());
        npc_stats_maxhealth.add(clone.wolf2.getHealth_point());
        npc_stats_maxhealth.add(clone.ogre1.getHealth_point());
        npc_stats_maxhealth.add(clone.dragon1.getHealth_point());
        npc_stats_maxhealth.add(clone.outlaw.getHealth_point());
        npc_stats_maxhealth.add(clone.merlin.getHealth_point());
        npc_stats_maxhealth.add(clone.human_trader.getHealth_point());
        npc_stats_maxhealth.add(clone.croco1.getHealth_point());
        npc_stats_maxhealth.add(clone.eagle1.getHealth_point());
        npc_stats_maxhealth.add(clone.snake1.getHealth_point());
        npc_stats_maxhealth.add(clone.boar1.getHealth_point());
        
               
        npc_stats_weapon.add(clone.bbear1.getWeapon());
        npc_stats_weapon.add(clone.bbear2.getWeapon());             
        npc_stats_weapon.add(clone.wolf1.getWeapon());             
        npc_stats_weapon.add(clone.wolf2.getWeapon());             
        npc_stats_weapon.add(clone.ogre1.getWeapon());             
        npc_stats_weapon.add(clone.dragon1.getWeapon());             
        npc_stats_weapon.add(clone.outlaw.getWeapon());             
        npc_stats_weapon.add(clone.merlin.getWeapon());
        npc_stats_weapon.add(clone.human_trader.getWeapon());
        npc_stats_weapon.add(clone.croco1.getWeapon());
        npc_stats_weapon.add(clone.eagle1.getWeapon());             
        npc_stats_weapon.add(clone.snake1.getWeapon());             
        npc_stats_weapon.add(clone.boar1.getWeapon());             
        
       
        npc_stats_shout.add(clone.bbear1.getShout());
        npc_stats_shout.add(clone.bbear2.getShout());
        npc_stats_shout.add(clone.wolf1.getShout());
        npc_stats_shout.add(clone.wolf2.getShout());
        npc_stats_shout.add(clone.ogre1.getShout());
        npc_stats_shout.add(clone.dragon1.getShout());
        npc_stats_shout.add(clone.outlaw.getShout());
        npc_stats_shout.add(clone.merlin.getShout());
        npc_stats_shout.add(clone.human_trader.getShout());
        npc_stats_shout.add(clone.croco1.getShout());
        npc_stats_shout.add(clone.eagle1.getShout());
        npc_stats_shout.add(clone.snake1.getShout());
        npc_stats_shout.add(clone.boar1.getShout());
        
        
    }
    
        public String getRandomNPCName(){
            
            i = (int) ((Math.random()*13));
            
            return npc_name.get(i);
        }
        
        public FileInputStream getNPCImage() throws FileNotFoundException{
            
            i = (int) ((Math.random()*13));
                        
            return new FileInputStream(npc_img.get(i));
            
            
        }
        
        public NPC getRandomNPC(){
            i = (int) ((Math.random()*13));
                        
            return npc_character.get(i);
            
        }
    
        public NPC getNPC(int i){
            
            return npc_character.get(i);
        }
        
        
}

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
public class Character_DataBase_PC {
    
    public List<String> players_img = new ArrayList<>();
    public List<String> players_name = new ArrayList<>();
    public List<String> players_bio = new ArrayList<>();
    public List<String> players_stats_gender = new ArrayList<>();
    public List<Integer> players_stats_age = new ArrayList<>();
    public List<String> players_stats_cast = new ArrayList<>();
    public List<Integer> players_stats_health = new ArrayList<>();
    public List<Integer> players_stats_skill = new ArrayList<>();
    public List<Integer> players_stats_xp = new ArrayList<>();
    public List<Integer> players_stats_defence = new ArrayList<>();
    public List<Playable_Character> player_character = new ArrayList<>();
    
    Summon_Characters clone = new Summon_Characters();
        
    public Character_DataBase_PC(){
                    
        player_character.add(clone.human_warrior);
        player_character.add(clone.human_mage);
        player_character.add(clone.elf_rogue);
        player_character.add(clone.dwarf_smith);
        
        players_img.add(clone.human_warrior.getAvatarImg());
        players_img.add(clone.human_mage.getAvatarImg());
        players_img.add(clone.elf_rogue.getAvatarImg());
        players_img.add(clone.dwarf_smith.getAvatarImg());
        
                
        players_name.add(clone.human_warrior.getFname());
        players_name.add(clone.human_mage.getFname());
        players_name.add(clone.elf_rogue.getFname());
        players_name.add(clone.dwarf_smith.getFname());
        
                       
        players_bio.add(clone.human_warrior.getBiography());
        players_bio.add(clone.human_mage.getBiography());
        players_bio.add(clone.elf_rogue.getBiography());
        players_bio.add(clone.dwarf_smith.getBiography());
        
                
        players_stats_gender.add(clone.human_warrior.getGender());
        players_stats_gender.add(clone.human_mage.getGender());
        players_stats_gender.add(clone.elf_rogue.getGender());
        players_stats_gender.add(clone.dwarf_smith.getGender());
        
                
        players_stats_age.add(clone.human_warrior.getAge());
        players_stats_age.add(clone.human_mage.getAge());
        players_stats_age.add(clone.elf_rogue.getAge());
        players_stats_age.add(clone.dwarf_smith.getAge());
        
               
        players_stats_cast.add(clone.human_warrior.getCast());
        players_stats_cast.add(clone.human_mage.getCast());
        players_stats_cast.add(clone.elf_rogue.getCast());
        players_stats_cast.add(clone.dwarf_smith.getCast());
        
        players_stats_health.add(clone.human_warrior.getHealth_point());
        players_stats_health.add(clone.human_mage.getHealth_point());
        players_stats_health.add(clone.elf_rogue.getHealth_point());
        players_stats_health.add(clone.dwarf_smith.getHealth_point());
        
        players_stats_skill.add(clone.human_warrior.getSkill_point());
        players_stats_skill.add(clone.human_mage.getSkill_point());
        players_stats_skill.add(clone.elf_rogue.getSkill_point());
        players_stats_skill.add(clone.dwarf_smith.getSkill_point());
        
        players_stats_xp.add(clone.human_warrior.getExperience_point());
        players_stats_xp.add(clone.human_mage.getExperience_point());
        players_stats_xp.add(clone.elf_rogue.getExperience_point());
        players_stats_xp.add(clone.dwarf_smith.getExperience_point());
        
        players_stats_defence.add(clone.human_warrior.getDefend_point());
        players_stats_defence.add(clone.human_mage.getDefend_point());
        players_stats_defence.add(clone.elf_rogue.getDefend_point());
        players_stats_defence.add(clone.dwarf_smith.getDefend_point());
        
    }
    
    public Playable_Character getPcCharacter(int i){
        //FIGYELEM! Az ArvenarSetPC getPlayableCharacter() 11x hivta meg itt a "dataBase()" eljárást, ezért 4 helyett 44 lett az adatbázis mérete!! dataBase() eljárás törölve.  
        return player_character.get(i);
    }
    
            
        public Playable_Character getRandomPC(){
            int i = (int) ((Math.random()*4));
                        
            return player_character.get(i);
            
    }
        
        public FileInputStream getPCImage() throws FileNotFoundException{
            
            int i = (int) ((Math.random()*4));
                        
            return new FileInputStream(players_img.get(i));
                        
        }
    
   }

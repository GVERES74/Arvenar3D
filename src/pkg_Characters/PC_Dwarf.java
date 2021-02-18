/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Characters;

import pkg_Items.potions.*;
import pkg_Items.weapons.*;

/**
 *
 * @author VERESG
 */

public class PC_Dwarf extends Playable_Character {

    
  public PC_Dwarf(String gender, String fname, int age, String cast, Weapons weapon, Potions potion,int skill_point, int experience_point, String avatarimg, String biography){
  
  this.gender = gender; 
  this.race = "Dwarf";
  this.fname = fname;
  this.age = age;
  this.cast = cast;
  this.level = 1;
  this.fav_weapon = weapon;
  this.inv_potion = potion;
  this.health_point = 100;
  this.skill_point = skill_point;
  this.experience_point = experience_point;
  this.current_weapon_damage = fav_weapon.weapon_getDamage();
  this.defend_point = fav_weapon.getMin_damage();
  this.money = 100;
  this.avatarimg = avatarimg;
  this.biography = biography;
    }       

    public String getRace() {
        return race;
    }
    
    public String getGender() {
        return gender;
    }

    public String getFname() {
        return fname;
    }

    public int getAge() {
        return age;
    }
   
    public String getCast() {
        return cast;
    }

    /*public String getFav_weapon() {
        return fav_weapon;
    }*/

    public int getHealth_point() {
        return health_point;
    }

    public int getSkill_point() {
        return skill_point;
    }

   public int getExperience_point() {
        return experience_point;
    }

    public int getDefend_point() {
        return defend_point;
    }
    
    public String getAvatarImg() {
        return avatarimg;
    }

    public String getBiography() {
        return biography;
    }
    
       @Override
    public void attacksOpponent(Character opponent) {
               
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    
    }
  
  
 
    
   
    



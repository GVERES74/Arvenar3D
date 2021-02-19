/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Characters;

import pkg_Items.weapons.Weapons;

/**
 *
 * @author VERESG
 */
public class NPC_Animal_Bear extends NPC_Animal{
    
    public NPC_Animal_Bear(String fname, String cast, Weapons weapon, String shout, int max_health, String avatarimg, String biography){
        
  this.fname = fname;
  this.race = "Animal";
  this.cast = cast;
  this.fav_weapon = weapon;
  this.shout = shout;
  this.health_point = max_health;
  this.max_health = max_health;
  this.current_weapon_damage = fav_weapon.weapon_getDamage();    
  this.defend_point = fav_weapon.getMin_damage();
  this.avatarimg = avatarimg;
  this.biography = biography;
}
   
    public String getFname() {
        return fname;
    }
   
   public String getCast() {
        return cast;
    }
   
   public String getRace(){
        return race;
   }

   public String getWeapon(){
       return fav_weapon.getDescription();
   }
   
   public String getShout(){
       return shout;
   }
   
   
   public int getHealth_point() {
        return health_point;
    }

   public String getAvatarimg(){
        return avatarimg;
   }
   
   public String getBiography(){
       return biography;
   }

   /*@Override
   public String getFav_weapon() {
        return fav_weapon;
    }*/
   
    @Override
    public void attacksOpponent(Character opponent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

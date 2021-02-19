/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Characters;

import pkg_Items.Item.*;
import pkg_Items.weapons.Weapons;

/**
 *
 * @author VERESG
 */
public class NPC_Human_Trader extends NPC_Human{
    
    public NPC_Human_Trader(String fname, String cast, Weapons weapon, int hasmoney, String avatarimg, String biography){
    
  this.fname = fname;
  this.race = "Human";
  this.cast = cast;
  this.hasmoney = hasmoney;
  this.fav_weapon = weapon;
  this.shout = "trading";  
  this.health_point = 100;
  this.max_health = 100;
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

   public String getWeapon() {
        return fav_weapon.getDescription();
    }
   
   
   public String getRace() {
        return race;
    }

   public String getShout() {
        return shout;
    }

   public int getHealth_point() {
        return health_point;
    }


    public String getAvatarimg() {
        return avatarimg;
    }	

    
    public String getBiography() {
        return biography;
    }


      
   public void trade_with_Character(Playable_Character client, Weapons weapon){
       client.money += weapon.getSellValue(); client.inventory.remove(weapon);
       hasmoney -= weapon.getSellValue(); inventory.add(weapon);
       System.out.println(client.inventory.isEmpty() ? client.getFname()+"'s inventory is empty. " : client.getFname()+"'s inventory now: "+client.inventory); 
       System.out.println(inventory.isEmpty() ? "Trader has no item in inventory.." : "Trader "+fname+"'s inventory now: "+inventory); 
       System.out.format("%s sold %s to %s for %d gold.%n",client.getFname(),client.fav_weapon.getDescription(),fname,weapon.getSellValue());
       System.out.format("%s has now %d gold, and %s has now %d gold.%n",fname,hasmoney,client.getFname(),client.money);
   }
   
       @Override
    public void attacksOpponent(Character opponent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      
    
}

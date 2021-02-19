/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Characters;

import java.util.ArrayList;
import pkg_Items.potions.Potions;
import pkg_Items.weapons.Weapons;

/**
 *
 * @author VERESG
 */
public abstract class NPC implements Character{
  
  String gender; 
  String race;
  String fname;
  int age;
  String cast;
  Weapons fav_weapon;
  Potions inv_potion;
  String shout = "attacking";
  int health_point;
  int max_health = 100; //differs from playable character's
  int hit_point; //differs from playable character's
  int level;
  int skill_point;
  int experience_point;
  int defend_point;
  int current_weapon_damage; //ha közvetlenül hívod meg a fav_weapon.weapon_getDamage() függvényt, akkor minden híváskor random újragenerálja az értéket
  int money;
  int hasmoney;
  ArrayList<Object> inventory = new ArrayList<Object>();
  String biography;
  String avatarimg = "";
    
  
  
  public void addItemToInventory (Object items){
      inventory.add(items);
  }
  
  public String getNpcDesc(){
      
      return (fname == null ? "Nameless": "Name: "+fname+"\n")+
      (race == null ? "Raceless": "Type: "+race+"\n")+
      (cast == null ? "Outlaw": "Cast: "+cast+"\n")+
      (fav_weapon.weapon_description == null ? "Fist": cast+" is "+fav_weapon.weapon_say+" with its "+fav_weapon.getDescription()+"."+"\n")+
      (health_point == 0 ? "Dead": "HP: "+health_point+"\n")+
      (defend_point == 0 ? "Defenseless": "Defend: "+defend_point+"\n")+
      (fav_weapon.getMin_damage() == 0 ? "Can't hit": "Damage: "+fav_weapon.getMin_damage()+" - "+fav_weapon.getMax_damage()+"\n")+
      (fav_weapon.getMin_damage() == 0 ? "Can't hit": "Hits by "+current_weapon_damage+" hit points."+"\n");
      
  }
  
  //Aktív cselekmény: Ellenfél megtámadása
  public void attacksOpponent (Playable_Character opponent){
      
        System.out.format("%s %s %s with its %s..%n",fname,shout,opponent.getFname(),fav_weapon.getDescription());
        System.out.println("Attack: "+current_weapon_damage+" vs. defend: "+opponent.getDefend_point());
        if (opponent.getDefend_point() > current_weapon_damage){
            System.out.format("Defend power: %d vs. attack power: %d. %s defended the attack. %n",opponent.getDefend_point(),current_weapon_damage,opponent.getFname());
        }
        else {
        opponent.decHealth(current_weapon_damage);
        }
        if (opponent.health_point == 0){
            System.out.format("%s is dead. %n",opponent.getFname());
        }
        else{
        incHealth(1);
        System.out.format("Attacker %s HP now: %d and defender %s HP now: %d..%n",fname,health_point,opponent.getFname(),opponent.health_point);
  }
  }
  
  public int decHealth(int damage){
      System.out.println(fname+" lost -"+damage+" health points.. ");
      health_point -= damage;
      return health_point;
    }

  public int incHealth(int health){
      if (health_point == max_health){
          health_point = max_health;
      }
      else{
          System.out.println(fname+" gained +"+health+" health points.. ");
      this.health_point += health;
      return health_point;
    }
  return health_point;
  }
            
         
    
    //----------------Setters------------------------

    public void setFname(String fname) {
        this.fname = fname;
    }

   
    public void setCast(String cast) {
        this.cast = cast;
    }

  
    public void setHealth_point(int health_point) {
        this.health_point = health_point;
    }

    
    public void setHit_point(int hit_point) {
        this.hit_point = hit_point;
    }

    //---------------------Getterek------------------------
    public String getFname() {
        return fname;
    }
    
    public String getCast() {
        return cast;
    }

    public String getRace() {
        return race;
    }

    public int getHealth_point() {
        return health_point;
    }
   
    int getDefend_point() {
        return defend_point;
    }
    
    public String getBiography(){
        return biography;
    }
    
    public String getAvatarimg(){
        return avatarimg;
    }

    public String getFav_weapon() {
        return fav_weapon.weapon_description;
    }

    public String getShout() {
        return shout;
    }

    
}

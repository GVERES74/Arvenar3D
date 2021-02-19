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
public abstract class Playable_Character implements Character{
  
  String gender; 
  String race;
  String fname;
  int age;
  String cast;
  Weapons fav_weapon;
  Potions inv_potion;
  String shout = "attacking";
  int health_point;
  int level;
  int skill_point;
  int experience_point;
  int defend_point;
  int current_weapon_damage; //ha közvetlenül hívod meg a fav_weapon.weapon_getDamage() függvényt, akkor minden híváskor random újragenerálja az értéket
  int money;
  ArrayList<Object> inventory = new ArrayList<Object>();
  String biography;
  String avatarimg = "";
    
  
  public String getPcDesc(){ //get full description of playable character
                      
      return (fname == null ? "Nameless": "I'm "+fname+", my race is "+race+"!"+"\n")+ //a little bit ugly using like this, but does the trick
        (gender == null ? "Genderless": "I'm a "+gender+"."+"\n")+
        (age == 0 ? "I wasn't born yet": "My age is "+age+"."+"\n")+
        (cast == null ? "Outlaw": "I was trained to be a "+cast+"."+"\n")+
        (level == 0 ? "No level": "Level: "+level+"\n")+
        (fav_weapon == null ? "Milk bottle": "I'm "+fav_weapon.weapon_say+" my enemy with my "+fav_weapon.getDescription()+"."+"\n")+
        (health_point == 0 ? "I'm dead": "HP: "+health_point+"\n")+
        (skill_point == 0 ? "Greenhorn": "SP: "+skill_point+"\n")+
        (experience_point == 0 ? "Farmer": "XP: "+experience_point+"\n")+
        (defend_point == 0 ? "Defenseless": "Defend: "+defend_point+"\n")+
        (money == 0 ? "Beggar": "Money: "+money+"\n")+
        
        ("Current inventory: \t\n")+
       
        (fav_weapon.weapon_description == null ? "No weapon": "Weapon name: "+fav_weapon.getWeapon_name()+"\n")+
        (fav_weapon.weapon_description == null ? "No weapon": "Weapon description: "+fav_weapon.getDescription()+"\n")+
        (fav_weapon.weapon_description == null ? "No weapon": "Weapon class: "+fav_weapon.getWeapon_class()+"\n")+
        (fav_weapon.getMin_damage() == 0 ? "Can't hit": "Damage: "+fav_weapon.getMin_damage()+" - "+fav_weapon.getMax_damage()+"\n")+
        (fav_weapon.getMin_damage() == 0 ? "Can't hit": "Current damage: "+current_weapon_damage+" hit points."+"\n")+
        (fav_weapon.getBuyValue() == 0 ? "Can't be bought\n": "Buy value: "+fav_weapon.getBuyValue()+"\n")+
        (fav_weapon.getSellValue() == 0 ? "Can't be sold\n": "Sell value: "+fav_weapon.getSellValue()+"\n")+
        
        /*for (int i=0; i < inventory.size(); i++){
        ("Current inventory:"+"\n"+"\t"+inventory.get(i))+
            
         }*/
        
        (inv_potion.getPotion_name() == null ? "No potion": "Potion name: "+inv_potion.getPotion_name()+"\n")+
        (inv_potion.getPotion_class() == null ? "No potion": "Potion class: "+inv_potion.getPotion_class()+"\n")+
        (inv_potion.getPotion_description() == null ? "No potion": "Potion description: "+inv_potion.getPotion_description()+"\n")+
        (inv_potion.getPotion_healvalue() == 0 ? "No effect": "Potion healvalue: "+inv_potion.getPotion_healvalue()+"\n")+
        (inv_potion.getPotion_buy_value() == 0 ? "Can't be bought": "Potion buyvalue: "+inv_potion.getPotion_buy_value()+"\n")+
        (inv_potion.getPotion_sell_value() == 0 ? "Can't be sold": "Potion sellvalue: "+inv_potion.getPotion_sell_value()+"\n");
  }
  
  public void addItemToInventory (Object items){
      inventory.add(items);
  }
  
  //Aktív cselekmény: Ellenfél megtámadása
  public void attacksOpponent (NPC opponent){
      
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
        incSkill(1);
        
        
  }
        
  }
  
   
  public int decHealth(int damage){
      System.out.println(fname+" lost -"+damage+" health points.. ");
      health_point -= damage;
      return health_point;
    }

  public int incHealth(int health){
      if (health_point == 100){
          health_point = 100;
          }
      else {
      System.out.println(fname+" gained +"+health+" health points.. ");
      this.health_point += health;
      return health_point;
    }
      return health_point;
  }
  
  public int incSkill(int skill){
      this.skill_point += skill;
      System.out.println(fname+" gained +"+skill+" skill points. Total SP: "+skill_point);
      return skill_point;
    }
  
  public int incExperience(int xp){
      this.experience_point += xp;
      return experience_point;
  }
     
  //--------Setters--------------------  
  
  public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
 
    public void setHealth_point(int health_point) {
        this.health_point = health_point;
    }

    public void setSkill_point(int skill_point) {
        this.skill_point = skill_point;
    }

    public void setExperience_point(int experience_point) {
        this.experience_point = experience_point;
    }

    //--------Getters--------------------
    
    public String getFname() {
        return fname;
    }

    public int getHealth_point() {
        return health_point;
    }
    
    public int getDefend_point() {
        return defend_point;
    }
    
    public String getBiography() {
        return biography;
    }

    public String getAvatarimg(){
        return avatarimg;
    }
    
    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public int getAge() {
        return age;
    }

    public String getCast() {
        return cast;
    }

    public int getExperience_point() {
        return experience_point;
    }

    public String getShout() {
        return shout;
    }

    public int getSkill_point() {
        return skill_point;
    }
    
    
   }


   
    
    

    
    

  

    

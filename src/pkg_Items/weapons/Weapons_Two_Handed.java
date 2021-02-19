/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Items.weapons;

/**
 *
 * @author VERESG
 */
public class Weapons_Two_Handed extends Weapons{
   
     public Weapons_Two_Handed(String weapon_name, String weapon_desc, int min_damage, int max_damage, int weapon_value, String weapon_say ){
    
    this.weapon_class = "Two handed";
    this.weapon_name = weapon_name;
    this.weapon_description = weapon_desc;
    this.min_damage = min_damage;
    this.max_damage = max_damage;
    this.weapon_buy_value = weapon_value;
    this.weapon_sell_value = weapon_value/2;
    this.weapon_say = weapon_say;
}
    
}

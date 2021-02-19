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
public class Weapons_Animal extends Weapons{
    
    public Weapons_Animal(String weapon_description, int min_damage, int max_damage, String weapon_say){
    
    this.weapon_description = weapon_description;
    this.min_damage = min_damage;
    this.max_damage = max_damage;
    this.weapon_buy_value = 0;
    this.weapon_sell_value = 0;
    this.weapon_say = weapon_say;
}
}

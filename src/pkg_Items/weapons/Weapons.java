/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Items.weapons;

import pkg_Items.Item;

/**
 *
 * @author VERESG
 */
public abstract class Weapons implements Item {
    
    String weapon_class;
    String weapon_name;
    public String weapon_description;
    int weapon_damage;
    int min_damage;
    int max_damage;
    public int weapon_sell_value;
    public int weapon_buy_value;
    public String weapon_say;
    
    
    public int weapon_getDamage() {
       weapon_damage = (int)(Math.random()* (max_damage - min_damage)+min_damage+1);  
       return weapon_damage;
    }

    public String getWeapon_class() {
        return weapon_class;
    }


    public String getWeapon_name() {
        return weapon_name;
    }

    
    public String getDescription() {
        return weapon_description;
    }

    public int getMin_damage() {
        return min_damage;
    }

    public int getMax_damage() {
        return max_damage;
    }

        public int getSellValue() {
        return weapon_sell_value;
    }

        public int getBuyValue() {
        return weapon_buy_value;
    }
        
    public String getWeapon_say() {
        return weapon_say;
    }
    
    public String getName() {
        return weapon_name;
    }
}

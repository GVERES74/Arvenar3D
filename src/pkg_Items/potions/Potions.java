/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Items.potions;

import pkg_Items.Item;

/**
 *
 * @author VERESG
 */
public abstract class Potions implements Item {
    
    String potion_class;
    String potion_name;
    public String potion_description;
    int potion_healvalue;
    public int potion_sell_value;
    public int potion_buy_value;
    public String potion_say;

    public String getPotion_class() {
        return potion_class;
    }

    public String getPotion_name() {
        return potion_name;
    }

    public String getPotion_description() {
        return potion_description;
    }

    public int getPotion_healvalue() {
        return potion_healvalue;
    }

    public int getPotion_sell_value() {
        return potion_sell_value;
    }

    public int getPotion_buy_value() {
        return potion_buy_value;
    }

    public String getPotion_say() {
        return potion_say;
    }
    
    public String getName() {
        return potion_name;
    }
    
}

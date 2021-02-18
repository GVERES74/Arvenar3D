/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Items.potions;

/**
 *
 * @author VERESG
 */
public class Potions_Healing extends Potions{
    
    public Potions_Healing(String potion_name, String potion_description, int potion_healvalue, int potion_trade_value, String potion_say){
    
    this.potion_class = "Healing potions";
    this.potion_name = potion_name;
    this.potion_description = potion_description;
    this.potion_healvalue = potion_healvalue;
    this.potion_sell_value = potion_trade_value/2;
    this.potion_buy_value = potion_trade_value;
    this.potion_say = potion_say;
}
    @Override
    public String getName() {
        return potion_name;
    }
}

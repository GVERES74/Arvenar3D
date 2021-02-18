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
public interface Character {
    
    public void addItemToInventory (Object items);    
               
    public void attacksOpponent (Character opponent);
/*Nem is olyan bonyolult interfészt definiálni. 
  Ebből származik a játszható karakter (Playable_Character) absztrakt osztály és a nem játszható (NPC) absztrakt osztály.
 Azért definiáljuk ebben (csak törzs nélkül lehet, majd az absztrakt osztályban megvalósítod) az ellenfél támadása 
(attacksOpponent) metódusfejet, mert így a játszható és nem játszható karakterek saját osztályukból leszármazott "ojjettumot :)" is támadhatják.
Először csak a Playable_Character absztrakt osztályt hoztam létre, és kiderült, hogy csak a paraméterként átadott osztályt támadhatja a másik,
pl. public void attacksOpponent (PC_Human opponent); a "torp" támadhatja az "ember" -t, de fordítva nem. Így viszont mindkettő Playable_Character, tehát működik!*/
}

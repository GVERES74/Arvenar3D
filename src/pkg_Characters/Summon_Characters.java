/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Characters;

import java.util.ArrayList;
import java.util.List;
import pkg_Items.potions.*;
import pkg_Items.weapons.*;

/**
 *
 * @author VERESG
 */
public class Summon_Characters {
    
          
    public Summon_Characters(){
        
       
    }
            
       //Inventory: Fegyverek létrehozása    
        public Weapons_One_Handed sword = new Weapons_One_Handed("Sword","Rusty sword", 9, 25, 35, "slashing");
        public Weapons_One_Handed knife = new Weapons_One_Handed("Knife","Bread slicer", 1, 4, 3, "slicing");
        public Weapons_Staff staff = new Weapons_Staff("Staff","Firemage staff", 8, 15, 18, "casting fire");
        public Weapons_Two_Handed cleaver = new Weapons_Two_Handed("Cleaver","Big Cleaver", 11, 25, 28, "chopping");
        public Weapons_Two_Handed axe = new Weapons_Two_Handed("Axe","Golden Axe", 21, 28, 26, "cutting");
        public Weapons_Ranged bow = new Weapons_Ranged("Bow","Ice arrows", 13, 16, 22, "shooting");
        public Weapons_Magic fireball = new Weapons_Magic("Fireball","Fireball", 5, 9, 0, "burning");
        
    //Állatok "fegyvere"
        public Weapons_Animal teeth = new Weapons_Animal("sharp teeth", 10, 25, "biting");
        public Weapons_Animal claws = new Weapons_Animal("razor claws", 11, 27, "scratching");
        public Weapons_Animal poison = new Weapons_Animal("poisoning teeth", 14, 20, "poisoning");
        
    //Szörnyek "fegyvere"
        public Weapons_Monster ogre_fist = new Weapons_Monster("fist", 20, 26, "punching you");
        public Weapons_Monster dragon_fire = new Weapons_Monster("fire", 32, 42, "burning you to ash");
        
    //Inventory: Potionok létrehozása
        public Potions_Healing healing = new Potions_Healing("Lesser healing potion", "Heal wounds", 2, 4, "healing you");
        
    //Játszható karakterek "Hero" létrehozása
        public PC_Dwarf dwarf_smith = new PC_Dwarf("Male", "Otli", 33, "Weaponsmith", axe, healing, 50, 25,"src/img/pc_dwarf_smith_otli.png",
        "Otli was born in Asgor, deep down in the dwarven mines of Ihrtil. His father Orda, the local weaponsmith taught him everything that he needed to survive on the surface world.");
        
        public PC_Human human_warrior = new PC_Human("Male", "Arthur", 26, "Knight", sword, healing, 55, 30,"src/img/pc_human_warrior_arthur.jpg",
                "Arthur was born in a small fishing village near the Carisian sea where the Kingdom of Alazar begins. He joined the king's service, and is currently seeking for the Golden Gral.");
        
        public PC_Elf elf_rogue = new PC_Elf("Female", "Arven", 83, "Archer", bow, healing, 52, 28, "src/img/pc_elf_female_arven.png",
        "Arven was just a little girl when she left Elvenar to seek adventures....but she needs to get back now, as folks speak her father will die soon.");
        
        public PC_Human human_mage = new PC_Human("Female", "Morgana", 27, "Fire mage", fireball, healing, 40, 23,"src/img/pc_human_mage_morgana.png",
        "Morgana was adopted and raised by a young mage couple sortly after she was born and left in a monastery by her parents who are unknown even today. She decided to travel accross the entire world to find her ancestors.");
        
     //NPC -k létrehozása
        
        //Kereskedők
        public NPC_Human_Trader human_trader = new NPC_Human_Trader("Rosalinda", "Merchant", knife, 100, "src/img/npc_human_merchant_rosalinda.png",
        "Rosalinda has a small own village of merchantry, and buys - sells any goods you can imagine. She also has some stolen / black market goods, if you know the magic word.");
            //Dwarf merchant?
            //Elf merchant?  
        
        //Ellenségek - human
        public NPC_Human_Bandit outlaw = new NPC_Human_Bandit("Caligula", "Bandit Outlaw", cleaver, cleaver.getWeapon_say(),100, "src/img/npc_human_bandit_caligula.png",
        "Caligula was originally a headhunter in the king's service, then become the leader of bandits in the Ravennest woods. Money changes people, but also shorten your life.");
        
        public NPC_Human_Mage merlin = new NPC_Human_Mage("Merlino", "Mage Inquisitor", staff, staff.getWeapon_say(),100, "src/img/npc_human_mage_merlino.jpg",
        "Merlino first appeared in Arvenar when the Guild of Mages dispelled him out of Morkedar. What the reason was, is unknown, and doesn't matter either. Robbery by using magic is far more profitable.");
            //NPC_Dwarf_Berserker.java
            //NPC_Elf_Mage.java
        
        //Ellenségek - animal
        public NPC_Animal_Wolf wolf1 = new NPC_Animal_Wolf("Sharpteeth", "Gray wolf", teeth, teeth.getWeapon_say(), 100, "src/img/npc_animal_wolf1.png",
        "Sharpteeth is a good dog, and a reliable friend. You may tame him later.");
        public NPC_Animal_Wolf wolf2 = new NPC_Animal_Wolf("Bigteeth", "Wolf pack leader", teeth, teeth.getWeapon_say(), 120, "src/img/npc_animal_wolf2.jpg",
        "Bigteeth rules and terrorizes the Ravennest woods with his pack. They know no mercy, but know, what human flesh tastes.");
        
        public NPC_Animal_Bear bbear1 = new NPC_Animal_Bear("Old razorclaw", "Brown bear", claws, claws.getWeapon_say(), 150,"src/img/npc_animal_bbear1.jpg",
        "Big Bear light can cause serious or lethal damage by his teeth and claws, but mostly avoids human. You can fight him, but you may die...and end up as his meat for dinner.");
        public NPC_Animal_Bear bbear2 = new NPC_Animal_Bear("Big paw", "Grizzly bear", teeth, teeth.getWeapon_say(), 200,"src/img/npc_animal_bbear2.jpg",
        "Big Bear heavy causes lethal damage immediately by his teeth and claws, and mostly attacks human. Avoid him at all costs. You cannot stand a chance!! Run for your life, if you can..");
        
        public NPC_Animal_Croco croco1 = new NPC_Animal_Croco("Hundred teeth", "Crocodile", teeth, teeth.getWeapon_say(), 200,"src/img/npc_animal_croco1.jpg",
        "One hundred teeth grips anything that comes into them. Crocodiles can be found everything around the lands with swamps, lakes and rivers.");
        
        public NPC_Animal_Eagle eagle1 = new NPC_Animal_Eagle("Great wings", "Mountain Eagle", claws, claws.getWeapon_say(), 200,"src/img/npc_animal_eagle1.jpg",
        "Eagles can attack from a mile high in the sky, and are able to grip and take even a human child. Watch out for them!");
        
        public NPC_Animal_Snake snake1 = new NPC_Animal_Snake("Poisoner", "Snake", poison, poison.getWeapon_say(), 200,"src/img/npc_animal_snake1.jpg",
        "Snakes usually avoid contact with other species, apart from their lunch. Their poison can though be lethal, so you better avoid them too!");
        
        public NPC_Animal_WildBoar boar1 = new NPC_Animal_WildBoar("Piggy", "WildBoar", teeth, teeth.getWeapon_say(), 200,"src/img/npc_animal_wildboar1.jpg",
        "Boars live in forests and in open areas. You better avoid them if you have harmed one. Their attack can cause serious damage!!");
        

        
        //Ellenségek - monster
        public NPC_Monster_Ogre ogre1 = new NPC_Monster_Ogre("Gorka", "Cave Ogre", ogre_fist, ogre_fist.getWeapon_say(), 200, "src/img/npc_monster_ogre_gorka.jpg",
        "Gorka be strong. Gorka be hungry. Gorka has big weapon. Gorka hates human. Gorka be smart. .... Now, you can question this last statement.....");
        
        public NPC_Monster_Dragon dragon1 = new NPC_Monster_Dragon("Azariel", "Fire Dragon", dragon_fire, dragon_fire.getWeapon_say(), 300, "src/img/npc_monster_dragon_azariel.jpg",
        "Azariel was born far back before the new world was created and ruled by humans, elven and dwarves. Folks say he can be 1000 years old, but his whereabouts these days are unknown. Or not.");
        
        
        
        
        //Inventory -k feltöltése
        public void load_inventory(){
        
        //trader.addItemToInventory("Name: "+knife.getName()+" \n\tDescription: "+knife.getDescription()+" \n\tClass: "+knife.getWeapon_class()+" \n\tBuy value: "+knife.getBuyValue()+" \n\tSell value: "+knife.getSellValue()+" \n\tMin. damage: "+knife.getMin_damage()+" \n\tMax. damage: "+knife.getMax_damage());
        human_trader.addItemToInventory(knife.getName());
        human_trader.addItemToInventory(sword.getName());
        human_trader.addItemToInventory(staff.getName());
        human_trader.addItemToInventory(cleaver.getName());
        human_trader.addItemToInventory(axe.getName());
        human_trader.addItemToInventory(bow.getName());
        human_trader.addItemToInventory(healing.getName());
        
        
                
        dwarf_smith.addItemToInventory(healing.getName());
        dwarf_smith.addItemToInventory(axe.getName());
        dwarf_smith.addItemToInventory(cleaver.getName());
        
        }
        

}

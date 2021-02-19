/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Maps;

/**
 *
 * @author TE332168
 */
public class Create_Maps {
    
 public Create_Maps(){
     
 }    
     //Hint: Arvenar_Maps(String map_name, String map_desc, String map_inhabitants, String map_image_path, String map_story)
 
     public Arvenar_Maps forest = new Arvenar_Maps("Whitewood forest", "Elven forest inhabited by elven", "Thieves, Bears, Wolves, Wildboars", "src/maps/m1_forest.jpg",
             "The forest is said to be as old as the world itself. It was created by the Elven ArchMage, who destroyed the old world and brought this new one to life for his people.");
     
     public Arvenar_Maps city = new Arvenar_Maps("Elyven city", "Elven city inhabited by elven citizens","Thieves", "src/maps/m2_elven_city.jpg", 
             "What a beautyful city!! - said people that ever been to Elyven of Arvenar. Markets, shops, and taverns for visitors are open to the public. But watch out for yourself! "
                     + "Thieves, bitches and drunken are common even in this area of the Elven Empire.");
     
     public Arvenar_Maps fort = new Arvenar_Maps("Fort of Lavelyn", "Elven fort once inhabited by elven warriors", "Skeletons, Undead, Spiders, Rats", "src/maps/m3_fort.jpg", 
             "Fort of Lavelyn was once the strategic HQ of the elven army, lead by Eredyr. During the second war beetwen humans and elven the fort was deserted and long forgotten");
     
     public Arvenar_Maps desert = new Arvenar_Maps("Forgotten desert", "Desert inhabited by thugs, animals and minions", "Snake, Scorpion, Bandits, Wolves", "src/maps/m4_desert.jpg", 
             "This desert as deadly as any other area in Arvenar. You never know, what is lurking among the sand dunes, waiting for its prey.");
   
     public Arvenar_Maps village = new Arvenar_Maps("Ayisha village", "Small, snowy village in th mountains", "Bandits, Smugglers, Wildboars", "src/maps/m5_snowy_village.jpg", 
             "Let it snow. - say the locals. The winter is long and dark in this area, far deep in the Amaran mountains. Visitors are rare, except for adventurers, as it is said that "
                     + "the smugglers buried here gold before they were driven out during the war. They are waiting for their big day of return, and are dangerous.");


     public Arvenar_Maps mines = new Arvenar_Maps("Orni'a mine", "Dwarven underground mine", "Smugglers, Rats, Outlaws", "src/maps/m6_dwarven_mines.jpg", 
             "The mine was closed shortly after the dwarven recognized that the source of the magic ore would be depleted by winter. Smithies won't work without ore, "
                     + "shops don't survive without armors and weapons.");
     
     
     public Arvenar_Maps harbor = new Arvenar_Maps("Port Havole", "Harbor at the sea", "Smugglers, Outlaws, Pirates", "src/maps/m7_harbor.jpg", 
             "Port Havole was built by the elven, but then later on ruled by human. The harbor is vital for markets and trade, and, of course, smuggling");
     
     
     public Arvenar_Maps countryside = new Arvenar_Maps("Countryside Farm", "Farm at countryside", "Smugglers, Outlaws, Pirates, Bears, Wolves, Wildboars", "src/maps/m8_farm.jpg", 
             "Lonely farm at countryside, far from crowded cities.");
}

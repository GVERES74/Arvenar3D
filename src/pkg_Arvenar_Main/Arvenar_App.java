package pkg_Arvenar_Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkg_Characters.Summon_Characters;



/**
 *
 * @author VERESG
 */
public class Arvenar_App{
    
    ArvenarSetPC choose_player = new ArvenarSetPC(); 
    Arvenar_View_NPC view_npc = new Arvenar_View_NPC(); 
    Summon_Characters born = new Summon_Characters();
    Arvenar_View_Maps view_maps = new Arvenar_View_Maps();
    ArvenarSettings setgame;
    ArvenarGameGUI gamegui;
    
    
    public Arvenar_App() throws FileNotFoundException{
        try {
            //Ez a konstruktor kell ahhoz, hogy lekezelje a fenti példányosítást.
            setgame = new ArvenarSettings();
            gamegui = new ArvenarGameGUI();
        } catch (Exception ex) {
            Logger.getLogger(Arvenar_App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //public static void main(String[] args){ !!Itt nem lehet main függvény, ha máshol már van!! --> ElvenFXMain.java
      
    public void fight() throws FileNotFoundException{
        
            
            born.dwarf_smith.attacksOpponent(born.wolf2);
            born.human_warrior.attacksOpponent(born.wolf1);
            born.elf_rogue.attacksOpponent(born.outlaw);
            born.outlaw.attacksOpponent(born.dwarf_smith);
            born.wolf2.attacksOpponent(born.human_warrior);
            born.bbear1.attacksOpponent(born.elf_rogue);
            born.merlin.attacksOpponent(born.human_warrior);
            born.bbear2.attacksOpponent(born.dwarf_smith);
            born.human_mage.attacksOpponent(born.bbear2);
     }       
             
        //PC és NPC adatok          //PC és NPC adatok  

     public void trade(){
                
        //born.trader.trade_with_Character(born.dwarf_smith, born.axe);
        born.human_trader.trade_with_Character(born.elf_rogue, born.bow);
     }
        
     public void stats(){
       
        born.load_inventory(); //inventory -k feltöltése
        System.out.println("Current hero: "+choose_player.getHeroName()+"\n");
        
        String hero = choose_player.getHeroName();
        
        switch (hero){
            case "Arthur": born.human_warrior.getPcDesc(); 
            break;
            case "Morgana": born.human_mage.getPcDesc();
            break;
            case "Arven": born.elf_rogue.getPcDesc(); 
            break;
            case "Otli": born.dwarf_smith.getPcDesc(); 
            break;
        }
    }
     
    
} 

      
      
      
      




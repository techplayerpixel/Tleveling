package me.hahulala.TowerLeveling;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.hahulala.TowerLeveling.gui.MainGui;

public class HashMaps implements Listener{
	int no;
	TowerLeveling plugin;
	public HashMaps(TowerLeveling plugin){
        this.plugin = plugin;
	}
	public static Map<String,String> xpadded = new HashMap<String,String>();
	public static List<Player> players = new ArrayList<Player>();
	
	public static Map<String,Double> Agility = new HashMap<String,Double>();
	public static Map<String,Double> Strength = new HashMap<String,Double>();
	public static Map<String,Double> Health = new HashMap<String,Double>();
	public static Map<String,Double> Endurance = new HashMap<String,Double>();
	public static Map<String,Double> DefencePen = new HashMap<String,Double>();
	public static Map<String,Double> Luck = new HashMap<String,Double>();
	public static Map<String,Integer> Mana = new HashMap<String,Integer>();
	public static Map<String,Integer> ManaReg = new HashMap<String,Integer>();
	
	public static Map<String,Integer> Level = new HashMap<String,Integer>();
	public static Map<String,Integer> currentxp = new HashMap<String,Integer>();
	public static Map<String,Integer> Maxxp = new HashMap<String,Integer>();
	public static Map<String,Integer> FreeStats = new HashMap<String,Integer>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String name = p.getName();
		if(!(Mana.containsKey(name))) {
			p.getInventory().setItem(8, MainGui.menu);
		}
		if(!(Mana.containsKey(name))) {
			Mana.put(name, 100);
			ManaReg.put(name, 100);
			p.sendMessage("§aYour mana has been set to "+"§c100"+"§a!");
		}
		if(!(Agility.containsKey(name))) {
			Agility.put(name, (double)10);
			p.sendMessage("§aYour agility has been set to 10!"+"§c10"+"§a!");
		}
		if(!(FreeStats.containsKey(name))) {
			FreeStats.put(name, 1);
			p.sendMessage("§aYour have been awarded "+"§c5"+"§a Free stat points!");
		}
		if(!(Health.containsKey(name))) {
			Health.put(name, (double)20);
			p.sendMessage("§aYour Health has been set to:"+"§c20"+"§a!");
		}
		if(!(Strength.containsKey(name))) {
			Strength.put(name, (double)20);
			p.sendMessage("§aYour Strength has been set to:"+"§c20"+"§a!");
		}
		if(!(Endurance.containsKey(name))) {
			Endurance.put(name, (double)10);
			p.sendMessage("§aYour Endurance has been set to:"+"§c10"+"§a!");
		}
		if(!(DefencePen.containsKey(name))) {
			DefencePen.put(name, (double)0);
			p.sendMessage("§aYour Defence Penetration has been set to:"+"§c0"+"§a!");
		}
		if(!(Luck.containsKey(name))) {
			Luck.put(name, (double)10);
			p.sendMessage("§aYour Luck has been set to:"+"§c10"+"§a!");
		}
		if(!(currentxp.containsKey(name))) {
			currentxp.put(name, 0);
			p.sendMessage("§aYour Current XP has been set to:"+"§c0"+"§a!");
		}
		if(!(Maxxp.containsKey(name))) {
			Maxxp.put(name, 10);
			p.sendMessage("§aYou need "+"§c100"+"§a! xp more to level up!");
		}
		if(!(Level.containsKey(name))) {
			Level.put(name, 1);
			p.sendMessage("§aYour Level has been set to:"+"§c1"+"§a!");
		}
		if(!(players.contains(p))){
			players.add(p);
		}
	}
	
	public void onLeft(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(players.contains(p)) {
			players.remove(p);
		}
	}
	public static void setXpActionBar(Player p,String howmuch) {
		if(xpadded.containsKey(p.getName())) {
			xpadded.remove(p.getName());
			xpadded.put(p.getName(), howmuch);
		}else {
			xpadded.put(p.getName(), howmuch);
		}
	}
	public static void setXpBarNull(Player p) {
		if(xpadded.containsKey(p.getName())) {
			xpadded.remove(p.getName());
		}
	}
	
}

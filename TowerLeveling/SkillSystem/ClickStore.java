package me.hahulala.TowerLeveling.SkillSystem;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickStore implements Listener{
	public static HashMap<String,Deque<String>> lastClicked = new HashMap<String,Deque<String>>();
	public static HashMap<String,Integer> lastUpdateTime = new HashMap<String,Integer>();
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction()==Action.RIGHT_CLICK_BLOCK)) {
			if(!(lastClicked.containsKey(p.getName()))) {
				Deque<String> newlist = new LinkedList<String>();
				newlist.addFirst("R");
				lastClicked.put(p.getName(), newlist);
			}else {
				lastClicked.get(p.getName()).addFirst("R");
			}
			if(lastUpdateTime.containsKey(p.getName())) {
				lastUpdateTime.remove(p.getName());
			}
			lastUpdateTime.put(p.getName(), (int) System.currentTimeMillis());
		}
		if((e.getAction() == Action.LEFT_CLICK_AIR) || (e.getAction()==Action.LEFT_CLICK_BLOCK)) {
			if(!(lastClicked.containsKey(p.getName()))) {
				Deque<String> newlist = new LinkedList<String>();
				newlist.addFirst("L");
				lastClicked.put(p.getName(), newlist);
			}else {
				lastClicked.get(p.getName()).addFirst("L");
			}
			if(lastUpdateTime.containsKey(p.getName())) {
				lastUpdateTime.remove(p.getName());
			}
			lastUpdateTime.put(p.getName(), (int) System.currentTimeMillis());
		}
	}
}

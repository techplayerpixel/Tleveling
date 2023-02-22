package me.hahulala.TowerLeveling.SetArea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class SetAreaMain implements CommandExecutor{
	
	public static Location hub1;
	public static Location hub2;
	
	public static List<String> Areas = new ArrayList<String>();
	public static Map<String,String> AreaType = new HashMap<String,String>();
	public static Map<String,String> MobType = new HashMap<String,String>();
	public static Map<String,Location> pos1 = new HashMap<String,Location>();
	public static Map<String,Location> pos2 = new HashMap<String,Location>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Only Players can use this Command!");
		}
		if(!(sender.hasPermission("tl.use"))) {
			sender.sendMessage("You dont have permission to use this command!");
			return true;
		}
		Player p = (Player) sender;
		if(label.equalsIgnoreCase("CreateArea")) {
			if(args[0] != null) {
				if(!Areas.contains(args[0])) {
					Areas.add(args[0]);
					AreaType.put(args[0], "nil");
					MobType.put(args[0], "nil");
					p.sendMessage(ChatColor.GREEN+"Created a area name: " + ChatColor.RED + args[0]);
				}else {
					p.sendMessage(ChatColor.RED+"There is already a area named: "+ChatColor.BLUE+ args[0]);
				}
			}
		}
		if(label.equalsIgnoreCase("Area")) {
			if(args[0] != null  && Areas.contains(args[0])) {
				if(args[1] !=null) {
					if(args[1] == "setloc") {
						if(args[2] !=null ) {
							if(args[2] == "pos1") {
								if(pos1.containsKey(args[0])) {
									pos1.remove(args[0]);
									pos1.put(args[0], p.getLocation());
								}else {
									pos1.put(args[0], p.getLocation());
								}
							}
							if(args[2] == "pos2") {
								if(pos2.containsKey(args[0])) {
									pos2.remove(args[0]);
									pos2.put(args[0], p.getLocation());
								}else {
									pos2.put(args[0], p.getLocation());
								}
							}
						}
					}
					if(args[1] == "type") {
						if(args[2] !=null) {
							if(args[2] == "mine") {
								String currentype = AreaType.get(args[0]);
								AreaType.remove(args[0]);
								AreaType.put(args[0], "Mine");
								p.sendMessage(ChatColor.RED+"Changed area type from "+ ChatColor.RED+ currentype +ChatColor.GREEN+" to " + ChatColor.RED + "Mine");
							}
							if(args[2] == "hub") {
								String currentype = AreaType.get(args[0]);
								AreaType.remove(args[0]);
								AreaType.put(args[0], "Hub");
								p.sendMessage(ChatColor.RED+"Changed area type from "+ ChatColor.RED+ currentype +ChatColor.GREEN+" to " + ChatColor.RED + "Hub");
							}
						}
					}
					if(args[1] == "mobtype") {
						if(args[2]!=null) {
							if(args[2] == "Zombie") {
								String currentype = AreaType.get(args[0]);
								AreaType.remove(args[0]);
								AreaType.put(args[0], "Zombie");
								p.sendMessage(ChatColor.RED+"Changed area type from "+ ChatColor.RED+ currentype +ChatColor.GREEN+" to " + ChatColor.RED + "Zombie");
							}
						}
					}
				}else {
					p.sendMessage(ChatColor.RED+"Please put an argument to use!");
				}
			}else {
				p.sendMessage(ChatColor.RED+"There is no such area created as: "+ ChatColor.BLUE+args[0]);
			}
		}
		
		return false;
	}
	public static boolean inHub(Entity p,String area) {
		World pworld = p.getWorld();
		if(pos1.get(area).getWorld() == pworld) {
			if(((((p.getLocation().getY() < pos1.get(area).getY())&&(p.getLocation().getY() > pos2.get(area).getY()))||(p.getLocation().getY() > pos1.get(area).getY())&&(p.getLocation().getY() < pos2.get(area).getY()))&&
					(((p.getLocation().getX() < pos1.get(area).getX())&&(p.getLocation().getX() > pos2.get(area).getX()))||((p.getLocation().getX() > pos1.get(area).getX())&&(p.getLocation().getX() < pos2.get(area).getX())))&&
					(((p.getLocation().getY() < pos1.get(area).getY())&&(p.getLocation().getY() > pos2.get(area).getY()))||((p.getLocation().getY() > pos1.get(area).getY())&&(p.getLocation().getY() < pos2.get(area).getY())))&&
					(((p.getLocation().getZ() < pos1.get(area).getZ())&&(p.getLocation().getZ() > pos2.get(area).getZ()))||((p.getLocation().getZ() > pos1.get(area).getZ())&&(p.getLocation().getZ() < pos2.get(area).getZ()))))) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}

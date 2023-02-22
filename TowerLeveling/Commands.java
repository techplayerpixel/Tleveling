package me.hahulala.TowerLeveling;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(!(p.hasPermission("tl.use"))) {
				p.sendMessage(ChatColor.RED+ "You dont have permission to use this command!");
				return true;
			}
			
			if(args.length == 0) {
				p.sendMessage("§cYou didn't provide enough information!");
				p.sendMessage("§aExample: /mana <Player name> <amount of mana needed to be set!>");
			}
			if(HashMaps.players.contains(Bukkit.getPlayer(args[0]))) {
				if(HashMaps.Mana.containsKey(args[0])) {
					Integer addbase = Integer.parseInt(args[1]);
					HashMaps.Mana.remove(args[0]);
					HashMaps.Mana.put(args[0], addbase);
					Integer all = addbase;
					Player seter = Bukkit.getPlayer(args[0]);
					seter.sendMessage("§aYou mana has been set to: "+ChatColor.RED  + all.toString());
				}else {
					HashMaps.Mana.put(args[0], Integer.parseInt(args[1]));
					Integer mana =  Integer.parseInt(args[1]);
					Player seter = Bukkit.getPlayer(args[0]);
					seter.sendMessage("§aYou mana has been set to: "+ChatColor.RED  + mana.toString());
				}
			}
			}
		
		return false;
	}

}

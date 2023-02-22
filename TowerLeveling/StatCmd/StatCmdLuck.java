package me.hahulala.TowerLeveling.StatCmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hahulala.TowerLeveling.HashMaps;

public class StatCmdLuck implements CommandExecutor{
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
				p.sendMessage("§aExample: /luck <Player name> <amount of Luck points needed to be set!>");
			}
			if(HashMaps.players.contains(Bukkit.getPlayer(args[0]))) {
				if(HashMaps.Luck.containsKey(args[0])) {
					double addbase = Double.valueOf(args[1]);
					HashMaps.Luck.remove(args[0]);
					HashMaps.Luck.put(args[0], addbase);
					Integer all = (int)addbase;
					Player seter = Bukkit.getPlayer(args[0]);
					seter.sendMessage("§aYou Luck has been set to: "+ChatColor.RED  + all.toString());
				}else {
					HashMaps.Luck.put(args[0], Double.valueOf(args[1]));
					Integer Luck =  Integer.parseInt(args[1]);
					Player seter = Bukkit.getPlayer(args[0]);
					seter.sendMessage("§aYou Luck has been set to: "+ChatColor.RED  + Luck.toString());
				}
			}
			}
		
		return false;
	}
}

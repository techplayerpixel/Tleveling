package me.hahulala.TowerLeveling.StatCmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hahulala.TowerLeveling.HashMaps;

public class StatCmdStrength implements CommandExecutor{
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
				p.sendMessage("§aExample: /strength <Player name> <amount of Strength points needed to be set!>");
			}
			if(HashMaps.players.contains(Bukkit.getPlayer(args[0]))) {
				if(HashMaps.Strength.containsKey(args[0])) {
					double addbase = Double.valueOf(args[1]);
					HashMaps.Strength.remove(args[0]);
					HashMaps.Strength.put(args[0], addbase);
					Integer all = (int)addbase;
					Player seter = Bukkit.getPlayer(args[0]);
					seter.sendMessage("§aYou Strength has been set to: "+ChatColor.RED  + all.toString());
				}else {
					HashMaps.Strength.put(args[0], Double.valueOf(args[1]));
					Integer Agility =  Integer.parseInt(args[1]);
					Player seter = Bukkit.getPlayer(args[0]);
					seter.sendMessage("§aYou Strength has been set to: "+ChatColor.RED  + Agility.toString());
				}
			}
			}
		return false;
	}
}

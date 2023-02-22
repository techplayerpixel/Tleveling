package me.hahulala.TowerLeveling.gui;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenCraftingSettings implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("Only Players can use this command!");
		}
		if(sender.hasPermission("tl.use")) {
			Player p = (Player) sender;
			MenuOpen.OpenCraftingSettins(p);
		}
		
		return false;
	}

}

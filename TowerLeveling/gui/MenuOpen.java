package me.hahulala.TowerLeveling.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuOpen implements Listener{
	@EventHandler
	public void onMenuOpen(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			ItemStack item = p.getInventory().getItemInMainHand();
			if(item.getItemMeta() != null && item.getItemMeta().getDisplayName() !=null &&
					item.getItemMeta().getDisplayName().equals("§aStats Menu §7(Right Click)")) {
				openMenu(p);
				e.setCancelled(true);
			}
		}
	}
	public static void openMenu(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54,"§7Stats Menu");
		for(int slot = 0; slot < 54;++slot) {
			inv.setItem(slot, MainGui.nullgray);
		}
		inv.setItem(13, MainGui.getPlayerHead(p));
		inv.setItem(21, MainGui.calendar);
		inv.setItem(22, MainGui.instantshop);
		inv.setItem(23, MainGui.questlog);
		inv.setItem(31, MainGui.craftingtable);
		inv.setItem(49, MainGui.closemenu);
		
		p.openInventory(inv);
	}
	public static void OpenProfile(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54,"§7Your profile");
		for(int slot = 0; slot < 54;++slot) {
			inv.setItem(slot, MainGui.nullgray);
		}
		ItemStack helmet = p.getInventory().getHelmet();
		ItemStack chest = p.getInventory().getChestplate();
		ItemStack leggings = p.getInventory().getLeggings();
		ItemStack boots = p.getInventory().getBoots();
		ItemStack iteminhand = p.getInventory().getItem(0);
		
		inv.setItem(10, helmet);
		inv.setItem(19, chest);
		inv.setItem(28, leggings);
		inv.setItem(37, boots);
		inv.setItem(46, iteminhand);
		inv.setItem(49, MainGui.closemenu);
		inv.setItem(48, MainGui.createMenuGoBack("To Main Menu"));
		inv.setItem(24, MainGui.createMenuStats(p));
		
		p.openInventory(inv);
	}
	public static void OpenStats(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54,"§7Your Stats");
		for(int slot = 0; slot < 54;++slot) {
			inv.setItem(slot, MainGui.nullgray);
		}
		inv.setItem(4, MainGui.createMenuStats(p));
		inv.setItem(11, MainGui.createPlayerStrength(p));
		inv.setItem(12, MainGui.createPlayerAgility(p));
		inv.setItem(13, MainGui.createPlayerEndurance(p));
		inv.setItem(14, MainGui.createPlayerHealth(p));
		inv.setItem(15, MainGui.createPlayerMana(p));
		inv.setItem(20, MainGui.createPlayerLuck(p));
		inv.setItem(5, MainGui.createPlayerFreeStats(p));
		inv.setItem(48, MainGui.createMenuGoBack("TO you profile"));
		inv.setItem(49, MainGui.closemenu);
		
		p.openInventory(inv);
	}
	public static void OpenCraftingSettins(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54,"§7Add Recipe");
		for(int slot = 0; slot < 54;++slot) {
			inv.setItem(slot, MainGui.nullgray);
		}
		inv.setItem(10, null);
		inv.setItem(11, null);
		inv.setItem(12, null);
		
		inv.setItem(19, null);
		inv.setItem(20, null);
		inv.setItem(21, null);
		
		inv.setItem(28, null);
		inv.setItem(29, null);
		inv.setItem(30, null);
		
		inv.setItem(23, null);
		inv.setItem(53, MainGui.createrecipe);
		inv.setItem(44, MainGui.removeattributes);
		
		inv.setItem(46, MainGui.setGradeCommon);
		inv.setItem(47, MainGui.setGradeUnCommon);
		inv.setItem(48, MainGui.setGradeRare);
		inv.setItem(49, MainGui.setGradeUnique);
		inv.setItem(50, MainGui.setGradeEpic);
		inv.setItem(51, MainGui.setGradeLegendary);
		
		p.openInventory(inv);
	}
	public static void OpenCraftingTable(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54,"§7Crafting Table");
		for(int slot = 0; slot < 54;++slot) {
			inv.setItem(slot, MainGui.nullgray);
		}
		inv.setItem(10, null);
		inv.setItem(11, null);
		inv.setItem(12, null);
		
		inv.setItem(19, null);
		inv.setItem(20, null);
		inv.setItem(21, null);
		
		inv.setItem(28, null);
		inv.setItem(29, null);
		inv.setItem(30, null);
		
		inv.setItem(23, MainGui.Barrier);
		
		p.openInventory(inv);
	}
}

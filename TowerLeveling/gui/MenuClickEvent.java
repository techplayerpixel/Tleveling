package me.hahulala.TowerLeveling.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import me.hahulala.TowerLeveling.HashMaps;
import net.md_5.bungee.api.ChatColor;

public class MenuClickEvent implements Listener{
	@EventHandler
	public void onMenuClick(InventoryClickEvent e) {
		//MAIN MENU
		Player player = (Player) e.getWhoClicked();
        if(player.getOpenInventory().getTitle().equals("§7Stats Menu")) {
        	//item
        	ItemStack item = e.getCurrentItem();
        	//profile
        	if(item!=null&& item.getItemMeta()!=null && item.getItemMeta().getDisplayName()!=null && item.getItemMeta().getDisplayName().equals("§aYour Profile")) {
        		MenuOpen.OpenProfile(player);
        	}
        	if(item!=null&& item.getItemMeta()!=null && item.getItemMeta().getDisplayName()!=null &&item.getItemMeta().getDisplayName().equals("§cCLOSE"))
        		player.closeInventory();
        	if(item.getItemMeta().getDisplayName().equals("§aCrafting Table"))
        		MenuOpen.OpenCraftingTable(player);
        	
            e.setCancelled(true);
        }
        //PLAYER INVENTORY
        if(e.getClickedInventory() !=null &&e.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
        	if(e.getCurrentItem()!=null&&e.getCurrentItem().getItemMeta()!=null&& e.getCurrentItem().getItemMeta().getDisplayName().equals("§aStats Menu §7(Right Click)")) {
        		
        		MenuOpen.openMenu(player);
        		
        		e.setCancelled(true);
        	}
        }
        if(player.getOpenInventory().getTitle().equals("§7Your profile")) {
        	ItemStack item = e.getCurrentItem();
        	if(item.getItemMeta().getDisplayName().equals("§cCLOSE"))
        		player.closeInventory();
        	if(item.getItemMeta().getDisplayName().equals("§aGo Back"))
        		MenuOpen.openMenu(player);
        	if(item.getItemMeta().getDisplayName().equals("§cMain Stats"))
        		MenuOpen.OpenStats(player);
        	e.setCancelled(true);
        }
        if(player.getOpenInventory().getTitle().equals("§7Your Stats")) {
        	ItemStack item = e.getCurrentItem();
        	Integer fs = HashMaps.FreeStats.get(player.getName());
        	if(item!=null&& item.getItemMeta()!=null && item.getItemMeta().getDisplayName()!=null &&item.getItemMeta().getDisplayName().equals("§aGo Back"))
        		MenuOpen.OpenProfile(player);;
        	if(item!=null&& item.getItemMeta()!=null && item.getItemMeta().getDisplayName()!=null &&item.getItemMeta().getDisplayName().contains("§c❁Strength")) {
        		if(fs > 0) {
        			Double basestrength = HashMaps.Strength.get(player.getName());
        			HashMaps.Strength.remove(player.getName());
        			HashMaps.Strength.put(player.getName(), basestrength+1);
        			Integer newstrength = (int) (basestrength+1);
        			player.sendMessage(ChatColor.GREEN+"Your Strength is now: "+ChatColor.RED +newstrength.toString());
        			Integer basestats = HashMaps.FreeStats.get(player.getName());
        			HashMaps.FreeStats.remove(player.getName());
        			Integer newstats = basestats-1;
        			HashMaps.FreeStats.put(player.getName(), newstats);
        			player.sendMessage(ChatColor.GREEN+"Your Free Stats are now: "+ChatColor.RED + newstats.toString());
        			MenuOpen.OpenStats(player);
        		}else {
        			player.sendMessage("§cYou dont have enough free stat points!");
        		}
        	}
        	if(item!=null&& item.getItemMeta()!=null && item.getItemMeta().getDisplayName()!=null &&item.getItemMeta().getDisplayName().contains("§c❤Health")) {
        		if(fs > 0) {
        			Double baseHealth = HashMaps.Health.get(player.getName());
        			HashMaps.Health.remove(player.getName());
        			HashMaps.Health.put(player.getName(), baseHealth+1);
        			Integer newHealth = (int) (baseHealth+1);
        			player.sendMessage(ChatColor.GREEN+"Your Health is now: "+ChatColor.RED +newHealth.toString());
        			Integer basestats = HashMaps.FreeStats.get(player.getName());
        			HashMaps.FreeStats.remove(player.getName());
        			Integer newstats = basestats-1;
        			HashMaps.FreeStats.put(player.getName(), newstats);
        			player.sendMessage(ChatColor.GREEN+"Your Free Stats are now: "+ChatColor.RED + newstats.toString());
        			MenuOpen.OpenStats(player);
        		}else {
        			player.sendMessage("§cYou dont have enough free stat points!");
        		}
        	}
        	if(item!=null&& item.getItemMeta()!=null && item.getItemMeta().getDisplayName()!=null &&item.getItemMeta().getDisplayName().contains("§f✦Agility")) {
        		if(fs > 0) {
        			Double baseAgility = HashMaps.Agility.get(player.getName());
        			HashMaps.Agility.remove(player.getName());
        			HashMaps.Agility.put(player.getName(), baseAgility+1);
        			Integer newAgility = (int) (baseAgility+1);
        			player.sendMessage(ChatColor.GREEN+"Your Agility is now: "+ChatColor.RED +newAgility.toString());
        			Integer basestats = HashMaps.FreeStats.get(player.getName());
        			HashMaps.FreeStats.remove(player.getName());
        			Integer newstats = basestats-1;
        			HashMaps.FreeStats.put(player.getName(), newstats);
        			player.sendMessage(ChatColor.GREEN+"Your Free Stats are now: "+ChatColor.RED + newstats.toString());
        			MenuOpen.OpenStats(player);
        		}else {
        			player.sendMessage("§cYou dont have enough free stat points!");
        		}
        	}
        	if(item!=null&& item.getItemMeta()!=null && item.getItemMeta().getDisplayName()!=null &&item.getItemMeta().getDisplayName().contains("§b✎Mana")) {
        		if(fs > 0) {
        			Integer baseMana = HashMaps.Mana.get(player.getName());
        			HashMaps.Mana.remove(player.getName());
        			HashMaps.Mana.put(player.getName(), baseMana+1);
        			Integer newMana = (int) (baseMana+1);
        			player.sendMessage(ChatColor.GREEN+"Your Mana is now: "+ChatColor.RED +newMana.toString());
        			Integer basestats = HashMaps.FreeStats.get(player.getName());
        			HashMaps.FreeStats.remove(player.getName());
        			Integer newstats = basestats-1;
        			HashMaps.FreeStats.put(player.getName(), newstats);
        			player.sendMessage(ChatColor.GREEN+"Your Free Stats are now: "+ChatColor.RED + newstats.toString());
        			MenuOpen.OpenStats(player);
        		}else {
        			player.sendMessage("§cYou dont have enough free stat points!");
        		}
        	}
        	if(item!=null&& item.getItemMeta()!=null && item.getItemMeta().getDisplayName()!=null &&item.getItemMeta().getDisplayName().contains("§a❈Endurance")) {
        		if(fs > 0) {
        			Double baseEndurance = HashMaps.Endurance.get(player.getName());
        			HashMaps.Endurance.remove(player.getName());
        			HashMaps.Endurance.put(player.getName(), baseEndurance+1);
        			Integer newEndurance = (int) (baseEndurance+1);
        			player.sendMessage(ChatColor.GREEN+"Your Endurance is now: "+ChatColor.RED +newEndurance.toString());
        			Integer basestats = HashMaps.FreeStats.get(player.getName());
        			HashMaps.FreeStats.remove(player.getName());
        			Integer newstats = basestats-1;
        			HashMaps.FreeStats.put(player.getName(), newstats);
        			player.sendMessage(ChatColor.GREEN+"Your Free Stats are now: "+ChatColor.RED + newstats.toString());
        			MenuOpen.OpenStats(player);
        		}else {
        			player.sendMessage("§cYou dont have enough free stat points!");
        		}
        	}
        	if(item!=null&& item.getItemMeta()!=null && item.getItemMeta().getDisplayName()!=null &&item.getItemMeta().getDisplayName().contains("§6♣Luck")) {
        		if(fs > 0) {
        			Double baseLuck = HashMaps.Luck.get(player.getName());
        			HashMaps.Luck.remove(player.getName());
        			HashMaps.Luck.put(player.getName(), baseLuck+1);
        			Integer newLuck = (int) (baseLuck+1);
        			player.sendMessage(ChatColor.GREEN+"Your Luck is now: "+ChatColor.RED +newLuck.toString());
        			Integer basestats = HashMaps.FreeStats.get(player.getName());
        			HashMaps.FreeStats.remove(player.getName());
        			Integer newstats = basestats-1;
        			HashMaps.FreeStats.put(player.getName(), newstats);
        			player.sendMessage(ChatColor.GREEN+"Your Free Stats are now: "+ChatColor.RED + newstats.toString());
        			MenuOpen.OpenStats(player);
        		}else {
        			player.sendMessage("§cYou dont have enough free stat points!");
        		}
        	}
        	
        	e.setCancelled(true);
        }
	}
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		if(item.getItemMeta() != null && item.getItemMeta().getDisplayName().equals("§aStats Menu §7(Right Click)")) {
			e.setCancelled(true);
		}
	}
}

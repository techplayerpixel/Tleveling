package me.hahulala.TowerLeveling.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftingTable implements Listener{
	public static Map<ItemStack,Inventory> craftingList = new HashMap<ItemStack,Inventory>();
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getView().getTitle().equals("§7Crafting Table")) {
			if(e.getClickedInventory().getType() != InventoryType.PLAYER) {
				if((e.getSlot()!= 10 && e.getSlot()!= 11 && e.getSlot()!= 12
						&& e.getSlot()!= 19 && e.getSlot()!= 20 && e.getSlot()!= 21
						&& e.getSlot()!= 28 && e.getSlot()!= 29 && e.getSlot()!= 30)) {
					
					if(e.getSlot() == 23) {
						if(e.getCurrentItem() !=null && e.getCurrentItem().getType() != Material.BARRIER) {
							e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
							Player p = (Player)e.getWhoClicked();
							MenuOpen.OpenCraftingTable(p);
						}
					}
					
					e.setCancelled(true);
				}else {
					if(craftingList.keySet() !=null) {
						for(Inventory inv : craftingList.values()) {
							if(e.getClickedInventory().getContents() == inv.getContents()) {
								for(ItemStack i : craftingList.keySet()) {
									if(craftingList.get(i) == inv) {
										e.getClickedInventory().setItem(23, i);
									}
								}
							}
						}
					}
				}
			}
		}
		
		//creating recipes
		if(e.getView().getTitle().equals("§7Add Recipe")  &&  e.getClickedInventory().getType() != InventoryType.PLAYER) {
			if((e.getSlot()!= 10 && e.getSlot()!= 11 && e.getSlot()!= 12
					&& e.getSlot()!= 19 && e.getSlot()!= 20 && e.getSlot()!= 21
					&& e.getSlot()!= 28 && e.getSlot()!= 29 && e.getSlot()!= 30
					&& e.getSlot()!= 23)) {
				if(e.getCurrentItem()!=null && e.getCurrentItem().getItemMeta().getDisplayName()!=null) {
					//To set grade of an item as common
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§fSet Item Grade To Common!")) {
						if(e.getClickedInventory().getItem(23)!=null && e.getClickedInventory().getItem(23).getItemMeta()!=null) {
							ItemStack item = changeItemGrade(e.getClickedInventory().getItem(23),"COMMON");
							e.getClickedInventory().setItem(23, item);
						}
					}
					//To set grade of an item as uncommon
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSet Item Grade To UnCommon!")) {
						if(e.getClickedInventory().getItem(23)!=null && e.getClickedInventory().getItem(23).getItemMeta()!=null) {
							ItemStack item = changeItemGrade(e.getClickedInventory().getItem(23),"UNCOMMON");
							e.getClickedInventory().setItem(23, item);
						}
					}
					//RARE
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE+"Set Item Grade To Rare!")) {
						if(e.getClickedInventory().getItem(23)!=null && e.getClickedInventory().getItem(23).getItemMeta()!=null) {
							ItemStack item = changeItemGrade(e.getClickedInventory().getItem(23),"RARE");
							e.getClickedInventory().setItem(23, item);
						}
					}
					//UNIQUE
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE+"Set Item Grade To Unique!")) {
						if(e.getClickedInventory().getItem(23)!=null && e.getClickedInventory().getItem(23).getItemMeta()!=null) {
							ItemStack item = changeItemGrade(e.getClickedInventory().getItem(23),"UNIQUE");
							e.getClickedInventory().setItem(23, item);
						}
					}
					//EPIC
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED+"Set Item Grade To Epic!")) {
						if(e.getClickedInventory().getItem(23)!=null && e.getClickedInventory().getItem(23).getItemMeta()!=null) {
							ItemStack item = changeItemGrade(e.getClickedInventory().getItem(23),"EPIC");
							e.getClickedInventory().setItem(23, item);
						}
					}
					//LEGENDARY
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD+"Set Item Grade To Legendary!")) {
						if(e.getClickedInventory().getItem(23)!=null && e.getClickedInventory().getItem(23).getItemMeta()!=null) {
							ItemStack item = changeItemGrade(e.getClickedInventory().getItem(23),"LEGENDARY");
							e.getClickedInventory().setItem(23, item);
						}
					}
					//removing attributes
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cRemove attributes of items")) {
						if(e.getClickedInventory().getItem(23)!=null) {
							e.getClickedInventory().getItem(23).getItemMeta().addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
						}
					}
					//saving a recipe
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCreate Recipe")) {
						if(e.getClickedInventory().getItem(23)!=null) {
							e.getClickedInventory().setItem(53, MainGui.nullgray);
							e.getClickedInventory().setItem(44, MainGui.nullgray);
							e.getClickedInventory().setItem(46, MainGui.nullgray);
							e.getClickedInventory().setItem(47, MainGui.nullgray);
							e.getClickedInventory().setItem(48, MainGui.nullgray);
							e.getClickedInventory().setItem(49, MainGui.nullgray);
							e.getClickedInventory().setItem(50, MainGui.nullgray);
							e.getClickedInventory().setItem(51, MainGui.nullgray);
							ItemStack recipefor = e.getInventory().getItem(23);
							e.getInventory().setItem(23, MainGui.Barrier);
							int check = 0;
							if(craftingList.values() !=null) {
								for(Inventory inv : craftingList.values()) {
									if(e.getClickedInventory().getContents() == inv.getContents()) {
										check = check+1;
									}
								}
							}
							if(check > 0) {
								e.getWhoClicked().sendMessage(ChatColor.RED+"There is already an item with this recipe!");
							}else {
								if(check == 0) {
									craftingList.put(recipefor, e.getClickedInventory());
								}
							}
							e.getWhoClicked().closeInventory();
						}
					}
				}
				
				e.setCancelled(true);
			}
		}
	}
	public static ItemStack changeItemGrade(ItemStack item, String ItemGrade) {
		ItemStack Item = item;
		ItemMeta meta =Item.getItemMeta();
		if(meta.getLore()!=null) {
			if(meta.getLore().get(meta.getLore().size()-1) != "COMMON" || meta.getLore().get(meta.getLore().size()-1) != "UNCOMMON"
					|| meta.getLore().get(meta.getLore().size()-1) != "RARE" || meta.getLore().get(meta.getLore().size()-1) != "UNIQUE"
					|| meta.getLore().get(meta.getLore().size()-1) != "EPIC" || meta.getLore().get(meta.getLore().size()-1) != "LEGENDARY") {
				List<String> lore = meta.getLore();
				lore.remove(lore.size()-1);
				if(ItemGrade == "COMMON") {
					lore.add(ChatColor.WHITE+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.WHITE + (n));
				}
				if(ItemGrade == "UNCOMMON") {
					lore.add(ChatColor.GREEN+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.GREEN + (n));
				}
				if(ItemGrade == "RARE") {
					lore.add(ChatColor.BLUE+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.BLUE + (n));
				}
				if(ItemGrade == "UNIQUE") {
					lore.add(ChatColor.DARK_PURPLE+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.DARK_PURPLE + (n));
				}
				if(ItemGrade == "EPIC") {
					lore.add(ChatColor.RED+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.RED + (n));
				}
				if(ItemGrade == "LEGENDARY") {
					lore.add(ChatColor.GOLD+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.GOLD + (n));
				}
				meta.setLore(lore);
				Item.setItemMeta(meta);
				return Item;
			}else {
				List<String> lore = meta.getLore();
				if(ItemGrade == "COMMON") {
					lore.add(ChatColor.WHITE+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.WHITE + (n));
				}
				if(ItemGrade == "UNCOMMON") {
					lore.add(ChatColor.GREEN+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.GREEN + (n));
				}
				if(ItemGrade == "RARE") {
					lore.add(ChatColor.BLUE+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.BLUE + (n));
				}
				if(ItemGrade == "UNIQUE") {
					lore.add(ChatColor.DARK_PURPLE+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.DARK_PURPLE + (n));
				}
				if(ItemGrade == "EPIC") {
					lore.add(ChatColor.RED+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.RED + (n));
				}
				if(ItemGrade == "LEGENDARY") {
					lore.add(ChatColor.GOLD+ItemGrade);
					String n = meta.getDisplayName();
					meta.setDisplayName(ChatColor.GOLD + (n));
				}
				meta.setLore(lore);
				Item.setItemMeta(meta);
				return Item;
			}
		}else {
			List<String> lore = new ArrayList<>();
			if(ItemGrade == "COMMON") {
				lore.add(ChatColor.WHITE+ItemGrade);
				String n = meta.getDisplayName();
				meta.setDisplayName(ChatColor.WHITE + (n));
			}
			if(ItemGrade == "UNCOMMON") {
				lore.add(ChatColor.GREEN+ItemGrade);
				String n = meta.getDisplayName();
				meta.setDisplayName(ChatColor.GREEN + (n));
			}
			if(ItemGrade == "RARE") {
				lore.add(ChatColor.BLUE+ItemGrade);
				String n = meta.getDisplayName();
				meta.setDisplayName(ChatColor.BLUE + (n));
			}
			if(ItemGrade == "UNIQUE") {
				lore.add(ChatColor.DARK_PURPLE+ItemGrade);
				String n = meta.getDisplayName();
				meta.setDisplayName(ChatColor.DARK_PURPLE + (n));
			}
			if(ItemGrade == "EPIC") {
				lore.add(ChatColor.RED+ItemGrade);
				String n = meta.getDisplayName();
				meta.setDisplayName(ChatColor.RED + (n));
			}
			if(ItemGrade == "LEGENDARY") {
				lore.add(ChatColor.GOLD+ItemGrade);
				String n = meta.getDisplayName();
				meta.setDisplayName(ChatColor.GOLD + (n));
			}
			meta.setLore(lore);
			Item.setItemMeta(meta);
			return Item;
		}
	}
}

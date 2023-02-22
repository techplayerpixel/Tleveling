package me.hahulala.TowerLeveling.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.hahulala.TowerLeveling.HashMaps;

public class MainGui {
	//Items
	public static ItemStack menu;
	public static ItemStack nullgray;
	public static ItemStack instantshop;
	public static ItemStack questlog;
	public static ItemStack calendar;
	public static ItemStack craftingtable;
	public static ItemStack closemenu;
	public static ItemStack createrecipe;
	public static ItemStack removeattributes;
	public static ItemStack setGradeCommon;
	public static ItemStack setGradeUnCommon;
	public static ItemStack setGradeRare;
	public static ItemStack setGradeUnique;
	public static ItemStack setGradeEpic;
	public static ItemStack setGradeLegendary;
	public static ItemStack Barrier;
	
	
	//Gui
	
	public static void init() {
		createmenu();
		createMenuNull();
		createMenuTrade();
		createMenuQuest();
		createMenuTable();
		createMenuCalendar();
		createMenuClose();
		createNewRecipe();
		removeAttributes();
		createSetCommonGrade();
		createSetUNCommonGrade();
		createSetRareGrade();
		createSetUniqueGrade();
		createSetEpicGrade();
		createSetLegendaryGrade();
		createBarrier();
	}
	
	public static void createmenu() {
		ItemStack item = new ItemStack(Material.NETHER_STAR,1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§aStats Menu §7(Right Click)");
		List<String> lore = new ArrayList<>();
		lore.add("§7View all your personal info!");
		lore.add("§7Stats, Quests, Progress, etc.");
		lore.add("§eClick to open");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		menu = item;
	}
	public static void createMenuNull() {
		ItemStack gray = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§f");
		gray.setItemMeta(stained_glass_meta);
		nullgray = gray;
	}
	public static ItemStack getPlayerHead(Player p) {
		
		ItemStack item = new ItemStack(Material.PLAYER_HEAD,1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§aYour Profile");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.BLUE + "Level: "+ChatColor.GREEN+HashMaps.Level.get(p.getName()));
		lore.add(ChatColor.GREEN + "XP: " + ChatColor.DARK_AQUA + ("("+HashMaps.currentxp.get(p.getName())+"/"+HashMaps.Maxxp.get(p.getName())+")"));
		lore.add("§7View your equipment, stats");
		lore.add("§7and more!");
		lore.add("   ");
		lore.add("§eClick to veiw your profile!");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		
		item.setItemMeta(meta);
		
		return item;
	}
	public static void createMenuTrade() {
		ItemStack gray = new ItemStack(Material.EMERALD, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§aInstant Shop!");
		List<String> lore = new ArrayList<>();
		lore.add("    ");
		lore.add("§7COMING SOON!");
		stained_glass_meta.setLore(lore);
		
		gray.setItemMeta(stained_glass_meta);
		
		instantshop = gray;
	}
	public static void createMenuQuest() {
		ItemStack gray = new ItemStack(Material.WRITTEN_BOOK, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§aQuest Log");
		List<String> lore = new ArrayList<>();
		lore.add("    ");
		lore.add("§7COMING SOON!");
		stained_glass_meta.setLore(lore);
		gray.setItemMeta(stained_glass_meta);
		questlog = gray;
	}
	public static void createMenuCalendar() {
		ItemStack gray = new ItemStack(Material.CLOCK, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§aCalendar and Events");
		List<String> lore = new ArrayList<>();
		lore.add("    ");
		lore.add("§7COMING SOON!");
		stained_glass_meta.setLore(lore);
		gray.setItemMeta(stained_glass_meta);
		calendar = gray;
	}
	public static void createMenuTable() {
		ItemStack gray = new ItemStack(Material.CRAFTING_TABLE, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§aCrafting Table");
		List<String> lore = new ArrayList<>();
		lore.add("    ");
		lore.add("§7COMING SOON!");
		stained_glass_meta.setLore(lore);
		gray.setItemMeta(stained_glass_meta);
		craftingtable = gray;
	}
	public static void createMenuClose() {
		ItemStack gray = new ItemStack(Material.BARRIER, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§cCLOSE");
		gray.setItemMeta(stained_glass_meta);
		closemenu = gray;
	}
	public static ItemStack createMenuStats(Player p) {
		ItemStack gray = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§cMain Stats");
		List<String> lore = new ArrayList<>();
		lore.add("§7The base stats you get!");
		lore.add("    ");
		Integer health = HashMaps.Health.get(p.getName()).intValue();
		Integer strength = HashMaps.Strength.get(p.getName()).intValue();
		Integer agility = HashMaps.Agility.get(p.getName()).intValue();
		Integer endurance = HashMaps.Endurance.get(p.getName()).intValue();
		Integer luck = HashMaps.Luck.get(p.getName()).intValue();
		Integer mana = HashMaps.Mana.get(p.getName()).intValue();
		lore.add("§c❤Health "+health.toString());
		lore.add("§a❈Endurance " +endurance.toString());
		lore.add("§c❁Strength "+strength.toString());
		lore.add("§f✦Agility "+agility.toString());
		lore.add("§b✎Mana  "+mana.toString());
		lore.add("§6♣Luck "+luck.toString());
		lore.add("   ");
		lore.add("§eClick to know more!");
		stained_glass_meta.setLore(lore);
		stained_glass_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
		gray.setItemMeta(stained_glass_meta);
		return gray;
	}
	public static ItemStack createMenuGoBack(String whereto) {
		ItemStack gray = new ItemStack(Material.ARROW, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§aGo Back");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + whereto);
		stained_glass_meta.setLore(lore);
		stained_glass_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		gray.setItemMeta(stained_glass_meta);
		return gray;
	}
	public static ItemStack createPlayerStrength(Player p) {
		ItemStack gray = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§c❁Strength: "+HashMaps.Strength.get(p.getName()).intValue());
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Strength increases the damage");
		lore.add("  ");
		lore.add(ChatColor.GREEN+"Click to add 1 stat point");
		lore.add(ChatColor.GRAY+"you deal.");
		stained_glass_meta.setLore(lore);
		stained_glass_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		gray.setItemMeta(stained_glass_meta);
		return gray;
	}
	public static ItemStack createPlayerHealth(Player p) {
		ItemStack gray = new ItemStack(Material.APPLE, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§c❤Health: "+HashMaps.Health.get(p.getName()).intValue());
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Health increases the maximum");
		lore.add("  ");
		lore.add(ChatColor.GREEN+"Click to add 1 stat point");
		lore.add(ChatColor.GRAY+"health you can have.");
		stained_glass_meta.setLore(lore);
		gray.setItemMeta(stained_glass_meta);
		return gray;
	}
	public static ItemStack createPlayerAgility(Player p) {
		ItemStack gray = new ItemStack(Material.SUGAR, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§f✦Agility: "+HashMaps.Agility.get(p.getName()).intValue());
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Agility increases your speed.");
		lore.add("  ");
		lore.add(ChatColor.GREEN+"Click to add 1 stat point");
		stained_glass_meta.setLore(lore);
		gray.setItemMeta(stained_glass_meta);
		return gray;
	}
	public static ItemStack createPlayerMana(Player p) {
		ItemStack gray = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§b✎Mana: "+HashMaps.Mana.get(p.getName()).intValue());
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Mana increases your mana pool.");
		lore.add("  ");
		lore.add(ChatColor.GREEN+"Click to add 1 stat point");
		stained_glass_meta.setLore(lore);
		stained_glass_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ENCHANTS);
		gray.setItemMeta(stained_glass_meta);
		return gray;
	}
	public static ItemStack createPlayerEndurance(Player p) {
		ItemStack gray = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§a❈Endurance: "+HashMaps.Endurance.get(p.getName()).intValue());
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Endurance increases you defence.");
		lore.add("  ");
		lore.add(ChatColor.GREEN+"Click to add 1 stat point");
		stained_glass_meta.setLore(lore);
		stained_glass_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		gray.setItemMeta(stained_glass_meta);
		return gray;
	}
	public static ItemStack createPlayerLuck(Player p) {
		ItemStack gray = new ItemStack(Material.STICK, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§6♣Luck: "+HashMaps.Luck.get(p.getName()).intValue());
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Increases your luck.");
		lore.add("  ");
		lore.add(ChatColor.GREEN+"Click to add 1 stat point");
		stained_glass_meta.setLore(lore);
		gray.setItemMeta(stained_glass_meta);
		return gray;
	}
	public static ItemStack createPlayerFreeStats(Player p) {
		ItemStack gray = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
		ItemMeta stained_glass_meta = gray.getItemMeta();
		stained_glass_meta.setDisplayName("§aFree Stats: "+HashMaps.FreeStats.get(p.getName()).toString());
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Use this to increase your stats!");
		stained_glass_meta.setLore(lore);
		gray.setItemMeta(stained_glass_meta);
		return gray;
	}
	public static void createNewRecipe() {
		ItemStack item = new ItemStack(Material.BOOK,1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§aCreate Recipe");
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		createrecipe = item;
	}
	public static void removeAttributes() {
		ItemStack item = new ItemStack(Material.ANVIL,1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§cRemove attributes of items");
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		removeattributes = item;
	}
	public static void createSetCommonGrade() {
		ItemStack item = new ItemStack(Material.BOOK,1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§fSet Item Grade To Common!");
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		setGradeCommon = item;
	}
	public static void createSetUNCommonGrade() {
		ItemStack item = new ItemStack(Material.BOOK,1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§aSet Item Grade To UnCommon!");
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		setGradeUnCommon = item;
	}
	public static void createSetRareGrade() {
		ItemStack item = new ItemStack(Material.BOOK,1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE+"Set Item Grade To Rare!");
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		setGradeRare = item;
	}
	public static void createSetUniqueGrade() {
		ItemStack item = new ItemStack(Material.BOOK,1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE+"Set Item Grade To Unique!");
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		setGradeUnique = item;
	}
	public static void createSetEpicGrade() {
		ItemStack item = new ItemStack(Material.BOOK,1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Set Item Grade To Epic!");
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		setGradeEpic = item;
	}
	public static void createSetLegendaryGrade() {
		ItemStack item = new ItemStack(Material.BOOK,1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Set Item Grade To Legendary!");
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		setGradeLegendary = item;
	}
	public static void createBarrier() {
		ItemStack item = new ItemStack(Material.BARRIER,1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f");
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		Barrier = item;
	}
}

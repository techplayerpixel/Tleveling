package me.hahulala.TowerLeveling;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.hahulala.TowerLeveling.Challenge.OnLeave;
import me.hahulala.TowerLeveling.MobSystem.MobSystemMain;
import me.hahulala.TowerLeveling.SetArea.ArenaCreation;
import me.hahulala.TowerLeveling.SetArea.SetAreaMain;
import me.hahulala.TowerLeveling.SkillSystem.ClickStore;
import me.hahulala.TowerLeveling.StatCmd.StatCmdAgility;
import me.hahulala.TowerLeveling.StatCmd.StatCmdEndurance;
import me.hahulala.TowerLeveling.StatCmd.StatCmdHealth;
import me.hahulala.TowerLeveling.StatCmd.StatCmdLuck;
import me.hahulala.TowerLeveling.StatCmd.StatCmdStrength;
import me.hahulala.TowerLeveling.gui.CraftingTable;
import me.hahulala.TowerLeveling.gui.MainGui;
import me.hahulala.TowerLeveling.gui.MenuClickEvent;
import me.hahulala.TowerLeveling.gui.MenuOpen;
import me.hahulala.TowerLeveling.gui.OpenCraftingSettings;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class TowerLeveling extends JavaPlugin{
	@Override
	public void onEnable() {
		
		//Configb
		this.saveDefaultConfig();
		if(this.getConfig().contains("PlayerData")) {
			restoreConfig();
		}
		
		//Listeners
		Bukkit.getPluginManager().registerEvents((Listener)new HashMaps(this), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new EnduranceFunction(), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new MobSystemMain(this), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new MenuOpen(), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new MenuClickEvent(), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new OnLeave(), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new ClickStore(), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new CraftingTable(), (Plugin)this);
		
		//items and interference loader
		MainGui.init();
		
		//Commands
		this.getCommand("mana").setExecutor(new Commands());
		this.getCommand("agility").setExecutor(new StatCmdAgility());
		this.getCommand("health").setExecutor(new StatCmdHealth());
		this.getCommand("strength").setExecutor(new StatCmdStrength());
		this.getCommand("endurance").setExecutor(new StatCmdEndurance());
		this.getCommand("luck").setExecutor(new StatCmdLuck());
		this.getCommand("createarea").setExecutor(new SetAreaMain());
		this.getCommand("area").setExecutor(new SetAreaMain());
		this.getCommand("createarena").setExecutor(new ArenaCreation(this));
		this.getCommand("arenarange").setExecutor(new ArenaCreation(this));
		this.getCommand("arenalocout").setExecutor(new ArenaCreation(this));
		this.getCommand("challenge").setExecutor(new ArenaCreation(this));
		this.getCommand("opencs").setExecutor(new OpenCraftingSettings());
		
		//armortand
		List<World> wlist = Bukkit.getServer().getWorlds();
		for(World w : wlist) {
			List<Entity> es = w.getEntities();
			int zm = 0;
			int sl = 0;
			int ar = 0;
			for(Entity e : es) {
				if(e instanceof Zombie) {
					Zombie z = (Zombie)e;
					z.setHealth(0);
					zm = zm+1;
				}
			}
			for(Entity e : es) {
				if(e instanceof Slime) {
					Slime s = (Slime)e;
					s.setHealth(0);
					sl = sl+1;
				}
			}
			for(Entity e : es) {
				if(e instanceof ArmorStand) {
					ArmorStand as = (ArmorStand) e;
					if(as.getCustomName()!=null && ((as.getCustomName().contains("❤")||(as.getCustomName().contains(ChatColor.RED+"❤"))))) {
						as.setHealth(0);
						ar = ar+1;
					}
				}
			}
			String sendmsg = ChatColor.BLUE+"[TowerLeveling]" +ChatColor.GREEN+"Cleared -> Zombie:"+ChatColor.RED+zm+ChatColor.GREEN+ " Slimes:"+ChatColor.RED+sl+ChatColor.GREEN+" Name Stands:" +ChatColor.RED+ar+ChatColor.GREEN+ (" in world "+w.getName());
			Bukkit.getServer().getConsoleSender().sendMessage(sendmsg);
		}
		
		
		//LOOP MAIN FUNCTIONS
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				//removing extra armorstand
				
				//for name adjesting
				List<World> wlist = Bukkit.getServer().getWorlds();
				for(World w : wlist) {
					List<Entity> es = w.getEntities();
					for(Entity e : es) {
						if(e instanceof LivingEntity) {
							LivingEntity en = (LivingEntity)e;
							UpdateName(en);
						}
					}
					for(Entity as : es) {
						if(as instanceof ArmorStand) {
							if(as.getCustomName()!=null&&((as.getCustomName().contains("❤"))||(as.getCustomName().contains(ChatColor.RED+"❤")))&&!(MobSystemMain.MobSt.containsKey(as))) {
								((ArmorStand) as).setHealth(0);
							}
							ArmorStand a = (ArmorStand)as;
							if(MobSystemMain.MobSt!=null &&MobSystemMain.MobSt.get(a)!=null && MobSystemMain.MobSt.get(a).isDead()) {
								a.remove();
							}
						}
					}
					for(Entity sl : es) {
						if(sl instanceof Slime) {
							Slime s = (Slime)sl;
							if((!(s.hasAI()) || s.isInvulnerable())&&!(MobSystemMain.MobSl!=null&&MobSystemMain.MobSl.containsValue(s))) {
								s.setHealth(0);
							}
							if(MobSystemMain.getms!=null&&MobSystemMain.getms.get(s)!=null &&MobSystemMain.getms.get(s).isDead()) {
								s.setHealth(0);
							}
						}
					}
				}
				
				//for players
				Bukkit.getOnlinePlayers().forEach(p -> {
					//check menu
					if(p.getInventory().getItem(8) ==null) {
						p.getInventory().setItem(8, MainGui.menu);
					}
					//Click store system
					if(ClickStore.lastClicked != null&& ClickStore.lastClicked.containsKey(p.getName())&& ClickStore.lastClicked.get(p.getName()) !=null&&ClickStore.lastClicked.get(p.getName()).size() >3) {
						ClickStore.lastClicked.get(p.getName()).removeLast();
					}
					//Reseting clicks
					int nowt = (int)System.currentTimeMillis();
					if(ClickStore.lastClicked != null&& ClickStore.lastClicked.containsKey(p.getName())&& ClickStore.lastClicked.get(p.getName()) !=null) {
						if((nowt-ClickStore.lastUpdateTime.get(p.getName()))>2000) {
							ClickStore.lastClicked.get(p.getName()).clear();
						}
					}
					Integer health = (int) p.getHealth();
					//HEALTH FUNCTION
					p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(HashMaps.Health.get(p.getName()));
					//DISPLAY BAR FUNCTION
					
					if(ClickStore.lastClicked != null&& ClickStore.lastClicked.containsKey(p.getName())&& ClickStore.lastClicked.get(p.getName()) !=null &&ClickStore.lastClicked.get(p.getName()).size() > 1) {
						String[] lastClicks = ClickStore.lastClicked.get(p.getName()).toArray(new String[0]);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED+ health.toString()+"§c/"+ ChatColor.RED +HashMaps.Health.get(p.getName()).intValue()+"§c❤"+"        "+ChatColor.RED+(String.join("-", lastClicks))+"           " +ChatColor.BLUE+HashMaps.ManaReg.get(p.getName()).intValue()+"§9/"+ChatColor.BLUE+HashMaps.Mana.get(p.getName()).intValue()+"§9 ✎Mana"));
					}else {
						if(HashMaps.xpadded.containsKey(p.getName())) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED+ health.toString()+"§c/"+ ChatColor.RED +HashMaps.Health.get(p.getName()).intValue()+"§c❤"+"      "+ChatColor.DARK_AQUA+HashMaps.xpadded.get(p.getName())+"      " +ChatColor.BLUE+HashMaps.ManaReg.get(p.getName()).intValue()+"§9/"+ChatColor.BLUE+HashMaps.Mana.get(p.getName()).intValue()+"§9 ✎Mana"));
						}else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED+ health.toString()+"§c/"+ ChatColor.RED +HashMaps.Health.get(p.getName()).intValue()+"§c❤"+"      "+ChatColor.GREEN + HashMaps.Endurance.get(p.getName()).intValue() +"§a ❈Endurance"+"      " +ChatColor.BLUE+HashMaps.ManaReg.get(p.getName()).intValue()+"§9/"+ChatColor.BLUE+HashMaps.Mana.get(p.getName()).intValue()+"§9 ✎Mana"));
						}
					}
					//AGILITY FUNCTION
					if(HashMaps.Agility.containsKey(p.getName())) {
						Double base = HashMaps.Agility.get(p.getName());
						Double newvalue = (((base-10)/1000)+0.1);
						p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(newvalue);
					}
					//STRENGTH FUNCTION
					if(HashMaps.Strength.containsKey(p.getName())) {
						Double baseStrength = HashMaps.Strength.get(p.getName());
						Double strength = (((baseStrength-20)/50)+2.0);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(strength);
					}
					//MANA FUNCTION
					if((HashMaps.ManaReg.get(p.getName())) < (HashMaps.Mana.get(p.getName()))) {
						Integer base = HashMaps.ManaReg.get(p.getName());
						HashMaps.ManaReg.remove(p.getName());
						HashMaps.ManaReg.put(p.getName(), base+1);
					}
					if((HashMaps.ManaReg.get(p.getName())) > (HashMaps.Mana.get(p.getName()))) {
						Integer base = HashMaps.Mana.get(p.getName());
						HashMaps.ManaReg.remove(p.getName());
						HashMaps.ManaReg.put(p.getName(), base);
					}
					//LEVEL SYSTEM
					if(HashMaps.currentxp.get(p.getName()) >= HashMaps.Maxxp.get(p.getName())) {
						if(HashMaps.currentxp.get(p.getName()) == HashMaps.Maxxp.get(p.getName())) {
							Integer baselevel = HashMaps.Level.get(p.getName());
							Integer newLevel = (baselevel+1);
							Double newmaxxp = (double) ((20 * ((newLevel - 1)*(newLevel-1))) + (20 * ((newLevel-1)*(newLevel-1))) + 10);
							HashMaps.Level.remove(p.getName());
							HashMaps.Level.put(p.getName(), newLevel);
							HashMaps.currentxp.remove(p.getName());
							HashMaps.currentxp.put(p.getName(), 0);
							HashMaps.Maxxp.remove(p.getName());
							HashMaps.Maxxp.put(p.getName(), newmaxxp.intValue());
							Integer basefreestatpoints = HashMaps.FreeStats.get(p.getName());
							HashMaps.FreeStats.remove(p.getName());
							HashMaps.FreeStats.put(p.getName(), basefreestatpoints+3);
							p.sendMessage(ChatColor.GREEN+"You have been awarded"+ChatColor.RED+" 3 "+ChatColor.GREEN+"Stat Points for leveling up!");
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 2);
						}
						if(HashMaps.currentxp.get(p.getName()) > HashMaps.Maxxp.get(p.getName())) {
							Integer baselevel = HashMaps.Level.get(p.getName());
							Integer basemax = HashMaps.Maxxp.get(p.getName());
							Integer newLevel = (baselevel+1);
							Double newmaxxp = (double)((20 * ((newLevel - 1)*(newLevel-1))) + (20 * ((newLevel-1)*(newLevel-1))) + 10);
							Integer basecurrent = HashMaps.currentxp.get(p.getName());
							HashMaps.Level.remove(p.getName());
							HashMaps.Level.put(p.getName(), newLevel);
							HashMaps.Maxxp.remove(p.getName());
							HashMaps.Maxxp.put(p.getName(), newmaxxp.intValue());
							HashMaps.currentxp.remove(p.getName());
							HashMaps.currentxp.put(p.getName(), basecurrent-basemax);
							Integer basefreestatpoints = HashMaps.FreeStats.get(p.getName());
							HashMaps.FreeStats.remove(p.getName());
							HashMaps.FreeStats.put(p.getName(), basefreestatpoints+3);
							p.sendMessage(ChatColor.GREEN+"You have been awarded"+ChatColor.RED+" 3 "+ChatColor.GREEN+"Stat Points for leveling up!");
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 2, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 2);
						}
					}
				});
			}
			
		}, 0, 1L);
		
	}
	@Override
	public void onDisable() {
        if(!HashMaps.Mana.isEmpty()) {
			saveData();
		}
	}
	public void saveData() {
		for(Map.Entry<String, Double> player : HashMaps.Agility.entrySet()) {
			this.getConfig().set("PlayerData.Agility." + player.getKey(), player.getValue());
		}
		for(Map.Entry<String, Double> player : HashMaps.Strength.entrySet()) {
			this.getConfig().set("PlayerData.Strength." + player.getKey(), player.getValue());
		}
		for(Map.Entry<String, Double> player : HashMaps.Endurance.entrySet()) {
			this.getConfig().set("PlayerData.Endurance." + player.getKey(), player.getValue());
		}
		for(Map.Entry<String, Double> player : HashMaps.Health.entrySet()) {
			this.getConfig().set("PlayerData.Health." + player.getKey(), player.getValue());
		}
		for(Map.Entry<String, Integer> player : HashMaps.Mana.entrySet()) {
			this.getConfig().set("PlayerData.Mana." + player.getKey(), player.getValue());
		}
		for(Map.Entry<String, Integer> player : HashMaps.ManaReg.entrySet()) {
			this.getConfig().set("PlayerData.ManaReg." + player.getKey(), player.getValue());
		}
		for(Map.Entry<String, Double> player : HashMaps.Luck.entrySet()) {
			this.getConfig().set("PlayerData.Luck." + player.getKey(), player.getValue());
		}
		for(Map.Entry<String, Integer> player : HashMaps.Level.entrySet()) {
			this.getConfig().set("PlayerData.Level." + player.getKey(), player.getValue());
		}
		for(Map.Entry<String, Integer> player : HashMaps.currentxp.entrySet()) {
			this.getConfig().set("PlayerData.CurrentXP." + player.getKey(), player.getValue());
		}
		for(Map.Entry<String, Integer> player : HashMaps.Maxxp.entrySet()) {
			this.getConfig().set("PlayerData.MaxXP." + player.getKey(), player.getValue());
		}
		for(Map.Entry<String, Integer> player : HashMaps.FreeStats.entrySet()) {
			this.getConfig().set("PlayerData.FreeStats." + player.getKey(), player.getValue());
		}
		this.saveConfig();
		
	}
	public void restoreConfig() {
		this.getConfig().getConfigurationSection("PlayerData.Agility.").getKeys(false).forEach(key->{
			Double agi = ((Double)this.getConfig().get("PlayerData.Agility." + key));
			HashMaps.Agility.put(key, agi);
		});
		this.getConfig().getConfigurationSection("PlayerData.Strength.").getKeys(false).forEach(key->{
			Double agi = ((Double)this.getConfig().get("PlayerData.Strength." + key));
			HashMaps.Strength.put(key, agi);
		});
		this.getConfig().getConfigurationSection("PlayerData.Health.").getKeys(false).forEach(key->{
			Double agi = ((Double)this.getConfig().get("PlayerData.Health." + key));
			HashMaps.Health.put(key, agi);
		});
		this.getConfig().getConfigurationSection("PlayerData.Endurance.").getKeys(false).forEach(key->{
			Double agi = ((Double)this.getConfig().get("PlayerData.Endurance." + key));
			HashMaps.Endurance.put(key, agi);
		});
		this.getConfig().getConfigurationSection("PlayerData.Luck.").getKeys(false).forEach(key->{
			Double agi = ((Double)this.getConfig().get("PlayerData.Luck." + key));
			HashMaps.Luck.put(key, agi);
		});
		this.getConfig().getConfigurationSection("PlayerData.Mana.").getKeys(false).forEach(key->{
			Integer agi = ((Integer)this.getConfig().get("PlayerData.Mana." + key));
			HashMaps.Mana.put(key, agi);
		});
		this.getConfig().getConfigurationSection("PlayerData.ManaReg.").getKeys(false).forEach(key->{
			Integer agi = ((Integer)this.getConfig().get("PlayerData.ManaReg." + key));
			HashMaps.ManaReg.put(key, agi);
		});
		this.getConfig().getConfigurationSection("PlayerData.Level.").getKeys(false).forEach(key->{
			Integer agi = ((Integer)this.getConfig().get("PlayerData.Level." + key));
			HashMaps.Level.put(key, agi);
		});
		this.getConfig().getConfigurationSection("PlayerData.CurrentXP.").getKeys(false).forEach(key->{
			Integer agi = ((Integer)this.getConfig().get("PlayerData.CurrentXP." + key));
			HashMaps.currentxp.put(key, agi);
		});
		this.getConfig().getConfigurationSection("PlayerData.MaxXP.").getKeys(false).forEach(key->{
			Integer agi = ((Integer)this.getConfig().get("PlayerData.MaxXP." + key));
			HashMaps.Maxxp.put(key, agi);
		});
		this.getConfig().getConfigurationSection("PlayerData.FreeStats.").getKeys(false).forEach(key->{
			Integer agi = ((Integer)this.getConfig().get("PlayerData.FreeStats." + key));
			HashMaps.FreeStats.put(key, agi);
		});
	}
	public void UpdateName(LivingEntity e) {
		if(MobSystemMain.MobName.containsKey(e.getUniqueId().toString())) {
			String[] namelist = MobSystemMain.MobName.get(e.getUniqueId().toString()).split(" ",-2);
			if(namelist!= null && namelist[0] != null) {
				if(namelist.length == 3) {
					int Health = (int) e.getHealth();
					int maxHealth = (int) e.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
					String healthadd = ChatColor.RED+(Health+"/"+maxHealth+"❤");
					String name = (namelist[0]+" "+namelist[1]+" "+healthadd);
					MobSystemMain.MobName.remove(e.getUniqueId().toString());
					MobSystemMain.MobName.put(e.getUniqueId().toString(), name);
					MobSystemMain.MobS.get(e.getUniqueId().toString()).setCustomName(name);
				}
			}
		}
	}
}

package me.hahulala.TowerLeveling.SetArea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.hahulala.TowerLeveling.TowerLeveling;
import me.hahulala.TowerLeveling.MobSystem.SummonTrialBoss;


public class ArenaCreation implements CommandExecutor{
	
	TowerLeveling plugin;
	
	public ArenaCreation(TowerLeveling plugin) {
		this.plugin = plugin;
	}
	
	public static List<String> Arenas = new ArrayList<String>();
	public static Map<String,String> arworld = new HashMap<String,String>();
	public static Map<String,Location> ArLoc = new HashMap<String,Location>();
	public static Map<String,Integer> ArenaRange = new HashMap<String,Integer>();
	public static Map<String,ArmorStand> ArStand = new HashMap<String,ArmorStand>();
	public static Map<String,Location> ArLocout = new HashMap<String,Location>();
	public static Map<String,String> aroccu = new HashMap<String,String>();
	public static Map<String,Player> arenausing = new HashMap<String,Player>();
	public static Map<Player,String> arenausingp = new HashMap<Player,String>();
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
			sender.sendMessage(ChatColor.RED+"Only Players can use this command!");
		Player p =(Player) sender;
		if(!(p.hasPermission("tl.use")))
			p.sendMessage(ChatColor.RED+"You dont have Permission to use this command!");
		if(label.equalsIgnoreCase("CreateArena")){
			if(args[0] !=null) {
				if(!(Arenas.contains("areastand: "+args[0]))) {
					//seting up the armorstand
					ArmorStand am = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
					am.setVisible(false);
					am.isInvulnerable();
					am.setCustomName("areastand: "+ args[0]);
					am.setCustomNameVisible(false);
					
					Arenas.add(args[0]);
					arworld.put("areastand: "+args[0], am.getWorld().toString());
					ArLoc.put("areastand: "+args[0], am.getLocation());
					ArLocout.put("areastand: "+args[0], am.getLocation());
					ArenaRange.put("areastand: "+args[0], 0);
					ArStand.put("areastand: "+args[0], am);
					aroccu.put("areastand: "+args[0], "false");
					
					p.sendMessage(ChatColor.GREEN+"Arena named " + ChatColor.RED +args[0] + ChatColor.GREEN+" has beed created!");
					
				}else {
					p.sendMessage(ChatColor.RED+"There is already a arena named: "+ChatColor.BLUE+args[0]);
				}
			}
		}
		if(label.equalsIgnoreCase("ArenaRange")) {
			if(args[0] !=null && args[1] != null) {
				if(Arenas.contains(args[0])) {
					int range = Integer.parseInt(args[1]);
					ArenaRange.remove("areastand: "+args[0]);
					ArenaRange.put("areastand: "+args[0], range);
					p.sendMessage(ChatColor.GREEN+"The range of Arena "+ChatColor.RED + args[0]+ ChatColor.GREEN+"has been set to: "+ChatColor.RED+range);
				}
			}
		}
		if(label.equalsIgnoreCase("ArenaLocOut")) {
			if(args[0] !=null) {
				if(Arenas.contains(args[0])) {
					ArLocout.remove("areastand: "+args[0]);
					ArLocout.put("areastand: "+args[0], p.getLocation());
					p.sendMessage(ChatColor.GREEN+"Your location has been set as the exit!");
				}
			}
		}
		if(label.equals("challenge")) {
			if(args[0] !=null && Arenas.contains(args[0])) {
				if((!(aroccu.isEmpty()))&& aroccu.containsKey("areastand: "+args[0]) && aroccu.get("areastand: "+args[0]).equals("false")) {
					aroccu.remove("areastand: "+args[0]);
					aroccu.put("areastand: "+args[0], "true");
					arenausing.put("areastand: "+args[0], p);
					arenausingp.put(p, "areastand: "+args[0]);
					p.teleport(ArLoc.get("areastand: "+args[0]));
					p.sendMessage(ChatColor.GREEN+"Please prepare for fight, 1st round will begin in "+ChatColor.RED+"5secs.");
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							if(arenausing.containsKey("areastand: "+args[0])) {
								p.sendMessage(ChatColor.RED+"5");
								p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 2);
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
										if(arenausing.containsKey("areastand: "+args[0])) {
											p.sendMessage(ChatColor.RED+"4");
											p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 2);
											Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
												@Override
												public void run() {
													if(arenausing.containsKey("areastand: "+args[0])) {
														p.sendMessage(ChatColor.RED+"3");
														p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 2);
														Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
															@Override
															public void run() {
																if(arenausing.containsKey("areastand: "+args[0])) {
																	p.sendMessage(ChatColor.RED+"2");
																	p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 2);
																	Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
																		@Override
																		public void run() {
																			if(arenausing.containsKey("areastand: "+args[0])) {
																				p.sendMessage(ChatColor.RED+"1");
																				p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 2);
																				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
																					@Override
																					public void run() {
																						if(arenausing.containsKey("areastand: "+args[0])) {
																							p.sendMessage(ChatColor.RED+"Undead Soilder has been summoned!");
																							SummonTrialBoss.summonUS(ArLoc.get("areastand: "+args[0]));
																							p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 2, 2);
																						}
																						
																					}
																				},20L);
																			}
																			
																		}
																	},20L);
																}
																
															}
														},20L);
													}
													
												}
											},20L);
										}
										
									}
								},20L);
							}
							
						}
					},20L);
				}else {
					p.sendMessage(ChatColor.RED+"There is already someone using the arena!");
				}
			}else {
				p.sendMessage("Area ka naam toh te madar!");
			}
		}
		
		
		return false;
	}
	public static boolean inArena(Entity entity,String arena) {
		if(Arenas.contains(arena)) {
			if(entity.getWorld().getEntities().contains(ArStand.get("areastand: "+arena))) {
				if(ArStand.get("areastand: "+arena).getNearbyEntities(ArenaRange.get("areastand: "+arena), ArenaRange.get("areastand: "+arena), ArenaRange.get("areastand: "+arena)).contains(entity)) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	public static boolean isAreaOccupied(Player p,String area) {
		
		return false;
	}
}

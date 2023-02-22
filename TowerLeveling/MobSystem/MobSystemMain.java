package me.hahulala.TowerLeveling.MobSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.Plugin;

import me.hahulala.TowerLeveling.HashMaps;
import me.hahulala.TowerLeveling.TowerLeveling;

public class MobSystemMain implements Listener
{
    TowerLeveling plugin;
    
    public static Map<String,Integer> MobLevel = new HashMap<String,Integer>();
    public static Map<String,String> MobName = new HashMap<String,String>();
    public static Map<String,ArmorStand> MobS = new HashMap<String,ArmorStand>();
    public static Map<String,Slime> MobSl = new HashMap<String,Slime>();
    public static Map<ArmorStand,LivingEntity> MobSt = new HashMap<ArmorStand,LivingEntity>();
    public static Map<Slime,LivingEntity> getms = new HashMap<Slime,LivingEntity>();
    
    public MobSystemMain(final TowerLeveling plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
    	if(e.getEntity().getType().equals(EntityType.ZOMBIE)) {
    		Random rn = new Random();
    		int upperlimit = 3;
    		int out = rn.nextInt(upperlimit);
    		Zombie zm = (Zombie)e.getEntity();
    		List<String> names = new ArrayList<>();
    		int Health = (int) zm.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    		names.add(ChatColor.BLUE+"["+ChatColor.GREEN+"1"+ChatColor.BLUE+"]"+"Zombie "+ChatColor.RED+(Health+"/"+Health+"❤"));
    		names.add(ChatColor.BLUE+"["+ChatColor.GREEN+"2"+ChatColor.BLUE+"]"+"Zombie "+ChatColor.RED+(Health+"/"+Health+"❤"));
    		names.add(ChatColor.BLUE+"["+ChatColor.GREEN+"3"+ChatColor.BLUE+"]"+"Zombie "+ChatColor.RED+(Health+"/"+Health+"❤"));
    		ArmorStand ar = (ArmorStand) zm.getWorld().spawnEntity(zm.getLocation(), EntityType.ARMOR_STAND);
    		Slime s = (Slime) zm.getWorld().spawnEntity(zm.getLocation(), EntityType.SLIME);
    		s.setAI(false);
    		s.setInvulnerable(true);
    		s.setSize(1);
    		s.setInvisible(true);
    		s.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2048);
    		s.setHealth(2048);
    		ar.setCustomName(names.get(out));
    		ar.setCustomNameVisible(true);
    		ar.setGravity(false);
    		ar.setVisible(false);
    		ar.setMarker(true);
    		ar.setSmall(true);
    		zm.addPassenger(s);
    		s.addPassenger(ar);
    		MobName.put(zm.getUniqueId().toString(), names.get(out));
    		MobLevel.put(zm.getUniqueId().toString(), out+1);
    		MobS.put(zm.getUniqueId().toString(),ar);
    		MobSt.put(ar,zm);
    		MobSl.put(zm.getUniqueId().toString(), s);
    		getms.put(s, zm);
    		adjestStats(zm);
    	}
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
    	if(e.getEntity() instanceof LivingEntity) {
    		if(e.getEntity() instanceof Slime) {
    			Slime s = (Slime)e.getEntity();
    			if(MobSl.containsValue(s)) {
    				e.setDamage(0);
    			}
    		}
    	}
    }
    
    
    @EventHandler
    public void onDeath(final EntityDeathEvent e) {
        if (e.getEntity() instanceof Monster) {
            Monster en = (Monster)e.getEntity();
            if(en instanceof Slime) {
            	if(en.isInvulnerable()) {
            		e.getDrops().clear();
            	}
            }
            if (en.getKiller() instanceof Player) {
            	Player p = en.getKiller();
            	if(!(MobLevel.containsKey(en.getUniqueId().toString()))) {
                	giveXP(p,10);
                }else {
                	int level = MobLevel.get(en.getUniqueId().toString());
                	giveXP(p,level*10);
                	ArmorStand as = MobS.get(en.getUniqueId().toString());
                	MobS.remove(en.getUniqueId().toString());
                	MobSt.remove(as);
                	as.remove();
                	Slime s = MobSl.get(en.getUniqueId().toString());
                	MobSl.remove(en.getUniqueId().toString());
                	getms.remove(s);
                	s.setHealth(0);
                	MobLevel.remove(en.getUniqueId().toString());
                	MobName.remove(en.getUniqueId().toString());
                }
            	
            }
        }
    }
    public void giveXP(Player p,Integer amount) {
        Integer basexp = HashMaps.currentxp.get(p.getName());
        HashMaps.currentxp.remove(p.getName());
        HashMaps.currentxp.put(p.getName(), basexp + amount);
        String base = (ChatColor.DARK_AQUA + ("+"+amount+"(" + HashMaps.currentxp.get(p.getName()) + "/" + HashMaps.Maxxp.get(p.getName()) + ")"));
        HashMaps.setXpActionBar(p,base);
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 2);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                HashMaps.setXpBarNull(p);
            }
        }, 40);
    }
    public void adjestStats(LivingEntity e) {
    	if(MobLevel.containsKey(e.getUniqueId().toString())) {
    		int lvl = MobLevel.get(e.getUniqueId().toString());
    		Double basehealth = e.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    		Double basespeed = e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue();
    		Double basedamage = e.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue();
    		e.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(basehealth*(lvl));
    		e.setHealth(basehealth*(lvl));
    		e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(basespeed+(0.2*lvl));
    		e.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(basedamage+(0.5*lvl));
    		e.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(e.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    	}
    }
}
package me.hahulala.TowerLeveling.MobSystem;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

import net.md_5.bungee.api.ChatColor;

public class SummonTrialBoss {
	public static void summonUS(Location place) {
		Zombie z = (Zombie) place.getWorld().spawnEntity(place, EntityType.ZOMBIE);
		z.setCustomName(ChatColor.BLUE+ "Undead Soilder");
		z.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
		z.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(z.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*1.2);
		z.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(2);
		z.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(z.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*4);
		z.setCustomNameVisible(true);
	}
}

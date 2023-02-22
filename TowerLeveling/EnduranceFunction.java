package me.hahulala.TowerLeveling;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EnduranceFunction implements Listener{
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof LivingEntity) {
			if(e.getEntity().getType().equals(EntityType.PLAYER)) {
				Player p = (Player) e.getEntity();
				if(HashMaps.Endurance.containsKey(p.getName())) {
					if(e.getDamager().getType().equals(EntityType.PLAYER)) {
						Player enemy = (Player) e.getDamager();
						if(HashMaps.DefencePen.containsKey(enemy.getName())) {
							Double defpen = HashMaps.DefencePen.get(enemy.getName());
							Double defence = HashMaps.Endurance.get(p.getName());
							Double realdef = defence/(defpen/100);
							Double damage = e.getDamage();
							Double finaldamage = damage/(1+realdef/100);
							e.setDamage(finaldamage);
						}else {
							Double defence = HashMaps.Endurance.get(p.getName());
							Double damage = e.getDamage();
							Double finaldamage = damage/(1+defence/100);
							e.setDamage(finaldamage);
						}
					}else {
						//needed to be
						if(HashMaps.Endurance.containsKey(p.getName())) {
							Double defence = HashMaps.Endurance.get(p.getName());
							Double damage = e.getDamage();
							Double finaldamage = damage/(1+defence/100);
							e.setDamage(finaldamage);
						}
					}
				}
			}
			
		}else {
			if(e.getEntity().getType().equals(EntityType.PLAYER)) {
				Player p = (Player) e.getEntity();
				if(HashMaps.Endurance.containsKey(p.getName())) {
					Double defence = HashMaps.Endurance.get(p.getName());
					Double damage = e.getDamage();
					Double finaldamage = damage/(1+defence/100);
					e.setDamage(finaldamage);
				}
			}
		}
	}
}

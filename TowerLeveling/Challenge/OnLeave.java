package me.hahulala.TowerLeveling.Challenge;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.hahulala.TowerLeveling.SetArea.ArenaCreation;
import net.md_5.bungee.api.ChatColor;

public class OnLeave implements Listener{
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		for(String arena : ArenaCreation.Arenas) {
			if(ArenaCreation.inArena(e.getEntity(), arena)) {
				e.setDeathMessage(ChatColor.RED+"You Have Died!");
				e.getEntity().teleport(ArenaCreation.ArLocout.get("areastand: "+arena));
				ArenaCreation.arenausing.remove("areastand: "+arena);
				ArenaCreation.aroccu.remove("areastand: "+arena);
				ArenaCreation.aroccu.put("areastand: "+arena, "false");
				Player p = e.getEntity();
				ArenaCreation.arenausingp.remove(p);
			}
		}
	}
	@EventHandler
	public void onDisconnect(PlayerQuitEvent e) {
		for(String arena : ArenaCreation.Arenas) {
			if(ArenaCreation.inArena(e.getPlayer(), arena)) {
				e.getPlayer().teleport(ArenaCreation.ArLocout.get("areastand: "+arena));
				ArenaCreation.arenausing.remove("areastand: "+arena);
				ArenaCreation.aroccu.remove("areastand: "+arena);
				ArenaCreation.aroccu.put("areastand: "+arena, "false");
				ArenaCreation.arenausingp.remove(e.getPlayer());
			}
		}
	}
}

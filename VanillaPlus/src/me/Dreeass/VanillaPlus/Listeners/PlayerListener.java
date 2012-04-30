package me.Dreeass.VanillaPlus.Listeners;

import me.Dreeass.VanillaPlus.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
	public static Main plugin;

	public PlayerListener(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(
				ChatColor.GOLD + player.getName() +
				ChatColor.WHITE + " joined the game."
				);
		if(plugin.getConfig().getBoolean("MOTD on login")){
			player.sendMessage(plugin.getConfig().getString("MOTD").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
		}
		else {
			
		}
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		event.setQuitMessage(
				ChatColor.GOLD + player.getName() +
				ChatColor.WHITE + " left the game."
				);
	}
	
	@EventHandler
	public void onPlayerDamage(final EntityDamageEvent event) {
		Entity entity = event.getEntity();
		//Player player = (Player) entity;
	if(entity instanceof Player) {
		Player player = (Player) entity;
		if(Main.godPlayers.contains(player.getName())) {
			event.setCancelled(true);
			}
		else {
			
		}
		}
	if(entity instanceof Player) {
		Player player = (Player) entity;
		if(plugin.teleporters.contains(player.getName())) {
			plugin.teleporters.remove(player.getName());
			player.sendMessage(ChatColor.GRAY + "The teleport has been cancelled because you got damaged.");
		}
		else {
			
		}
	}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if(Main.afkPlayers.contains(player.getName())) {
			Bukkit.broadcastMessage(
					ChatColor.GOLD + player.getName() + ChatColor.WHITE + " has returned."
					);
			Main.afkPlayers.remove(player.getName());
		}
		
		else {
			
		}
		if(plugin.teleporters.contains(player.getName())) {
			plugin.teleporters.remove(player.getName());
			player.sendMessage(ChatColor.GRAY + "The teleport has been cancelled because you moved.");
		}
		else {
			
		}
	}
}
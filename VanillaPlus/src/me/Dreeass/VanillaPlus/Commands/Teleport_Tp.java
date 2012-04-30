package me.Dreeass.VanillaPlus.Commands;

import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport_Tp implements CommandExecutor {
	
	public static Main plugin;

	public Teleport_Tp(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		final Player playerSender = (Player) sender;

		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.teleport")) {
					
				if(args.length == 0) {
						playerSender.sendMessage(ChatColor.RED + "/tp <player> (target)");
						}
					
				if(args.length == 1) {
						final Player playerTarget = playerSender.getServer().getPlayer(args[0]);
						
						if (playerTarget != null) {
							final Location locationPlayerTarget = playerTarget.getLocation();
							if (playerSender.isOp() || playerSender.hasPermission("vanillaplus.teleport.nodelay")) {
								playerSender.teleport(locationPlayerTarget);
								playerSender.sendMessage(
										ChatColor.GRAY + "You teleported to " + 
										ChatColor.GOLD + playerTarget.getName() + 
										ChatColor.GRAY + ".");
								playerTarget.sendMessage(
										ChatColor.GOLD + playerSender.getName() +
										ChatColor.GRAY + " teleported to you.");
								}
							else {
								playerSender.sendMessage(
										ChatColor.GRAY + "You'll get teleport in " +
										ChatColor.GOLD + plugin.getConfig().getInt("Teleport delay") +
										ChatColor.GRAY + " seconds. Don't move!"
										);
								plugin.teleporters.add(playerSender.getName());
									plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
										public void run() {
											if(plugin.teleporters.contains(playerSender.getName())) {
											playerSender.teleport(locationPlayerTarget);
											playerSender.sendMessage(
													ChatColor.GRAY + "You teleported to " + 
													ChatColor.GOLD + playerTarget.getName() + 
													ChatColor.GRAY + ".");
											playerTarget.sendMessage(
													ChatColor.GOLD + playerSender.getName() +
													ChatColor.GRAY + " teleported to you.");
											plugin.teleporters.remove(playerSender.getName());
											}
											else {
											}
										}
									}, plugin.getConfig().getInt("Teleport delay") * 20);
								
							}
						}
						
						else {
							playerSender.sendMessage(
									ChatColor.GRAY + "The target player does not exist or is not online."
									);
						}
						
						}
					
					if(args.length == 2) {
						if(playerSender.hasPermission("vanillaplus.teleport.other")) {
						Player playerTarget1 = playerSender.getServer().getPlayer(args[0]);
						Player playerTarget2 = playerSender.getServer().getPlayer(args[1]);
						if(playerTarget1 != null && playerTarget2 != null) {
							Location locationplayerTarget2 = playerTarget2.getLocation();
							playerTarget1.teleport(locationplayerTarget2);
							playerTarget1.sendMessage(
								ChatColor.GRAY + "You got teleported to " +
								ChatColor.GOLD + playerTarget1.getName() +
								ChatColor.GRAY + " by " +
								ChatColor.GOLD + playerSender.getName() +
								ChatColor.GRAY + "."
								);
							playerTarget2.sendMessage(
									ChatColor.GOLD + playerTarget1.getName() +
									ChatColor.GRAY + " got teleported to you by " +
									ChatColor.GOLD + playerSender.getName() +
									ChatColor.GRAY + "."
									);
							playerSender.sendMessage(
									ChatColor.GRAY + "You teleported " +
									ChatColor.GOLD + playerTarget1.getName() +
									ChatColor.GRAY + " to " +
									ChatColor.GOLD + playerTarget2.getName() + "."
									);
								}
						
						else {
							playerSender.sendMessage(ChatColor.GRAY + "You do not have permission to use this command.");
						}
						
							}
						
						else {
							playerSender.sendMessage(
									ChatColor.GRAY + "One or more of the target players are not online."
									);
							}
						
						}
					}
			
					else  {
					playerSender.sendMessage(
							ChatColor.GRAY + "You do not have permission to use this command."
							);
				}
			
		return false;
	}

}

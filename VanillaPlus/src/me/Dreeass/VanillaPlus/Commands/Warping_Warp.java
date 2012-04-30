package me.Dreeass.VanillaPlus.Commands;


import me.Dreeass.VanillaPlus.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warping_Warp implements CommandExecutor {

	public static Main plugin;
	
	public Warping_Warp(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		final Player playerSender = (Player) sender;
		if (playerSender.isOp() || playerSender.hasPermission("vanillaplus.warp")) {
			if(args.length == 1) {
				final String WarpName = args[0].toString();
				final String WarpNameL = WarpName.toLowerCase();
				if(plugin.WarpsConfig.get(WarpNameL) != null) {
					String x = plugin.WarpsConfig.getString(WarpNameL);
				    final Location l = new Location(null, 0, 0, 0, 0, 0);
						    String[] info = x.split(",");
						    l.setWorld(Bukkit.getWorld(info[0]));
						    l.setX(Double.parseDouble(info[1]));
						    l.setX(Double.parseDouble(info[1]));
						    l.setY(Double.parseDouble(info[2]));
						    l.setZ(Double.parseDouble(info[3]));
						    l.setYaw(Float.parseFloat(info[4]));
						    l.setPitch(Float.parseFloat(info[5]));
					if (playerSender.isOp() || playerSender.hasPermission("vanillaplus.teleport.nodelay")) {
					    playerSender.teleport(l);
					playerSender.sendMessage(
							ChatColor.GRAY + "You teleported to the warp " +
							ChatColor.GOLD + WarpName +
							ChatColor.GRAY + "."
							);
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
								    playerSender.teleport(l);
									playerSender.sendMessage(ChatColor.GRAY + "You teleported to the warp " +
									ChatColor.GOLD + WarpName +
									ChatColor.GRAY + ".");
									plugin.teleporters.remove(playerSender.getName());
								}
								else {
								}
							}
						}, plugin.getConfig().getInt("Teleport delay") * 20);
					}
				}
			else {
				playerSender.sendMessage(ChatColor.GRAY + "The target warp does not exist.");
				}
			}
			if(args.length == 2) {
				if (playerSender.isOp() || playerSender.hasPermission("vanillaplus.warp.other")) {
					String WarpName = args[0].toString();
					String WarpNameL = WarpName.toLowerCase();
					Player playerTarget = plugin.getServer().getPlayer(args[1]);
					if(plugin.WarpsConfig.get(WarpNameL) != null) {

						String x = plugin.WarpsConfig.getString(WarpNameL);
					    final Location l = new Location(null, 0, 0, 0, 0, 0);
							    String[] info = x.split(",");
							    l.setWorld(Bukkit.getWorld(info[0]));
							    l.setX(Double.parseDouble(info[1]));
							    l.setX(Double.parseDouble(info[1]));
							    l.setY(Double.parseDouble(info[2]));
							    l.setZ(Double.parseDouble(info[3]));
							    l.setYaw(Float.parseFloat(info[4]));
							    l.setPitch(Float.parseFloat(info[5]));
					
					if(playerTarget != null) {
						if (playerTarget == playerSender) {
							playerSender.teleport(l);
							playerSender.sendMessage(
									ChatColor.GRAY + "You teleported to the warp " + ChatColor.GOLD + WarpName + ChatColor.GRAY + "."
									);
						}
						else {
							playerTarget.teleport(l);
							playerSender.sendMessage(
									ChatColor.GRAY + "You teleported " +
									ChatColor.GOLD + playerTarget.getName() +
									ChatColor.GRAY + " to the warp " +
									ChatColor.GOLD + WarpName +
									ChatColor.GRAY + "."
									);
							playerTarget.sendMessage(
									ChatColor.GRAY + "You got teleport to the warp " +
									ChatColor.GOLD + WarpName +
									ChatColor.GRAY + " by " +
									ChatColor.GOLD + playerSender.getName()
									);
						}
					}
					
					else {
						playerSender.sendMessage(ChatColor.GRAY + "The target player is not online or does not exist.");
					}
					
				}
				
				else {
					if(playerTarget != null) {
						playerSender.sendMessage(ChatColor.GRAY + "The target warp does not exist.");
					}
					else {
						playerSender.sendMessage(ChatColor.GRAY + "The target warp and player do not exist.");
					}
				}
			}
			}
			if(args.length == 0) {
				playerSender.sendMessage(ChatColor.RED + "/warp <name>");
			}
		}
		else {
			playerSender.sendMessage(ChatColor.GRAY + "You do not have permissions to use this command.");
		}
		
		
		return false;
	}

}

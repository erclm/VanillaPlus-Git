package me.Dreeass.VanillaPlus.Commands;


import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warping_Setwarp implements CommandExecutor {
	
	public static Main plugin;
	
	public Warping_Setwarp(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;
		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.warp.set")) {
			
			if(args.length == 1) {

				String WarpName = args[0].toString();
				String WarpNameL = WarpName.toLowerCase();
				
				if(plugin.WarpsConfig.get(WarpName.toLowerCase()) == null) {
					World world = playerSender.getWorld();
					double x = playerSender.getLocation().getBlockX();
					double y = playerSender.getLocation().getBlockY();
					double z = playerSender.getLocation().getBlockZ();
					float yaw = playerSender.getLocation().getYaw();
					float pitch = playerSender.getLocation().getPitch();
					plugin.WarpsConfig.set(WarpNameL ,world.getName() + "," + x + "," + y + "," + z + "," + yaw + "," + pitch);
					plugin.saveWarpsConfig();
					
					playerSender.sendMessage(
							ChatColor.GRAY + "Warp " +
							ChatColor.GOLD + WarpName +
							ChatColor.GRAY + " set!"
							);
	
					plugin.saveWarpsConfig();
				}
				else {
					if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.warp.overwrite")) {
						World world = playerSender.getWorld();
						double x = playerSender.getLocation().getBlockX();
						double y = playerSender.getLocation().getBlockY();
						double z = playerSender.getLocation().getBlockZ();
						float yaw = playerSender.getLocation().getYaw();
						float pitch = playerSender.getLocation().getPitch();
						plugin.WarpsConfig.set(WarpNameL ,world.getName() + "," + x + "," + y + "," + z + "," + yaw + "," + pitch);
					
					playerSender.sendMessage(
							ChatColor.GRAY + "Warp " +
							ChatColor.GOLD + WarpName +
							ChatColor.GRAY + " already exists.");
					playerSender.sendMessage(
							ChatColor.GRAY + "Type " +
							ChatColor.GOLD + "/overwritewarp" +
							ChatColor.GRAY + " to overwrite it, " +
							ChatColor.GOLD + "/cancelwarp" +
							ChatColor.GRAY + " to cancel the warp."
							);
				}
				else {
					playerSender.sendMessage(ChatColor.GRAY + "Do do not have permission to overwrite warps.");
					}
				}
			}
			else {
				playerSender.sendMessage(ChatColor.RED + "/setwarp <name>");
			}
			
			}
			else {
				playerSender.sendMessage(ChatColor.GRAY + "You do not have permission to use this command.");
			}

		return false;
	}

}

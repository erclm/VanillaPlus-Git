package me.Dreeass.VanillaPlus.Commands;

import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Homes_Set implements CommandExecutor {
	
	public static Main plugin;
	
	public Homes_Set(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player playerSender = (Player) sender;
		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.home")) {
			String name = playerSender.getName();
				if(!plugin.HomesConfig.contains(name)) {
					World world = playerSender.getWorld();
					double x = playerSender.getLocation().getBlockX();
					double y = playerSender.getLocation().getBlockY();
					double z = playerSender.getLocation().getBlockZ();
					float yaw = playerSender.getLocation().getYaw();
					float pitch = playerSender.getLocation().getPitch();
					plugin.HomesConfig.set(name ,world.getName() + "," + x + "," + y + "," + z + "," + yaw + "," + pitch);
					plugin.saveHomesConfig();
					
					playerSender.sendMessage(ChatColor.GRAY + "Home set to your current location.");
				}
				
				else {
					World world = playerSender.getWorld();
					double x = playerSender.getLocation().getBlockX();
					double y = playerSender.getLocation().getBlockY();
					double z = playerSender.getLocation().getBlockZ();
					float yaw = playerSender.getLocation().getYaw();
					float pitch = playerSender.getLocation().getPitch();
					plugin.HomesConfig.set(name ,world.getName() + "," + x + "," + y + "," + z + "," + yaw + "," + pitch);
					
					playerSender.sendMessage(ChatColor.GRAY + "You already have a home!");
					playerSender.sendMessage(ChatColor.GRAY + "If you wish to overwrite it, type /overwritehome.");
				}
			}
		
		else {
			playerSender.sendMessage(ChatColor.GRAY + "You do not have permissions to use this command.");
			}
		return false;
	}

}

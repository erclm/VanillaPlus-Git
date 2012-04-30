package me.Dreeass.VanillaPlus.Commands;

import me.Dreeass.VanillaPlus.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Homes_Home implements CommandExecutor {
	
	public static Main plugin;
	
	public Homes_Home(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		final Player playerSender = (Player) sender;
		if (playerSender.isOp() || playerSender.hasPermission("vanillaplus.home")) {
			final String name = playerSender.getName();
			if(plugin.HomesConfig.contains(name)) {
				
				if (playerSender.isOp() || playerSender.hasPermission("vanillaplus.teleport.nodelay")) {
					if(plugin.HomesConfig.contains(name)) {
						String x = plugin.HomesConfig.getString(name);
					    Location l = new Location(null, 0, 0, 0, 0, 0);
					    String[] info = x.split(",");
					    l.setWorld(Bukkit.getWorld(info[0]));
					    l.setX(Double.parseDouble(info[1]));
					    l.setX(Double.parseDouble(info[1]));
					    l.setY(Double.parseDouble(info[2]));
					    l.setZ(Double.parseDouble(info[3]));
					    l.setYaw(Float.parseFloat(info[4]));
					    l.setPitch(Float.parseFloat(info[5]));
						playerSender.teleport(l);
					playerSender.sendMessage(ChatColor.GRAY + "You teleported to your home."
						);
					}
					else {
							playerSender.sendMessage(ChatColor.GRAY + "You did not set a home yet.");
					}
				}
			
			else {
				playerSender.sendMessage(
						ChatColor.GRAY + "You'll get teleported in " +
						ChatColor.GOLD + plugin.getConfig().getInt("Teleport delay") +
						ChatColor.GRAY + " seconds. Don't move!"
						);
				plugin.teleporters.add(playerSender.getName());
				plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
					public void run() {
								String x = plugin.HomesConfig.getString(name);
							    String[] info = x.split(",");
							    Location l = new Location(null, 0, 0, 0, 0, 0);
							    l.setWorld(Bukkit.getWorld(info[0]));
							    l.setX(Double.parseDouble(info[1]));
							    l.setY(Double.parseDouble(info[2]));
							    l.setZ(Double.parseDouble(info[3]));
							    l.setYaw(Float.parseFloat(info[4]));
							    l.setPitch(Float.parseFloat(info[5]));
								playerSender.teleport(l);
								playerSender.sendMessage(ChatColor.GRAY + "You teleported to your home."
									);
							plugin.teleporters.remove(playerSender.getName());
					}
				}, plugin.getConfig().getInt("Teleport delay") * 20);
			}
			
			}
		}
		else {
			playerSender.sendMessage(ChatColor.GRAY + "You do not have permission to use this command.");
		}
		return false;
	}

}

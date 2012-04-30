package me.Dreeass.VanillaPlus.Commands;

import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Weather implements CommandExecutor {

	public static Main plugin;
	
	public Weather(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		final Player playerSender = (Player) sender;
		if (playerSender == null) {
			plugin.getServer().getLogger().info("This command can only be performed by a player in-game.");
		}
		else {
			if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.weather")) {
				if(args.length == 0) {
					playerSender.sendMessage(ChatColor.RED + "/weather <sun/storm>");
				}
				if(args.length == 1) {
					World world = playerSender.getWorld();
					String weather = args[0].toLowerCase();
					if(weather.equalsIgnoreCase("sun") || weather.equalsIgnoreCase("sunny")){
						world.setStorm(false);
						playerSender.sendMessage(
								ChatColor.GRAY + "The weather in "+
								ChatColor.GOLD + world.getName() +
								ChatColor.GRAY + " has been set to " + 
								ChatColor.GOLD + "sunny" +
								ChatColor.GRAY + "."
								);
					}
					if(weather.equalsIgnoreCase("storm") || weather.equalsIgnoreCase("stormy")) {
						world.setStorm(true);
						playerSender.sendMessage(
								ChatColor.GRAY + "The weather in "+
								ChatColor.GOLD + world.getName() +
								ChatColor.GRAY + " has been set to " + 
								ChatColor.GOLD + "stormy" +
								ChatColor.GRAY + "."
								);
					}
					if(!weather.equalsIgnoreCase("storm") && !weather.equalsIgnoreCase("stormy") && !weather.equalsIgnoreCase("sun") && !weather.equalsIgnoreCase("sunny")) {
						playerSender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.GRAY + " is not a valid weathercondition. 1");
					}
					else {
						
					}
				}
				else {
					World world = plugin.getServer().getWorld(args[1]);
					if(world != null) {
						String weather = args[0].toLowerCase();
						if(weather.equalsIgnoreCase("sun") || weather.equalsIgnoreCase("sunny")) {
							world.setStorm(false);
							playerSender.sendMessage(
									ChatColor.GRAY + "The weather in "+
									ChatColor.GOLD + world.getName() +
									ChatColor.GRAY + " has been set to " + 
									ChatColor.GOLD + "sunny" +
									ChatColor.GRAY + "."
									);
						}
						if(weather.equalsIgnoreCase("storm") || weather.equalsIgnoreCase("stormy")) {
							world.setStorm(true);
							playerSender.sendMessage(
									ChatColor.GRAY + "The weather in "+
									ChatColor.GOLD + world.getName() +
									ChatColor.GRAY + " has been set to " + 
									ChatColor.GOLD + "stormy" +
									ChatColor.GRAY + "."
									);
						}
						if(!weather.equalsIgnoreCase("storm") && !weather.equalsIgnoreCase("stormy") && !weather.equalsIgnoreCase("sun") && !weather.equalsIgnoreCase("sunny")) {
							playerSender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.GRAY + " is not a valid weathercondition. 2");
						}
						else {
							
						}
					}
					else {
						playerSender.sendMessage(ChatColor.GOLD + args[1] + 
								ChatColor.GRAY + " does not exist."
								);
					}
				}
			}
			else {
				playerSender.sendMessage(ChatColor.GRAY + "You do not have permission to use this command.");
			}
		}
		return false;
	}

}

package me.Dreeass.VanillaPlus.Commands;

import me.Dreeass.VanillaPlus.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;

public class Broadcast implements CommandExecutor {
	
	public static Main plugin;

	public Broadcast(Main instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player playerSender = (Player) sender;
			
			if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.broadcast")) {
				if (args.length >= 1) {
					String message = Joiner.on(" ").join(args);
					//String message = args.
					Bukkit.broadcastMessage(
									plugin.getConfig().getString("Broadcastcolor").replaceAll("(&([a-f0-9]))", "\u00A7$2") + "[Broadcast] " + message
									);
				}
				else {
					playerSender.sendMessage(
											ChatColor.RED  + "/broadcast <message>"
											);
				}
			}
			else {
				playerSender.sendMessage(
					ChatColor.GRAY + "You do not have permission to use this command."
					);
			}
		}
		else {
				if (args.length >= 1) {
					String message = Joiner.on(" ").join(args);
					//String message = args.
					Bukkit.broadcastMessage(
									plugin.getConfig().getString("Broadcastcolor").replaceAll("(&([a-f0-9]))", "\u00A7$2") + "[Broadcast] " + message
									);
					}
				else {
					plugin.logger.info(
									ChatColor.RED  + "/broadcast <message>"
									);
				}
		}
				
		return false;
	}

}

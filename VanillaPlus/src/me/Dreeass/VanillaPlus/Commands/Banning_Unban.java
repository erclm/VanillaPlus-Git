package me.Dreeass.VanillaPlus.Commands;


import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Banning_Unban implements CommandExecutor {
	
	public static Main plugin;
		
	public Banning_Unban(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;
		if (playerSender == null) {
			if(args.length >= 1) {
				Player playerTarget = plugin.getServer().getPlayer(args[0]);
				if(playerTarget != null) {
					
				}
				else {
					plugin.logger.info("");
				}
			}
		}
		else {
			if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.unban")) {
				
				if(args.length == 1) {
					Player playerTarget = plugin.getServer().getPlayer(args[0]);
					
						if(playerTarget.isBanned()) {
							playerTarget.setBanned(false);
							playerSender.sendMessage(
									ChatColor.GOLD + playerTarget.getName() +
									ChatColor.GRAY + " has been unbanned."
									);
						}
						else {
							playerSender.sendMessage(
									ChatColor.GOLD + playerTarget.getName() +
									ChatColor.GRAY + " hasn't been banned before."
									);
						}
				}
				else {
					playerSender.sendMessage(
							ChatColor.RED + "/unban <player>"
							);
				}
			}
			
			else {
				playerSender.sendMessage(
						ChatColor.GRAY + "You do not have permission to use this command."
						);
			}
		}
		return false;
	}

}

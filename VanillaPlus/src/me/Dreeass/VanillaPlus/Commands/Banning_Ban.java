package me.Dreeass.VanillaPlus.Commands;


import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Banning_Ban implements CommandExecutor {
	
	public static Main plugin;
		
	public Banning_Ban(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (!(sender instanceof Player)) {
			if(args.length >= 1) {
				Player playerTarget = plugin.getServer().getPlayer(args[0]);
					if(playerTarget.isBanned()) {
						playerTarget.setBanned(true);
						plugin.logger.info(
								plugin.MessagesConfig.getString("Ban-Banned".replace("%player", playerTarget.getName()).replaceAll("(&([a-f0-9]))", ""))
								);
					}
					else {
						plugin.logger.info(plugin.MessagesConfig.getString("Ban-AlreadyBanned".replace("%player", playerTarget.getName()).replaceAll("(&([a-f0-9]))", ""))
						);
					}
				}
				else {
					plugin.logger.info(
							"/ban <player>"
							);
			}
		}
		else {
			Player playerSender = (Player) sender;
			if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.ban")) {
				if(args.length == 1) {
					Player playerTarget = plugin.getServer().getPlayer(args[0]);
					if(!playerTarget.isBanned() && playerTarget != null) {
							playerTarget.setBanned(true);
							plugin.logger.info(
									plugin.MessagesConfig.getString("Ban-Banned".replace("%player", playerTarget.getName()).replaceAll("(&([a-f0-9]))", "\u00A7$2"))
									);
						}
						else {
							plugin.logger.info(plugin.MessagesConfig.getString("Ban-AlreadyBanned".replace("%player", playerTarget.getName()).replaceAll("(&([a-f0-9]))", "\u00A7$2"))
									);
						}
				}
				else {
					playerSender.sendMessage(ChatColor.RED + "/ban <player>");
				}
			}
		}
		
		return false;
	}

}

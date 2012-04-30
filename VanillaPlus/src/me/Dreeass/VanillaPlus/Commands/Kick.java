package me.Dreeass.VanillaPlus.Commands;


import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {

	public static Main plugin;
		
	public Kick(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;

		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.kick")) {
			if(args.length == 1) {
				Player playerTarget = plugin.getServer().getPlayer(args[0]);
				if(playerTarget != null) {
					if(playerTarget == playerSender) {
						playerSender.sendMessage(ChatColor.GRAY + "You cannot kick yourself!");
					}
					
					else {
						playerTarget.kickPlayer("You have been kicked.");
					}
				}
				else {
					playerSender.sendMessage(ChatColor.GRAY + "The target player does not exist or is not online.");
				}
			}
			else {
				playerSender.sendMessage(
						ChatColor.RED + "/kick <player>"
						);
			}
		}
		
		else {
			playerSender.sendMessage(
					ChatColor.GRAY + "You do not have permission to use this command."
					);
		}
		
		return false;
	}

}

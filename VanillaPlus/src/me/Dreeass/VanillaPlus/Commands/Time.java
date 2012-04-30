package me.Dreeass.VanillaPlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Time implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;

			if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.time")) {
			if(args.length == 0) {
					playerSender.sendMessage(
							ChatColor.GRAY + "The time in " +
							ChatColor.GOLD + playerSender.getWorld().getName() +
							ChatColor.GRAY + " is now " +
							ChatColor.GOLD + playerSender.getWorld().getTime() +
							ChatColor.GRAY + "."
							);
				}
			else {
				if(args.length == 1) {
				if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.time.set")) {
					if(args[0].equalsIgnoreCase("day")) {
						playerSender.getWorld().setTime(0);
						playerSender.sendMessage(
								ChatColor.GRAY + "You have set the time in " +
								ChatColor.GOLD + playerSender.getWorld().getName() +
								ChatColor.GRAY + " to day."
								);
							}
					else {
						if(args[0].equalsIgnoreCase("night")) {
							playerSender.getWorld().setTime(13000);
							playerSender.sendMessage(
									ChatColor.GRAY + "You have set the time in " +
									ChatColor.GOLD + playerSender.getWorld().getName() +
									ChatColor.GRAY + " to night."
									);
							}
						else {
							playerSender.sendMessage(ChatColor.RED + "/time (day/night)");
								}
							}
						}
					}
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

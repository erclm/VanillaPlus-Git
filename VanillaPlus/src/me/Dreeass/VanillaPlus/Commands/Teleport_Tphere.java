package me.Dreeass.VanillaPlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport_Tphere implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			Player playerSender = (Player) sender;
				
				if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.teleport.other")) {

					if(args.length == 0) {
						playerSender.sendMessage(
								ChatColor.RED + "/tphere <player>"
												);
					}
					if (args.length == 1) {
					Player playerTarget = playerSender.getServer().getPlayer(args[0]);
					Location playerSenderLocation = playerSender.getLocation();
					if(playerTarget == null) {
						playerSender.sendMessage(
								ChatColor.GRAY + "The target player does not exist or is not online."
								);
					}
					else {
					playerTarget.teleport(playerSenderLocation);
					playerTarget.sendMessage(
											"You got teleported to " +
											ChatColor.GOLD + playerTarget.getName() +
											ChatColor.GRAY + " by " +
											ChatColor.GOLD + playerSender.getName() +
											ChatColor.GRAY + ".");
					playerSender.sendMessage(
									ChatColor.GRAY + "You teleported " +
									ChatColor.GOLD + playerTarget.getName() +
									ChatColor.GRAY + " to you."
									);
						}
					}
				}
				else {
					playerSender.sendMessage(
							ChatColor.GRAY + "You do not have permission to use this command." +
							ChatColor.RED + "op " +
							ChatColor.GRAY + "to use this command."
							);
					}
			return false;
	}

}

package me.Dreeass.VanillaPlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;

			if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.heal")) {
				if (args.length == 0) {
				playerSender.setHealth(20);
				playerSender.setFoodLevel(20);
				playerSender.sendMessage(
						ChatColor.GRAY + "You healed and fed yourself!"
						);
				}
				if (args.length == 1) {
					if(playerSender.hasPermission("vanillaplus.heal.other")) {
						Player playerTarget = playerSender.getServer().getPlayer(args[0]);
						if(playerTarget != null){
							playerTarget.setHealth(20);
							playerTarget.setFoodLevel(20);
							playerTarget.sendMessage(
							ChatColor.GRAY + "You got healed and fed by " + ChatColor.GOLD + playerSender.getName() +
							ChatColor.GRAY + "."
											);
					playerSender.sendMessage(
							ChatColor.GRAY + "You healed and fed " + ChatColor.GOLD + playerTarget.getName() +
							ChatColor.GRAY + "."
							);
						}
					if (playerTarget == null) {
						playerSender.sendMessage(
								ChatColor.GRAY + "The target player does not exist or is not online."
								);
							}
					}
					else {
						playerSender.sendMessage(
								ChatColor.GRAY + "You do not have permission to use this command."
								);
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

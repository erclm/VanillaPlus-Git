package me.Dreeass.VanillaPlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;
		
			if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.gamemode")){
				if(args.length > 1) {
					playerSender.sendMessage(ChatColor.RED + "/gm (player)");
				}
				if (args.length == 0) {
					if(playerSender.getGameMode().getValue() == 0) {
						playerSender.setGameMode(GameMode.CREATIVE);
						playerSender.sendMessage(
								ChatColor.GRAY + "Your gamemode has been set to creative."
								);
					}
					else {
						playerSender.setGameMode(GameMode.SURVIVAL);
						playerSender.sendMessage(
								ChatColor.GRAY + "Your gamemode has been set to survival."
								);
						}
					}
				else {
					if(playerSender.hasPermission("vanillaplus.gamemode.other")) {
					Player playerTarget = playerSender.getServer().getPlayer(args[0]);
					if(playerTarget != null) {
						if(playerTarget.getGameMode().getValue() == 0) {
						playerTarget.setGameMode(GameMode.CREATIVE);
						playerSender.sendMessage(
								ChatColor.GRAY + "The gamemode of " + 
								ChatColor.GOLD + playerTarget.getName() +
								ChatColor.GRAY + " changed to creative."
								);
						playerTarget.sendMessage(
								ChatColor.GRAY + "Your gamemode changed to creative by " +
								ChatColor.GOLD + playerTarget.getName() +
								ChatColor.GRAY + "."
								);
						}
						else {
								playerTarget.setGameMode(GameMode.SURVIVAL);
								playerSender.sendMessage(
										ChatColor.GRAY + "The gamemode of " + 
										ChatColor.GOLD + playerTarget.getName() +
										ChatColor.GRAY + " changed to survival."
										);
								playerTarget.sendMessage(
										ChatColor.GRAY + "Your gamemode changed to survival by " +
										ChatColor.GOLD + playerTarget.getName() +
										ChatColor.GRAY + "."
										);
							
						}
					}
					else {
						playerSender.sendMessage(ChatColor.GRAY + "The target player does not exist or is not online.");
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

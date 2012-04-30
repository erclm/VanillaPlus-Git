package me.Dreeass.VanillaPlus.Commands;

import me.Dreeass.VanillaPlus.Main;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;

public class Message implements CommandExecutor {

	public static Main plugin;

	public Message(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;
		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.message")) {
			if(args.length == 0) {
				playerSender.sendMessage(ChatColor.RED + "/msg <player> <message>");
			}
			else {
				Player playerTarget = plugin.getServer().getPlayer(args[0]);
				args = (String[]) ArrayUtils.removeElement(args, args[0]);
				String message = Joiner.on(" ").join(args);
				if(playerTarget != null) {
					if(!Main.afkPlayers.contains(playerTarget.getName())) {
						playerTarget.sendMessage(ChatColor.GOLD + "From " + playerSender.getName() + ": " +
						ChatColor.GRAY + message.replaceAll("(&([a-f0-9]))", "\u00A7$2")
						);
						playerSender.sendMessage(
								ChatColor.GRAY + "Message sent to " +
								ChatColor.GOLD + playerTarget.getName() +
								ChatColor.GRAY + "."
								);
					}
					else {
						if(plugin.getConfig().getBoolean("EnableMessagesToAfk")) {
							playerSender.sendMessage(
									ChatColor.GOLD + playerTarget.getName() +
									ChatColor.GRAY + "is currently afk.");
						}
						else {
							playerTarget.sendMessage(ChatColor.GOLD + "From " + playerSender.getName() + ": " +
									ChatColor.GRAY + message.replaceAll("(&([a-f0-9]))", "\u00A7$2")
									);
									playerSender.sendMessage(
											ChatColor.GRAY + "Message sent to " +
											ChatColor.GOLD + playerTarget.getName() +
											ChatColor.GRAY + " who is afk."
											);
						}
					}
				}
				else {
					playerSender.sendMessage(
							ChatColor.GRAY +
							"The target player does not exist or is not online."
							);
					}
				}
		}
		else {
			playerSender.sendMessage(ChatColor.GRAY + "You do not have permission to use this command.");
		}
		return false;
	}

}

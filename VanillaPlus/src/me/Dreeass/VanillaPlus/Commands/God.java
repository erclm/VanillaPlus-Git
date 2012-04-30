package me.Dreeass.VanillaPlus.Commands;


import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class God implements CommandExecutor {
	
	public static Main plugin;
	
	public God(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;
			String name = playerSender.getName();
			if (playerSender.isOp() || playerSender.hasPermission("vanillaplus.god")) {
				if (args.length == 0) {
					if(Main.godPlayers.contains(name)) {
						playerSender.sendMessage(
								ChatColor.GRAY + "Godmode disabled."
								);
						Main.godPlayers.remove(name);
					}
					else {
						playerSender.sendMessage(
								ChatColor.GRAY + "Godmode enabled."
								);
						Main.godPlayers.add(name);
						}
				}
				else {
					Player playerTarget = plugin.getServer().getPlayer(args[0]);
					if(playerTarget != null) {
						String nameTarget = playerTarget.getName();
						if(playerTarget == playerSender) {
							if(Main.godPlayers.contains(name)) {
								playerSender.sendMessage(
										ChatColor.GRAY + "Godmode disabled."
										);
								Main.godPlayers.remove(name);
							}
							else {
								playerSender.sendMessage(
										ChatColor.GRAY + "Godmode enabled."
										);
								Main.godPlayers.add(name);
								}
							}
						
						if(Main.godPlayers.contains(nameTarget)) {
							playerTarget.sendMessage(
									ChatColor.GRAY + "Godmode disabled by " +
									ChatColor.GOLD + name +
									ChatColor.GRAY + "."
									);
							Main.godPlayers.remove(nameTarget);
						}
						if(!Main.godPlayers.contains(nameTarget)) {
							playerTarget.sendMessage(
									ChatColor.GRAY + "Godmode enabled by " +
									ChatColor.GOLD + name +
									ChatColor.GRAY + "."
									);
							Main.godPlayers.add(name);
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

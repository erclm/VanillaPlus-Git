package me.Dreeass.VanillaPlus.Commands;



import me.Dreeass.VanillaPlus.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Afk implements CommandExecutor {
	
	public static Main plugin;
	
	public Afk(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	Player player = (Player) sender;
	if (!(sender instanceof Player)) {
		plugin.logger.info("You cannot use that command in the console.");
	}
	else {
		String name = player.getName();
		if(player.hasPermission("vanillaplus.afk")) {
			if(Main.afkPlayers.contains(name)) {
				Bukkit.broadcastMessage(
						plugin.MessagesConfig.getString("AFK-Return".replace("%player", name).replaceAll("(&([a-f0-9]))", "\u00A7$2"))
						);
				Main.afkPlayers.remove(name);
			}
			else {
			if(!Main.afkPlayers.contains(name)) {
				Bukkit.broadcastMessage(
						plugin.MessagesConfig.getString("AFK-Away-Broadcast".replace("%player", name).replaceAll("(&([a-f0-9]))", "\u00A7$2"))
						);
				player.sendMessage(
						plugin.MessagesConfig.getString("AFK-Away-Player".replace("%player", name).replaceAll("(&([a-f0-9]))", "\u00A7$2"))
						);
				Main.afkPlayers.add(name);
				}	
			}
		}
	}
		return false;
	}

}

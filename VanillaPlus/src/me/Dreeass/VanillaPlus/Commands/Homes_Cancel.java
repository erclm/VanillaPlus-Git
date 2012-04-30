package me.Dreeass.VanillaPlus.Commands;

import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Homes_Cancel implements CommandExecutor {
	
	public static Main plugin;
	
	public Homes_Cancel(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;

		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.home")) {
			
			plugin.reloadHomesConfig();
			playerSender.sendMessage(ChatColor.GRAY + "Home cancelled!");
			
			}
		
		else {
			playerSender.sendMessage(ChatColor.GRAY + "Do do not have permission to cancel homes.");
			}
		return false;
	}

}

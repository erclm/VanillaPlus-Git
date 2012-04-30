package me.Dreeass.VanillaPlus.Commands;

import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Homes_Overwrite implements CommandExecutor {
	
	public static Main plugin;
	
	public Homes_Overwrite(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;

		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.home")) {
			
			plugin.saveHomesConfig();
			playerSender.sendMessage(ChatColor.GRAY + "Home overwritten!");
			
			}
		
		else {
			playerSender.sendMessage(ChatColor.GRAY + "Do do not have permission to overwrite homes.");
			}
		
		return false;
	}

}

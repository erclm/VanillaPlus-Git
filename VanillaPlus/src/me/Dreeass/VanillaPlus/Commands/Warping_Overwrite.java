package me.Dreeass.VanillaPlus.Commands;


import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warping_Overwrite implements CommandExecutor {

	public static Main plugin;
	
	public Warping_Overwrite(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;

		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.warp.overwrite")) {
			
			plugin.saveWarpsConfig();
			playerSender.sendMessage(ChatColor.GRAY + "Warp overwritten!");
			
			}
		
		else {
			playerSender.sendMessage(ChatColor.GRAY + "Do do not have permission to overwrite warps.");
			}
		
		return false;
	}

}

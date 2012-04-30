package me.Dreeass.VanillaPlus.Commands;


import me.Dreeass.VanillaPlus.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MOTD implements CommandExecutor {
	public static Main plugin;
	
	public MOTD(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;
		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.motd")) {
		playerSender.sendMessage(plugin.getConfig().getString("MOTD").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
		}
		
		return false;
	}

}

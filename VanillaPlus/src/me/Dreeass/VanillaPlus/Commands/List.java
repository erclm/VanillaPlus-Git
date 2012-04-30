package me.Dreeass.VanillaPlus.Commands;

import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class List implements CommandExecutor {
	
	public static Main plugin;

	public List(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;
		
		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.list")) {
			if(playerSender.getServer().getOnlinePlayers().length == 1) {
				playerSender.sendMessage(
						ChatColor.GRAY + "You are the only player on the server."
						);
			}
			else {
				Player[] players = plugin.getServer().getOnlinePlayers();
				String list = "";
				int l = players.length;
				for(Player n : players) {
					list+= n.getName();
					l--;
					if(l == 0) {
						list += ".";
					}
					else {
						list+= ", ";
					}
				}
				playerSender.sendMessage(
						ChatColor.GOLD + "There are " +
						playerSender.getServer().getOnlinePlayers().length + " players online: " +
						ChatColor.GRAY + list
						);
			}
		}
		
		return false;
	}

}

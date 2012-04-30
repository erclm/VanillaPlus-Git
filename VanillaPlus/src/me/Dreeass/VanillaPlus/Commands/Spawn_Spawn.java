package me.Dreeass.VanillaPlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn_Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;
		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.spawn")) {
			Location spawnLocation = playerSender.getWorld().getSpawnLocation();
			playerSender.teleport(spawnLocation);
			playerSender.sendMessage(
					ChatColor.GRAY + "You have been teleported to the spawn."
					);
		}
		return false;
	}

}

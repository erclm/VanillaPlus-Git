package me.Dreeass.VanillaPlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn_Setspawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;
		
			if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.spawn.set")) {
				if (args.length == 0) {
				int x = playerSender.getLocation().getBlockX();
				int y = playerSender.getLocation().getBlockY();
				int z = playerSender.getLocation().getBlockZ();
				float pitch = playerSender.getLocation().getPitch();
				float yaw = playerSender.getLocation().getYaw();
				World world = playerSender.getWorld();
				Location playerLocation = new Location(world,x,y,z,yaw,pitch);
				playerSender.getServer().getWorld(world.getName()).setSpawnLocation(playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ());
				playerSender.sendMessage(
						ChatColor.GRAY + "The spawn for " +
						ChatColor.GOLD + world.getName() +
						ChatColor.GRAY + " has been set to your current location."
						);
				}
				else {
					if(args[0] == "global" || args[0] == "g") {
						
						playerSender.sendMessage(
								ChatColor.GRAY + "The global spawn has been set to your current location."
								);
					}
					else {
						playerSender.sendMessage(
								ChatColor.RED + "/setspawn (global)"
								);
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

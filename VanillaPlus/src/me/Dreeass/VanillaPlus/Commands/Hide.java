package me.Dreeass.VanillaPlus.Commands;

import me.Dreeass.VanillaPlus.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hide implements CommandExecutor {
	
	public static Main plugin;
	
	public Hide(Main instance) {
		plugin = instance;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("vanillaplus.hide") || p.isOp()) {
				if(args.length == 1) {
					if(p.hasPermission("vanillaplus.hide.other") || p.isOp()) {
						Player t = Bukkit.getPlayer(args[0]); 
						if(t != null) {
							t.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 0, 1));
							p.sendMessage(ChatColor.GOLD + t.getName() + ChatColor.GRAY +  " is now hidden!");
							t.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.GRAY + " made you hidden!");
						}
						else {
							p.sendMessage(ChatColor.GOLD + args[0] + ChatColor.GRAY + " has te be online for that command.");
						}
					}
					else {
						p.sendMessage(ChatColor.GRAY + "You do not have permission for this command.");
					}
				}
				else {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60 * 20, 1));
					p.sendMessage(ChatColor.GRAY + "You are now hidden!");
				}
			}
		}
		
		else {
			plugin.logger.info("You have to be ingame to use this command.");
		}
		
		return false;
	}

}

package me.Dreeass.VanillaPlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Give implements CommandExecutor {

	public boolean isParsableToInt(String i)
	 {
	 try
	 {
	 Integer.parseInt(i);
	 return true;
	 }
	 catch(NumberFormatException nfe)
	 {
	 return false;
	 }
	 }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player playerSender = (Player) sender;
		
		if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.give")) {
			if(args.length <= 1 || args.length > 3) {
				playerSender.sendMessage(ChatColor.RED + "/give <player> <id> (amount)");
			}
			if(args.length == 2) { // /give <player> <id> -> Geef 1
				//boolean isInteger = Pattern.matches("^\d*$", args[1]);
				
				 
				if(!isParsableToInt(args[1])) {
					playerSender.sendMessage(ChatColor.GRAY + "Give the ID of the item, not the name.");
				}
				else {
				int itemID = Integer.parseInt(args[1]);
				Player playerTarget = playerSender.getServer().getPlayer(args[0]);
				String itemName = Material.getMaterial(itemID).toString().toLowerCase();
				ItemStack itemNoAmount = new ItemStack(itemID, 1);
				
					if(playerTarget != null) {
						if(playerTarget == playerSender) {
							playerSender.getInventory().addItem(itemNoAmount);
							playerSender.sendMessage(
									ChatColor.GRAY + "You gave yourself 1 " +
									ChatColor.GOLD + itemName +
									ChatColor.GRAY + "."
									);
						}
						
						else {
							if (playerSender.isOp() || playerSender.hasPermission("vanilla.give.other")) {
							playerTarget.getInventory().addItem(itemNoAmount);
							playerSender.sendMessage(
									ChatColor.GRAY + "You gave " +
									ChatColor.GOLD + playerTarget.getName() +
									ChatColor.GRAY + " 1 " +
									ChatColor.GOLD + itemName +
									ChatColor.GRAY + "."
									);
							playerTarget.sendMessage(
									ChatColor.GRAY + "You got 1 " +
									ChatColor.GOLD + itemName + " " +
									ChatColor.GRAY + "from " +
									ChatColor.GOLD + playerSender.getName()
									);
							}
							else {
								playerSender.sendMessage(ChatColor.GRAY + "You do not have permission to use this command.");
							}
						}
					}
					
					else {
						playerSender.sendMessage(ChatColor.GRAY + "The given player does not exist or is not online.");
					}
				}
			}
			
				if(args.length == 3) {
					int itemID = Integer.parseInt(args[1]);
					Player playerTarget = playerSender.getServer().getPlayer(args[0]);
					String itemName = Material.getMaterial(itemID).toString().toLowerCase();
					int itemAmount = Integer.parseInt(args[2]);
					ItemStack itemWithAmount = new ItemStack(itemID, itemAmount);
					if(!isParsableToInt(args[1])) {
						playerSender.sendMessage(ChatColor.GRAY + "Give the ID of the item, not the name.");
					}
					if(playerTarget != null) {
						
						if(playerTarget == playerSender) {
							playerSender.getInventory().addItem(itemWithAmount);
							playerSender.sendMessage(
									ChatColor.GRAY + "You gave yourself " + itemAmount + " " +
									ChatColor.GOLD + itemName +
									ChatColor.GRAY + "."
									);
						}
						
						else {
							
							if(playerSender.isOp() || playerSender.hasPermission("vanillaplus.give.other")) {
							playerTarget.getInventory().addItem(itemWithAmount);
							playerSender.sendMessage(
									ChatColor.GRAY + "You gave " +
									ChatColor.GOLD + playerTarget.getName() +
									ChatColor.GRAY + " " + itemAmount + " " +
									ChatColor.GOLD + itemName +
									ChatColor.GRAY + "."
									);
							playerTarget.sendMessage(
									ChatColor.GRAY + "You got " + itemAmount + " " +
									ChatColor.GOLD + itemName + " " +
									ChatColor.GRAY + "from " +
									ChatColor.GOLD + playerSender.getName()
									);
							}
							
							else {
								playerSender.sendMessage(ChatColor.GRAY + "You do not have permission to use this command.");
								}
							}
						}
					}
				}
		
		else {
			playerSender.sendMessage(ChatColor.GRAY + "You do not have permission to use this command.");
		}
			
		return false;
	}

}

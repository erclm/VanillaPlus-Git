package me.Dreeass.VanillaPlus.Listeners;

import me.Dreeass.VanillaPlus.Main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {
	
	public static Main plugin;

	public BlockListener(Main instance) {
		plugin = instance;
	}
	
	public static Material[] blacklist = {Material.TNT, Material.LAVA, Material.LAVA_BUCKET, Material.BEDROCK};
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Material block = event.getBlock().getType();
		Player player = event.getPlayer();
		
		for(Material blocked : blacklist) {
			if(blocked == block) {
				if(player.isOp() || player.hasPermission("vanillaplus.place")) {
				}
				else {
					event.getBlock().setType(Material.AIR);
					Material.getMaterial(block.name());
					player.sendMessage(
					ChatColor.GRAY + "You are not allowed to place " +
					ChatColor.RED + block.name() +
					ChatColor.WHITE + ".");
				}
			}
		}
	}
}

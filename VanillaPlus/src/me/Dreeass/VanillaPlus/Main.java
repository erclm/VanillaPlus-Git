package me.Dreeass.VanillaPlus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;


import me.Dreeass.VanillaPlus.Commands.Afk;
import me.Dreeass.VanillaPlus.Commands.Banning_Ban;
import me.Dreeass.VanillaPlus.Commands.Banning_Unban;
import me.Dreeass.VanillaPlus.Commands.Broadcast;
import me.Dreeass.VanillaPlus.Commands.Gamemode;
import me.Dreeass.VanillaPlus.Commands.Give;
import me.Dreeass.VanillaPlus.Commands.God;
import me.Dreeass.VanillaPlus.Commands.Heal;
import me.Dreeass.VanillaPlus.Commands.Hide;
import me.Dreeass.VanillaPlus.Commands.Homes_Cancel;
import me.Dreeass.VanillaPlus.Commands.Homes_Home;
import me.Dreeass.VanillaPlus.Commands.Homes_Overwrite;
import me.Dreeass.VanillaPlus.Commands.Homes_Set;
import me.Dreeass.VanillaPlus.Commands.Kick;
import me.Dreeass.VanillaPlus.Commands.List;
import me.Dreeass.VanillaPlus.Commands.MOTD;
import me.Dreeass.VanillaPlus.Commands.Message;
import me.Dreeass.VanillaPlus.Commands.Spawn_Setspawn;
import me.Dreeass.VanillaPlus.Commands.Spawn_Spawn;
import me.Dreeass.VanillaPlus.Commands.Teleport_Tp;
import me.Dreeass.VanillaPlus.Commands.Teleport_Tphere;
import me.Dreeass.VanillaPlus.Commands.Time;
import me.Dreeass.VanillaPlus.Commands.Warping_Cancel;
import me.Dreeass.VanillaPlus.Commands.Warping_Overwrite;
import me.Dreeass.VanillaPlus.Commands.Warping_Setwarp;
import me.Dreeass.VanillaPlus.Commands.Warping_Warp;
import me.Dreeass.VanillaPlus.Commands.Weather;
import me.Dreeass.VanillaPlus.Listeners.BlockListener;
import me.Dreeass.VanillaPlus.Listeners.PlayerListener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static ArrayList<String> godPlayers = new ArrayList<String>();
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;
	public final BlockListener bl = new BlockListener(this);
	public final PlayerListener pl = new PlayerListener(this);
	public static ArrayList<String> afkPlayers = new ArrayList<String>();
	public final ArrayList<String> teleporters = new ArrayList<String>();
	public final HashMap<String, Location> homes = new HashMap<String, Location>();
	//String = String.replaceAll("(&([a-f0-9]))", "\u00A7$2");
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " has been disabled.");
		
	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " has been enabled.");
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.bl, this);
		pm.registerEvents(this.pl, this);
		
		final FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        this.saveConfig();

		final FileConfiguration homesconfig = this.getHomesConfig();
		homesconfig.options().copyDefaults(true);
		saveHomesConfig();
		
		final FileConfiguration warpsconfig = this.getWarpsConfig();
		warpsconfig.options().copyDefaults(true);
		saveWarpsConfig();

		reloadMessagesConfig();
		final FileConfiguration messagesconfig = this.getMessagesConfig();
		messagesconfig.options().copyDefaults(true);
		saveMessagesConfig();
		
		if(this.getConfig().getBoolean("Enable autobroadcasting")) {
			AutoBroadcast();
		}
		else {
			this.logger.info("Autobroadcasting has been disabled.");
		}
		
		getCommand("motd").setExecutor(new MOTD(this));
		getCommand("afk").setExecutor(new Afk(this));
		getCommand("tp").setExecutor(new Teleport_Tp(this));
		getCommand("tphere").setExecutor(new Teleport_Tphere());
		getCommand("god").setExecutor(new God(this));
		getCommand("spawn").setExecutor(new Spawn_Spawn());
		getCommand("setspawn").setExecutor(new Spawn_Setspawn());
		getCommand("give").setExecutor(new Give());
		getCommand("broadcast").setExecutor(new Broadcast(this));
		getCommand("heal").setExecutor(new Heal());
		getCommand("time").setExecutor(new Time());
		getCommand("list").setExecutor(new List(this));
		getCommand("gm").setExecutor(new Gamemode());
		getCommand("ban").setExecutor(new Banning_Ban(this));
		getCommand("unban").setExecutor(new Banning_Unban(this));
		getCommand("kick").setExecutor(new Kick(this));
		getCommand("sethome").setExecutor(new Homes_Set(this));
		getCommand("home").setExecutor(new Homes_Home(this));
		getCommand("overwritehome").setExecutor(new Homes_Overwrite(this));
		getCommand("cancelhome").setExecutor(new Homes_Cancel(this));
		getCommand("warp").setExecutor(new Warping_Warp(this));
		getCommand("setwarp").setExecutor(new Warping_Setwarp(this));
		getCommand("overwritewarp").setExecutor(new Warping_Overwrite(this));
		getCommand("cancelwarp").setExecutor(new Warping_Cancel(this));
		getCommand("msg").setExecutor(new Message(this));
		getCommand("weather").setExecutor(new Weather(this));
		getCommand("hide").setExecutor(new Hide(this));
		
		
	}
	
	//// AUTOBROADCAST \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void AutoBroadcast() {
		int timer = getConfig().getInt("Broadcast timer");
				this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
					public void run() {
						Bukkit.broadcastMessage(getConfig().getString("Broadcastmessage").replaceAll("(&([a-f0-9]))", "\u00A7$2")
						);
					}
				}, 0L, timer * 20);
	}
	
	//// HOMES CONFIGURATIONFILE GENERATION AND METHODS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public FileConfiguration HomesConfig = null;
	public File HomesConfigFile = null;
	
	public void reloadHomesConfig() {
	    if (HomesConfigFile == null) {
	    HomesConfigFile = new File(getDataFolder(), "homes.yml");
	    }
	    HomesConfig = YamlConfiguration.loadConfiguration(HomesConfigFile);
	 
	    InputStream defConfigStream = getResource("homes.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        HomesConfig.setDefaults(defConfig);
	    }
	}
	
	public FileConfiguration getHomesConfig() {
	    if (HomesConfig == null) {
	    	File CMBhomesFile = new File(getDataFolder(), "homes.csv");
	    	Boolean cmb = this.getConfig().getBoolean("Commandbookhomes");
	    	if(cmb) {
				BufferedReader r = null;
				try {
					r = new BufferedReader(new FileReader(CMBhomesFile));
				} catch (FileNotFoundException e1) {
					logger.info("FileNotFoundExeption:");
					logger.info(e1.toString());
					e1.printStackTrace();
				}
				String q;
				try {
					while((q = r.readLine()) != null) {
						String[] info = q.split(",");
					    Location l = new Location(null, 0, 0, 0, 0, 0);
					    
					    l.setWorld(Bukkit.getWorld(info[1].replaceAll("\"", "").trim()));
					    l.setX(Double.parseDouble(info[3].replaceAll("\"", "").trim()));
					    l.setY(Double.parseDouble(info[4].replaceAll("\"", "").trim()));
					    l.setZ(Double.parseDouble(info[5].replaceAll("\"", "").trim()));
					    l.setYaw(Float.parseFloat(info[6].replaceAll("\"", "").trim()));
					    l.setPitch(Float.parseFloat(info[7].replaceAll("\"", "").trim()));
					    
					    homes.put(info[0].replaceAll("\"", "").trim(), l);
					    //HomesConfig.set("homes", homes);
						    FileConfiguration.createPath(HomesConfig, "homes");
						    for (Entry<String, Location> e:homes.entrySet()) {
						        HomesConfig.set("homes."+e.getKey(), e.getValue());
						    }
					}
				} catch (NumberFormatException e) {
					logger.info("NumberFormatException: ");
					logger.info(e.toString());
					e.printStackTrace();
				} catch (IOException e) {
					logger.info("IOException: ");
					logger.info(e.toString());
					e.printStackTrace();
				}
	    	}
	    	reloadHomesConfig();
	    }
	    return HomesConfig;
	}
	
	public void saveHomesConfig() {
	    if (HomesConfig == null || HomesConfigFile == null) {
	    return;
	    }
	    try {
	        HomesConfig.save(HomesConfigFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + HomesConfigFile, ex);
	    }
	}
	
	
	//// WARPS CONFIGURATIONFILE GENERATION AND METHODS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	

	public FileConfiguration WarpsConfig = null;
	public File WarpsConfigFile = null;
	
	public void reloadWarpsConfig() {
	    if (WarpsConfigFile == null) {
	    WarpsConfigFile = new File(getDataFolder(), "warps.yml");
	    }
	    WarpsConfig = YamlConfiguration.loadConfiguration(WarpsConfigFile);
	 
	    InputStream defConfigStream = getResource("warps.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        WarpsConfig.setDefaults(defConfig);
	    }
	}
	
	public FileConfiguration getWarpsConfig() {
	    if (WarpsConfig == null) {
	        reloadWarpsConfig();
	    }
	    return WarpsConfig;
	}
	
	public void saveWarpsConfig() {
	    if (WarpsConfig == null || WarpsConfigFile == null) {
	    return;
	    }
	    try {
	        WarpsConfig.save(WarpsConfigFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + WarpsConfigFile, ex);
	    }
	}
	
	
	// MESSAGES CONFIG \\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
	public FileConfiguration MessagesConfig = null;
	public File MessagesConfigFile = null;
	
	public void reloadMessagesConfig() {
	    if (MessagesConfigFile == null) {
	    	MessagesConfigFile = new File(getDataFolder(), "messages.yml");
	    }
	    MessagesConfig = YamlConfiguration.loadConfiguration(MessagesConfigFile);
	 
	    InputStream defConfigStream = getResource("messages.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        MessagesConfig.setDefaults(defConfig);
	    }
	}
	
	public FileConfiguration getMessagesConfig() {
	    if (MessagesConfig == null) {
	        reloadWarpsConfig();
	    }
	    return MessagesConfig;
	}
	
	public void saveMessagesConfig() {
	    if (MessagesConfig == null || MessagesConfigFile == null) {
	    return;
	    }
	    try {
	    	MessagesConfig.save(MessagesConfigFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + MessagesConfigFile, ex);
	    }
	}
	
}

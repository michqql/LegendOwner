package com.michqql.legendowner.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.michqql.legendowner.Main;


public class Config {
	
	private Main plugin;

	public Config(Main main) {
		this.plugin = main;
		setup();
		write();
		save();
	}

	public FileConfiguration config;
	public File file;

	public void setup() {

		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		file = new File(plugin.getDataFolder(), "legendowner.yml");

		if (!file.exists()) {

			try {
				file.createNewFile();
				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "[LegendOwner] " + ChatColor.GRAY + "Config: "
								+ ChatColor.GREEN + "Language config (lang.yml) file created successfully!");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "[LegendOwner] " + ChatColor.GRAY + "Config: "
								+ ChatColor.RED + "Language config (lang.yml) file creation failed!");
			}
		}

		config = YamlConfiguration.loadConfiguration(file);

	}
	
	public void write() {
		config.addDefault("prefix", "&d[LegendOwner] ");
		config.addDefault("use_prefix", true);
		
		config.addDefault("lore_message", "&7Original Owner: &a%player%");
		config.addDefault("key_word", "UUID");
		config.addDefault("under_key", true);
		
		List<String> mats = new ArrayList<String>();
		mats.add(Material.RECORD_3.toString());
		mats.add(Material.RECORD_4.toString());
		mats.add(Material.RECORD_5.toString());
		mats.add(Material.RECORD_6.toString());
		mats.add(Material.RECORD_7.toString());
		mats.add(Material.RECORD_8.toString());
		mats.add(Material.RECORD_9.toString());
		mats.add(Material.RECORD_10.toString());
		mats.add(Material.RECORD_11.toString());
		mats.add(Material.RECORD_12.toString());
		mats.add(Material.GOLD_RECORD.toString());
		mats.add(Material.GREEN_RECORD.toString());
		config.addDefault("items_to_apply", mats.toArray());
		
		config.options().copyDefaults(true);
	}

	public FileConfiguration get() {
		return config;
	}

	public void save() {
		
		try {
			config.save(file);
		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender()
					.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "[LegendOwner] " + ChatColor.GRAY + "Config: "
							+ ChatColor.RED + "Could not save Language config (lang.yml)!");
		}

	}

	public void reload() {
		config = YamlConfiguration.loadConfiguration(file);
	}

}

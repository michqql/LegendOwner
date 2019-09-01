package com.michqql.legendowner;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.michqql.legendowner.config.Config;
import com.michqql.legendowner.events.ItemPickUpEvent;
import com.michqql.legendowner.utils.CompareItemLore;

public class Main extends JavaPlugin {
	
	private Config config;
	private CompareItemLore compare;
	
	public void onEnable() {
		init();
	}
	
	public void init() {
		config = new Config(this);
		compare = new CompareItemLore(config.get().getString("lore_message"));
		Bukkit.getPluginManager().registerEvents(new ItemPickUpEvent(compare, config.get().getStringList("items_to_apply")), this);
	}

}

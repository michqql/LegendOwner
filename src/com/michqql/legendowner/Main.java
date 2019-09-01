package com.michqql.legendowner;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.michqql.legendowner.events.ItemPickUpEvent;
import com.michqql.legendowner.utils.CompareItemLore;

public class Main extends JavaPlugin {

	public void onEnable() {
		init();
	}

	public void init() {
		this.getConfig().options().copyDefaults();
		saveDefaultConfig();
		Bukkit.getPluginManager()
				.registerEvents(new ItemPickUpEvent(this.getConfig().getStringList("legend_materials")), this);
		new CompareItemLore(this.getConfig().getString("lore_message"), this.getConfig().getString("keyword"));
	}

}

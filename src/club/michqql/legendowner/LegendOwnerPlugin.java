package club.michqql.legendowner;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import club.michqql.legendowner.commands.ReloadCommand;
import club.michqql.legendowner.events.ItemEvent;
import club.michqql.legendowner.utils.CompareItemLore;

public class LegendOwnerPlugin extends JavaPlugin {
	
	private String prefix = "&d[LegendOwner]", reloadMessage = "&7Reloaded in &f%time%ms&7.";
	private boolean usePrefix = true;
	
	private final String LORE_MESSAGE = "&7Original Owner: &a%player%", KEYWORD = "UUID";
	private CompareItemLore itemLoreUtil;

	@Override
	public void onEnable() {
		init();
	}

	private void init() {
		this.getConfig().options().copyDefaults();
		this.saveDefaultConfig();
		
		this.prefix = getConfig().getString("prefix");
		this.usePrefix = getConfig().getBoolean("use-prefix");
		this.reloadMessage = getConfig().getString("reload-message");
		
		this.itemLoreUtil = new CompareItemLore(LORE_MESSAGE, KEYWORD);
		register();
	}
	
	public void reload() {
		init();
	}
	
	private void register() {
		Bukkit.getPluginManager().registerEvents(new ItemEvent(
				this.getConfig().getStringList("legend-materials"),
				this.getConfig().getString("world-destroyer.material"), 
				this.getConfig().getString("world-destroyer.name-without-colour-code"), 
				itemLoreUtil), this);
		getCommand("legendownerreload").setExecutor(new ReloadCommand(this));
	}

	public String getPrefix() {
		return ((usePrefix) ? (prefix + " ") : "");
	}

	public String getReloadMessage(long time) {
		return reloadMessage.replaceAll("%time%", Long.toString(time));
	}
}

package club.michqql.legendowner.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import club.michqql.legendowner.LegendOwnerPlugin;

public class ReloadCommand implements CommandExecutor {
	
	private LegendOwnerPlugin plugin;
	
	private final String COMMAND_NAME = "legendownerreload";
	
	public ReloadCommand(LegendOwnerPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase(COMMAND_NAME)) {
			if(sender.hasPermission("legendowner.reload")) {
				long now = System.currentTimeMillis();
				plugin.reload();
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', 
						plugin.getPrefix() + plugin.getReloadMessage((System.currentTimeMillis() - now))
						));
				return true;
			}
		}
		return false;
	}

}

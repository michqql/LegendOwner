package com.michqql.legendowner.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.michqql.legendowner.utils.CompareItemLore;

public class ItemPickUpEvent implements Listener {
	
	private List<Material> mats = new ArrayList<Material>();

	public ItemPickUpEvent(List<String> mats) {
		for (String line : mats) {
			Material mat = Material.getMaterial(line);
			if (mat == null)
				continue;
			this.mats.add(mat);
		}
	}

	@EventHandler
	public void onItemHold(PlayerItemHeldEvent e) {
		ItemStack item = e.getPlayer().getInventory().getItem(e.getNewSlot());
		if (item == null || item.getType() == Material.AIR)
			return;

		boolean flag = false;
		for (Material mat : this.mats) {
			if (item.getType() == mat) {
				flag = true;
				continue;
			}
		}
		if (!flag)
			return;
		
		ItemMeta im = item.getItemMeta();
		item.setItemMeta(CompareItemLore.compare(e.getPlayer(), im));
	}

	@EventHandler
	public void onItemPick(PlayerPickupItemEvent e) {
		ItemStack item = e.getItem().getItemStack();
		if (item == null || item.getType() == Material.AIR)
			return;

		boolean flag = false;
		for (Material mat : this.mats) {
			if (item.getType() == mat) {
				flag = true;
				continue;
			}
		}
		if (!flag)
			return;

		ItemMeta im = item.getItemMeta();
		item.setItemMeta(CompareItemLore.compare(e.getPlayer(), im));
	}

}

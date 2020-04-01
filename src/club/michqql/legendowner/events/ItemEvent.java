package club.michqql.legendowner.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import club.michqql.legendowner.utils.CompareItemLore;

public class ItemEvent implements Listener {
	
	private List<Material> legendMaterials = new ArrayList<Material>();
	private Material runedPickMaterial;
	private String runedPickName;
	private CompareItemLore util;

	/**
	 * Constructor
	 * 
	 * Converts material string into material object
	 * @param materialsIn - a list the materials legendary items are made of
	 */
	public ItemEvent(List<String> materialsIn, String runedPickMaterialIn, String runedPickNameIn, CompareItemLore util) {
		this.util = util;
		this.runedPickMaterial = (Material.getMaterial(runedPickMaterialIn) != null) ? Material.getMaterial(runedPickMaterialIn) : Material.DIAMOND_PICKAXE;
		this.runedPickName = runedPickNameIn;
		for (String string : materialsIn) {
			Material material = Material.getMaterial(string);
			if (material == null)
				continue;
			this.legendMaterials.add(material);
		}
	}
	
	/*
	 * Called when a player switches hotbar slot
	 */
	@EventHandler
	public void onItemHold(PlayerItemHeldEvent e) {
		ItemStack item = e.getPlayer().getInventory().getItem(e.getNewSlot());
		itemCheck(item, e.getPlayer());
	}

	/*
	 * Called when a player picks up an item
	 */
	@EventHandler
	public void onItemPick(PlayerPickupItemEvent e) {
		ItemStack item = e.getItem().getItemStack();
		itemCheck(item, e.getPlayer());
	}
	
	/**
	 * Checks if the item is a legendary item and edits lore accordingly
	 * @param item
	 */
	private void itemCheck(ItemStack item, Player player) {
		if (item == null || 
				!legendMaterials.contains(item.getType()) || 
				!(item.getType() == runedPickMaterial && item.getItemMeta().getDisplayName().contains(runedPickName)))
			return;
		
		ItemMeta im = item.getItemMeta();
		item.setItemMeta(util.checkItemLore(player, im));
	}

}

package club.michqql.legendowner.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class CompareItemLore {

	private final String LORE_MESSAGE, KEYWORD;

	public CompareItemLore(String message, String keyword) {
		this.LORE_MESSAGE = message;
		this.KEYWORD = keyword;
	}

	public ItemMeta checkItemLore(Player itemHolder, ItemMeta itemMeta) {
		List<String> lore = (itemMeta.getLore() == null || !itemMeta.hasLore()) ? new ArrayList<String>() : itemMeta.getLore();

		if (doesLoreContainOwner(lore))
			return itemMeta;

		// Doesnt contain line
		int index = getKeywordLine(lore);
		if (index < 0)
			return itemMeta;
		
		lore.set(index, translateLoreMessage(itemHolder.getName()));
		itemMeta.setLore(lore);
		return itemMeta;
	}

	private int getKeywordLine(List<String> lore) {
		// Here we need to check for the keyword
		int index = 0;
		for (String line : lore) {
			if (line.contains(KEYWORD)) {
				try {
					int i = 0;
					while(!lore.get(index + i).isEmpty()) {
						i--;
					}
					return index + i;
				} catch (IndexOutOfBoundsException e) {
					return -100;                                  //Error code break
				}
			}
			index++;
		}
		return lore.size();
	}

	/**
	 * Method to check whether the lore already contains the lore message<br>
	 * If it does contain the lore message, we dont need to add it again
	 * 
	 * @param lore
	 * @return
	 */
	private boolean doesLoreContainOwner(List<String> itemLore) {
		if(itemLore == null) return false;
		
		String[] compareSplit = LORE_MESSAGE.split("\\s+");                    //The comparative message broken up at spaces 
		                                                                       //            (s+ being any amount of spaces)
		
		Collections.reverse(itemLore);                                         //Reverse the order of the item lore
		                                                                       //(As we expect the target line to be nearer to the end)
		for (String line : itemLore) {
			String[] lineSplit = line.split("\\s+");                           //The singular line in lore broken up at spaces
			int flagCounter = 0;
			
			for (int i = 0; i < lineSplit.length; i++) {
				if (i >= compareSplit.length) break;
				if (lineSplit[i].equalsIgnoreCase(compareSplit[i])) flagCounter++;
			}
			
			if (flagCounter >= (compareSplit.length / 2)) {
				return true;
			}
		}
		return false;
	}
	
	private String translateLoreMessage(String name) {
		return ChatColor.translateAlternateColorCodes('&', LORE_MESSAGE.replaceAll("%player%", name));
	}
}

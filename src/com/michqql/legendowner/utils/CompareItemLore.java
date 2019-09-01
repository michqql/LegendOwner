package com.michqql.legendowner.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class CompareItemLore {

	private static String loreMessage;

	// keyword algorithm settings
	private static String keyword;

	public CompareItemLore(String line, String key) {
		loreMessage = line;
		keyword = key;
	}

	public static ItemMeta compare(Player player, ItemMeta im) {

		List<String> lore;
		if (im.getLore() == null || (!im.hasLore())) {
			lore = new ArrayList<String>();
		} else {
			lore = im.getLore();
		}

		if (lineCheck(lore))
			return im;

		// Doesnt contain line
		int index = getKeywordLine(lore);

		String legendLine = loreMessage;
		legendLine = legendLine.replaceAll("%player%", player.getName());
		legendLine = legendLine.replaceAll("&", "§");
		lore.set(index, legendLine);
		im.setLore(lore);
		return im;
	}

	private static int getKeywordLine(List<String> lore) {
		// Here we need to check for the keyword
		int index = 0;
		for (String line : lore) {
			if (line.contains(keyword)) {
				if(!lore.get(index + 2).isEmpty()) {
					return index + 1;
				}
				return index + 2;
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
	private static boolean lineCheck(List<String> lore) {
		String[] compareSplit = loreMessage.split("\\s+");
		for (String line : lore) {
			String[] lineSplit = line.split("\\s+");
			int flagCounter = 0;
			lineLoop: for (int i = 0; i < lineSplit.length; i++) {
				if (i >= compareSplit.length)
					break lineLoop;
				if (lineSplit[i].equalsIgnoreCase(compareSplit[i])) {
					flagCounter++;
				}
			}
			if (flagCounter >= (compareSplit.length / 2)) {
				return true;
			}
		}
		return false;
	}
}

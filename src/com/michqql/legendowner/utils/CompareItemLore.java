package com.michqql.legendowner.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class CompareItemLore {
	
	private String lineToCompare;
	
	public CompareItemLore(String line) {
		lineToCompare = line;
	}
	
	public ItemMeta compare(Player player, ItemMeta im) {
		
		List<String> lore;
		if(im.getLore() == null || (!im.hasLore())) {
			lore = new ArrayList<String>();
		} else {
			lore = im.getLore();
		}
		
		if(lineCheck(lore)) return im;
		
		//Doesnt contain line
		String legendLine = lineToCompare;
		legendLine = legendLine.replaceAll("%player%", player.getName());
		legendLine = legendLine.replaceAll("&", "§");
		lore.add(legendLine);
		im.setLore(lore);
		return im;
	}
	
	private boolean lineCheck(List<String> lore) {
		String[] compareSplit = lineToCompare.split("\\s+");
		for(String line : lore) {
			String[] lineSplit = line.split("\\s+");
			int flagCounter = 0;
			lineLoop: for(int i = 0; i < lineSplit.length; i++) {
				if(i >= compareSplit.length) break lineLoop;
				if(lineSplit[i].equalsIgnoreCase(compareSplit[i])) {
					flagCounter++;
				}
			}
			if(flagCounter >= (compareSplit.length / 2)) {
				return true;
			}
		}
		return false;
	}
}

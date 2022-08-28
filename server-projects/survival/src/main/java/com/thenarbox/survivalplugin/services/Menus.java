package com.thenarbox.survivalplugin.services;

import com.thenarbox.api.ChatNotice;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Menus implements Listener {

    static Inventory inv1;
    static List<String> lore1, lore2;


    // HLAVNÍ MENU

    public static void mainMenu(Player player){
        inv1 = Bukkit.createInventory(null, 45, "Hlavní menu");

        ItemStack item1 = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lPŘÍRODA"));
        lore1 = new ArrayList<>();
        lore1.add(ChatColor.GRAY + " ");
        lore1.add(ChatColor.WHITE + "Náhodná teleportace do přírody.");
        lore1.add(ChatColor.GRAY + " ");
        lore1.add(ChatColor.WHITE + "Rozloha přírody: " + ChatColor.GREEN + "25000x25000");
        lore1.add(ChatColor.WHITE + "Obtížnost: " + ChatColor.GREEN + "Normální");
        lore1.add(ChatColor.GRAY + " ");
        lore1.add(ChatColor.WHITE + "Klikni pro vstup do přírody!");
        itemMeta1.setLore(lore1);
        item1.setItemMeta(itemMeta1);

        ItemStack item2 = new ItemStack(Material.OAK_SIGN, 1);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lHLAVNÍ MENU"));
        lore2 = new ArrayList<>();
        lore2.add(ChatColor.GRAY + " ");
        lore2.add(ChatColor.WHITE + " ");
        itemMeta2.setLore(lore2);
        item2.setItemMeta(itemMeta2);

        inv1.setItem(20, item1);
        inv1.setItem(4, item2);
        player.openInventory(inv1);
    }


    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Hlavní menu")){
            e.setCancelled(true);
            if(e.getCurrentItem() == null)
                return;

            if (e.getCurrentItem().getType() == Material.GRASS_BLOCK){
                ChatNotice.error(player, Component.text("Tato funkce ještě není implementována!"));
            }
        }
    }

    @EventHandler
    public void interact(PlayerInteractEvent e){
        if (e.getPlayer().getOpenInventory().getTitle().equals("Hlavní menu")){
            e.setCancelled(true);
        }
    }




}

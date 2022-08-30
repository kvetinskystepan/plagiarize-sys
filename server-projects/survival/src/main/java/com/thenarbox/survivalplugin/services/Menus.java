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
import java.util.Objects;

public class Menus implements Listener {

    static Inventory inv1;
    static List<String> lore1, lore2;


    static String difficulty = ChatColor.translateAlternateColorCodes('&', "&x&0&e&f&b&6&dN&x&2&9&f&b&6&eO&x&4&3&f&c&6&fR&x&5&e&f&c&7&0M&x&7&9&f&c&7&0Á&x&9&4&f&c&7&1L&x&a&e&f&d&7&2N&x&c&9&f&d&7&3Í");
    static String rozloha = ChatColor.translateAlternateColorCodes('&', "&x&0&e&f&b&6&d2&x&2&1&f&b&6&e5 &x&3&3&f&b&6&e0&x&4&6&f&c&6&f0&x&5&9&f&c&6&f0 &x&6&c&f&c&7&0x &x&7&e&f&c&7&12&x&9&1&f&c&7&15 &x&a&4&f&d&7&20&x&b&6&f&d&7&20&x&c&9&f&d&7&30");
    static String priroda = ChatColor.translateAlternateColorCodes('&', "&x&0&e&f&b&6&d&lP&x&2&d&f&b&6&e&lŘ&x&4&c&f&c&6&f&lÍ&x&6&c&f&c&7&0&lR&x&8&b&f&c&7&1&lO&x&a&a&f&d&7&2&lD&x&c&9&f&d&7&3&lA");
    static String name1 = ChatColor.translateAlternateColorCodes('&', "&x&0&e&f&b&6&d&lH&x&2&3&f&b&6&e&lL&x&3&8&f&b&6&e&lA&x&4&c&f&c&6&f&lV&x&6&1&f&c&7&0&lN&x&7&6&f&c&7&0&lÍ &x&8&b&f&c&7&1&lM&x&9&f&f&d&7&2&lE&x&b&4&f&d&7&2&lN&x&c&9&f&d&7&3&lU");


    // HLAVNÍ MENU

    public static void mainMenu(Player player){
        inv1 = Bukkit.createInventory(null, 45, name1);

        ItemStack item1 = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.setDisplayName(priroda);
        lore1 = new ArrayList<>();
        lore1.add(ChatColor.GRAY + " ");
        lore1.add(ChatColor.WHITE + "Náhodná teleportace do přírody.");
        lore1.add(ChatColor.GRAY + " ");
        lore1.add(ChatColor.WHITE + "Rozloha přírody: " + rozloha);
        lore1.add(ChatColor.WHITE + "Obtížnost: " + difficulty);
        lore1.add(ChatColor.GRAY + " ");
        lore1.add(ChatColor.WHITE + "Klikni pro vstup do přírody!");
        itemMeta1.setLore(lore1);
        item1.setItemMeta(itemMeta1);

        ItemStack item2 = new ItemStack(Material.OAK_SIGN, 1);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName(name1);
        lore2 = new ArrayList<>();
        itemMeta2.setLore(lore2);
        item2.setItemMeta(itemMeta2);

        inv1.setItem(20, item1);
        inv1.setItem(4, item2);
        player.openInventory(inv1);
    }


    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals(name1)){
            e.setCancelled(true);
            if(e.getCurrentItem() == null)
                return;

            if (e.getCurrentItem().getType() == Material.GRASS_BLOCK){
                player.closeInventory();
                player.performCommand("rtp");
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

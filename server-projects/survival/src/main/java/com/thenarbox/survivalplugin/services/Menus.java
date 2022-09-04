package com.thenarbox.survivalplugin.services;

import com.thenarbox.api.ChatNotice;
import com.thenarbox.survivalplugin.SurvivalPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Menus implements Listener {

    static Inventory inv1, inv2, inv3;
    static List<String> lore1, lore2, lore3, lore4;


    // hex: #769544, #0BD35B
    // nether: #FF0624, #FF4B56
    // end: #950EFF, #CF10FF

    static String jobsMenu = ChatColor.translateAlternateColorCodes('&', "&8&lJobs Menu");
    static String hlasovaniOPocasi = ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4H&x&7&2&9&7&4&5L&x&6&f&9&9&4&6A&x&6&b&9&b&4&6S&x&6&8&9&d&4&7O&x&6&4&9&f&4&8V&x&6&1&a&1&4&9Á&x&5&d&a&3&4&9N&x&5&9&a&6&4&aÍ &x&5&6&a&8&4&bO &x&5&2&a&a&4&cZ&x&4&f&a&c&4&cM&x&4&b&a&e&4&dĚ&x&4&8&b&0&4&eN&x&4&4&b&2&4&fĚ &x&4&1&b&4&5&0P&x&3&d&b&6&5&0O&x&3&9&b&8&5&1Č&x&3&6&b&a&5&2A&x&3&2&b&c&5&3S&x&2&f&b&e&5&3Í &x&2&b&c&0&5&4N&x&2&8&c&2&5&5A &x&2&4&c&5&5&6S&x&2&0&c&7&5&6L&x&1&d&c&9&5&7U&x&1&9&c&b&5&8N&x&1&6&c&d&5&9E&x&1&2&c&f&5&9Č&x&0&f&d&1&5&aN&x&0&b&d&3&5&bO");

    static String hlasovaniServer = ChatColor.translateAlternateColorCodes('&', "");

    static String hlasovaniOCase = ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4H&x&7&1&9&8&4&5L&x&6&d&9&a&4&6A&x&6&8&9&d&4&7S&x&6&3&a&0&4&8O&x&5&f&a&2&4&9V&x&5&a&a&5&4&aÁ&x&5&5&a&8&4&bN&x&5&1&a&b&4&cÍ &x&4&c&a&d&4&dO &x&4&7&b&0&4&eZ&x&4&3&b&3&4&fM&x&3&e&b&5&5&0Ě&x&3&a&b&8&5&1N&x&3&5&b&b&5&2Ě &x&3&0&b&d&5&3Č&x&2&c&c&0&5&4A&x&2&7&c&3&5&5S&x&2&2&c&6&5&6U &x&1&e&c&8&5&7N&x&1&9&c&b&5&8A &x&1&4&c&e&5&9D&x&1&0&d&0&5&aE&x&0&b&d&3&5&bN");
    static String hlasovaciMenuMain = ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4&lH&x&6&d&9&a&4&6&lL&x&6&4&9&f&4&8&lA&x&5&b&a&5&4&a&lS&x&5&2&a&a&4&c&lO&x&4&9&a&f&4&e&lV&x&4&1&b&4&5&0&lA&x&3&8&b&9&5&1&lC&x&2&f&b&e&5&3&lÍ &x&2&6&c&4&5&5&lM&x&1&d&c&9&5&7&lE&x&1&4&c&e&5&9&lN&x&0&b&d&3&5&b&lU");
    static String difficulty = ChatColor.translateAlternateColorCodes('&', "&x&0&e&f&b&6&dN&x&2&9&f&b&6&eO&x&4&3&f&c&6&fR&x&5&e&f&c&7&0M&x&7&9&f&c&7&0Á&x&9&4&f&c&7&1L&x&a&e&f&d&7&2N&x&c&9&f&d&7&3Í");
    static String rozloha = ChatColor.translateAlternateColorCodes('&', "&x&c&a&f&f&7&42&x&9&b&f&f&7&35 &x&6&c&f&f&7&10&x&3&c&f&f&7&00&x&0&d&f&f&6&e0 &x&6&c&f&c&7&0x &x&c&a&f&f&7&42&x&9&b&f&f&7&35 &x&6&c&f&f&7&10&x&3&c&f&f&7&00&x&0&d&f&f&6&e0");
    static String priroda = ChatColor.translateAlternateColorCodes('&', "&x&0&b&d&3&5&b&lP&x&1&d&c&9&5&7&lŘ&x&2&f&b&e&5&3&lÍ&x&4&1&b&4&5&0&lR&x&5&2&a&a&4&c&lO&x&6&4&9&f&4&8&lD&x&7&6&9&5&4&4&lA");
    static String name1 = ChatColor.translateAlternateColorCodes('&', "&x&0&b&d&3&5&b&lH&x&1&7&c&c&5&8&lL&x&2&3&c&5&5&6&lA&x&2&f&b&e&5&3&lV&x&3&b&b&7&5&1&lN&x&4&6&b&1&4&e&lÍ &x&5&2&a&a&4&c&lM&x&5&e&a&3&4&9&lE&x&6&a&9&c&4&7&lN&x&7&6&9&5&4&4&lU");


    // PRACOVNI MENU
    public static void jobsMenu(Player player){
        inv3 = Bukkit.createInventory(null, 45, jobsMenu);
        


        player.openInventory(inv3);
    }


    // HLASOVACI MENU
    public static void voteMenuMain(Player player){
        lore3 = new ArrayList<>();
        lore4 = new ArrayList<>();
        inv2 = Bukkit.createInventory(null, 27, hlasovaciMenuMain);


        ItemStack item1 = new ItemStack(Material.CLOCK);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.setDisplayName(hlasovaniOCase);
        lore3.add(" ");
        lore3.add(ChatColor.WHITE + "Po kliknutí zahájíš hlasování");
        lore3.add(ChatColor.WHITE + "o změně času na den!");
        lore3.add(" ");
        lore3.add(ChatColor.DARK_GRAY + "Pokud již den je, hlasovat nemůžeš.");
        itemMeta1.setLore(lore3);
        item1.setItemMeta(itemMeta1);


        inv2.setItem(11, item1);


        ItemStack item2 = new ItemStack(Material.BUCKET);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName(hlasovaniOPocasi);
        lore4.add(" ");
        lore4.add(ChatColor.WHITE + "Po kliknutí zahájíš hlasování");
        lore4.add(ChatColor.WHITE + "o změně počasí na slunečno!");
        lore4.add(" ");
        lore4.add(ChatColor.DARK_GRAY + "Pokud již slunečno je, hlasovat nemůžeš.");
        itemMeta2.setLore(lore4);
        item2.setItemMeta(itemMeta2);


        inv2.setItem(15, item2);
        player.openInventory(inv2);
    }


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

        if (e.getView().getTitle().equals(hlasovaciMenuMain)){
            e.setCancelled(true);
            if (e.getCurrentItem() == null)
                return;
            if (e.getCurrentItem().getType() == Material.CLOCK){
                if (!player.getWorld().getName().equals("world")){
                    ChatNotice.error(player, Component.text("V tomto světě nemůžeš hlasovat!"));
                    player.closeInventory();
                    return;
                }
                if (player.getWorld().getFullTime() < 13000 && player.getWorld().getFullTime() > 1000){
                    ChatNotice.error(player, Component.text("Hlasovat o dni můžeš pouze v noci!"));
                    player.closeInventory();
                    return;
                }
                Voting.changeToDayTimeVoting(SurvivalPlugin.getPlugin(SurvivalPlugin.class), player);
                if (Voting.votedNo.contains(player.getName()) || Voting.votedYes.contains(player.getName())){
                    player.closeInventory();
                }
                else {
                    player.closeInventory();
                    player.performCommand("ano");
                }
            }

            if (e.getCurrentItem().getType() == Material.BUCKET){
                if (!player.getWorld().getName().equals("world")){
                    ChatNotice.error(player, Component.text("V tomto světě nemůžeš hlasovat!"));
                    player.closeInventory();
                    return;
                }
                if (player.getWorld().isThundering() || player.getWorld().hasStorm() || !player.getWorld().isClearWeather()){
                    Voting.changeToSunnyVoting(SurvivalPlugin.getPlugin(SurvivalPlugin.class), player);
                    if (Voting.votedNo.contains(player.getName()) || Voting.votedYes.contains(player.getName())){
                        player.closeInventory();
                    }
                    else {
                        player.closeInventory();
                        player.performCommand("ano");
                    }
                }
                else {
                    ChatNotice.error(player, Component.text("V tuto chvíli nemůžeš hlasovat protože neprobíhá bouře ani neprší!"));
                    player.closeInventory();
                    return;
                }

            }
        }


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
}

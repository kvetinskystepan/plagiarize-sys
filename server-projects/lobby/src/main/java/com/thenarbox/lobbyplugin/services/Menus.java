package com.thenarbox.lobbyplugin.services;

import com.google.common.io.ByteStreams;
import com.sun.tools.javac.Main;
import com.thenarbox.api.ChatNotice;
import com.thenarbox.api.PlayerChangeServerEvent;
import com.thenarbox.api.ping.ServerStatus;
import com.thenarbox.api.services.Server;
import com.thenarbox.lobbyplugin.LobbyPlugin;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Menus
        implements Listener {

    public static Boolean isInventoryOpen = false;

    static Inventory inv5, inv2, inv1, inv, inv3;

    static List<String> lore9, lore15,  lore1, lore2, lore10, lore, lore20, lore30, lore3, lore4, lore5, lore6, lore7, lore8, lore21;

    static String obchodRanky = "&x&9&f&4&7&c&8O&x&a&6&4&e&c&db&x&a&e&5&5&d&2c&x&b&5&5&b&d&7h&x&b&c&6&2&d&co&x&c&4&6&9&e&1d &x&c&b&7&0&e&6s &x&d&3&7&7&e&bR&x&d&a&7&e&f&0a&x&e&1&8&4&f&5n&x&e&9&8&b&f&ak&x&f&0&9&2&f&fy";
    static String obchodDoplnky = "&x&9&f&4&7&c&8O&x&a&5&4&d&c&cb&x&a&b&5&3&d&0c&x&b&2&5&8&d&5h&x&b&8&5&e&d&9o&x&b&e&6&4&d&dd &x&c&4&6&a&e&1s &x&c&b&6&f&e&6D&x&d&1&7&5&e&ao&x&d&7&7&b&e&ep&x&d&d&8&1&f&2l&x&e&4&8&6&f&7ň&x&e&a&8&c&f&bk&x&f&0&9&2&f&fy";

    static String obchodUrovne = "&x&9&f&4&7&c&8O&x&a&5&4&d&c&cb&x&a&b&5&3&d&0h&x&b&2&5&8&d&5o&x&b&8&5&e&d&9d &x&b&e&6&4&d&ds &x&c&4&6&a&e&1Ú&x&c&b&6&f&e&6r&x&d&1&7&5&e&ao&x&d&7&7&b&e&ev&x&d&d&8&1&f&2n&x&e&4&8&6&f&7ě&x&e&a&8&c&f&bm&x&f&0&9&2&f&fi";

    static String passwordChange = "&x&9&f&4&7&c&8Z&x&a&8&4&f&c&em&x&b&1&5&8&d&4ě&x&b&a&6&0&d&an&x&c&3&6&8&e&0a &x&c&c&7&1&e&7h&x&d&5&7&9&e&de&x&d&e&8&1&f&3s&x&e&7&8&a&f&9l&x&f&0&9&2&f&fa";

    // SHOP MENU



    public static void shopMenu(Player player) {
        inv1 = Bukkit.createInventory(null, 45, name1);
        lore3 = new ArrayList<>();
        ItemStack item = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8O&x&a&5&4&c&c&cb&x&a&b&5&2&d&0c&x&b&0&5&7&d&4h&x&b&6&5&c&d&8o&x&b&c&6&2&d&cd&x&c&2&6&7&e&0n&x&c&8&6&d&e&4í &x&c&d&7&2&e&7n&x&d&3&7&7&e&ba&x&d&9&7&d&e&fb&x&d&f&8&2&f&3í&x&e&4&8&7&f&7d&x&e&a&8&d&f&bk&x&f&0&9&2&f&fa"));
        lore3.add(" ");
        lore3.add(ChatColor.GRAY + "Vítej v serverovém obchodě!");
        lore3.add(ChatColor.GRAY + " ");
        lore3.add(ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8Z&x&a&4&4&b&c&bd&x&a&8&4&f&c&ee &x&a&d&5&4&d&1s&x&b&1&5&8&d&4i &x&b&6&5&c&d&7m&x&b&a&6&0&d&aů&x&b&f&6&4&d&dž&x&c&3&6&8&e&0e&x&c&8&6&d&e&4š &x&c&c&7&1&e&7z&x&d&1&7&5&e&aa&x&d&5&7&9&e&dk&x&d&a&7&d&f&0o&x&d&e&8&1&f&3u&x&e&3&8&6&f&6p&x&e&7&8&a&f&9i&x&e&c&8&e&f&ct&x&f&0&9&2&f&f: "));
        lore3.add(ChatColor.GRAY + " ");
        lore3.add(ChatColor.GRAY + " - VIP");
        lore3.add(ChatColor.GRAY + " - Ranky");
        lore3.add(ChatColor.GRAY + " - Doplňky (Prefixy, Suffixy)");
        lore3.add(ChatColor.GRAY + " - Levely");
        lore3.add(ChatColor.GRAY + " ");
        lore3.add(ChatColor.DARK_GRAY + "Jako platidlo zde používáme ");
        lore3.add(ChatColor.DARK_GRAY + "úroveň hráče. V případě, že ");
        lore3.add(ChatColor.DARK_GRAY + "je rank hodnotnější tak reálné peníze");
        item.setItemMeta(meta);
        item.setLore(lore3);



        lore5 = new ArrayList<>();
        ItemStack item2 = new ItemStack(Material.ANVIL, 1);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', obchodRanky));
        lore5.add(" ");
        lore5.add(ChatColor.GRAY + "Zde si můžeš zakoupit ranky.");
        item2.setItemMeta(meta2);
        item2.setLore(lore5);

        lore6 = new ArrayList<>();
        ItemStack item3 = new ItemStack(Material.PAINTING, 1);
        ItemMeta meta3 = item3.getItemMeta();
        meta3.setDisplayName(ChatColor.translateAlternateColorCodes('&', obchodDoplnky));
        lore6.add(" ");
        lore6.add(ChatColor.GRAY + "Zde si můžeš zakoupit doplňky.");
        item3.setItemMeta(meta3);
        item3.setLore(lore6);

        lore7 = new ArrayList<>();
        ItemStack item4 = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta meta4 = item4.getItemMeta();
        meta4.setDisplayName(ChatColor.translateAlternateColorCodes('&', obchodUrovne));
        lore7.add(" ");
        lore7.add(ChatColor.GRAY + "Zde si můžeš zakoupit levely.");
        item4.setItemMeta(meta4);
        item4.setLore(lore7);

        ItemStack item5 = new ItemStack(Material.ARROW, 1);
        ItemMeta meta5 = item5.getItemMeta();
        meta5.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lZpět do profilu"));
        item5.setItemMeta(meta5);

        lore4 = new ArrayList<>();
        ItemStack item6 = new ItemStack(Material.PAPER, 1);
        ItemMeta meta6 = item6.getItemMeta();
        meta6.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8&lI&x&a&9&5&0&c&f&ln&x&b&3&5&a&d&6&lf&x&b&d&6&3&d&d&lo&x&c&8&6&d&e&4&lr&x&d&2&7&6&e&a&lm&x&d&c&7&f&f&1&la&x&e&6&8&9&f&8&lc&x&f&0&9&2&f&f&le"));
        lore4.add(" ");
        lore4.add(ChatColor.WHITE + "Pro další informace koukni na náš web");
        lore4.add(ChatColor.GRAY + " ");
        lore4.add(ChatColor.GRAY + "Klikni pro zobrazení webu nebo přejdi na náš web");
        lore4.add(ChatColor.GRAY + " ");
        lore4.add(ChatColor.GRAY + "                     www.mejs.cz");
        item6.setItemMeta(meta6);
        item6.setLore(lore4);

        ItemStack item7 = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta7 = item7.getItemMeta();
        meta7.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lZavřít obchod"));
        item7.setItemMeta(meta7);


        inv1.setItem(44, item6);
        if (isInventoryOpen){
            inv1.setItem(36, item5);
        }
        else {
            inv1.setItem(36, item7);
        }
        inv1.setItem(21, item2);
        inv1.setItem(22, item3);
        inv1.setItem(23, item4);
        inv1.setItem(4, item);
        player.openInventory(inv1);
    }




    // RANK SHOP

    public static void rankShop(Player player){
        inv3 = Bukkit.createInventory(null, 45, "Obchod s Ranky");
        lore8 = new ArrayList<>();

        ItemStack anvil = new ItemStack(Material.ANVIL, 1);
        ItemMeta meta = anvil.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lObchod s Ranky"));
        lore8.add(" ");
        lore8.add(ChatColor.GRAY + "Zde si můžeš zakoupit ranky.");
        lore8.add(ChatColor.GRAY + " ");
        lore8.add(ChatColor.AQUA + "Ranky lze odemknout pomocí: ");
        lore8.add(ChatColor.GRAY + " ");
        lore8.add(ChatColor.GRAY + " - Úroveně hráče");
        lore8.add(ChatColor.GRAY + " ");
        lore8.add(ChatColor.DARK_GRAY + "Každý rank má vlastní ceny i vzácnost.");
        meta.setLore(lore8);
        anvil.setItemMeta(meta);

        inv3.setItem(4, anvil);
        player.openInventory(inv3);
    }




    // MAIN MENU


    public static void updateInventory(final Player player, final Plugin plugin) {
        String status = PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:32002%");
        String status1 = PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:64000%");
        String status2 = PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:32003%");


        new BukkitRunnable() {
            @Override
            public void run() {
                Inventory inventory = ((HumanEntity) player).getOpenInventory().getTopInventory();

                if (!player.getOpenInventory().getTitle().equals(name)) {
                    cancel();
                    return;
                }

                lore.clear();
                lore.add(ChatColor.GRAY + " ");
                lore.add(ChatColor.translateAlternateColorCodes('&', "&7Status: " + PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:32002%")));
                if (!status.equals("&cOffline")){
                    lore.add(ChatColor.translateAlternateColorCodes('&', "&7Online: &a" + PlaceholderAPI.setPlaceholders(player, "%pinger_players_172.18.0.1:32002%")));
                    lore.add(ChatColor.translateAlternateColorCodes('&', "&7Verze: &x&9&f&4&7&c&81&x&b&3&5&a&d&6.&x&c&8&6&d&e&41&x&d&c&7&f&f&19&x&f&0&9&2&f&f+"));
                    lore.add(ChatColor.GRAY + " ");
                    lore.add(ChatColor.translateAlternateColorCodes('&', "&8&oResidence, Práce, Úkoly a mnoho dalšího..."));
                    lore.add(ChatColor.GRAY + " ");
                    lore.add(ChatColor.GRAY + "Klikni pro vstup do hry!");
                }
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(22, item);


                lore20.clear();
                lore20.add(" ");
                lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Status: " + PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:64000%")));
                if (!status1.equals("&cOffline")){
                    lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Online: &a" + PlaceholderAPI.setPlaceholders(player, "%pinger_players_172.18.0.1:64000%")));
                    lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Doporučená verze: &x&9&f&4&7&c&81&x&b&3&5&a&d&6.&x&c&8&6&d&e&41&x&d&c&7&f&f&19&x&f&0&9&2&f&f+"));
                    lore20.add(ChatColor.GRAY + " ");
                    lore20.add(ChatColor.translateAlternateColorCodes('&', "&8&oServer pro naše stavitele :)"));
                    lore20.add(ChatColor.GRAY + " ");
                }
                meta1.setLore(lore20);
                item1.setItemMeta(meta1);

                if (player.hasPermission("proxyserver.build.join"))
                    inv.setItem(36, item1);

                lore21.clear();
                lore21.add(" ");
                lore21.add(ChatColor.translateAlternateColorCodes('&', "&7Status: " + PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:32003%")));
                if (!status2.equals("&cOffline")){
                    lore21.add(ChatColor.translateAlternateColorCodes('&', "&7Online: &a" + PlaceholderAPI.setPlaceholders(player, "%pinger_players_172.18.0.1:32003%")));
                    lore21.add(ChatColor.translateAlternateColorCodes('&', "&7Verze: &x&9&f&4&7&c&81&x&b&3&5&a&d&6.&x&c&8&6&d&e&41&x&d&c&7&f&f&19&x&f&0&9&2&f&f+"));
                    lore21.add(ChatColor.GRAY + " ");
                    lore21.add(ChatColor.GRAY + "Klikni pro vstup do hry!");
                }
                meta3.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                meta3.setLore(lore21);
                item3.setItemMeta(meta3);
                inv.setItem(21, item3);


            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    static ItemMeta meta;
    static ItemMeta meta1, meta3;
    static ItemStack item;
    static ItemStack item1, item3;

    static String boxfightName = ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8B&x&a&b&5&2&d&0o&x&b&6&5&c&d&8x&x&c&2&6&7&e&0F&x&c&d&7&2&e&7i&x&d&9&7&d&e&fg&x&e&4&8&7&f&7h&x&f&0&9&2&f&ft");

    static String name2 = ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8P&x&a&f&5&6&d&3r&x&b&f&6&5&d&eo&x&d&0&7&4&e&9f&x&e&0&8&3&f&4i&x&f&0&9&2&f&fl");

    static String name1 = ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8O&x&a&f&5&6&d&3b&x&b&f&6&5&d&ec&x&d&0&7&4&e&9h&x&e&0&8&3&f&4o&x&f&0&9&2&f&fd");

    static String name = ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8H&x&a&8&4&f&c&el&x&b&1&5&8&d&4a&x&b&a&6&0&d&av&x&c&3&6&8&e&0n&x&c&c&7&1&e&7í &x&d&5&7&9&e&dM&x&d&e&8&1&f&3e&x&e&7&8&a&f&9n&x&f&0&9&2&f&fu");

    static String friendsSettings = "&x&9&f&4&7&c&8N&x&a&3&4&b&c&ba&x&a&7&4&e&c&ds&x&a&b&5&2&d&0t&x&a&e&5&5&d&2a&x&b&2&5&9&d&5v&x&b&6&5&c&d&8e&x&b&a&6&0&d&an&x&b&e&6&4&d&dí &x&c&2&6&7&e&0s&x&c&6&6&b&e&2y&x&c&9&6&e&e&5s&x&c&d&7&2&e&7t&x&d&1&7&5&e&aé&x&d&5&7&9&e&dm&x&d&9&7&d&e&fu &x&d&d&8&0&f&2p&x&e&1&8&4&f&5ř&x&e&4&8&7&f&7á&x&e&8&8&b&f&at&x&e&c&8&e&f&ce&x&f&0&9&2&f&fl";
    public static void mainMenu(Player player, Plugin plugin)
    {
        String status = PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:32002%");
        String status1 = PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:64000%");
        String status2 = PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:32003%");
        inv = Bukkit.createInventory(null, 45, name);
        item = new ItemStack(Material.GRASS_BLOCK, 1);
        meta = item.getItemMeta();
        String metaName = ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8S&x&a&6&4&e&c&du&x&a&e&5&5&d&2r&x&b&5&5&b&d&7v&x&b&c&6&2&d&ci&x&c&4&6&9&e&1v&x&c&b&7&0&e&6a&x&d&3&7&7&e&bl &x&d&a&7&e&f&01&x&e&1&8&4&f&5.&x&e&9&8&b&f&a1&x&f&0&9&2&f&f9");
        meta.setDisplayName(metaName);
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Status: " + PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:32002%")));
        if (!status.equals("&cOffline")){
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Online: &a" + PlaceholderAPI.setPlaceholders(player, "%pinger_players_172.18.0.1:32002%")));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Verze: &x&9&f&4&7&c&81&x&b&3&5&a&d&6.&x&c&8&6&d&e&41&x&d&c&7&f&f&19&x&f&0&9&2&f&f+"));
            lore.add(ChatColor.GRAY + " ");
            lore.add(ChatColor.translateAlternateColorCodes('&', "&8&oResidence, Práce, Úkoly a mnoho dalšího..."));
            lore.add(ChatColor.GRAY + " ");
            lore.add(ChatColor.GRAY + "Klikni pro vstup do hry!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        lore20 = new ArrayList<>();
        item1 = new ItemStack(Material.WOODEN_AXE, 1);
        meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8B&x&a&7&4&f&c&eu&x&a&f&5&6&d&3i&x&b&7&5&e&d&9l&x&b&f&6&5&d&ed &x&c&8&6&d&e&4S&x&d&0&7&4&e&9e&x&d&8&7&c&e&fr&x&e&0&8&3&f&4v&x&e&8&8&b&f&ae&x&f&0&9&2&f&fr"));
        lore20.add(" ");
        lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Status: " + PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:64000%")));
        if (!status1.equals("&cOffline")){
            lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Online: &a" + PlaceholderAPI.setPlaceholders(player, "%pinger_players_172.18.0.1:64000%")));
            lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Doporučená verze: &x&9&f&4&7&c&81&x&b&3&5&a&d&6.&x&c&8&6&d&e&41&x&d&c&7&f&f&19&x&f&0&9&2&f&f+"));
            lore20.add(ChatColor.GRAY + " ");
            lore20.add(ChatColor.translateAlternateColorCodes('&', "&8&oServer pro naše stavitele :)"));
            lore20.add(ChatColor.GRAY + " ");
        }
        meta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta1.setLore(lore20);
        item1.setItemMeta(meta1);

        String discord = ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8/&x&a&b&5&2&d&0d&x&b&6&5&c&d&8i&x&c&2&6&7&e&0s&x&c&d&7&2&e&7c&x&d&9&7&d&e&fo&x&e&4&8&7&f&7r&x&f&0&9&2&f&fd");

        lore30 = new ArrayList<>();
        ItemStack item2 = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(name);
        lore30.add(ChatColor.GRAY + " ");
        lore30.add(ChatColor.GRAY + "Zde nalezneš veškeré servery");
        lore30.add(ChatColor.GRAY + "a informace o nich. Pokud se chceš");
        lore30.add(ChatColor.GRAY + "připojit k některému serveru, klikni na něj.");
        lore30.add(ChatColor.GRAY + " ");
        lore30.add(ChatColor.WHITE + "Pokud se vyskytne problém, můžeš ");
        lore30.add(ChatColor.WHITE + "nám ho zaslat na náš discord");
        lore30.add(ChatColor.WHITE + "server do ticketu. ");
        lore30.add(ChatColor.GRAY + " ");
        lore30.add(ChatColor.AQUA + discord + ChatColor.GRAY + " pro více informací.");
        meta2.setLore(lore30);
        item2.setItemMeta(meta2);

        lore21 = new ArrayList<>();
        item3 = new ItemStack(Material.IRON_SWORD, 1);
        meta3 = item3.getItemMeta();
        lore21.add(" ");
        lore21.add(ChatColor.translateAlternateColorCodes('&', "&7Status: " + PlaceholderAPI.setPlaceholders(player, "%pinger_isonline_172.18.0.1:32003%")));
        if (!status2.equals("&cOffline")){
            lore21.add(ChatColor.translateAlternateColorCodes('&', "&7Online: &a" + PlaceholderAPI.setPlaceholders(player, "%pinger_players_172.18.0.1:32003%")));
            lore21.add(ChatColor.translateAlternateColorCodes('&', "&7Doporučená verze: &x&9&f&4&7&c&81&x&b&3&5&a&d&6.&x&c&8&6&d&e&41&x&d&c&7&f&f&19&x&f&0&9&2&f&f+"));
            lore21.add(ChatColor.GRAY + " ");
            lore21.add(ChatColor.GRAY + "Klikni pro vstup do hry!");
        }
        meta3.setLore(lore21);
        meta3.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta3.setDisplayName(boxfightName);
        item3.setItemMeta(meta3);

        ItemStack item4 = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta4 = item4.getItemMeta();
        meta4.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTak tady se ještě pracuje.."));
        item4.setItemMeta(meta4);


        inv.setItem(4, item2);
        if (player.hasPermission("proxyserver.build.join"))
            inv.setItem(36, item1);
        inv.setItem(21, item3);
        inv.setItem(22, item);
        inv.setItem(23, item4);
        player.openInventory(inv);
        updateInventory(player, plugin);
    }



    // PROFILE MENU



    public static void profileMenu(Player player){
        Inventory profile = Bukkit.createInventory(null, 27, name2);
        lore1 = new ArrayList<>();

        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fInformace o &x&b&8&7&0&c&3&l" + player.getName()));
        meta.setOwner(player.getName());
        item.setItemMeta(meta);

        String rank = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
        String rank1 = rank.toLowerCase();
        String duration = PlaceholderAPI.setPlaceholders(player, " %luckperms_group_expiry_time_"+rank1+"%");
        lore1.add(" ");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8&lR&x&a&f&5&6&d&3&la&x&b&f&6&5&d&e&ln&x&d&0&7&4&e&9&lk&x&e&0&8&3&f&4&ly&x&f&0&9&2&f&f&l:"));
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&fRank: &x&b&8&7&0&c&3" + rank));
        if (duration.equals(" ")){
            lore1.add(ChatColor.translateAlternateColorCodes('&', "&fExpirace: &x&b&8&7&0&c&3Tvůj rank je permanentní"));
        }
        else {
            lore1.add(ChatColor.translateAlternateColorCodes('&', "&fExpirace:&x&b&8&7&0&c&3" + duration));
        }
        lore1.add(" ");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8&lÚ&x&a&d&5&4&d&1&lr&x&b&a&6&0&d&a&lo&x&c&8&6&d&e&4&lv&x&d&5&7&9&e&d&le&x&e&3&8&6&f&6&lň&x&f&0&9&2&f&f&l:"));
        String level = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points_formatted%");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&fÚroveň: &x&b&8&7&0&c&3" + level));
        lore1.add(" ");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8&lP&x&a&b&5&2&d&0&lř&x&b&6&5&c&d&8&lá&x&c&2&6&7&e&0&lt&x&c&d&7&2&e&7&le&x&d&9&7&d&e&f&ll&x&e&4&8&7&f&7&lé&x&f&0&9&2&f&f&l:"));
        String friends = PlaceholderAPI.setPlaceholders(player, "%friendsapi_friendcount%");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&fPočet přátel: &x&b&8&7&0&c&3" + friends));
        String pending = PlaceholderAPI.setPlaceholders(player, "%friendsapi_friendrequestcount%");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&fPočet přátel čekajících na odpověď: &x&b&8&7&0&c&3" + pending));

        item.setLore(lore1);

        lore2 = new ArrayList<>();
        ItemStack item1 = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(name1);
        lore2.add(" ");
        lore2.add(ChatColor.DARK_GRAY + "Zde můžeš nakoupit ranky, doplňky, a další");
        item1.setItemMeta(meta1);
        item1.setLore(lore2);

        lore10 = new ArrayList<>();
        ItemStack item2 = new ItemStack(Material.REDSTONE_TORCH, 1);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8N&x&a&9&5&0&c&fa&x&b&3&5&a&d&6s&x&b&d&6&3&d&dt&x&c&8&6&d&e&4a&x&d&2&7&6&e&av&x&d&c&7&f&f&1e&x&e&6&8&9&f&8n&x&f&0&9&2&f&fí"));
        lore10.add(" ");
        lore10.add(ChatColor.DARK_GRAY + "Zde si můžeš nastavit žádosti ");
        lore10.add(ChatColor.DARK_GRAY + "o přátelství, změnit heslo, a další");
        item2.setItemMeta(meta2);
        item2.setLore(lore10);

        profile.setItem(11, item1);
        profile.setItem(13, item);
        profile.setItem(15, item2);

        player.openInventory(profile);
    }


    // SETTINGS MENU



    public static void settingsMenu(Player player){
        inv2 = Bukkit.createInventory(null, 27, "Nastavení");
        lore9 = new ArrayList<>();
        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', passwordChange));
        lore9.add(ChatColor.GRAY + " ");
        lore9.add(ChatColor.DARK_GRAY + "Klikni pro změnu hesla");
        meta.setLore(lore9);
        item.setItemMeta(meta);

        ItemStack item5 = new ItemStack(Material.ARROW, 1);
        ItemMeta meta5 = item5.getItemMeta();
        meta5.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lZpět do profilu"));
        item5.setItemMeta(meta5);

        lore15 = new ArrayList<>();
        ItemStack item1 = new ItemStack(Material.BOOK, 1);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', friendsSettings));
        lore15.add(ChatColor.GRAY + " ");
        lore15.add(ChatColor.WHITE + "Nastavit si upozornění, žádosti,");
        lore15.add(ChatColor.WHITE + "a další si můžeš pomocí: ");
        lore15.add(ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8/&x&a&4&4&c&c&cf&x&a&a&5&1&c&fr&x&a&f&5&6&d&3i&x&b&5&5&b&d&7e&x&b&a&6&0&d&an&x&b&f&6&5&d&ed&x&c&5&6&a&e&2s &x&c&a&6&f&e&5s&x&d&0&7&4&e&9e&x&d&5&7&9&e&dt&x&d&a&7&e&f&0t&x&e&0&8&3&f&4i&x&e&5&8&8&f&8n&x&e&b&8&d&f&bg&x&f&0&9&2&f&fs"));
        item1.setItemMeta(meta1);
        item1.setLore(lore15);

        inv2.setItem(18, item5);
        inv2.setItem(12, item1);
        inv2.setItem(11, item);
        player.openInventory(inv2);
    }


    // CLICK EVENT




    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equals("Nastavení systému přátel")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null)
                return;

            if(e.getCurrentItem().getType() == Material.ARROW){
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                settingsMenu(player);
            }
        }

        if(e.getView().getTitle().equals("Nastavení")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null)
                return;

            if (e.getCurrentItem().getType() == Material.ARROW){
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                profileMenu(player);
                return;
            }

            if (e.getCurrentItem().getType() == Material.TRIPWIRE_HOOK) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                player.closeInventory();
                player.performCommand("cp");
            }
        }


        if (e.getView().getTitle().equals(name1)){
            e.setCancelled(true);
            if (e.getCurrentItem() == null)
                return;

            if (e.getCurrentItem().getType() == Material.ARROW){
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                profileMenu(player);
                return;
            }
            if (e.getCurrentItem().getType() == Material.BARRIER){
                player.closeInventory();
                return;
            }
            if (e.getCurrentItem().getType() == Material.ANVIL){
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                ChatNotice.error(player, Component.text("Tento obchod je momentálně nedostupný."));
                player.closeInventory();
                return;
            }
            if (e.getCurrentItem().getType() == Material.EXPERIENCE_BOTTLE){
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                ChatNotice.error(player, Component.text("Tento obchod je momentálně nedostupný."));
                player.closeInventory();
                return;
            }
            if (e.getCurrentItem().getType() == Material.PAINTING){
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                ChatNotice.error(player, Component.text("Tento obchod je momentálně nedostupný."));
                player.closeInventory();
                return;
            }
            if (e.getCurrentItem().getType() == Material.PAPER){
                TextComponent mainComponent = new TextComponent( "Náš web: " );
                TextComponent subComponent = new TextComponent( "www.mejs.cz" );
                subComponent.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Klikni pro otevření" ).create() ) );
                subComponent.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "https://mejs.cz" ) );
                mainComponent.addExtra( subComponent );
                player.closeInventory();
                ChatNotice.infoHover(player, mainComponent);
            }
        }


        if(e.getView().getTitle().equals(name2)){
            e.setCancelled(true);
            if(e.getCurrentItem() == null){
                return;
            }

            if (e.getCurrentItem().getType() == Material.NAME_TAG){
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                isInventoryOpen = true;
                shopMenu(player);
            }
            if(e.getCurrentItem().getType() == Material.REDSTONE_TORCH){
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                settingsMenu(player);
            }
        }

        if (e.getView().getTitle().equals(name)){
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            }

            final String name;
            final int port;
            switch (e.getCurrentItem().getType()) {
                case GRASS_BLOCK -> { name = "Survival"; port = 32002; }
                case WOODEN_AXE ->  { name = "Build";    port = 64000; }
                case IRON_SWORD ->  { name = "BoxFight"; port = 32003; }
                default -> { name = null; port = -1; }
            }
            if(port == -1)
                return;

            ServerStatus.queryServerStatus("172.18.0.1", port).thenAccept(result -> {
                if(result != null) {
                    Bukkit.getScheduler().runTask(LobbyPlugin.getInstance(), () -> {
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        PlayerChangeServerEvent.connect(player, name);
                        player.closeInventory();
                    });
                } else {
                    Bukkit.getScheduler().runTask(LobbyPlugin.getInstance(), () -> {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                        ChatNotice.error(player, Component.text("Server je momentálně nedostupný."));
                        player.closeInventory();
                    });
                }
            });
        }
    }
}

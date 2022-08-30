package com.thenarbox.lobbyplugin.services;

import com.thenarbox.api.ChatNotice;
import com.thenarbox.api.PlayerChangeServerEvent;
import com.thenarbox.api.services.Server;
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
import java.util.Timer;
import java.util.TimerTask;

public class Menus
        implements Listener {

    public static Boolean isInventoryOpen = false;

    static Inventory inv5, inv2, inv1, inv, inv3;

    static List<String> lore9, lore15,  lore1, lore2, lore10, lore, lore20, lore30, lore3, lore4, lore5, lore6, lore7, lore8;



    // SHOP MENU



    public static void shopMenu(Player player) {
        inv1 = Bukkit.createInventory(null, 45, name1);
        lore3 = new ArrayList<>();
        ItemStack item = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lObchodní nabídka"));
        lore3.add(" ");
        lore3.add(ChatColor.GRAY + "Vítej v serverovém obchodě!");
        lore3.add(ChatColor.GRAY + " ");
        lore3.add(ChatColor.AQUA + "Zde si můžeš zakoupit: ");
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
        meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lObchod s Ranky"));
        lore5.add(" ");
        lore5.add(ChatColor.GRAY + "Zde si můžeš zakoupit ranky.");
        lore5.add(ChatColor.GRAY + " ");
        lore5.add(ChatColor.AQUA + "Platidlem zde je: ");
        lore5.add(ChatColor.GRAY + " ");
        lore5.add(ChatColor.GRAY + " - Úroveň hráče");
        lore5.add(ChatColor.GRAY + " ");
        item2.setItemMeta(meta2);
        item2.setLore(lore5);

        lore6 = new ArrayList<>();
        ItemStack item3 = new ItemStack(Material.PAINTING, 1);
        ItemMeta meta3 = item3.getItemMeta();
        meta3.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lObchod s Doplňky"));
        lore6.add(" ");
        lore6.add(ChatColor.GRAY + "Zde si můžeš zakoupit doplňky.");
        lore6.add(ChatColor.GRAY + " ");
        lore6.add(ChatColor.AQUA + "Platidlem zde je: ");
        lore6.add(ChatColor.GRAY + " ");
        lore6.add(ChatColor.GRAY + " - Úroveň hráče");
        lore6.add(ChatColor.GRAY + " - Reálné peníze");
        lore6.add(ChatColor.GRAY + " - Pro VIP zdarma některé doplňky");
        lore6.add(ChatColor.GRAY + " ");
        item3.setItemMeta(meta3);
        item3.setLore(lore6);

        lore7 = new ArrayList<>();
        ItemStack item4 = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta meta4 = item4.getItemMeta();
        meta4.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lObchod s Levely"));
        lore7.add(" ");
        lore7.add(ChatColor.GRAY + "Zde si můžeš zakoupit levely.");
        lore7.add(ChatColor.GRAY + " ");
        lore7.add(ChatColor.AQUA + "Platidlem zde jsou: ");
        lore7.add(ChatColor.GRAY + " ");
        lore7.add(ChatColor.GRAY + " - Reálné peníze");
        lore6.add(ChatColor.GRAY + " - Pro VIP automaticky +X levelů");
        lore7.add(ChatColor.GRAY + " ");
        item4.setItemMeta(meta4);
        item4.setLore(lore7);

        ItemStack item5 = new ItemStack(Material.ARROW, 1);
        ItemMeta meta5 = item5.getItemMeta();
        meta5.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lZpět do profilu"));
        item5.setItemMeta(meta5);

        lore4 = new ArrayList<>();
        ItemStack item6 = new ItemStack(Material.PAPER, 1);
        ItemMeta meta6 = item6.getItemMeta();
        meta6.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lInformace"));
        lore4.add(" ");
        lore4.add(ChatColor.AQUA + "Pro další informace koukni na náš web");
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
                lore.add(ChatColor.translateAlternateColorCodes('&', "&7Status: " + Server.status("172.18.0.1", 32002)));
                if (!Server.status("172.18.0.1", 32002).equals(ChatColor.RED + "Offline")){
                    lore.add(ChatColor.translateAlternateColorCodes('&', "&7Online: &a" + PlaceholderAPI.setPlaceholders(player, "%pinger_players_172.18.0.1:32002%")));
                    lore.add(ChatColor.translateAlternateColorCodes('&', "&7Verze: &x&0&b&f&f&d&31&x&0&f&d&7&c&5.&x&1&3&a&e&b&71&x&1&7&8&6&a&99&x&1&b&5&d&9&b+"));
                    lore.add(ChatColor.GRAY + " ");
                    lore.add(ChatColor.translateAlternateColorCodes('&', "&8&oResidence, Práce, Úkoly a mnoho dalšího..."));
                    lore.add(ChatColor.GRAY + " ");
                    lore.add(ChatColor.GRAY + "Klikni pro vstup do hry!");
                }
                meta.setLore(lore);
                item.setItemMeta(meta);


                lore20.clear();
                lore20.add(" ");
                lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Status: " + Server.status("172.18.0.1", 64000)));
                if (!Server.status("172.18.0.1", 64000).equals(ChatColor.RED + "Offline")){
                    lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Online: &a" + PlaceholderAPI.setPlaceholders(player, "%pinger_players_172.18.0.1:64000%")));
                    lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Doporučená verze: &x&0&b&f&f&d&31&x&0&f&d&7&c&5.&x&1&3&a&e&b&71&x&1&7&8&6&a&99&x&1&b&5&d&9&b+"));
                    lore20.add(ChatColor.GRAY + " ");
                    lore20.add(ChatColor.translateAlternateColorCodes('&', "&8&oServer pro naše stavitele :)"));
                    lore20.add(ChatColor.GRAY + " ");
                }
                meta1.setLore(lore20);
                item1.setItemMeta(meta1);

                if (player.hasPermission("proxyserver.build.join"))
                    inv.setItem(36, item1);
                inv.setItem(21, item);


            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    static ItemMeta meta;
    static ItemMeta meta1;
    static ItemStack item;
    static ItemStack item1;

    static String name2 = ChatColor.translateAlternateColorCodes('&', "&x&0&b&f&f&d&3&lP&x&1&a&e&b&b&0&lR&x&2&a&d&7&8&d&lO&x&3&9&c&3&6&a&lF&x&4&9&a&f&4&7&lI&x&5&8&9&b&2&4&lL");

    static String name1 = ChatColor.translateAlternateColorCodes('&', "&x&0&b&f&f&d&3&lO&x&1&a&e&b&b&0&lB&x&2&a&d&7&8&d&lC&x&3&9&c&3&6&a&lH&x&4&9&a&f&4&7&lO&x&5&8&9&b&2&4&lD");

    static String name = ChatColor.translateAlternateColorCodes('&', "&x&0&b&f&f&d&3&lH&x&1&4&f&4&c&0&lL&x&1&c&e&9&a&c&lA&x&2&5&d&e&9&9&lV&x&2&d&d&3&8&5&lN&x&3&6&c&7&7&2&lÍ &x&3&e&b&c&5&e&lM&x&4&7&b&1&4&b&lE&x&4&f&a&6&3&7&lN&x&5&8&9&b&2&4&lU");

    public static void mainMenu(Player player, Plugin plugin)
    {
        inv = Bukkit.createInventory(null, 45, name);
        item = new ItemStack(Material.GRASS_BLOCK, 1);
        meta = item.getItemMeta();
        String metaName = ChatColor.translateAlternateColorCodes('&', "&x&0&b&f&f&d&3&lS&x&0&c&f&2&c&e&lU&x&0&e&e&4&c&a&lR&x&0&f&d&7&c&5&lV&x&1&0&c&9&c&0&lI&x&1&2&b&c&b&c&lV&x&1&3&a&e&b&7&lA&x&1&4&a&1&b&2&lL &x&1&6&9&3&a&e&l1&x&1&7&8&6&a&9&l.&x&1&8&7&8&a&4&l1&x&1&a&6&b&a&0&l9&x&1&b&5&d&9&b&l+");
        meta.setDisplayName(metaName);
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Status: " + Server.status("172.18.0.1", 32002)));
        if (!Server.status("172.18.0.1", 32002).equals(ChatColor.RED + "Offline")){
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Online: &a" + PlaceholderAPI.setPlaceholders(player, "%pinger_players_172.18.0.1:32002%")));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Verze: &x&0&b&f&f&d&31&x&0&f&d&7&c&5.&x&1&3&a&e&b&71&x&1&7&8&6&a&99&x&1&b&5&d&9&b+"));
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
        meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&x&0&b&f&f&d&3&lB&x&0&d&e&f&c&d&lU&x&0&e&d&f&c&8&lI&x&1&0&c&e&c&2&lL&x&1&1&b&e&b&d&lD &x&1&3&a&e&b&7&lS&x&1&5&9&e&b&1&lE&x&1&6&8&e&a&c&lR&x&1&8&7&d&a&6&lV&x&1&9&6&d&a&1&lE&x&1&b&5&d&9&b&lR"));
        lore20.add(" ");
        lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Status: " + Server.status("172.18.0.1", 64000)));
        if (!Server.status("172.18.0.1", 64000).equals(ChatColor.RED + "Offline")){
            lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Online: &a" + PlaceholderAPI.setPlaceholders(player, "%pinger_players_172.18.0.1:64000%")));
            lore20.add(ChatColor.translateAlternateColorCodes('&', "&7Doporučená verze: &x&0&b&f&f&d&31&x&0&f&d&7&c&5.&x&1&3&a&e&b&71&x&1&7&8&6&a&99&x&1&b&5&d&9&b+"));
            lore20.add(ChatColor.GRAY + " ");
            lore20.add(ChatColor.translateAlternateColorCodes('&', "&8&oServer pro naše stavitele :)"));
            lore20.add(ChatColor.GRAY + " ");
        }
        meta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta1.setLore(lore20);
        item1.setItemMeta(meta1);

        String discord = ChatColor.translateAlternateColorCodes('&', "&x&0&b&f&f&d&3/&x&0&d&e&8&c&bd&x&1&0&d&1&c&3i&x&1&2&b&a&b&bs&x&1&4&a&2&b&3c&x&1&6&8&b&a&bo&x&1&9&7&4&a&3r&x&1&b&5&d&9&bd");

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

        ItemStack item3 = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta3 = item3.getItemMeta();
        meta3.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTak tady se ještě pracuje.."));
        item3.setItemMeta(meta3);

        ItemStack item4 = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta4 = item4.getItemMeta();
        meta4.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTak tady se ještě pracuje.."));
        item4.setItemMeta(meta4);


        inv.setItem(4, item2);
        if (player.hasPermission("proxyserver.build.join"))
            inv.setItem(36, item1);
        inv.setItem(21, item);
        inv.setItem(22, item3);
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
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fInformace o &b&l" + player.getName()));
        meta.setOwner(player.getName());
        item.setItemMeta(meta);

        String rank = PlaceholderAPI.setPlaceholders(player, "%luckperms_suffix%");
        String rank1 = rank.toLowerCase();
        String duration = PlaceholderAPI.setPlaceholders(player, " %luckperms_group_expiry_time_"+rank1+"%");
        lore1.add(" ");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&b&lRanky"));
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&fRank: " + ChatColor.AQUA + rank));
        if (duration.equals(" ")){
            lore1.add(ChatColor.translateAlternateColorCodes('&', "&fExpirace: " + ChatColor.AQUA + "Tvůj rank je permanentní"));
        }
        else {
            lore1.add(ChatColor.translateAlternateColorCodes('&', "&fExpirace:" + ChatColor.AQUA + duration));
        }
        lore1.add(" ");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&b&lÚroveň"));
        String level = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points_formatted%");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&fÚroveň: " + ChatColor.AQUA + level));
        lore1.add(" ");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&b&lPřátelé"));
        String friends = PlaceholderAPI.setPlaceholders(player, "%friendsapi_friendcount%");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&fPočet přátel: " + ChatColor.AQUA + friends));
        String pending = PlaceholderAPI.setPlaceholders(player, "%friendsapi_friendrequestcount%");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&fPočet přátel čekajících na odpověď: " + ChatColor.AQUA + pending));

        item.setLore(lore1);

        lore2 = new ArrayList<>();
        ItemStack item1 = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lObchod"));
        lore2.add(" ");
        lore2.add(ChatColor.DARK_GRAY + "Zde můžeš nakoupit ranky, doplňky, a další");
        item1.setItemMeta(meta1);
        item1.setLore(lore2);

        lore10 = new ArrayList<>();
        ItemStack item2 = new ItemStack(Material.REDSTONE_TORCH, 1);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lNastavení"));
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
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lZměna hesla"));
        lore9.add(ChatColor.GRAY + " ");
        lore9.add(ChatColor.GRAY + "Klikni pro změnu hesla");
        meta.setLore(lore9);
        item.setItemMeta(meta);

        ItemStack item5 = new ItemStack(Material.ARROW, 1);
        ItemMeta meta5 = item5.getItemMeta();
        meta5.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lZpět do profilu"));
        item5.setItemMeta(meta5);

        lore15 = new ArrayList<>();
        ItemStack item1 = new ItemStack(Material.BOOK, 1);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lNastavení systému přátel"));
        lore15.add(ChatColor.GRAY + " ");
        lore15.add(ChatColor.GRAY + "Klikni pro nastavení systému přátel");
        item1.setItemMeta(meta1);
        item1.setLore(lore15);

        inv2.setItem(18, item5);
        inv2.setItem(12, item1);
        inv2.setItem(11, item);
        player.openInventory(inv2);
    }


    // FRIENDS SETTINGS



    public static void friendsSettings(Player player){
        inv5 = Bukkit.createInventory(null, 9, "Nastavení systému přátel");

        ItemStack item5 = new ItemStack(Material.ARROW, 1);
        ItemMeta meta5 = item5.getItemMeta();
        meta5.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lZpět do nastavení"));
        item5.setItemMeta(meta5);

        inv5.setItem(8, item5);

        player.openInventory(inv5);
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

            if (e.getCurrentItem().getType() == Material.BOOK){
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                friendsSettings(player);
                return;
            }

            if (e.getCurrentItem().getType() == Material.TRIPWIRE_HOOK) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                player.closeInventory();
                ChatNotice.success(player, Component.text("Kontaktuji autentifikační server..."));
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
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                rankShop(player);
                return;
            }
            if (e.getCurrentItem().getType() == Material.PAPER){
                TextComponent mainComponent = new TextComponent( "Náš web (klikni pro otevření): " );
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

            if (e.getCurrentItem().getType() == Material.GRASS_BLOCK){
                if (!Server.status("172.18.0.1", 32002).equals(ChatColor.RED + "Offline")){
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    PlayerChangeServerEvent.connect(player, "Survival");
                    player.closeInventory();
                }
                else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                    player.closeInventory();
                    ChatNotice.error(player, Component.text("Server je offline!"));
                }
            }
            if (e.getCurrentItem().getType() == Material.WOODEN_AXE){
                if (!Server.status("172.18.0.1", 64000).equals(ChatColor.RED + "Offline")){
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    PlayerChangeServerEvent.connect(player, "Build");
                    player.closeInventory();
                }
                else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                    player.closeInventory();
                    ChatNotice.error(player, Component.text("Server je offline!"));
                }
            }
        }
    }
}

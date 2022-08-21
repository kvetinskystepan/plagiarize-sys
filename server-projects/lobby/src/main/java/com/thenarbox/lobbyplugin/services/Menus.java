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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class Menus
        implements Listener {

    public static Boolean isInventoryOpen = false;
    static List<String> lore3;
    static List<String> lore4;
    static List<String> lore5;
    static List<String> lore6;
    static List<String> lore7;
    static Inventory inv1;
    public static void shopMenu(Player player) {
        inv1 = Bukkit.createInventory(null, 45, "Obchod");

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

    static List<String> lore;
    public static void mainMenu(Player player)
    {
        Inventory inv = Bukkit.createInventory(null, 27, "Hlavní menu");
        ItemStack item = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lSURVIVAL CLASSIC"));
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Status: " + Server.status("172.18.0.1", 32002)));
        if (!Server.status("172.18.0.1", 32002).equals(ChatColor.RED + "Offline")){
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Online: &a" + Server.PlayerCount("172.18.0.1", 32002)));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Verze: &b" + Server.version("172.18.0.1", 32002)));
            lore.add(ChatColor.GRAY + " ");
            lore.add(ChatColor.translateAlternateColorCodes('&', "&8&oResidence, Práce, Úkoly a mnoho dalšího..."));
            lore.add(ChatColor.GRAY + " ");
            lore.add(ChatColor.GRAY + "Klikni pro vstup do hry!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(13, item);
        player.openInventory(inv);
    }


    static List<String> lore1;
    static List<String> lore2;
    static List<String> lore10;
    public static void profileMenu(Player player){
        Inventory profile = Bukkit.createInventory(null, 27, "Profil");
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
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&fRank: " + ChatColor.AQUA + rank));
        if (duration.equals(" ")){
            lore1.add(ChatColor.translateAlternateColorCodes('&', "&fExpirace: " + ChatColor.AQUA + "Tvůj rank je permanentní"));
        }
        else {
            lore1.add(ChatColor.translateAlternateColorCodes('&', "&fExpirace:" + ChatColor.AQUA + duration));
        }
        lore1.add(" ");
        String level = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points_formatted%");
        lore1.add(ChatColor.translateAlternateColorCodes('&', "&fÚroveň: " + ChatColor.AQUA + level));

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

        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
        player.openInventory(profile);
    }

    static List<String> lore9;
    static List<String> lore15;
    static Inventory inv2;
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

    static Inventory inv5;
    public static void friendsSettings(Player player){
        inv5 = Bukkit.createInventory(null, 9, "Nastavení systému přátel");

        ItemStack item5 = new ItemStack(Material.ARROW, 1);
        ItemMeta meta5 = item5.getItemMeta();
        meta5.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lZpět do nastavení"));
        item5.setItemMeta(meta5);

        inv5.setItem(8, item5);

        player.openInventory(inv5);
    }


    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equals("Nastavení systému přátel")){
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == null){
                return;
            }
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
                if (!Server.status("172.18.0.1", 32003).equals(ChatColor.RED + "Offline")){
                    PlayerChangeServerEvent.connect(player, "PasswordChange");
                }
                else {
                    ChatNotice.error(player, Component.text("Autentifikační server je offline!"));
                }
            }
        }


        if (e.getView().getTitle().equals("Obchod")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null)
                return;
            if (e.getCurrentItem().getType() == Material.ARROW){
                profileMenu(player);
                return;
            }
            if (e.getCurrentItem().getType() == Material.BARRIER){
                player.closeInventory();
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


        if(e.getView().getTitle().equals("Profil")){
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

        if (e.getView().getTitle().equals("Hlavní menu")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            }
            if (e.getCurrentItem().getType() == Material.GRASS_BLOCK){
                if (!Server.status("172.18.0.1", 32002).equals(ChatColor.RED + "Offline")){
                    PlayerChangeServerEvent.connect(player, "Survival");
                }
                else {
                    ChatNotice.error(player, Component.text("Server je offline!"));
                }
            }
        }
    }


}

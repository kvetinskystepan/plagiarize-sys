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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Menus implements Listener {

    static Inventory inv1, inv2, inv3;
    static List<String> lore1, lore2, lore3, lore4, lore5, lore6, lore7, lore8, lore9, lore10, lore11;


    // hex: #769544, #0BD35B
    // nether: #FF0624, #FF4B56
    // end: #950EFF, #CF10FF

    static String jobsMenu = ChatColor.translateAlternateColorCodes('&', "&8&lJobs Menu");
    static String hlasovaniOPocasi = ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4H&x&7&2&9&7&4&5L&x&6&f&9&9&4&6A&x&6&b&9&b&4&6S&x&6&8&9&d&4&7O&x&6&4&9&f&4&8V&x&6&1&a&1&4&9Á&x&5&d&a&3&4&9N&x&5&9&a&6&4&aÍ &x&5&6&a&8&4&bO &x&5&2&a&a&4&cZ&x&4&f&a&c&4&cM&x&4&b&a&e&4&dĚ&x&4&8&b&0&4&eN&x&4&4&b&2&4&fĚ &x&4&1&b&4&5&0P&x&3&d&b&6&5&0O&x&3&9&b&8&5&1Č&x&3&6&b&a&5&2A&x&3&2&b&c&5&3S&x&2&f&b&e&5&3Í &x&2&b&c&0&5&4N&x&2&8&c&2&5&5A &x&2&4&c&5&5&6S&x&2&0&c&7&5&6L&x&1&d&c&9&5&7U&x&1&9&c&b&5&8N&x&1&6&c&d&5&9E&x&1&2&c&f&5&9Č&x&0&f&d&1&5&aN&x&0&b&d&3&5&bO");

    static String residence = ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4&lR&x&6&9&9&d&4&7&lE&x&5&b&a&5&4&a&lS&x&4&e&a&c&4&d&lI&x&4&1&b&4&5&0&lD&x&3&3&b&c&5&2&lE&x&2&6&c&4&5&5&lN&x&1&8&c&b&5&8&lC&x&0&b&d&3&5&b&lE");

    static String hlasovaniOCase = ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4H&x&7&1&9&8&4&5L&x&6&d&9&a&4&6A&x&6&8&9&d&4&7S&x&6&3&a&0&4&8O&x&5&f&a&2&4&9V&x&5&a&a&5&4&aÁ&x&5&5&a&8&4&bN&x&5&1&a&b&4&cÍ &x&4&c&a&d&4&dO &x&4&7&b&0&4&eZ&x&4&3&b&3&4&fM&x&3&e&b&5&5&0Ě&x&3&a&b&8&5&1N&x&3&5&b&b&5&2Ě &x&3&0&b&d&5&3Č&x&2&c&c&0&5&4A&x&2&7&c&3&5&5S&x&2&2&c&6&5&6U &x&1&e&c&8&5&7N&x&1&9&c&b&5&8A &x&1&4&c&e&5&9D&x&1&0&d&0&5&aE&x&0&b&d&3&5&bN");
    static String hlasovaciMenuMain = ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4&lH&x&6&d&9&a&4&6&lL&x&6&4&9&f&4&8&lA&x&5&b&a&5&4&a&lS&x&5&2&a&a&4&c&lO&x&4&9&a&f&4&e&lV&x&4&1&b&4&5&0&lA&x&3&8&b&9&5&1&lC&x&2&f&b&e&5&3&lÍ &x&2&6&c&4&5&5&lM&x&1&d&c&9&5&7&lE&x&1&4&c&e&5&9&lN&x&0&b&d&3&5&b&lU");
    static String difficulty = ChatColor.translateAlternateColorCodes('&', "&x&0&e&f&b&6&dN&x&2&9&f&b&6&eO&x&4&3&f&c&6&fR&x&5&e&f&c&7&0M&x&7&9&f&c&7&0Á&x&9&4&f&c&7&1L&x&a&e&f&d&7&2N&x&c&9&f&d&7&3Í");
    static String rozloha = ChatColor.translateAlternateColorCodes('&', "&x&c&a&f&f&7&42&x&9&b&f&f&7&35 &x&6&c&f&f&7&10&x&3&c&f&f&7&00&x&0&d&f&f&6&e0 &x&6&c&f&c&7&0x &x&c&a&f&f&7&42&x&9&b&f&f&7&35 &x&6&c&f&f&7&10&x&3&c&f&f&7&00&x&0&d&f&f&6&e0");
    static String priroda = ChatColor.translateAlternateColorCodes('&', "&x&0&b&d&3&5&b&lP&x&1&d&c&9&5&7&lŘ&x&2&f&b&e&5&3&lÍ&x&4&1&b&4&5&0&lR&x&5&2&a&a&4&c&lO&x&6&4&9&f&4&8&lD&x&7&6&9&5&4&4&lA");
    static String name1 = ChatColor.translateAlternateColorCodes('&', "&x&0&b&d&3&5&b&lH&x&1&7&c&c&5&8&lL&x&2&3&c&5&5&6&lA&x&2&f&b&e&5&3&lV&x&3&b&b&7&5&1&lN&x&4&6&b&1&4&e&lÍ &x&5&2&a&a&4&c&lM&x&5&e&a&3&4&9&lE&x&6&a&9&c&4&7&lN&x&7&6&9&5&4&4&lU");

    static String hlasovaniONazvu = ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4H&x&7&0&9&9&4&5L&x&6&9&9&c&4&7A&x&6&3&a&0&4&8S&x&5&d&a&4&4&9O&x&5&7&a&7&4&bV&x&5&0&a&b&4&cÁ&x&4&a&a&f&4&dN&x&4&4&b&2&4&fÍ &x&3&d&b&6&5&0P&x&3&7&b&9&5&2R&x&3&1&b&d&5&3O &x&2&a&c&1&5&4S&x&2&4&c&4&5&6E&x&1&e&c&8&5&7R&x&1&8&c&c&5&8V&x&1&1&c&f&5&aE&x&0&b&d&3&5&bR");
    // HLASOVACI MENU
    public static void voteMenuMain(Player player){
        lore5 = new ArrayList<>();
        lore4 = new ArrayList<>();
        inv2 = Bukkit.createInventory(null, 27, hlasovaciMenuMain);


        ItemStack item1 = new ItemStack(Material.CLOCK);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.setDisplayName(hlasovaniOCase);
        lore5.add(" ");
        lore5.add(ChatColor.WHITE + "Po kliknutí zahájíš hlasování");
        lore5.add(ChatColor.WHITE + "o změně času na den!");
        lore5.add(" ");
        lore5.add(ChatColor.DARK_GRAY + "Pokud již den je, hlasovat nemůžeš.");
        itemMeta1.setLore(lore5);
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

        ItemStack item3 = new ItemStack(Material.NAME_TAG);
        ItemMeta itemMeta3 = item3.getItemMeta();
        itemMeta3.setDisplayName(hlasovaniONazvu);
        lore11 = new ArrayList<>();
        lore11.add(" ");
        lore11.add(ChatColor.WHITE + "Chceš hlasovat pro náš server?");
        lore11.add(ChatColor.WHITE + "Klikni pro zobrazení odkazu!");
        lore11.add(" ");
        itemMeta3.setLore(lore11);
        item3.setItemMeta(itemMeta3);

        inv2.setItem(13, item3);
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

        ItemStack item3 = new ItemStack(Material.WOODEN_HOE, 1);
        ItemMeta itemMeta3 = item3.getItemMeta();
        itemMeta3.setDisplayName(residence);
        lore3 = new ArrayList<>();
        lore3.add(ChatColor.GRAY + " ");
        if (player.hasPermission("residence.group.team")){
            lore3.add(ChatColor.WHITE + "Maximální rozloha: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&41&x&6&9&9&d&4&70&x&5&b&a&5&4&a0&x&4&e&a&c&4&d0&x&4&1&b&4&5&0x&x&3&3&b&c&5&21&x&2&6&c&4&5&50&x&1&8&c&b&5&80&x&0&b&d&3&5&b0"));
            lore3.add(ChatColor.WHITE + "Počet residencí: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&410"));
            lore3.add(ChatColor.WHITE + "Cena za blok: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&40.7") + " coin");
            lore3.add(ChatColor.WHITE + "Změna zprávy residence: " + ChatColor.translateAlternateColorCodes('&', "&a&l✔"));
        }
        else if (player.hasPermission("residence.group.vip")){
            lore3.add(ChatColor.WHITE + "Maximální rozloha: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&42&x&6&4&9&f&4&80&x&5&2&a&a&4&c0&x&4&1&b&4&5&0x&x&2&f&b&e&5&32&x&1&d&c&9&5&70&x&0&b&d&3&5&b0"));
            lore3.add(ChatColor.WHITE + "Počet residencí: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&45"));
            lore3.add(ChatColor.WHITE + "Cena za blok: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&40.9") + " coin");
            lore3.add(ChatColor.WHITE + "Změna zprávy residence: " + ChatColor.translateAlternateColorCodes('&', "&4&l✖"));
        }
        else if (player.hasPermission("residence.group.default")){
            lore3.add(ChatColor.WHITE + "Maximální rozloha: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&41&x&6&4&9&f&4&80&x&5&2&a&a&4&c0&x&4&1&b&4&5&0x&x&2&f&b&e&5&31&x&1&d&c&9&5&70&x&0&b&d&3&5&b0"));
            lore3.add(ChatColor.WHITE + "Počet residencí: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&43"));
            lore3.add(ChatColor.WHITE + "Cena za blok: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&41") + " coin");
            lore3.add(ChatColor.WHITE + "Změna zprávy residence: " + ChatColor.translateAlternateColorCodes('&', "&4&l✖"));
        }
        else {
            lore3.add(ChatColor.translateAlternateColorCodes('&', "&fBez práva na residenci &4&l✖"));
        }
        itemMeta3.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta3.setLore(lore3);
        item3.setItemMeta(itemMeta3);

        ItemStack item4 = new ItemStack(Material.BLACK_BED, 1);
        ItemMeta itemMeta4 = item4.getItemMeta();
        itemMeta4.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4&lD&x&6&1&a&1&4&9&lO&x&4&b&a&e&4&d&lM&x&3&6&b&a&5&2&lO&x&2&0&c&7&5&6&lV&x&0&b&d&3&5&b&lY"));
        lore6 = new ArrayList<>();
        lore6.add(ChatColor.GRAY + " ");
        lore6.add(ChatColor.WHITE + "Seznam svých domovů si ");
        lore6.add(ChatColor.WHITE + "zobrazíš pomocí " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4/&x&6&1&a&1&4&9h&x&4&b&a&e&4&do&x&3&6&b&a&5&2m&x&2&0&c&7&5&6e&x&0&b&d&3&5&bs"));
        lore6.add(ChatColor.GRAY + " ");
        if (player.hasPermission("survival.homes.team"))
            lore6.add(ChatColor.WHITE + "Maximální počet domovů: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&410"));
        else if (player.hasPermission("survival.homes.vip"))
            lore6.add(ChatColor.WHITE + "Maximální počet domovů: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&45"));
        else
            lore6.add(ChatColor.WHITE + "Maximální počet domovů: " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&43"));
        lore6.add(ChatColor.GRAY + " ");
        lore6.add(ChatColor.WHITE + "Pokud ale žádný domov ještě nemáš ");
        lore6.add(ChatColor.WHITE + "můžeš si ho založit pomocí ");
        lore6.add(ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4/&x&6&e&9&9&4&6s&x&6&7&9&e&4&7e&x&5&f&a&2&4&9t&x&5&7&a&7&4&bh&x&5&0&a&b&4&co&x&4&8&b&0&4&em&x&4&1&b&4&5&0e &x&3&9&b&8&5&1<&x&3&1&b&d&5&3n&x&2&a&c&1&5&4á&x&2&2&c&6&5&6z&x&1&a&c&a&5&8e&x&1&3&c&f&5&9v&x&0&b&d&3&5&b>"));
        itemMeta4.setLore(lore6);
        item4.setItemMeta(itemMeta4);

        ItemStack item5 = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta itemMeta5 = item5.getItemMeta();
        itemMeta5.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4&lM&x&6&b&9&b&4&6&lĚ&x&6&1&a&1&4&9&lN&x&5&6&a&8&4&b&lA &x&4&b&a&e&4&d&lA &x&4&1&b&4&5&0&lP&x&3&6&b&a&5&2&lL&x&2&b&c&0&5&4&lA&x&2&0&c&7&5&6&lT&x&1&6&c&d&5&9&lB&x&0&b&d&3&5&b&lY"));
        lore7 = new ArrayList<>();
        lore7.add(ChatColor.GRAY + " ");
        lore7.add(ChatColor.WHITE + "Náš server používá jako měnu ");
        lore7.add(ChatColor.WHITE + "coiny se kterýma můžeš nakupovat ");
        lore7.add(ChatColor.WHITE + "v obchodě ale také za ně platit");
        lore7.add(ChatColor.WHITE + "residence a další.");
        lore7.add(ChatColor.GRAY + " ");
        lore7.add(ChatColor.WHITE + "Vydělat si peníze můžeš například");
        lore7.add(ChatColor.WHITE + "pomocí /prace ");
        lore7.add(ChatColor.WHITE + " ");
        lore7.add(ChatColor.WHITE + "Převody mezi hráči můžeš provádět");
        lore7.add(ChatColor.WHITE + "pomocí " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4/&x&7&2&9&7&4&5c&x&6&e&9&a&4&6o&x&6&a&9&c&4&7i&x&6&6&9&f&4&8n&x&6&1&a&1&4&8s &x&5&d&a&3&4&9c&x&5&9&a&6&4&ao&x&5&5&a&8&4&bn&x&5&1&a&a&4&cv&x&4&d&a&d&4&de&x&4&9&a&f&4&er&x&4&5&b&2&4&ft &x&4&1&b&4&5&0<&x&3&c&b&6&5&0h&x&3&8&b&9&5&1r&x&3&4&b&b&5&2á&x&3&0&b&e&5&3č&x&2&c&c&0&5&4> &x&2&8&c&2&5&5<&x&2&4&c&5&5&6č&x&2&0&c&7&5&7á&x&1&b&c&9&5&7s&x&1&7&c&c&5&8t&x&1&3&c&e&5&9k&x&0&f&d&1&5&aa&x&0&b&d&3&5&b>"));
        itemMeta5.setLore(lore7);
        item5.setItemMeta(itemMeta5);

        ItemStack item6 = new ItemStack(Material.NETHERRACK, 1);
        ItemMeta itemMeta6 = item6.getItemMeta();
        itemMeta6.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4&lE&x&6&a&9&c&4&7&lN&x&5&e&a&3&4&9&lD &x&5&2&a&a&4&c&lA &x&4&6&b&1&4&e&lN&x&3&b&b&7&5&1&lE&x&2&f&b&e&5&3&lT&x&2&3&c&5&5&6&lH&x&1&7&c&c&5&8&lE&x&0&b&d&3&5&b&lR"));
        lore8 = new ArrayList<>();
        lore8.add(ChatColor.GRAY + " ");
        lore8.add(ChatColor.WHITE + "Do endu nebo netheru se dostaneš");
        lore8.add(ChatColor.WHITE + "pouze pomocí portálů které jsou");
        lore8.add(ChatColor.WHITE + "v normální přírodě. ");
        lore8.add(ChatColor.WHITE + " ");
        lore8.add(ChatColor.WHITE + "END a NETHER se resetuje jednou za měsíc");
        lore8.add(ChatColor.WHITE + "Rozloha těchto světů je 5000x5000");
        lore8.add(ChatColor.WHITE + "Díky resetováním těchto světů můžeme");
        lore8.add(ChatColor.WHITE + "zaručit, že i nováčci mohou získat");
        lore8.add(ChatColor.WHITE + "všechny materiály z endu nebo netheru.");
        itemMeta6.setLore(lore8);
        item6.setItemMeta(itemMeta6);

        ItemStack item7 = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta itemMeta7 = item7.getItemMeta();
        itemMeta7.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4&lK&x&5&2&a&a&4&c&lI&x&2&f&b&e&5&3&lT&x&0&b&d&3&5&b&lY"));
        lore9 = new ArrayList<>();
        lore9.add(ChatColor.GRAY + " ");
        lore9.add(ChatColor.WHITE + "Na serveru máme aktuálně k dispozici");
        lore9.add(ChatColor.WHITE + "2 kity. Dostupnost ověříš pomocí " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4/&x&5&b&a&5&4&ak&x&4&1&b&4&5&0i&x&2&6&c&4&5&5t&x&0&b&d&3&5&bs"));
        itemMeta7.setLore(lore9);
        item7.setItemMeta(itemMeta7);

        ItemStack item8 = new ItemStack(Material.EMERALD, 1);
        ItemMeta itemMeta8 = item8.getItemMeta();
        itemMeta8.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4&lO&x&6&1&a&1&4&9&lB&x&4&b&a&e&4&d&lC&x&3&6&b&a&5&2&lH&x&2&0&c&7&5&6&lO&x&0&b&d&3&5&b&lD"));
        lore10 = new ArrayList<>();
        lore10.add(ChatColor.GRAY + " ");
        lore10.add(ChatColor.WHITE + "Na serveru máme tržnici s vesničany");
        lore10.add(ChatColor.WHITE + "kteří prodávají různé věci. ");
        lore10.add(ChatColor.WHITE + "Do obchodu se dostaneš pomocí");
        lore10.add(ChatColor.WHITE + "příkazu " + ChatColor.translateAlternateColorCodes('&', "&x&7&6&9&5&4&4/&x&6&4&9&f&4&8o&x&5&2&a&a&4&cb&x&4&1&b&4&5&0c&x&2&f&b&e&5&3h&x&1&d&c&9&5&7o&x&0&b&d&3&5&bd"));
        itemMeta8.setLore(lore10);
        item8.setItemMeta(itemMeta8);



        ItemStack item2 = new ItemStack(Material.OAK_SIGN, 1);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName(name1);
        lore2 = new ArrayList<>();
        itemMeta2.setLore(lore2);
        item2.setItemMeta(itemMeta2);

        inv1.setItem(20, item1);
        inv1.setItem(4, item2);
        inv1.setItem(21, item3);
        inv1.setItem(22, item4);
        inv1.setItem(23, item5);
        inv1.setItem(24, item6);
        inv1.setItem(30, item7);
        inv1.setItem(32, item8);

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

            if (e.getCurrentItem().getType() == Material.NAME_TAG){
                player.closeInventory();
                ChatNotice.info(player, Component.text("Hlasovat pro server můžeš zde: https://craftlist.org/mejs-cz"));
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

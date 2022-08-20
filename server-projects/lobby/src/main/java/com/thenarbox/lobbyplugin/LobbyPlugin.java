package com.thenarbox.lobbyplugin;

import com.thenarbox.api.AllowedCommands;
import com.thenarbox.api.ChatNotice;
import com.thenarbox.api.Standards;
import com.thenarbox.api.ping.MinecraftPing;
import com.thenarbox.api.services.Server;
import com.thenarbox.lobbyplugin.extenders.DoubleJump;
import com.thenarbox.lobbyplugin.listeners.CommandMechanic;
import com.thenarbox.api.PlayerChangeServerEvent;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2(topic = "LobbyPlugin")
public class LobbyPlugin extends JavaPlugin implements Listener {

    ArrayList<String> allowedCommands480 = new ArrayList<>();

    @Getter
    Location location = null;

    @Override
    public void onEnable() {
        System.out.println("LobbyPlugin is enabled!");
        {
            Standards.worlds();
            Standards.commands();
        }

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        PlayerChangeServerEvent.instance = this;
        location = new Location(Bukkit.getWorld("world"), 390.5, 89, 209.5, -90, 0);

        if(!getServer().getPluginManager().isPluginEnabled("Vault")){
            log.error("Vault is not enabled! Disabling LobbyPlugin...");
            getServer().getPluginManager().disablePlugin(this);
        }
        if(!getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")){
            log.error("PlaceholderAPI is not enabled! Disabling LobbyPlugin...");
            getServer().getPluginManager().disablePlugin(this);
        }

        getServer().getPluginManager()
                .registerEvents(this, this);
        getServer().getPluginManager()
                .registerEvents(new CommandMechanic(), this);
        getServer().getPluginManager()
                .registerEvents(new DoubleJump(), this);

        allowedCommands480 = AllowedCommands.initMysql();
        Standards.View.tab(this);
    }

    @Override
    public void onDisable() {
        System.out.println("LobbyPlugin is now disabled.");
        getServer().getMessenger().unregisterIncomingPluginChannel(this, "BungeeCord");
        HandlerList.unregisterAll();
    }

    ItemStack item;
    {
        item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Hlavní menu");
        item.setItemMeta(meta);
    }

    ItemStack item3;
    {
        item3 = new ItemStack(Material.NAME_TAG);
        ItemMeta meta = item3.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Obchod");
        item3.setItemMeta(meta);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.getInventory().clear();
        String level = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points%");
        player.setLevel(Integer.parseInt(level));
        ItemStack item2 = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item2.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Profil");
        meta.setOwner(player.getName());
        item2.setItemMeta(meta);

        player.getInventory().setItem(8, item2);
        player.getInventory().setItem(0, item);
        player.getInventory().setItem(7, item3);
        player.setGameMode(GameMode.ADVENTURE);
        player.setMaxHealth(20);
        player.setFoodLevel(20);
        player.setWalkSpeed(0.4f);
        player.teleport(location);
        e.setJoinMessage(null);
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        String replaced = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%"));
        String level = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points%");

        if (replaced.equals("")){
            e.setFormat(ChatColor.AQUA + level + ChatColor.GRAY + " | " +ChatColor.WHITE + player.getName() + ": " + ChatColor.GRAY + e.getMessage());
        }
        else {
            e.setFormat(replaced + ChatColor.GRAY + " | " + ChatColor.WHITE + player.getName() + ": " + e.getMessage());
        }
    }


    @EventHandler
    public void onPlayer(PlayerCommandSendEvent e){
        final var allowedCommands = allowedCommands480;
        final var sentCommands = e.getCommands();
        sentCommands.retainAll(allowedCommands);
    }

    List<String> lore;
    public void prepareInventory(Player player)
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

    List<String> lore9;
    Inventory inv2;
    {
        inv2 = Bukkit.createInventory(null, 27, "Nastavení");
        lore9 = new ArrayList<>();
        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lZměna hesla"));
        lore9.add(ChatColor.GRAY + " ");
        lore9.add(ChatColor.GRAY + "Klikni pro změnu hesla");
        meta.setLore(lore9);
        item.setItemMeta(meta);

        inv2.setItem(11, item);
    }

    List<String> lore3;
    List<String> lore4;
    List<String> lore5;
    List<String> lore6;
    List<String> lore7;
    Inventory inv1;
    {
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
        ItemStack item4 = new ItemStack(Material.LEGACY_EXP_BOTTLE, 1);
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

        ItemStack item5 = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta5 = item5.getItemMeta();
        meta5.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lZavřít Obchod"));
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


        inv1.setItem(44, item6);
        inv1.setItem(36, item5);
        inv1.setItem(21, item2);
        inv1.setItem(22, item3);
        inv1.setItem(23, item4);
        inv1.setItem(4, item);
    }

    @EventHandler
    public void inv(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equals("Nastavení")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null)
                return;

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
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                player.openInventory(inv1);
            }
            if(e.getCurrentItem().getType() == Material.REDSTONE_TORCH){
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                player.openInventory(inv2);
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
    List<String> lore1;
    List<String> lore2;
    List<String> lore10;
    @EventHandler
    public void interact(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType().equals(Material.COMPASS)) {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                prepareInventory(player);
                e.setCancelled(true);
            }
            else if (player.getItemInHand().getType().equals(Material.NAME_TAG)) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                player.openInventory(inv1);
                e.setCancelled(true);
            }
            else if (player.getItemInHand().getType().equals(Material.PLAYER_HEAD)) {

                // Inventory creation, List initialise
                Inventory profile = Bukkit.createInventory(null, 27, "Profil");
                lore1 = new ArrayList<>();

                // ItemStack creation, ItemMeta creation, Lore creation, ItemMeta setLore, ItemStack setItemMeta
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
                e.setCancelled(true);
            }
            else {
                e.setCancelled(true);
            }
        }
        else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void command(PlayerCommandPreprocessEvent e){
        Player player = e.getPlayer();
        final var commandMessage = e.getMessage();
        final int length;
        {
            final int index = commandMessage.indexOf(' ');
            length = index == -1 ? commandMessage.length() : index;
        }
        final var commandName = commandMessage.substring(1, length);
        if(!allowedCommands480.contains(commandName)){
            e.setCancelled(true);
            ChatNotice.error(player, Component.text("Na provedení tohoto příkazu nemáš oprávnění."));
        }
    }

    @EventHandler
    public void onTnT(final EntityExplodeEvent e) {
        final Entity entity = e.getEntity();
        if (entity != null && entity instanceof TNTPrimed) {
            e.blockList().clear();
        }
    }

    @EventHandler
    public void onTab(TabCompleteEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void swap(PlayerSwapHandItemsEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void Quit(PlayerQuitEvent e){
        e.setQuitMessage(null);
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void VineGrow(BlockSpreadEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void noFood(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void kill(EntityDamageEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void death(PlayerDeathEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void inventory(InventoryClickEvent e){
            e.setCancelled(true);
    }

    @EventHandler
    public void LeaveDecay(LeavesDecayEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onPick(PlayerPickupItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();

        if (player.getLocation().getY() <= 0) {
            player.teleport(location);
        }
        if (player.getLocation().getY() >= 310) {
            player.teleport(location);
        }

        if (player.getLocation().getX() <= -65){
            player.teleport(location);
        }
        if (player.getLocation().getX() >= 900){
            player.teleport(location);
        }

        if (player.getLocation().getZ() <= -160){
            player.teleport(location);
        }
        if (player.getLocation().getZ() >= 800){
            player.teleport(location);
        }
    }
    @EventHandler
    public void noPvP(EntityDamageByEntityEvent e){
        e.setCancelled(true);
    }
}

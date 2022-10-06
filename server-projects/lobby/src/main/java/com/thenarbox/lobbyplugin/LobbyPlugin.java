package com.thenarbox.lobbyplugin;

import com.thenarbox.api.AllowedCommands;
import com.thenarbox.api.ChatNotice;
import com.thenarbox.api.Standards;
import com.thenarbox.api.ranks.Rank;
import com.thenarbox.lobbyplugin.extenders.DoubleJump;
import com.thenarbox.api.PlayerChangeServerEvent;
import com.thenarbox.lobbyplugin.services.Menus;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

//#9F47C8
//#F092FF

//#17B3C8
//#25FFB0
@Log4j2(topic = "LobbyPlugin")
public class LobbyPlugin extends JavaPlugin implements Listener {

    ArrayList<String> allowedCommands480 = new ArrayList<>();

    @Getter
    private static LobbyPlugin instance;

    @Getter
    Location location = null;

    @Override
    public void onEnable() {
        log.error("SPRÁVA LOBBY MEJS.CZ");
        log.error(" ");
        {
            Standards.worlds();
            Standards.commands(this);
            Standards.proxyLinks(this);
        }

        instance = this;
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        PlayerChangeServerEvent.instance = this;
        location = new Location(Bukkit.getWorld("world"), 390.5, 89, 209.5, -90, 0);
        log.error("Probíhá inicializace...50%");
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
                .registerEvents(new DoubleJump(), this);
        getServer().getPluginManager()
                .registerEvents(new Menus(), this);

        allowedCommands480 = AllowedCommands.initMysql();
        Standards.View.tab(this);
        allowedCommands480.add("friends");
        log.error(" ");
        log.error("Inicializace proběhla úspěšně.");
        log.error(" ");
        log.error("SPRÁVA LOBBY MEJS.CZ");
    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterIncomingPluginChannel(this, "BungeeCord");
        HandlerList.unregisterAll();
        log.info(ChatColor.RED + "LobbyPlugin byl vypnut.");
    }

    ItemStack item;
    {
        item = new ItemStack(Material.COMPASS);
        ItemMeta meta1 = item.getItemMeta();
        String name = ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8H&x&a&8&4&f&c&el&x&b&1&5&8&d&4a&x&b&a&6&0&d&av&x&c&3&6&8&e&0n&x&c&c&7&1&e&7í &x&d&5&7&9&e&dM&x&d&e&8&1&f&3e&x&e&7&8&a&f&9n&x&f&0&9&2&f&fu " + ChatColor.GRAY + "(Pravé kliknutí)");
        meta1.setDisplayName(name);
        item.setItemMeta(meta1);
    }

    ItemStack item3;
    {
        item3 = new ItemStack(Material.NAME_TAG);
        ItemMeta meta2 = item3.getItemMeta();
        String name = ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8O&x&a&f&5&6&d&3b&x&b&f&6&5&d&ec&x&d&0&7&4&e&9h&x&e&0&8&3&f&4o&x&f&0&9&2&f&fd " + ChatColor.GRAY + "(Pravé kliknutí)");
        meta2.setDisplayName(name);
        item3.setItemMeta(meta2);
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        e.getPlayer().clearTitle();
        player.getInventory().clear();

        ItemStack item2 = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta3 = (SkullMeta) item2.getItemMeta();
        String name = ChatColor.translateAlternateColorCodes('&', "&x&9&f&4&7&c&8P&x&a&f&5&6&d&3r&x&b&f&6&5&d&eo&x&d&0&7&4&e&9f&x&e&0&8&3&f&4i&x&f&0&9&2&f&fl " + ChatColor.GRAY + "(Pravé kliknutí)");
        meta3.setDisplayName(ChatColor.AQUA + name);
        meta3.setOwner(player.getName());
        item2.setItemMeta(meta3);

        player.getInventory().setItem(8, item2);
        player.getInventory().setItem(0, item);
        player.getInventory().setItem(7, item3);

        player.setGameMode(GameMode.ADVENTURE);

        player.setMaxHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.setWalkSpeed(0.4f);

        player.teleport(location);

        String level = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points%");
        player.setLevel(Integer.parseInt(level));

        e.setJoinMessage(null);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void chat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        String replaced = ChatColor.translateAlternateColorCodes('&', Rank.getRankPrefix(PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%")));
        String level = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points%");

        if (replaced.equals("")){
            e.setFormat(ChatColor.AQUA + level + ChatColor.GRAY + " | " + ChatColor.WHITE + player.getName() + ": " + ChatColor.GRAY + e.getMessage().replace("%", "%%"));
        }
        else {
            e.setFormat(ChatColor.AQUA + level + ChatColor.GRAY + " | " + replaced + " " + ChatColor.WHITE + player.getName() + ": " + e.getMessage().replace("%", "%%"));
        }
    }


    @EventHandler
    public void onPlayer(PlayerCommandSendEvent e){
        final var allowedCommands = allowedCommands480;
        final var sentCommands = e.getCommands();
        sentCommands.retainAll(allowedCommands);
    }

    @EventHandler
    public void interact(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType().equals(Material.COMPASS)) {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                Menus.mainMenu(player, this);
                e.setCancelled(true);
            }
            else if (player.getItemInHand().getType().equals(Material.NAME_TAG)) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                Menus.isInventoryOpen = false;
                Menus.shopMenu(player);
                e.setCancelled(true);
            }
            else if (player.getItemInHand().getType().equals(Material.PLAYER_HEAD)) {
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
                Menus.profileMenu(player);
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
        final var commandMessage = e.getMessage().toLowerCase();
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

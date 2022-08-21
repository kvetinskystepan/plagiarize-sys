package com.thenarbox.lobbyplugin;

import com.thenarbox.api.AllowedCommands;
import com.thenarbox.api.ChatNotice;
import com.thenarbox.api.Standards;
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
                .registerEvents(new DoubleJump(), this);
        getServer().getPluginManager()
                .registerEvents(new Menus(), this);

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

    @EventHandler
    public void interact(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType().equals(Material.COMPASS)) {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                Menus.mainMenu(player);
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

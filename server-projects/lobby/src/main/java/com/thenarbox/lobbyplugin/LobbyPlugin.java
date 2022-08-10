package com.thenarbox.lobbyplugin;

import com.thenarbox.api.AllowedCommands;
import com.thenarbox.api.ChatNotice;
import com.thenarbox.api.Standards;
import com.thenarbox.lobbyplugin.extenders.DoubleJump;
import com.thenarbox.lobbyplugin.listeners.CommandMechanic;
import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
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
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

@Log4j2(topic = "LobbyPlugin")
public class LobbyPlugin extends JavaPlugin implements Listener {

    ArrayList<String> allowedCommands480 = new ArrayList<>();
    @Override
    public void onEnable() {
        System.out.println("LobbyPlugin is enabled!");
        {
            Standards.worlds();
            Standards.commands();
        }

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
        HandlerList.unregisterAll();
    }

    ItemStack item;
    {
        item = new ItemStack(org.bukkit.Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Hlavní menu");
        item.setItemMeta(meta);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.getInventory().clear();
        player.getInventory().addItem(item);
        player.setGameMode(GameMode.ADVENTURE);
        player.setMaxHealth(20);
        player.setFoodLevel(20);
        player.setWalkSpeed(0.4f);
        e.setJoinMessage(null);
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        String replaced = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%"));
        e.setFormat(replaced + ChatColor.GRAY + " | " + ChatColor.WHITE + player.getName() + ": " + e.getMessage());
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
                Inventory inv = Bukkit.createInventory(null, 36, ChatColor.GOLD + "Hlavní menu");
                player.openInventory(inv);
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
            ChatNotice.error(player, Component.text("Na provedení tohoto příkazu nemáš opravnění."));
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
    public void noPvP(EntityDamageByEntityEvent e){
        e.setCancelled(true);
    }
}

package com.thenarbox.survivalplugin;

import com.thenarbox.api.AllowedCommands;
import com.thenarbox.api.ChatNotice;
import com.thenarbox.api.Standards;
import com.thenarbox.survivalplugin.services.SpawnService;
import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static com.thenarbox.api.Standards.vanishPlayers;
import static com.thenarbox.survivalplugin.services.SpawnService.spawn;

@Log4j2(topic = "SurvivalPlugin")
public class SurvivalPlugin extends JavaPlugin implements Listener {

    ArrayList<String> allowedCommands32 = new ArrayList<>();

    public void onEnable() {
        log.info("SurvivalPlugin has been enabled.");

        if(!getServer().getPluginManager().isPluginEnabled("Vault")){
            log.error("Vault is not enabled! Disabling LobbyPlugin...");
            getServer().getPluginManager().disablePlugin(this);
        }
        if(!getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")){
            log.error("PlaceholderAPI is not enabled! Disabling LobbyPlugin...");
            getServer().getPluginManager().disablePlugin(this);
        }

        Standards.survivalCommands(this);
        Standards.commands();
        Standards.View.tab(this);
        SpawnService.spawnSettings();

        getServer().getPluginManager()
                .registerEvents(this, this);
        getServer().getPluginManager()
                .registerEvents(new SpawnService(), this);

        allowedCommands32 = AllowedCommands.initSurvivalMysql();

    }

    @EventHandler
    public void onPlayer(PlayerCommandSendEvent e){
        final var allowedCommands = allowedCommands32;
        final var sentCommands = e.getCommands();
        sentCommands.retainAll(allowedCommands);
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
        if(!allowedCommands32.contains(commandName)){
            e.setCancelled(true);
            ChatNotice.error(player, Component.text("Na provedení tohoto příkazu nemáš oprávnění."));
        }
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        String replaced = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%"));
        String level = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points%");

        if (replaced.equals("")){
            e.setFormat(ChatColor.GOLD + level + ChatColor.GRAY + " | " +ChatColor.WHITE + player.getName() + ": " + ChatColor.GRAY + e.getMessage());
        }
        else {
            e.setFormat(replaced + ChatColor.GRAY + " | " + ChatColor.WHITE + player.getName() + ": " + e.getMessage());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("spawn")){
            player.teleport(spawn);
        }
        if(!player.hasPlayedBefore()){
            player.teleport(spawn);
        }
        Bukkit.getOnlinePlayers().forEach(online -> {
            if(vanishPlayers.contains(player.getName()))
                e.getPlayer().hidePlayer(this, online);
        });
        e.setJoinMessage(ChatColor.GREEN + "+" + ChatColor.GRAY + " | " + ChatColor.GOLD + player.getName() + ChatColor.WHITE + " se připojil.");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        if (vanishPlayers.contains(player.getName())){
            vanishPlayers.remove(player.getName());
            e.setQuitMessage(null);
        }
        else {
            e.setQuitMessage(ChatColor.RED + "-" + ChatColor.GRAY + " | " + ChatColor.GOLD + player.getName() + ChatColor.WHITE + " se odpojil.");
        }
    }

    @EventHandler
    public void Heal(FoodLevelChangeEvent e){
        Player player = (Player)e.getEntity();
        if (vanishPlayers.contains(player.getName()))
            e.setCancelled(true);
    }
    @EventHandler
    public void onPick(PlayerPickupArrowEvent e){
        Player player = e.getPlayer();

        if (vanishPlayers.contains(player.getName()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player player = e.getPlayer();

        if (vanishPlayers.contains(player.getName()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e){
        Player player = e.getPlayer();

        if (vanishPlayers.contains(player.getName()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player player = e.getPlayer();
        if (vanishPlayers.contains(player.getName()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        if (vanishPlayers.contains(player.getName()))
            e.setCancelled(true);
    }

    @EventHandler
    public void Fill(PlayerBucketFillEvent e){
        Player player = e.getPlayer();
        if (vanishPlayers.contains(player.getName()))
            e.setCancelled(true);
    }

    @EventHandler
    public void hitEvent(EntityDamageByEntityEvent e){
        if((e.getDamager() instanceof Player player)){
            player = (Player)e.getDamager();
            if(vanishPlayers.contains(player.getName()))
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void Damage(EntityDamageEvent e){
        if ((e.getEntity() instanceof Player player)){
            player = (Player)e.getEntity();
            if(vanishPlayers.contains(player.getName()))
                e.setCancelled(true);
        }
    }

}

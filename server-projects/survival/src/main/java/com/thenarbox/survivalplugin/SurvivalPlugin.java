package com.thenarbox.survivalplugin;

import com.thenarbox.api.AllowedCommands;
import com.thenarbox.api.ChatNotice;
import com.thenarbox.api.Standards;
import com.thenarbox.api.colors.ColorAPI;
import com.thenarbox.survivalplugin.mechanics.Command;
import com.thenarbox.survivalplugin.services.Menus;
import com.thenarbox.survivalplugin.services.SpawnService;
import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

import static com.thenarbox.api.Standards.vanishPlayers;
import static com.thenarbox.survivalplugin.services.SpawnService.spawn;

@Log4j2(topic = "SurvivalPlugin")
public class SurvivalPlugin extends JavaPlugin implements Listener {

    public static HashMap<String, Long> cooldownMap = new HashMap<>();
    static int cooldownTime = 5;
    ArrayList<String> allowedCommands32 = new ArrayList<>();

    public void onEnable() {
        log.error("SPRÁVA SURVIVAL MEJS.CZ");
        log.error(" ");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        if(!getServer().getPluginManager().isPluginEnabled("Vault")){
            log.error("Vault is not enabled! Disabling LobbyPlugin...");
            getServer().getPluginManager().disablePlugin(this);
        }
        if(!getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")){
            log.error("PlaceholderAPI is not enabled! Disabling LobbyPlugin...");
            getServer().getPluginManager().disablePlugin(this);
        }
        getServer().createWorld(new WorldCreator("Spawn"));
        Command.commands();
        Standards.survivalCommands(this);
        Standards.commands(this);
        Standards.View.tab(this);
        log.error("Probíhá inicializace...50%");
        SpawnService.spawnSettings();
        getServer().getPluginManager()
                .registerEvents(this, this);
        getServer().getPluginManager()
                .registerEvents(new SpawnService(), this);
        getServer().getPluginManager()
                .registerEvents(new Menus(), this);

        allowedCommands32 = AllowedCommands.initSurvivalMysql();

        log.error(" ");
        log.error("Inicializace proběhla úspěšně.");
        log.error(" ");
        log.error("SPRÁVA SURVIVAL MEJS.CZ");
    }

    // CAFF74
    // 0DFF6E
   @EventHandler
    public void onPlayer(PlayerCommandSendEvent e){
        final var allowedCommands = allowedCommands32;
        final var sentCommands = e.getCommands();
        sentCommands.retainAll(allowedCommands);
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
            if(cooldownMap.containsKey(player.getName())) {
                long secondsLeft = ((cooldownMap.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                if(secondsLeft>0) {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                    ChatNotice.error(player, Component.text("Nemůžeš odeslílat zprávy takhle rychle. Zkus to znovu za: " + secondsLeft + " sekund."));
                    e.setCancelled(true);
                    return;
                }
            }
            cooldownMap.put(player.getName(), System.currentTimeMillis());
        }

        if (replaced.equals("")){
            e.setFormat(ChatColor.AQUA + level + ChatColor.GRAY + " | " + ChatColor.WHITE + player.getName() + ": " + ChatColor.GRAY + e.getMessage().replace("%", "%%"));
        }
        else {
            e.setFormat(ChatColor.AQUA + level + ChatColor.GRAY + " | " + replaced + " " + ChatColor.WHITE + player.getName() + ": " + e.getMessage().replace("%", "%%"));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("Spawn")){
            player.teleport(spawn);
        }
        if(!player.hasPlayedBefore()){
            player.teleport(spawn);
        }
        Bukkit.getOnlinePlayers().forEach(online -> {
            if(vanishPlayers.contains(player.getName()))
                e.getPlayer().hidePlayer(this, online);
        });
        e.setJoinMessage(ChatColor.GREEN + "+" + ChatColor.GRAY + " | " + ChatColor.GOLD + ColorAPI.process("<GRADIENT:34eb92>"+player.getName()+"</GRADIENT:34eb92>") + ChatColor.WHITE + " se připojil.");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        if (vanishPlayers.contains(player.getName())){
            vanishPlayers.remove(player.getName());
            e.setQuitMessage(null);
        }
        else {
            e.setQuitMessage(ChatColor.RED + "-" + ChatColor.GRAY + " | " + ChatColor.GOLD + ColorAPI.process("<GRADIENT:34eb92>"+player.getName()+"</GRADIENT:34eb92>") + ChatColor.WHITE + " se odpojil.");
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

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.setDeathMessage(null);
        Player player = e.getPlayer();
        if (player.getKiller() != null){
            for (Player players : Bukkit.getOnlinePlayers()){
                players.sendMessage(ChatColor.DARK_RED + "✟" + ChatColor.GRAY + " | " + ColorAPI.process("<GRADIENT:34eb92>"+player.getName()+"</GRADIENT:34eb92>") + ChatColor.WHITE + " byl zavražděn hráčem " + ColorAPI.process("<GRADIENT:34eb92>"+player.getKiller().getName()+"</GRADIENT:34eb92>") + ChatColor.WHITE + ".");
            }
        }
        else {
            for (Player players : Bukkit.getOnlinePlayers()){
                players.sendMessage(ChatColor.DARK_RED + "✟" + ChatColor.GRAY + " | " + ColorAPI.process("<GRADIENT:34eb92>"+player.getName()+"</GRADIENT:34eb92>") + ChatColor.WHITE + " zemřel.");
            }
        }
        e.getPlayer().teleport(spawn);
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e){
        Player player = e.getPlayer();
        player.setMetadata("previouslocation", new FixedMetadataValue(this, player.getLocation()));
    }

}

package com.thenarbox.authplugin;

import com.thenarbox.api.AllowedCommands;
import com.thenarbox.api.Standards;
import lombok.extern.log4j.Log4j2;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Log4j2(topic = "AuthPlugin")
public final class AuthPlugin extends JavaPlugin implements Listener {

    ArrayList<String> allowedCommands01 = new ArrayList<String>();
    @Override
    public void onEnable() {
        log.info("AuthPlugin is enabled!");

        {
            Standards.worlds();
            Standards.commands();
        }

        allowedCommands01.add("login");
        allowedCommands01.add("register");
        allowedCommands01.add("l");
        allowedCommands01.add("reg");
        getServer().getPluginManager()
                .registerEvents(this, this);
        getServer().setDefaultGameMode(GameMode.ADVENTURE);
    }
    @Override
    public void onDisable() {
        log.info("AuthPlugin is disabled!");
        HandlerList.unregisterAll();
    }

    @EventHandler
    public void onPlayer(PlayerCommandSendEvent e){
        final var allowedCommands = allowedCommands01;
        final var sentCommands = e.getCommands();
        sentCommands.retainAll(allowedCommands);
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent e){
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
    public void LeaveDecay(LeavesDecayEvent e) {
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

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (e.getPlayer().getName().startsWith("MCSTORM")){
            e.getPlayer().kickPlayer("Connect to our server by your real nickname and don't use bots or other software to join us.");
            log.error("Player " + e.getPlayer().getName() + " was kicked for using bots or other software to join us.");
            return;
        }
        Player player = e.getPlayer();
        player.hidePlayer(player);
        Bukkit.getOnlinePlayers().forEach(online -> {
            player.hidePlayer(online);
            online.hidePlayer(player);
        });
        player.teleport(new Location(Bukkit.getWorld("world"), -48.5, 65.5, 34.5, -50, 0));
        player.setGameMode(getServer().getDefaultGameMode());
        player.setMaxHealth(20);
        player.setFoodLevel(20);
        e.setJoinMessage(null);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
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
    public void onInteract(PlayerInteractEvent e){
        e.setCancelled(true);
    }

}

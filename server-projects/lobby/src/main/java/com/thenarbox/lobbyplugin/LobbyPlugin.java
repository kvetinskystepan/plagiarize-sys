package com.thenarbox.lobbyplugin;

import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import com.thenarbox.api.Standards;
import com.thenarbox.lobbyplugin.extenders.DoubleJump;
import com.thenarbox.lobbyplugin.listeners.CommandMechanic;
import lombok.extern.log4j.Log4j2;
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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Log4j2(topic = "LobbyPlugin")
public class LobbyPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("LobbyPlugin is enabled!");

        // Register standards
        {
            Standards.worlds();
            Standards.commands();
        }

        getServer().getPluginManager()
                .registerEvents(this, this);
        getServer().getPluginManager()
                .registerEvents(new CommandMechanic(), this);
        getServer().getPluginManager()
                .registerEvents(new DoubleJump(), this);

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                sendTablistToPlayers();
            }
        }, 0, 20);

    }

    public void sendTablistToPlayers() {
        String pattern = "HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerListHeader("\n" + ChatColor.translateAlternateColorCodes('&', "&6&lMejs.cz") + "\n" + "\n" + ChatColor.GRAY + "discord.mejs.cz" + "\n" + "\n" + ChatColor.WHITE + "Čas: " + ChatColor.GOLD + df.format(today) + "\n");
            player.setPlayerListFooter("\n" + "  " + ChatColor.WHITE + "Hráčů: " + ChatColor.GOLD + Bukkit.getOnlinePlayers().size() + ChatColor.GRAY + " | " + ChatColor.WHITE + "Server: " + ChatColor.GOLD + getServer().getMotd() + ChatColor.GRAY + " | " + ChatColor.WHITE + "Ping: " + ChatColor.GOLD + player.getPing() + "  ");
        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.getPlayer().setGameMode(GameMode.ADVENTURE);
        e.getPlayer().setMaxHealth(20);
        e.getPlayer().setFoodLevel(20);
        e.getPlayer().setWalkSpeed(0.4f);
        e.setJoinMessage(null);
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

    @Override
    public void onDisable() {
        System.out.println("LobbyPlugin is now disabled.");
        HandlerList.unregisterAll();
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
    public void onBoom(final EntityExplodeEvent e) {
        final Entity entity = e.getEntity();
        if (entity != null && entity instanceof TNTPrimed) {
            e.blockList().clear();
        }
    }

}

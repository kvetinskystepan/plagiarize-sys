package com.thenarbox.authplugin;

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
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Log4j2(topic = "AuthPlugin")
public final class AuthPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        log.info("AuthPlugin is enabled!");

        // Register standards
        {
            Standards.worlds();
            Standards.commands();
        }

        getServer().getPluginManager()
                .registerEvents(this, this);
        getServer().setDefaultGameMode(GameMode.ADVENTURE);

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                sendTablistToPlayers();
            }
        }, 0, 20);
    }
    @Override
    public void onDisable() {
        log.info("AuthPlugin is disabled!");

        HandlerList.unregisterAll();
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
        Player player = e.getPlayer();
        player.hidePlayer(player);
        Bukkit.getOnlinePlayers().forEach(online -> {
            player.hidePlayer(online);
            online.hidePlayer(player);
        });
        player.setGameMode(getServer().getDefaultGameMode());
        player.setMaxHealth(20);
        player.setFoodLevel(20);
        e.setJoinMessage(null);
        player.teleport(new Location(Bukkit.getWorld("world"), -48.5, 65.5, 34.5, -50, 0));
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

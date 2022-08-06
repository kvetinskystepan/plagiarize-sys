package com.thenarbox.survivalplugin;

import lombok.extern.log4j.Log4j2;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Log4j2(topic = "SurvivalPlugin")
public class SurvivalPlugin extends JavaPlugin implements Listener {
    public void onEnable() {
        log.info("SurvivalPlugin has been enabled.");

        getServer().getPluginManager().registerEvents(this, this);
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
        Player player = e.getPlayer();
        e.setJoinMessage(ChatColor.GREEN + "+" + ChatColor.GRAY + " | " + ChatColor.GOLD + player.getName() + ChatColor.WHITE + " se připojil.");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.RED + "-" + ChatColor.GRAY + " | " + ChatColor.GOLD + player.getName() + ChatColor.WHITE + " se odpojil.");
    }
}

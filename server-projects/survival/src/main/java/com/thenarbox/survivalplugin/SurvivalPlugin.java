package com.thenarbox.survivalplugin;

import com.thenarbox.api.Standards;
import lombok.extern.log4j.Log4j2;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

@Log4j2(topic = "SurvivalPlugin")
public class SurvivalPlugin extends JavaPlugin implements Listener {
    public void onEnable() {
        log.info("SurvivalPlugin has been enabled.");

        Standards.tab(this);
        Standards.commands();
        getServer().getPluginManager().registerEvents(this, this);
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

package com.thenarbox.survivalplugin;

import com.thenarbox.api.Standards;
import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

@Log4j2(topic = "SurvivalPlugin")
public class SurvivalPlugin extends JavaPlugin implements Listener {
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

        Standards.commands();
        Standards.View.tab(this);

        getServer().getPluginManager()
                .registerEvents(this, this);
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        String replaced = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%"));

        if (replaced.equals("")){
            e.setFormat(ChatColor.WHITE + player.getName() + ": " + ChatColor.GRAY + e.getMessage());
        }
        else {
            e.setFormat(replaced + ChatColor.GRAY + " | " + ChatColor.WHITE + player.getName() + ": " + e.getMessage());
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

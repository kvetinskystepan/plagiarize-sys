package com.thenarbox.boxfightplugin;

import com.thenarbox.api.ChatNotice;
import com.thenarbox.api.Standards;
import com.thenarbox.api.colors.ColorAPI;
import com.thenarbox.api.ranks.Rank;
import com.thenarbox.boxfightplugin.listeners.Command;
import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

// #FBC254
// #FDF999

@Log4j2(topic = "BoxFightPlugin")
public final class BoxFightPlugin extends JavaPlugin implements Listener {

    public static HashMap<String, Long> cooldownMap = new HashMap<>();
    static int cooldownTime = 5;

    public void onEnable() {
        log.error("SPRÁVA BOXFIGHT MEJS.CZ");
        log.error(" ");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        log.error("Probíhá inicializace...50%");

        if(!getServer().getPluginManager().isPluginEnabled("Vault")){
            log.error("Vault is not enabled!");
        }

        if(!getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")){
            log.error("PlaceholderAPI is not enabled!");
        }

        getServer().getPluginManager()
                .registerEvents(this, this);
        getServer().getPluginManager()
                .registerEvents(new Command(), this);

        Command.cmds(this);
        Standards.View.tab(this);
        Standards.commands(this);

        log.error(" ");
        log.error("Inicializace proběhla úspěšně.");
        log.error(" ");
        log.error("SPRÁVA BOXFIGHT MEJS.CZ");
    }

    public void onDisable() {
        HandlerList.unregisterAll();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void chat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        String replaced = ChatColor.translateAlternateColorCodes('&', Rank.getRankPrefix(PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%")));
        String level = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points%");

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
        e.setJoinMessage(ChatColor.GREEN + "+" + ChatColor.GRAY + " | " + ChatColor.GOLD + ColorAPI.process("<GRADIENT:FBC254>"+player.getName()+"</GRADIENT:FBC254>") + ChatColor.WHITE + " se připojil.");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.RED + "-" + ChatColor.GRAY + " | " + ChatColor.GOLD + ColorAPI.process("<GRADIENT:FBC254>" + player.getName() + "</GRADIENT:FBC254>") + ChatColor.WHITE + " se odpojil.");
    }

}

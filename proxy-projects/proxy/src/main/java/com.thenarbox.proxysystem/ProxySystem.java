package com.thenarbox.proxysystem;
import com.thenarbox.proxysystem.listeners.CommandMechanic;
import com.thenarbox.proxysystem.messages.PrivateMessages;
import com.thenarbox.proxysystem.motd.Motd;
import lombok.Getter;
import me.clip.placeholderapi.libs.kyori.adventure.Adventure;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public final class ProxySystem extends Plugin implements Listener {

    @Getter
    private static Plugin staticInstance = null;

    public ProxySystem() {
        staticInstance = this;
    }

    @Override
    public void onEnable() {
        System.out.println("ProxySystem is now enabled.");
        ProxyServer.getInstance().getPluginManager().registerListener(this, new PrivateMessages());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new Motd());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new CommandMechanic());
        CommandMechanic.Commands();

        ProxyServer.getInstance().getScheduler().schedule(this, new Runnable() {
            @Override
            public void run() {
                try {
                    tab();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 30, TimeUnit.MILLISECONDS);
    }


    public void tab() {
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            final var playerServer = player.getServer();
            if(playerServer == null)
                return;
            final String serverName = ChatColor.translateAlternateColorCodes('&', "&x&0&4&d&d&f&b&lM&x&0&c&d&6&f&b&lE&x&1&4&d&0&f&c&lJ&x&1&c&c&9&f&c&lS&x&2&4&c&2&f&c&l.&x&2&c&b&c&f&d&lC&x&3&4&b&5&f&d&lZ");
            player.setTabHeader(new TextComponent("\n" + serverName + "\n" + "\n" + ChatColor.GRAY + "discord.mejs.cz" + "\n"), new TextComponent("\n" + "  " + ChatColor.WHITE + "Hráčů: " + ChatColor.AQUA + ProxyServer.getInstance().getPlayers().size() + ChatColor.GRAY + " | " + ChatColor.WHITE + "Server: " + ChatColor.AQUA + player.getServer().getInfo().getMotd() + ChatColor.GRAY + " | " + ChatColor.WHITE + "Ping: " + ChatColor.AQUA + player.getPing() + "  "));
        }
    }


    @Override
    public void onDisable() {
        System.out.println("ProxySystem is now disabled.");
    }


}

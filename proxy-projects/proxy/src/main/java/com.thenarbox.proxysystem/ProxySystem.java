package com.thenarbox.proxysystem;
import com.thenarbox.proxysystem.listeners.CommandMechanic;
import com.thenarbox.proxysystem.messages.PrivateMessages;
import com.thenarbox.proxysystem.motd.Motd;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
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

            final var color = TextComponent.fromLegacyText("§x§0§f§6§2§a§f");
            final var serverName = TextComponent.fromLegacyText("§x§1§5§8§f§f§f§lM§x§2§b§7§b§f§f§lE§x§4§1§6§6§f§f§lJ§x§5§7§5§2§f§f§lS§x§6§c§3§e§f§f§l.§x§8§2§2§9§f§f§lC§x§9§8§1§5§f§f§lZ");

            final ComponentBuilder builder = new ComponentBuilder();
            final ComponentBuilder builder1 = new ComponentBuilder();

            builder.append("\n").append(serverName).append("\n \n").append(ChatColor.GRAY + "discord.mejs.cz").append("\n");
            builder1.append("\n").append(ChatColor.WHITE + " Hráčů:").append(color).append(" " + ProxyServer.getInstance().getPlayers().size()).append(ChatColor.GRAY + " | ").append(ChatColor.WHITE + "Server:").append(color).append(" " + player.getServer().getInfo().getMotd()).append(ChatColor.GRAY + " | ").append(ChatColor.WHITE + "Ping:").append(color).append(" " + player.getPing()).append(" ");

            player.setTabHeader(new TextComponent(builder.create()), new TextComponent(builder1.create()));
        }
    }


    @Override
    public void onDisable() {
        System.out.println("ProxySystem is now disabled.");
    }


}

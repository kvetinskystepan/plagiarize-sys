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

            final var color = TextComponent.fromLegacyText("§x§1§7§b§3§c§8");
            final var serverName = TextComponent.fromLegacyText("§x§1§7§b§3§c§8§lM§x§1§9§c§0§c§4§lE§x§1§c§c§c§c§0§lJ§x§1§e§d§9§b§c§lS§x§2§0§e§6§b§8§l.§x§2§3§f§2§b§4§lC§x§2§5§f§f§b§0§lZ");

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

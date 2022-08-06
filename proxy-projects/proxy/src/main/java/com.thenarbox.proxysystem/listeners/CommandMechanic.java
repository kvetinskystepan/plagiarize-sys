package com.thenarbox.proxysystem.listeners;

import com.thenarbox.api.ChatNotice;
import com.thenarbox.proxysystem.ProxySystem;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

public class CommandMechanic implements Listener {
    public CommandMechanic() {
    }


    public static void Commands(){
        {
            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("lobby") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if(!player.getServer().getInfo().getMotd().equals("Lobby-1")){
                        ChatNotice.success(player, Component.text("Přesunušeš se na Lobby."));
                        player.connect(ProxyServer.getInstance().getServerInfo("Lobby-1"));
                        return;
                    }
                    ChatNotice.error(player, Component.text("Jsi již na Lobby."));
                }
            });
        }
        {
            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("hub") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if(!player.getServer().getInfo().getMotd().equals("Lobby-1")){
                        ChatNotice.success(player, Component.text("Přesunušeš se na Lobby."));
                        player.connect(ProxyServer.getInstance().getServerInfo("Lobby-1"));
                        return;
                    }
                    ChatNotice.error(player, Component.text("Jsi již na Lobby."));
                }
            });
        }
    }
}

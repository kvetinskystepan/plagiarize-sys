package com.thenarbox.proxysystem.listeners;

import com.thenarbox.api.ChatNotice;
import com.thenarbox.proxysystem.ProxySystem;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import com.thenarbox.proxysystem.services.LobbyServiceControl;
import net.md_5.bungee.api.plugin.Plugin;

public class CommandMechanic implements Listener {
    public CommandMechanic() {
    }
    @Getter
    public static LobbyServiceControl control = new LobbyServiceControl();

    public static void Commands(){
        {
            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("lobby") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;

                    if(player.getServer().getInfo().getMotd() == "Lobby-1"){
                        ChatNotice.error(player, Component.text("Již se nacházíš na Lobby."));
                        return;
                    }

                    if (control.isOnline()){
                        player.connect(ProxyServer.getInstance().getServerInfo("Lobby-1"));
                    } else {
                        ChatNotice.error(player, Component.text("Žádný lobby server není dostupný. Zkus to prosím později."));
                    }
                }
            });
        }
    }
}

package com.thenarbox.proxysystem.listeners;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyListener implements Listener {

    @EventHandler
    public void handleEvent(PluginMessageEvent event) {
        if (!event.getTag().equals("BungeeCord")) {
            return;
        }
        var data = ByteStreams.newDataInput(event.getData());
        var subChannel = data.readUTF();
        if (subChannel.equals("ExecuteCommand")) {
            var command = data.readUTF();
            var player = (ProxiedPlayer) event.getReceiver();
            player.chat("/"+command);
            event.setCancelled(true);
        }
    }
}

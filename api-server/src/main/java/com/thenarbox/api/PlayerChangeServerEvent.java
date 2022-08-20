package com.thenarbox.api;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class PlayerChangeServerEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private Player playerPla;
    private String serverStr;
    private String serverAddressStr;

    public static Plugin instance;

    public static void connect(Player player, String serverName) {
        PlayerChangeServerEvent event = new PlayerChangeServerEvent(player, serverName);
        Bukkit.getPluginManager().callEvent(event);

        if(!event.isCancelled()) {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);

            ChatNotice.success(player, Component.text("Připojuješ se k " + serverName + " serveru"));

            try {
                out.writeUTF("Connect");
                out.writeUTF(serverName);
            } catch(IOException ex) {}
            player.sendPluginMessage(instance, "BungeeCord", b.toByteArray());
        }
    }

    public PlayerChangeServerEvent(Player player, String server) {
        playerPla = player;
        serverStr = server;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public Player getPlayer() {
        return playerPla;
    }

    public String getServer() {
        return serverStr;
    }

    public String getServerAddress() {
        return serverAddressStr;
    }

    public void setServer(String server) {
        serverStr = server;
    }

    public void setServerAddress(String serverAddress) {
        serverAddressStr = serverAddress;
    }

}

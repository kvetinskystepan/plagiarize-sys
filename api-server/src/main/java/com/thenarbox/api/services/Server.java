package com.thenarbox.api.services;

import com.thenarbox.api.ping.MinecraftPing;
import com.thenarbox.api.ping.MinecraftPingOptions;
import com.thenarbox.api.ping.MinecraftPingReply;
import org.bukkit.ChatColor;

import java.io.IOException;

public class Server {

    public static String status(String hostname, int port) {
        MinecraftPingReply data = null;

        try {
            data = new MinecraftPing().getPing(new MinecraftPingOptions().setHostname(hostname).setPort(port));
        } catch (final IOException e) {
            return ChatColor.RED + "Offline";
        }

        if (data.getDescription().getText().trim().equalsIgnoreCase("")){
            return ChatColor.RED + "Offline";
        }
        else {
            return ChatColor.GREEN + "Online";
        }
    }

    public static double version(String hostname, int port){
        MinecraftPingReply data = null;

        try {
            data = new MinecraftPing().getPing(new MinecraftPingOptions().setHostname(hostname).setPort(port));
        } catch (final IOException e) {
            return 0;
        }

        if (data.getVersion().getProtocol() == 759){
            return 1.19;
        }
        else {
            return data.getVersion().getProtocol();
        }
    }

    public static int PlayerCount(String hostname, int port) {
        MinecraftPingReply data = null;

        try {
            data = new MinecraftPing().getPing(new MinecraftPingOptions().setHostname(hostname).setPort(port));
        } catch (final IOException e) {
            return 0;
        }

        return data.getPlayers().getOnline();
    }



}

package com.thenarbox.api.services;

import com.thenarbox.api.ping.mcping.MinecraftPing;
import com.thenarbox.api.ping.mcping.MinecraftPingOptions;
import com.thenarbox.api.ping.mcping.MinecraftPingReply;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Server {

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

package com.thenarbox.api.services;

import com.thenarbox.api.ping.MinecraftPing;
import com.thenarbox.api.ping.MinecraftPingOptions;
import com.thenarbox.api.ping.MinecraftPingReply;

import java.io.IOException;

public class Server {

    public static int PlayerCount(String hostname, int port) {
        MinecraftPingReply data = null;

        try {
            data = new MinecraftPing().getPing(new MinecraftPingOptions().setHostname(hostname).setPort(port));
        } catch (final IOException e) {
            return -1;
        }

        return data.getPlayers().getOnline();
    }



}

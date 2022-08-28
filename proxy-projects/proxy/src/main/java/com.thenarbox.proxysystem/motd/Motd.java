package com.thenarbox.proxysystem.motd;

import com.thenarbox.api.colors.ColorAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;

public class Motd implements Listener {
    private static Motd instance;
    public Motd() {

    }

    public static Motd getInstance() {
        return instance;
    }


    @EventHandler(priority = 64)
    public void onProxyPing(ProxyPingEvent e) throws IOException {
        ServerPing ping = e.getResponse();
        getInstance();
        String mod1 = "MEJS.cz";
        ping.setDescription(mod1);
        e.setResponse(ping);
    }
}

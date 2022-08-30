package com.thenarbox.proxysystem.motd;

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
        String version = ChatColor.translateAlternateColorCodes('&', " &x&0&4&d&d&f&b&l1&x&0&f&c&9&f&c&l.&x&1&9&b&6&f&c&l1&x&2&4&a&2&f&d&l9");
        String mod1 = ChatColor.translateAlternateColorCodes('&', "&x&b&3&5&3&f&f&lM&x&a&0&8&7&f&f&lE&x&8&e&b&b&f&f&lJ&x&7&b&e&f&f&f&lS&x&5&b&d&d&f&f&l.&x&3&b&c&c&f&f&lC&x&1&b&b&a&f&f&lZ " + "&x&e&4&7&9&f&f&l⇒" + version);
        String mod2 = ChatColor.translateAlternateColorCodes('&', "&x&e&4&7&9&f&fS&x&e&0&7&8&f&fe&x&d&c&7&7&f&fr&x&d&9&7&6&f&fv&x&d&5&7&5&f&fe&x&d&1&7&5&f&fr &x&c&d&7&4&f&fj&x&c&9&7&3&f&fe &x&c&6&7&2&f&fv &x&c&2&7&1&f&fr&x&b&e&7&0&f&fe&x&b&a&6&f&f&fž&x&b&6&6&e&f&fi&x&b&3&6&d&f&fm&x&a&f&6&c&f&fu &x&a&b&6&c&f&fú&x&a&7&6&b&f&fd&x&a&3&6&a&f&fr&x&a&0&6&9&f&fž&x&9&c&6&8&f&fb&x&9&8&6&7&f&fy");
        ping.setDescription(mod1 + "\n" + mod2);
        e.setResponse(ping);
    }
}

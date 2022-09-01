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
        String mod2 = ChatColor.translateAlternateColorCodes('&', "&x&1&7&b&3&c&8P&x&1&8&b&6&c&7r&x&1&8&b&9&c&6á&x&1&9&b&c&c&5v&x&1&9&b&f&c&4ě &x&1&a&c&2&c&3p&x&1&a&c&5&c&2r&x&1&b&c&7&c&2o&x&1&b&c&a&c&1b&x&1&c&c&d&c&0í&x&1&c&d&0&b&fh&x&1&d&d&3&b&eá &x&1&d&d&6&b&dú&x&1&e&d&9&b&cd&x&1&f&d&c&b&br&x&1&f&d&f&b&až&x&2&0&e&2&b&9b&x&2&0&e&5&b&8a &x&2&1&e&8&b&7s&x&2&1&e&b&b&6e&x&2&2&e&d&b&6r&x&2&2&f&0&b&5v&x&2&3&f&3&b&4e&x&2&3&f&6&b&3r&x&2&4&f&9&b&2u&x&2&4&f&c&b&1.&x&2&5&f&f&b&0.");
        String mod1 = ChatColor.translateAlternateColorCodes('&', "&x&1&7&b&3&c&8&lM&x&1&8&b&7&c&7&le&x&1&9&b&b&c&5&lj&x&1&9&c&0&c&4&ls&x&1&a&c&4&c&3&l.&x&1&b&c&8&c&1&lc&x&1&c&c&c&c&0&lz &f&l【 &x&1&e&d&9&b&c&l1&x&1&f&d&d&b&b&l.&x&2&0&e&1&b&9&l1&x&2&0&e&6&b&8&l6 &x&2&1&e&a&b&7&l- &x&2&2&e&e&b&5&l1&x&2&3&f&2&b&4&l.&x&2&3&f&7&b&3&l1&x&2&4&f&b&b&1&l9 &f&l】");
        ping.setDescription(mod1 + "\n" + mod2);
        e.setResponse(ping);
    }
}

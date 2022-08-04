package com.thenarbox.proxysystem.listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

public class CommandMechanic extends Command implements Listener {
    public CommandMechanic() {
        super("ping");
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }


        ProxiedPlayer player = (ProxiedPlayer) sender;
        player.sendMessage(ChatColor.GRAY + "Your ping is " + ChatColor.GOLD + player.getPing() + ChatColor.GRAY + "ms.");
    }
}

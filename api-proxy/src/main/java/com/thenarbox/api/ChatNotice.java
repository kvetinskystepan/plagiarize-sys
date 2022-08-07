package com.thenarbox.api;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.kyori.adventure.text.Component;

public class ChatNotice {

    public ChatNotice() {
    }

    public static void info(ProxiedPlayer player, TextComponent component){
        player.sendMessage(ChatColor.GOLD + "✎ "  + ChatColor.GRAY + "| " + ChatColor.WHITE + component.toPlainText());
    }

    public static void error(ProxiedPlayer player, Component component){
        player.sendMessage(ChatColor.DARK_RED + "✖ " + ChatColor.GRAY + "| " + ChatColor.WHITE + PlainTextComponentSerializer.plainText().serialize(component));
    }

    public static void success(ProxiedPlayer player, Component component){
        player.sendMessage(ChatColor.GREEN + "✔ " + ChatColor.GRAY + "| " + ChatColor.WHITE + PlainTextComponentSerializer.plainText().serialize(component));
    }

    public static void warning(ProxiedPlayer player, Component component){
        player.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.GRAY + "| " + ChatColor.WHITE + PlainTextComponentSerializer.plainText().serialize(component));
    }

}

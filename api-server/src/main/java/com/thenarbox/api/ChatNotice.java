package com.thenarbox.api;

import lombok.extern.log4j.Log4j2;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Log4j2(topic = "ChatNotice")
public class ChatNotice {

    public ChatNotice() {
    }

    public static void infoHover(Player player, TextComponent component){
        player.sendMessage(ChatColor.AQUA + "✎ "  + ChatColor.GRAY + "| " + ChatColor.WHITE + component.toPlainText());
    }

    public static void info(Player player, Component component){
        player.sendMessage(ChatColor.AQUA + "✎ "  + ChatColor.GRAY + "| " + ChatColor.WHITE + PlainTextComponentSerializer.plainText().serialize(component));
    }

    public static void error(Player player, Component component){
        player.sendMessage(ChatColor.DARK_RED + "✖ " + ChatColor.GRAY + "| " + ChatColor.WHITE + PlainTextComponentSerializer.plainText().serialize(component));
    }

    public static void success(Player player, Component component){
        player.sendMessage(ChatColor.GREEN + "✔ " + ChatColor.GRAY + "| " + ChatColor.WHITE + PlainTextComponentSerializer.plainText().serialize(component));
    }

    public static void warning(Player player, Component component){
        player.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.GRAY + "| " + ChatColor.WHITE + PlainTextComponentSerializer.plainText().serialize(component));
    }

}

package com.thenarbox.api;

import lombok.extern.log4j.Log4j2;
import org.bukkit.ChatColor;

@Log4j2(topic = "ChatNotice")
public class ChatNotice {

    public ChatNotice() {
    }

    public static String chatInfoNotice(String text){
        return ChatColor.GOLD + "✎ " + ChatColor.WHITE + text;
    }

    public static String chatErrorNotice(String text){
        return ChatColor.DARK_RED + "✖ " + ChatColor.GRAY + "| " + ChatColor.WHITE + text;
    }

    public static String chatSuccessNotice(String text){
        return ChatColor.GREEN + "✔ " + ChatColor.GRAY + "| " + ChatColor.WHITE + text;
    }

    public static String chatWarningNotice(String text){
        return ChatColor.YELLOW + "⚠ " + ChatColor.GRAY + "| " + ChatColor.WHITE + text;
    }

}

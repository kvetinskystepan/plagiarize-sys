package com.thenarbox.api;

import lombok.extern.log4j.Log4j2;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;

@Log4j2(topic = "ChatNotice")
public class ChatNotice {

    public ChatNotice() {
    }

    public String ChatErrorNotice(Component component){
        return ChatColor.DARK_RED + "✖ " + component;
    }

}

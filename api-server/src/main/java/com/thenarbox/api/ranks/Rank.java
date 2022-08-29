package com.thenarbox.api.ranks;

import com.thenarbox.api.ChatNotice;
import lombok.Getter;

import java.awt.*;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public enum Rank implements Listener {

    MAJITEL("majitel", "Majitel", "Majitel", 'A', new Color(179, 0, 0)),


    ;

    public boolean has(final Rank rank) {
        return has(null, rank, false);
    }

    public boolean has(final @Nullable Object player,
                       final Rank rank,
                       final boolean inform) {
        if (compareTo(rank) <= 0)
            return true;
        if (inform && player != null)
            ChatNotice.error((Player) player, Component.text("Minimal rank " + rank.getName()));
        return false;
    }

    private final @NotNull
    String identifier;
    private final @NotNull
    String name;
    private final @NotNull
    String tabPrefix;

    private final @NotNull
    Character alphabet;
    private final @NotNull
    Color color;


    Rank(@NotNull final String identifier,
         @NotNull final String name,
         @Nullable final String tabPrefix,
         @NotNull final Character alphabet,
         @NotNull final Color color) {
        this.identifier = identifier;
        this.name = name;
        this.tabPrefix = tabPrefix == null ? name : tabPrefix;
        this.alphabet = alphabet;
        this.color = color;
    }
}

package com.thenarbox.boxfightplugin.listeners;

import com.thenarbox.api.ChatNotice;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Command implements Listener {


    public static void cmds(Plugin plugin){

        Location spawn = new Location(Bukkit.getWorld("world"), 87.5, 89, 54.5, 90, 0);

        {
            Bukkit.getCommandMap().register("boxfight", new org.bukkit.command.Command("spawn") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    final Player player = (Player) sender;
                    player.teleport(spawn);
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                    player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 1);
                    ChatNotice.success(player, Component.text("Byl jsi teleportován na spawn."));
                    return false;
                }
            });
        }



    }
}

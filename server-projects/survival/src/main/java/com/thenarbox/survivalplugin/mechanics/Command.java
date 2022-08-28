package com.thenarbox.survivalplugin.mechanics;

import com.thenarbox.survivalplugin.services.Menus;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class Command implements Listener {

    public static void commands(){

        {
            Bukkit.getCommandMap().register("survival", new org.bukkit.command.Command("menu") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("menu")){
                        Menus.mainMenu(player);
                    }

                    return false;
                }
            });

        }
    }




}

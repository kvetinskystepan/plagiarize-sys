package com.thenarbox.survivalplugin.mechanics;

import com.thenarbox.api.ChatNotice;
import com.thenarbox.survivalplugin.services.Menus;
import com.thenarbox.survivalplugin.services.RandomTeleport;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;


public class Command implements Listener {

    public static HashMap<String, Long> cooldowns = new HashMap<>();
    static int cooldownTime = 60;

    public static void commands(){

        {
            Bukkit.getCommandMap().register("survival", new org.bukkit.command.Command("hlasovani") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;
                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("hlasovani")){
                        Menus.voteMenuMain(player);
                    }
                    return false;
                }
            });

        }

        {
            Bukkit.getCommandMap().register("survival", new org.bukkit.command.Command("rtp") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("rtp")){

                        if(cooldowns.containsKey(player.getName())) {
                            long secondsLeft = ((cooldowns.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                            if(secondsLeft>0) {
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                                ChatNotice.error(player, Component.text("Nemůžeš se teleportovat tak rychle! Zkus to znovu za: " + secondsLeft + " sekund."));
                                return true;
                            }
                        }
                        cooldowns.put(player.getName(), System.currentTimeMillis());
                        RandomTeleport.teleport(player, Objects.requireNonNull(Bukkit.getWorld("world")));
                        return true;
                    }

                    return false;
                }
            });

        }

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

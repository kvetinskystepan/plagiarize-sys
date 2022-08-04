package com.thenarbox.api;

import lombok.extern.log4j.Log4j2;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


/**
 * Standards for plugins.
 */
@Log4j2(topic = "Standards")
public class Standards {

    public static ArrayList<Player> flyingPlayers = new ArrayList<Player>();

    /**
     * Handle world standards.
     */
    public static void worlds() {
        final var world = Bukkit.getServer()
                .getWorld("world");

        if(world != null) {
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
            world.setGameRule(GameRule.DO_FIRE_TICK, false);
            world.setGameRule(GameRule.MOB_GRIEFING, false);
            world.setGameRule(GameRule.FALL_DAMAGE, false);
            world.setGameRule(GameRule.SPAWN_RADIUS, 1000000);
            world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            world.setFullTime(6000);
            world.getWorldBorder().reset();
        } else {
            log.warn("Default world not found.");
        }
    }

    /**
     * Handle command standards.
     */
    public static void commands() {

        {
            Bukkit.getCommandMap().register("fly", new Command("fly") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("fly")) {

                        if (args.length == 0) {
                            if (flyingPlayers.contains(player)) {
                                player.setFlying(false);
                                flyingPlayers.remove(player);
                                sender.sendMessage(ChatNotice.chatSuccessNotice("Létání bylo vypnuto."));
                            }
                            else {
                                flyingPlayers.add(player);
                                player.setFlying(true);
                                sender.sendMessage(ChatNotice.chatSuccessNotice("Létání bylo zapnuto."));
                            }
                        }
                        else if (args.length == 1){
                            Player toPlayer = Bukkit.getPlayer(args[0]);
                            if (toPlayer != null) {
                                if (flyingPlayers.contains(toPlayer)) {
                                    toPlayer.setFlying(false);
                                    flyingPlayers.remove(toPlayer);
                                    toPlayer.sendMessage(ChatNotice.chatSuccessNotice("Létání bylo vypnuto."));
                                    sender.sendMessage(ChatNotice.chatSuccessNotice("Létání hráči "+ChatColor.GOLD+toPlayer.getName()+ChatColor.WHITE+" bylo vypnuto."));
                                }
                                else {
                                    flyingPlayers.add(toPlayer);
                                    toPlayer.setFlying(true);
                                    toPlayer.sendMessage(ChatNotice.chatSuccessNotice("Létání bylo zapnuto."));
                                    sender.sendMessage(ChatNotice.chatSuccessNotice("Létání hráči "+ChatColor.GOLD+toPlayer.getName()+ChatColor.WHITE+" bylo zapnuto."));
                                }
                            }
                            else {
                                sender.sendMessage(ChatNotice.chatErrorNotice(ChatColor.WHITE + "Hráč " + args[0] + " není online."));
                            }
                        }
                    }
                    return true;
                }
            });
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if (flyingPlayers.contains(e.getPlayer())) {
            flyingPlayers.remove(e.getPlayer());
        }
    }

}

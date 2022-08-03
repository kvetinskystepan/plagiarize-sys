package com.thenarbox.api;

import lombok.extern.log4j.Log4j2;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Standards for plugins.
 */
@Log4j2(topic = "Standards")
public class Standards {

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
        } else {
            log.warn("Default world not found.");
        }
    }

    /**
     * Handle command standards.
     */
    public static void commands() {

        // sudo command
        {
            Bukkit.getCommandMap().register("narbox", new Command("sudo") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    if (commandLabel.equalsIgnoreCase("sudo")) {
                        if (args.length == 0) {
                            sender.sendMessage("/sudo <player>");
                            return true;
                        }
                        if (args.length == 1) {
                            sender.sendMessage("/sudo <player> <command>");
                            return true;
                        }
                        if (args.length == 2) {
                            Player toPlayer = Bukkit.getPlayer(args[0]);
                            if (toPlayer == null) {
                                sender.sendMessage("Player not found");
                                return true;
                            }
                            if (args[1].startsWith("/"))
                                toPlayer.performCommand(args[1]);
                            else
                                toPlayer.chat(args[1]);
                        }
                    }
                    return true;
                }
            });


            // test commands
            {
                Bukkit.getCommandMap().register("narbox", new Command("test") {
                    @Override
                    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                        return true;
                    }
                });
            }
        }
    }

}

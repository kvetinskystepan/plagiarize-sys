package com.thenarbox.api;

import lombok.extern.log4j.Log4j2;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

                    if (commandLabel.equalsIgnoreCase("fly")) {
                        if (args.length == 0) {
                            ((Player) sender).setFlying(true);
                            sender.sendMessage(ChatNotice.chatSuccessNotice("Létání bylo zapnuto."));
                            return false;
                        }
                        else if (args.length == 1){
                            Player toPlayer = Bukkit.getPlayer(args[0]);
                            if (toPlayer != null) {
                                toPlayer.setFlying(true);
                                toPlayer.sendMessage(ChatNotice.chatSuccessNotice("Létání bylo zapnuto."));
                                sender.sendMessage(ChatNotice.chatSuccessNotice("Létání hráči "+ChatColor.GOLD+toPlayer.getName()+ChatColor.WHITE+" bylo zapnuto."));
                            }
                            else {
                                sender.sendMessage(ChatNotice.chatErrorNotice(ChatColor.WHITE + "Hráč " + args[0] + " není online."));
                            }
                            return false;
                        }
                        else {
                            sender.sendMessage(ChatNotice.chatErrorNotice(ChatColor.WHITE + "Syntaxe příkazu: /fly [hráč]"));
                        }
                    }
                    return true;
                }
            });
        }

        // sudo command
        {
            Bukkit.getCommandMap().register("sudo", new Command("sudo") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    if (commandLabel.equalsIgnoreCase("sudo")) {
                        if (args.length < 2) {
                            sender.sendMessage(ChatNotice.chatErrorNotice(ChatColor.WHITE + "Syntaxe příkazu: /sudo <hráč> <příkaz|zpráva>"));
                            return true;
                        }
                        if (args.length == 2) {
                            Player toPlayer = Bukkit.getPlayer(args[0]);
                            if (toPlayer == null) {
                                sender.sendMessage(ChatNotice.chatErrorNotice(ChatColor.WHITE + "Hráč nebyl nalezen. + [ "+ args[0] +" ]"));
                                return true;
                            }
                            sender.sendMessage(args.length + " delka");
                            final StringBuilder sb = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                sb.append(args[i]).append(" ");
                            }

                            if (args[1].startsWith("/"))
                                toPlayer.performCommand(sb.toString());
                            else
                                toPlayer.chat(sb.toString());
                        }
                    }
                    return true;
                }
            });
        }
    }

}

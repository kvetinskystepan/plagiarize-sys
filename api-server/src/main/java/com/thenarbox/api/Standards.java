package com.thenarbox.api;

import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getServer;

@Log4j2(topic = "Standards")
public class Standards {

    public class View{
        public static void tab(Plugin plugin){
            final Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    var team = scoreboard.getTeam(player.getName());
                    if(team == null)
                        team = scoreboard.registerNewTeam(player.getName());
                    String prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
                    team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix + ChatColor.GRAY + " | " + ChatColor.WHITE));
                    team.addPlayer(player);
                }
            }, 0, 20);
        }
    }

    public static void worlds() {
        final var world = getServer()
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

    public static void commands() {

        {
            Bukkit.getCommandMap().register("", new Command("fly") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    final Player player = (Player) sender;

                        if (commandLabel.equalsIgnoreCase("fly")) {
                            if(player.hasPermission("standarts.fly")){
                                if (args.length == 0) {
                                    if (player.getAllowFlight()){
                                        player.setAllowFlight(false);
                                        player.setFlying(false);
                                        ChatNotice.success(player, Component.text("Létání bylo vypnuto."));
                                    }
                                    else {
                                        player.setAllowFlight(true);
                                        player.setFlying(true);
                                        ChatNotice.success(player, Component.text("Létání bylo zapnuto."));
                                    }
                                }
                                else if (args.length == 1){
                                    if(player.hasPermission("standarts.fly.other")){
                                        Player toPlayer = Bukkit.getPlayer(args[0]);
                                        if (toPlayer != null) {
                                            if (toPlayer.getAllowFlight()) {
                                                toPlayer.setAllowFlight(false);
                                                toPlayer.setFlying(false);
                                                ChatNotice.success(toPlayer, Component.text("Létání bylo vypnuto."));
                                                ChatNotice.success(player, Component.text("Létání hráči "+ChatColor.GOLD+toPlayer.getName()+ChatColor.WHITE+" bylo vypnuto."));
                                            }
                                            else {
                                                toPlayer.setAllowFlight(true);
                                                toPlayer.setFlying(true);
                                                ChatNotice.success(toPlayer, Component.text("Létání bylo zapnuto."));
                                                ChatNotice.success(player, Component.text("Létání hráči "+ChatColor.GOLD+toPlayer.getName()+ChatColor.WHITE+" bylo zapnuto."));
                                            }
                                        }
                                        else {
                                            ChatNotice.error(player, Component.text(ChatColor.WHITE + "Hráč " + args[0] + " není online."));
                                        }
                                    }
                                    else {
                                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Builder."));
                                    }
                                }
                            }
                            else {
                                ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je VIP."));
                            }
                        }
                    return true;
                }
            });
        }
    }
}

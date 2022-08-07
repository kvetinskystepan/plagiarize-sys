package com.thenarbox.api;

import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;


/**
 * Standards for plugins.
 */
@Log4j2(topic = "Standards")
public class Standards {

    public static ArrayList<Player> flyingPlayers = new ArrayList<Player>();

    private static Permission perms = null;

    public static boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    /**
     * Handle world standards.
     */
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

    /**
     * Handle command standards.
     */
    public static void commands() {

        {

            Bukkit.getCommandMap().register("", new Command("restart") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    Player playerCo = (Player) sender;
                    if (!playerCo.hasPermission("standarts.restart")){
                        ChatNotice.error(playerCo, Component.text("Minimální hodnost pro použití toho příkazu je Developer."));
                        return false;
                    }

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        ChatNotice.warning(player, Component.text("Server se bude restartovat. Vyčkejte prosím na znovuspuštění. Omlouváme se za komplikace."));
                        player.performCommand("lobby");
                    }
                    Bukkit.getServer().shutdown();
                    return true;
                }
            });
            Bukkit.getCommandMap().register("", new Command("fly") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    final Player player = (Player) sender;

                        if (commandLabel.equalsIgnoreCase("fly")) {

                            if(!player.hasPermission("standarts.fly")){
                                ChatNotice.error(player, Component.text("Minimální hodnost pro použití toho příkazu je VIP."));
                                return false;
                            }

                        if (args.length == 0) {
                            if (flyingPlayers.contains(player)) {
                                player.setFlying(false);
                                flyingPlayers.remove(player);
                                ChatNotice.success(player, Component.text("Létání bylo vypnuto."));
                            }
                            else {
                                flyingPlayers.add(player);
                                player.setFlying(true);
                                ChatNotice.success(player, Component.text("Létání bylo zapnuto."));
                            }
                        }


                        else if (args.length == 1){
                            if(!player.hasPermission("standarts.fly.other")){
                                ChatNotice.error(player, Component.text("Minimální hodnost pro použití toho příkazu je V.Builder."));
                                return false;
                            }
                            Player toPlayer = Bukkit.getPlayer(args[0]);
                            if (toPlayer != null) {
                                if (flyingPlayers.contains(toPlayer)) {
                                    toPlayer.setFlying(false);
                                    flyingPlayers.remove(toPlayer);
                                    ChatNotice.success(toPlayer, Component.text("Létání bylo vypnuto."));
                                    ChatNotice.success(player, Component.text("Létání hráči "+ChatColor.GOLD+toPlayer.getName()+ChatColor.WHITE+" bylo vypnuto."));
                                }
                                else {
                                    flyingPlayers.add(toPlayer);
                                    toPlayer.setFlying(true);
                                    ChatNotice.success(toPlayer, Component.text("Létání bylo zapnuto."));
                                    ChatNotice.success(player, Component.text("Létání hráči "+ChatColor.GOLD+toPlayer.getName()+ChatColor.WHITE+" bylo zapnuto."));
                                }
                            }
                            else {
                                ChatNotice.error(player, Component.text(ChatColor.WHITE + "Hráč " + args[0] + " není online."));
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

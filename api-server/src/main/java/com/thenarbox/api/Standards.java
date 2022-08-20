package com.thenarbox.api;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

@Log4j2(topic = "Standards")
public class Standards {

    public static ArrayList<String> vanishPlayers = new ArrayList<String>();

    public class View{
        static String priority = "A";
        public static void tab(Plugin plugin){
            final Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    String suffix = PlaceholderAPI.setPlaceholders(player, "%luckperms_suffix%");
                    if (suffix.equalsIgnoreCase("majitel")){
                        priority = "A";
                    }
                    else if (suffix.equalsIgnoreCase("vedení")){
                        priority = "B";
                    }
                    else if (suffix.equalsIgnoreCase("v.developer")){
                        priority = "C";
                    }
                    else if (suffix.equalsIgnoreCase("developer")){
                        priority = "D";
                    }
                    else if (suffix.equalsIgnoreCase("v.helper")){
                        priority = "E";
                    }
                    else if (suffix.equalsIgnoreCase("helper")){
                        priority = "F";
                    }
                    else if (suffix.equalsIgnoreCase("v.builder")){
                        priority = "G";
                    }
                    else if (suffix.equalsIgnoreCase("builder")){
                        priority = "H";
                    }
                    else if (suffix.equalsIgnoreCase("eventer")){
                        priority = "I";
                    }
                    else if (suffix.equalsIgnoreCase("hráč")){
                        priority = "J";
                    }
                    else {
                        priority = "K";
                    }
                    var team = scoreboard.getTeam(priority + player.getName());
                    if(team == null)
                        team = scoreboard.registerNewTeam(priority + player.getName());
                    String prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
                    if (suffix.equalsIgnoreCase("hráč")){
                        team.setPrefix("");
                    }
                    else {
                        team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix + ChatColor.GRAY + " | " + ChatColor.WHITE));
                    }
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
            world.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
            world.setFullTime(6000);
            world.getWorldBorder().reset();
        } else {
            log.warn("Default world not found.");
        }
    }

    public static void commands() {

        {
            Bukkit.getCommandMap().register("global", new Command("uroven") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;
                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("uroven")){
                        player.performCommand("level");
                    }
                    return true;
                }
            });

        }

        {
            Bukkit.getCommandMap().register("global", new Command("level") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("level")){
                        String level = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points%");
                        ChatNotice.info(player, Component.text("Tvá úroveň je: " + ChatColor.GOLD + level));
                    }
                    return true;
                }
            });

        }

        {
            Bukkit.getCommandMap().register("global", new Command("sudo") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("sudo")){
                        if(player.hasPermission("standarts.sudo")){
                            if(args.length < 2){
                                ChatNotice.error(player, Component.text("Použití: /sudo <hráč> <příkaz>"));
                            }
                            else {
                                final Player target = Bukkit.getPlayer(args[0]);
                                if(target != null){
                                    final StringBuilder sb = new StringBuilder();
                                    for (int i = 1; i < args.length; i++) {
                                        sb.append(args[i]).append(" ");
                                    }
                                    target.chat(sb.toString());
                                    ChatNotice.success(player, Component.text("Hráč " + target.getName() + " úspěšně vykonal požadavek."));
                                }
                                else{
                                    ChatNotice.error(player, Component.text("Hráč s tímto nickem nebyl nalezen."));
                                }
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Developer."));
                        }
                    }
                    return true;
                }
            });

        }

        {

            Bukkit.getCommandMap().register("global", new Command("fly") {
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

    public static void survivalCommands(Plugin plugin){


        {
            Bukkit.getCommandMap().register("survival", new Command("v") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("v")){
                        player.performCommand("vanish");
                    }

                    return false;
                }
            });
        }


        {
            Bukkit.getCommandMap().register("survival", new Command("vanish") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("vanish")) {
                        if (player.hasPermission("survival.vanish")){
                            if (vanishPlayers.contains(player.getName())) {
                                vanishPlayers.remove(player.getName());
                                if (player.getGameMode() != GameMode.CREATIVE){
                                    player.setAllowFlight(false);
                                    player.setFlying(false);
                                }
                                Bukkit.getOnlinePlayers().stream().filter(online -> online != player).forEach(online ->
                                        online.showPlayer(plugin, player));
                                final String message = ChatColor.GREEN + "+" + ChatColor.GRAY + " | " + ChatColor.GOLD + player.getName() + ChatColor.WHITE + " se připojil.";
                                for (Player p : Bukkit.getOnlinePlayers())
                                    p.sendMessage(message);
                                ChatNotice.warning(player, Component.text("Vanish deaktivován."));
                            }
                            else {
                                vanishPlayers.add(player.getName());
                                Bukkit.getOnlinePlayers().stream().filter(online -> online != player).forEach(online ->
                                        online.hidePlayer(plugin, player));
                                String message = ChatColor.RED + "-" + ChatColor.GRAY + " | " + ChatColor.GOLD + player.getName() + ChatColor.WHITE + " se odpojil.";
                                for (Player p : Bukkit.getOnlinePlayers())
                                    p.sendMessage(message);
                                for (final Player team : Bukkit.getOnlinePlayers()) {
                                    if (!player.hasPermission("survival.vanish.info"))
                                        continue;
                                    ChatNotice.info(team, Component.text("Člen " + player.getName() + " si aktivoval vanish."));
                                }
                                player.setGameMode(GameMode.SURVIVAL);
                                player.setAllowFlight(true);
                                player.setFlying(true);
                                player.setHealth(player.getMaxHealth());
                                player.setFoodLevel(20);
                                ChatNotice.warning(player, Component.text("Vanish byl aktivován."));
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Helper."));
                        }
                    }
                    return true;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("heal") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("heal")){
                        if (player.hasPermission("survival.heal")){
                            if (args.length < 1){
                                player.setHealth(player.getMaxHealth());
                                player.setFoodLevel(20);
                                ChatNotice.success(player, Component.text("Byl jsi vyléčen."));
                            }
                            if (args.length == 1){
                                if (player.hasPermission("survival.heal.other")){
                                    Player toPlayer = Bukkit.getPlayer(args[0]);
                                    if (toPlayer != null){
                                        toPlayer.setHealth(toPlayer.getMaxHealth());
                                        toPlayer.setFoodLevel(20);
                                        ChatNotice.success(player, Component.text("Uzdravil si hráče " + toPlayer.getName()));
                                    }
                                    else {
                                        ChatNotice.error(player, Component.text("Hráč nebyl nalezen."));
                                    }
                                }
                                else {
                                    ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Helper."));
                                }
                            }
                            else if (args.length > 1){
                                ChatNotice.error(player, Component.text("Použití: /heal <jméno>"));
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

        {
            Bukkit.getCommandMap().register("survival", new Command("tp") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("tp")){
                        if (player.hasPermission("survival.tp")){
                            if (args.length == 0){
                                ChatNotice.error(player, Component.text("Použití: /tp <jméno>"));
                            }
                            else {
                                Player toPlayer = Bukkit.getPlayer(args[0]);
                                if (toPlayer != null){
                                    player.teleport(toPlayer);
                                    ChatNotice.success(player, Component.text("Teleportoval si se na hráče " + toPlayer.getName()));
                                }
                                else {
                                    ChatNotice.error(player, Component.text("Hráč nebyl nalezen."));
                                }
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Helper."));
                        }
                    }
                    return true;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("gmc") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("gmc")){
                        if (player.hasPermission("survival.gmc")){
                            if (args.length < 1){
                                if (player.getGameMode() == GameMode.CREATIVE)
                                    ChatNotice.error(player, Component.text("Již máš zapnutý creative."));
                                else {
                                    player.setGameMode(GameMode.CREATIVE);
                                    ChatNotice.success(player, Component.text("Creative byl zapnut."));
                                }
                            }
                            else if (args.length == 1){
                                Player toPlayer = Bukkit.getPlayer(args[0]);
                                if (toPlayer != null){
                                    if (toPlayer.getGameMode() == GameMode.CREATIVE)
                                        ChatNotice.error(player, Component.text("Hráč " + toPlayer.getName() + " již má zapnutý creative."));
                                    else {
                                        toPlayer.setGameMode(GameMode.CREATIVE);
                                        ChatNotice.success(player, Component.text("Creative byl zapnut pro hráče " + toPlayer.getName()));
                                        ChatNotice.success(toPlayer, Component.text("Creative byl zapnut."));
                                    }
                                }
                                else {
                                    ChatNotice.error(player, Component.text("Hráč nebyl nalezen."));
                                }
                            }
                            else {
                                ChatNotice.error(player, Component.text("Použití: /gmc <jméno>"));
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Builder."));
                        }
                    }
                    return true;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("gms") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("gms")){
                        if (player.hasPermission("survival.gms")){
                            if (args.length < 1){
                                if (player.getGameMode() == GameMode.SURVIVAL)
                                    ChatNotice.error(player, Component.text("Již máš zapnutý survival."));
                                else {
                                    player.setGameMode(GameMode.SURVIVAL);
                                    ChatNotice.success(player, Component.text("Survival byl zapnut."));
                                }
                            }
                            else if (args.length == 1){
                                Player toPlayer = Bukkit.getPlayer(args[0]);
                                if (toPlayer != null){
                                    if (toPlayer.getGameMode() == GameMode.SURVIVAL)
                                        ChatNotice.error(player, Component.text("Hráč " + toPlayer.getName() + " již má zapnutý survival."));
                                    else {
                                        toPlayer.setGameMode(GameMode.SURVIVAL);
                                        ChatNotice.success(player, Component.text("Survival byl zapnut pro hráče " + toPlayer.getName()));
                                        ChatNotice.success(toPlayer, Component.text("Survival byl zapnut."));
                                    }
                                }
                                else {
                                    ChatNotice.error(player, Component.text("Hráč nebyl nalezen."));
                                }
                            }
                            else {
                                ChatNotice.error(player, Component.text("Použití: /gms <jméno>"));
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Builder."));
                        }
                    }
                    return true;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("gma") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("gma")){
                        if (player.hasPermission("survival.gma")){
                            if (args.length < 1){
                                if (player.getGameMode() == GameMode.ADVENTURE)
                                    ChatNotice.error(player, Component.text("Již máš zapnutý adventure."));
                                else {
                                    player.setGameMode(GameMode.ADVENTURE);
                                    ChatNotice.success(player, Component.text("Adventure byl zapnut."));
                                }
                            }
                            else if (args.length == 1){
                                Player toPlayer = Bukkit.getPlayer(args[0]);
                                if (toPlayer != null){
                                    if (toPlayer.getGameMode() == GameMode.ADVENTURE)
                                        ChatNotice.error(player, Component.text("Hráč " + toPlayer.getName() + " již má zapnutý adventure."));
                                    else {
                                        toPlayer.setGameMode(GameMode.ADVENTURE);
                                        ChatNotice.success(player, Component.text("Adventure byl zapnut pro hráče " + toPlayer.getName()));
                                        ChatNotice.success(toPlayer, Component.text("Adventure byl zapnut."));
                                    }
                                }
                                else {
                                    ChatNotice.error(player, Component.text("Hráč nebyl nalezen."));
                                }
                            }
                            else {
                                ChatNotice.error(player, Component.text("Použití: /gma <jméno>"));
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Builder."));
                        }
                    }
                    return true;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("gmsp") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("gmsp")){
                        if (player.hasPermission("survival.gmsp")){
                            if (args.length < 1){
                                if (player.getGameMode() == GameMode.SPECTATOR)
                                    ChatNotice.error(player, Component.text("Již máš zapnutý spectator."));
                                else {
                                    player.setGameMode(GameMode.SPECTATOR);
                                    ChatNotice.success(player, Component.text("Spectator byl zapnut."));
                                }
                            }
                            else if (args.length == 1){
                                Player toPlayer = Bukkit.getPlayer(args[0]);
                                if (toPlayer != null){
                                    if (toPlayer.getGameMode() == GameMode.SPECTATOR)
                                        ChatNotice.error(player, Component.text("Hráč " + toPlayer.getName() + " již má zapnutý spectator."));
                                    else {
                                        toPlayer.setGameMode(GameMode.SPECTATOR);
                                        ChatNotice.success(player, Component.text("Spectator byl zapnut pro hráče " + toPlayer.getName()));
                                        ChatNotice.success(toPlayer, Component.text("Spectator byl zapnut."));
                                    }
                                }
                                else {
                                    ChatNotice.error(player, Component.text("Hráč nebyl nalezen."));
                                }
                            }
                            else {
                                ChatNotice.error(player, Component.text("Použití: /gmsp <jméno>"));
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Builder."));
                        }
                    }
                    return true;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("sun") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("sun")){
                        if (player.hasPermission("survival.sun")){
                            player.getWorld().setStorm(false);
                            player.getWorld().setThundering(false);
                            player.getWorld().setWeatherDuration(0);
                            ChatNotice.success(player, Component.text("Déšť byl vypnut."));
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Builder."));
                        }
                    }
                    return true;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("day") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("day")){
                        if (player.hasPermission("survival.day")){
                            player.getWorld().setFullTime(1000);
                            ChatNotice.success(player, Component.text("Denní čas byl nastaven."));
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Builder."));
                        }
                    }
                    return true;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("night") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof  Player))
                        return false;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("night")){
                        if (player.hasPermission("survival.night")){
                            player.getWorld().setFullTime(13000);
                            ChatNotice.success(player, Component.text("Noční čas byl nastaven."));
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Builder."));
                        }
                    }
                    return true;
                }
            });
        }
    }
}

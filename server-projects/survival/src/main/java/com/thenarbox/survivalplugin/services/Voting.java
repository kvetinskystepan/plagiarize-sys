package com.thenarbox.survivalplugin.services;

import com.thenarbox.api.ChatNotice;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Voting implements Listener {

    static ArrayList<String> votedYes = new ArrayList();
    static ArrayList<String> votedNo = new ArrayList();
    public static boolean voting = false;

    public static BossBar bar;


    public static void votingCmds(){

        {
            Bukkit.getCommandMap().register("survival", new Command("ano") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }
                    Player player = (Player) sender;
                    if (!voting){
                        ChatNotice.error(player, Component.text("V tuto chvíli není otevřené žádné hlasování! Nové zahájíš pomocí /hlasovani"));
                    }
                    else if(votedYes.contains(player.getName()) || votedNo.contains(player.getName())) {
                        ChatNotice.error(player, Component.text("Už jsi hlasoval/a."));
                    }
                    else {
                        votedYes.add(player.getName());
                        ChatNotice.success(player, Component.text("Hlasoval/a jsi pro změnu."));
                    }
                    return false;
                }
            });

        }

        {
            Bukkit.getCommandMap().register("survival", new Command("ne") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }
                    Player player = (Player) sender;
                    if (!voting){
                        ChatNotice.error(player, Component.text("V tuto chvíli není otevřené žádné hlasování! Nové zahájíš pomocí /hlasovani"));
                    }
                    else if(votedYes.contains(player.getName()) || votedNo.contains(player.getName())) {
                        ChatNotice.error(player, Component.text("Už jsi hlasoval/a."));
                    }
                    else {
                        votedNo.add(player.getName());
                        ChatNotice.success(player, Component.text("Hlasoval/a jsi proti změně."));
                    }
                    return false;
                }
            });
        }
    }


    public static void changeToDayTimeVoting(Plugin plugin, Player player){
        if (voting){
            ChatNotice.error(player, Component.text("V tuto chvíli již probíhá nějaké hlasování!"));
        }
        else {
            bar = Bukkit.createBossBar("Hlasování o změně času na den | /ano, /ne", BarColor.GREEN, BarStyle.SOLID);
            bar.setVisible(true);
            bar.setProgress(1);
            Bukkit.getOnlinePlayers().forEach(bar::addPlayer);
            voting = true;
            for (Player players : Bukkit.getOnlinePlayers()){
                players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7| &x&4&1&8&5&f&cH&x&4&5&8&a&f&cL&x&4&a&8&f&f&cA&x&4&f&9&4&f&cS&x&5&3&9&8&f&cO&x&5&8&9&d&f&cV&x&5&d&a&2&f&cÁ&x&6&2&a&7&f&cN&x&6&6&a&b&f&cÍ &x&6&b&b&0&f&cO &x&7&0&b&5&f&cZ&x&7&4&b&a&f&cM&x&7&9&b&f&f&cĚ&x&7&e&c&3&f&cN&x&8&3&c&8&f&cĚ &x&8&7&c&d&f&dČ&x&8&c&d&2&f&dA&x&9&1&d&6&f&dS&x&9&5&d&b&f&dU &x&9&a&e&0&f&dN&x&9&f&e&5&f&dA &x&a&4&e&9&f&dD&x&a&8&e&e&f&dE&x&a&d&f&3&f&dN"));
                players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7| &x&0&8&4&c&f&bH&x&0&e&5&2&f&bl&x&1&4&5&8&f&ba&x&1&a&5&e&f&bs&x&2&0&6&4&f&bu&x&2&5&6&a&f&bj&x&2&b&7&0&f&bt&x&3&1&7&6&f&ce &x&3&7&7&c&f&cp&x&3&d&8&2&f&co&x&4&3&8&8&f&cm&x&4&9&8&e&f&co&x&4&f&9&4&f&cc&x&5&5&9&a&f&cí &x&5&b&a&0&f&cp&x&6&0&a&5&f&cř&x&6&6&a&b&f&cí&x&6&c&b&1&f&ck&x&7&2&b&7&f&ca&x&7&8&b&d&f&cz&x&7&e&c&3&f&ců &x&8&4&c&9&f&d/&x&8&a&c&f&f&da&x&9&0&d&5&f&dn&x&9&5&d&b&f&do &x&9&b&e&1&f&da &x&a&1&e&7&f&d/&x&a&7&e&d&f&dn&x&a&d&f&3&f&de"));
            }
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                voting = false;
                if (votedYes.size() >= votedNo.size()){
                    for (Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7| &x&2&4&f&b&2&dH&x&2&8&f&b&3&3l&x&2&c&f&b&3&9a&x&3&0&f&a&3&fs&x&3&4&f&a&4&5o&x&3&8&f&a&4&bv&x&3&b&f&a&5&1á&x&3&f&f&9&5&7n&x&4&3&f&9&5&dí &x&4&7&f&9&6&2o &x&4&b&f&9&6&8z&x&4&f&f&8&6&em&x&5&3&f&8&7&4ě&x&5&7&f&8&7&an&x&5&b&f&8&8&0ě &x&5&f&f&8&8&6č&x&6&3&f&7&8&ca&x&6&7&f&7&9&2s&x&6&a&f&7&9&8u &x&6&e&f&7&9&en&x&7&2&f&6&a&4a &x&7&6&f&6&a&ad&x&7&a&f&6&b&0e&x&7&e&f&6&b&6n &x&8&2&f&6&b&cb&x&8&6&f&5&c&2y&x&8&a&f&5&c&8l&x&8&e&f&5&c&do &x&9&2&f&5&d&3ú&x&9&6&f&4&d&9s&x&9&9&f&4&d&fp&x&9&d&f&4&e&5ě&x&a&1&f&4&e&bš&x&a&5&f&3&f&1n&x&a&9&f&3&f&7é&x&a&d&f&3&f&d!"));
                    }
                    Objects.requireNonNull(Bukkit.getWorld("world")).setFullTime(1000);
                }
                else {
                    for (Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7| &x&f&b&1&d&1&dH&x&f&9&2&3&2&3l&x&f&7&2&9&2&9a&x&f&5&2&e&2&fs&x&f&3&3&4&3&5o&x&f&0&3&a&3&bv&x&e&e&4&0&4&1á&x&e&c&4&5&4&7n&x&e&a&4&b&4&dí &x&e&8&5&1&5&3o &x&e&6&5&7&5&az&x&e&4&5&d&6&0m&x&e&2&6&2&6&6ě&x&e&0&6&8&6&cn&x&d&d&6&e&7&2ě &x&d&b&7&4&7&8č&x&d&9&7&a&7&ea&x&d&7&7&f&8&4s&x&d&5&8&5&8&au &x&d&3&8&b&9&0n&x&d&1&9&1&9&6a &x&c&f&9&6&9&cd&x&c&d&9&c&a&2e&x&c&b&a&2&a&8n &x&c&8&a&8&a&en&x&c&6&a&e&b&4e&x&c&4&b&3&b&ab&x&c&2&b&9&c&0y&x&c&0&b&f&c&7l&x&b&e&c&5&c&do &x&b&c&c&b&d&3ú&x&b&a&d&0&d&9s&x&b&8&d&6&d&fp&x&b&5&d&c&e&5ě&x&b&3&e&2&e&bš&x&b&1&e&7&f&1n&x&a&f&e&d&f&7é&x&a&d&f&3&f&d!"));
                    }
                }
                votedYes.clear();
                votedNo.clear();
                bar.removeAll();
            }, 20*60);
        }

    }

    public static void changeToSunnyVoting(Plugin plugin, Player player){
        if (voting){
            ChatNotice.error(player, Component.text("V tuto chvíli již probíhá nějaké hlasování!"));
        }
        else {
            bar = Bukkit.createBossBar("Hlasování o změně počasí na slunečné | /ano, /ne", BarColor.GREEN, BarStyle.SOLID);
            bar.setVisible(true);
            bar.setProgress(1);
            Bukkit.getOnlinePlayers().forEach(bar::addPlayer);
            voting = true;
            for (Player players : Bukkit.getOnlinePlayers()){
                players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7| &x&0&8&4&c&f&bH&x&0&e&5&2&f&bL&x&1&3&5&7&f&bA&x&1&9&5&d&f&bS&x&1&e&6&2&f&bO&x&2&4&6&8&f&bV&x&2&9&6&d&f&bÁ&x&2&f&7&3&f&bN&x&3&4&7&9&f&cÍ &x&3&a&7&e&f&cO &x&3&f&8&4&f&cZ&x&4&5&8&9&f&cM&x&4&a&8&f&f&cĚ&x&5&0&9&4&f&cN&x&5&5&9&a&f&cĚ &x&5&b&a&0&f&cP&x&6&0&a&5&f&cO&x&6&6&a&b&f&cČ&x&6&b&b&0&f&cA&x&7&1&b&6&f&cS&x&7&6&b&b&f&cÍ &x&7&c&c&1&f&cN&x&8&1&c&6&f&cA &x&8&7&c&c&f&dS&x&8&c&d&2&f&dL&x&9&2&d&7&f&dU&x&9&7&d&d&f&dN&x&9&d&e&2&f&dE&x&a&2&e&8&f&dČ&x&a&8&e&d&f&dN&x&a&d&f&3&f&dÉ"));
                players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7| &x&0&8&4&c&f&bH&x&0&e&5&2&f&bl&x&1&4&5&8&f&ba&x&1&a&5&e&f&bs&x&2&0&6&4&f&bu&x&2&5&6&a&f&bj&x&2&b&7&0&f&bt&x&3&1&7&6&f&ce &x&3&7&7&c&f&cp&x&3&d&8&2&f&co&x&4&3&8&8&f&cm&x&4&9&8&e&f&co&x&4&f&9&4&f&cc&x&5&5&9&a&f&cí &x&5&b&a&0&f&cp&x&6&0&a&5&f&cř&x&6&6&a&b&f&cí&x&6&c&b&1&f&ck&x&7&2&b&7&f&ca&x&7&8&b&d&f&cz&x&7&e&c&3&f&ců &x&8&4&c&9&f&d/&x&8&a&c&f&f&da&x&9&0&d&5&f&dn&x&9&5&d&b&f&do &x&9&b&e&1&f&da &x&a&1&e&7&f&d/&x&a&7&e&d&f&dn&x&a&d&f&3&f&de"));
            }
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                voting = false;
                if (votedYes.size() >= votedNo.size()){
                    for (Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7| &x&2&4&f&b&2&dH&x&2&7&f&b&3&2l&x&2&b&f&b&3&7a&x&2&e&f&a&3&cs&x&3&1&f&a&4&1o&x&3&4&f&a&4&6v&x&3&8&f&a&4&bá&x&3&b&f&a&5&0n&x&3&e&f&9&5&5í &x&4&1&f&9&5&ao &x&4&5&f&9&5&fz&x&4&8&f&9&6&3m&x&4&b&f&9&6&8ě&x&4&e&f&9&6&dn&x&5&2&f&8&7&2ě &x&5&5&f&8&7&7p&x&5&8&f&8&7&co&x&5&b&f&8&8&1č&x&5&f&f&8&8&6a&x&6&2&f&7&8&bs&x&6&5&f&7&9&0í &x&6&9&f&7&9&5n&x&6&c&f&7&9&aa &x&6&f&f&7&9&fs&x&7&2&f&6&a&4l&x&7&6&f&6&a&9u&x&7&9&f&6&a&en&x&7&c&f&6&b&3e&x&7&f&f&6&b&8č&x&8&3&f&5&b&dn&x&8&6&f&5&c&2é &x&8&9&f&5&c&7b&x&8&c&f&5&c&by&x&9&0&f&5&d&0l&x&9&3&f&5&d&5o &x&9&6&f&4&d&aú&x&9&9&f&4&d&fs&x&9&d&f&4&e&4p&x&a&0&f&4&e&9ě&x&a&3&f&4&e&eš&x&a&6&f&3&f&3n&x&a&a&f&3&f&8é&x&a&d&f&3&f&d!"));
                    }
                    Objects.requireNonNull(Bukkit.getWorld("world")).setStorm(false);
                    Objects.requireNonNull(Bukkit.getWorld("world")).setThundering(false);
                }
                else {
                    for (Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7| &x&f&b&1&d&1&dH&x&f&9&2&2&2&2l&x&f&7&2&7&2&7a&x&f&6&2&c&2&cs&x&f&4&3&0&3&1o&x&f&2&3&5&3&6v&x&f&0&3&a&3&cá&x&e&f&3&f&4&1n&x&e&d&4&4&4&6í &x&e&b&4&9&4&bo &x&e&9&4&e&5&0z&x&e&8&5&3&5&5m&x&e&6&5&7&5&aě&x&e&4&5&c&5&fn&x&e&2&6&1&6&4ě &x&e&0&6&6&6&9p&x&d&f&6&b&6&eo&x&d&d&7&0&7&4č&x&d&b&7&5&7&9a&x&d&9&7&9&7&es&x&d&8&7&e&8&3í &x&d&6&8&3&8&8n&x&d&4&8&8&8&da &x&d&2&8&d&9&2s&x&d&0&9&2&9&7l&x&c&f&9&7&9&cu&x&c&d&9&b&a&1n&x&c&b&a&0&a&6e&x&c&9&a&5&a&cč&x&c&8&a&a&b&1n&x&c&6&a&f&b&6é &x&c&4&b&4&b&bn&x&c&2&b&9&c&0e&x&c&1&b&e&c&5b&x&b&f&c&2&c&ay&x&b&d&c&7&c&fl&x&b&b&c&c&d&4o &x&b&9&d&1&d&9ú&x&b&8&d&6&d&es&x&b&6&d&b&e&4p&x&b&4&e&0&e&9ě&x&b&2&e&4&e&eš&x&b&1&e&9&f&3n&x&a&f&e&e&f&8é&x&a&d&f&3&f&d!"));
                    }
                }
                votedYes.clear();
                votedNo.clear();
                bar.removeAll();
            }, 20*60);
        }

    }
}

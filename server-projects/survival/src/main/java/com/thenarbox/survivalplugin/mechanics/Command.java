package com.thenarbox.survivalplugin.mechanics;

import com.thenarbox.api.ChatNotice;
import com.thenarbox.survivalplugin.services.Kits;
import com.thenarbox.survivalplugin.services.Menus;
import com.thenarbox.survivalplugin.services.RandomTeleport;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;


public class Command implements Listener {

    public static HashMap<String, Long> cooldowns = new HashMap<>();
    public static HashMap<String, Long> cooldowns1 = new HashMap<>();
    public static HashMap<String, Long> cooldowns2 = new HashMap<>();
    final static int cooldownTime = 60;

    final static int cooldownDuration = 3600;

    public static void commands(){

        {
            Bukkit.getCommandMap().register("survival", new org.bukkit.command.Command("ec") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;
                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("ec")){
                        if (player.hasPermission("survival.ec")){
                            player.openInventory(player.getEnderChest());
                        } else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je VIP."));
                        }
                    }
                    return false;
                }
            });

        }

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
            Bukkit.getCommandMap().register("survival", new org.bukkit.command.Command("kit") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;
                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("kit")){
                        if (args.length == 0){
                            ChatNotice.info(player, Component.text("Použití: /kit <název>"));
                            if (player.hasPermission("survival.kits.vip")){
                                if(cooldowns1.containsKey(player.getName()) && cooldowns2.containsKey(player.getName()) ) {
                                    ChatNotice.info(player, Component.text("Dostupné kity: " + ChatColor.STRIKETHROUGH + "default" + ChatColor.WHITE + ", " + ChatColor.STRIKETHROUGH + "vip"));
                                }
                                else if (cooldowns1.containsKey(player.getName())){
                                    ChatNotice.info(player, Component.text("Dostupné kity: " + ChatColor.STRIKETHROUGH + "default" + ChatColor.WHITE + ", vip"));
                                }
                                else if (cooldowns2.containsKey(player.getName())){
                                    ChatNotice.info(player, Component.text("Dostupné kity: default, " + ChatColor.STRIKETHROUGH + "vip"));
                                }
                                else {
                                    ChatNotice.info(player, Component.text("Dostupné kity: default, vip"));
                                }
                            } else {
                                if (cooldowns1.containsKey(player.getName())){
                                    ChatNotice.info(player, Component.text("Dostupné kity: "+ChatColor.STRIKETHROUGH+"default"));
                                }
                                else {
                                    ChatNotice.info(player, Component.text("Dostupné kity: default"));
                                }
                            }
                        }
                        else if (args.length == 1){
                            if (args[0].equalsIgnoreCase("default")){
                                if (cooldowns1.containsKey(player.getName())){
                                    long secondsLeft = ((cooldowns1.get(player.getName()) / 1000) + cooldownDuration) - (System.currentTimeMillis() / 1000);
                                    if (secondsLeft > 0){
                                        ChatNotice.error(player, Component.text("Musíš počkat " + secondsLeft/60 + " minut, než budeš moci použít tento kit znovu!"));
                                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                                        return false;
                                    }
                                }
                                cooldowns1.put(player.getName(), System.currentTimeMillis());
                                Kits.defaultKit(player);
                                ChatNotice.success(player, Component.text("Kit " + args[0] + " byl úspěšně použit!"));
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                                return false;
                            }
                            if (args[0].equalsIgnoreCase("vip")){
                                if (player.hasPermission("survival.kits.vip")){
                                    if (cooldowns2.containsKey(player.getName())){
                                        long secondsLeft = ((cooldowns2.get(player.getName()) / 1000) + cooldownDuration) - (System.currentTimeMillis() / 1000);
                                        if (secondsLeft > 0){
                                            ChatNotice.error(player, Component.text("Musíš počkat " + secondsLeft/60 + " minut, než budeš moci použít tento kit znovu!"));
                                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                                            return false;
                                        }
                                    }
                                    cooldowns2.put(player.getName(), System.currentTimeMillis());
                                    Kits.vipKit(player);
                                    ChatNotice.success(player, Component.text("Kit " + args[0] + " byl úspěšně použit!"));
                                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                                    return false;
                                } else {
                                    ChatNotice.error(player, Component.text("Nemáš dostatečná oprávnění pro použití tohoto kitu!"));
                                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                                }
                            }
                            else {
                                ChatNotice.error(player, Component.text("Kit " + args[0] + " neexistuje!"));
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                            }
                        }
                        else {
                            ChatNotice.info(player, Component.text("Použití: /kit <název>"));
                            if (player.hasPermission("survival.kits.vip")){
                                ChatNotice.info(player, Component.text("Dostupné kity: default, vip"));
                            } else {
                                ChatNotice.info(player, Component.text("Dostupné kity: default"));
                            }
                        }
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
            Bukkit.getCommandMap().register("survival", new org.bukkit.command.Command("odmeny") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("odmeny")){
                        player.performCommand("rewards");
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

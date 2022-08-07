package com.thenarbox.proxysystem.listeners;

import com.thenarbox.api.ChatNotice;
import com.thenarbox.proxysystem.ProxySystem;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

public class CommandMechanic implements Listener {
    public CommandMechanic() {
    }


    public static void Commands(){
        {

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("discord") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    TextComponent mainComponent = new TextComponent( "Náš discord: " );
                    TextComponent subComponent = new TextComponent( "discord.mejs.cz" );
                    subComponent.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Klikni pro otevření" ).create() ) );
                    subComponent.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "https://discord.mejs.cz" ) );
                    mainComponent.addExtra( subComponent );
                    ChatNotice.info(player, mainComponent);
                }
            });
            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("tc") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("proxyserver.staffchats.tc")){
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Builder."));
                        return;
                    }
                    if (args.length == 0){
                        ChatNotice.error(player, Component.text("Syntaxe příkazu: /tc <zpráva>"));
                        return;
                    }

                    for (ProxiedPlayer player1 : ProxyServer.getInstance().getPlayers()){
                        if (!player1.hasPermission("proxyserver.staffchats.tc")){
                            continue;
                        }
                        if (player1 == null)
                            continue;
                        player1.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&lTeamChat: ") + ChatColor.WHITE + player.getName() + ": " + ChatColor.RESET + String.join(" ", args));
                    }
                }
            });

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("ac") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("proxyserver.staffchats.ac")){
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Developer."));
                        return;
                    }
                    if (args.length == 0){
                        ChatNotice.error(player, Component.text("Syntaxe příkazu: /ac <zpráva>"));
                        return;
                    }

                    for (ProxiedPlayer player1 : ProxyServer.getInstance().getPlayers()){
                        if (!player1.hasPermission("proxyserver.staffchats.ac")){
                            continue;
                        }
                        if (player1 == null)
                            continue;
                        player1.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lAdminChat: ") + ChatColor.WHITE + player.getName() + ": " + ChatColor.RESET + String.join(" ", args));
                    }
                }
            });

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("build") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("proxyserver.build.join")){
                        ChatNotice.error(player, Component.text("Minimální hodnost pro připojení na tento server je Builder."));
                        return;
                    }
                    if(!player.getServer().getInfo().getMotd().equals("Build")){
                        ChatNotice.success(player, Component.text("Připojuješ se na BuildServer."));
                        player.connect(ProxyServer.getInstance().getServerInfo("Build"));
                        return;
                    }
                    ChatNotice.error(player, Component.text("Jsi již na serveru pro stavění."));
                }
            });
        }
        {
            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("lobby") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if(!player.getServer().getInfo().getMotd().equals("Lobby-1")){
                        ChatNotice.success(player, Component.text("Připojuješ se na Lobby."));
                        player.connect(ProxyServer.getInstance().getServerInfo("Lobby-1"));
                        return;
                    }
                    ChatNotice.error(player, Component.text("Jsi již na Lobby."));
                }
            });
        }
        {
            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("hub") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if(!player.getServer().getInfo().getMotd().equals("Lobby-1")){
                        ChatNotice.success(player, Component.text("Připojuješ se na Lobby."));
                        player.connect(ProxyServer.getInstance().getServerInfo("Lobby-1"));
                        return;
                    }
                    ChatNotice.error(player, Component.text("Jsi již na Lobby."));
                }
            });
        }
    }
}

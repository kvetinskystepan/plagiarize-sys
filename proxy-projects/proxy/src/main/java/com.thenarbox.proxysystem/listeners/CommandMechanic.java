package com.thenarbox.proxysystem.listeners;

import com.thenarbox.api.ChatNotice;
import com.thenarbox.proxysystem.ProxySystem;
import me.clip.placeholderapi.util.TimeFormat;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class CommandMechanic implements Listener {
    public CommandMechanic() {
    }


    public static void Commands(){
        {

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("serverlist") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("proxyserver.serverlist")) {
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Developer."));
                        return;
                    }
                    String message = "";
                    Map<String, ServerInfo> allServer = ProxyServer.getInstance().getServers();
                    for (ServerInfo info : allServer.values()){
                        if (info.canAccess(player))
                            message += info.getName() + ", ";
                    }
                    ChatNotice.info(player, new TextComponent(message));
                }
            });

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("ranklist") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("proxyserver.ranklist")) {
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Vedení."));
                        return;
                    }
                    ChatNotice.infoComponent(player, Component.text("Skupiny: majitel, vedení, v.developer, developer, v.helper, helper, v.builder, builder, eventer, default"));
                }
            });

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("settemprank") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    if (!(sender instanceof ProxiedPlayer))
                        return;

                    ProxiedPlayer player = (ProxiedPlayer) sender;

                    if (!player.hasPermission("proxyserver.settemprank")) {
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Vedení."));
                        return;
                    };
                    if (args.length != 3) {
                        ChatNotice.error(player, Component.text("Použití: /settemprank <jméno> <doba ve dnech> <skupina>"));
                        return;
                    }
                    String name = args[0];
                    int time;
                    String group = args[2];
                    try {
                        time = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        ChatNotice.error(player, Component.text("Doba musí být číslo ve dnech."));
                        return;
                    }
                    if (!group.equalsIgnoreCase("majitel") && !group.equalsIgnoreCase("vedení") && !group.equalsIgnoreCase("v.developer") && !group.equalsIgnoreCase("developer") && !group.equalsIgnoreCase("v.helper") && !group.equalsIgnoreCase("helper") && !group.equalsIgnoreCase("v.builder") && !group.equalsIgnoreCase("builder") && !group.equalsIgnoreCase("eventer") && !group.equalsIgnoreCase("default")) {
                        ChatNotice.error(player, Component.text("Neplatná skupina. Zkus nahlédnout do /ranklist pro více informací."));
                    }
                    else {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + name + " clear");
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + name + " parent addtemp " + group + " " + time + "d");
                        ChatNotice.success(player, Component.text("Uživatel " + name + " byl úspěšně přidán do skupiny " + group + " v délce trvání: " + time + " dnů."));
                    }
                }
            });

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("setrank") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("proxyserver.setrank")) {
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Vedení."));
                        return;
                    };
                    if (args.length == 0) {
                        ChatNotice.error(player, Component.text("Použití: /setrank <jméno> <skupina>"));
                        return;
                    }
                    if (args.length == 1) {
                        ChatNotice.error(player, Component.text("Použití: /setrank <jméno> <skupina>"));
                        return;
                    }
                    String name = args[0];
                    String group = args[1];
                    if (!group.equalsIgnoreCase("majitel") && !group.equalsIgnoreCase("vedení") && !group.equalsIgnoreCase("v.developer") && !group.equalsIgnoreCase("developer") && !group.equalsIgnoreCase("v.helper") && !group.equalsIgnoreCase("helper") && !group.equalsIgnoreCase("v.builder") && !group.equalsIgnoreCase("builder") && !group.equalsIgnoreCase("eventer") && !group.equalsIgnoreCase("default")) {
                        ChatNotice.error(player, Component.text("Neplatná skupina. Zkus nahlédnout do /ranklist pro více informací."));
                    }
                    else {

                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + name + " clear");
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + name + " parent add " + group);
                        ChatNotice.success(player, Component.text("Uživatel " + name + " byl úspěšně přidán do skupiny " + group));
                    }
                }
            });

           /* ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("server") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("proxyserver.server")) {
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Developer."));
                        return;
                    }
                    if (args.length == 0) {
                        ChatNotice.error(player, Component.text("Použití: /server <server>"));
                        return;
                    }
                    ServerInfo info = ProxyServer.getInstance().getServerInfo(args[0]);
                    if (info == null) {
                        ChatNotice.error(player, Component.text("Server nebyl nalezen."));
                        return;
                    }
                    if (!info.canAccess(player)) {
                        ChatNotice.error(player, Component.text("Nemáš přístup k tomuto serveru."));
                        return;
                    }
                    player.connect(info);
                    ChatNotice.success(player, Component.text("Připojuji se k serveru " + info.getName() + "."));
                }
            });*/


            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("discord") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    TextComponent mainComponent = new TextComponent( "Náš discord (klikni pro otevření): " );
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
                        try {
                            player.connect(ProxyServer.getInstance().getServerInfo("Lobby-1"));
                            ChatNotice.success(player, Component.text("Připojuješ se na Lobby."));
                        }
                        catch (Exception e){
                            ChatNotice.error(player, Component.text("Server Lobby-1 není dostupný."));
                        }

                    }
                    else {
                        ChatNotice.error(player, Component.text("Jsi již na Lobby."));

                    }
                }
            });
        }

        {
            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("hub") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if(!player.getServer().getInfo().getMotd().equals("Lobby-1")){
                        try {
                            player.connect(ProxyServer.getInstance().getServerInfo("Lobby-1"));
                            ChatNotice.success(player, Component.text("Připojuješ se na Lobby."));
                        }
                        catch (Exception e){
                            ChatNotice.error(player, Component.text("Server Lobby-1 není dostupný."));
                        }

                    }
                    else {
                        ChatNotice.error(player, Component.text("Jsi již na Lobby."));

                    }
                }
            });
        }
    }
}

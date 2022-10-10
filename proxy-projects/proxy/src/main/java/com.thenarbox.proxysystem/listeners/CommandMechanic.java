package com.thenarbox.proxysystem.listeners;

import com.google.common.io.ByteStreams;
import com.thenarbox.api.ChatNotice;
import com.thenarbox.proxysystem.ProxySystem;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

import java.io.Console;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CommandMechanic implements Listener {
    public CommandMechanic() {
    }


    public static void Commands(){
        {

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("block") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("punishments.block")) {
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Helper."));
                        return;
                    }
                    if (args.length != 1) {
                        ChatNotice.error(player, Component.text("Použití: /block <hráč>"));
                        return;
                    }

                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

                    if (target == null) {
                        ChatNotice.error(player, Component.text("Hráč nenalezen."));
                        return;
                    }

                    var stream = ByteStreams.newDataOutput();
                    stream.writeUTF("SYS");
                    stream.writeUTF("points reset " + target.getName());
                    target.getServer().sendData("BungeeCord", stream.toByteArray());

                    target.connect(ProxyServer.getInstance().getServerInfo("Lobby-1"));
                    ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + target.getName() + " clear");
                    ChatNotice.warning(target, Component.text("Byl jsi odpojen. Tvůj účet byl pozastaven."));
                    ChatNotice.success(player, Component.text("Účet hráče " + target.getName() + " byl pozastaven."));

                    ProxyServer.getInstance().getScheduler().schedule(ProxySystem.getStaticInstance(), () -> {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "ban " + target.getName() + " Block");
                    }, 6, TimeUnit.SECONDS).getId();

                }
            });

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("pblock") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("punishments.pblock")) {
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Helper."));
                        return;
                    }
                    if (args.length != 1) {
                        ChatNotice.error(player, Component.text("Použití: /pblock <hráč>"));
                        ChatNotice.warning(player, Component.text("Poznámka: Tento příkaz je pouze pro případy, kdy se jedná o závažné porušení pravidel."));
                        ChatNotice.warning(player, Component.text("Poznámka: Tuto akci nelze vrátit!"));
                        return;
                    }

                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                    if (target == null) {
                        ChatNotice.error(player, Component.text("Hráč nenalezen."));
                        return;
                    }

                    var stream = ByteStreams.newDataOutput();
                    stream.writeUTF("SYS");
                    stream.writeUTF("points reset " + target.getName());
                    target.getServer().sendData("BungeeCord", stream.toByteArray());

                    target.connect(ProxyServer.getInstance().getServerInfo("Lobby-1"));
                    ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + target.getName() + " clear");
                    ChatNotice.success(player, Component.text("Účet hráče " + target.getName() + " byl permanentně zabanován."));
                    ChatNotice.warning(target, Component.text("Byl jsi odpojen. Tvůj účet byl permanentně zabanován."));

                    ProxyServer.getInstance().getScheduler().schedule(ProxySystem.getStaticInstance(), () -> {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "ban " + target.getName() + " Permanent");
                    }, 6, TimeUnit.SECONDS).getId();
                }
            });


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
                    for (ServerInfo info : allServer.values()) {
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
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Builder."));
                        return;
                    }
                    ChatNotice.infoComponent(player, Component.text("Skupiny: majitel, vedení, v.developer, developer, v.helper, helper, v.builder, builder, eventer, default"));
                }
            });

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("setrank") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("proxyserver.setrank")) {
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Builder."));
                        return;
                    }
                    ;
                    if (args.length < 2) {
                        ChatNotice.error(player, Component.text("Použití: /setrank <jméno> <skupina> | <doba ve dnech>"));
                        return;
                    }
                    if (args.length == 2) {
                        String name = args[0];
                        String group = args[1];
                        if (!group.equalsIgnoreCase("majitel") && !group.equalsIgnoreCase("vedení") && !group.equalsIgnoreCase("v.developer") && !group.equalsIgnoreCase("developer") && !group.equalsIgnoreCase("v.helper") && !group.equalsIgnoreCase("helper") && !group.equalsIgnoreCase("v.builder") && !group.equalsIgnoreCase("builder") && !group.equalsIgnoreCase("eventer") && !group.equalsIgnoreCase("default")) {
                            ChatNotice.error(player, Component.text("Neplatná skupina. Zkus nahlédnout do /ranklist pro více informací."));
                        } else {

                            ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + name + " clear");
                            ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + name + " parent add " + group);
                            ChatNotice.success(player, Component.text("Uživatel " + name + " byl úspěšně přidán do skupiny " + group));
                        }
                    } else if (args.length == 3) {
                        String name = args[0];
                        int time;
                        String group = args[1];
                        try {
                            time = Integer.parseInt(args[2]);
                        } catch (NumberFormatException e) {
                            ChatNotice.error(player, Component.text("Doba musí být číslo ve dnech."));
                            return;
                        }
                        if (!group.equalsIgnoreCase("majitel") && !group.equalsIgnoreCase("vedení") && !group.equalsIgnoreCase("v.developer") && !group.equalsIgnoreCase("developer") && !group.equalsIgnoreCase("v.helper") && !group.equalsIgnoreCase("helper") && !group.equalsIgnoreCase("v.builder") && !group.equalsIgnoreCase("builder") && !group.equalsIgnoreCase("eventer") && !group.equalsIgnoreCase("default")) {
                            ChatNotice.error(player, Component.text("Neplatná skupina. Zkus nahlédnout do /ranklist pro více informací."));
                        } else {
                            ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + name + " clear");
                            ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + name + " parent addtemp " + group + " " + time + "d");
                            ChatNotice.success(player, Component.text("Uživatel " + name + " byl úspěšně přidán do skupiny " + group + " v délce trvání: " + time + " dnů."));
                        }
                    } else if (args.length > 3) {
                        ChatNotice.error(player, Component.text("Použití: /setrank <jméno> <skupina> | <doba ve dnech>"));
                    }

                }
            });

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("send") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("proxyserver.send")) {
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Builder."));
                        return;
                    }
                    if (args.length < 2) {
                        ChatNotice.error(player, Component.text("Použití: /send <jméno> <server>"));
                        return;
                    }
                    String name = args[0];
                    String server = args[1];
                    if (ProxyServer.getInstance().getPlayer(name) == null) {
                        ChatNotice.error(player, Component.text("Tento hráč není online."));
                        return;
                    }
                    if (ProxyServer.getInstance().getServerInfo(server) == null) {
                        ChatNotice.error(player, Component.text("Tento server neexistuje."));
                        return;
                    }
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(name);
                    ServerInfo targetServer = ProxyServer.getInstance().getServerInfo(server);
                    target.connect(targetServer);
                    ChatNotice.success(player, Component.text("Hráč " + name + " byl úspěšně přesunut na server " + server));
                }
            });

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("find") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("proxyserver.find")) {
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Helper."));
                        return;
                    }
                    if (args.length == 0) {
                        ChatNotice.error(player, Component.text("Použití: /find <jméno>"));
                        return;
                    }
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                    if (target == null) {
                        ChatNotice.error(player, Component.text("Hráč nebyl nalezen."));
                        return;
                    }
                    ChatNotice.success(player, Component.text("Hráč " + target.getName() + " je na serveru " + target.getServer().getInfo().getName() + "."));
                }
            });

            ProxyServer.getInstance().getPluginManager().registerCommand(ProxySystem.getStaticInstance(), new Command("server") {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    if (!player.hasPermission("proxyserver.server")) {
                        ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Developer."));
                        return;
                    }
                    if (args.length != 1) {
                        ChatNotice.error(player, Component.text("Použití: /server <server>"));
                        return;
                    }
                    ServerInfo info = ProxyServer.getInstance().getServerInfo(args[0]);
                    if (info == null) {
                        ChatNotice.error(player, Component.text("Server nebyl nalezen. Dostupné servery nalezneš v /serverlist"));
                        return;
                    }
                    if (!info.canAccess(player)) {
                        ChatNotice.error(player, Component.text("Nemáš přístup k tomuto serveru."));
                        return;
                    }
                    player.connect(info);
                    ChatNotice.success(player, Component.text("Připojuji tě k serveru " + info.getName() + "."));
                }
            });


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

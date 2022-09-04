package com.thenarbox.authplugin;

import com.thenarbox.api.ChatNotice;
import com.thenarbox.api.Standards;
import lombok.extern.log4j.Log4j2;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

@Log4j2(topic = "AuthPlugin")
public final class AuthPlugin extends JavaPlugin implements Listener {

    ArrayList<String> allowedCommands01 = new ArrayList<String>();
    ArrayList<String> whitelist = new ArrayList<String>();

    @Override
    public void onEnable() {
        log.info("AuthPlugin is enabled!");

        whitelist.add("TheNarbox");
        whitelist.add("MistrMisak");
        whitelist.add("laifaner");
        whitelist.add("Angelsword3");
        whitelist.add("Tonda");
        whitelist.add("Pan_bartCZ");
        whitelist.add("Skymmel");
        whitelist.add("ItzCowinka_");
        whitelist.add("Pan_Okurka");
        whitelist.add("Mata08");
        whitelist.add("Medveduus");
        whitelist.add("gugi736");
        whitelist.add("_Jenek_");
        whitelist.add("King_Stanley");
        whitelist.add("Mr_CrazyHD_");
        whitelist.add("Vaclos");
        {
            Standards.worlds();
            Standards.commands(this);
        }

        allowedCommands01.add("login");
        allowedCommands01.add("register");
        allowedCommands01.add("l");
        allowedCommands01.add("reg");
        allowedCommands01.add("premium");
        allowedCommands01.add("cracked");
        getServer().getPluginManager()
                .registerEvents(this, this);
        getServer().setDefaultGameMode(GameMode.ADVENTURE);
    }
    @Override
    public void onDisable() {
        log.info("AuthPlugin is disabled!");
        HandlerList.unregisterAll();
    }

    @EventHandler
    public void command(PlayerCommandPreprocessEvent e){
        Player player = e.getPlayer();
        final var commandMessage = e.getMessage();
        final int length;
        {
            final int index = commandMessage.indexOf(' ');
            length = index == -1 ? commandMessage.length() : index;
        }
        final var commandName = commandMessage.substring(1, length);
        if(!allowedCommands01.contains(commandName)){
            e.setCancelled(true);
            ChatNotice.error(player, Component.text("Na provedení tohoto příkazu nemáš opravnění."));
        }
    }

    @EventHandler
    public void onPlayer(PlayerCommandSendEvent e){
        final var allowedCommands = allowedCommands01;
        final var sentCommands = e.getCommands();
        sentCommands.retainAll(allowedCommands);
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void VineGrow(BlockSpreadEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void noFood(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void LeaveDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPick(PlayerPickupItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void noPvP(EntityDamageByEntityEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (e.getPlayer().getName().startsWith("MCSTORM")){
            e.getPlayer().kickPlayer("Connect to our server by your real nickname and don't use bots or other software to join us.");
            log.error("Player " + e.getPlayer().getName() + " was kicked for using bots or other software to join us.");
            return;
        }
        Player player = e.getPlayer();
        if (!whitelist.contains(player.getName())){
            player.kickPlayer(ChatColor.RED + "Přístup na server byl zamítnut \n \n" + ChatColor.WHITE + "Herní jméno: " + ChatColor.GOLD + player.getName() + "\n" + ChatColor.WHITE + "UUID: " + ChatColor.GOLD + player.getUniqueId() + "\n" + ChatColor.WHITE + "IP: " + ChatColor.GOLD + player.getAddress().getAddress().getHostAddress());
            log.error("Hráč " + player.getName() + " byl vyhozen protože není na seznamu povolených hráčů");
            return;
        }
        player.hidePlayer(player);
        Bukkit.getOnlinePlayers().forEach(online -> {
            player.hidePlayer(online);
            online.hidePlayer(player);
        });
        player.teleport(new Location(Bukkit.getWorld("world"), -48.5, 65.5, 34.5, -50, 0));
        player.sendTitle(ChatColor.translateAlternateColorCodes('§', "§x§1§7§b§3§c§8§lM§x§1§9§c§0§c§4§lE§x§1§c§c§c§c§0§lJ§x§1§e§d§9§b§c§lS§x§2§0§e§6§b§8§l.§x§2§3§f§2§b§4§lC§x§2§5§f§f§b§0§lZ"), ChatColor.WHITE + "Prosím přihlaš se nebo zaregistruj", 10, 70, 20);
        player.setGameMode(getServer().getDefaultGameMode());
        player.setMaxHealth(20);
        player.setFoodLevel(20);
        e.setJoinMessage(null);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void Quit(PlayerQuitEvent e){
        e.getPlayer().clearTitle();
        e.setQuitMessage(null);
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        e.setCancelled(true);
    }

}

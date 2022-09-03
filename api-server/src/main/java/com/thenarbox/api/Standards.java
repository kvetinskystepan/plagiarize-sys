package com.thenarbox.api;

import com.thenarbox.api.colors.ColorAPI;
import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static org.bukkit.Bukkit.getServer;

@Log4j2(topic = "Standards")
public class Standards {

    public static HashMap<String, Long> cooldownRepair = new HashMap<>();
    public static HashMap<String, Long> cooldownHealth = new HashMap<>();
    static int cooldownHeal = 600;
    static int cooldownRepairTime = 600;
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
                        team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix) + " " + ChatColor.WHITE);
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

    public static void commands(Plugin plugin) {

        getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");

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
                        ChatNotice.info(player, Component.text("Tvá úroveň je: " + ChatColor.AQUA + level));
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
                                            ChatNotice.success(player, Component.text("Létání hráči "+ChatColor.AQUA+toPlayer.getName()+ChatColor.WHITE+" bylo vypnuto."));
                                        }
                                        else {
                                            toPlayer.setAllowFlight(true);
                                            toPlayer.setFlying(true);
                                            ChatNotice.success(toPlayer, Component.text("Létání bylo zapnuto."));
                                            ChatNotice.success(player, Component.text("Létání hráči "+ChatColor.AQUA+toPlayer.getName()+ChatColor.WHITE+" bylo zapnuto."));
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

    private static HashMap<UUID, UUID> requests = new HashMap<>();
    public static void survivalCommands(Plugin plugin){

        {
            Bukkit.getCommandMap().register("survival", new Command("prace") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }
                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("prace")) {
                        player.performCommand("jobs join");
                    }
                    return false;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("anvil") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }
                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("anvil")) {
                        if (player.hasPermission("survival.anvil")){
                            player.openAnvil(player.getLocation(), true);
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                            ChatNotice.success(player, Component.text("Otevřel si přenosnou kovadlinu."));
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je VIP."));
                        }
                    }
                    return false;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("wb") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }
                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("wb")) {
                        if (player.hasPermission("survival.wb")){
                            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
                            player.openWorkbench(player.getLocation(), true);
                            ChatNotice.success(player, Component.text("Otevřel si přenosný pracovní stůl."));
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je VIP."));
                        }
                    }
                    return false;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("obchod") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }
                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("obchod")) {
                        Location shop = new Location(Bukkit.getWorld("Spawn"), -168.5, 51, 21.5, 125, 0);
                        player.teleport(shop);
                        ChatNotice.success(player, Component.text("Byl jsi teleportován do obchodu."));
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                        player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 1);
                    }
                    return false;
                }
            });


        }

        {
            Bukkit.getCommandMap().register("survival", new Command("shop") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }
                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("shop")) {
                        Location shop = new Location(Bukkit.getWorld("Spawn"), -168.5, 51, 21.5, 125, 0);
                        player.teleport(shop);
                        ChatNotice.success(player, Component.text("Byl jsi teleportován do obchodu."));
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                        player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 1);
                    }
                    return false;
                }
            });


        }

        {
            Bukkit.getCommandMap().register("survival", new Command("pay") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }
                    final Player player = (Player) sender;
                    if (commandLabel.equalsIgnoreCase("pay")) {
                        if (args.length == 2){
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target != null){
                                if (Integer.parseInt(args[1]) > 0){
                                    String playerMoney = PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance%");
                                    if (Integer.parseInt(playerMoney) >= Integer.parseInt(args[1])){
                                        getServer().dispatchCommand(getServer().getConsoleSender(), "money remove " + player.getName() + " " + args[1]);
                                        getServer().dispatchCommand(getServer().getConsoleSender(), "money add " + target.getName() + " " + args[1]);
                                        ChatNotice.success(player, Component.text("Odeslal jsi " + args[1] + " coinů hráči " + target.getName() + "."));
                                        ChatNotice.info(target, Component.text("Obdržel jsi " + args[1] + " coinů od hráče " + player.getName() + "."));
                                    }
                                    else {
                                        ChatNotice.error(player, Component.text("Nemáš dostatek coinů na účtu."));
                                    }
                                }
                                else {
                                    ChatNotice.error(player, Component.text("Musíš zadat číslo větší než 0."));
                                }
                            }
                            else {
                                ChatNotice.error(player, Component.text(ChatColor.WHITE + "Hráč " + args[0] + " není online."));
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Použití: /pay <hráč> <částka>"));
                        }
                    }
                    return false;
                }
            });


        }

        {
            Bukkit.getCommandMap().register("survival", new Command("clear") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("clear")) {
                        if (player.hasPermission("survival.clear")){
                            player.clearActiveItem();
                            player.getInventory().clear();
                            player.getInventory().setArmorContents(null);
                            ChatNotice.success(player, Component.text("Všechny předměty byly odebrány z tvého inventáře."));
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je V.Developer."));
                        }
                    }

                    return false;
                }
            });


        }

        {
            Bukkit.getCommandMap().register("survival", new Command("penize") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("penize")) {
                        String money = PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance%");
                        ChatNotice.info(player, Component.text("Tvůj zůstatek je: " + ColorAPI.process("<GRADIENT:34eb92>"+money+"</GRADIENT:34eb92>") + " Ⓒ"));
                    }

                    return false;
                }
            });


        }

        {
            Bukkit.getCommandMap().register("survival", new Command("coins") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("coins")) {
                        if (args.length == 0){
                            String money = PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance%");
                            ChatNotice.info(player, Component.text("Tvůj zůstatek je: " + ColorAPI.process("<GRADIENT:34eb92>"+money+"</GRADIENT:34eb92>") + " Ⓒ"));
                        }
                        else if (args.length == 3){
                            if (args[0].equals("convert")){
                                Player target = Bukkit.getPlayer(args[1]);
                                if (target != null){
                                    if (target != player){
                                        if (Float.parseFloat(args[2]) > 0){
                                            String money = PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance%");
                                            if (Float.parseFloat(money) >= Float.parseFloat(args[2])){
                                                getServer().dispatchCommand(getServer().getConsoleSender(), "money take " + player.getName() + " " + args[2]);
                                                getServer().dispatchCommand(getServer().getConsoleSender(), "money give " + target.getName() + " " + args[2]);
                                                ChatNotice.success(player, Component.text("Odeslal jsi " + args[2] + " coinů hráči " + target.getName() + "."));
                                                ChatNotice.info(target, Component.text("Obdržel jsi " + args[2] + " coinů od hráče " + player.getName() + "."));
                                            }
                                            else {
                                                ChatNotice.error(player, Component.text("Nemáš dostatek coinů na účtu."));
                                            }
                                        }
                                        else {
                                            ChatNotice.error(player, Component.text("Musíš zadat platnou částku k odeslání."));
                                        }
                                    }
                                    else {
                                        ChatNotice.error(player, Component.text("Nemůžeš poslat peníze sám sobě."));
                                    }
                                }
                                else {
                                    ChatNotice.error(player, Component.text(ChatColor.WHITE + "Hráč " + args[1] + " není online."));
                                }
                            }
                            else {
                                ChatNotice.error(player, Component.text("Použití: /coins convert <hráč> <částka>"));
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Použití: /coins convert <hráč> <částka>"));
                        }

                    }

                    return false;
                }
            });


        }

        {
            Bukkit.getCommandMap().register("survival", new Command("invsee") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof final Player player))
                        return true;

                    if (commandLabel.equalsIgnoreCase("invsee")) {
                        if (player.hasPermission("survival.invsee")){

                            Player target = Bukkit.getPlayer(args[0]);

                            if (args.length == 0){
                                ChatNotice.error(player, Component.text("Použití: /invsee <hráč>"));
                            }
                            if (target != null) {
                                player.openInventory(target.getInventory());
                            }
                            else {
                                ChatNotice.error(player, Component.text("Hráč s tímto nickem nebyl nalezen."));
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Helper."));
                        }
                    }
                    return false;
                }
            });
        }

        plugin.saveDefaultConfig();
        {
            Bukkit.getCommandMap().register("survival", new Command("sethome") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof final Player player))
                        return true;

                    if (commandLabel.equalsIgnoreCase("sethome")){
                        if (args.length == 0){
                            ChatNotice.error(player, Component.text("Použití: /sethome <název>"));
                        }
                        else if (player.getWorld().getName().equalsIgnoreCase("Spawn")){
                            ChatNotice.error(player, Component.text("Nemůžeš mít domov v tomto světě."));
                        }
                        else if (plugin.getConfig().get("homes." + player.getUniqueId() + "." + args[0]) != null){
                            ChatNotice.error(player, Component.text("Tento název domova již existuje."));
                        }
                        else {
                            plugin.getConfig().set("homes." + player.getUniqueId() + "." + args[0], player.getLocation());
                            plugin.saveConfig();
                            ChatNotice.success(player, Component.text("Domov s názvem "+args[0]+" byl úspěšně vytvořen."));
                        }
                    }
                    return false;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("back") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof final Player player))
                        return true;
                    if (commandLabel.equalsIgnoreCase("back")){
                        if (player.hasPermission("survival.back")){
                                if (player.getMetadata("previouslocation").size() == 0){
                                    ChatNotice.error(player, Component.text("Nemáš žádnou předchozí lokaci."));
                                }
                                else {
                                    Location loc = (Location) player.getMetadata("previouslocation").get(0).value();
                                    player.teleport(loc);
                                    player.removeMetadata("previouslocation", plugin);
                                    ChatNotice.success(player, Component.text("Přesunul jsi se na předchozí lokaci."));
                                }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je Helper."));
                        }
                    }
                    return false;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("delhome") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof final Player player))
                        return true;

                    if (commandLabel.equalsIgnoreCase("delhome")){
                        if (args.length == 0){
                            ChatNotice.error(player, Component.text("Použití: /delhome <název>"));
                            player.performCommand("homes");
                        }
                        else {
                            plugin.getConfig().set("homes." + player.getUniqueId() + "." + args[0], null);
                            plugin.saveConfig();
                            ChatNotice.success(player, Component.text("Domov s názvem "+args[0]+" byl úspěšně smazán."));
                        }
                    }
                    return false;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("home") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof final Player player))
                        return true;

                    if (commandLabel.equalsIgnoreCase("home")){
                        if (args.length == 0){
                            ChatNotice.error(player, Component.text("Použití: /home <název>"));
                            player.performCommand("homes");
                        }
                        else {
                            if (plugin.getConfig().contains("homes." + player.getUniqueId() + "." + args[0])){
                                player.teleport(Objects.requireNonNull(plugin.getConfig().getLocation("homes." + player.getUniqueId() + "." + args[0])));
                                ChatNotice.success(player, Component.text("Byl jsi úspěšně teleportován na " + args[0]));
                            }
                            else {
                                ChatNotice.error(player, Component.text("Domov s tímto názvem nebyl nalezen."));
                            }
                        }
                    }
                    return false;
                }
            });
        }

        {
            Bukkit.getCommandMap().register("survival", new Command("homes") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof final Player player))
                        return true;

                    if (commandLabel.equalsIgnoreCase("homes")){
                        if (args.length == 0){
                            var section = plugin.getConfig().getConfigurationSection("homes." + player.getUniqueId());
                            if (section != null && !section.getKeys(false).isEmpty()){
                                StringBuilder sb = new StringBuilder();
                                for (String key : Objects.requireNonNull(plugin.getConfig().getConfigurationSection("homes." + player.getUniqueId())).getKeys(false)){
                                    sb.append(key).append(" ");
                                }
                                ChatNotice.info(player, Component.text("Máš následující domovy: " + sb.toString()));
                            }
                            else {
                                ChatNotice.error(player, Component.text("Nemáš žádné domovy. Vytvoř si nový pomocí /sethome <název>."));
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Použití: /homes"));
                        }
                    }
                    return false;
                }
            });
        }

        {

            Bukkit.getCommandMap().register("survival", new Command("repair") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return true;

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("repair")){
                        if (player.hasPermission("survival.repair")){
                            if (player.hasPermission("survival.bypass.cooldown")){
                                for (ItemStack item : player.getInventory().getContents()){
                                    if (item == null)
                                        continue;
                                    if (!item.hasItemMeta())
                                        continue;

                                    ItemMeta meta = item.getItemMeta();
                                    if (meta instanceof Damageable d){
                                        d.setDamage(0);
                                        item.setItemMeta(meta);
                                    }
                                }
                                ChatNotice.success(player, Component.text("Oprava byla úspěšně provedena."));
                            }
                            else if(cooldownRepair.containsKey(player.getName())) {
                                long secondsLeft = ((cooldownRepair.get(player.getName())/1000)+cooldownRepairTime) - (System.currentTimeMillis()/1000);
                                if(secondsLeft>0) {
                                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                                    if (secondsLeft/60 < 1){
                                        ChatNotice.error(player, Component.text("Další oprava bude možná za: " + secondsLeft + " sekund."));
                                    }
                                    else {
                                        ChatNotice.error(player, Component.text("Další oprava bude možná za: " + secondsLeft/60 + " minut."));
                                    }
                                    return true;
                                }
                            }
                            else {
                                cooldownRepair.put(player.getName(), System.currentTimeMillis());
                                for (ItemStack item : player.getInventory().getContents()){
                                    if (item == null)
                                        continue;
                                    if (!item.hasItemMeta())
                                        continue;

                                    ItemMeta meta = item.getItemMeta();
                                    if (meta instanceof Damageable d){
                                        d.setDamage(0);
                                    }
                                }
                                ChatNotice.success(player, Component.text("Oprava byla úspěšně provedena."));
                            }
                        }
                        else {
                            ChatNotice.error(player, Component.text("Minimální hodnost pro použití tohoto příkazu je VIP."));
                        }
                    }
                    return false;
                }
            });


        }


        {
            Bukkit.getCommandMap().register("survival", new Command("tpaccept") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("tpaccept")) {
                        if (requests.containsKey(player.getUniqueId())) {
                            ChatNotice.success(player, Component.text("Přijal si požadavek na teleportaci."));
                            ChatNotice.success(Bukkit.getPlayer(requests.get(player.getUniqueId())), Component.text("Hráč " + player.getName() + " přijal tvůj požadavek na teleportaci."));
                            Bukkit.getPlayer(requests.get(player.getUniqueId())).teleport(player);
                            requests.remove(player.getUniqueId());
                            return true;
                        }
                        ChatNotice.error(player, Component.text("Nemáš žádné otevřené žádosti o teleportaci."));
                    }

                    return false;
                }
            });


        }

        {
            Bukkit.getCommandMap().register("survival", new Command("tpdeny") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("tpdeny")) {
                        if (requests.containsKey(player.getUniqueId())) {
                            ChatNotice.success(player, Component.text("Odmítl jsi požadavek na teleportaci."));
                            ChatNotice.info(Bukkit.getPlayer(requests.get(player.getUniqueId())), Component.text("Hráč " + player.getName() + " odmítl tvůj požadavek na teleportaci."));
                            requests.remove(player.getUniqueId());
                            return true;
                        }
                        ChatNotice.error(player, Component.text("Nemáš žádné otevřené žádosti o teleportaci."));
                    }

                    return false;
                }
            });


        }


        {
            Bukkit.getCommandMap().register("survival", new Command("tpa") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(!(sender instanceof Player)) {
                        return true;
                    }

                    final Player player = (Player) sender;

                    if (commandLabel.equalsIgnoreCase("tpa")) {
                        if (args.length != 1) {
                            ChatNotice.error(player, Component.text("Použití: /tpa <jméno>"));
                            return true;
                        }

                        Player target = Bukkit.getPlayer(args[0]);
                        if (requests.containsKey(target.getUniqueId())) {
                            ChatNotice.error(player, Component.text("Máš již otevřený požadavek na teleportaci."));
                            return true;
                        }

                        if (target == player){
                            ChatNotice.error(player, Component.text("Nemůžeš se teleportovat sám na sebe."));
                            return true;
                        }
                        if (target != null) {
                            requests.put(target.getUniqueId(), player.getUniqueId());
                            ChatNotice.success(player, Component.text("Poslal jsi požadavek na teleportaci hráči " + ChatColor.AQUA + target.getName() + ChatColor.WHITE + "."));
                            ChatNotice.info(target, Component.text("Hráč " + ChatColor.AQUA + player.getName() + ChatColor.WHITE + " ti poslal žádost o teleportaci."));
                            ChatNotice.info(target, Component.text("Použij "+ ChatColor.AQUA+"/tpaccept"+ChatColor.WHITE + " pro potvrzení nebo "+ ChatColor.AQUA+"/tpdeny "+ChatColor.WHITE+"pro zamítnutí."));
                            ChatNotice.info(target, Component.text("Na potvrzení máš "+ChatColor.AQUA+"30 "+ChatColor.WHITE+"sekund."));
                            Timer timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    if (requests.containsKey(target.getUniqueId())) {
                                        requests.remove(player.getUniqueId());
                                        requests.remove(target.getUniqueId());
                                        ChatNotice.info(player, Component.text("Hráč " + target.getName() + " neodpověděl na tvůj požadavek na teleportaci."));
                                        ChatNotice.info(target, Component.text("Žádost hráče " + player.getName() + " byla smazána."));
                                    }
                                }
                            }, 30000);
                            return true;
                        }
                        ChatNotice.error(player, Component.text("Hráč " + args[0] + " není online."));
                    }
                    return false;
                }
            });
        }


        Location spawn = new Location(Bukkit.getWorld("Spawn"), 22.5, 50, 39.5, 90, 0);

        {
            Bukkit.getCommandMap().register("survival", new Command("spawn") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if (!(sender instanceof Player))
                        return false;

                    final Player player = (Player) sender;
                    player.teleport(spawn);
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                    player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 1);
                    ChatNotice.success(player, Component.text("Byl jsi teleportován na spawn."));
                    return false;
                }
            });
        }

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
                                final String message = ChatColor.GREEN + "+" + ChatColor.GRAY + " | " + ChatColor.GOLD + ColorAPI.process("<GRADIENT:34eb92>"+player.getName()+"</GRADIENT:34eb92>") + ChatColor.WHITE + " se připojil.";
                                for (Player p : Bukkit.getOnlinePlayers())
                                    p.sendMessage(message);
                                ChatNotice.warning(player, Component.text("Vanish deaktivován."));
                            }
                            else {
                                vanishPlayers.add(player.getName());
                                Bukkit.getOnlinePlayers().stream().filter(online -> online != player).forEach(online ->
                                        online.hidePlayer(plugin, player));
                                String message = ChatColor.RED + "-" + ChatColor.GRAY + " | " + ChatColor.GOLD + ColorAPI.process("<GRADIENT:34eb92>"+player.getName()+"</GRADIENT:34eb92>") + ChatColor.WHITE + " se odpojil.";
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
                                if (player.hasPermission("survival.bypass.cooldown")){
                                    player.setHealth(player.getMaxHealth());
                                    player.setFoodLevel(20);
                                    ChatNotice.success(player, Component.text("Byl jsi vyléčen."));
                                }
                                else if(cooldownHealth.containsKey(player.getName())) {
                                    long secondsLeft = ((cooldownHealth.get(player.getName())/1000)+cooldownHeal) - (System.currentTimeMillis()/1000);
                                    if(secondsLeft>0) {
                                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                                        if (secondsLeft/60 < 1){
                                            ChatNotice.error(player, Component.text("Další uzdravení bude možné za: " + secondsLeft + " sekund."));
                                        }
                                        else {
                                            ChatNotice.error(player, Component.text("Další uzdravení bude možné za: " + secondsLeft/60 + " minut."));
                                        }
                                        return true;
                                    }
                                }
                                else {
                                    cooldownHealth.put(player.getName(), System.currentTimeMillis());
                                    player.setHealth(player.getMaxHealth());
                                    player.setFoodLevel(20);
                                    ChatNotice.success(player, Component.text("Byl jsi vyléčen."));
                                }
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
                                ChatNotice.error(player, Component.text("Použití: /tp <jméno> | <na hráče>"));
                            }
                            else if (args.length == 1){
                                Player toPlayer = Bukkit.getPlayer(args[0]);
                                if (toPlayer != null){
                                    player.teleport(toPlayer);
                                    ChatNotice.success(player, Component.text("Teleportoval jsi se na hráče " + toPlayer.getName()));
                                }
                                else {
                                    ChatNotice.error(player, Component.text("Hráč nebyl nalezen."));
                                }
                            }
                            else if (args.length == 2){
                                Player fromPlayer = Bukkit.getPlayer(args[0]);
                                Player toPlayer = Bukkit.getPlayer(args[1]);
                                if (toPlayer != null){
                                    if (fromPlayer != null){
                                        fromPlayer.teleport(toPlayer);
                                        ChatNotice.success(player, Component.text("Teleportoval jsi hráče " + fromPlayer.getName() + " na hráče " + toPlayer.getName()));
                                    }
                                    else {
                                        ChatNotice.error(player, Component.text("Hráč kterého chceš teleportovat nebyl nalezen."));
                                    }
                                }
                                else {
                                    ChatNotice.error(player, Component.text("Hráč na kterého chceš provést teleportaci nebyl nalezen."));
                                }
                            }
                            else {
                                ChatNotice.error(player, Component.text("Použití: /tp <jméno> | <na hráče>"));
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

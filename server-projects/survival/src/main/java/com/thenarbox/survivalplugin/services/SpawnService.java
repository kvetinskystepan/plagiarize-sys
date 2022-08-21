package com.thenarbox.survivalplugin.services;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import static org.bukkit.Bukkit.getServer;

public class SpawnService implements Listener {

    public static Location spawn;
    public static void spawnSettings(){
        spawn = new Location(Bukkit.getWorld("world"), 22.5, 50, 39.5, 90, 0);

        final var spawn_world = getServer().
                getWorld("world");

        spawn_world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        spawn_world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        spawn_world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        spawn_world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
        spawn_world.setGameRule(GameRule.DO_FIRE_TICK, false);
        spawn_world.setGameRule(GameRule.MOB_GRIEFING, false);
        spawn_world.setGameRule(GameRule.FALL_DAMAGE, false);
        spawn_world.setGameRule(GameRule.SPAWN_RADIUS, 1000000);
        spawn_world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
        spawn_world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        spawn_world.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);

        spawn_world.setTime(6000);
        spawn_world.setStorm(false);
        spawn_world.setThundering(false);
        spawn_world.setWeatherDuration(0);
    }


    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        Player player = (Player) e.getEntity();
        if (player.getWorld().getName().equals("world")){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        Player player = (Player) e.getEntity();
        if (player.getWorld().getName().equals("world")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void Fill(PlayerBucketFillEvent e){
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("world")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void noPvP(EntityDamageByEntityEvent e) {
        Player player = (Player) e.getEntity();
        if (player.getWorld().getName().equals("world")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("world")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("world")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("world")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("world")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("world")) {
            e.setCancelled(true);
        }
    }



}
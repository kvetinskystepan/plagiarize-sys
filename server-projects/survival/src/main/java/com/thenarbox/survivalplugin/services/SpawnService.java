package com.thenarbox.survivalplugin.services;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.*;

import static org.bukkit.Bukkit.getServer;

public class SpawnService implements Listener {

    public static Location spawn;

    public static void spawnSettings(){
        spawn = new Location(Bukkit.getWorld("Spawn"), 22.5, 50, 39.5, 90, 0);

        final var spawn_world = getServer().getWorld("Spawn");

        spawn_world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        spawn_world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        spawn_world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        spawn_world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
        spawn_world.setGameRule(GameRule.DO_FIRE_TICK, false);
        spawn_world.setGameRule(GameRule.MOB_GRIEFING, false);
        spawn_world.setGameRule(GameRule.FALL_DAMAGE, false);
        spawn_world.setGameRule(GameRule.SPAWN_RADIUS, 999999999);
        spawn_world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
        spawn_world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        spawn_world.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);

        spawn_world.setTime(18000);
        spawn_world.setStorm(false);
        spawn_world.setThundering(false);
        spawn_world.setWeatherDuration(0);
    }


    @EventHandler
    public void onPlayerAction(PlayerInteractEntityEvent event){
        if (event.getPlayer().getLocation().getWorld().getName().equals("Spawn")){
             event.setCancelled(true);
        }
    }
    @EventHandler
    public void onFrames(BlockPhysicsEvent event){
        if (event.getBlock().getLocation().getWorld().getName().equals("Spawn")){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onFrameBreak(HangingBreakByEntityEvent event){
        if(event.getEntity().getLocation().getWorld().getName().equals("Spawn")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFramePlace(HangingPlaceEvent event) {
        if(event.getEntity().getLocation().getWorld().getName().equals("Spawn")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFrame(HangingBreakEvent event){
        if(event.getEntity().getLocation().getWorld().getName().equals("Spawn")){
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        Player player = (Player) e.getEntity();
        if (player.getWorld().getName().equals("Spawn")){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof ArmorStand){
            if (e.getEntity().getLocation().getWorld().getName().equals("Spawn")){
                e.setCancelled(true);
            }
        }

        if ((e.getEntity() instanceof Player player)){
            player = (Player)e.getEntity();
            if(player.getWorld().getName().equals("Spawn"))
               e.setCancelled(true);
        }
    }

    @EventHandler
    public void Fill(PlayerBucketFillEvent e){
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("Spawn")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void noPvP(EntityDamageByEntityEvent e) {
        if ((e.getEntity() instanceof Player player)){
            player = (Player)e.getEntity();
            if(player.getWorld().getName().equals("Spawn"))
               e.setCancelled(true);
        }

        if ((e.getEntity() instanceof ArmorStand)){
            if (e.getEntity().getLocation().getWorld().getName().equals("Spawn")){
                e.setCancelled(true);
            }
        }

        if((e.getEntity() instanceof ItemFrame frame)){
            frame = (ItemFrame)e.getEntity();
            if(frame.getLocation().getWorld().getName().equals("Spawn"))
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("Spawn")) {
           e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("Spawn")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("Spawn")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("Spawn")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();
        if (player.getWorld().getName().equals("Spawn")) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();

        if(!player.getWorld().getName().equals("Spawn"))
            return;

        if (player.getLocation().getY() <= 0) {
            player.teleport(spawn);
        }
        if (player.getLocation().getY() >= 200) {
            player.teleport(spawn);
        }

        if (player.getLocation().getX() <= -390){
            player.teleport(spawn);
        }
        if (player.getLocation().getX() >= 265){
            player.teleport(spawn);
        }

        if (player.getLocation().getZ() <= -300){
            player.teleport(spawn);
        }
        if (player.getLocation().getZ() >= 340){
            player.teleport(spawn);
        }
    }
}

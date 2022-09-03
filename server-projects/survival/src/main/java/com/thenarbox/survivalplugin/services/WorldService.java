package com.thenarbox.survivalplugin.services;

import org.bukkit.GameRule;
import org.bukkit.event.Listener;

import static org.bukkit.Bukkit.getServer;

public class WorldService implements Listener {

        public static void worldSettings(){
            final var world = getServer().getWorld("world");
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, true);
            world.setGameRule(GameRule.DO_MOB_SPAWNING, true);
            world.setGameRule(GameRule.DO_ENTITY_DROPS, true);
            world.setGameRule(GameRule.DO_FIRE_TICK, true);
            world.setGameRule(GameRule.MOB_GRIEFING, true);
            world.setGameRule(GameRule.FALL_DAMAGE, true);
            world.setGameRule(GameRule.SPAWN_RADIUS, 0);
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            world.setGameRule(GameRule.RANDOM_TICK_SPEED, 3);
        }
}

package com.thenarbox.survivalplugin.services;

import com.thenarbox.api.ChatNotice;
import lombok.extern.log4j.Log4j2;
import net.kyori.adventure.text.Component;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Random;

@Log4j2(topic = "RandomTeleport")
public class RandomTeleport implements Listener {

    static int randomX = 0;
    static int randomZ = 0;
    static Random random = new Random();

    private static void teleportInternal(Player player, World world){
        randomX = random.nextInt(-12000, 12000);
        randomZ = random.nextInt(-12000, 12000);
        var future = world.getChunkAtAsync(randomX, randomZ);
        future.thenAccept(chunk -> {
            var block = world.getHighestBlockAt(randomX, randomZ);
            if (block.getType().equals(Material.WATER) || block.getType().equals(Material.LAVA)) {
                teleportInternal(player, world);
                return;
            }
            player.teleport(world.getHighestBlockAt(randomX, randomZ).getLocation());
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            ChatNotice.success(player, Component.text("Byl/a jsi teleportován/a na náhodné místo v přírodě."));
            ChatNotice.info(player, Component.text("Tvé souřadnice: X: " + player.getLocation().getBlockX() + ", Y: " + player.getLocation().getBlockZ()));
            player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 1);
        });
    }
    public static void teleport(Player player, World world){
        ChatNotice.success(player, Component.text("Teleportuji tě na náhodné místo v přírodě..."));
        teleportInternal(player, world);
    }
}

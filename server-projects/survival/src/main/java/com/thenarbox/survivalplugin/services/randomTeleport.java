package com.thenarbox.survivalplugin.services;

import com.thenarbox.api.ChatNotice;
import net.kyori.adventure.text.Component;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Random;

public class randomTeleport implements Listener {

    static int randomX = 0;
    static int randomZ = 0;
    static Random random = new Random();
    public static void teleport(Player player, World world){
        randomX = random.nextInt(25000);
        randomZ = random.nextInt(25000);
        ChatNotice.success(player, Component.text("Teleportuji tě na náhodné místo v přírodě..."));
        player.teleport(world.getHighestBlockAt(randomX, randomZ).getLocation());
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 1);
        ChatNotice.success(player, Component.text("Byl jsi teleportován na náhodné místo v přírodě."));
        ChatNotice.info(player, Component.text("Tvé souřadnice: X: " + player.getLocation().getBlockX() + ", Y: " + player.getLocation().getBlockZ()));
    }
}

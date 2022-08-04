package com.thenarbox.lobbyplugin.extenders;

import com.thenarbox.api.Standards;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.concurrent.locks.StampedLock;


public class DoubleJump
        implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerFly(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if(Standards.flyingPlayers.contains(p))
            return;
        if (p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
            p.setAllowFlight(false);
            p.setFlying(false);
            p.setVelocity(p.getLocation().getDirection().multiply(2.0D).setY(0.9D));
            p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.5F, 1.0F);}
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(Standards.flyingPlayers.contains(p))
            return;
        if ((e.getPlayer().getGameMode() != GameMode.CREATIVE) && (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
            p.setAllowFlight(true);
        }
    }
}

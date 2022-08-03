package com.thenarbox.authplugin.scoreboard;

import com.thenarbox.psys.abstraction.Controllable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;

@RequiredArgsConstructor
public class BoardService
        implements Listener, Controllable {

    private final JavaPlugin plugin;

    @Getter
    private final Scoreboard board
            = Bukkit.getScoreboardManager().getNewScoreboard();

    @Override
    public void initialize() {
        board.registerNewTeam("picusiucv");

        new BukkitRunnable(){
            @Override
            public void run() {
                handleUpdate();
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    @Override
    public void terminate() {

    }

    private void handleUpdate() {

    }

    @EventHandler
    private void handlePlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.setScoreboard(board);
    }

}

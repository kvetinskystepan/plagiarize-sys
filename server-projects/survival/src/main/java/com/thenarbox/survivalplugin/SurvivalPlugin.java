package com.thenarbox.survivalplugin;

import lombok.extern.log4j.Log4j2;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Log4j2(topic = "SurvivalPlugin")
public class SurvivalPlugin extends JavaPlugin implements Listener {
    public void onEnable() {
        log.info("SurvivalPlugin has been enabled.");
    }
}

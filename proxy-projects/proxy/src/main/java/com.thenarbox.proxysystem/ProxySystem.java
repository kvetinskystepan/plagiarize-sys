package com.thenarbox.proxysystem;
import com.thenarbox.proxysystem.listeners.CommandMechanic;
import com.thenarbox.proxysystem.messages.PrivateMessages;
import com.thenarbox.proxysystem.motd.Motd;
import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public final class ProxySystem extends Plugin implements Listener {

    @Getter
    private static Plugin staticInstance = null;

    public ProxySystem() {
        staticInstance = this;
    }

    @Override
    public void onEnable() {
        System.out.println("ProxySystem is now enabled.");
        ProxyServer.getInstance().getPluginManager().registerListener(this, new PrivateMessages());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new Motd());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new CommandMechanic());
        CommandMechanic.Commands();
    }


    @Override
    public void onDisable() {
        System.out.println("ProxySystem is now disabled.");
    }


}

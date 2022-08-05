package com.thenarbox.proxysystem.services;

import com.google.common.util.concurrent.Service;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.Server;

import java.net.Proxy;

public class LobbyServiceControl extends Service.Listener {

    Server server = (Server) ProxyServer.getInstance().getServers().get("Lobby-1");

    public boolean isOnline(){
        final boolean[] isOnline = {false};
        ProxyServer.getInstance().getServerInfo("Lobby-1").ping(new Callback<ServerPing>() {

            @Override
            public void done(ServerPing result, Throwable error) {
                if (result == null)
                    isOnline[0] = false;
                else
                    isOnline[0] = true;
            }
        });
        return isOnline[0];
    }
}

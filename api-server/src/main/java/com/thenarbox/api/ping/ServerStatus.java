package com.thenarbox.api.ping;

import com.thenarbox.api.ping.mcping.MinecraftPing;
import com.thenarbox.api.ping.mcping.MinecraftPingOptions;
import com.thenarbox.api.ping.mcping.MinecraftPingReply;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ServerStatus {

    private static final Int2ObjectArrayMap<MinecraftPingReply> cache
            = new Int2ObjectArrayMap<>();

    public static MinecraftPingReply cachedServerStatus(int port) {
        return cache.get(port);
    }
    public static CompletableFuture<MinecraftPingReply> queryServerStatus(String hostname, int port) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var data = MinecraftPing.getPing(
                        new MinecraftPingOptions().setHostname(hostname).setPort(port)
                );
                cache.put(port, data);
                return data;
            } catch (final IOException e) {
                return null;
            }
        });
    }


}

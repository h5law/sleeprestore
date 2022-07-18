package com.github.h5law.sleeprestore;

import io.papermc.paper.event.player.PlayerDeepSleepEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SleepRestoreListener implements Listener {
    private final SleepRestore plugin;

    public SleepRestoreListener(SleepRestore plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerSleepListener(PlayerDeepSleepEvent event) {
        plugin.restorePlayer(event.getPlayer());
    }
}

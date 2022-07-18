package com.github.h5law.sleeprestore;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class SleepRestore extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginDescriptionFile pdFile = getDescription();
        Logger logger = getLogger();
        logger.log(
                Level.INFO,
                "{0} has been enabled v{1}",
                new Object[]{ pdFile.getName(), pdFile.getVersion() }
        );

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new SleepRestoreListener(this), this);
    }

    public void restorePlayer(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        // Log to server
        PluginDescriptionFile pdFile = getDescription();
        Logger logger = getLogger();
        logger.log(
                Level.INFO,
                "[{0}] {1} has been fully healed",
                new Object[]{
                        pdFile.getName(),
                        PlainTextComponentSerializer.plainText().serialize(player.displayName())
                }
        );
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdFile = getDescription();
        Logger logger = getLogger();
        logger.log(
                Level.INFO,
                "{0} has been disabled v{1}",
                new Object[]{ pdFile.getName(), pdFile.getVersion() }
        );
    }
}

package com.github.h5law.sleeprestore;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SleepRestore extends JavaPlugin {

    // In set then show debug info to player with UUID
    public HashSet<UUID> PlayerDebug = new HashSet<>();
    // In set then stop replacing tools
    public HashSet<UUID> DisableSleepRestore = new HashSet<>();

    public void toggle(Player player, HashSet<UUID> set) {
        if (set.contains(player.getUniqueId())) {
            set.remove(player.getUniqueId());
        } else {
            set.add(player.getUniqueId());
        }
    }

    public void toggle(Player player, HashSet<UUID> set, Boolean bool) {
        if (bool) {
            set.add(player.getUniqueId());
        } else {
            set.remove(player.getUniqueId());
        }

    }

    private void debug(Player player, Boolean critical, String text, @Nullable Object... vars) {
        if (!PlayerDebug.contains(player.getUniqueId())) return;
        if (text != null) {
            if (vars.length > 0) {
                for (int i = 0; i < vars.length; i++) {
                    text = text.replace("{" + i + "}", vars[i].toString());
                }
            }
            player.sendMessage(
                    (critical ? ChatColor.RED : ChatColor.GREEN)
                            + "[SleepRestore] " + text);
        }
    }

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
        getCommand("sleeprestore").setExecutor(new SleepRestoreCommand(this));
    }

    public void restorePlayer(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        debug(player, false, "{0} has been fully healed", PlainTextComponentSerializer.plainText().serialize(player.displayName()));
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

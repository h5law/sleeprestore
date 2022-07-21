package com.github.h5law.sleeprestore;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

import static net.kyori.adventure.text.Component.text;

public final class SleepRestore extends JavaPlugin {

    public Boolean dayChange = true;

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
        // Logging
        PluginDescriptionFile pdFile = getDescription();
        player.sendMessage(
                String.format(
                        ChatColor.GREEN + "[%s] %s has been fully healed",
                        pdFile.getName(),
                        PlainTextComponentSerializer.plainText().serialize(player.displayName())
                )
        );
        broadcastMessage(player);
    }

    public void setDayChange(Boolean bool) {
        dayChange = bool;
    }

    public void setDayTime(Player player) {
        World world = player.getWorld();
        long time = 24000 - world.getTime();
        if (world.hasStorm()) {
            world.setStorm(false);
        }
        if (world.isThundering()) {
            world.setThundering(false);
        }
        world.setTime(time);
        // Logging
        PluginDescriptionFile pdFile = getDescription();
        TextComponent completedMessage = text(
                String.format(
                        ChatColor.GREEN + "[%s] %s slept the server through the night",
                        pdFile.getName(),
                        PlainTextComponentSerializer.plainText().serialize(player.displayName())
                )
        );
        getServer().broadcast(completedMessage);
    }

    public void broadcastMessage(Player player) {
        PluginDescriptionFile pdFile = getDescription();
        String playerName = PlainTextComponentSerializer.plainText().serialize(player.displayName());

        String cancelMessage = "[\"\",{\"text\":\"[SleepRestore] player\"},{\"text\":\" is sleeping [\"},{\"text\":\"CANCEL\",\"color\":\"red\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/sr cancel\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"tooltip\"}]}}},{\"text\":\"]\",\"bold\":false}]";
        cancelMessage = cancelMessage.replace("player", playerName);
        cancelMessage = cancelMessage.replace("tooltip", "Click to cancel sleeping through the night");

        if (getServer().getOnlinePlayers().size() > 1) {
            setDayChange(true);

            final Component component = GsonComponentSerializer.gson().deserialize(cancelMessage);
            getServer().broadcast(component);

            getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    if (dayChange) {
                        setDayTime(player);
                    } else {
                        setDayChange(true);
                    }
                }
            }, 10 * 20);
        } else {
            setDayTime(player);
        }
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

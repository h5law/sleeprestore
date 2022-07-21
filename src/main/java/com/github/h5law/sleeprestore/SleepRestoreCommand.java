package com.github.h5law.sleeprestore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.text;

public class SleepRestoreCommand implements CommandExecutor {

    private final SleepRestore plugin;

    public SleepRestoreCommand(SleepRestore plugin) {
        this.plugin = plugin;
    }
    public void usage(Player player) {
        player.sendMessage("[SleepRestore] Usage: /tr [command]");
        player.sendMessage("[SleepRestore] Options: help, cancel");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                usage(player);
            } else {
                switch (args[0]) {
                    case "help" -> {
                        usage(player);
                        return true;
                    }
                    case "cancel" -> {
                        if (plugin.dayChange) {
                            plugin.setDayChange(false);
                            plugin.getServer().broadcast(text("[SleepRestore] Sleep event cancelled"));
                        }
                        return true;
                    }
                    default -> {
                        player.sendMessage(ChatColor.RED + "[SleepRestore] Unknown option");
                        usage(player);
                    }
                }
            }
        }
        return false;
    }
}
package com.github.h5law.sleeprestore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SleepRestoreCommand implements CommandExecutor {

    private final SleepRestore plugin;

    public SleepRestoreCommand(SleepRestore plugin) {
        this.plugin = plugin;
    }

    public void usage(Player player) {
        player.sendMessage("[SleepRestore] Usage: /sr [OPTION] [ARGUMENT]");
        player.sendMessage("[SleepRestore] Options: help, debug, restore");
        player.sendMessage("[SleepRestore] Arguments: enable, disable, toggle");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                usage(player);
            } else {
                switch (args[0]) {
                    case "help":
                        usage(player);
                        return true;
                    case "debug":
                        if (args.length > 1) {
                            switch (args[1]) {
                                case "true" -> {
                                    plugin.toggle(player, plugin.PlayerDebug, true);
                                    player.sendMessage(ChatColor.GREEN + "[SleepRestore] Debug enabled");
                                    return true;
                                }
                                case "false" -> {
                                    plugin.toggle(player, plugin.PlayerDebug, false);
                                    player.sendMessage(ChatColor.RED + "[SleepRestore] Debug disabled");
                                    return true;
                                }
                                default ->
                                        player.sendMessage(ChatColor.RED + "[SleepRestore] Usage: /sr debug <true/false>");
                            }
                        } else {
                            plugin.toggle(player, plugin.PlayerDebug);
                            if (plugin.PlayerDebug.contains(player.getUniqueId())) {
                                player.sendMessage(ChatColor.GREEN + "[SleepRestore] Debug enabled");
                            } else {
                                player.sendMessage(ChatColor.RED + "[SleepRestore] Debug disabled");
                            }
                            return true;
                        }
                    case "replace":
                        if (args.length > 1) {
                            switch (args[1]) {
                                case "true" -> {
                                    // remove from disable set
                                    plugin.toggle(player, plugin.DisableSleepRestore, false);
                                    player.sendMessage(ChatColor.GREEN + "[SleepRestore] Sleep restoration enabled");
                                    return true;
                                }
                                case "false" -> {
                                    // add to disable set
                                    plugin.toggle(player, plugin.DisableSleepRestore, true);
                                    player.sendMessage(ChatColor.RED + "[SleepRestore] Sleep restoration disabled");
                                    return true;
                                }
                                default ->
                                        player.sendMessage(ChatColor.RED + "[SleepRestore] Usage: /sr restore <true/false>");
                            }
                        } else {
                            plugin.toggle(player, plugin.DisableSleepRestore);
                            if (plugin.DisableSleepRestore.contains(player.getUniqueId())) {
                                player.sendMessage(ChatColor.RED + "[SleepRestore] Sleep restoration disabled");
                            } else {
                                player.sendMessage(ChatColor.GREEN + "[SleepRestore] Sleep restoration enabled");
                            }
                            return true;
                        }
                    default:
                        player.sendMessage(ChatColor.RED + "[SleepRestore] Unknown option");
                        usage(player);
                }
            }
        }
        return false;
    }
}

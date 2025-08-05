package io.github.keishispl.knockDC;

import io.github.keishispl.knockDC.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logger {
    public static void success(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatUtils.translateHexColors(KnockDC.getPlugin().getConfig().getString("prefix")) + " " + ChatColor.GREEN + ChatUtils.translateHexColors(message));
    }

    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatUtils.translateHexColors(KnockDC.getPlugin().getConfig().getString("prefix")) + " " + ChatColor.GRAY + ChatUtils.translateHexColors(message));
    }

    public static void warn(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatUtils.translateHexColors(KnockDC.getPlugin().getConfig().getString("prefix")) + " " + ChatColor.YELLOW + ChatUtils.translateHexColors(message));
    }

    public static void error(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatUtils.translateHexColors(KnockDC.getPlugin().getConfig().getString("prefix")) + " " + ChatColor.RED + ChatUtils.translateHexColors(message));
    }
}

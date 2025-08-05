package io.github.keishispl.knockDC.utils;

import io.github.keishispl.knockDC.KnockDC;
import org.bukkit.command.CommandSender;

public class Message {
    public static void send(CommandSender sender, String message) {
        sender.sendMessage(ChatUtils.translateHexColors(KnockDC.getPlugin().getConfig().getString("prefix")) + " " + ChatUtils.translateHexColors(message));
    }
    public static void sendClear(CommandSender sender, String message) {
        sender.sendMessage(ChatUtils.translateHexColors(message));
    }
}

package io.github.keishispl.knockDC.utils;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

public class Message {
    public static void send(CommandSender sender, String message) {
        sendClear(sender, ChatUtils.translateHexColors(Config.get().getString("prefix")) + " " + ChatUtils.translateHexColors(message));
    }

    public static void sendClear(CommandSender sender, String message) {
        sender.sendMessage(ChatUtils.translateHexColors(message));
    }

    public static void spigot(CommandSender player, TextComponent... message) {
        player.spigot().sendMessage(message);
    }
}

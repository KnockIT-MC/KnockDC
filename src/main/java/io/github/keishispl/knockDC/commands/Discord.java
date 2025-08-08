package io.github.keishispl.knockDC.commands;

import io.github.keishispl.knockDC.utils.Component;
import io.github.keishispl.knockDC.utils.Config;
import io.github.keishispl.knockDC.utils.Message;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Discord implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Config.get().getString("invite") != null && !Config.get().getString("invite").isEmpty()) {
            TextComponent prefix = new Component(Config.get().getString("prefix") + " ").create();
            TextComponent message = new Component("&fJoin our Discord server ").create();
            TextComponent here = new Component("&bhere")
                    .setHover("&fClick to join!")
                    .setClick("https://discord.com/invite/" + Config.get().getString("invite"))
                    .create();
            Message.spigot(sender, prefix, message, here, new Component("&f.").create());
            return true;
        }
        return false;
    }
}

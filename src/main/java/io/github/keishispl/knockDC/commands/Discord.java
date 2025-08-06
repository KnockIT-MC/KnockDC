package io.github.keishispl.knockDC.commands;

import io.github.keishispl.knockDC.KnockDC;
import io.github.keishispl.knockDC.utils.ChatUtils;
import io.github.keishispl.knockDC.utils.Message;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Discord implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (KnockDC.getPlugin().getConfig().getString("invite") != null && !KnockDC.getPlugin().getConfig().getString("invite").isEmpty()) {
            TextComponent prefix = new TextComponent(ChatUtils.translateHexColors(KnockDC.getPlugin().getConfig().getString("prefix")) + " ");
            TextComponent here = new TextComponent(ChatUtils.translateHexColors("&bhere"));
            here.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatUtils.translateHexColors("&fClick to join!")).create()));
            here.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.com/invite/" + KnockDC.getPlugin().getConfig().getString("invite")));
            TextComponent message = new TextComponent(ChatUtils.translateHexColors("&fJoin our Discord server "));
            Message.spigot(sender, prefix, message, here);
            return true;
        }
        return false;
    }
}

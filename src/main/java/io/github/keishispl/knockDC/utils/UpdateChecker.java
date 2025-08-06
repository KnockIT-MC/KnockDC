package io.github.keishispl.knockDC.utils;

import io.github.keishispl.knockDC.KnockDC;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

import static io.github.keishispl.knockDC.KnockDC.latest_version;


@SuppressWarnings("deprecation")
public class UpdateChecker implements Listener {

    public static boolean updateCheck(CommandSender p){
        latest_version = getVersion.latest();
        if (!Objects.equals(latest_version, KnockDC.getPlugin().getDescription().getVersion())) {

            Message.sendClear(p, "&fThere is a new &9KnockDC &fupdate! v&9" + KnockDC.getPlugin().getDescription().getVersion() + "&8 -> &fv&b" + latest_version);
            TextComponent message = new TextComponent(ChatUtils.translateHexColors("&bhere"));
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatUtils.translateHexColors("&fClick to update!")).create()));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://modrinth.com/plugin/knockdc/version/" + latest_version));
            Message.spigot(p, message);
            return true;
        }
        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (KnockDC.getPlugin().getConfig().getBoolean("update-check", true)) {
            if (e.getPlayer().hasPermission("op")) {
                Bukkit.getScheduler().runTaskLater(KnockDC.getPlugin(), () -> updateCheck(e.getPlayer()), 100L);
            }
        }
    }
}
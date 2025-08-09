package io.github.keishispl.knockDC.discord;

import io.github.keishispl.knockDC.KnockDC;
import io.github.keishispl.knockDC.Logger;
import io.github.keishispl.knockDC.utils.Config;
import io.github.keishispl.knockDC.utils.EmbedFooter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class JoinLeaveEvent implements Listener {
    private void sendMessage(Player player, Color color, String description) {
        List<String> channels = Config.get().getStringList("entry.channels");
        for (String channelString : channels) {
            TextChannel channel = KnockDC.getPlugin().getJDA().getTextChannelById(channelString);
            if (channel != null) {
                String link = "";
                if (Config.get().getBoolean("entry.include-photo")) {
                    link = "https://visage.surgeplay.com/bust/" + player.getUniqueId();
                }
                try {
                    MessageEmbed embed = new EmbedBuilder()
                            .setAuthor(player.getName(), null, "https://visage.surgeplay.com/face/" + player.getUniqueId())
                            .setColor(color)
                            .setDescription(description)
                            .setImage(link)
                            .setFooter(EmbedFooter.get())
                            .setTimestamp(LocalDateTime.now())
                            .build();
                    channel.sendMessageEmbeds(embed).queue();
                } catch (Exception e) {}
            } else {
                Logger.error("Channel " + channelString + " does not exist.");
            }
        }
    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        KnockDC.getPlugin().getJDA().getPresence().setActivity(Activity.watching(Bukkit.getServer().getOnlinePlayers().size() + " players"));

        if (!Config.get().getBoolean("entry.enabled") || !Config.check("entry")) return;
        if (!Config.get().getBoolean("entry.config.join.enabled") || !Config.check("entry.config.join")) return;

        Color color = new Color(50, 252, 104);
        String description = Config.get().getString("entry.config.join.message")
                .replaceAll("%player%", event.getPlayer().getName())
                .replaceAll("%player-display%", event.getPlayer().getDisplayName());
        if (!event.getPlayer().hasPlayedBefore()) {
            if (Config.get().getBoolean("entry.config.join.new-player.enabled") && Config.check("entry.config.join.new-player")) {
                color = new Color(252, 232, 3);
                description = Config.get().getString("entry.config.join.new-player.message")
                        .replaceAll("%player%", event.getPlayer().getName())
                        .replaceAll("%player-display%", event.getPlayer().getDisplayName())
                        .replaceAll("%player-count%", "" + Bukkit.getServer().getOfflinePlayers().length);
            }
        }
        sendMessage(event.getPlayer(), color, description);
    }

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent event) {
        KnockDC.getPlugin().getJDA().getPresence().setActivity(Activity.watching((Bukkit.getServer().getOnlinePlayers().size() - 1) + " players"));

        if (!Config.get().getBoolean("entry.enabled") || !Config.check("entry")) return;
        if (!Config.get().getBoolean("entry.config.leave.enabled") || !Config.check("entry.config.leave")) return;

        Color color = new Color(252, 3, 3);
        String description = Config.get().getString("entry.config.leave.message")
                .replaceAll("%player%", event.getPlayer().getName())
                .replaceAll("%player-display%", event.getPlayer().getDisplayName());
        sendMessage(event.getPlayer(), color, description);
    }
}

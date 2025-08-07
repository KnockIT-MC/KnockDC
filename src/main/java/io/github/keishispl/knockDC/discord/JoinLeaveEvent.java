package io.github.keishispl.knockDC.discord;

import io.github.keishispl.knockDC.KnockDC;
import io.github.keishispl.knockDC.Logger;
import io.github.keishispl.knockDC.utils.CheckConfig;
import io.github.keishispl.knockDC.utils.EmbedFooter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class JoinLeaveEvent implements Listener {
    private KnockDC plugin = KnockDC.getPlugin();
    private JDA jda;

    private void sendMessage(Player player, Color color, String description) {
        jda = plugin.getJDA();
        List<String> channels = plugin.getConfig().getStringList("entry.channels");
        for (String channelString : channels) {
            TextChannel channel = jda.getTextChannelById(channelString);
            if (channel != null) {
                String link = "";
                if (plugin.getConfig().getBoolean("entry.include-photo")) {
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
                } catch (Exception e) {

                }
            } else {
                Logger.error("Channel " + channelString + " does not exist.");
            }
        }
    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        plugin.getJDA().getPresence().setActivity(Activity.watching(plugin.getServer().getOnlinePlayers().size() + " players"));

        if (!plugin.getConfig().getBoolean("entry.enabled") || !CheckConfig.check("entry")) return;
        if (!plugin.getConfig().getBoolean("entry.config.join.enabled") || !CheckConfig.check("entry.config.join")) return;

        Color color = new Color(50, 252, 104);
        String description = KnockDC.getPlugin().getConfig().getString("entry.config.join.message")
                .replaceAll("%player%", event.getPlayer().getName());
        if (!event.getPlayer().hasPlayedBefore()) {
            if (plugin.getConfig().getBoolean("entry.config.join.new-player.enabled") && CheckConfig.check("entry.config.join.new-player")) {
                color = new Color(252, 232, 3);
                description = KnockDC.getPlugin().getConfig().getString("entry.config.join.new-player.message")
                        .replaceAll("%player%", event.getPlayer().getName())
                        .replaceAll("%player-count%", "" + plugin.getServer().getOfflinePlayers().length);
            }
        }
        sendMessage(event.getPlayer(), color, description);
    }

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent event) {
        plugin.getJDA().getPresence().setActivity(Activity.watching((plugin.getServer().getOnlinePlayers().size() - 1) + " players"));

        if (!plugin.getConfig().getBoolean("entry.enabled") || !CheckConfig.check("entry")) return;
        if (!plugin.getConfig().getBoolean("entry.config.leave.enabled") || !CheckConfig.check("entry.config.leave")) return;

        Color color = new Color(252, 3, 3);
        String description = KnockDC.getPlugin().getConfig().getString("entry.config.leave.message")
                .replaceAll("%player%", event.getPlayer().getName());
        sendMessage(event.getPlayer(), color, description);
    }
}

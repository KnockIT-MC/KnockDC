package io.github.keishispl.knockDC.discord;

import io.github.keishispl.knockDC.KnockDC;
import io.github.keishispl.knockDC.Logger;
import io.github.keishispl.knockDC.utils.CheckConfig;
import io.github.keishispl.knockDC.utils.EmbedFooter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class ChatMessageEvent implements Listener {
    private KnockDC plugin = KnockDC.getPlugin();
    private JDA jda;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (event.isCancelled()) return;
        if (!plugin.getConfig().getBoolean("chat.enabled") || !CheckConfig.check("chat")) return;

        jda = plugin.getJDA();
        List<String> channels = plugin.getConfig().getStringList("chat.channels");
        for (String channelString : channels) {
            TextChannel channel = jda.getTextChannelById(channelString);
            if (channel != null) {
                try {
                    MessageEmbed embed = new EmbedBuilder()
                            .setAuthor(event.getPlayer().getName(), null, "https://visage.surgeplay.com/face/" + event.getPlayer().getUniqueId())
                            .setColor(new Color(73, 120, 214))
                            .setDescription(KnockDC.getPlugin().getConfig().getString("chat.message")
                                    .replaceAll("%player%", event.getPlayer().getName())
                                    .replaceAll("%message%", event.getMessage())
                            )
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
}

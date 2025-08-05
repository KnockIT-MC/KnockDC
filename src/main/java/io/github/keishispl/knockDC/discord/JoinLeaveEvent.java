package io.github.keishispl.knockDC.discord;

import io.github.keishispl.knockDC.KnockDC;
import io.github.keishispl.knockDC.Logger;
import io.github.keishispl.knockDC.utils.CheckConfig;
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
        List<String> channels = plugin.getConfig().getStringList("chat.channels");
        for (String channelString : channels) {
            TextChannel channel = jda.getTextChannelById(channelString);
            if (channel != null) {
                try {
                    MessageEmbed embed = new EmbedBuilder()
                            .setAuthor(player.getName(), null, "https://visage.surgeplay.com/face/" + player.getUniqueId())
                            .setColor(color)
                            .setDescription(description)
                            .setImage("https://visage.surgeplay.com/bust/" + player.getUniqueId())
                            .setFooter("From KnockIT in-game.")
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

        if (!plugin.getConfig().getBoolean("chat.enabled") || !CheckConfig.check("chat")) return;
        if (!plugin.getConfig().getBoolean("chat.join-leave-messages")) return;

        Color color = new Color(50, 252, 104);
        String description = event.getPlayer().getName() + " joined the server.";
        if (!event.getPlayer().hasPlayedBefore()) {
            color = new Color(252, 232, 3);
            description = event.getPlayer().getName() + " joined the server for the first time. #" + plugin.getServer().getOfflinePlayers().length;
        }
        sendMessage(event.getPlayer(), color, description);
    }

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent event) {
        plugin.getJDA().getPresence().setActivity(Activity.watching(plugin.getServer().getOnlinePlayers().size() + " players"));

        if (!plugin.getConfig().getBoolean("chat.enabled") || !CheckConfig.check("chat")) return;
        if (!plugin.getConfig().getBoolean("chat.join-leave-messages")) return;

        Color color = new Color(252, 3, 3);
        String description = event.getPlayer().getName() + " left the server.";
        sendMessage(event.getPlayer(), color, description);
    }
}

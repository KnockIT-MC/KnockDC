package io.github.keishispl.knockDC.discord;

import io.github.keishispl.knockDC.Logger;
import io.github.keishispl.knockDC.utils.Config;
import io.github.keishispl.knockDC.utils.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DiscordMessageEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!Config.get().getBoolean("receive.enabled") || !Config.check("receive")) return;

        if (Config.get().getStringList("receive.channels").contains(event.getChannel().getId())) {
            if (event.getAuthor().isBot()) return;

            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                Message.sendClear(player, Config.get().getString("prefix") + " " + event.getAuthor().getName() + "&7: &f" + event.getMessage().getContentDisplay());
            }
            Logger.info("&f" + event.getAuthor().getName() + "&7: &f" + event.getMessage().getContentDisplay());
        }
    }
}

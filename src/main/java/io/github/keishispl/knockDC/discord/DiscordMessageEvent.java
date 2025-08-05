package io.github.keishispl.knockDC.discord;

import io.github.keishispl.knockDC.KnockDC;
import io.github.keishispl.knockDC.Logger;
import io.github.keishispl.knockDC.utils.CheckConfig;
import io.github.keishispl.knockDC.utils.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.entity.Player;

public class DiscordMessageEvent extends ListenerAdapter {
    private KnockDC plugin = KnockDC.getPlugin();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!plugin.getConfig().getBoolean("receive.enabled") || !CheckConfig.check("receive")) return;

        if (plugin.getConfig().getStringList("receive.channels").contains(event.getChannel().getId())) {
            if (event.getAuthor().isBot()) return;

            for (Player player : plugin.getServer().getOnlinePlayers()) {
                Message.sendClear(player, plugin.getConfig().getString("prefix") + " " + event.getAuthor().getName() + "&7: &f" + event.getMessage().getContentDisplay());
            }
            Logger.info("&f" + event.getAuthor().getName() + "&7: &f" + event.getMessage().getContentDisplay());
        }
    }
}

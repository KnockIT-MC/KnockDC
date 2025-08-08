package io.github.keishispl.knockDC.utils;

import io.github.keishispl.knockDC.KnockDC;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import java.util.Objects;

import static io.github.keishispl.knockDC.KnockDC.latest_version;

@SuppressWarnings("deprecation")
public class UpdateChecker implements Listener {
    public static boolean updateCheck(CommandSender p){
        latest_version = getVersion.latest();
        if (!Objects.equals(latest_version, KnockDC.getPlugin().getDescription().getVersion())) {

            Message.send(p, "&fThere is a new update! &9" + KnockDC.getPlugin().getDescription().getVersion() + "&8 -> &b" + latest_version);

            TextComponent prefix = new Component(Config.get().getString("prefix") + " ").create();

            TextComponent messageModrinth = new Component()
                    .setText("&ahttps://modrinth.com/plugin/knockdc/version/" + latest_version)
                    .setHover("&fClick to update!")
                    .setClick("https://modrinth.com/plugin/knockdc/version/" + latest_version)
                    .create();
            Message.spigot(p, prefix, new Component("&fModrinth: ").create(), messageModrinth);

            TextComponent messageGithub = new Component()
                    .setText("&bhttps://github.com/KnockIT-MC/KnockDC/releases/tag/" + latest_version)
                    .setHover("&fClick to update!")
                    .setClick("https://github.com/KnockIT-MC/KnockDC/releases/tag/" + latest_version)
                    .create();
            Message.spigot(p, prefix, new Component("&fGithub: ").create(), messageGithub);

            return true;
        }
        return false;
    }
}
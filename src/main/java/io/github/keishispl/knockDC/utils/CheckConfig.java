package io.github.keishispl.knockDC.utils;

import io.github.keishispl.knockDC.KnockDC;
import io.github.keishispl.knockDC.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckConfig {
    private static KnockDC plugin = KnockDC.getPlugin();

    public static void withMessage(CommandSender sender) {
        if (!check("token")) {
            Logger.info(LangConfig.get("plugin.error.token"));
            if (sender instanceof Player) {
                Message.send(sender, LangConfig.get("plugin.error.token"));
            }
            return;
        }
        if (!check("chat")) {
            Logger.info(LangConfig.get("plugin.error.chat"));
            if (sender instanceof Player) {
                Message.send(sender, LangConfig.get("plugin.error.chat"));
            }
        }
        if (!check("receive")) {
            Logger.info(LangConfig.get("plugin.error.receive"));
            if (sender instanceof Player) {
                Message.send(sender, LangConfig.get("plugin.error.receive"));
            }
        }
    }

    public static Boolean check(String type) {
        if (type.equalsIgnoreCase("token")) {
            if (plugin.getConfig().getString("token") == null || plugin.getConfig().getString("token").isEmpty()) {
                return false;
            }
            return true;
        } else {
            if (plugin.getConfig().getBoolean(type + ".enabled") && plugin.getConfig().getStringList(type + ".channels").isEmpty()) {
                return false;
            }
            return true;
        }
    }
}

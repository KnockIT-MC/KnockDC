package io.github.keishispl.knockDC.utils;

import io.github.keishispl.knockDC.KnockDC;
import io.github.keishispl.knockDC.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Config {
    private static KnockDC plugin = KnockDC.getPlugin();

    public static void checkWithMessage(CommandSender sender) {
        if (!check("token")) {
            Logger.info(getLang("plugin.error.token"));
            if (sender instanceof Player) {
                Message.send(sender, getLang("plugin.error.token"));
            }
            return;
        }

        ArrayList<String> list = new ArrayList<>();
        list.add("chat");
        list.add("entry");
        list.add("receive");

        for (String type : list) {
            if (!check(type)) {
                Logger.info(getLang("plugin.error." + type));
                if (sender instanceof Player) {
                    Message.send(sender, getLang("plugin.error." + type));
                }
            }
        }
    }

    public static Boolean check(String type) {
        if (type.equalsIgnoreCase("token")) {
            if (plugin.getConfig().getString("token") == null || plugin.getConfig().getString("token").isEmpty()) {
                return false;
            }
            return true;
        } else if (type.startsWith("entry.config.")) {
            if (plugin.getConfig().getBoolean(type + ".enabled") && (plugin.getConfig().getString(type + ".message") == null || plugin.getConfig().getString(type + ".message").isEmpty())) {
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

    public static FileConfiguration get() {
        return plugin.getConfig();
    }

    public static String getLang(String key) {
        return plugin.getLangConfig().getString(key);
    }

    public static String getLang(String key, String[] args) {
        String message = getLang(key);
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                while (message.contains("{" + i + "}")) {
                    message = message.replace("{" + i + "}", args[i]);
                }
            }
        }
        return message;
    }
}

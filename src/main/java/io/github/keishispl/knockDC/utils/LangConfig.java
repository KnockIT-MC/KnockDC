package io.github.keishispl.knockDC.utils;

import io.github.keishispl.knockDC.KnockDC;

public class LangConfig {
    // LangConfig.get("");
    public static String get(String key) {
        return KnockDC.getPlugin().getLangConfig().getString(key);
    }

    // LangConfig.get("", new String[]{});
    public static String get(String key, String[] args) {
        String message = get(key);
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

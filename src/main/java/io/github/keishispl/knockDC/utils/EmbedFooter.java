package io.github.keishispl.knockDC.utils;

public class EmbedFooter {
    public static String get() {
        String footer = Config.get().getString("embed-footer");
        if (footer == null || footer.isEmpty()) {
            return "";
        } else {
            return footer;
        }
    }
}

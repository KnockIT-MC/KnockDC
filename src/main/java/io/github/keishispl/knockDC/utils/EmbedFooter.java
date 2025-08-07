package io.github.keishispl.knockDC.utils;

import io.github.keishispl.knockDC.KnockDC;

public class EmbedFooter {
    public static String get() {
        String footer = KnockDC.getPlugin().getConfig().getString("embed-footer");
        if (footer == null || footer.isEmpty()) {
            return "";
        } else {
            return footer;
        }
    }
}

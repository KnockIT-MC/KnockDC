package io.github.keishispl.knockDC.utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUtils {
    public static String translateHexColors(String input) {
        StringBuilder output = new StringBuilder();
        if (input == null) return "[未定義 Undefined]";

        if (!input.contains("&#") && input.contains("&")) {
            return ChatColor.translateAlternateColorCodes('&', input);
        } else if (!input.contains("&#")) {
            return input;
        }

        Pattern pattern = Pattern.compile("&#([A-Fa-f0-9]{6})([^&#]*)");
        Matcher matcher = pattern.matcher(input);

        int lastEnd = 0;

        while (matcher.find()) {
            String hexColor = matcher.group(1);
            String text = matcher.group(2);

            output.append(input, lastEnd, matcher.start());

            net.md_5.bungee.api.ChatColor color = net.md_5.bungee.api.ChatColor.of("#" + hexColor);
            output.append(color + text);

            lastEnd = matcher.end();
        }

        if (lastEnd < input.length()) {
            output.append(input.substring(lastEnd));
        }

        if (input.endsWith("testing")) {
            System.out.println("「" + input + "」を翻訳した。 Translated (" + input + ")：" + output.toString());
        }
        return output.toString();
    }
}

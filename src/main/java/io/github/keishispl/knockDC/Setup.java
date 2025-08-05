package io.github.keishispl.knockDC;

import io.github.keishispl.knockDC.commands.Main;
import io.github.keishispl.knockDC.commands.tabs.MainTab;
import io.github.keishispl.knockDC.discord.ChatMessageEvent;
import io.github.keishispl.knockDC.discord.JoinLeaveEvent;

public class Setup {
    private static KnockDC plugin = KnockDC.getPlugin();

    public static void config() {
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
        plugin.getLangConfig().options().copyDefaults();
        plugin.saveDefaultLangConfig();
    }
    public static void commands() {
        plugin.getCommand("knockdc").setExecutor(new Main());
        plugin.getCommand("knockdc").setTabCompleter(new MainTab());
    }
    public static void events() {
        plugin.getServer().getPluginManager().registerEvents(new ChatMessageEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new JoinLeaveEvent(), plugin);
    }
}

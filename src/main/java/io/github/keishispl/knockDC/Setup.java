package io.github.keishispl.knockDC;

import io.github.keishispl.knockDC.commands.Discord;
import io.github.keishispl.knockDC.commands.Main;
import io.github.keishispl.knockDC.commands.tabs.MainTab;
import io.github.keishispl.knockDC.discord.ChatMessageEvent;
import io.github.keishispl.knockDC.discord.JoinLeaveEvent;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;

public class Setup {
    private static KnockDC plugin = KnockDC.getPlugin();

    private static void registerCommand(String command, CommandExecutor executor) {
        plugin.getCommand(command).setExecutor(executor);
    }

    private static void registerCommand(String command, CommandExecutor executor, TabCompleter completer) {
        registerCommand(command, executor);
        plugin.getCommand(command).setTabCompleter(completer);
    }

    private static void registerEvent(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }


    public static void config() {
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
        plugin.getLangConfig().options().copyDefaults();
        plugin.saveDefaultLangConfig();
    }

    public static void commands() {
        registerCommand("knockdc", new Main(), new MainTab());
        registerCommand("discord", new Discord());
    }

    public static void events() {
        registerEvent(new ChatMessageEvent());
        registerEvent(new JoinLeaveEvent());
    }
}

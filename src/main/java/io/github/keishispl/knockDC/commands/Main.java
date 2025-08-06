package io.github.keishispl.knockDC.commands;

import io.github.keishispl.knockDC.KnockDC;
import io.github.keishispl.knockDC.utils.CheckConfig;
import io.github.keishispl.knockDC.utils.LangConfig;
import io.github.keishispl.knockDC.utils.Message;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Main implements CommandExecutor {
    public static void init(CommandSender sender, String command, String[] args) {
        if (command.equalsIgnoreCase("reload")) {
            KnockDC.getPlugin().reloadConfig();
            CheckConfig.withMessage(sender);
            KnockDC.getPlugin().getJDA().shutdownNow();
            KnockDC.getPlugin().buildJDA();
            Message.send(sender, LangConfig.get("plugin.reload"));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            init(sender, args[0], args);
        }
        return true;
    }
}

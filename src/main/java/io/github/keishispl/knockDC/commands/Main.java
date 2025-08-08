package io.github.keishispl.knockDC.commands;

import io.github.keishispl.knockDC.KnockDC;
import io.github.keishispl.knockDC.utils.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static io.github.keishispl.knockDC.utils.UpdateChecker.updateCheck;

public class Main implements CommandExecutor {
    public static void init(CommandSender sender, String command, String[] args) {
        if (command.equalsIgnoreCase("reload")) {
            KnockDC.getPlugin().reloadConfig();
            Config.checkWithMessage(sender);
            KnockDC.getPlugin().getJDA().shutdownNow();
            KnockDC.getPlugin().buildJDA();
            Message.send(sender, Config.getLang("plugin.reload"));
        }
        if (command.equalsIgnoreCase("update")) {
            String v = getVersion.latest();
            if (v.equals(KnockDC.getPlugin().getDescription().getVersion())){
                Message.send(sender, "You are up to date!");
            } else{
                updateCheck(sender);
            }
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

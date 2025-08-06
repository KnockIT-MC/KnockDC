package io.github.keishispl.knockDC;

import com.google.common.base.Charsets;
import io.github.keishispl.knockDC.discord.DiscordMessageEvent;
import io.github.keishispl.knockDC.utils.CheckConfig;
import io.github.keishispl.knockDC.utils.LangConfig;
import io.github.keishispl.knockDC.utils.UpdateChecker;
import io.github.keishispl.knockDC.utils.getVersion;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EnumSet;

public final class KnockDC extends JavaPlugin {
    public static KnockDC plugin;
    public static String latest_version;
    private JDA jda;
    private FileConfiguration newLangConfig = null;
    private File langConfigFile = null;

    @Override
    public void onEnable() {
        langConfigFile = new File(getDataFolder(), "lang.yml");
        plugin = this;
        Setup.config();
        Setup.commands();
        Setup.events();
        CheckConfig.withMessage(null);
        buildJDA();

        Logger.info(LangConfig.get("plugin.enable"));
        UpdateChecker.updateCheck(Bukkit.getConsoleSender());
        plugin.getJDA().getPresence().setActivity(Activity.watching(plugin.getServer().getOnlinePlayers().size() + " players"));
    }

    public void buildJDA() {
        try {
            if (CheckConfig.check("token")) {
                jda = JDABuilder.create(plugin.getConfig().getString("token"), EnumSet.of(
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT
                    )).disableCache(CacheFlag.ACTIVITY, CacheFlag.VOICE_STATE, CacheFlag.EMOJI, CacheFlag.STICKER, CacheFlag.CLIENT_STATUS, CacheFlag.ONLINE_STATUS, CacheFlag.SCHEDULED_EVENTS)
                        .setStatus(OnlineStatus.IDLE)
                        .addEventListeners(new DiscordMessageEvent())
                        .build();
            }
        } catch (IllegalArgumentException e) {

        }
    }

    public JDA getJDA() {
        return jda;
    }

    @Override
    public void onDisable() {
        Logger.info(LangConfig.get("plugin.disable"));
        jda.shutdownNow();
        getServer().getScheduler().cancelTasks(plugin);
    }

    public static KnockDC getPlugin() {
        return plugin;
    }

    public FileConfiguration getLangConfig() {
        if (newLangConfig == null) {
            reloadLangConfig();
        }
        return newLangConfig;
    }

    public void reloadLangConfig() {
        newLangConfig = YamlConfiguration.loadConfiguration(langConfigFile);

        final InputStream defConfigStream = getResource("lang.yml");
        if (defConfigStream == null) {
            return;
        }

        newLangConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
    }

    public void saveLangConfig() {
        try {
            getLangConfig().save(langConfigFile);
        } catch (IOException ex) {
            Logger.error("Could not save config to " + langConfigFile);
        }
    }

    public void saveDefaultLangConfig() {
        if (!langConfigFile.exists()) {
            saveResource("lang.yml", false);
        }
    }
}

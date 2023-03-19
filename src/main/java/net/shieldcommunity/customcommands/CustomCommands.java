package net.shieldcommunity.customcommands;

import net.shieldcommunity.customcommands.commands.PluginCommand;
import net.shieldcommunity.customcommands.commands.CustomCommand;
import net.shieldcommunity.customcommands.utils.PluginUtils;
import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.SlimePluginInformation;
import dev.mruniverse.slimelib.loader.BaseSlimeLoader;
import dev.mruniverse.slimelib.loader.DefaultSlimeLoader;
import dev.mruniverse.slimelib.logs.SlimeLogger;
import dev.mruniverse.slimelib.logs.SlimeLogs;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class CustomCommands extends JavaPlugin implements SlimePlugin<JavaPlugin> {

    private final SlimePluginInformation information = new SlimePluginInformation(
            getServerType(),
            this
    );

    private final DefaultSlimeLoader<JavaPlugin> loader = new DefaultSlimeLoader<>(
            this
    );

    private final List<CustomCommand> commandList = new ArrayList<>();

    private final SlimeLogs logs = SlimeLogger.createLogs(
            getServerType(),
            this
    );

    private boolean hasPAPI;

    @Override
    public void onEnable() {
        PluginUtils.setupLogger(
                logs
        );

        loader.setFiles(SlimeFile.class);

        loader.init();

        int pId = 0;

        for (String id : getConfigurationHandler(SlimeFile.COMMANDS).getContent("commands", false)) {
            commandList.add(
                    new CustomCommand(
                            this,
                            id,
                            "sls" + pId
                    )
            );
            pId++;
        }

        for (CustomCommand command : commandList) {
            getCommands().register(
                    command
            );
        }

        getCommands().register(
                new PluginCommand(
                        this
                )
        );

        hasPAPI = getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");

        new Metrics(this, 16767);
    }

    public boolean hasPlaceholdersAPI() {
        return hasPAPI;
    }

    @Override
    public void onDisable() {
        logs.info("Disabling plugin, thanks for using it!");
    }

    @Override
    public SlimePluginInformation getPluginInformation() {
        return information;
    }

    @Override
    public BaseSlimeLoader<JavaPlugin> getLoader() {
        return loader;
    }

    @Override
    public SlimeLogs getLogs() {
        return logs;
    }

    @Override
    public JavaPlugin getPlugin() {
        return this;
    }

    @Override
    public void reload() {
        loader.reload();
    }
}

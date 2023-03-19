package net.shieldcommunity.customcommands.commands;

import net.shieldcommunity.customcommands.CustomCommands;
import dev.mruniverse.slimelib.commands.command.Command;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.source.SlimeSource;

@Command(
        description = "Plugin reload command",
        shortDescription = "Reload command"
)
public class PluginCommand implements SlimeCommand {
    private final CustomCommands plugin;

    public PluginCommand(CustomCommands plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getCommand() {
        return "MyCustomCommands";
    }

    @Override
    public void execute(SlimeSource sender, String commandLabel, String[] args) {
        if (sender.hasPermission("customcommands.reload") || sender.hasPermission("customcommands.admin") || sender.hasPermission("customcommands.*")) {
            long current = System.currentTimeMillis();

            plugin.reload();

            sender.sendColoredMessage(
                    "&aPlugin has been reloaded in " + (System.currentTimeMillis() - current) + "ms."
            );
        } else {
            sender.sendColoredMessage(
                    "&aMyCustomCommands created by JustJustin v" + plugin.getDescription().getVersion()
            );
        }
    }
}

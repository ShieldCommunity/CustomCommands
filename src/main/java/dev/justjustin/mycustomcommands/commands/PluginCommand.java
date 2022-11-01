package dev.justjustin.mycustomcommands.commands;

import dev.justjustin.mycustomcommands.MyCustomCommands;
import dev.mruniverse.slimelib.commands.command.Command;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.source.SlimeSource;

@Command(
        description = "Plugin reload command",
        shortDescription = "Reload command"
)
public class PluginCommand implements SlimeCommand {
    private final MyCustomCommands plugin;

    public PluginCommand(MyCustomCommands plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getCommand() {
        return "MyCustomCommands";
    }

    @Override
    public void execute(SlimeSource sender, String commandLabel, String[] args) {
        if (sender.hasPermission("mycustomcommands.reload") || sender.hasPermission("mycustomcommands.admin") || sender.hasPermission("mycustomcommands.*")) {
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

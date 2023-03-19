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
        return "CustomCommands";
    }

    @Override
    public void execute(SlimeSource sender, String commandLabel, String[] args) {

        if (!(sender.hasPermission("customcommands.admin"))) {
            sender.sendColoredMessage("&cYou don't have permissions for this command.");
            return;
        }

        plugin.reload();

        sender.sendColoredMessage("&aPlugin has been successfully reloaded");
    }
}

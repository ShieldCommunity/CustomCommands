package net.shieldcommunity.customcommands.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import net.shieldcommunity.customcommands.SlimeFile;
import net.shieldcommunity.customcommands.CustomCommands;
import dev.mruniverse.slimelib.commands.command.Command;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.file.configuration.TextDecoration;
import dev.mruniverse.slimelib.source.SlimeSource;
import dev.mruniverse.slimelib.source.player.SlimePlayer;
import org.bukkit.entity.Player;

@Command(
        shortDescription = "Social media command",
        description = "A simple social media command"
)
public class CustomCommand implements SlimeCommand {
    private final CustomCommands plugin;
    private final String command;
    private final String id;

    public CustomCommand(CustomCommands plugin, String id, String defaultCommand) {
        this.command = plugin.getConfigurationHandler(SlimeFile.COMMANDS).getString("commands." + id + ".command", defaultCommand);
        this.plugin = plugin;
        this.id = id;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public void execute(SlimeSource sender, String commandLabel, String[] args) {
        String permission = plugin.getConfigurationHandler(SlimeFile.COMMANDS).getString("commands." + id + ".permission", "");

        if (!permission.isEmpty()) {
            if (!sender.hasPermission(permission)) {
                sender.sendMessage(
                        plugin.getConfigurationHandler(SlimeFile.COMMANDS).getString(
                                TextDecoration.LEGACY,
                                "commands." + id + ".permission-error",
                                "&cYou don't have permissions for this command."
                        )
                );
                return;
            }
        }

        if (plugin.hasPlaceholdersAPI() && sender.isPlayer()) {
            Player player = ((SlimePlayer)sender).getOriginalSource();

            for (String message : plugin.getConfigurationHandler(SlimeFile.COMMANDS).getStringList(
                    TextDecoration.LEGACY,
                    "commands." + id + ".result"
            )) {
                sender.sendMessage(
                        replacePlaceholders(
                                player,
                                message.replace("%player%", sender.getName())
                        )
                );
            }
            return;
        }

        for (String message : plugin.getConfigurationHandler(SlimeFile.COMMANDS).getStringList(
                TextDecoration.LEGACY,
                "commands." + id + ".result"
        )) {
            sender.sendMessage(
                    message.replace("%player%", sender.getName())
            );
        }
    }

    public static String replacePlaceholders(Player player, String message) {
        return PlaceholderAPI.setPlaceholders(
                player,
                message
        );
    }
}

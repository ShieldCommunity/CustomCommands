package net.shieldcommunity.customcommands.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class Placeholders {


    public static String replacePlaceholders(Player player, String message) {
        return PlaceholderAPI.setPlaceholders(
                player,
                message
        );
    }

}

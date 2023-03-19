package net.shieldcommunity.customcommands.utils;

import dev.mruniverse.slimelib.logs.SlimeLogs;

public class PluginUtils {

    public static void setupLogger(SlimeLogs logs) {
        logs.getPrefixes().getDebug().setPrefix("&aCustomCommands | &f");
        logs.getPrefixes().getInfo().setPrefix("&5CustomCommands | &f");
        logs.getPrefixes().getWarn().setPrefix("&eCustomCommands | &f");
        logs.getPrefixes().getIssue().setPrefix("&6CustomCommands | &f");

        logs.getProperties().getExceptionProperties().CODE_COLOR = "&e";
        logs.getProperties().getExceptionProperties().BASE_COLOR = "&e";

        logs.getSlimeLogger().setContainIdentifier("net.shieldcommunity.customcommands");
        logs.getSlimeLogger().setHidePackage("net.shieldcommunity.customcommands");
    }

}

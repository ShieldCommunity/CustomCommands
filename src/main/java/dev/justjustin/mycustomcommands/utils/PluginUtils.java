package dev.justjustin.mycustomcommands.utils;

import dev.mruniverse.slimelib.logs.SlimeLogs;

public class PluginUtils {

    public static void setupLogger(SlimeLogs logs) {
        logs.getPrefixes().getDebug().setPrefix("&aMyCustomCommands | &f");
        logs.getPrefixes().getInfo().setPrefix("&5MyCustomCommands | &f");
        logs.getPrefixes().getWarn().setPrefix("&eMyCustomCommands | &f");
        logs.getPrefixes().getIssue().setPrefix("&6MyCustomCommands | &f");

        logs.getProperties().getExceptionProperties().CODE_COLOR = "&e";
        logs.getProperties().getExceptionProperties().BASE_COLOR = "&e";

        logs.getSlimeLogger().setContainIdentifier("dev.justjustin.mycustomcommands");
        logs.getSlimeLogger().setHidePackage("dev.justjustin.mycustomcommands");
    }

}

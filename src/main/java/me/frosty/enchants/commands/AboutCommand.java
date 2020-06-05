package me.frosty.enchants.commands;

import com.codeitforyou.lib.api.command.Command;
import com.codeitforyou.lib.api.general.StringUtil;
import me.frosty.enchants.Enchants;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class AboutCommand
{

    @Command()
    public static void execute(final CommandSender sender, final Enchants plugin, final String[] args)
    {
        final FileConfiguration config = plugin.getConfig();

        for (String message : config.getStringList("about-message"))
        {
            message = message.replace("{build}", plugin.getDescription().getVersion());

            sender.sendMessage(StringUtil.translate(message));
        }
    }
}

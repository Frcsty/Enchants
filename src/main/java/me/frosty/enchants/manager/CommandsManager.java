package me.frosty.enchants.manager;

import com.codeitforyou.lib.api.command.CommandManager;
import com.codeitforyou.lib.api.general.StringUtil;
import me.frosty.enchants.Enchants;
import me.frosty.enchants.commands.AboutCommand;
import me.frosty.enchants.commands.GiveCommand;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;

public class CommandsManager
{

    private final Enchants plugin;
    private final FileConfiguration config;
    private final CommandManager manager;
    private final CommandManager.Locale locale;

    public CommandsManager(final Enchants plugin)
    {
        this.plugin = plugin;
        this.config = plugin.getConfig();

        this.manager = new CommandManager(Arrays.asList(
                GiveCommand.class
        ), this.config.getString("settings.base-command"), plugin);

        this.locale = manager.getLocale();
    }

    public void registerCommand()
    {
        // Assign attributes to before created Command Manager
        this.createCommandAttributes();

        // Register Command Manager after all attributes have been applied
        this.manager.register();
    }

    private void createCommandAttributes()
    {
        // Set the main plugin command
        this.manager.setMainCommand(AboutCommand.class);

        // Assign command aliases
        for (final String cmd : plugin.getConfig().getStringList("settings.alias"))
        {
            this.manager.addAlias(cmd);
        }

        // Set command messages handled by the libs Command Manager
        this.locale.setUsage(getDefaultMessage(this.config.getString("messages.usages-message")));
        this.locale.setUnknownCommand(getDefaultMessage(this.config.getString("messages.unknown-command-message")));
        this.locale.setPlayerOnly(getDefaultMessage(this.config.getString("messages.player-only-message")));
        this.locale.setNoPermission(getDefaultMessage(this.config.getString("messages.no-permission-message")));
    }

    private String getDefaultMessage(final String message)
    {
        return message == null ? StringUtil.translate("&8[&9Enchants&8] &cNo default message specified in the config!") : StringUtil.translate(message);
    }

    public CommandManager getManager()
    {
        return manager;
    }

}

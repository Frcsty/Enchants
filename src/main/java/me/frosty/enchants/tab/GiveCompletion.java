package me.frosty.enchants.tab;

import me.frosty.enchants.Enchants;
import me.frosty.enchants.enchantments.Enchantments;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GiveCompletion implements TabCompleter
{

    private final Enchants plugin;
    private final Enchantments enchantments;

    public GiveCompletion(final Enchants plugin)
    {
        this.plugin = plugin;
        this.enchantments = plugin.getEnchantments();
    }

    // /enchants give <player> <enchant> [level]

    @Override
    public List<String> onTabComplete(CommandSender s, Command cmd, String alias, String[] args)
    {
        if (args.length == 1)
        {
            final List<String> commands = new ArrayList<>();
            for (final Map.Entry<String, Method> commandPair : plugin.getCommandsManager().getManager().getCommands().entrySet())
            {
                if (commandPair.getKey().toLowerCase().startsWith(args[0].toLowerCase()))
                {
                    commands.add(commandPair.getKey());
                }
            }

            return commands;
        }
        if (args[0].equalsIgnoreCase("give"))
        {
            if (args.length == 2)
            {
                final List<String> players = new ArrayList<>();
                for (Player plr : plugin.getServer().getOnlinePlayers())
                {
                    players.add(plr.getName());
                }

                return players;
            }
            else if (args.length == 3)
            {
                return enchantments.getListedEnchantments();
            }
        }
        return Collections.emptyList();
    }

}

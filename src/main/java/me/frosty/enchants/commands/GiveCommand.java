package me.frosty.enchants.commands;

import com.codeitforyou.lib.api.command.Command;
import com.codeitforyou.lib.api.general.StringUtil;
import com.codeitforyou.lib.api.item.ItemBuilder;
import com.codeitforyou.lib.api.xseries.XMaterial;
import me.frosty.enchants.Enchants;
import me.frosty.enchants.storage.Storage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.logging.Level;

public class GiveCommand
{

    @Command(permission = "enchants.give", aliases = {"give"}, usage = "give <target> (enchantment) [level-optional]", requiredArgs = 2)
    public static void execute(final CommandSender sender, final Enchants plugin, final String[] args)
    {
        final Storage storage = plugin.getStorage();
        final Player target = Bukkit.getPlayerExact(args[0]);
        final String enchantment = args[1];
        final Integer level = args.length < 3 ? 1 : Integer.parseInt(args[2]);

        if (target == null)
        {
            sender.sendMessage(StringUtil.translate(storage.getNoTargetMessage()));
            return;
        }

        if (!target.isOnline())
        {
            sender.sendMessage(StringUtil.translate(storage.getOfflineTargetMessage()));
            return;
        }

        if (plugin.getConfig().getString("enchantments." + enchantment + ".display-name") == null)
        {
            sender.sendMessage(StringUtil.translate(storage.getInvalidEnchantment()));
            return;
        }

        final ConfigurationSection enchantments = plugin.getConfig().getConfigurationSection("enchantments." + enchantment);

        if (enchantments == null)
        {
            plugin.getLogger().log(Level.WARNING, "Configuration section 'enchantments." + enchantment + "' does not exist, could not give enchantment!");
            return;
        }

        String display_name = enchantments.getString("display-name");
        final Material material = XMaterial.valueOf(enchantments.getString("material")).parseMaterial();
        final List<String> lore = StringUtil.translate(enchantments.getStringList("lore"));
        final Integer max_level = enchantments.getInt("max-level");

        if (level > max_level)
        {
            sender.sendMessage(StringUtil.translate(storage.getInvalidEnchantmentLevel()));
            return;
        }

        final int enchant = enchantments.getInt("enchant");

        ItemBuilder itemBuilder = new ItemBuilder(material)
                .withLore(lore)
                .withNBTString("level", String.valueOf(level))
                .withNBTString("id", String.valueOf(enchant))
        ;

        System.out.println("Enchantment: " + enchant);
        System.out.println("Level: " + level);

        if (display_name != null)
        {
            if (display_name.contains("{level}"))
            {
                display_name = display_name.replace("{level}", plugin.getNumberFormat().getFormattedNumber(level));
            }
            itemBuilder.withName(display_name);
        }

        if (target.getInventory().firstEmpty() != -1)
        {
            target.getInventory().addItem(itemBuilder.getItem());
        }
    }

}

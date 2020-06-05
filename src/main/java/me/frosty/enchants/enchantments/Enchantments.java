package me.frosty.enchants.enchantments;

import me.frosty.enchants.Enchants;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Enchantments
{

    private final Enchants plugin;

    public Enchantments(final Enchants plugin)
    {
        this.plugin = plugin;
    }

    public List<String> getListedEnchantments()
    {
        final ConfigurationSection enchantments = plugin.getConfig().getConfigurationSection("enchantments");

        if (enchantments == null)
        {
            plugin.getLogger().log(Level.WARNING, "Configuration section 'enchantments' does not exist, could not list enchants!");
            return null;
        }

        return new ArrayList<>(enchantments.getKeys(false));
    }


}

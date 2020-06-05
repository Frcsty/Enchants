package me.frosty.enchants.enchantments;

import me.frosty.enchants.Enchants;
import me.frosty.enchants.enchantments.custom.NightVisionEnchantment;
import me.frosty.enchants.enchantments.custom.StrengthEnchantment;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class EnchantmentWrapper
{

    private final Enchants plugin;

    private final NightVisionEnchantment nightVisionEnchantment;
    private final StrengthEnchantment    strengthEnchantment;

    public EnchantmentWrapper(final Enchants plugin)
    {
        this.plugin = plugin;

        this.nightVisionEnchantment = new NightVisionEnchantment(plugin, 101);
        this.strengthEnchantment = new StrengthEnchantment(plugin, 102);
    }

    public void loadEnchantments()
    {
        try
        {
            final Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);

            Enchantment.registerEnchantment(nightVisionEnchantment);
            Enchantment.registerEnchantment(strengthEnchantment);
        }
        catch (IllegalAccessException | NoSuchFieldException ex)
        {
            plugin.getLogger().log(Level.WARNING, "Internal error occurred while enabling enchantments", ex);
            return;
        }
        plugin.getServer().getPluginManager().registerEvents(nightVisionEnchantment, plugin);
        plugin.getServer().getPluginManager().registerEvents(strengthEnchantment, plugin);
    }

    @SuppressWarnings("unchecked")
    public void unloadEnchantments()
    {
        try
        {
            final Field keyField = Enchantment.class.getDeclaredField("byKey");
            final Field nameField = Enchantment.class.getDeclaredField("byName");

            keyField.setAccessible(true);
            nameField.setAccessible(true);

            final Map<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);
            final Map<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            byKey.remove(nightVisionEnchantment.getKey());
            byName.remove(nightVisionEnchantment.getName());
        }
        catch (IllegalAccessException | NoSuchFieldException ex)
        {
            plugin.getLogger().log(Level.WARNING, "Failed to unload enchantments!", ex);
        }
    }

}

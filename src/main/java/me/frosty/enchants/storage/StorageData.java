package me.frosty.enchants.storage;

import me.frosty.enchants.Enchants;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageData
{

    private final Enchants plugin;
    private final Storage  storage;

    public StorageData(final Enchants plugin)
    {
        this.plugin = plugin;
        this.storage = plugin.getStorage();
    }

    public void reloadStorageData()
    {
        final Logger logger = plugin.getLogger();
        final FileConfiguration config = plugin.getConfig();
        final ConfigurationSection messages = config.getConfigurationSection("messages");

        if (messages == null)
        {
            logger.log(Level.WARNING, "Configuration section 'messages' does not exist, reloading unsuccessful!");
            return;
        }

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                storage.setNoTargetMessage(messages.getString("invalid-target-message"));
                storage.setOfflineTargetMessage(messages.getString("offline-target-message"));
                storage.setInvalidEnchantment(messages.getString("invalid-enchantment-message"));
                storage.setInvalidEnchantmentLevel(messages.getString("invalid-enchantment-level-message"));

                storage.setListedEnchantments(plugin.getEnchantments().getListedEnchantments());

                final HashMap<Integer, String> formatter = new HashMap<>();
                for (String format : config.getStringList("level-formatting"))
                {
                    final String[] parts = format.split(";");
                    formatter.put(Integer.parseInt(parts[0]), parts[1]);
                }
                storage.setFormattedLevels(formatter);
            }
        }.runTaskAsynchronously(this.plugin);
    }

}

package me.frosty.enchants;

import me.frosty.enchants.enchantments.EnchantmentWrapper;
import me.frosty.enchants.enchantments.Enchantments;
import me.frosty.enchants.listener.InventoryListener;
import me.frosty.enchants.manager.CommandsManager;
import me.frosty.enchants.storage.Storage;
import me.frosty.enchants.storage.StorageData;
import me.frosty.enchants.tab.GiveCompletion;
import me.frosty.enchants.util.NumberFormat;
import org.bukkit.plugin.java.JavaPlugin;

public final class Enchants extends JavaPlugin
{

    private final CommandsManager    commandsManager    = new CommandsManager(this);
    private final Storage            storage            = new Storage();
    private final StorageData        storageData        = new StorageData(this);
    private final Enchantments       enchantments       = new Enchantments(this);
    private final InventoryListener  inventoryListener  = new InventoryListener(this);
    private final GiveCompletion     giveCompletion     = new GiveCompletion(this);
    private final NumberFormat       numberFormat       = new NumberFormat(this);

    private final EnchantmentWrapper enchantmentWrapper = new EnchantmentWrapper(this);

    @Override
    public void onEnable()
    {
        saveDefaultConfig();
        reloadConfig();

        // Register the plugins commands
        commandsManager.registerCommand();

        // Reload storage data
        storageData.reloadStorageData();

        // Register listener
        getServer().getPluginManager().registerEvents(inventoryListener, this);

        // Register tab completion
        final String command = getConfig().getString("settings.base-command");

        if (command != null)
        {
            getCommand(command).setTabCompleter(giveCompletion);
        }

        enchantmentWrapper.loadEnchantments();
    }

    @Override
    public void onDisable()
    {
        reloadConfig();

        enchantmentWrapper.unloadEnchantments();
    }

    public Storage getStorage()
    {
        return storage;
    }

    public Enchantments getEnchantments()
    {
        return enchantments;
    }

    public CommandsManager getCommandsManager()
    {
        return commandsManager;
    }

    public NumberFormat getNumberFormat()
    {
        return numberFormat;
    }

}

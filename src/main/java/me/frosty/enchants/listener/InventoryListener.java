package me.frosty.enchants.listener;

import com.codeitforyou.lib.api.general.StringUtil;
import com.codeitforyou.lib.api.item.ItemUtil;
import me.frosty.enchants.Enchants;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryListener implements Listener
{

    private final Enchants plugin;

    public InventoryListener(final Enchants plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryDrag(InventoryClickEvent event)
    {
        final ItemStack startingItem = event.getCursor();
        final ItemStack finishedItem = event.getCurrentItem();

        if (startingItem == null)
        {
            return;
        }

        if (ItemUtil.getNBTString(startingItem, "id") == null)
        {
            return;
        }

        if (finishedItem == null)
        {
            return;
        }

        final NamespacedKey key = new NamespacedKey(plugin, ItemUtil.getNBTString(startingItem, "id"));
        Enchantment enchant = Enchantment.getByKey(key);
        final int level = Integer.parseInt(ItemUtil.getNBTString(startingItem, "level"));

        if (enchant != null)
        {
            System.out.println("Enchant is not null");
            if (isEnchantmentCustom(enchant))
            {
                if (enchant.getItemTarget().includes(finishedItem))
                {
                    finishedItem.addEnchantment(enchant, level);

                    final ItemMeta meta = finishedItem.getItemMeta();

                    if (meta != null)
                    {
                        if (meta.hasLore())
                        {
                            final List<String> lore = meta.getLore();

                            if (lore != null && lore.contains(enchant.getName()))
                            {
                                lore.remove(enchant.getName() + " " + finishedItem.getEnchantmentLevel(enchant));
                                System.out.println(enchant.getName() + " " + finishedItem.getEnchantmentLevel(enchant));
                                lore.add(enchant.getName() + " " + plugin.getNumberFormat().getFormattedNumber(level));

                                meta.setLore(lore);
                            }
                        }
                        else
                        {
                            final List<String> lore = new ArrayList<>();

                            lore.add(StringUtil.translate("&7" + enchant.getName() + " " + plugin.getNumberFormat().getFormattedNumber(level)));

                            meta.setLore(lore);
                        }
                        finishedItem.setItemMeta(meta);
                        startingItem.setType(Material.AIR);
                        return;
                    }
                }
            }

            finishedItem.addEnchantment(enchant, level);
            startingItem.setType(Material.AIR);
        }
    }

    private boolean isEnchantmentCustom(final Enchantment enchantment)
    {
        return Arrays.asList("101", "102", "103", "104").contains(enchantment.getKey().getKey());
    }

}

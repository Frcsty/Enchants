package me.frosty.enchants.enchantments.custom;

import com.codeitforyou.lib.api.xseries.XMaterial;
import me.frosty.enchants.Enchants;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class StrengthEnchantment extends Enchantment implements Listener
{
    // Helmet = slot: 40

    private final Enchants plugin;
    private final int      id;

    public StrengthEnchantment(final Enchants plugin, final int id)
    {
        super(new NamespacedKey(plugin, String.valueOf(id)));
        this.plugin = plugin;
        this.id = id;
    }

    @EventHandler
    public void onArmorApply(InventoryClickEvent event)
    {
        if (event.getWhoClicked() instanceof Player)
        {
            final Player player = (Player) event.getWhoClicked();
            final PotionEffectType strength_effect = PotionEffectType.INCREASE_DAMAGE;

            int level = 0;
            if (player.getInventory().getChestplate() != null)
            {
                if (player.getInventory().getChestplate().containsEnchantment(this))
                {
                    if (player.getInventory().getChestplate().getEnchantmentLevel(this) == 2)
                    {
                        level = 1;
                    }
                }
            }
            final PotionEffect strength = strength_effect.createEffect(Integer.MAX_VALUE, level);

            if (event.getSlot() == 40)
            {
                if (player.hasPotionEffect(strength_effect))
                {
                    if (getComparedMaterial(player.getInventory().getChestplate()))
                    {
                        player.addPotionEffect(strength);
                    }
                    else
                    {
                        player.removePotionEffect(strength_effect);
                    }
                }
                else
                {
                    if (getComparedMaterial(player.getInventory().getChestplate()))
                    {
                        player.addPotionEffect(strength);
                    }
                }
            }
            else
            {
                if (getComparedMaterial(player.getInventory().getChestplate()))
                {
                    if (!player.hasPotionEffect(strength_effect))
                    {
                        player.addPotionEffect(strength);
                    }
                }
                else
                {
                    if (player.hasPotionEffect(strength_effect))
                    {
                        player.removePotionEffect(strength_effect);
                    }
                }
            }
        }
    }

    private boolean getComparedMaterial(final ItemStack item)
    {
        if (item == null)
        {
            return false;
        }

        return Arrays.asList(Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE, XMaterial.GOLDEN_CHESTPLATE.parseMaterial(), Material.DIAMOND_CHESTPLATE).contains(item.getType());
    }

    @Override
    public NamespacedKey getKey()
    {
        return new NamespacedKey(this.plugin, String.valueOf(this.id));
    }

    @Override
    public String getName()
    {
        return "Strength";
    }

    @Override
    public int getMaxLevel()
    {
        return 2;
    }

    @Override
    public int getStartLevel()
    {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget()
    {
        return EnchantmentTarget.ARMOR_TORSO;
    }

    @Override
    public boolean isTreasure()
    {
        return false;
    }

    @Override
    public boolean isCursed()
    {
        return false;
    }

    @Override
    public boolean conflictsWith(final Enchantment other)
    {
        return false;
    }

    @Override
    public boolean canEnchantItem(final ItemStack item)
    {
        return true;
    }

}


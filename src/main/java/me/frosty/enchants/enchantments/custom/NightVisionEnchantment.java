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

public class NightVisionEnchantment extends Enchantment implements Listener
{
    // Helmet = slot: 39

    private final Enchants plugin;
    private final int      id;

    public NightVisionEnchantment(final Enchants plugin, final int id)
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
            final PotionEffect night_vision = PotionEffectType.NIGHT_VISION.createEffect(Integer.MAX_VALUE, 0);
            final PotionEffectType night_vision_effect = PotionEffectType.NIGHT_VISION;

            if (event.getSlot() == 39)
            {
                if (player.hasPotionEffect(night_vision_effect))
                {
                    if (getComparedMaterial(player.getInventory().getHelmet()))
                    {
                        player.addPotionEffect(night_vision);
                    }
                    else
                    {
                        player.removePotionEffect(night_vision_effect);
                    }
                }
                else
                {
                    if (getComparedMaterial(player.getInventory().getHelmet()))
                    {
                        player.addPotionEffect(night_vision);
                    }
                }
            }
            else
            {
                if (getComparedMaterial(player.getInventory().getHelmet()))
                {
                    if (!player.hasPotionEffect(night_vision_effect))
                    {
                        player.addPotionEffect(night_vision);
                    }
                }
                else
                {
                    if (player.hasPotionEffect(night_vision_effect))
                    {
                        player.removePotionEffect(night_vision_effect);
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

        return Arrays.asList(Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, XMaterial.GOLDEN_HELMET.parseMaterial(), Material.DIAMOND_HELMET).contains(item.getType());
    }

    @Override
    public NamespacedKey getKey()
    {
        return new NamespacedKey(this.plugin, String.valueOf(this.id));
    }

    @Override
    public String getName()
    {
        return "Night Vision";
    }

    @Override
    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public int getStartLevel()
    {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget()
    {
        return EnchantmentTarget.ARMOR_HEAD;
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

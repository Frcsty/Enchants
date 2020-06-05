package me.frosty.enchants.util;

import me.frosty.enchants.Enchants;

public class NumberFormat
{
    private final Enchants plugin;

    public NumberFormat(final Enchants plugin)
    {
        this.plugin = plugin;
    }

    public String getFormattedNumber(final int number)
    {
        if (number == 0)
        {
            return "Invalid Number!";
        }

        return plugin.getStorage().getFormattedLevels().get(number);
    }

}

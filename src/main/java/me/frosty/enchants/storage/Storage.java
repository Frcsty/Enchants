package me.frosty.enchants.storage;

import java.util.HashMap;
import java.util.List;

public class Storage
{

    private String noTargetMessage;
    public String getNoTargetMessage()
    {
        return noTargetMessage;
    }
    void setNoTargetMessage(final String noTargetMessage)
    {
        this.noTargetMessage = noTargetMessage;
    }

    private String offlineTargetMessage;
    public String getOfflineTargetMessage()
    {
        return offlineTargetMessage;
    }
    void setOfflineTargetMessage(final String offlineTargetMessage)
    {
        this.offlineTargetMessage = offlineTargetMessage;
    }

    private List<String> listedEnchantments;
    public List<String> getListedEnchantments()
    {
        return listedEnchantments;
    }
    void setListedEnchantments(final List<String> listedEnchantments)
    {
        this.listedEnchantments = listedEnchantments;
    }

    private String invalidEnchantment;
    public String getInvalidEnchantment()
    {
        return invalidEnchantment;
    }
    void setInvalidEnchantment(final String invalidEnchantment)
    {
        this.invalidEnchantment = invalidEnchantment;
    }

    private String invalidEnchantmentLevel;
    public String getInvalidEnchantmentLevel()
    {
        return invalidEnchantmentLevel;
    }
    void setInvalidEnchantmentLevel(final String invalidEnchantmentLevel)
    {
        this.invalidEnchantmentLevel = invalidEnchantmentLevel;
    }

    private HashMap<Integer, String> formattedLevels;
    public HashMap<Integer, String> getFormattedLevels()
    {
        return formattedLevels;
    }
    void setFormattedLevels(final HashMap<Integer, String> formattedLevels)
    {
        this.formattedLevels = formattedLevels;
    }

}

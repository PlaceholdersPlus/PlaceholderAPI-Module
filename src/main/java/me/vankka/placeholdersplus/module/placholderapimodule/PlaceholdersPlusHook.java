package me.vankka.placeholdersplus.module.placholderapimodule;

import me.clip.placeholderapi.PlaceholderHook;
import me.vankka.placeholdersplus.common.IPlaceholderHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlaceholdersPlusHook extends PlaceholderHook {

    private final IPlaceholderHook placeholderHook;

    PlaceholdersPlusHook(IPlaceholderHook placeholderHook) {
        this.placeholderHook = placeholderHook;
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        return placeholderHook.getPlaceholderReplacement(identifier, player).getReplacement();
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        return placeholderHook.getPlaceholderReplacement(identifier, player).getReplacement();
    }
}

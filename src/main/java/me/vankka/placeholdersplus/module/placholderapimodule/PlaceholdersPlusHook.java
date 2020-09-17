package me.vankka.placeholdersplus.module.placholderapimodule;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.vankka.placeholdersplus.common.IPlaceholderHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholdersPlusHook extends PlaceholderExpansion {

    private final IPlaceholderHook placeholderHook;

    PlaceholdersPlusHook(IPlaceholderHook placeholderHook) {
        this.placeholderHook = placeholderHook;

    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        return placeholderHook.getPlaceholderReplacement(identifier, player).getReplacement();
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String identifier) {
        return placeholderHook.getPlaceholderReplacement(identifier, player).getReplacement();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "placeholdersplus";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Vankka";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.1";
    }
}

package me.vankka.placeholdersplus.module.placholderapimodule;

import me.clip.placeholderapi.PlaceholderAPI;
import me.vankka.placeholdersplus.common.model.PlaceholderLookupResult;
import me.vankka.placeholdersplus.common.model.PlaceholderReplacer;
import me.vankka.placeholdersplus.common.model.PlaceholdersPlusModule;
import me.vankka.placeholdersplus.common.model.PlaceholdersPlusPlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class PlaceholderAPIModule extends PlaceholdersPlusModule implements PlaceholderReplacer {

    @Override
    public void enable(PlaceholdersPlusPlugin plugin) {
        placeholderHook.addPlaceholderReplacers(this);

        PlaceholderAPI.registerPlaceholderHook("placeholdersplus", new PlaceholdersPlusHook(placeholderHook));
    }

    @Override
    public void disable(PlaceholdersPlusPlugin plugin) {
        placeholderHook.removePlaceholderReplacers(this);

        PlaceholderAPI.unregisterPlaceholderHook("placeholdersplus");
    }

    @Override
    public PlaceholderLookupResult lookup(String placeholder, List<Object> extraObjects) {
        if (!placeholder.startsWith("placeholderapi_"))
            return new PlaceholderLookupResult(PlaceholderLookupResult.ResultType.UNKNOWN_PLACEHOLDER);

        placeholder = placeholder.replace("placeholderapi_", "");

        Player player = extraObjects.stream()
                .filter(object -> object instanceof Player)
                .map(object -> (Player) object)
                .findAny().orElse(null);
        if (player != null)
            return output(PlaceholderAPI.setPlaceholders(player, "%" + placeholder + "%"));

        OfflinePlayer offlinePlayer = extraObjects.stream()
                .filter(object -> object instanceof OfflinePlayer)
                .map(object -> (OfflinePlayer) object)
                .findAny().orElse(null);
        if (offlinePlayer != null)
            return output(PlaceholderAPI.setPlaceholders(offlinePlayer, "%" + placeholder + "%"));

        return output(PlaceholderAPI.setPlaceholders(null, "%" + placeholder + "%"));
    }

    private PlaceholderLookupResult output(String output) {
        if (output == null)
            return new PlaceholderLookupResult(PlaceholderLookupResult.ResultType.UNKNOWN_PLACEHOLDER);
        else if (output.isEmpty())
            return new PlaceholderLookupResult(PlaceholderLookupResult.ResultType.DATA_NOT_LOADED);
        else
            return new PlaceholderLookupResult(output);
    }
}

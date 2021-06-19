package ga.uniquecoding.uniquelevels.files;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class DataFile extends ga.uniquecoding.uniquecore.manager.DataFile {

    public DataFile(File directory) {
        super(directory, "data");
    }

    public int getXPCount(Player player) {
        return player.getTotalExperience();
    }

    private ConfigurationSection getLevels(Player player) {
        String playerId = player.getUniqueId().toString();
        ConfigurationSection levels = data.getConfigurationSection(playerId);

        return Optional.ofNullable(levels)
                .orElseGet(() -> data.createSection(playerId));
    }
}
package ga.uniquecoding.uniquelevels.events;

import ga.uniquecoding.uniquelevels.UniqueLevels;
import ga.uniquecoding.uniquelevels.files.DataFile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerInitializer implements Listener {

    private DataFile dataFile;

    public PlayerInitializer(UniqueLevels uniqueLevels, DataFile dataFile) {
        this.dataFile = dataFile;
    }

    @EventHandler
    public void RegisterPlayer(PlayerLoginEvent event) {
        var player = event.getPlayer();

        if (!(player.hasPlayedBefore())) {
            dataFile.getDataFile().getConfigurationSection("").createSection(String.valueOf(player.getUniqueId()));
        }
    }
}

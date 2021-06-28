package ga.uniquecoding.uniquelevels.events;

import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.IOException;

public class PlayerInitializer implements Listener {

    private PlayerDataFile playerDataFile;

    public PlayerInitializer(PlayerDataFile playerDataFile) {
        this.playerDataFile = playerDataFile;
    }

    @EventHandler
    public void RegisterPlayer(PlayerLoginEvent event) throws IOException {
        var player = event.getPlayer();

        if (player.hasPlayedBefore()) {
            playerDataFile.createPlayerData(player.getUniqueId(), player.getPlayer());
        }
    }
}

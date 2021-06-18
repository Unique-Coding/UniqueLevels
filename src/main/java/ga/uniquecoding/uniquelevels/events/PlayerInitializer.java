package ga.uniquecoding.uniquelevels.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerInitializer implements Listener {

    @EventHandler
    public void RegisterPlayer (PlayerLoginEvent event) {
        var player = event.getPlayer();

        if (!(player.hasPlayedBefore())) {

        }
    }
}

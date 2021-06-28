package ga.uniquecoding.uniquelevels.events;

import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerInitializer implements Listener
{

	private final PlayerDataFile playerDataFile;

	public PlayerInitializer(PlayerDataFile playerDataFile)
	{
		this.playerDataFile = playerDataFile;
	}

	@EventHandler
	public void RegisterPlayer(PlayerLoginEvent event)
	{
		var player = event.getPlayer();

		if (player.hasPlayedBefore())
		{
			playerDataFile.createPlayerData(player.getUniqueId(), player.getPlayer());
		}
	}
}

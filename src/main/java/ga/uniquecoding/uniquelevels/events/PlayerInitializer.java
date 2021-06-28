package ga.uniquecoding.uniquelevels.events;

import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.IOException;

public class PlayerInitializer implements Listener
{
	private final PlayerDataFile dataFile;

	public PlayerInitializer(PlayerDataFile dataFile)
	{
		this.dataFile = dataFile;
	}

	@EventHandler
	public void onPlayerJoin(PlayerLoginEvent event) throws IOException
	{
		var player = event.getPlayer();

		if (!player.hasPlayedBefore())
		{
			dataFile.createPlayerData(player);
			dataFile.flush();
		}
	}
}

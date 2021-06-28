package ga.uniquecoding.uniquelevels;

import ga.uniquecoding.uniquelevels.events.PlayerInitializer;
import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class UniqueLevels extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		try
		{
			var dataPath = getDataFolder().toPath();
			var playerFile = new PlayerDataFile(dataPath);

			getServer().getPluginManager()
					   .registerEvents(
							   new PlayerInitializer(playerFile), this
					   );

			this.saveDefaultConfig();
			this.reloadConfig();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

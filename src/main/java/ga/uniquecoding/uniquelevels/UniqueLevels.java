package ga.uniquecoding.uniquelevels;

import ga.uniquecoding.uniquelevels.events.PlayerInitializer;
import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;


public final class UniqueLevels extends JavaPlugin
{

	private PlayerDataFile playerDataFile;

	@Override
	public void onEnable()
	{
		var folder = new File(getDataFolder(), "data");

		if (!(folder.exists()))
		{
			folder.mkdir();
		}

		File file = new File(folder, "playerdata.yml");

		if (!(file.exists()))
		{
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		this.registerEvents();
		this.saveDefaultConfig();
		this.reloadConfig();
	}

	public void registerEvents()
	{
		getServer().getPluginManager().registerEvents(
				new PlayerInitializer(new PlayerDataFile(getDataFolder())), this);
	}
}

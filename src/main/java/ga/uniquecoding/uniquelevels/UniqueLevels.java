package ga.uniquecoding.uniquelevels;

import dev.jorel.commandapi.CommandAPICommand;
import ga.uniquecoding.uniquelevels.commands.LevelCommand;
import ga.uniquecoding.uniquelevels.commands.PrestigeCommand;
import ga.uniquecoding.uniquelevels.commands.XpCommand;
import ga.uniquecoding.uniquelevels.events.PlayerInitializer;
import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

import static java.util.Comparator.comparingInt;

public final class UniqueLevels extends JavaPlugin
{
	public static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance();

	@Override
	public void onEnable()
	{
		try
		{
			this.saveDefaultConfig();
			this.reloadConfig();

			var config = getConfig();

			var prestiges = getPrestiges(config);

			var dataPath = getDataFolder().toPath();
			var playerFile = new PlayerDataFile(dataPath);
			var dataFetcher = new PlayerDataFetcher(prestiges, config.getInt("levelup-factor"), playerFile);

			getServer().getPluginManager()
					   .registerEvents(
							   new PlayerInitializer(playerFile), this
					   );

			var xp = new XpCommand(playerFile);
			var level = new LevelCommand(dataFetcher);
			var prestige = new PrestigeCommand(dataFetcher);

			registerCommands(xp, level, prestige);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private Collection<Prestige> getPrestiges(FileConfiguration config)
	{
		var prestigesSection = config.getConfigurationSection("prestiges");
		var prestigeNames = prestigesSection.getKeys(false);
		var prestiges = new ArrayList<Prestige>();

		for (var prestigeName : prestigeNames)
		{
			var prestige = prestigesSection.getConfigurationSection(prestigeName);
			var level = prestige.getInt("level");
			var color = prestige.getString("color");

			prestiges.add(new Prestige(prestigeName, level, color));
		}

		prestiges.sort(comparingInt(Prestige::level));

		return prestiges;
	}

	private static void registerCommands(CommandAPICommand... commands)
	{
		for (var command : commands)
		{
			command.register();
		}
	}
}

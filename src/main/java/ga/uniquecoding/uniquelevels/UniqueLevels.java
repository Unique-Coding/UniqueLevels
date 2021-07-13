package ga.uniquecoding.uniquelevels;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandAPIConfig;
import ga.uniquecoding.uniquelevels.commands.LevelCommand;
import ga.uniquecoding.uniquelevels.commands.PrestigeCommand;
import ga.uniquecoding.uniquelevels.commands.XpCommand;
import ga.uniquecoding.uniquelevels.events.PlayerInitializer;
import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;

import static java.util.Comparator.comparingInt;

public final class UniqueLevels extends JavaPlugin
{
	public static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance();

	@Override
	public void onEnable()
	{
		CommandAPI.onEnable(this);
	}

	@Override
	public void onLoad()
	{
		try
		{
			CommandAPI.onLoad(new CommandAPIConfig());

			this.saveDefaultConfig();
			this.reloadConfig();

			var config = getConfig();

			var prestiges = getPrestiges(config);

			var dataPath = getDataFolder().toPath();

			var playerFile = new PlayerDataFile(dataPath);
			var dataFetcher = new PlayerDataFetcher(prestiges, config.getInt("levelup-factor"), playerFile);

			var xp = new XpCommand(playerFile);
			var level = new LevelCommand(dataFetcher);
			var prestige = new PrestigeCommand(dataFetcher);

			registerCommands(xp, level, prestige);

			getServer().getPluginManager()
					   .registerEvents(
							   new PlayerInitializer(playerFile), this
					   );
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
		var out = new ArrayList<Prestige>();

		for (var prestigeName : prestigeNames)
		{
			var prestige = prestigesSection.getConfigurationSection(prestigeName);
			var level = prestige.getInt("level");
			var color = prestige.getString("color");

			out.add(new Prestige(prestigeName, level, color));
		}

		out.sort(comparingInt(Prestige::level));

		return out;
	}

	private static void registerCommands(CommandAPICommand... commands)
	{
		for (var command : commands)
		{
			command.register();
		}
	}
}

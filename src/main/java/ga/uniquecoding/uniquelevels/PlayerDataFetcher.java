package ga.uniquecoding.uniquelevels;

import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Math.log;
import static java.util.Collections.reverse;

public class PlayerDataFetcher
{
	private final Collection<Prestige> prestiges;
	private final int levelingFactor;
	private final PlayerDataFile dataFile;

	public PlayerDataFetcher(Collection<Prestige> prestiges, int levelingFactor, PlayerDataFile dataFile)
	{
		this.prestiges = prestiges;
		this.levelingFactor = levelingFactor;
		this.dataFile = dataFile;
	}

	public int getXp(Player player)
	{
		return dataFile.getXp(player);
	}

	public int getLevel(Player player)
	{
		var xp = getXp(player);

		return (int) (log(xp + 1.0) / log(levelingFactor));
	}

	public Prestige getPrestige(Player player)
	{
		var level = getLevel(player);
		var prestigesCopy = new ArrayList<>(prestiges);
		var out = prestigesCopy.get(0); // Lowest pres

		reverse(prestigesCopy);

		for (var prestige : prestigesCopy)
		{
			if (prestige.level() >= level)
				out = prestige;
		}

		return out;
	}
}

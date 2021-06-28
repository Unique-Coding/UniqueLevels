package ga.uniquecoding.uniquelevels.files;

import ga.uniquecoding.uniquecore.utils.DataFile;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Path;

public final class PlayerDataFile extends DataFile
{
	public PlayerDataFile(Path rootPath) throws IOException
	{
		super(rootPath, "playerdata");
	}

	public void createPlayerData(Player player)
	{
		var uuid = player.getUniqueId();
		var playerSection = data.createSection(uuid.toString());

		playerSection.set("xp", 0);
	}

	public int getXp(Player player)
	{
		return data.getConfigurationSection(player.getUniqueId().toString())
				   .getInt("xp");
	}
}

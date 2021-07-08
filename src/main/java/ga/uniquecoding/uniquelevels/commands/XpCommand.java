package ga.uniquecoding.uniquelevels.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static ga.uniquecoding.uniquelevels.UniqueLevels.NUMBER_FORMAT;

public class XpCommand extends CommandAPICommand
{
	private final PlayerDataFile dataFile;

	public XpCommand(PlayerDataFile dataFile)
	{
		super("xp");
		setArguments(List.of(new PlayerArgument("player")));
		withPermission("levels.admin.xp");
		executes(this::execute);

		this.dataFile = dataFile;
	}

	private void execute(CommandSender sender, Object[] args)
	{
		var target = (Player) args[0];
		var xp = dataFile.getXp(target);
		sender.sendMessage(target.getName() + " has " + NUMBER_FORMAT.format(xp) + " xp");
	}
}

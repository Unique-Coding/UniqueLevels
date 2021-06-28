package ga.uniquecoding.uniquelevels.commands;

import ga.uniquecoding.uniquecore.command.PluginCommandWrapper;
import ga.uniquecoding.uniquecore.command.exceptions.CommandExecutionException;
import ga.uniquecoding.uniquecore.command.exceptions.InvalidUsageException;
import ga.uniquecoding.uniquelevels.PlayerDataFetcher;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import static ga.uniquecoding.uniquelevels.UniqueLevels.NUMBER_FORMAT;

public class LevelCommand extends PluginCommandWrapper
{
	private final PlayerDataFetcher dataFetcher;

	public LevelCommand(PlayerDataFetcher dataFetcher, PluginCommand wrapped)
	{
		super(wrapped);
		this.dataFetcher = dataFetcher;
	}

	@Override
	public void execute(CommandSender sender, String[] args) throws CommandExecutionException
	{
		if (args.length != 1)
			throw new InvalidUsageException("Invalid number of arguments");

		var target = Bukkit.getPlayer(args[0]);

		if (target == null)
			throw new CommandExecutionException("That player is not online");

		var level = dataFetcher.getLevel(target);

		sender.sendMessage(target.getName() + " is level " + NUMBER_FORMAT.format(level));
	}
}

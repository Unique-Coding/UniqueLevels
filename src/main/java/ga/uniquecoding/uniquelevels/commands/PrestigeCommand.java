package ga.uniquecoding.uniquelevels.commands;

import ga.uniquecoding.uniquecore.command.PluginCommandWrapper;
import ga.uniquecoding.uniquecore.command.exceptions.CommandExecutionException;
import ga.uniquecoding.uniquecore.command.exceptions.InvalidUsageException;
import ga.uniquecoding.uniquelevels.PlayerDataFetcher;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

public class PrestigeCommand extends PluginCommandWrapper
{
	private final PlayerDataFetcher dataFetcher;

	public PrestigeCommand(PlayerDataFetcher dataFetcher, PluginCommand wrapped)
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

		var prestige = dataFetcher.getPrestige(target);

		sender.sendMessage(target.getName() + " is a " + prestige.name() + " prestige");
	}
}

package ga.uniquecoding.uniquelevels.commands;

import ga.uniquecoding.uniquecore.command.PluginCommandWrapper;
import ga.uniquecoding.uniquecore.command.exceptions.CommandExecutionException;
import ga.uniquecoding.uniquecore.command.exceptions.InvalidUsageException;
import ga.uniquecoding.uniquecore.command.tabcompletion.ArgumentTabCompleter;
import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import static ga.uniquecoding.uniquecore.command.tabcompletion.Argument.ONLINE_PLAYER;
import static ga.uniquecoding.uniquelevels.UniqueLevels.NUMBER_FORMAT;

public class XpCommand extends PluginCommandWrapper
{
	private final PlayerDataFile dataFile;

	public XpCommand(PlayerDataFile dataFile, PluginCommand wrapped)
	{
		super(wrapped, ArgumentTabCompleter.ofArgs(ONLINE_PLAYER));
		this.dataFile = dataFile;
	}

	@Override
	public void execute(CommandSender sender, String[] args) throws CommandExecutionException
	{
		if (args.length != 1)
			throw new InvalidUsageException("Invalid number of arguments");

		var target = Bukkit.getPlayer(args[0]);

		if (target == null)
			throw new CommandExecutionException("That player is not online");

		var xp = dataFile.getXp(target);

		sender.sendMessage(target.getName() + " has " + NUMBER_FORMAT.format(xp) + " xp");
	}
}

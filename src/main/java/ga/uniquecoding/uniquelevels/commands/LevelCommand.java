package ga.uniquecoding.uniquelevels.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import ga.uniquecoding.uniquelevels.PlayerDataFetcher;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static ga.uniquecoding.uniquelevels.UniqueLevels.NUMBER_FORMAT;

public class LevelCommand extends CommandAPICommand
{
	private final PlayerDataFetcher dataFetcher;

	public LevelCommand(PlayerDataFetcher dataFetcher)
	{
		super("level");
		setAliases(new String[]{"lvl"});
		withArguments(new PlayerArgument("player"));
		withPermission("levels.admin.level");
		executes(this::execute);

		this.dataFetcher = dataFetcher;
	}

	private void execute(CommandSender sender, Object[] args)
	{
		var target = (Player) args[0];
		var level = dataFetcher.getLevel(target);
		sender.sendMessage(target.getName() + " is level " + NUMBER_FORMAT.format(level));
	}
}

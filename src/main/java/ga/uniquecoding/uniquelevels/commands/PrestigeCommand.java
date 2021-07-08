package ga.uniquecoding.uniquelevels.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import ga.uniquecoding.uniquelevels.PlayerDataFetcher;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PrestigeCommand extends CommandAPICommand
{
	private final PlayerDataFetcher dataFetcher;

	public PrestigeCommand(PlayerDataFetcher dataFetcher)
	{
		super("prestige");
		setAliases(new String[]{"pres"});
		setArguments(List.of(new PlayerArgument("player")));

		this.dataFetcher = dataFetcher;
	}

	private void execute(CommandSender sender, Object[] args)
	{

		var target = (Player) args[0];
		var prestige = dataFetcher.getPrestige(target);
		sender.sendMessage(target.getName() + " is a " + prestige.name() + " prestige");
	}
}

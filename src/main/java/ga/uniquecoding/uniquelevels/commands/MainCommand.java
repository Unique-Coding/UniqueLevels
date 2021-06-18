package ga.uniquecoding.uniquelevels.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 0) {
                var player = (Player) sender;

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "" +
                        "&b&lUniqueLevels&8 (Help)" +
                        " \n &7/ul help -&b Shows this help message." +
                        " \n &7/ul set <player> <level/prestige> <amount> -&b Sets the player's level or prestige to the specified amount." +
                        " \n &7/ul level -&b Shows your level and prestige."));
            }
        }
        return false;
    }
}
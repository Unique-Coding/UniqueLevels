package ga.uniquecoding.uniquelevels.commands;

import ga.uniquecoding.uniquelevels.UniqueLevels;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private UniqueLevels uniqueLevels;

    public MainCommand(UniqueLevels uniqueLevels){
        this.uniqueLevels = uniqueLevels;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 0) {
                var player = (Player) sender;

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "" +
                        "&b&lUniqueLevels&8 (HELP)" +
                        " \n &7/ul help -&b Shows this help message." +
                        " \n &7/ul level -&b Shows your level and prestige." +
                        " \n &7/ul reload -&b Reloads the configurations." +
                        " \n &7/ul set <player> <level/prestige> <amount> -&b Sets the player's level or prestige to the specified amount."));
            }
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("uniquelevels.levelscommand.reload")) {
                        uniqueLevels.reload();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReloaded configuration files"));

                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo permission to execute this command"));
                    }
                }
            }
        }
        return false;
    }
}
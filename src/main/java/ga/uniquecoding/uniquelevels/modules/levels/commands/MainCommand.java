package ga.uniquecoding.uniquelevels.modules.levels.commands;

import ga.uniquecoding.uniquecore.command.AbstractCommand;
import ga.uniquecoding.uniquecore.command.exceptions.CommandExecutionException;
import ga.uniquecoding.uniquecore.command.exceptions.InvalidUsageException;
import ga.uniquecoding.uniquelevels.UniqueLevels;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MainCommand extends AbstractCommand {
    public MainCommand(JavaPlugin plugin) {
        super(plugin, "uniquelevels");

    }

    private CommandSender sender;
    private String[] args;

    @Override
    public synchronized void execute(CommandSender sender, String[] args) throws CommandExecutionException {
        this.sender = sender;
        this.args = args;

        switch (args.length) {
            case 0:
                if (args.equal)
                runNoArgs();
                break;
            case 1:
                runOneArg();
                break;
            default:
                throw new InvalidUsageException();
        }
    }

    private UniqueLevels uniqueLevels;

    public MainCommand(UniqueLevels uniqueLevels) {
        this.uniqueLevels = uniqueLevels;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 0) {
                var player = (Player) sender;
                if (player.hasPermission("uniquelevels.levelcommand.admin")) {

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "" +
                            "&bUniqueLevels&3 v" + uniqueLevels.getDescription().getVersion() + " &bby&3 Zaniey" +
                            " \n &f/ul help -&7 Get plugin help." +
                            " \n &f/ul level -&7 Check your level." +
                            " \n &f/ul prestige -&7 Check your prestige." +
                            " \n &f/ul progress -&7 Check your progress." +
                            " \n &f/ul reload -&7 Reload the configurations." +
                            " \n &f/ul set <player> <level/prestige> <amount> -&7 Set player's levels or prestige."));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "" +
                            "&bUniqueLevels&3 v" + uniqueLevels.getDescription().getVersion() + " &bby&3 Zaniey" +
                            " \n &7/ul help -&b Get plugin help." +
                            " \n &7/ul level -&b Check your level." +
                            " \n &7/ul prestige -&b Check your prestige." +
                            " \n &7/ul progress -&b Check your progress."));
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
        }
        return false;
    }
}
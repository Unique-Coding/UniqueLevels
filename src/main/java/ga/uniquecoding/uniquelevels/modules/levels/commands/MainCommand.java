package ga.uniquecoding.uniquelevels.modules.levels.commands;

import ga.uniquecoding.uniquecore.UniqueCore;
import ga.uniquecoding.uniquecore.command.AbstractCommand;
import ga.uniquecoding.uniquecore.command.exceptions.CommandExecutionException;
import ga.uniquecoding.uniquecore.command.exceptions.InvalidUsageException;
import ga.uniquecoding.uniquecore.command.tabcompletion.OnlinePlayerTabCompleter;
import ga.uniquecoding.uniquecore.utils.Utils;
import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import static org.bukkit.Bukkit.getPlayer;


public class MainCommand extends AbstractCommand {

    private final JavaPlugin plugin;

    public MainCommand(JavaPlugin plugin) {
        super(plugin, "uniquelevels");
        this.plugin = plugin;

        setTabCompleter(new OnlinePlayerTabCompleter(1));
    }

    private CommandSender sender;
    private String[] args;
    private PlayerDataFile playerDataFile;

    @Override
    public synchronized void execute(CommandSender sender, String[] args) throws CommandExecutionException {
        this.sender = sender;

        switch (args.length) {
            case 0:
                runNoArgs();
                break;
            case 1:
                runOneArg();
                break;
            case 3:
                try {
                    runMoreArgs();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new InvalidUsageException();
        }
    }

    private void runNoArgs() {
        Player player = (Player) sender;

        if (player.hasPermission("uniquelevels.admin")) {

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "" +
                    "&bUniqueLevels&3 v" + plugin.getDescription().getVersion() + " &bby&3 Zaniey" +
                    " \n &f/ul help -&7 Get plugin help." +
                    " \n &f/ul level -&7 Check your level." +
                    " \n &f/ul prestige -&7 Check your prestige." +
                    " \n &f/ul progress -&7 Check your progress." +
                    " \n &f/ul reload -&7 Reload the configurations." +
                    " \n &f/ul set <player> <level/prestige> <amount> -&7 Set player's levels or prestige."));
        } else {

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "" +
                    "&bUniqueLevels&3 v" + UniqueCore.server().getVersion() + " &bby&3 Zaniey" +
                    " \n &7/ul help -&b Get plugin help." +
                    " \n &7/ul level -&b Check your level." +
                    " \n &7/ul prestige -&b Check your prestige." +
                    " \n &7/ul progress -&b Check your progress."));
        }
    }

    private void runOneArg() {
        Player player = (Player) sender;
        if (player.hasPermission("uniquelevels.reload")) {

            switch (args[0]) {
                case "reload":
                    plugin.reloadConfig();
                    sender.sendMessage(Utils.colorCode("&aReloaded configuration files"));
            }
        }
    }

    private void runMoreArgs() throws CommandExecutionException, IOException {
        Player targetName = getPlayer(args[0]);
        Player player = (Player) sender;
        String type = args[1];

        if (targetName == null)
            throw new CommandExecutionException(targetName.getName() + " is not online");

        if (player.hasPermission("uniquelevels.admin")) {

            switch (args[0]) {
                case "set":
                    if (type.equalsIgnoreCase("level")) {
                        playerDataFile.setLevel(targetName.getUniqueId(), Integer.parseInt(args[3]));
                    } else if (type.equalsIgnoreCase("prestige")) {
                        targetName.sendMessage("Your mom :))");
                    }
                case "give":
                    if (type.equalsIgnoreCase("level")) {
                        playerDataFile.giveLevel(targetName.getUniqueId(), Integer.parseInt(args[3]));
                    }
            }
        }
    }
}
package ga.uniquecoding.uniquelevels.modules.levels;

import ga.uniquecoding.uniquecore.command.CommandModule;
import ga.uniquecoding.uniquelevels.files.DataFile;
import ga.uniquecoding.uniquelevels.modules.levels.commands.MainCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

public class LevelsModule extends CommandModule {

    private final FileConfiguration config;

    public LevelsModule(JavaPlugin plugin, File storageFolder) {
        this.config = plugin.getConfig();

        DataFile dataFile = new DataFile(storageFolder);

        commands.addAll(Arrays.asList(
                new MainCommand(plugin)
        ));
    }
}

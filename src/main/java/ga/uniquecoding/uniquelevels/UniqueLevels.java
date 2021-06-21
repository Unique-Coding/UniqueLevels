package ga.uniquecoding.uniquelevels;

import ga.uniquecoding.uniquecore.command.CommandManager;
import ga.uniquecoding.uniquecore.command.CommandModule;
import ga.uniquecoding.uniquelevels.events.PlayerInitializer;
import ga.uniquecoding.uniquelevels.files.PlayerDataFile;
import ga.uniquecoding.uniquelevels.modules.levels.LevelsModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public final class UniqueLevels extends JavaPlugin {

    @Override
    public void onEnable() {
    /*
        All the enable stuff.
    */
        this.saveDefaultConfig();
        this.reloadConfig();
        this.registerCommands();
        getServer().getPluginManager().registerEvents(
                new PlayerInitializer(new PlayerDataFile(getDataFolder())), this);

    }

    private void registerCommands() {
        var storageFolder = new File(getDataFolder() + "/data/");

        CommandModule[] modules = {
                new LevelsModule(this, storageFolder)
        };

        for (CommandModule m : modules)
            CommandManager.getInstance().register(m.getCommands());
    }

    /*
        Reload the configuration and other stuff.
    */
    public void reload() {
        this.reloadConfig();
    }
}
